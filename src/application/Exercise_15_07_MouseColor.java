package application;

/*********************************************************************************
* (Change color using a mouse) Write a program that displays the color of a      *
* circle as black when the mouse button is pressed and as white when the mouse   *
* button is released.     
* @author jsquared21                                                       *
*********************************************************************************/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class Exercise_15_07_MouseColor extends Application {
	@Override  // override the start method in the application class
	public void start(Stage primaryStage) {
		// add media sound
		String filePath = "C:\\Temp\\music\\Sounds\\mixkit-fast-small-sweep-transition-166.wav";
		Media sound = new Media(new File(filePath).toURI().toString());
		MediaPlayer media = new MediaPlayer(sound);
		
		// create a stack pane 
		StackPane pane = new StackPane();
		
		// create a circle and set its properties
		Circle circle = new Circle(50);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		pane.getChildren().add(circle);
		
		// create and register a handler from the event handler class
		pane.setOnMousePressed(e -> {
			media.play();
			// if left click
			if (e.isPrimaryButtonDown()) {
				circle.setFill(Color.BLACK);
			}
			// is right click
			if (e.isSecondaryButtonDown()) {
				circle.setFill(Color.RED);
			}
			// if middle click 
			if (e.isMiddleButtonDown()) {
				circle.setFill(Color.YELLOW);
			}
		});
		
		pane.setOnMouseReleased(e -> {
			media.stop();
			circle.setFill(Color.WHITE);
			circle.setStroke(Color.BLACK);
		});
		
		// create a scene and place it in the stage
		Scene scene = new Scene(pane, 120, 120);
		primaryStage.setTitle("Mouse Color"); // set the stage title
		primaryStage.setScene(scene); // place the scene in the stage
		primaryStage.show(); // display the stage 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
