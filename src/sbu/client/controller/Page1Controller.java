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

        label.setVisible(false);
        fillFields.setVisible(false);

        String usernameCheck = userField.getText();
        String passwordCheck =  passField.getText();

        if(usernameCheck.isEmpty()|| passwordCheck.isEmpty()){
            fillFields.setVisible(true);
            return;
        }
        Profile profile = API.login(usernameCheck,passwordCheck);
        if(profile == null){
            //showInvalidLoginDialog();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"WELLCOME!");
        alert.showAndWait();
        label.setVisible(true);
        /*
        else if(usernameCheck.equals("ali") && passwordCheck.equals("1111")){
            System.out.println("hey!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"WELLCOME!");
            alert.showAndWait();
        }else{
            label.setVisible(true);
        }
        //sbu.client.Main.fxmlYarooKone("timeline.fxml");*/
    } 
    /*
    public void signupController(ActionEvent event){

    }*/
}
