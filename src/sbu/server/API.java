package sbu.server;

import sbu.common.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
public class API{
	public static Map<String,Object> doesUserNameExists(Map<String,Object> income){

		String check = (String) income.get("username");
		Profile profile = Server.profiles.get(check);
		Boolean exists = (profile != null);

		Map<String,Object> ans = new HashMap<>();
		ans.put("answer",exists);
		ans.put("command",Command.CHECK_USERNAME);

		return ans;
	}
	public static Map<String,Object> login(Map<String,Object> income){

		String username = (String) income.get("username");
		String password = (String) income.get("password");

		Boolean isNullProfile = (Server.profiles.get(username) == null);
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.LOGIN);
		ans.put("exists",!isNullProfile);
		if(isNullProfile){
			return ans;
		}
		Profile profile = Server.profiles.get(username).authenticate(username, password);
		ans.put("answer",profile);

		if(profile != null){
			System.out.println(profile.getUserName() + " signin");
			System.out.println("time : "+Time.getTime());
		}
		return ans;
	}
	public static Map<String,Object> signup(Map<String,Object> income){
		Profile newUser = (Profile) income.get("profile");
		String username = newUser.getUserName();
		Server.profiles.put(username,newUser);
		DBManager.getInstance().updateDataBase(); 
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.SIGNUP);
		ans.put("answer",new Boolean(true));

		System.out.println(newUser.getUserName() + " register"); 
		System.out.println("time : "+Time.getTime());
		System.out.println(newUser.getUserName() + " signin");
		System.out.println("time : "+Time.getTime());

		return ans;
	}
	public static Map<String,Object> logout(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.LOGOUT);
		ans.put("answer",new Boolean(true));
		return ans;
	}
}