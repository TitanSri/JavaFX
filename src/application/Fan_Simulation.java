package application;

import java.io.File;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fan_Simulation extends Application{
	int rotation = 10;
	 
	@Override
	public void start(Stage primaryStage) {
	    BorderPane pane = new BorderPane();
	    Group group = new Group();
	    
	    // add media sound
	    String filePath= "C:\\Users\\User\\Music\\AirCon.mp3";
	    Media sound = new Media(new File(filePath).toURI().toString());
	    MediaPlayer media = new MediaPlayer(sound);
	    media.setCycleCount(MediaPlayer.INDEFINITE);
	   
	    Circle circle = new Circle(250, 250, 210, Color.TRANSPARENT);
	    circle.setStroke(Color.BLACK);
	    
	    Arc arc1 = new Arc(250, 250, 200, 200, 0, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc1.setFill(Color.PURPLE);
	    arc1.setType(ArcType.ROUND);
	    
	    Arc arc2 = new Arc(250, 250, 200, 200, 42, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc2.setFill(Color.WHITE);
	    arc2.setType(ArcType.ROUND);
	    
	    Arc arc3 = new Arc(250, 250, 200, 200, 60, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc3.setFill(Color.BLACK);
	    arc3.setType(ArcType.ROUND);
	    
	    Arc arc4 = new Arc(250, 250, 200, 200, 102, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc4.setFill(Color.WHITE);
	    arc4.setType(ArcType.ROUND);
	    
	    Arc arc5 = new Arc(250, 250, 200, 200, 120, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc5.setFill(Color.PURPLE);
	    arc5.setType(ArcType.ROUND);
	    
	    Arc arc6 = new Arc(250, 250, 200, 200, 162, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc6.setFill(Color.WHITE);
	    arc6.setType(ArcType.ROUND);
	    
	    Arc arc7 = new Arc(250, 250, 200, 200, 180, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc7.setFill(Color.BLACK);
	    arc7.setType(ArcType.ROUND);
	    
	    Arc arc8 = new Arc(250, 250, 200, 200, 222, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc8.setFill(Color.WHITE);
	    arc8.setType(ArcType.ROUND);
	    
	    Arc arc9 = new Arc(250, 250, 200, 200, 240, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc9.setFill(Color.PURPLE);
	    arc9.setType(ArcType.ROUND);
	    
	    Arc arc10 = new Arc(250, 250, 200, 200, 282, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc10.setFill(Color.WHITE);
	    arc10.setType(ArcType.ROUND);
	    
	    Arc arc11 = new Arc(250, 250, 200, 200, 300, 42); // x axis, y axis, x radius, y radius, start, length 
	    arc11.setFill(Color.BLACK);
	    arc11.setType(ArcType.ROUND);
	    
	    Arc arc12 = new Arc(250, 250, 200, 200, 342, 18); // x axis, y axis, x radius, y radius, start, length 
	    arc12.setFill(Color.WHITE);
	    arc12.setType(ArcType.ROUND);
	    
	    group.getChildren().addAll(arc1, arc2, arc3, arc4, arc5, arc6, arc7, arc8, arc9, arc10, arc11, arc12);
	    Timeline timeline = new Timeline(24, new KeyFrame(new Duration(1000), e -> group.setRotate(rotation += 15)));
	    timeline.setCycleCount(Integer.MAX_VALUE);
	    
	    Timeline timelineReverse = new Timeline(24, new KeyFrame(new Duration(1000), f -> group.setRotate(rotation -= 15)));
    	timelineReverse.setCycleCount(Integer.MAX_VALUE);
	    
        // Buttons pause, resume, increase, decrease
        Button stop = new Button("Stop");
        stop.setOnAction(e-> {
        	if (timeline.getStatus() == Animation.Status.STOPPED && timelineReverse.getStatus() == Animation.Status.STOPPED) {
        		System.err.println("Error: Already stopped");
        	}
        	timeline.stop();
        	timelineReverse.stop();
        	media.stop();
        });

        Button start = new Button("Start");
        start.setOnAction(e-> {
        	timelineReverse.stop();
        	if (timeline.getStatus() == Animation.Status.RUNNING) {
        		System.err.println("Error: Start already runnning");
        	}
        	timeline.play();
        	media.stop();
        	media.play();
        });

        Button reverse = new Button("Reverse");
        reverse.setOnAction(e-> {
        	timeline.stop();
        	if (timelineReverse.getStatus() == Animation.Status.RUNNING) {
        		System.err.println("Error: Reverse already running");
        	}
        	timelineReverse.play();
        	media.stop();
        	media.play();
        });
	    
        HBox hButtons = new HBox(stop, start, reverse);
        hButtons.setSpacing(10);
        hButtons.setAlignment(Pos.CENTER);
        hButtons.setPadding(new Insets(5, 5, 5, 5));
        
	    //adding pieChart to the root.
	    pane.getChildren().addAll(group, circle);
	    pane.setBottom(hButtons);
	    Scene scene = new Scene(pane, 500, 500);

	    primaryStage.setTitle("Fan Simulation Test");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
	    launch(args);
	}
	

}
