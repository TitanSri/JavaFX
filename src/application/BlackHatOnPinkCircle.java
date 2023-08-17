package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlackHatOnPinkCircle extends Application{

	@Override
	  public void start(Stage stage) {
	    Arc arc = new Arc(200, 200, 100, 100, 0, 180); // centerX, centreY, radiusX, radiusY, start angle, length
	    arc.setFill(Color.PINK);
	    arc.setStroke(Color.BLACK);
	    arc.setType(ArcType.ROUND);
	    
	    Arc arc2 = new Arc(200, 105, 50, 10, 0, 180); // centerX, centreY, radiusX, radiusY, start angle, length
	    arc2.setFill(Color.BLACK);
	    arc2.setStroke(Color.BLACK);
	    arc2.setType(ArcType.ROUND);


	    Pane box = new Pane(arc, arc2);
	 
	    Scene scene = new Scene(box, 400, 400);
	    stage.setScene(scene);
	    stage.setTitle("Test");
	    stage.show();
	  }
	
	public static void main(String[] args) {
		launch(args);
	}
}
