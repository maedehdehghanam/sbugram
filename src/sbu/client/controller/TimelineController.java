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
	private ListView likeList;
	@FXML
	private ListView commentList;
	@FXML
	private Button logout;
	@FXML
	private Button like;
	@FXML
	private Button repost;

	@FXML
	private Button unlike;
	@FXML
	private Button unrepost;
	@FXML
	private TextField commentfield;

	@FXML
	private Button comment;
	@FXML
	private Button timelineposts;
	private Profile user = Main.currentUser; 
	private ArrayList<Post> posted = user.getAllPosts();
	private Post currentPost = null;
	public void initialize() {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	like.setVisible(false);
    	comment.setVisible(false);
    	repost.setVisible(false);
    	unlike.setVisible(false);
    	unrepost.setVisible(false);
    	commentfield.setVisible(false);
    	publish.setVisible(true);
    }
    //button settings for selecting type of posts
    public void setButtonSelectedPost(){
    	like.setVisible(true);
    	comment.setVisible(true);
    	repost.setVisible(true);
    	commentfield.setVisible(true);
    	publish.setVisible(false);
    }
    public void setButtonNewPost(){
    	like.setVisible(false);
    	comment.setVisible(false);
    	repost.setVisible(false);
    	unlike.setVisible(false);
    	unrepost.setVisible(false);
    	commentfield.setVisible(false);
    	publish.setVisible(true);
    }
    //making posting part ready for new post
    public void newPost(ActionEvent event){
    	setButtonNewPost();
    	commentList.setVisible(false);
    	likeList.setVisible(false);
    	postList.setVisible(true);
    	title.setText("");
    	caption.setText("");
    	publish.setVisible(true);

    }
    //publishin a new post
    public void hey(ActionEvent event){
    	setButtonNewPost();
    	System.out.println(posted.size());
    	currentPost = new Post(null,caption.getText(),user,title.getText());
    	posted.add(currentPost);
    	sbu.client.API.sendPost(currentPost);
    	postList.getItems().add(currentPost.toString());
    	title.setText("");
    	caption.setText("");
    	posted = user.getAllPosts();
    }
    //showing a selcted post from timeline
    public void showPost(MouseEvent event){
    	posted = user.getAllPosts();
    	setButtonSelectedPost();
    	int index = (postList.getSelectionModel().getSelectedIndex());
    	setPost(posted.get(index));
    	Main.mainPost = posted.get(index);
    	if (Main.mainPost.getLikedPeople().contains(Main.currentUser)) {
    		unlike.setVisible(true);
    		like.setVisible(false);
    	} else{
    		like.setVisible(true);
    		unlike.setVisible(false);
    	}
    	if(Main.currentUser.getPosts().contains(Main.mainPost)){
    		unrepost.setVisible(true);
    		repost.setVisible(false);
    	} else {
    		repost.setVisible(true);
    		unrepost.setVisible(false);
    	}
    	
    }
    //setting post 
    public void setPost(Post chosen){
    	title.setText(chosen.getTitle());
    	caption.setText(chosen.getCaption());
    }
    //+ text fields
    public void likeThePost(ActionEvent e){
    	if((Main.mainPost.getLikedPeople()).contains(Main.currentUser)){
    		boolean y = API.like(Main.mainPost,Main.currentUser);
    		//Main.mainPost.getLikedPeople().remove(Main.currentUser);
    		unlike.setVisible(false);
    		like.setVisible(true);
    	}else{
    		boolean x = API.like(Main.mainPost,Main.currentUser);
    		//Main.mainPost.getLikedPeople().add(Main.currentUser);
    		like.setVisible(false);
    		unlike.setVisible(true);
    	}
    	System.out.println(Main.mainPost.getLikedPeople().size());
    	posted = user.getAllPosts();
    }
    public void commentForThePost(ActionEvent e){
    	posted = user.getAllPosts();
    	setButtonSelectedPost();
    	commentList.getItems().clear();
    	commentList.setVisible(true);
    	likeList.setVisible(false);
    	postList.setVisible(false);
    	for (Comment p : Main.mainPost.getComments() ) {
    		commentList.getItems().add(p.toString());
    	}
    	if((!commentfield.getText().equals("")) || commentfield.getText()==null){
	    	Comment c = new Comment(Main.currentUser,Main.mainPost.getPoster(),commentfield.getText());
	    	boolean x = API.comment(Main.mainPost,c);
	    	commentList.getItems().add(c.toString());
    	}
    	commentfield.setText("");
    }
    public void repostThePost(ActionEvent e){
    	if(Main.currentUser.getPosts().contains(Main.mainPost)){
			boolean y = API.like(Main.mainPost,Main.currentUser);
			unrepost.setVisible(true);
    		repost.setVisible(false);
    	}else{
    		boolean x = API.like(Main.mainPost,Main.currentUser);
    		repost.setVisible(true);
    		unrepost.setVisible(false);
    	}
    	posted = user.getAllPosts();
    }
    //showing timeline
    public void showTimeLine(ActionEvent e){
    	posted = user.getAllPosts();
    	setButtonNewPost();
    	postList.getItems().clear();
    	for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	commentList.setVisible(false);
    	likeList.setVisible(false);
    	postList.setVisible(true);
    	title.setText("");
    	caption.setText("");
    	publish.setVisible(true);
    	commentList.setVisible(false);
    	likeList.setVisible(false);
    	postList.setVisible(true);
    }
    //showing who has liked the post
    public void showPostLike(MouseEvent event){
    	posted = user.getAllPosts();
    	setButtonSelectedPost();
    	likeList.getItems().clear();
    	commentList.setVisible(false);
    	likeList.setVisible(true);
    	postList.setVisible(false);
    	for (Profile p : Main.mainPost.getLikedPeople() ) {
    		likeList.getItems().add(p.getUserName());
    	}
    }
    // showing commentsunder the post
    /*public void showPostComments(ActionEvent e){
    	setButtonSelectedPost();
    	commentList.setVisible(true);
    	likeList.setVisible(false);
    	postList.setVisible(false);
    	for (Comment p : Main.mainPost.getComments() ) {
    		commentList.getItems().add(p.toString());
    	}
    }*/
    //logging out 
    public void logout(ActionEvent e){
    	boolean a =  API.logout();
    }


}