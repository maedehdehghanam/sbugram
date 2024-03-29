package sbu.client;

import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
public class Connector{
	public static String serverAddress = "localhost";
	public static final int PORT = 2727;

	private static boolean isConnected = false;
	public static Socket socket;
	public static ObjectInputStream socketIn;
	public static ObjectOutputStream socketOut;
	public static boolean isConnected(){
		return isConnected;
	}
	public static Boolean connectToServer() throws Exception{
		if(socket != null){ 
			return false;}
		
		try{
			System.out.println("server's ip is : " + serverAddress);
			System.out.println(PORT);
			socket = new Socket( serverAddress, PORT);
			socketOut = new ObjectOutputStream( socket.getOutputStream() );
			socketIn = new ObjectInputStream( socket.getInputStream() );
			isConnected = true;
			return true;
		}catch ( IOException e){
			e.printStackTrace();
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
			e.printStackTrace();
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
	public static Map<String,Object> serve(Map<String,Object> toSend){
		Map<String,Object> recieved = null;
		try{
			socketOut.writeObject(toSend);
			socketOut.flush();
			socketOut.reset();
			recieved = (Map<String,Object>) socketIn.readObject();
			return recieved;

		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch( IOException e){
			e.printStackTrace();
		}
		return recieved;
	}
}