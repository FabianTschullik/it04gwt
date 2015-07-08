package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * Mapper-Klasse, die <code>Baugruppe</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfuegung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden koennen. Das Mapping ist bidirektional. D.h., Objekte
 * koennen in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @author Maehler, Voelker, Tschullik, Thies
 */
public class EnderzeugnisMapper {

	/**
	 * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt ueber die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static EnderzeugnisMapper enderzeugnisMapper = null;

	/**
	 * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected EnderzeugnisMapper() {
	}

	/**
	 * Diese statische Methode kann aufgerufen werden durch
	 * <code>BaugruppeMapper.baugruppeMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine
	 * einzige Instanz von <code>BaugruppeMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> BaugruppeMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>BaugruppeMapper</code>-Objekt.
	 * @see BaugruppeMapper
	 */
	public static EnderzeugnisMapper enderzeugnisMapper() {
		if (enderzeugnisMapper == null)
			enderzeugnisMapper = new EnderzeugnisMapper();

		return enderzeugnisMapper;
	}

	/**
	 * Suchen einer Baugruppe mit vorgegebener id. Da diese eindeutig ist, wird
	 * genau ein Vektor-Objekt zurueckgegeben.
	 * 
	 * Warum Vektor? Da im spaeteren Verlauf die Methode findByKey und
	 * findByName zusammen gefuehrt werden. So ist es moeglich ueber das
	 * Suchfeld per Name und id zusuchen. Der Vektor ist notwendig, da der Name
	 * nicht als primaer Schluessel gekennzeichnet ist. Daher koennen auch
	 * mehrere Ergebnise zurueck gegeben werden. Der Vektor ist fuer die
	 * findByKey Methode im Prinzip nicht notwendig.
	 * 
	 * @param id
	 *            Primaerschluesselattribut (DB)
	 * @return Konto-Objekt-Vektor, das dem uebergebenen Schluessel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */
	public Baugruppe findConnectedBaugruppe(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT baugruppe.id, baugruppe.name, baugruppe.beschreibung, baugruppe.letzterBearbeiter, baugruppe.erstellungsDatum, baugruppe.aenderungsDatum "
							+ "FROM enderzeugnis, baugruppe "
							+ "WHERE enderzeugnis.baugruppe = baugruppe.id "
							+ "AND enderzeugnis.id = " + id);

			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			if (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
				bg.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bg.setBeschreibung(rs.getString("beschreibung"));
				bg.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bg.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				return bg;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Einfuegen eines <code>Enderzeugnis</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschluessel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt.
	 * 
	 * @return das bereits uebergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Enderzeugnis insert() {
		Connection con = DbConnection.connection();

		Enderzeugnis ez = new Enderzeugnis();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM enderzeugnis ");

			// Wenn wir etwas zurueckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * bt erhaelt den bisher maximalen, nun um 1 inkrementierten
				 * Primaerschluessel.
				 */
				ez.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Aktuelle Zeit fuer Timestamp erstellungsDatum,
				// aenderungsDatum
				// holen

				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());

				ez.setAenderungsDatum(timestamp);
				ez.setErstellungsDatum(timestamp);

				// Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
				stmt.executeUpdate("INSERT INTO enderzeugnis (id, erstellungsDatum, aenderungsDatum) "
						+ "VALUES ('"
						+ ez.getId()
						+ "','"
						+ new Timestamp(date.getTime())
						+ "','"
						+ new Timestamp(date.getTime()) + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Rueckgabe des evtl. korrigierten Bauteils.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte uebergeben werden, waere die Anpassung des Bauteil-Objekts
		 * auch ohne diese explizite Rueckgabe ausserhalb dieser Methode
		 * sichtbar. Die explizite Rueckgabe von be ist eher ein Stilmittel, um
		 * zu signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * veraendert hat.
		 */
		return ez;
	}

	/**
	 * Auslesen aller Enderzeugnisse.
	 * 
	 * @return Ein Vektor mit Enderzeugnis-Objekten, die saemtliche
	 *         Enderzeugnisse repraesentieren. Bei evtl. Exceptions wird ein
	 *         partiell gefuellter oder ggf. auch leerer Vetor zurueckgeliefert.
	 */
	public Vector<Enderzeugnis> findAll() {
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Enderzeugnis> result = new Vector<Enderzeugnis>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, preis, baugruppe, erstellungsDatum, aenderungsDatum, letzterBearbeiter, beschreibung FROM enderzeugnis "
							+ " ORDER BY id");

			// Fuer jeden Eintrag im Suchergebnis wird nun ein Bauteil-Objekt
			// erstellt.
			while (rs.next()) {
				Enderzeugnis ez = new Enderzeugnis();
				ez.setId(rs.getInt("id"));
				ez.setName(rs.getString("name"));
				ez.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				ez.setPreis(rs.getDouble("preis"));
				ez.setBaugruppe(rs.getInt("baugruppe"));
				ez.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				ez.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));
				ez.setBeschreibung(rs.getString("beschreibung"));

