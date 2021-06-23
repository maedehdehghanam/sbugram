package sbu.client;

import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class API{
	public static boolean doesUserNameExists(String check){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.CHECK_USERNAME);
		toSend.put("username",check);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("answer");
	}
	public static Profile login(String username, String password){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.LOGIN);
		toSend.put("username",username);
		toSend.put("password",password);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("answer") == null ) return null;
		return (Profile) recieved.get("answer");
	}
	public static Boolean signUp(Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.SIGNUP);
		toSend.put("profile", profile);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("answer") == null ) return null;
		return (Boolean) recieved.get("answer");
	}
	public static Boolean logout(){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.LOGOUT);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("answer") == null ) return false;
		return (Boolean) recieved.get("answer");
	}
	public static void sendPost(Post thePost){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.POST);
		toSend.put("post",thePost);
		Connector.serve(toSend);
	}
}