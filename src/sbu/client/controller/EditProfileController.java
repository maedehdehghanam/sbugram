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
public class EditProfileController{
	@FXML
	private TextField fullname;
	@FXML
	private TextField birthyear;
	@FXML
	private TextField status;
	@FXML
	private TextField hobby;
	@FXML
	private TextField place;
	@FXML
	private TextField username;
	@FXML
	private ImageView thePic;
	@FXML
	private Button addNewImage;
	public byte[] profileImageByteArray;
	 byte[] newimageToByteArray;
	String usernamee;
	String userhobby;
	String userplace;
	String userstatus;
	String password;
	String userName;
	public void initialize() {
		String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
		if(Main.currentUser.getProfilepic()!=null){
            thePic.setImage(new Image(new ByteArrayInputStream(Main.currentUser.getProfilepic())));
        } else{
        	thePic.setImage(null);
        }
        username.setText(Main.currentUser.getUserName());
        fullname.setText(Main.currentUser.getName());
        usernamee = Main.currentUser.getName();
        birthyear.setText(String.valueOf(Main.currentUser.getBirthyear()));
        userhobby = Main.currentUser.getHobby();
        place.setText(Main.currentUser.getPlace());
        userplace =  Main.currentUser.getPlace() ;
        userstatus = Main.currentUser.getStatus() ;
        status.setText(Main.currentUser.getStatus());
        profileImageByteArray = Main.currentUser.getProfilepic();
        password = Main.currentUser.getPassword();
        userName = Main.currentUser.getUserName();


	}
	//save changes
	public void saveChanges(ActionEvent e){
		if(!usernamee.equals(fullname.getText())){
			sbu.client.API.updateProfile(userName,password,usernamee,"name",fullname.getText());
		}
		if(!userhobby.equals(hobby.getText())){
			sbu.client.API.updateProfile(userName,password,usernamee,"hobbys",hobby.getText());
		}
		if(!userplace.equals(place.getText())){
			sbu.client.API.updateProfile(userName,password,usernamee,"place",place.getText());
		}
		if(!userstatus.equals(status.getText())){
			sbu.client.API.updateProfile(userName,password,usernamee,"status",status.getText());
		} 
		if(! Arrays.equals(newimageToByteArray,(profileImageByteArray))){
			sbu.client.API.updateProfile(userName,password,usernamee,"profileImage",newimageToByteArray);
		}
		Main.currentUser = API.updateUser(Main.currentUser);
		hobby.setText(Main.currentUser.getHobby());
		fullname.setText(Main.currentUser.getName());
		status.setText(Main.currentUser.getStatus());
		place.setText(Main.currentUser.getPlace());
		sbu.client.Main.newfxml("timeline.fxml");

	}



	//add new photo
	public void addNewProfile(ActionEvent actionEvent) {
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
            newimageToByteArray=imageToByteArray;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        thePic.setImage(image);
    }

	//forgot pass?
	public void forgotpassControl(){
         sbu.client.Main.newfxml("forgotpass.fxml");
    }
	//back to time line
    public void timeline(ActionEvent e){
    	sbu.client.Main.newfxml("timeline.fxml");
    }
}