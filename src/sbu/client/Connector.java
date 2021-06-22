package sbu.client;

import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
public class Connector{
	public static String serverAddress;
	public static final int PORT = 2727;

	private static boolean isConnected = false;
	public static Socket socket;
	public static ObjectInputStream socketIn;
	public static ObjectOutputStream socketOut;
	public static boolean isConnected(){
		return isConnected;
	}
	public static Boolean connectToServer(){
		if(socket != null) 
			return false;
		try{
			System.out.println("server's ip is : " + serverAddress);
			socket = new Socket( serverAddress, PORT);
			socketOut = new ObjectOutputStream( socket.getOutputStream() );
			socketIn = new ObjectInputStream( socket.getInputStream() );
			isConnected = true;
			return true;

		}catch (ConnectException e){
		}
		catch (IOException e) {
		}
		return false;
	}
	public static Boolean disconnectFromServer() throws Exception{
		try{
			socketIn.close();
			socketOut.close();
			socket.close();
			isConnected = false;

			socket = null;
			socketIn = null;
			socketOut = null;

			return true;
		}
		catch (SocketException | NullPointerException e ){
		}
		catch( Exception e){
			e.printStackTrace();
		}
		socket = null;
		socketIn = null;
		socketOut = null;
		return false;
	}
	//serve method has not made yet
}