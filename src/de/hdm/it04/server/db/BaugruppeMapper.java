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
import de.hdm.it04.shared.Element;

/**
 * Mapper-Klasse, die <code>Baugruppe</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class BaugruppeMapper {

	/**
	 * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt über die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static BaugruppeMapper baugruppeMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BaugruppeMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>BaugruppeMapper.baugruppeMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine
	 * einzige Instanz von <code>BaugruppeMapper</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> BaugruppeMapper sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * 
	 * @return DAS <code>BaugruppeMapper</code>-Objekt.
	 * @see baugruppeMapper
	 */
	public static BaugruppeMapper baugruppeMapper() {
		if (baugruppeMapper == null)
			baugruppeMapper = new BaugruppeMapper();

		return baugruppeMapper;
	}

	/**
	 * Suchen einer Baugruppe mit vorgegebener id. Da diese eindeutig ist, wird
	 * genau ein Vektor-Objekt zurückgegeben.
	 * 
	 * Warum Vektor? Da im späteren Verlauf die Methode findByKey und findByName
	 * zusammen geführt werden. So ist es möglich über das Suchfeld per Name und
	 * id zusuchen. Der Vektor ist notwendig, da der Name nicht als primär
	 * Schlüssel gekennzeichnet ist. Daher können auch mehrere Ergebnise zurück
	 * gegeben werden. Der Vektor ist für die findByKey Methode im prinzip nicht
	 * notwendig.
	 * 
	 * @param id
	 *            Primärschlüsselattribut (->DB)
	 * @return Konto-Objekt-Vektor, das dem übergebenen Schlüssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Element> findConnectedBauteileByKey(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Element> result = new Vector<Element>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT bauteil.id, bauteil.beschreibung " +
									"FROM bauteil, baugruppe, bauteilBaugruppe " +
									"WHERE bauteil.id = bauteilBaugruppe.bauteil " +
									"AND baugruppe.id = bauteilBaugruppe.baugruppe " +
									"AND baugruppe.id=" + id);
							
							
			/*
			 * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */

			if (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Bauteil bt = new Bauteil();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setMaterialBezeichnung("materialBezeichnung");
				bt.setBeschreibung(rs.getString("beschreibung"));
				bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.add(bt);

				return result;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Einfügen eines <code>Baugruppen</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param bt
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Baugruppe insert() {
		Connection con = DbConnection.connection();
		
		Baugruppe bg = new Baugruppe();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zun�chst schauen wir nach, welches der momentan h�chste
			 * Prim�rschl�sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM baugruppe ");

			// Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * bt erh�lt den bisher maximalen, nun um 1 inkrementierten
				 * Prim�rschl�ssel.
				 */
				bg.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Aktuelle Zeit f�r Timestamp erstellungsDatum, aenderungsDatum
				// holen

				Date date = new Date();
				new Timestamp(date.getTime());

				// Jetzt erst erfolgt die tats�chliche Einf�geoperation
				stmt.executeUpdate("INSERT INTO baugruppe (id, erstellungsDatum, aenderungsDatum) "
						+ "VALUES ("
						+ bg.getId()
						+ ",'"
						+ new Timestamp(date.getTime())
						+ "','"
						+ new Timestamp(date.getTime()) + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * R�ckgabe, der evtl. korrigierten Baugruppe.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte �bergeben werden, w�re die Anpassung des Bauteil-Objekts auch
		 * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
		 * explizite R�ckgabe von be ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * ver�ndert hat.
		 */
		return bg;
	}

	/**
	 * Auslesen aller Bauteile.
	 * 
	 * @return Ein Vektor mit Bauteil-Objekten, die sämtliche Bauteile
	 *         repräsentieren. Bei evtl. Exceptions wird ein partiell gefüllter
	 *         oder ggf. auch leerer Vetor zurückgeliefert.
	 */
	public Vector<Baugruppe> findAll() {
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM baugruppe "
							+ " ORDER BY id");

			// Für jeden Eintrag im Suchergebnis wird nun ein Baugruppen-Objekt
			// erstellt.
			while (rs.next()) {
				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
				bg.setBeschreibung(rs.getString("beschreibung"));
				bg.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bg.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(bg);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

	/**
	 * Suchen eines Bauteils mit vorgegebenen namen. Da dieser nicht eindeutig
	 * ist, wird ein Vektor-Objekt zurückgegeben.
	 * 
	 * Warum Vektor? Da name kein Primörschlüsselattribut ist können Bauteile
	 * mit dem gleiche Namen in diesem Vektor zurück gegeben werden.
	 * 
	 * @return Konto-Objekt-Vektor, das dem übergebenen namen entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */
	public Vector<Baugruppe> findByName(String name) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM baugruppe "
							+ "WHERE name=" + "'" + name + "'");
			/*
			 * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
				bg.setBeschreibung(rs.getString("beschreibung"));
				bg.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bg.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.add(bg);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param bg
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public Baugruppe update(Baugruppe bg) {

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();
			
			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("UPDATE baugruppe SET name = '" + bg.getName()+ "', " 
					+ "beschreibung = '" + bg.getBeschreibung() + "', "
					+ "aenderungsDatum = '" + new Timestamp(date.getTime())
					+ "' WHERE id=" + bg.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Um Analogie zu insert(Baugruppe bg) zu wahren, geben wir bg zurück
		return bg;
	}

	public String delete(int id) {

		String ergebnis = "Baugruppe wurde erfolgreich geloescht!";

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM baugruppe " + "WHERE id = " + id);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return ergebnis;
	}

}

