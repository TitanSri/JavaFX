package application;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise_31_19_SplitPane extends Application {
	private Image usImage = new Image(
	    "https://liveexample.pearsoncmg.com/common/image/us.gif");
	  private Image ukImage = new Image(
	    "https://liveexample.pearsoncmg.com/common/image/uk.gif");
	  private Image caImage = new Image(
	    "https://liveexample.pearsoncmg.com/common/image/ca.gif");
	  private String usDescription = "Description for US ...";
	  private String ukDescription = "Description for UK ...";
	  private String caDescription = "Description for CA ...";
	
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {   
		SplitPane content = new SplitPane();
		content.setOrientation(Orientation.VERTICAL);
		ImageView imageView = new ImageView(usImage);
		StackPane imagePane = new StackPane();
		imagePane.getChildren().add(imageView);
		TextArea taDescription = new TextArea();   
		taDescription.setText(usDescription);
		content.getItems().addAll(
		imagePane, new ScrollPane(taDescription));
	    
	    SplitPane content2 = new SplitPane();
	    content2.setOrientation(Orientation.VERTICAL);
	    ImageView imageView2 = new ImageView(ukImage);
	    StackPane imagePane2 = new StackPane();
	    imagePane2.getChildren().add(imageView2);
	    TextArea taDescription2 = new TextArea();   
	    taDescription2.setText(ukDescription);
	    content2.getItems().addAll(
	    imagePane2, new ScrollPane(taDescription2));
	    
	    SplitPane content3 = new SplitPane();
	    content3.setOrientation(Orientation.VERTICAL);
	    content3.setDividerPositions(0, 0.5);
	    Circle circle = new Circle(50);
	    circle.setFill(Color.TRANSPARENT);
	    circle.setStroke(Color.BLACK);
	    Rectangle rectangle = new Rectangle(50, 50);
	    rectangle.setFill(Color.TRANSPARENT);
	    rectangle.setStroke(Color.BLACK);
	    content3.getItems().addAll(
	    circle, rectangle);
	    
	    SplitPane content4 = new SplitPane();
	    content4.setOrientation(Orientation.VERTICAL);
	    QuadCurve quadCurve = new QuadCurve(10, 80, 40, 20, 150, 56); // startX, startY, controlX, ControlY, endX, endY
	    quadCurve.setStroke(Color.BLACK);
	    quadCurve.setFill(Color.TRANSPARENT);
	    CubicCurve cubicCurve = new CubicCurve(200, 80, 240, 20, 350, 156, 450, 80); // startX, statY, controlX1, controlY1, controlX2, controlY2, endX, endY
	    cubicCurve.setStroke(Color.BLACK);
	    cubicCurve.setFill(Color.TRANSPARENT);
	    content4.getItems().addAll(
	    quadCurve, cubicCurve);
	    content4.setDividerPositions(0.5);
	        
	    SplitPane sp = new SplitPane();
	    sp.getItems().addAll(content, content2, content3, content4);
	
	    Scene scene = new Scene(sp, 1000, 300);           
	    primaryStage.setTitle("SplitPaneDemo"); // Set the window title
	    primaryStage.setScene(scene); // Place the scene in the window
	    primaryStage.setResizable(false); // prevent users from dragging the window size
	    primaryStage.show(); // Display the window
	  }
	
	  // Launch the program from command-line
	  public static void main(String[] args) {
	    launch(args);
	  }
	
}
