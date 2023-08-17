package application;

/*********************************************************************************
* (Move a circle using keys) Write a program that moves a circle up, down, left, *
* or right using the arrow keys.                                                 *
*********************************************************************************/
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class Exercise_15_11_MoveCircle extends Application{
	// the circles original position
	private double originalX = 20; 
	private double originalY = 20;
	
	@Override
	public void start(Stage primaryStage) {
		// create a pane
		Pane pane = new Pane();
		pane.setPadding(new Insets(20, 20, 20, 20));
		// create a circle
		Circle circle = new Circle (20, 20, 20);
		pane.getChildren().add(circle);
		
		// create and register the key handle e-> from event handler class
		pane.setOnKeyPressed(e -> {
			switch (e.getCode()) {
				// sets the center of the circle, if the center is more than the radius than - 10 
				//move up since the top left is 0, 0
				// else stay at the top
				case UP : circle.setCenterY(circle.getCenterY() >
						circle.getRadius() ? circle.getCenterY() - 10:
						circle.getCenterY());  
						break;
				// sets the center of the circle, if the center is less than the height of the pane
				//than + 10 (move down since the top left is 0, 0)
				// else stay at the bottom
				case DOWN : circle.setCenterY(circle.getCenterY() <
						pane.getHeight() - circle.getRadius() ?
						circle.getCenterY() + 10 : circle.getCenterY());
						break;
				// sets the center of the circle, if the center is more than the radius than - 10 
				// move left since the top left is 0, 0
				// else stay at the left
				case LEFT : circle.setCenterX(circle.getCenterX() >
						circle.getRadius() ? circle.getCenterX() - 10 :
						circle.getCenterX());
						break;
				// sets the center of the circle, if the center is less than the width of the pane
				// than + 10 move right since the top left is 0, 0
				// else stay at the right
				case RIGHT : circle.setCenterX(circle.getCenterX() <
						pane.getWidth() - circle.getRadius() ?
						circle.getCenterX() + 10 : circle.getCenterX());
			}
		});
		
		// create a scene and place it in the stage
		Scene scene = new Scene(pane, 200, 200); // place pane in the scene
		primaryStage.setTitle("Press arrow keys to move the circle");  // set the title of the stage
		primaryStage.setScene(scene); // place the scene in the stage
		
		// create and register the drag handle e-> from the event handler
		circle.setOnMousePressed((MouseEvent m) -> {
			scene.setCursor(Cursor.CLOSED_HAND); // when the circle is clicked the arrow icon will change to a hand
		});
		
		circle.setOnMouseReleased((MouseEvent m) -> {
			scene.setCursor(Cursor.DEFAULT); // change the icon back on release
		});
		
		// to drag the circle
		circle.setOnMouseDragged((MouseEvent m) -> {
			// get the mouse offset when dragged
			double offsetX = m.getX() - originalX;
			double offsetY = m.getY() - originalY;
			
			// add is to the original circle location
			circle.setCenterX(circle.getCenterX() + offsetX);
			circle.setCenterY(circle.getCenterY() + offsetY);
			
			// update the original circle location
			originalX = m.getX();
			originalY = m.getY();
		});
		
		primaryStage.show(); // display the stage
		
		pane.requestFocus(); // listener is active
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
