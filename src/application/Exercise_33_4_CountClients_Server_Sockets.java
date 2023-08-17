package application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class Exercise_33_4_CountClients_Server_Sockets extends Application{
	  // Text area for displaying contents
	  private TextArea ta = new TextArea();
	  
	  // Number a client
	  private int clientNo;
	  
	  // Random Access file
	  private RandomAccessFile raf;

	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) { 
		  
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(new ScrollPane(ta), 450, 200);
	    primaryStage.setTitle("MultiThreadServer"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    // new thread to run on a new connection
	    new Thread( () -> {
	      try {
	    	// create or open the count file
	    	raf = new RandomAccessFile("count.dat", "rw");
	    	raf.seek(0);
	    	
	    	// get the count from the file from previous sessions
	    	if (raf.length() == 0) {
	    		clientNo = 0;
	    	} else {
	    		clientNo = raf.read();
	    	}
	    	
	        // Create a server socket
	        ServerSocket serverSocket = new ServerSocket(8000);
	        ta.appendText("MultiThreadServer started at " 
	          + new Date() + '\n');
	    
	        while (true) {
	          // Listen for a new connection request
	          Socket socket = serverSocket.accept();
	    
	          // Increment clientNo
	          clientNo++;
	          
	          // update the file to the current client number
	          raf.seek(0);
	          raf.write(clientNo);
	          
	          Platform.runLater( () -> {
	            // Display the client number
	            ta.appendText("Starting thread for client " + clientNo +
	              " at " + new Date() + '\n');

	            // Find the client's host name, and IP address
	            InetAddress inetAddress = socket.getInetAddress();
	            ta.appendText("Client " + clientNo + "'s host name is "
	              + inetAddress.getHostName() + "\n");
	            ta.appendText("Client " + clientNo + "'s IP Address is "
	              + inetAddress.getHostAddress() + "\n");
	          });
	          
	          // Create and start a new thread for the connection
	          new Thread(new HandleAClient(socket)).start();
	        }
	      }
	      catch(IOException ex) {
	        System.err.println(ex);
	      }
	    }).start();
	  }
	  
	  // Define the thread class for handling new connection from a client
	  class HandleAClient implements Runnable {
	    private Socket socket; // A connected socket

	    /** Construct a thread */
	    public HandleAClient(Socket socket) {
	      this.socket = socket;
	    }

	    /** Run a thread */
	    public void run() {
	      try {
	        // Create data input and output streams
	        DataInputStream inputFromClient = new DataInputStream(
	          socket.getInputStream());
	        DataOutputStream outputToClient = new DataOutputStream(
	          socket.getOutputStream());

	        // Continuously serve the client
	        while (true) {
	          // Receive radius from the client
	          double radius = inputFromClient.readDouble();

	          // Compute area
	          double area = radius * radius * Math.PI;

	          // Send area back to the client
	          outputToClient.writeDouble(area);
	          outputToClient.writeDouble(clientNo);
	          
	          Platform.runLater(() -> {
	            ta.appendText("radius received from client: " +
	              radius + '\n');
	            ta.appendText("Area found: " + area + '\n');
	          });
	        }
	      }
	      catch(IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
	  
	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
	}