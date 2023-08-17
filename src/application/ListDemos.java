package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ListDemos extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Fruit Program");
				
		BorderPane pane = new BorderPane();
				
		// Create the menus
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		Menu helpMenu = new Menu("Help");
		
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		
		MenuItem loadItem = new MenuItem("Load");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem closeItem = new MenuItem("Close");
		MenuItem aboutItem = new MenuItem("About");
		
		fileMenu.getItems().addAll(loadItem, saveItem, closeItem);
		helpMenu.getItems().add(aboutItem);
		
		// Add the menus to the top of the pane
		pane.setTop(menuBar);
		
		// Create the button and add it to the bottom of the pane
		Button button = new Button("Add Fruit");
		BorderPane.setAlignment(button, Pos.CENTER);
		pane.setBottom(button);
		
		// create the list
		ListView<String> list = new ListView<String>();	
		
		// create an observable list for adding fruit items to
		ObservableList<String> obsList = FXCollections.observableArrayList();
		list.setItems(obsList);	
	
		//add the listview to the centre of the pane
		pane.setCenter(list);
		
		// add the action listeners
		button.setOnAction((ActionEvent e) -> {
			TextInputDialog textDialog = new TextInputDialog();
			textDialog.showAndWait();
			String result = textDialog.getResult();
			
			// add any results to the observable list
			if (result != null) {
				obsList.add(result);
			}
		});
				
		closeItem.setOnAction((ActionEvent e) -> {
			stage.close();
		});
		
		// the help menu about tab
		aboutItem.setOnAction((ActionEvent e) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About Fruit Program");
			alert.setHeaderText("Information about the Fruit Program");
			alert.setContentText("This program is designed to manage your list of fruits. Never forget an important piece of fruit on your next shopping trip EVER again! \n"
					+ "(C) Jason Howarth 2022");
			alert.show();
		});
		
		// save the file
		saveItem.setOnAction((ActionEvent e) -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Save As");
			File selectedFile = chooser.showSaveDialog(stage); // choose the location in windows explorer
			if (selectedFile == null) // no file chosen
				return;
			
			// convert the observablelist to an ArrayList for serialization
			ArrayList<String> savedList = new ArrayList<String>(obsList);
			
			try {
				serialize(selectedFile, savedList); // saves the list to the file
				System.out.println("File selected: " + selectedFile);
			} catch (IOException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Something went wrong");
				alert.setContentText(e1.getMessage());
				alert.showAndWait();
			}		
		});
		
		// loads the file
		loadItem.setOnAction((ActionEvent e) -> {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Open Fruit File");
			File selectedFile = chooser.showOpenDialog(stage); // choose the file from windows explorer
			if (selectedFile == null) // no file chosen
				return;
		
			ArrayList<String> savedList;
			try {
				savedList = deserialize(selectedFile); // loads the file
				obsList.clear();
				obsList.addAll(savedList); // adds the list to the observable list
			} catch (ClassNotFoundException | IOException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Something went wrong");
				alert.setContentText(e1.getMessage());
			}
			
		});
		
		Scene scene = new Scene(pane, 400, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	// deserialize the file
	public ArrayList<String> deserialize(File file) throws IOException, ClassNotFoundException {
		FileInputStream fIs = new FileInputStream(file);
		ObjectInputStream objectIn = new ObjectInputStream(fIs);
		System.out.println("Returning the object: " + objectIn);
		ArrayList<String> listOfFruits = (ArrayList<String>) objectIn.readObject();
		objectIn.close();
		return listOfFruits;
	}
	
	// serialize the file
	public void serialize(File f, ArrayList<String> list) throws IOException {
		FileOutputStream fOs = new FileOutputStream(f);
		ObjectOutputStream objectOut = new ObjectOutputStream(fOs);
		objectOut.writeObject(list);
		System.out.println("This is the object: " + objectOut);
		objectOut.close();
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    // Save file
	}	

	public static void main(String[] args) {
		Application.launch(args);
	}

}