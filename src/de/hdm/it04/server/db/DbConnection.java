package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.DriverManager;



public class DbConnection {

   
    
    private static Connection con = null;

   
     
    //private static String googleUrl = "jdbc:google:mysql://it04sms:it04/sms?user=root";
    private static String localUrl = "jdbc:mysql://127.0.0.1:3306/sms?user=root&password=root";

  
    public static Connection connection() {
       
        if (con == null) {
            String url = null;
            try {
               
                    
                    Class.forName("com.mysql.jdbc.Driver");  //für local use: "com.mysql.jdbc.Driver" or "com.mysql.jdbc.GoogleDriver" for cloud sql
                    url = localUrl;
               
                
              
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
            }
        }

    
        return con;
    }

}































