package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Test extends Application {
  //Create a label that will report the selection.
  private Label response = new Label("Menu Demo");

  public static void main(String[] args) {
    launch(args);//from   ww  w  .j a  va 2  s  .com
  }
  public void start(Stage myStage) {    myStage.setTitle("Menus from java2s.com");

    // Use a BorderPane for the root node.
    BorderPane rootNode = new BorderPane();

    Scene myScene = new Scene(rootNode, 300, 300);

    myStage.setScene(myScene);

    rootNode.setBottom(response);
    BorderPane.setAlignment(response, Pos.CENTER);

    myStage.show();
  }
}