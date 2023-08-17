package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JFX_Spinning_Rectangle_With_Thread extends Application{
	public void start(Stage stage) {
		
		Spinning_Rectangle_With_Thread rect1 = new Spinning_Rectangle_With_Thread(20f,50f);
		rect1.setFill(Color.RED);
		
		BorderPane pane = new BorderPane();
		pane.setBackground(Background.EMPTY);
		pane.setCenter(rect1);
		BorderPane.setAlignment(rect1, Pos.CENTER);
	
		HBox hBox = new HBox();
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		hBox.getChildren().addAll(start, stop);
		hBox.setSpacing(15);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(5));
		pane.setBottom(hBox);
		BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);
		
		start.setOnAction( e -> {
			rect1.start();
		});
		
		stop.setOnAction( e -> {
			rect1.stop();
		});		
		
		Scene scene = new Scene(pane, 400, 400, Color.WHITE);
		
		stage.setScene(scene);
		stage.setTitle("Java FX Thread App");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}

