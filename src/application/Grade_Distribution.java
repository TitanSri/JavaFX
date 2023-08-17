package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Grade_Distribution extends Application  {
	@Override
	public void start(Stage primaryStage) {
	    Pane pane = new Pane();
	    
	    Arc hd = new Arc(250, 250, 200, 200, 0, 360*15/100); // x axis, y axis, x radius, y radius, start, length 
	    hd.setFill(Color.GREEN);
	    hd.setType(ArcType.ROUND);
	    
	    Arc di = new Arc(250, 250, 200, 200, 360*15/100, 360*20/100); // x axis, y axis, x radius, y radius, start, length 
	    di.setFill(Color.BLUE);
	    di.setType(ArcType.ROUND);
	    
	    Arc cr = new Arc(250, 250, 200, 200, 360*35/100, 360*25/100); // x axis, y axis, x radius, y radius, start, length 
	    cr.setFill(Color.AQUA);
	    cr.setType(ArcType.ROUND);
	    
	    Arc ps = new Arc(250, 250, 200, 200, 360*60/100, 360*35/100); // x axis, y axis, x radius, y radius, start, length 
	    ps.setFill(Color.ORANGE);
	    ps.setType(ArcType.ROUND);
	    
	    Arc fl = new Arc(250, 250, 200, 200, 360*95/100, 360*5/100); // x axis, y axis, x radius, y radius, start, length 
	    fl.setFill(Color.RED);
	    fl.setType(ArcType.ROUND);
	    
	    Text hdText = new Text(300, 220, "HD = 15%");
	    Font hdFont = Font.font("Arial", FontWeight.BOLD, 30);
	    hdText.setFont(hdFont);
	    
	    Text diText = new Text(200, 100, "DI = 20%");
	    Font diFont = Font.font("Arial", FontWeight.BOLD, 30);
	    diText.setFont(diFont);
	    
	    Text crText = new Text(60, 250, "CR = 25%");
	    Font crFont = Font.font("Arial", FontWeight.BOLD, 30);
	    crText.setFont(crFont);
	    
	    Text psText = new Text(200, 400, "PS = 35%");
	    Font psFont = Font.font("Arial", FontWeight.BOLD, 30);
	    psText.setFont(psFont);
	    
	    Text flText = new Text(330, 275, "FL = 5%");
	    Font flFont = Font.font("Arial", FontWeight.BOLD, 30);
	    flText.setFont(flFont);
	  
	    //adding pieChart to the root.
	    pane.getChildren().addAll(hd, di, cr, ps, fl, hdText, diText, crText, psText, flText);
	    Scene scene = new Scene(pane, 500, 500);

	    primaryStage.setTitle("Grade Distribution Test");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}

	public static void main(String[] args) {
	    launch(args);
	}

}