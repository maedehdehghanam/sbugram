package sbu.common;

import java.io.*;
import java.util.*;

public class Profile implements Serializable {
	private String username;
	private String name;
	private String password;
	private int birthyear;
	private RecoverOptions option;
	private final String passRecover;
	private List<Profile> follwers;
  private List<Profile> followings;
  private List<Post> posts;
	public Profile(String userName, String password, 
		int birthyear,String name,RecoverOptions option ,String passRecover){
		this.username =  userName;
    this.name = name;
		this.password =  password;
		this.birthyear =  birthyear;
		this.option = option;
		this.passRecover = passRecover;
	}
	public int hashCode() {
        return username.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
      if(obj == null)
        return false;
      try{
        return this.username.equals(((Profile)obj).getUserName());
      }
      catch(Exception e){
        return false;
      }
    }
    public String toString() {
        return "" + username + ": " + name + "\nbirth year: " + birthyear + "";
    }
    public RecoverOptions getRecoverOption(){
      return option;
    }
    public String getUserName() {
        return username;
    }
    public String getName(){
    	return name;
    }
    public String getPassword() {
        return password;
    }
    public void setNewPassword(String newPassword){
    	this.password = newPassword;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBirthyear(){
      return (birthyear);
    }
    public void post(Post thePost){
      posts.add(thePost);
    }
    public void follow(Profile follow){
      followings.add(follow);
    }
    public Profile authenticate(String username,String password){
      if(this.username.equals(username) && this.password.equals(password)) 
        return this;
      else
        return null;
    }
}