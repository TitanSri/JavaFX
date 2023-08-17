package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TaxManagementDatabaseApplication extends Application{
	
	private Label labelStatus = new Label();
	
	private Label labelId = new Label("ID:");
	private TextField textId = new TextField();
	private Label labelFinancialYear = new Label("Finanical Year:");
	private TextField textFinancialYear = new TextField();
	private Label labelTaxableIncome = new Label("Taxable Income:");
	private TextField textTaxableIncome = new TextField();
	private Label labelTax = new Label("Tax:");
	private TextField textTax = new TextField();
	
	private Button buttonCalculate = new Button("Calculate");
	private Button buttonSearch = new Button("Search");
	private Button buttonUpdate = new Button("Update");
	private Button buttonDelete = new Button("Delete");
	
	private Connection connection;
	private Statement statement;
	private ResultSet results;
	
	private static HashMap<Integer, List> brackets = new HashMap<Integer, List>();
	private static double calculated;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// first vertical box
		VBox vbox1 = new VBox(20);
		vbox1.getChildren().addAll(labelId, labelFinancialYear, labelTaxableIncome, labelTax, new Label(), buttonSearch);
		
		// second vertical box
		VBox vbox2 = new VBox(10);
		vbox2.getChildren().addAll(textId, textFinancialYear, textTaxableIncome, textTax, buttonCalculate, buttonUpdate);
		vbox2.setAlignment(Pos.BASELINE_RIGHT); // align all the nodes to the right
		VBox.setMargin(buttonUpdate, new Insets(0, 150, 0, 0)); // need to move the update button to the left
		
		// third vertical box
		VBox vbox3 = new VBox(20);
		vbox3.getChildren().addAll(new Label(), new Label(), new Label(), new Label(), new Label(), buttonDelete);
		
		// put all the vertical box into a horizontal box
		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(vbox1, vbox2, vbox3);
		
		labelStatus.setTextFill(Color.RED);
		
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(labelStatus);
		borderpane.setCenter(hbox);
//		borderpane.setBottom(bottomBox);
		
		Scene scene = new Scene(borderpane);
		primaryStage.setTitle("Database and Multithreaded Programming");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		getTaxRates();
		initialDB();
		
		buttonSearch.setOnAction(e -> search());
		buttonUpdate.setOnAction(e -> update());
		buttonDelete.setOnAction(e -> delete());
		buttonCalculate.setOnAction(e -> calculate());
		
		
	}
	
	private void getTaxRates() throws IOException {
		// PrintWriter for writing 
		// to output.txt file
		PrintWriter pw
			= new PrintWriter("src/output.txt");

		// Compile() argument is the
		// regular expression for dollar
		Pattern p
			= Pattern.compile(
					"0|[0-9]*"
					+ ","
					+ "[0-9]*");
		
		// regular expression for cents
				Pattern cp
					= Pattern.compile(
							"[0-9]c|[0-9]*.[0-9]*"
							+ " cents");

		// Calling extract
		extractDollars(pw, p);
		extractCents(pw, cp);
		
		// lazy print
		System.out.print(brackets);
		
		// iterate print
		for (Entry<Integer, List> set : brackets.entrySet()){
			System.out.println(set.getKey() + " = " + set.getValue());
		}
		
		// get a particle index from list in key 
		String s = (String) brackets.get(2).get(1);
		String s2 = s.replace(",","");
		System.out.println(s2);
		Double num = Double.parseDouble(s2);
		System.out.println(num);
		
		
	}

	public void initialDB() throws ClassNotFoundException, SQLException {
		// loads and checks the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded:");
		
		// connection for database...make sure the URL is correct JDBC:MYSQL
		String url = "jdbc:mysql://127.0.0.1:3306/taxmanagementsystem";
		String username = "root";
		String password = "mysql";
		
		// connect to the database
		try {
			connection = DriverManager.getConnection(url, username, password);
			labelStatus.setText("Database connected");
			System.out.println("Database connected:");
			statement = connection.createStatement();
		} catch (SQLException ex) {
			labelStatus.setText("Connection failed");
			System.out.println(ex.getMessage());
		}
		
	}
	
	public void calculate() {
		// we need to save the text before the search loads the fields
		String fy = textFinancialYear.getText();
		String ti = textTaxableIncome.getText();
		double answer;
		
		// run the search to see if the record exist 
		search();
		if (labelStatus.getText() == "Record not found") {
			return;
		}
		
		// set the text back before updating 
		textFinancialYear.setText(fy);
		textTaxableIncome.setText(ti);
		
		Double ta = Double.parseDouble(ti);
		
		// get a particle index from list in key 
		String s5 = (String) brackets.get(5).get(0);
		String ss5 = s5.replace(",","");
		System.out.println(ss5);
		Double num5 = Double.parseDouble(ss5);
		System.out.println("num5: " + num5);
		
		String a5 = (String) brackets.get(5).get(1);
		String aa5 = a5.replace(",","");
		System.out.println(aa5);
		Double additionalCharge5 = Double.parseDouble(aa5);
		System.out.println("additional charge 5: " + additionalCharge5);
		
		String c5 = (String) brackets.get(5).get(brackets.get(5).size() - 1);
		String cc5 = c5.replace(" cents","");
		System.out.println(cc5);
		Double cents5 = Double.parseDouble(cc5);
		System.out.println(cents5);
		
		String s4 = (String) brackets.get(4).get(0);
		String ss4 = s4.replace(",","");
		System.out.println(ss4);
		Double num4 = Double.parseDouble(ss4);
		System.out.println("num4: " + num4);
		
		String a4 = (String) brackets.get(4).get(2);
		String aa4 = a4.replace(",","");
		System.out.println(aa4);
		Double additionalCharge4 = Double.parseDouble(aa4);
		System.out.println("additional charge 4: " + additionalCharge4);
		
		String c4 = (String) brackets.get(4).get(brackets.get(4).size() - 1);
		String cc4 = c4.replace(" cents","");
		System.out.println(cc4);
		Double cents4 = Double.parseDouble(cc4);
		System.out.println(cents4);
		
		String s3 = (String) brackets.get(3).get(0);
		String ss3 = s3.replace(",","");
		System.out.println(ss3);
		Double num3 = Double.parseDouble(ss3);
		System.out.println("num3: " + num3);
		
		String a3 = (String) brackets.get(3).get(2);
		String aa3 = a3.replace(",","");
		System.out.println(aa3);
		Double additionalCharge3 = Double.parseDouble(aa3);
		System.out.println("additional charge 3: " + additionalCharge3);
		
		String c3 = (String) brackets.get(3).get(brackets.get(3).size() - 1);
		String cc3 = c3.replace(" cents","");
		System.out.println(cc3);
		Double cents3 = Double.parseDouble(cc3);
		System.out.println(cents3);
		
		String s2 = (String) brackets.get(2).get(0);
		String ss2 = s2.replace(",","");
		System.out.println(ss2);
		Double num2 = Double.parseDouble(ss2);
		System.out.println("num2: " + num2);
		
		String c2 = (String) brackets.get(2).get(brackets.get(2).size() - 1);
		String cc2 = c2.replace("c","");
		System.out.println(cc2);
		Double cents2 = Double.parseDouble(cc2);
		System.out.println(cents2);
		
		String s1 = (String) brackets.get(1).get(0);
		String ss1 = s1.replace(",","");
		System.out.println(ss1);
		Double num1 = Double.parseDouble(ss1);
		System.out.println("num1: " + num1);
		
		String c1 = (String) brackets.get(1).get(brackets.get(1).size() - 1);
//		String cc1 = c1.replace(" cents","");
//		System.out.println(cc1);
		Double cents1 = Double.parseDouble(c1);
		System.out.println(cents1);
		
		System.out.println(ta);
		
//		calculated = ta * 5;
		 if (ta > num5 - 1) {
			 calculated = additionalCharge5 + ((ta - num5 + 1) * cents5);
			 System.out.println("Here5");
		 } else if ( ta > num4 - 1) {
			 calculated = additionalCharge4 + ((ta - num4 + 1) * cents4);
			 System.out.println("Here4");
		 } else if ( ta > num3 - 1) {
			 calculated = additionalCharge3 + ((ta - num3 +1) * cents3);
			 System.out.println("Here3");
		 } else if ( ta > num2 - 1) {
			 calculated = (ta - num2 + 1) * cents2;
			 System.out.println("Here2");
		 } else  {
			 calculated = 0;
			 System.out.println("Here1");
		 }
		
		
		String updateQueryCalculate = "UPDATE `taxmanagementsystem`.`taxresult` SET `Tax` = " + calculated + " WHERE ID = '" + textId.getText().trim() + "';";
		
		System.out.println(updateQueryCalculate);
		
		try {
			// execute statement
			statement.executeUpdate(updateQueryCalculate);
			labelStatus.setText("Update completed:");
			System.out.println("Update suceeded");
			textTax.setText(Double.toString(calculated));
		} catch (SQLException Ex){
			labelStatus.setText("Update failed:");
			System.out.println(Ex.getMessage());
		}
		System.out.println("Done");
		
	}
	
	public void search() {
		String query = "SELECT * FROM `taxmanagementsystem`.`taxresult` WHERE ID = '" + textId.getText().trim() + "'";
		
		System.out.println(query);
		
		try {
			// execute statement
			results = statement.executeQuery(query);
			loadFields(results);
		} catch (SQLException Ex) {
			labelStatus.setText("Record failed:");
			System.out.println(Ex.getMessage());
		}
	}
	
	private void update() {
		// we need to save the text before the search loads the fields
		String fy = textFinancialYear.getText();
		String ti = textTaxableIncome.getText();
		String t = textTax.getText();
		// run the search to see if the record exist 
		search();
		if (labelStatus.getText() == "Record not found") {
			return;
		}
		
		// set the text back before updating 
		textFinancialYear.setText(fy);
		textTaxableIncome.setText(ti);
		textTax.setText(t);
		
		String updateQuery = "UPDATE `taxmanagementsystem`.`taxresult` SET "
				+ "Financial_Year = '" + textFinancialYear.getText().trim()+ 
				"', Taxable_Income = '" + textTaxableIncome.getText().trim() + 
				"', Tax = '" + textTax.getText().trim() + 
				"' WHERE ID = '" + textId.getText().trim() + "';";
		
		System.out.println(updateQuery);
		
		try {
			// execute statement
			statement.executeUpdate(updateQuery);
			labelStatus.setText("Update completed:");
			System.out.println("Update suceeded");
		} catch (SQLException Ex){
			labelStatus.setText("Update failed:");
			System.out.println(Ex.getMessage());
		}
	}
	
	private void delete() {
		String deleteQuery = "DELETE FROM `taxmanagementsystem`.`taxresult` WHERE ID = '" + textId.getText().trim() + "'";
		
		try {
			// execute statement
			statement.executeUpdate(deleteQuery);
			labelStatus.setText("Delete completed:");
			System.out.println("Delete suceeded");
			
		} catch (SQLException Ex){
			labelStatus.setText("Delete failed:");
			System.out.println(Ex.getMessage());
		}
	}
	
	public void loadFields(ResultSet results) throws SQLException {
		if (results.next()) {
			textId.setText(results.getString(1));
			textFinancialYear.setText(results.getString(2));
			textTaxableIncome.setText(results.getString(3));
			textTax.setText(results.getString(4));
			labelStatus.setText("Record found");
			System.out.println("Record found");
		} else {
			textId.setText("");
			textFinancialYear.setText("");
			textTaxableIncome.setText("");
			textTax.setText("");
			labelStatus.setText("Record not found");
			System.out.println("Record not found");
		}
	}
	
	public static void extractDollars(PrintWriter pw, Pattern p) throws IOException
	{
		try (// BufferedReader for reading
				// from input.txt file
				BufferedReader br = new BufferedReader(
					new FileReader("D:\\MyDocuments\\Eclipse\\JavaFX\\taxrates.txt"))
				) {
		String line = br.readLine();
		int i = 0;

		while (line != null) {
			Matcher m = p.matcher(line);
			List<String> set = new ArrayList<String>();

			// If any match
			while (m.find()) {

				// write the email id
				// to output.txt file
				pw.println(m.group());
				set.add(m.group());
				// display on the email address in the console
				System.out.print(m.group() + "\t");
			}
			System.out.println();
			// Goto next line in input.txt file
			brackets.put(i, set);
			line = br.readLine();
			i++;
		}
		}

		pw.flush();
	}
	
	public static void extractCents(PrintWriter pw, Pattern cp) throws IOException
		{
		try (// BufferedReader for reading
			// from input.txt file
			BufferedReader br = new BufferedReader(
				new FileReader("D:\\MyDocuments\\Eclipse\\JavaFX\\taxrates.txt"))
			) {
				String line = br.readLine();
				int i = 0;

				while (line != null) {

					Matcher m = cp.matcher(line);

					// If any match
					while (m.find()) {

						// write the email id
						// to output.txt file
						pw.println(m.group());
						brackets.get(i).add(m.group());
						// display on the email address in the console
						System.out.println(m.group());
					}

					// Goto next line in input.txt file
					line = br.readLine();
					i++;
				}
			}
				
			pw.flush();
		}
	
	public static void main(String[] args) {
		launch(args);
	}

}
