package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;

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
import java.io.*;
import java.util.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
public class ProfileController{
	@FXML
	private ImageView thePic;
	@FXML
	private ImageView postImage;
	@FXML
	private TextField username;
	@FXML
	private TextField birthyear;
	@FXML
	private TextField fullname;
	@FXML
	private TextField following;
	@FXML
	private TextField followers;
	@FXML
	private TextField commentfield;
	@FXML
	private TextField status;
	@FXML
	private TextField hobby;
	@FXML
	private TextField place;
	@FXML
	private TextArea caption;
	@FXML
	private TextField title;
	@FXML
	private Button logout;
	@FXML 
	private Button login;
	@FXML
	private Button timelineposts;
	@FXML
	private Button search;
	@FXML
	private Button allPosts;
	@FXML
	private Button like;
	@FXML 
	private Button edit;
	@FXML
	private Button comment;
	@FXML
	private Button flw;
	@FXML
	private Button block;
	@FXML
	private Button mute;
	@FXML
	private Button showFollower;
	@FXML
	private Button showFollowing;
	@FXML
	private ListView postList;
	@FXML
	private ListView likeList;
	@FXML
	private ListView commentList;
	@FXML
	private ListView followersList;
	@FXML
	private ListView followingsList;
	@FXML
	private Label samePlace;
	private Post post;
	private ArrayList<Post> posted = new  ArrayList<Post>();
	public void initialize() {
		if(API.getUserPost(Main.checkingUser) != null)
			posted = API.getUserPost(Main.checkingUser);
		commentList.setVisible(false);
    	likeList.setVisible(false);
    	followersList.setVisible(false);
    	followingsList.setVisible(false);
    	postList.setVisible(true);
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	if(Main.checkingUser.getProfilepic()!=null){
            thePic.setImage(new Image(new ByteArrayInputStream(Main.checkingUser.getProfilepic())));
        } else{
        	thePic.setImage(null);
        }
        username.setText("username: " + Main.checkingUser.getUserName());
        fullname.setText("name: "+Main.checkingUser.getName());
        following.setText("followings: "+String.valueOf(Main.checkingUser.getFollowings().size()));
        followers.setText("followers: "+String.valueOf(Main.checkingUser.getFollowers().size()));
        birthyear.setText("Birthyear: "+String.valueOf(Main.checkingUser.getBirthyear()));
        hobby.setText("Hobby: " + Main.checkingUser.getHobby());
		status.setText("Status: "+ Main.checkingUser.getStatus());
		place.setText("Place: "+ Main.checkingUser.getPlace());
        if(Main.checkingUser.getUserName().equals(Main.currentUser.getUserName())){
        	edit.setVisible(true);
			flw.setVisible(false);
			block.setVisible(false);
			mute.setVisible(false);
        } else{
        	edit.setVisible(false);
			flw.setVisible(true);
			block.setVisible(true);
			mute.setVisible(true);
        }
        if(Main.currentUser.getPlace().equals(Main.checkingUser.getPlace()) && !Main.currentUser.getPlace().equals("")){
        	samePlace.setVisible(true);
        }
    	
    }

    public void setPost(Post chosen){
    	title.setText(chosen.getTitle());
    	caption.setText(chosen.getCaption());
    }
    public void showPost(MouseEvent event){
    	postImage.setImage(null);
    	int index = (postList.getSelectionModel().getSelectedIndex());
    	setPost(posted.get(index));
    	post = posted.get(index);
    	if(posted.get(index).postImage()!=null){
            postImage.setImage(new Image(new ByteArrayInputStream(posted.get(index).postImage())));
        } else{
        	postImage.setImage(null);
        }


    }
    //comment for post
    public void commentForThePost(ActionEvent e){
    	commentList.getItems().clear();
    	commentList.setVisible(true);
    	likeList.setVisible(false);
    	postList.setVisible(false);
    	followersList.setVisible(false);
    	followingsList.setVisible(false);
    	for (Comment p : API.getComments(post) ) {
    		commentList.getItems().add(p.toString());
    	}
    	if((!commentfield.getText().equals("")) || commentfield.getText()==null){
	    	Comment c = new Comment(Main.currentUser,post.getPoster(),commentfield.getText());
	    	boolean x = API.comment(post,c);
	    	commentList.getItems().add(c.toString());
    	}
    	commentfield.setText("");
    }
    //like the post
    public void likeThePost(ActionEvent e){
    	post = API.updatePost(post);
    	if((post.getLikedPeople()).contains(Main.currentUser)){
    		boolean y = API.like(post,Main.currentUser);
    		//unlike.setVisible(false);
    		like.setVisible(true);
    	}else{
    		boolean x = API.like(post,Main.currentUser);
    		like.setVisible(false);
    		//unlike.setVisible(true);
    	}
    	System.out.println(post.getLikedPeople().size());
    	postList.getItems().clear();
    	posted =  API.getUserPost(Main.checkingUser);
    	for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    }
    //show all posts
    public void showAllPosts(ActionEvent e){
    	postList.getItems().clear();
    	if(API.getUserPost(Main.checkingUser) != null)
			posted = API.getUserPost(Main.checkingUser);
		
		for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	postList.setVisible(true);
    	likeList.setVisible(false);
    	commentList.setVisible(false);
    	followersList.setVisible(false);
    	followingsList.setVisible(false);
    }
    //show followers
     public void showFollowers(ActionEvent e){
    	ArrayList<Profile> follower = API.getFollowers(Main.checkingUser);
		followersList.getItems().clear();
		for (Profile p : follower ) {
    		followersList.getItems().add(p.getUserName());
    	}
    	postList.setVisible(false);
    	likeList.setVisible(false);
    	commentList.setVisible(false);
    	followersList.setVisible(true);
    	followingsList.setVisible(false);
    }
    //show followings
    public void showFollowings(ActionEvent e){
    	ArrayList<Profile> following = API.getFollowings(Main.checkingUser);
		followingsList.getItems().clear();
		for (Profile p : following ) {
    		followingsList.getItems().add(p.getUserName());
    	}
    	postList.setVisible(false);
    	likeList.setVisible(false);
    	commentList.setVisible(false);
    	followersList.setVisible(false);
    	followingsList.setVisible(true);
    }
    //follow!
    public void follow(ActionEvent e){
    	Main.currentUser = API.updateUser(Main.currentUser);
    	if(API.follow(Main.checkingUser,Main.currentUser)){
    		followers.setText("followers: "+String.valueOf(API.updateUser(Main.checkingUser).getFollowers().size()));
    	}
    	Main.checkingUser = API.updateUser(Main.checkingUser);

    }
    //editprofile 
    public void editProfile(ActionEvent e){
    	sbu.client.Main.newfxml("EditProfile.fxml");
    }
    //back to time line
    public void timeline(ActionEvent e){
    	sbu.client.Main.newfxml("timeline.fxml");
    }
    //login page
    public void login(ActionEvent e){
    	Main.newfxml("page1.fxml");
    }
    //logging out 
    public void logout(ActionEvent e){
    	boolean a =  API.logout();
    	Alert a3 = new Alert(AlertType.NONE);
        a3.setAlertType(AlertType.INFORMATION);
        a3.setContentText("Hope you enjoyed SBUGRAM!");
        a3.show();
    	 System.exit(0);
    }
    public void searchPage(ActionEvent e){
        Main.newfxml("Search.fxml");
    }
    	

}