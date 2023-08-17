package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_31_17_InvestmentValueCalculator extends Application {
  private TextField investmentAmount = new TextField();
  private TextField numberOfYears = new TextField();
  private TextField annualInterestRate = new TextField();
  private TextField futureValue = new TextField();
    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    MenuBar menuBar = new MenuBar();    
    
    // create a menu
    Menu menuOperation = new Menu("Operation");
    Menu menuExit = new Menu("Exit");
    menuBar.getMenus().addAll(menuOperation, menuExit);
    
    // create menu items
    MenuItem menuItemCalculate = new MenuItem("Calculate");
    menuOperation.getItems().addAll(menuItemCalculate);
    
    // create close menu item
    MenuItem menuItemClose = new MenuItem("Close");
    menuExit.getItems().add(menuItemClose);
    
    // create accelerator
    menuItemCalculate.setAccelerator(
    		KeyCombination.keyCombination("Ctrl+C"));
    
    // create a VBox where all the text field are vertical
//    VBox vBox1 = new VBox(5);
//    investmentAmount.setPrefColumnCount(2);
//    numberOfYears.setPrefColumnCount(2);
//    annualInterestRate.setPrefColumnCount(2);
//    futureValue.setPrefColumnCount(2);
//    vBox1.getChildren().addAll(new Label("Investment Amount:"), investmentAmount,
//      new Label("Number of Years:"), numberOfYears, new Label("Annual Interest Rate:"), annualInterestRate, new Label("Result:"), 
//      futureValue);
//    vBox1.setAlignment(Pos.CENTER);
    
    // create a HBox for investment amount
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Investment Amount:"), investmentAmount);
    hBox.setAlignment(Pos.CENTER_RIGHT);
    
    // create a HBox for number of years
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(new Label("Number of Years:"), numberOfYears);
    hBox1.setAlignment(Pos.CENTER_RIGHT);
    
    // create a HBox for annual interest rate
    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(new Label("Annual Interest Rate:"), annualInterestRate);
    hBox2.setAlignment(Pos.CENTER_RIGHT);
    
    // create a HBox for results 
    HBox hBox3 = new HBox(5);
    hBox3.getChildren().addAll(new Label("Result:"), futureValue);
    hBox3.setAlignment(Pos.CENTER_RIGHT);
    
    // create a HBox
    HBox hBoxButton = new HBox(2);
    Button calculate = new Button("Calculate");
    hBoxButton.getChildren().addAll(calculate);
    hBoxButton.setAlignment(Pos.CENTER_RIGHT);
    
    // create a VBox for all boxes 
    VBox vBox = new VBox(10);
    vBox.getChildren().addAll(menuBar, hBox, hBox1, hBox2, hBox3, hBoxButton);
    Scene scene = new Scene(vBox, 300, 300);  
    primaryStage.setTitle("MenuDemo"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
    
    // Handle menu actions
    menuItemCalculate.setOnAction(e -> perform('%'));
    menuItemClose.setOnAction(e -> System.exit(0));
    
    // Handle button actions
    calculate.setOnAction(e -> perform('%'));
  }

  private void perform(char operator) {
    double amount = Double.parseDouble(investmentAmount.getText());
    double years = Double.parseDouble(numberOfYears.getText());
    double rate = Double.parseDouble(annualInterestRate.getText());
    
    double result = 0;
    switch (operator) {
      case '%': result = amount * (Math.pow((1 + (rate / 12)), (years * 12))); break;
    }
    
    futureValue.setText(result + "");
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

