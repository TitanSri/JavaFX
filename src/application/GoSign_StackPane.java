package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GoSign_StackPane extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Green for Go");
		
		// We are using absolute positioning here so use a Pane
		StackPane pane = new StackPane();
		
		// Create circle
		Circle circle1 = new Circle(100);
			
		// assign colour
		circle1.setFill(Color.GREEN);
		
		// Create the white text Arial 50 point
		Text text = new Text("GO");
		text.setFont(Font.font("Arial", 50));
		text.setFill(Color.WHITE);
				
		// add the circle and text to the pane
		pane.getChildren().addAll(circle1, text);
		
		// create a Scene and add the pane
		Scene scene = new Scene(pane, 400, 400);
		
		//add the scene to the stage and show
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}