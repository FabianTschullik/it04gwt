package de.hdm.it04.server.db;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Timestamp;

import de.hdm.it04.shared.Bauteil;

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
public class BauteilMapper {

	/**
	 * Die Klasse BauteilMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt ueber die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static BauteilMapper bauteilMapper = null;

	/**
	 * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BauteilMapper() {
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
	public static BauteilMapper bauteilMapper() {
		if (bauteilMapper == null)
			bauteilMapper = new BauteilMapper();

		return bauteilMapper;
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
	 *            Primaerschluesselattribut (->DB)
	 * @return Konto-Objekt-Vektor, das dem uebergebenen Schluessel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Bauteil> findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Bauteil> result = new Vector<Bauteil>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM bauteil "
							+ "WHERE id=" + id);
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Bauteil bt = new Bauteil();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setBeschreibung(rs.getString("beschreibung"));
				bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
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
	 * Einfuegen eines <code>Bauteil</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primaerschluessel des uebergebenen Objekts geprueft und ggf.
	 * berichtigt.
	 * 
	 * @param bt
	 *            das zu speichernde Objekt
	 * @return das bereits uebergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Bauteil insert() {
		Connection con = DbConnection.connection();

		Bauteil bt = new Bauteil();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM bauteil ");

			// Wenn wir etwas zurueckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * bt erhaelt den bisher maximalen, nun um 1 inkrementierten
				 * Primaerschluessel.
				 */
				bt.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Aktuelle Zeit fuer Timestamp erstellungsDatum,
				// aenderungsDatum
				// holen

				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());

				bt.setAenderungsDatum(timestamp);
				bt.setErstellungsDatum(timestamp);

				// Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
				stmt.executeUpdate("INSERT INTO bauteil (id, erstellungsDatum, aenderungsDatum) "
						+ "VALUES ("
						+ bt.getId()
						+ ",'"
						+ new Timestamp(date.getTime())
						+ "','"
						+ new Timestamp(date.getTime()) + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * Rueckgabe, des evtl. korrigierten Bauteils.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte uebergeben werden, waere die Anpassung des Bauteil-Objekts
		 * auch ohne diese explizite Rueckgabe ausserhalb dieser Methode
		 * sichtbar. Die explizite Rueckgabe von be ist eher ein Stilmittel, um
		 * zu signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * veraendert hat.
		 */
		return bt;
	}

	/**
	 * Auslesen aller Bauteile.
	 * 
	 * @return Ein Vektor mit Bauteil-Objekten, die saemtliche Bauteile
	 *         repraesentieren. Bei evtl. Exceptions wird ein partiell
	 *         gefuellter oder ggf. auch leerer Vetor zurueckgeliefert.
	 */
	public Vector<Bauteil> findAll() {
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Bauteil> result = new Vector<Bauteil>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM bauteil "
							+ " ORDER BY id");

			// Fuer jeden Eintrag im Suchergebnis wird nun ein Bauteil-Objekt
			// erstellt.
			while (rs.next()) {
				Bauteil bt = new Bauteil();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setBeschreibung(rs.getString("beschreibung"));
				bt.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
				bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				// Hinzufuegen des neuen Objekts zum Ergebnisvektor
				result.addElement(bt);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	/**
	 * Suchen eines Bauteils mit vorgegebenen Namen. Da dieser nicht eindeutig
	 * ist, wird ein Vektor-Objekt zurueckgegeben.
	 * 
	 * Warum Vektor? Da name kein Primaerschluesselattribut ist koennen Bauteile
	 * mit dem gleiche Namen in diesem Vektor zurueck gegeben werden.
	 * 
	 * @return Konto-Objekt-Vektor, das dem uebergebenen namen entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Bauteil> findByName(String name) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		Vector<Bauteil> result = new Vector<Bauteil>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM bauteil "
							+ "WHERE name=" + "'" + name + "'");

			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Bauteil bt = new Bauteil();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setBeschreibung(rs.getString("beschreibung"));
				bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
				bt.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.add(bt);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param bt
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter uebergebene Objekt
	 */
	public Vector<Bauteil> update(Bauteil bt) {

		Connection con = DbConnection.connection();
		Vector<Bauteil> result = new Vector<Bauteil>();

		try {
			Statement stmt = con.createStatement();

			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("UPDATE bauteil SET name = '" + bt.getName()
					+ "', " + "beschreibung = '" + bt.getBeschreibung() + "', "
					+ "aenderungsDatum = '" + new Timestamp(date.getTime())
					+ "', " + "materialBezeichnung = '"
					+ bt.getMaterialBezeichnung() + "', "
					+ "letzterBearbeiter = '" + bt.getLetzterBearbeiter()
					+ "' WHERE id=" + bt.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Bauteil bt) zu wahren, geben wir bt zurueck
		result.add(bt);
		return result;
	}

	/**
	 * Loescht ein Bauteil aus der Datenbank
	 * 
	 * @param id
	 * @return ergebnis als String-Erfolgsmeldung
	 */
	public String delete(int id) {

		String ergebnis = "Bauteil wurde erfolgreich geloescht!";

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bauteil " + "WHERE id = " + id);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return ergebnis;
	}
}