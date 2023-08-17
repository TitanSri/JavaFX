package application;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PoppingCircles extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		// add your own sound file in here
		String soundFile = "C:\\Temp\\music\\Sounds\\mixkit-fast-small-sweep-transition-166.wav";

		Media sound = new Media(new File(soundFile).toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);
		
		stage.setTitle("Popping Circles");

		Pane pane = new Pane();
		ArrayList<Circle> circles = new ArrayList<Circle>();
		
		// populate the array list with circles
		for(int i=0; i < 10; i++) {
			Circle c = new Circle(10);
			
			c.setOnMouseClicked((MouseEvent e) -> {
				player.stop();
				player.play();
				c.setVisible(false);
			});
			circles.add(c);
		}
		
		// display the circles on the pane at random locations
		for (int i=0; i < circles.size(); i++) {
			int x = (int) (10 + Math.random() * 390);
			int y = (int) (10 + Math.random() * 390);
			System.out.println(x + ", " + y);
			
			Circle c = circles.get(i);
			c.setCenterX(x);
			c.setCenterY(y);
			
			// create the random rgb values
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			
			c.setFill(Color.rgb(r, g, b));
			
		}
		
		pane.getChildren().addAll(circles);
		Scene scene = new Scene(pane, 400, 400);
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
