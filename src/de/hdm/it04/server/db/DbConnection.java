package de.hdm.it04.server.db;


import java.sql.Connection;

public class DbConnection {
	
	public static Connection verb = null;
	
	// Werte eintragen!
	
	private static String localurl = "Lokale Adresse";
	private static String googleurl = "Google Link";
	
	public static Connection connection(){
		
		
		return verb;
		
	}

}
