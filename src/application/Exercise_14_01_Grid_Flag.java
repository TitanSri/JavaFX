package application;

/*********************************************************************************
* (Display images) Write a program that displays four images in a grid pane, as  *
* shown in Figure 14.43a.                                                        *
*********************************************************************************/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Exercise_14_01_Grid_Flag extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a pane to hold the images views
		GridPane pane = new GridPane();

		// Place nodes in the pane
		pane.add(new ImageView(new Image("image/uk.gif")), 0, 0); // first column index then the line index
		pane.add(new ImageView(new Image("image/ca.gif")), 1, 0);
		pane.add(new ImageView(new Image("image/china.gif")), 0, 1);
		pane.add(new ImageView(new Image("image/us.gif")), 2, 2);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane); // insert the pane into the scene
		primaryStage.setTitle("Exercise_14_01"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage.
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}