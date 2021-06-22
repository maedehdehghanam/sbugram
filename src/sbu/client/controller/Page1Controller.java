package sbu.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class Page1Controller {
    
    @FXML
    private Label label;
    @FXML 
    private Button loginButton;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    } 
    public void loginController(ActionEvent event){
        System.out.println(userField.getText() + " " + passField.getText());
        sbu.client.Main.fxmlYarooKone("timeline.fxml");

    } 
}
