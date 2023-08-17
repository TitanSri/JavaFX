package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LightDisplay extends Application {
	private final int RADIUS = 10;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Christmas Lights");
		
		Button btnStop = new Button("Stop");
		Button btnStart = new Button("Start");
		Button btnPause = new Button("Pause");
		Button btnResume = new Button("Resume");
		
		HBox hBoxButtons = new HBox(btnStop, btnStart, btnPause, btnResume);
		hBoxButtons.setSpacing(10);
		hBoxButtons.setAlignment(Pos.CENTER);
		
		ArrayList<FlashingLight_Thread> lights = new ArrayList<FlashingLight_Thread>();
		lights.add(new FlashingLight_Thread(RADIUS, Color.GREEN));
		lights.add(new FlashingLight_Thread(RADIUS, Color.BLUE));
		lights.add(new FlashingLight_Thread(RADIUS, Color.RED));
		lights.add(new FlashingLight_Thread(RADIUS, Color.PINK));
		lights.add(new FlashingLight_Thread(RADIUS, Color.ORANGE));
		lights.add(new FlashingLight_Thread(RADIUS, Color.YELLOW));
		
		
		HBox hBoxLights = new HBox();
		hBoxLights.getChildren().addAll(lights);
		hBoxLights.setAlignment(Pos.CENTER);
		
		
		BorderPane pane = new BorderPane();
		pane.setBackground(Background.EMPTY);
		pane.setCenter(hBoxLights);
		pane.setBottom(hBoxButtons);
		
		
		btnStart.setOnAction((ActionEvent e) -> {
			for (FlashingLight_Thread l : lights)
				l.start();
		});
		
		btnStop.setOnAction((ActionEvent e) -> {
			for (FlashingLight_Thread l : lights)
				l.stop();
		});
		
		btnPause.setOnAction((ActionEvent e) -> {
			for (FlashingLight_Thread l : lights)
				l.pause();
		});
		
		btnResume.setOnAction((ActionEvent e) -> {
			for (FlashingLight_Thread l : lights)
				l.resume();
		});
		
		stage.setOnCloseRequest((WindowEvent we) -> {
			System.exit(0);
		});
		
		Scene scene = new Scene(pane, 400, 400);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}