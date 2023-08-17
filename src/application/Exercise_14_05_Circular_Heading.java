package application;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_14_05_Circular_Heading extends Application{

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane(); // new pane
        String[] java = "Welcome to Java".split(""); // create an array with each word
        Font font = Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 30); // set the font 

        // http://en.wikipedia.org/wiki/Circle#Equations
        // The equation can be written in parametric form using the
        // trigonometric functions sine and cosine as
        // x = a+r * cos t
        // y = b+r * sin t
        // where t is a parametric variable in the range 0 to 2π,

        Point2D center = new Point2D(200, 200); // uses points to define location 
        double radius = 100;
        double angle = 205; // make the text start from the left
        double rotate = 295; // rotate angle + 90 to make the letters towards the center 
        for (int i = 0; i < java.length; i++, angle += 22, rotate += 22) {
            double x = center.getX() + radius * Math.cos(Math.toRadians(angle));
            double y = center.getY() + radius * Math.sin(Math.toRadians(angle));
            Text text = new Text(x, y,java[i]);
            text.setRotate(rotate);
            text.setFont(font);
            pane.getChildren().add(text);

        }
 
        Scene scene = new Scene(pane, 400, 400); // size of the scene
        primaryStage.setTitle("Welcome to Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
