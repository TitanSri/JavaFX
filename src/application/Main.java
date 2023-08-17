package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
//		// Create a pane to hold the images views
//				GridPane pane = new GridPane();
//
//				// Place nodes in the pane
//				pane.add(new ImageView(new Image("image/uk.gif")), 0, 0);
//				pane.add(new ImageView(new Image("image/ca.gif")), 1, 0);
//				pane.add(new ImageView(new Image("image/china.gif")), 0, 1);
//				pane.add(new ImageView(new Image("image/us.gif")), 1, 1);
//
//				// Create a scene and place it in the stage
//				Scene scene = new Scene(pane);
//				primaryStage.setTitle("Exercise_14_01"); // Set the stage title
//				primaryStage.setScene(scene); // Place the scene in the stage
//				primaryStage.show(); // Display the stage.
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
