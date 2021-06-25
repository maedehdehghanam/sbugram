package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;
//import sbu.server.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
	@FXML
	private Button logout;
	private Profile user = Main.currentUser; 
	private ArrayList<Post> posted = user.getAllPosts();
	private Post currentPost = null;
	public void initialize() {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
        for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    }
    public void hey(ActionEvent event){
    	System.out.println(posted.size());
    	currentPost = new Post(null,caption.getText(),user,title.getText());
    	posted.add(currentPost);
    	sbu.client.API.sendPost(currentPost);
    	postList.getItems().add(currentPost.toString());
    	title.setText("");
    	caption.setText("");
    }
    public void showPost(MouseEvent event){
    	publish.setVisible(false);
    	int index = (postList.getSelectionModel().getSelectedIndex());
    	setPost(posted.get(index));
    	
    }
    public void setPost(Post chosen){
    	title.setText(chosen.getTitle());
    	caption.setText(chosen.getCaption());
    }
    public void newPost(ActionEvent event){
    	title.setText("");
    	caption.setText("");
    	publish.setVisible(true);
    }
    public void logout(ActionEvent e){
    	boolean a =  API.logout();
    }


}