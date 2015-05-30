package de.hdm.it04.server.db;


import java.sql.*;

public class DbConnection {
	
	private static Connection con = null;
	
	// Werte eintragen!
	
	private static String localurl = "jdbc:mysql://127.0.0.1:3306/sms?user=root&password=root";
	private static String googleurl = "Google Link";
	
	public static Connection connection(){
		
		
		return con;
		
	}

}
