package sbu.server;

import sbu.common.*;
//import sbu.client.*;

import java.io.*;
import java.util.concurrent.*;
public class DBManager{

	private static DBManager theDBManager = new DBManager();
	private static final String profilesPath = "DataBase/ProfilesDB.bin";
	private static final String postsPath = "DataBase/PostsDB.bin";

	private DBManager(){};
	public static DBManager getInstance(){
		return theDBManager;
	}
	public synchronized void initializeServer(){
		try{
			File file = new File(profilesPath);
			if(file.length() == 0){
				Server.profiles = new ConcurrentHashMap<>();
			}
			else{
			    FileInputStream fin=new FileInputStream(DBManager.profilesPath);
			    ObjectInputStream inFromFile=new ObjectInputStream(fin);
			    Server.profiles = new ConcurrentHashMap<>( (ConcurrentHashMap<String, Profile>) inFromFile.readObject());
			    inFromFile.close();
			    fin.close();
			}
	  	}catch(Exception e){
	  		e.printStackTrace();
	  	}
	  	try {
	  		File file = new File(postsPath);
	  		if(file.length() == 0){
	  			 Server.posts = new ConcurrentSkipListSet<>();
	  		}
	  		else{
			    FileInputStream fin = new FileInputStream(DBManager.postsPath);
			    ObjectInputStream inFromFile = new ObjectInputStream(fin);
			    ServerEXE.mails = new ConcurrentSkipListSet<>( (ConcurrentSkipListSet<Mail>) inFromFile.readObject());
			    inFromFile.close();
			    fin.close();
			}
  		}catch(Exception e){
  			e.printStackTrace();
  		}
	}
	public synchronized void updateDataBase(){
		try{
            FileOutputStream fout = new FileOutputStream(profilesPath);
            ObjectOutputStream objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(Server.profiles); //writing profiles
            objToFile.close();
            fout.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        try{
        	FileOutputStream fout = new FileOutputStream(postsPath);
            ObjectOutputStream objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(Server.profiles); //writing posts
            objToFile.close();
            fout.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}