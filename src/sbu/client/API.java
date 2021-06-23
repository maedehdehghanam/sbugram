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
		return (boolean) recieved.get("success");
	}
	public static Profile login(String username, String password){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.LOGIN);
		toSend.put("username",username);
		toSend.put("password",password);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("success") == null ) return null;
		return (Profile) recieved.get("success");
	}
	public static Boolean signUp(Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.SIGNUP);
		toSend.put("profile", profile);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("success") == null ) return null;
		return (Boolean) recieved.get("success");
	}
	public static Boolean logout(){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.LOGOUT);
		Map<String,Object> recieved = Connector.serve(toSend);
		if ( recieved.get("success") == null ) return false;
		return (Boolean) recieved.get("success");
	}
	public static void sendPost(Post thePost){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.POST);
		toSend.put("post",thePost);
		Connector.serve(toSend);
	}
	public static Boolean like(Post post,Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("profile",profile);
		toSend.put("post", post);
		toSend.put("command", Command.LIKE);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static Boolean unlike(Post post,Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("profile",profile);
		toSend.put("post", post);
		toSend.put("command", Command.UNLIKE);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static Boolean comment(Post post,Comment cm){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("comment",cm);
		toSend.put("post", post);
		toSend.put("command",Command.COMMENT);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}

}