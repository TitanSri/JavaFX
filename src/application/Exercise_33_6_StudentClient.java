package application;

import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/*
 * Cannot get the object to return
 */

public class Exercise_33_6_StudentClient extends Application {
	  private TextField tfName = new TextField();
	  private TextField tfStreet = new TextField();
	  private TextField tfCity = new TextField();
	  private TextField tfState = new TextField();
	  private TextField tfZip = new TextField();

	  // Button for sending a student to the server
	  private Button btRegister = new Button("Register to the Server");
	  private Button btAdd = new Button("Add");
	  private Button btFirst = new Button("First");
	  private Button btNext = new Button("Next");
	  private Button btPrevious = new Button("Previous");
	  private Button btLast = new Button("Last");

	  // Host name or ip
	  String host = "localhost";

	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    GridPane pane = new GridPane();
	    pane.add(new Label("Name"), 0, 0);
	    pane.add(tfName, 1, 0);    
	    pane.add(new Label("Street"), 0, 1);
	    pane.add(tfStreet, 1, 1);
	    pane.add(new Label("City"), 0, 2);
	    
	    HBox hBox = new HBox(2);
	    pane.add(hBox, 1, 2);
	    hBox.getChildren().addAll(tfCity, new Label("State"), tfState,
	      new Label("Zip"), tfZip);
	    pane.add(btRegister, 1, 3);
	    GridPane.setHalignment(btRegister, HPos.RIGHT);
//	    pane.add(btAdd, 0, 4);
//	    pane.add(btFirst, 0, 5);
//	    pane.add(btNext, 1, 5);
//	    pane.add(btPrevious, 0, 6);
	    pane.add(btLast, 1, 6);
	    
//	    btAdd.setOnAction(new add());
//		btFirst.setOnAction(new first());
//		btNext.setOnAction(new next());
//		btPrevious.setOnAction(new previous());
		btLast.setOnAction(new last());
	    
	    // Text area to display contents
	    BorderPane mainPane = new BorderPane();
	    TextArea ta = new TextArea();
	    mainPane.setCenter(new ScrollPane(ta));
	    
	    pane.setAlignment(Pos.CENTER);   
	    tfName.setPrefColumnCount(15);
	    tfStreet.setPrefColumnCount(15);
	    tfCity.setPrefColumnCount(10);
	    tfState.setPrefColumnCount(2);
	    tfZip.setPrefColumnCount(3);

	    btRegister.setOnAction(new Register());
//	    btView.setOnAction(new ButtonListener());
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 450, 200);
	    primaryStage.setTitle("StudentClient"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }
	  
	  	private class last implements EventHandler<ActionEvent> {
	  		 @Override
	 	    public void handle(ActionEvent e) {
	 	      try {
	 	    	 // Establish connection with the server
	 	    	
	 		        Socket socket = new Socket(host, 8001);

	 		        // Create an output stream to the server
	 		        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());	 		        
	 		        ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
	 	 	        
	 	 	        Exercise_33_6_StudentAddress st = (Exercise_33_6_StudentAddress) fromServer.readObject();

	 	 	        loadFields(st);
	 	      }
	 	      catch (IOException ex) {
	 	        ex.printStackTrace();
	 	      } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	 	    }
		}
	
	
		
		private void loadFields(Exercise_33_6_StudentAddress object) {
			// TODO Auto-generated method stub
			tfName.setText(object.getName());
			tfStreet.setText(object.getStreet());
			tfCity.setText(object.getCity());
			tfState.setText(object.getState());
			tfZip.setText(object.getZip());
		}


	  /** Handle button action */
	  private class Register implements EventHandler<ActionEvent> {
	    @Override
	    public void handle(ActionEvent e) {
	      try {
	        // Establish connection with the server
	        Socket socket = new Socket(host, 8001);

	        // Create an output stream to the server
	        ObjectOutputStream toServer =
	          new ObjectOutputStream(socket.getOutputStream());

	        // Get text field
	        String name = tfName.getText().trim();
	        String street = tfStreet.getText().trim();
	        String city = tfCity.getText().trim();
	        String state = tfState.getText().trim();
	        String zip = tfZip.getText().trim();

	        // Create a Student object and send to the server
	        Exercise_33_6_StudentAddress s =
	          new Exercise_33_6_StudentAddress(name, street, city, state, zip);
	        toServer.writeObject(s);
	        
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }   
	    }
	  }
	  
	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
	}