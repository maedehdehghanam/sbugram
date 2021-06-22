package sbu.server;

import sbu.common.*;
import sbu.client.*;

import java.io.*;
import java.util.concurrent.*;
public class DBManager{

	private static DBManager theDBManager = new DBManager();
	private static final String profilesPath = "src/sbu/recourses/data/ProfilesDB.txt";
	//private final 

	private DBManager(){};
	public static DBManager getInstance(){
		return theDBManager;
	}
	public synchronized void initializeServer(){
		try{
			File file = new File(profilesPath);
			if(file.length == 0){
				Server.profiles = new ConcurrentHashMap<>();
				file.close();
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
	}
	public synchronized void updateDataBase(){
		try{
            FileOutputStream fout = new FileOutputStream(PROFILES_FILE);
            ObjectOutputStream objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(ServerEXE.profiles); //writing profiles
            objToFile.close();
            fout.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
    }
}