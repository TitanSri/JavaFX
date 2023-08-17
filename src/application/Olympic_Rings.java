package application;

/**
 * Write a JavaFX program that displays the Olympic Rings symbol as per the image below. The
symbol should be centred in a window that measures 600 * 400. The window should not be
resizable. The colours of the rings (from left to right) are blue, gold, black, green, red. Note the
way that each ring interlocks with the surrounding rings; this feature needs to be reproduced
in your application. Add a label at the bottom centre of the window, with a small space below,
with the words The Olympic Rings written in Arial italic 16-point font.

 *@author Toby Sriratanakoul
 *Student ID: 11485513
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Olympic_Rings extends Application  {
	@Override 
	public void start(Stage primaryStage) {
		
		//create a pane
		BorderPane pane = new BorderPane();
		pane.setPrefSize(600, 400); // set the size as advised up top
		
		//create 6 arcs since the green does not go through two additional rings after overlapping all 5
		Arc arc1 = new Arc(180, 150, 50, 50, 295 , 335); // starts at the bottom
		arc1.setStroke(Color.BLUE); // color of the ring
		arc1.setFill(Color.TRANSPARENT); // need the center to be clear
		arc1.setStrokeWidth(10); // width for the stroke to be 10
		
		// create a circle for the background to prevent white spaces from arc shortage
		Circle circle1 = new Circle();
		circle1.setRadius(50);
		circle1.setFill(Color.TRANSPARENT);
		circle1.setStroke(Color.BLUE);
		circle1.setStrokeWidth(10);
		circle1.setCenterX(180);
		circle1.setCenterY(150);
		
		Arc arc2 = new Arc(300, 150, 50, 50, 195 , 335); // starts on the left
		arc2.setStroke(Color.BLACK);
		arc2.setFill(Color.TRANSPARENT);
		arc2.setStrokeWidth(10);
		
		Circle circle2 = new Circle();
		circle2.setRadius(50);
		circle2.setFill(Color.TRANSPARENT);
		circle2.setStroke(Color.BLACK);
		circle2.setStrokeWidth(10);
		circle2.setCenterX(300);
		circle2.setCenterY(150);
		
		Arc arc3 = new Arc(420, 150, 50, 50, 195 , 335); //starts on the left
		arc3.setStroke(Color.RED);
		arc3.setFill(Color.TRANSPARENT);
		arc3.setStrokeWidth(10);
		
		Circle circle3 = new Circle();
		circle3.setRadius(50);
		circle3.setFill(Color.TRANSPARENT);
		circle3.setStroke(Color.RED);
		circle3.setStrokeWidth(10);
		circle3.setCenterX(420);
		circle3.setCenterY(150);
		
		Arc arc4 = new Arc(240, 200, 50, 50, 115 , 335); // starts at the top
		arc4.setStroke(Color.GOLD);
		arc4.setFill(Color.TRANSPARENT);
		arc4.setStrokeWidth(10);
		
		Circle circle4 = new Circle();
		circle4.setRadius(50);
		circle4.setFill(Color.TRANSPARENT);
		circle4.setStroke(Color.GOLD);
		circle4.setStrokeWidth(10);
		circle4.setCenterX(240);
		circle4.setCenterY(200);
		
		Arc arc5 = new Arc(360, 200, 50, 50, 15 , 76); // starts on the right
		arc5.setStroke(Color.GREEN);
		arc5.setFill(Color.TRANSPARENT);
		arc5.setStrokeWidth(10);
		
		Circle circle5 = new Circle();
		circle5.setRadius(50);
		circle5.setFill(Color.TRANSPARENT);
		circle5.setStroke(Color.GREEN);
		circle5.setStrokeWidth(10);
		circle5.setCenterX(360);
		circle5.setCenterY(200);
		
		Arc arc6 = new Arc(360, 200, 50, 50, 115 , 245); // starts on the top
		arc6.setStroke(Color.GREEN);
		arc6.setFill(Color.TRANSPARENT);
		arc6.setStrokeWidth(10);
		
		Label label = new Label("The Olympic Rings"); // sets the text at the bottom in the center
		Font font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 16); // sets the font to arial, bold, italic and size 16
		label.setFont(font); // set the text to the font
		label.setTextFill(Color.BLACK);
		
		pane.getChildren().addAll(circle1, circle4, circle2, circle5, circle3, arc1, arc4, arc2, arc5, arc6, arc3);	// insert the rings on older to ensure the overlapping is correct
		pane.setBottom(label);
		BorderPane.setAlignment(label, Pos.BASELINE_CENTER);
		
		// create a scene with white background
		Scene scene = new Scene(pane, Color.WHITE); 
		primaryStage.setTitle("Olympic Rings"); // the title for the window
		primaryStage.setScene(scene); // add the scene to the stage
		primaryStage.setResizable(false); // prevent users from dragging the window size
		primaryStage.show();		// show the stage
	}
	
	public static void main(String[] args) {
		launch(args); // launch the application
	}

}
