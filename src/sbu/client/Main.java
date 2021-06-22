package sbu.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    public static Stage fStage=null;
    @Override
    public void start(Stage stage) throws Exception {
        fStage=stage;
        fxmlYarooKone("page1.fxml");
        
    }
    public static void fxmlYarooKone(String fmxlPath){
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
