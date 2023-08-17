package application;

import java.io.*;
import java.net.*;

public class Exercise_33_6_StudentServer {
	  private ObjectOutputStream outputToFile;
	  private ObjectInputStream outputFromFile;
	  private ObjectInputStream inputFromClient;
	  private ObjectOutputStream outputFromServer;

	  public static void main(String[] args) {
	    new Exercise_33_6_StudentServer();
	  }

	  public Exercise_33_6_StudentServer() {
	    try {
	      // Create a server socket
	      ServerSocket serverSocket = new ServerSocket(8001);
	      System.out.println("Server started ");

	      // Create an object output stream
	      outputToFile = new ObjectOutputStream(
	        new FileOutputStream("student.dat", true));

	      while (true) {
	        // Listen for a new connection request
	        Socket socket = serverSocket.accept();

	        // Create an input and output stream from the socket
	        inputFromClient = new ObjectInputStream(socket.getInputStream());
	        outputFromServer = new ObjectOutputStream(socket.getOutputStream());
	        
	        FileInputStream fileStream = new FileInputStream("student.dat");
	        ObjectInputStream input = new ObjectInputStream(fileStream);
	        
	        //Read object from file
	        while (fileStream.available() != 0) {
		        Exercise_33_6_StudentAddress st = (Exercise_33_6_StudentAddress) input.readObject();
		        if (st != null) {
		        	System.out.println(st);
		        	outputFromServer.writeObject(st);
		        }		        
	        }
	        
	     // Read from input
	        Object object = inputFromClient.readObject();

	        // Write to the file
	        outputToFile.writeObject(object);
	        System.out.println("A new student object is stored");
	      }
	    }
	    catch(ClassNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch(IOException ex) {
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        inputFromClient.close();
	        outputToFile.close();
	      }
	      catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }
	  }
}
