package sbu.client;
import sbu.common.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static Profile currentUser = null;
    public static Post mainPost = null;
    public static Stage fStage=null;
    @Override
    public void start(Stage stage) throws Exception {
        fStage=stage;
        newfxml("page1.fxml");
        
    }
    public static void newfxml(String fmxlPath){
       try{
            Parent root = FXMLLoader.load(new File("./src/sbu/client/resources/fxmls/" + fmxlPath).toURI().toURL());
            Scene scene = new Scene(root);
            fStage.setTitle("Sbu Gram");
            fStage.setScene(scene);
            fStage.show();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        launch(args);

    }

}
