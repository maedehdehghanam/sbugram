package sbu.server;

import sbu.common.*; 

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class ClientHandler implements Runnable{

	private Socket userSocket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	public Boolean clientOnline = true;

	public ClientHandler(Socket socket){
		try{
			userSocket = socket;
			this.socketIn = new ObjectInputStream (socket.getInputStream() );
			this.socketOut = new ObjectOutputStream (socket.getOutputStream() );
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	@Override
	//some apis are yet too be made
	public void run(){
		while(clientOnline){
			Map<String,Object> income = null;
			try{
				income = (Map<String,Object>) socketIn.readObject();
				Map<String,Object> answer = null;
				Command command = (Command) income.get("command");
				switch(command){
					case CHECK_USERNAME:
						answer = API.doesUserNameExists(income);
						break;
					case LOGIN:
						answer = API.login(income);
						break;
					case LOGOUT:
						answer = API.logout(income);
						clientOnline = false;
						break;
					case SIGNUP:
						answer = API.signup(income);
						break;
					case POST:
						answer = API.post(income);
						break;
					case LIKE:
						answer = API.like(income);
						break; 
					case UNLIKE :
						answer = API.unlike(income);
						break;
					case COMMENT :
						answer = API.comment(income);
						break;
					case DELETE_POST:
						answer = API.deletePost(income);
						break;
					case EDIT_POST:
						answer = API.editPost(income);
						break;
 				}
				socketOut.writeObject(answer);
				//socketOut.flush();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			socketIn.close();
			socketOut.close();
			userSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}