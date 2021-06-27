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
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
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
    private Label validpass;
    @FXML
    private Label fillFields;
    @FXML
    private TextField fullName;
    @FXML
    private ChoiceBox<String> cb = new ChoiceBox<>();
    @FXML
    private Button back;
    @FXML
    private Label successed;
    @FXML
    private Button addProfileButton;
    @FXML
    private ImageView profileImg;
    public byte[] profileImageByteArray;
    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);
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
            
        }
        Matcher matcher = pattern.matcher(password);
        if(password.length()<8 || !matcher.matches()){
            validpass.setVisible(true);
            

        }
        int birth = Integer.parseInt(birthyear);
        if( API.doesUserNameExists(username))
        {
            usernameTaken.setVisible(true);
            
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

        Profile justCreatedProfile = new Profile(username,password,birth,fullname,option,recover, profileImageByteArray);
        if(API.signUp(justCreatedProfile)){
            System.out.println("happy happy me!");
            successed.setVisible(true);
        }
        if(Main.currentUser == null)
            Main.currentUser = justCreatedProfile;

        
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);
        a.setContentText("WellCome to SBUGRAM!");
        a.show();

        Main.newfxml("page1.fxml");

    }

    public void addProfile(ActionEvent actionEvent) {
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
            profileImageByteArray=imageToByteArray;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        profileImg.setImage(image);
    }

    public void backToLogin(ActionEvent event){
         sbu.client.Main.newfxml("page1.fxml");
    }
    
}