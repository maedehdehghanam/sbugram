package sbu.client.controller;

import sbu.common.*;
import sbu.client.*;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
public class SearchController{
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Profile> tableview;
	@FXML
	private TableColumn<Profile,String> usernameColumn;
	@FXML
	private TableColumn<Profile,String> namesColumn;
	/*@FXML
	private Button search;
	@FXML
	private Button s;*/
	@FXML 
	private Button timeline;
	private ObservableList<Profile> dataList = FXCollections.observableArrayList();

	private List<Profile> allUsers = API.getAllUsers();
	public void initialize() {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
		allUsers = API.getAllUsers();
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("iusername")); 
		namesColumn.setCellValueFactory(new PropertyValueFactory<>("name")); 
		dataList.addAll(allUsers);
		 FilteredList<Profile> filteredData = new FilteredList<>(dataList, b -> true);
		 System.out.println(allUsers.size());
		 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getIusername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Profile> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview.setItems(sortedData);
    }
    public void searchiii(ActionEvent e){
    	ObservableList<Profile> selectedItems = tableview.getSelectionModel().getSelectedItems();
    	Main.checkingUser = API.forgotpass(selectedItems.get(0).getUserName() );
    	Main.newfxml("Profile.fxml");

    }
    public void timeline(ActionEvent e){
    	sbu.client.Main.newfxml("timeline.fxml");
    }


}