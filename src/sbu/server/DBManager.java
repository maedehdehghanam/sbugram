package sbu.server;

import sbu.common.*;
//import sbu.client.*;

import java.io.*;
import java.util.concurrent.*;
public class DBManager{

	private static DBManager theDBManager = new DBManager();
	private static final String profilesPath = "DataBase/ProfilesDB";
	private static final String postsPath = "DataBase/PostsDB";

	private DBManager(){};
	public static DBManager getInstance(){
		return theDBManager;
	}
	public synchronized void initializeServer(){
		try {
		    FileInputStream fin=new FileInputStream(DBManager.profilesPath);
		    ObjectInputStream inFromFile=new ObjectInputStream(fin);
		    Server.profiles = new ConcurrentHashMap<>( (ConcurrentHashMap<String, Profile>) inFromFile.readObject());
		    inFromFile.close();
		    fin.close();
		    }catch(EOFException | StreamCorruptedException e){
    			Server.profiles = new ConcurrentHashMap<>();
            }catch (Exception e){
    			e.printStackTrace();
  			}
  			/*try {
			    FileInputStream fin = new FileInputStream(DBManager.postsPath);
			    ObjectInputStream inFromFile = new ObjectInputStream(fin);
			    Server.posts = new ConcurrentSkipListSet<>( (ConcurrentSkipListSet<Post>) inFromFile.readObject());
			    inFromFile.close();
			    fin.close();
			  }
			  catch(EOFException | StreamCorruptedException e){
			    Server.posts = new ConcurrentSkipListSet<>();
			  }catch (Exception e){
			    e.printStackTrace();
			  }*/
	}
	public synchronized void updateDataBase(){
		  try {
		  		System.out.println(Server.profiles.get("a").getAllPosts().size());
			      FileOutputStream fout = new FileOutputStream(profilesPath);
			      ObjectOutputStream objToFile = new ObjectOutputStream(fout);
			      objToFile.writeObject(Server.profiles); //writing profiles
			      objToFile.close();
			      fout.close();
			      //for (int i =0 ;i<Server.profiles.size() ;++i ) {
			      	//System.out.println(Server.profiles.get("a").getPosts().size());
			      //}
			      /*fout = new FileOutputStream(postsPath);
			      objToFile = new ObjectOutputStream(fout);
			      objToFile.writeObject(Server.posts); // writing mails
			      objToFile.close();
			      fout.close();*/
			  } catch (IOException e) {
			    e.printStackTrace();
			  }

    }
}