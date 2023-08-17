package application;

/*
 * @author https://soultionmanual.blogspot.com/2016/12/chapter-15-exercise-10-introduction-to.html
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Exercise_15_10_TypeString extends Application{
	@Override
	public void start(Stage primaryStage) {
		double width = 400;
		double height = 400;
		
		Label label = new Label(); // create a label which has more functions than text
		StackPane pane = new StackPane(label); // adds the label to the pane
		
		StringBuilder s = new StringBuilder();
		pane.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				label.setText(s.toString()); // set the string to the label
				s.delete(0, s.length()); // delete the string from 0 to the length 
			} else {
				s.append(e.getText()); // adds the key presses to the string 
			}
		});
		
		primaryStage.setScene(new Scene(pane, width, height)); // insert the stack pane into the stage
		primaryStage.setTitle("Type something than press enter...");
		primaryStage.show();
		pane.requestFocus();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
