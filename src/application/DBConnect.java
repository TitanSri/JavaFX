package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Narayan
 */

public class DBConnect {

    private static Connection conn;
    private static String url = "jdbc:mysql://127.0.0.1:3306/javabook";
    private static String user = "root";
    private static String pass = "mysql";

    public static Connection connect() throws SQLException, InstantiationException, IllegalAccessException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }

        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }
}