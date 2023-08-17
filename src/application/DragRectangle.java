package application;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DragRectangle extends Application {
	private int rectWidth = 70;
	private int rectLength = 30;
	private double originalX = 200f;
	private double originalY = 200f;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Rectange App");
		Pane p = new Pane();
		
		// center the rectangle in the pane
		Rectangle r = new Rectangle(originalX - (rectWidth / 2), originalY - (rectLength / 2), rectWidth , rectLength);
		
		r.setFill(Color.BROWN);
		p.getChildren().add(r);
		
		Scene scene = new Scene(p, 400, 400);
		stage.setScene(scene);
		
		// event handlers
		
		// some fancy stuff with the cursor
		r.setOnMousePressed((MouseEvent m) -> {
			scene.setCursor(Cursor.CLOSED_HAND);
		});
		
		// don't forget to return the cursor back to normal when the drag is finished
		r.setOnMouseReleased((MouseEvent m) -> {
			scene.setCursor(Cursor.DEFAULT);
		});
						
		// responding to the drag		
		r.setOnMouseDragged((MouseEvent m) -> {
			// get the offset of the drag
			double offsetX = m.getX() - originalX;
			double offsetY = m.getY() - originalY;
			
			// add it to the original rectangle
			r.setX(r.getX() + offsetX);
			r.setY(r.getY() + offsetY);
			
			// update the original x and y to the new positions
			originalX = m.getX();
			originalY = m.getY();
		});
		
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}