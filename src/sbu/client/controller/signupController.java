package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;

import java.io.File;
import java.net.URL;
import java.util.*;
public class signupController {
	//@FXML
	//private ChoiceBox cb ;
	@FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private TextField birthYear;
    @FXML
    private Button signup;
    @FXML
    private TextField recoverPass;
    @FXML
    private Label usernameTaken;
    @FXML
    private Label ageError;
    @FXML
    private Label fillFields;
    @FXML
    private TextField fullName;
    @FXML
    private ChoiceBox<String> cb = new ChoiceBox<>();
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
        cb.getItems().add("What is your dream job?");
        cb.getItems().add("When is your mom's birthday?");
        cb.getItems().add("Wher did you hsve your best vacation?");
        cb.getItems().add("Who was your childhood hero?");
        cb.getItems().add("What was your first teacher's name?");
    } 
    public void signupButton(ActionEvent event){
        fillFields.setVisible(false);
        usernameTaken.setVisible(false);
        ageError.setVisible(false);
        String username = userField.getText();
        String password = passField.getText();
        String birthyear= birthYear.getText();
        String fullname = fullName.getText();
        String recover = recoverPass.getText();
        if( recover.isEmpty() || username.isEmpty() || birthyear.isEmpty()
         || fullname.isEmpty() || password.isEmpty()){
            fillFields.setVisible(true);
            return;
        }
        int birth = Integer.parseInt(birthyear);
        if( API.doesUserNameExists(username))
        {
            usernameTaken.setVisible(true);
            return;
        }
        if(2021-birth<18){
            ageError.setVisible(true);
            return;
        }

        //Profile justCreatedProfile = new Profile(username,password,birth,name,option,recover);
        //if(API.signUp(justCreatedProfile))

        //Command chosen = 
    }

    
}