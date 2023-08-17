package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckerboardPattern_GridPane extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("GridPane Demo");
		
		// stop resizing - that will be a more complex implementation
		stage.setResizable(false);
		
		// We are using absolute positioning here so use a Pane
		GridPane pane = new GridPane();
		
		Color colour = Color.BLACK;
		
		
		// nested loop to handle row and column addition of rectangles
		for (int i=0; i < 10; i++) {
			for (int j=1; j < 10; j++) {
				Rectangle r = new Rectangle(50, 50);
				r.setFill(colour);
					
				pane.add(r, i, j);
			
				
				// alternate the colours
				if (colour == Color.WHITE) {
					colour = Color.BLACK;
				}
				else if (colour == Color.BLACK) {
					colour = Color.WHITE;
				}
			}
		}
		
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