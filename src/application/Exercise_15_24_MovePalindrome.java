package application;

/*********************************************************************************
* (Animation: palindrome) Write a program that animates a palindrome swing as    *
* shown in Figure 15.31. Press/release the mouse to pause/resume the animation.  *
*********************************************************************************/
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_24_MovePalindrome extends Application {
	@Override
	public void start(Stage primaryStage) {
		// create a pane
		Pane pane = new Pane();
		
		// create a arc
		Arc arc = new Arc(100, 50, 75, 25, 0, -180); // x, y, width, height, start angle, arc angle 
		arc.setFill(Color.WHITE);
		arc.setStroke(Color.BLACK);
		
		// create a circle
		Circle circle = new Circle(100, 75, 5); // x, y, radius
		
		// place nodes in pane
		pane.getChildren().addAll(arc, circle);
		
		// create a path transition
		PathTransition pt = new PathTransition();
		pt.setDuration(Duration.millis(4000)); // duration 
		pt.setPath(arc); // path on arc
		pt.setNode(circle); // shape on arc
		pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); // placement 
		pt.setCycleCount(Timeline.INDEFINITE); // how many cycles
		pt.setAutoReverse(true); // go backwards
		pt.play();
		
		// create a register handle from the event handler
		pane.setOnMousePressed(e -> {
			pt.pause();
		});
		
		pane.setOnMouseReleased(e -> {
			pt.play();
		});
		
		
		// create a scene and place it in the stage
		Scene scene = new Scene(pane, 200, 100);
		primaryStage.setTitle("click to pause...");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
