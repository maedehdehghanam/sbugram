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
    @FXML
    private Button back;
    public void connectToServer(){
        try{
            if ( !Connector.isConnected() ){
                Connector.connectToServer();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void initialize() {
        connectToServer();
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
        //default value
        cb.setValue("What is your dream job?");
        cb.getItems().add("What is your dream job?");
        cb.getItems().add("When is your mom's birthday?");
        cb.getItems().add("Where did you have your best vacation?");
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
        String chosedQ = cb.getValue();
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
        RecoverOptions option=null;
        if(chosedQ.equals("What is your dream job?"))
        {
            option=RecoverOptions.DREAM_JOB;
        }
        else if(chosedQ.equals("When is your mom's birthday?")){
            option = RecoverOptions.MOM_BIRTHDAY;
        }
        else if(chosedQ.equals("Where did you have your best vacation?")){
            option = RecoverOptions.BEST_VACATION;
        }
        else if(chosedQ.equals("Who was your childhood hero?")){
            option = RecoverOptions.CHILDHOOD_HERO;
        }
        else if(chosedQ.equals("What was your first teacher's name?")){
            option = RecoverOptions.FIRST_TEACHER;
        }

        Profile justCreatedProfile = new Profile(username,password,birth,fullname,option,recover);
        if(API.signUp(justCreatedProfile))
            System.out.println("happy happy me!");
        if(Main.currentUser == null)
            Main.currentUser = justCreatedProfile;

    }
    public void backToLogin(ActionEvent event){
         sbu.client.Main.newfxml("page1.fxml");
    }
    
}