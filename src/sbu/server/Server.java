package sbu.server;

import sbu.common.Command;
import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class Server{
	public static final int PORT = 2727;
	private static boolean isServerUp = true;
	public static Map<String, Profile> profiles = null;
	
	public static boolean isServerUp(){
		return isServerUp;
	}
	public static void main(String[] args) throws Exception{
		DBManager.getInstance().initializeServer();
		ServerSocket server = new ServerSocket(PORT);
		while ( isServerUp() ){
			Socket currentUserSocket = server.accept();
			ClientHandler clientHandler=new ClientHandler(currentUserSocket);
			new Thread( clientHandler ).start();
		}
	}
}