				// Hinzufuegen des neuen Objekts zum Ergebnisvektor
				result.addElement(ez);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param ez
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter uebergebene Objekt
	 */
	public Vector<Enderzeugnis> updateEnderzeugnis(Enderzeugnis ez) {

		Connection con = DbConnection.connection();

		Vector<Enderzeugnis> result = new Vector<Enderzeugnis>();

		try {
			Statement stmt = con.createStatement();

			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("UPDATE enderzeugnis SET name = '"
					+ ez.getName() + "', " + "aenderungsDatum = '"
					+ new Timestamp(date.getTime()) + "', " + "preis = "
					+ ez.getPreis() + ", " + "baugruppe = " + ez.getBaugruppe()
					+ ", " + "beschreibung = '" + ez.getBeschreibung() + "', "
					+ "letzterBearbeiter = '" + ez.getLetzterBearbeiter()
					+ "' WHERE id= " + ez.getId());

			result.add(ez);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Suchen eines Bauteils mit vorgegebener id. Da diese eindeutig ist, wird
	 * genau ein Vektor-Objekt zurueckgegeben.
	 * 
	 * Warum Vektor? Da im spaeteren Verlauf die Methode findByKey und
	 * findByName zusammen gefuehrt werden. So ist es moeglich ueber das
	 * Suchfeld per Name und id zusuchen. Der Vektor ist notwendig, da der Name
	 * nicht als primaer Schluessel gekennzeichnet ist. Daher koennen auch
	 * mehrere Ergebnisse zurueck gegeben werden. Der Vektor ist fuer die
	 * findByKey Methode im Prinzip nicht notwendig.
	 * 
	 * @param id
	 *            Primaerschluesselattribut (DB)
	 * @return Konto-Objekt-Vektor, das dem uebergebenen Schluessel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Enderzeugnis> findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Enderzeugnis> result = new Vector<Enderzeugnis>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, baugruppe, preis, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM enderzeugnis "
							+ "WHERE id=" + id);
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Enderzeugnis bt = new Enderzeugnis();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setBeschreibung(rs.getString("beschreibung"));
				bt.setBaugruppe(rs.getInt("baugruppe"));
				bt.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.addElement(bt);

				return result;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Suchen eines Enderzeugnisses mit vorgegebenen Namen. Da dieser nicht
	 * eindeutig ist, wird ein Vektor-Objekt zurueckgegeben.
	 * 
	 * Warum Vektor? Da name kein Primaerschluesselattribut ist koennen
	 * Enderzeugnisse mit dem gleiche Namen in diesem Vektor zurueck gegeben
	 * werden.
	 * 
	 * @return Konto-Objekt-Vektor, das dem uebergebenen namen entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Enderzeugnis> findByName(String name) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		Vector<Enderzeugnis> result = new Vector<Enderzeugnis>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM enderzeugnis "
							+ "WHERE name=" + "'" + name + "'");
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Enderzeugnis ez = new Enderzeugnis();
				ez.setId(rs.getInt("id"));
				ez.setName(rs.getString("name"));
				ez.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				ez.setBeschreibung(rs.getString("beschreibung"));
				ez.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				ez.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.add(ez);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Loescht ein Enderzeugnis aus der Datenbank
	 * 
	 * @param id
	 * @return ergebnis als String-Erfolgsmeldung
	 */
	public String deleteEnderzeugnis(int id) {

		String ergebnis = "Enderzeugnis wurde erfolgreich geloescht!";

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM enderzeugnis " + "WHERE id = " + id);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return ergebnis;
	}
}