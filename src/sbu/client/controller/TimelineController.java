package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;
import sbu.server.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.net.URL;
import java.util.*;
public class TimelineController{
	@FXML
	private TextArea caption;
	@FXML
	private TextField title;
	@FXML 
	private Button publish;
	@FXML
	private ListView postList;
	private Profile user = Main.currentUser; 
	private ArrayList<Post> posted = new ArrayList<>();
	private Post currentPost = null;
	public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }
    public void hey(ActionEvent event){
    	System.out.println("hh");
    	currentPost = new Post(null,caption.getText(),user,title.getText());
    	posted.add(currentPost);
    	sbu.client.API.sendPost(currentPost);
    	postList.getItems().add(currentPost.getTitle());
    	title.setText("");
    	caption.setText("");
    }



}