package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class ObjectIOExample {
 
    private static final String filepath="student.dat";
 
    public static void main(String args[]) {
 
        ObjectIOExample objectIO = new ObjectIOExample();
 
        //Read object from file
        Exercise_33_6_StudentAddress st = (Exercise_33_6_StudentAddress) objectIO.ReadObjectFromFile(filepath);
        System.out.println("Name: " + st.getName());
        System.out.println("Street: " + st.getStreet());
        System.out.println("City: " + st.getCity());
        System.out.println("State: " + st.getState());
        System.out.println("Zip: " + st.getZip());
    }
 
    public Object ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}