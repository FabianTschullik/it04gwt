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
	
/**
 * Mapper-Klasse, die <code>Bauteil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 *  * @see BaugruppeMapper, EnderzeugnisMapper, BenutzerMapper
 * @author Maehler, Voelker, Thies
 */
	
public class BenutzerMapper {
		
		/**
		 * Die Klasse BenutzerMapper wird nur einmal instantiiert. Man spricht
		 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
		 * das Objekt über die Instanzoperation zugegriffen werden.
		 * <p>
		 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
		 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
		 * speichert die einzige Instanz dieser Klasse.
		 */
		private static BenutzerMapper benutzerMapper = null;
		
		/**
		 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
		 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
		 */
		protected BenutzerMapper() {}
		
		/**
		 * Diese statische Methode kann aufgerufen werden durch
		 * <code>BauteilMapper.bauteilMapper()</code>. Sie stellt die
		 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
		 * einzige Instanz von <code>BauteilMapper</code> existiert.
		 * <p>
		 * 
		 * <b>Fazit:</b> BauteilMapper sollte nicht mittels <code>new</code>
		 * instantiiert werden, sondern stets durch Aufruf dieser statischen
		 * Methode.
		 * 
		 * @return DAS <code>BauteilMapper</code>-Objekt.
		 * @see BauteilMapper
		 */

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