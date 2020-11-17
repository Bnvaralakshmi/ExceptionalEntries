package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EE_UtilityFile {
	public Connection conn = null;
    
	public void OpenConnection() throws SQLException{
    	String conString = "jdbc:ingres://i-tapaculo:CL7/sqadb";
        String userid = "test";
        String password = "passmark100"; 
     
  	   try{
   	        Class.forName("com.ingres.jdbc.IngresDriver");
  	        System.out.println("Connecting to database...");
  	  
  	        conn = DriverManager.getConnection(conString,userid,password);
  	        DriverManager.getConnection(conString, userid, password);
  	       }
  	            catch(SQLException se){
  	        	System.out.println("JDBC error ...");
  	            se.printStackTrace();
  	        }catch(ClassNotFoundException e){
  	        	System.out.println("Class.forName error ...");
  	            e.printStackTrace();
  	        }
    }   
    
}


