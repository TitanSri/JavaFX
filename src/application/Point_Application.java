package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class Point_Application extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane pane = new BorderPane();
		Label label = new Label();
	
		Collection<Circle> circles = new ArrayList<Circle>();
		
		ArrayList<String> xycoords = new ArrayList<String>();
		getXYCoords(xycoords);
		
		for ( int i = 0; i < xycoords.size(); i++) {
			Circle circle = new Circle(5);
			Float x = Float.parseFloat(xycoords.get(i));
			i++;
			Float y = Float.parseFloat(xycoords.get(i));	
			System.out.println(x + " " + y);
			circle.setCenterX(x);
			circle.setCenterY(y);
			
			
			// start as white 
			int r = 255;
			int b = 255;
			int g = 255;
			
			// prevent white color for the circles
			while (r == 255 && b == 255 && g == 255) {
			r = (int)(Math.random() * 255);
			b = (int)(Math.random() * 255);
			g = (int)(Math.random() * 255);
			}
			
			circle.setFill(Color.rgb(r, b, g));
			circles.add(circle);
			
			circle.setOnMouseDragged((MouseEvent m) -> {				
				if (m.getX() > 0 && m.getY() > 0 && m.getX() < 400 && m.getY() < 400 ) {			
					// update the original circle location
					circle.setCenterX(m.getX());
					circle.setCenterY(m.getY());
				}
				
			});
			
			// when entering the circle also known as hover over the circle
			circle.setOnMouseEntered(e -> {
				label.setText(circle.getCenterX() + " " + circle.getCenterY());
			});

		}
		
		pane.getChildren().addAll(circles);
		pane.setBottom(label);
		Font font = Font.font("Ariel", FontWeight.BOLD, 12);
		label.setFont(font);
		BorderPane.setAlignment(label, Pos.BASELINE_CENTER);
		
		Scene scene = new Scene(pane, 400, 400);
		stage.setScene(scene);
		stage.setTitle("Show Points Test");
		stage.show();
		
		// saves the coordinates for each circle when window closes from top right cross button.
		stage.setOnCloseRequest(e -> {
			String s;
			try {
				PrintWriter writer = new PrintWriter(new File("D:\\MyDocuments\\Eclipse\\ITC313\\src\\PointApplication.txt"));
				for (Circle e1 : circles) {
					float x = (float) e1.getCenterX();
					float y = (float) e1.getCenterY();
					s = ("x=" + Float.toString(x) + "; y=" + Float.toString(y));		
					System.out.println(s);
					writer.println(s);
				}
				writer.close();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	public ArrayList<String> getXYCoords(ArrayList<String> temp){
		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\MyDocuments\\Eclipse\\ITC313\\src\\PointApplication.txt")))) {
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				String x = line.substring(2, line.indexOf(";"));
				System.out.println(x);
				String y = line.substring(line.indexOf(";") + 4, line.length());
				System.out.println(y);		
				temp.add(x);
				temp.add(y);
				System.out.println(temp);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp; 
	}
	
	public static void main(String[] args) throws IOException {
		
		Application.launch(args);
		
	}
}
		
