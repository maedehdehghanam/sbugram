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
	private ListView postList;

	private ArrayList<Post> posted = API.updateTimeline(Main.checkingUser);
	public void initialize() {

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
        username.setText(Main.checkingUser.getUserName());
        fullname.setText(Main.checkingUser.getName());
        following.setText(String.valueOf(Main.checkingUser.getFollowings().size()));
        followers.setText(String.valueOf(Main.checkingUser.getFollowers().size()));
        birthyear.setText(String.valueOf(Main.checkingUser.getBirthyear()));
    	
    }

    public void setPost(Post chosen){
    	title.setText(chosen.getTitle());
    	caption.setText(chosen.getCaption());
    }
    public void showPost(MouseEvent event){
    	postImage.setImage(null);
    	int index = (postList.getSelectionModel().getSelectedIndex());
    	setPost(posted.get(index));
    	if(posted.get(index).postImage()!=null){
            postImage.setImage(new Image(new ByteArrayInputStream(posted.get(index).postImage())));
        } else{
        	postImage.setImage(null);
        }


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
    }

}