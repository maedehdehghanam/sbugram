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
	public static Profile forgotpass(String username){
		Map<String,Object> recieved = Connector.serve(Map.of("username",username,"command", Command.FORGOT_PASS));
		return (Profile) recieved.get("profile");
	}
	public static void sendPost(Post thePost){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.POST);
		toSend.put("post",thePost);
		toSend.put("poster",thePost.getPoster());
		Connector.serve(toSend);
	}
	public static Post updatePost(Post p){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.UPDATEPOST);
		toSend.put("post",p);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (Post) recieved.get("updatedPost");
	}
	public static Profile updateUser(Profile p){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.UPDATEUSER);
		toSend.put("profile",p);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (Profile) recieved.get("updatedUser");
	}
	public static void repost(Post thePost,Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.REPOST);
		toSend.put("post",thePost);
		toSend.put("reposter",profile);
		Connector.serve(toSend);
	}
	public static void updateProfile(String userName, String password,String name ,String change,Object changeable)
	{
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.UPDATE_PROFILE);
		toSend.put("username",userName);
		toSend.put("password",password);
		toSend.put("name", name);
		toSend.put("change",change);
		toSend.put("changeable",changeable);
		Connector.serve(toSend);

	}
	public static ArrayList<Post> updateTimeline(Profile user ){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.UPDATE);
		toSend.put("profile",user);
		Map<String,Object> recieved = Connector.serve(toSend);
		return  (ArrayList<Post>) recieved.get("timelinePosts");
	}
	public static Boolean like(Post post,Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("profile",profile);
		toSend.put("post", post);
		toSend.put("command", Command.LIKE);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static Boolean follow(Profile follow,Profile profile){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("profile",profile);
		toSend.put("follow", follow);
		toSend.put("command", Command.FOLLOW);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}

	public static ArrayList<Profile> getAllUsers(){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("command", Command.GETALLUSERS);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (ArrayList<Profile>) recieved.get("allusers");
	}
	public static Boolean comment(Post post,Comment cm){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("comment",cm);
		toSend.put("post", post);
		toSend.put("command",Command.COMMENT);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static Boolean deletePost(Post post){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("post", post);
		toSend.put("command",Command.DELETE_POST);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static Boolean editPost(Post post,String newCaption){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("post", post);
		toSend.put("caption",newCaption);
		toSend.put("command",Command.EDIT_POST);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (boolean) recieved.get("success");
	}
	public static List<Comment> getComments(Post post){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("post", post);
		toSend.put("command",Command.GETCOMMENTS);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (List<Comment>) recieved.get("commentslist");
	}
	public static List<Profile> getLikes(Post post){
		Map<String,Object> toSend = new HashMap<>();
		toSend.put("post", post);
		toSend.put("command",Command.GETLIKES);
		Map<String,Object> recieved = Connector.serve(toSend);
		return (List<Profile>) recieved.get("likeslist");
	}
}