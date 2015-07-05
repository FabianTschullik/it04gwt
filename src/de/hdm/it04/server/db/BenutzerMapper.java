package de.hdm.it04.server.db;

	
	
	
	import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Timestamp;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Benutzer;
	
	public class BenutzerMapper {

	
		private static BenutzerMapper benutzerMapper = null;
		
		
		protected BenutzerMapper() {}


		public static BenutzerMapper benutzerMapper() {
			if (benutzerMapper == null)
				benutzerMapper = new BenutzerMapper();

			return benutzerMapper;
		}
		
	public Benutzer saveBenutzer(String mail) {
			
			Benutzer benutzer = new Benutzer();
			benutzer.setEmail(mail);

			// DB-Verbindung holen
			Connection con = DbConnection.connection();
			
			

			try {

				// Leeres SQL-Statement (JDBC) anlegen

				Statement stmt = con.createStatement();

				// Statement ausf�llen und als Query an die DB schicken
				
				Date date = new Date();
				new Timestamp(date.getTime());

						stmt.executeUpdate("INSERT INTO benutzer (mail, erstellungsDatum) VALUES( '" + mail+ "','"+new Timestamp(date.getTime())+"')");
						
						


		return benutzer;

			} catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}

		}
		
		

		
		public Benutzer checkBenutzer(String mail) {
			
			Benutzer benutzer = new Benutzer();

			// DB-Verbindung holen
			Connection con = DbConnection.connection();

			// Ergebnisvektor vorbereiten
			Vector<Baugruppe> result = new Vector<Baugruppe>();

			try {

				// Leeres SQL-Statement (JDBC) anlegen

				Statement stmt = con.createStatement();

				// Statement ausf�llen und als Query an die DB schicken

						stmt.executeUpdate("SELECT mail FROM benutzer WHERE mail = '"+mail+"'");
					
						benutzer.setEmail(mail);
						
				/*
				 *
				 * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
				 * werden. Pr�fe, ob ein Ergebnis vorliegt.
				 */

		return benutzer;

			} catch (SQLException e2) {
				e2.printStackTrace();
				return null;
			}
			
		}
		
		
		
		}
	
	
	


