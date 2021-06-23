package sbu.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Page1Controller {
    
    @FXML
    private Label label;
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
    //fucking comment
    public void loginController(ActionEvent event){
        label.setVisible(false);
        fillFields.setVisible(false);
        String usernameCheck = userField.getText();
        String passwordCheck =  passField.getText();
        if(usernameCheck.isEmpty()|| passwordCheck.isEmpty()){
            fillFields.setVisible(true);
        }
        else if(usernameCheck.equals("ali") && passwordCheck.equals("1111")){
            System.out.println("hey!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"WELLCOME!");
            alert.showAndWait();
        }else{
            label.setVisible(true);
        }
        //sbu.client.Main.fxmlYarooKone("timeline.fxml");
        //wait(100000);
    } 
}
