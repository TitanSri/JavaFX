package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise_31_01_Use_css_File extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(5);
    Scene scene = new Scene(hBox, 600, 100);        
    scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());       

    Pane pane1 = new Pane();
    Circle circle1 = new Circle(40, 50, 30);
    pane1.getChildren().addAll(circle1);
    pane1.getStyleClass().add("border");
    circle1.getStyleClass().add("plaincircle"); // Add a style class
     
    Pane pane2 = new Pane();
    Circle circle2 = new Circle(40, 50, 30);
    circle2.getStyleClass().addAll("circleborder", "plainCircle"); 
    circle2.setId("plaincircle"); // Add a style class
    circle2.getStyleClass().add("plaincircle"); // Add a style class
    pane2.getChildren().add(circle2);
    
    Pane pane3 = new Pane();
    Circle circle3 = new Circle(40, 50, 30);
    circle3.getStyleClass().addAll("circleborder", "plainCircle"); 
    circle3.setId("redcircle"); // Add a style id
    pane3.getChildren().add(circle3);
    
    Pane pane4 = new Pane();
    Circle circle4 = new Circle(40, 50, 30);
    circle4.getStyleClass().addAll("circleborder", "plainCircle"); 
    circle4.setId("greencircle"); // Add a style class
    pane4.getChildren().add(circle4);
    pane4.getStyleClass().add("border");

    hBox.getChildren().addAll(pane1, pane2, pane3, pane4); 
    
    primaryStage.setTitle("StyleSheetDemo"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  // Launch the program from command-line
  public static void main(String[] args) {
    launch(args);
  }
}

