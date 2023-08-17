package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PieChartDemo_MySQL extends Application{
	//PIE CHART DATA
    private ObservableList<Data> data; // for the pie chart
    private ArrayList<String> names; // for the bar chart
    private ArrayList<Double> count; // for the bar chart
    private static Connection conn;

    //MAIN EXECUTOR
    public static void main(String[] args) {
        Application.launch(PieChartDemo_MySQL.class, args);
    }

    //CONNECTION DATABASE SAVING DATA
    public void buildData() throws ClassNotFoundException{
    	// loads and checks the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded:");
		
		// connection for database...make sure the URL is correct JDBC:MYSQL
		String url = "jdbc:mysql://127.0.0.1:3306/javabook";
		String username = "root";
		String password = "mysql";
		
		// connect to the database
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected:");
			
			data = FXCollections.observableArrayList();			
			 //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT State, COUNT(*) FROM `javabook`.`ex 34.1` GROUP BY State";
            
            System.out.println(SQL);

            ResultSet rs = conn.createStatement().executeQuery(SQL);
            names = new ArrayList<String>();
            count = new ArrayList<Double>();
            
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(1),rs.getDouble(2)));
                names.add(rs.getString(1)); // add the column value to the names array
                count.add(rs.getDouble(2)); // add the column value to the count array
            }
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} 
      }

      @Override
      public void start(Stage stage) throws Exception {	      
        //PIE CHART
        PieChart pieChart = new PieChart();
        buildData(); // build the data from the SQL connection and query
        pieChart.getData().addAll(data);
        
  	    //BAR CHART
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("State"); // set the bar x title

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count"); // set the bar y title

        BarChart barChart = new BarChart(xAxis, yAxis);
        
        // need a X Y chart for the bar chart
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series();
		dataSeries1.setName("State");
		for (int i = 0; i < names.size(); i++ ) {
			dataSeries1.getData().add(new XYChart.Data<>(names.get(i), count.get(i)));
		}
        
        barChart.getData().add(dataSeries1); // add the X Y chart to the bar chart
        
        HBox hbox = new HBox(pieChart, barChart); // create a horizontal box

        //Main Scene
        Scene scene = new Scene(hbox);        
        stage.setScene(scene);
        stage.show();
      }
}
