package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;
import sbu.server.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;

import java.io.File;
import java.net.URL;
import java.util.*;
public class ForgotPassController {
	@FXML
	private TextField userField;
	@FXML
	private Button recoverButton;
	@FXML
    private Label notFound;
    @FXML
    private TextField backupPass;
    @FXML
    private TextField backupKey;
    @FXML
    private TextField setNewPass;
    @FXML
    private Label passIsWrong;
    @FXML
    private Label passSet;
    private Profile forgotenPassProfile = null;
    private String backupKEY;
    private String question = null;
	public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    } 
    public void recoverPass(ActionEvent event){
    	notFound.setVisible(false);
    	passIsWrong.setVisible(false);
    	String usernameCheck = userField.getText();
    	if(! sbu.client.API.doesUserNameExists(usernameCheck) )
        {
            notFound.setVisible(true);
            return;
        }
        forgotenPassProfile = sbu.client.API.forgotpass(usernameCheck);
        RecoverOptions option = forgotenPassProfile.getRecoverOption();
        switch(option){
        	case DREAM_JOB :
        		question = "what is your dream job?";
        		break;
			case MOM_BIRTHDAY :
				question = "when is your mom's birthday?";
				break;
			case BEST_VACATION :
				question = "where did you have your best vacation?";
				break;
			case CHILDHOOD_HERO:
				question = "who was your childhood hero?";
				break;
			case FIRST_TEACHER :
				question = "what was your first teacher's name?";
				break;
        }
        backupPass.setVisible(true);
        backupPass.setText(question);
        backupKey.setVisible(true);
        setNewPass.setVisible(true);
        //ok.setVisible(true);
        
        

    }
    public void checkPass(ActionEvent event){

    	if(backupKey.getText().equals(forgotenPassProfile.getPassRecover())){
        	sbu.client.API.updateProfile(forgotenPassProfile.getUserName(),forgotenPassProfile.getPassword(),
                forgotenPassProfile.getName(),"password",setNewPass.getText());

        } else{
        	passIsWrong.setVisible(true);
        }
    }

}