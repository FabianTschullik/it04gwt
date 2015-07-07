package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Benutzer;

/**
 * Mapper-Klasse, die <code>Bauteil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfuegung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden koennen. Das Mapping ist bidirektional. D.h., Objekte
 * koennen in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden. * @see
 * BaugruppeMapper, EnderzeugnisMapper, BenutzerMapper
 * 
 * @author Maehler, Voelker, Thies
 */

public class BenutzerMapper {

	/**
	 * Die Klasse BenutzerMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt ueber die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static BenutzerMapper benutzerMapper = null;

	/**
	 * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BenutzerMapper() {
	}

	/**
	 * Diese statische Methode kann aufgerufen werden durch
	 * <code>BauteilMapper.bauteilMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine
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

	/**
	 * 
	 * @param mail
	 * @return benutzer
	 */
	public Benutzer saveBenutzer(String mail) {

		Benutzer benutzer = new Benutzer();
		benutzer.setEmail(mail);

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("INSERT INTO benutzer (mail, erstellungsDatum) VALUES( '"
					+ mail + "','" + new Timestamp(date.getTime()) + "')");

			return benutzer;

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param mail
	 * @return benutzer
	 */
	public Benutzer checkBenutzer(String mail) {

		Benutzer benutzer = new Benutzer();

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken
			stmt.executeUpdate("SELECT mail FROM benutzer WHERE mail = '"
					+ mail + "'");

			benutzer.setEmail(mail);

			/*
			 * 
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			return benutzer;

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
	}
}