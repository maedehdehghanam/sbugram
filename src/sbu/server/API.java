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
		ans.put("success",exists);
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
		ans.put("success",profile);

		if(profile != null){
			System.out.println(profile.getUserName() + " Login");
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
		ans.put("success",new Boolean(true));

		System.out.println(newUser.getUserName() + " register"); 
		System.out.println("time : "+Time.getTime());
		System.out.println(newUser.getUserName() + " signin");
		System.out.println("time : "+Time.getTime());

		return ans;
	}
	public static Map<String,Object> logout(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.LOGOUT);
		ans.put("success",new Boolean(true));
		return ans;
	}
	public static Map<String,Object> post(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.POST);
		Post posting = (Post) income.get("post");
		Profile sender = (Profile) income.get("poster");
		if(posting == null){
			ans.put("success", new Boolean(false));
			System.out.println("-> Posting failed! Invalid file has been chosen.");
		}
		else{
			//().post(posting);
			Server.profiles.get(sender.getUserName()).post(posting);
			System.out.println( Server.profiles.get(sender.getUserName()).getPosts().size());
			//Server.posts.add(posting);
			DBManager.getInstance().updateDataBase();
			ans.put("success", new Boolean(true));
			System.out.println("->Posting was successful!");
		}
		return ans;
	}
	public static Map<String,Object> like(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.LIKE);
		Post thePost = (Post) income.get("post");
		Profile liker = (Profile) income.get("profile");
		thePost.likePost(liker);
		DBManager.getInstance().updateDataBase();
		ans.put("success", new Boolean(true));
		return ans;
	}
	public static Map<String,Object> unlike(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.UNLIKE);
		Post thePost = (Post) income.get("post");
		Profile liker = (Profile) income.get("profile");
		thePost.unlikePost(liker);
		DBManager.getInstance().updateDataBase();
		ans.put("success", new Boolean(true));
		return ans;
	}
	public static Map<String,Object> forgotpass(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.FORGOT_PASS);
		String username = (String) income.get("username");
		Profile profile = Server.profiles.get(username);
		ans.put("profile",profile);
		ans.put("success", new Boolean(true));
		return ans;
	}
	public static Map<String,Object> comment(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.COMMENT);
		Post thePost = (Post) income.get("post");
		Comment theComment = (Comment) income.get("comment");
		thePost.addComment(theComment);
		DBManager.getInstance().updateDataBase();
		ans.put("success", new Boolean(true));
		return ans;
	}
	public static Map<String,Object> updateProfile(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.UPDATE_PROFILE);
		String changing = (String) income.get("change");
		Profile person = Server.profiles.get((String) income.get("username"));
		if(changing.equals("username")){
			person.setUsername((String) income.get("changeable"));
		}
		else if(changing.equals("password")){
			person.setNewPassword((String) income.get("changeable"));
		}
		else if(changing.equals("name")){
			person.setName((String) income.get("changeable"));
		}
		DBManager.getInstance().updateDataBase();
		return ans;
	}
	public static  Map<String,Object> deletePost(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.DELETE_POST);
		Post thePost = (Post) income.get("post");
		(thePost.getPoster()).deletePost(thePost);
		//Server.posts.remove(thePost);
		thePost.deletePost();
		DBManager.getInstance().updateDataBase();
		ans.put("success", new Boolean(true));
		return ans;
	}
	public static Map<String,Object> editPost(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.EDIT_POST);
		Post thePost = (Post) income.get("post");
		String newCaption = (String) income.get("caption");
		thePost.editPost(newCaption);
		thePost.deletePost();
		DBManager.getInstance().updateDataBase();
		ans.put("success", new Boolean(true));
		return ans;
	}
	/*public static Map<String,Object> follow(Map<String,Object> income){
		Map<String,Object> ans = new HashMap<>();
		ans.put("command",Command.FOLLOW);
		Profile follow = (Profile) income.get("following");
		Profile follower =(Profile) in
	}*/
}