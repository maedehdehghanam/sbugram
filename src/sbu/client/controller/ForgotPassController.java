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
	public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        //label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    } 
    public void recoverPass(ActionEvent event){
    	notFound.setVisible(false);
    	String usernameCheck = userField.getText();
    	if(! sbu.client.API.doesUserNameExists(usernameCheck) )
        {
            notFound.setVisible(true);
            return;
        }
        RecoverOptions option =  RecoverOptions.DREAM_JOB;
        // sbu.server.Server.profiles.get(usernameCheck).getRecoverOption();
        String question = null;
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

    }

}