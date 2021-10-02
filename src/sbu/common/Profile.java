package sbu.common;

import java.io.*;
import java.util.*;

public class Profile implements Serializable {
  //private static final long serialVersionUID = 8452845068004156728L;
	private String username;
	private String name;
	private String password;
  private String iusername;
  private String status = "";
  private String hobbys="";
  private String place="";
	private final int birthyear;
	private final RecoverOptions option;
	private final String passRecover;
	private ArrayList<Profile> follwers = new ArrayList<>();
  private ArrayList<Profile> followings = new ArrayList<>();
  private ArrayList<Post> posts = new ArrayList<>();
  private ArrayList<Post> allPosts;
  private byte[] profileImage;
	public Profile(String userName, String password, 
		int birthyear,String name,RecoverOptions option ,String passRecover,byte[] profileImage){
		this.username =  userName;
    this.name = name;
		this.password =  password;
		this.birthyear =  birthyear;
		this.option = option;
		this.passRecover = passRecover;
    this.profileImage = profileImage;
    this.allPosts = new ArrayList<>();
    iusername = username;
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
    public void setHobby(String hobby){
      this.hobbys = hobby;
    }
    public String getHobby(){
      return hobbys;
    }
    public void setStatus(String status){
      this.status = status;
    }
     public String getStatus(){
      return status;
    }
    public void setPlace(String place){
      this.place = place;
    }
    public String getPlace(){
      return place;
    }
    public void setNewProfilepic(byte[] chosenprofile){
      profileImage = chosenprofile;
    }
    public byte[] getProfilepic(){
      return profileImage;
    }
    public RecoverOptions getRecoverOption(){
      return option;
    }
    public String getIusername(){
      return iusername;
    }
    public String getPassRecover(){
      return passRecover;
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
    public ArrayList<Post> getAllPosts(){
      allPosts = new ArrayList<>();
      for (int i=0;i<followings.size();++i ) {
        allPosts.addAll(followings.get(i).getPosts());
      }
      allPosts.addAll(posts);
      return allPosts;
    }
    public void setNewPassword(String newPassword){
    	this.password = newPassword;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username){
      this.username = username;
        }
    public int getBirthyear(){
      return (birthyear);
    }
    public ArrayList<Post> getPosts(){
      return posts;
    }
    public boolean equals(Profile p){
      if(p.getUserName().equals(username))
        return true;
      return false;
    }
    public void post(Post thePost){
      posts.add(thePost);
    }
    public void deletePost(Post post){
      posts.remove(post);
    }
    public List<Profile> getFollowings(){
      return followings;
    }
    public List<Profile> getFollowers(){
      return follwers;
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