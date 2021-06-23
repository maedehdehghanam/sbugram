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
public class Page1Controller {
    
    @FXML
    private Label label;
    @FXML
    private Button signUpButton;
    @FXML
    private Label fillFields;
    @FXML 
    private Button loginButton;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button forgotPassButton;
    @FXML
    private Label notFound;
    @FXML
    private CheckBox showpassCheck;
    @FXML
    private TextField showpassField;
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    } 
    public void connectToServer(){
        try{
            if ( !Connector.isConnected() ){
                Connector.connectToServer();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loginController(ActionEvent event){
        connectToServer();
        notFound.setVisible(false);
        label.setVisible(false);
        fillFields.setVisible(false);

        String usernameCheck = userField.getText();
        String passwordCheck;
        if(passField.isVisible()){
            passwordCheck = passField.getText();
        }
        else{
            passwordCheck = showpassField.getText();
        }

        if(usernameCheck.isEmpty()|| passwordCheck.isEmpty()){
            fillFields.setVisible(true);
            return;
        }
        if(! API.doesUserNameExists(usernameCheck))
        {
            notFound.setVisible(true);
            return;
        }
        Profile profile = API.login(usernameCheck,passwordCheck);
        if(profile == null){
            //showInvalidLoginDialog();
            label.setVisible(true);
            return;
        }
        //sbu.client.Main.newFxml("timeline.fxml");*/
    } 
    public void showPass(){
        if(!showpassField.isVisible()){
            showpassField.setVisible(true);
            passField.setVisible(false);
            showpassField.setText(passField.getText());
        } else {
            showpassField.setVisible(false);
            passField.setVisible(true);
            passField.setText(showpassField.getText());
        }
    }
    public void forgotpassControl(){
         sbu.client.Main.newfxml("forgotpass.fxml");
    }
    public void signupControl(ActionEvent event){
        sbu.client.Main.newfxml("signup.fxml");

    }
}
