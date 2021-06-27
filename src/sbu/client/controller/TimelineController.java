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
	private Button login;
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
	@FXML
	private Button chooseButton;
   /* @FXML
    private Button search;*/
    @FXML
    private Button s;
	@FXML
    private ImageView chosenImage;
    @FXML
    private Button profileCheck;
    @FXML
    private ImageView posterPic;
    //@FXML
    //private ImageView likeImage;

    public byte[] chosenimageByteArray = null;
	private Profile user = Main.currentUser; 
	private ArrayList<Post> posted = user.getAllPosts();
	private Post currentPost = null;
	public void initialize() {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	if(user.getProfilepic()!=null){
            posterPic.setImage(new Image(new ByteArrayInputStream(user.getProfilepic())));
        } else{
        	posterPic.setImage(null);
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
    	chooseButton.setVisible(true);
    }
    //making posting part ready for new post
    public void newPost(ActionEvent event){
    	updateTimeLine();
    	setButtonNewPost();
    	if(user.getProfilepic()!=null){
    		System.out.println("not null!");
            posterPic.setImage(new Image(new ByteArrayInputStream(user.getProfilepic())));
        } else{
        	posterPic.setImage(null);
        }
    	chosenImage.setImage(null);
    	chooseButton.setVisible(true);
    	commentList.setVisible(false);
    	likeList.setVisible(false);
    	postList.setVisible(true);
    	chosenimageByteArray = null;
    	title.setText("");
    	caption.setText("");
    	publish.setVisible(true);
    	chooseButton.setVisible(true);

    }
    //publishin a new post
    public void hey(ActionEvent event){
    	setButtonNewPost();
    	System.out.println(posted.size());
    	currentPost = new Post(chosenimageByteArray,caption.getText(),user,title.getText());
    	posted.add(currentPost);
    	sbu.client.API.sendPost(currentPost);
    	postList.getItems().clear();
    	posted = user.getAllPosts();
    	for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    	postList.getItems().add(currentPost.toString());
 		chosenimageByteArray = null;
    	title.setText("");
    	caption.setText("");
    	chooseButton.setVisible(false);
    	publish.setVisible(false);
    	updateTimeLine();
    	
    }
    //chose image
    public void addImage(ActionEvent actionEvent) {
        Stage stage=new Stage();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        byte[] imageToByteArray;
        try {
            imageToByteArray= Files.readAllBytes(file.toPath());
            chosenimageByteArray=imageToByteArray;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        chosenImage.setImage(image);
    }
    //showing a selcted post from timeline
    public void showPost(MouseEvent event){
    	updateTimeLine();
    	chooseButton.setVisible(false);
    	chosenImage.setImage(null);
    	posted = user.getAllPosts();
        updateTimeLine();
    	setButtonSelectedPost();
    	int index = (postList.getSelectionModel().getSelectedIndex());
    	setPost(posted.get(index));
    	Main.mainPost = posted.get(index);

    	if(posted.get(index).getPoster().getProfilepic()!=null){
            posterPic.setImage(new Image(new ByteArrayInputStream(posted.get(index).getPoster().getProfilepic())));
        } else{
        	posterPic.setImage(null);
        }

    	if(posted.get(index).postImage()!=null){
            chosenImage.setImage(new Image(new ByteArrayInputStream(posted.get(index).postImage())));
        } else{
        	chosenimageByteArray = null;
        }
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
    	updateTimeLine();
        Main.currentUser = API.updateUser(Main.currentUser);
        Main.mainPost = API.updatePost(Main.mainPost);
    	if((Main.mainPost.getLikedPeople()).contains(Main.currentUser)){
    		boolean y = API.like(Main.mainPost,Main.currentUser);
    		unlike.setVisible(false);
    		like.setVisible(true);
    	}else{
    		boolean x = API.like(Main.mainPost,Main.currentUser);
    		like.setVisible(false);
    		unlike.setVisible(true);
    	}
    	updateTimeLine();
    	System.out.println(Main.mainPost.getLikedPeople().size());
    	postList.getItems().clear();
    	posted = user.getAllPosts();
    	for (Post p : posted ) {
    		postList.getItems().add(p.toString());
    	}
    }
    public void commentForThePost(ActionEvent e){
    	updateTimeLine();
    	setButtonSelectedPost();
    	commentList.getItems().clear();
    	commentList.setVisible(true);
    	likeList.setVisible(false);
    	postList.setVisible(false);
    	for (Comment p : API.getComments(Main.mainPost) ) {
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
        Main.currentUser = API.updateUser(Main.currentUser);
        Main.mainPost = API.updatePost(Main.mainPost);
    	if(Main.currentUser.getPosts().contains(Main.mainPost)){
			API.repost(Main.mainPost,Main.currentUser);
			unrepost.setVisible(true);
    		repost.setVisible(false);
    	}else{
    		API.like(Main.mainPost,Main.currentUser);
    		repost.setVisible(true);
    		unrepost.setVisible(false);
    	}
    	posted = user.getAllPosts();
    }
    //showing timeline
    public void showTimeLine(ActionEvent e){
    	updateTimeLine();
    	setButtonNewPost();
    	if(user.getProfilepic()!=null){
            posterPic.setImage(new Image(new ByteArrayInputStream(user.getProfilepic())));
        } else{
        	posterPic.setImage(null);
        }
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
    	chooseButton.setVisible(true);
    	commentList.setVisible(false);
    	likeList.setVisible(false);
    	postList.setVisible(true);
    }
    //showing who has liked the post
    public void showPostLike(ActionEvent event){
    	updateTimeLine();
    	setButtonSelectedPost();
    	likeList.getItems().clear();
    	commentList.setVisible(false);
    	likeList.setVisible(true);
    	postList.setVisible(false);
    	for (Profile p :API.getLikes(Main.mainPost) ) {
    		likeList.getItems().add(p.getUserName());
    	}
    	updateTimeLine();
    }
    //
    public void profileChecking(ActionEvent e){
    	Main.checkingUser = API.updateUser(Main.mainPost.getPoster());
    	Main.newfxml("Profile.fxml");
    }
    //get updet posts
    public void updateTimeLine(){
    	if(user.getProfilepic()!=null){
            posterPic.setImage(new Image(new ByteArrayInputStream(user.getProfilepic())));
        } else{
        	posterPic.setImage(null);
        }
    	posted = API.updateTimeline(Main.currentUser);
    	chosenImage.setImage(null);
    }
    //search usernames
    public void search(ActionEvent e){
        Main.newfxml("Search.fxml");
    }
    //back to login page
    public void login(ActionEvent e){
    	Main.newfxml("page1.fxml");
    }
    //logging out 
    public void logout(ActionEvent e){
    	boolean a =  API.logout();
        Alert a2 = new Alert(AlertType.NONE);
        a2.setAlertType(AlertType.INFORMATION);
        a2.setContentText("Hope you enjoyed SBUGRAM!");
        a2.show();
         System.exit(0);
    }



}