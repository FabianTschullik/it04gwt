package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.TeileListe;

/**
 * Mapper-Klasse, die <code>Baugruppe</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfuegung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden koennen. Das Mapping ist bidirektional. D.h., Objekte
 * koennen in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see BauteilMapper, EnderzeugnisMapper, BenutzerMapper
 * @author Geier, Maehler, Voelker, Tschullik, Thies
 */
public class BaugruppeMapper {

	/**
	 * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt ueber die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static BaugruppeMapper baugruppeMapper = null;

	/**
	 * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BaugruppeMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
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
	 * @see baugruppeMapper
	 */
	public static BaugruppeMapper baugruppeMapper() {
		if (baugruppeMapper == null)
			baugruppeMapper = new BaugruppeMapper();

		return baugruppeMapper;
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
	 * findByKey Methode im prinzip nicht notwendig.
	 * 
	 * @param id
	 *            Primaerschluesselattribut (->DB)
	 * @return Konto-Objekt-Vektor, das dem uebergebenen Schluessel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public Vector<Baugruppe> findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM baugruppe "
							+ "WHERE id=" + id);
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
				bg.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bg.setBeschreibung(rs.getString("beschreibung"));
				bg.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bg.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				result.add(bg);

				bg.connectedBauteile = findConnectedBauteile(bg.getId());
				bg.connectedBaugruppen = findConnectedBaugruppen(bg.getId());

				return result;
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Einfuegen eines <code>Baugruppen</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primaerschluessel des uebergebenen Objekts geprueft und
	 * ggf. berichtigt.
	 * 
	 * @param bt
	 *            das zu speichernde Objekt
	 * @return das bereits uebergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Baugruppe insert() {
		Connection con = DbConnection.connection();

		Baugruppe bg = new Baugruppe();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM baugruppe ");

			// Wenn wir etwas zurueckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * bt erhaelt den bisher maximalen, nun um 1 inkrementierten
				 * Primaerschluessel.
				 */
				bg.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Aktuelle Zeit fuer Timestamp erstellungsDatum,
				// aenderungsDatum
				// holen

				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());

				bg.setAenderungsDatum(timestamp);
				bg.setErstellungsDatum(timestamp);

				// Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
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
		 * Rueckgabe, der evtl. korrigierten Baugruppe.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte uebergeben werden, waere die Anpassung des Bauteil-Objekts
		 * auch ohne diese explizite Rueckgabe ausserhalb dieser Methode
		 * sichtbar. Die explizite Rueckgabe von be ist eher ein Stilmittel, um
		 * zu signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * veraendert hat.
		 */
		return bg;
	}

	/**
	 * Auslesen aller Bauteile.
	 * 
	 * @return Ein Vektor mit Bauteil-Objekten, die saemtliche Bauteile
	 *         repraesentieren. Bei evtl. Exceptions wird ein partiell
	 *         gefuellter oder ggf. auch leerer Vetor zurueckgeliefert.
	 */
	public Vector<Baugruppe> findAll() {
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM baugruppe "
							+ " ORDER BY id");

			// Fuer jeden Eintrag im Suchergebnis wird nun ein Baugruppen-Objekt
			// erstellt.
			while (rs.next()) {
				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setLetzterBearbeiter(rs.getString("letzterBearbeiter"));
				bg.setName(rs.getString("name"));
				bg.setBeschreibung(rs.getString("beschreibung"));
				bg.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
				bg.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

				bg.connectedBauteile = findConnectedBauteile(bg.getId());
				bg.connectedBaugruppen = findConnectedBaugruppen(bg.getId());

				// Hinzufuegen des neuen Objekts zum Ergebnisvektor
				result.addElement(bg);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurueckgeben
		return result;
	}

	public Vector<Baugruppe> findByName(String name) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM baugruppe "
							+ "WHERE name=" + "'" + name + "'");
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
				bg.setName(rs.getString("letzterBearbeiter"));
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

	public void updateBauteilBaugruppe(int id, int anzahl) {

		Connection con = DbConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("UPDATE bauteilBaugruppe SET anzahl = '"
					+ anzahl + "' " + " WHERE id=" + id);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void updateBaugruppeBaugruppe(int id, int anzahl) {

		Connection con = DbConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE baugruppeBaugruppe SET anzahl = '"
					+ anzahl + "' " + " WHERE id=" + id);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param bg
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter uebergebene Objekt
	 */
	public Vector<Baugruppe> updateBaugruppe(Baugruppe bg) {

		Vector<Baugruppe> result = new Vector<Baugruppe>();
		Connection con = DbConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			Date date = new Date();
			new Timestamp(date.getTime());

			stmt.executeUpdate("UPDATE baugruppe SET name = '" + bg.getName()
					+ "', " + "beschreibung = '" + bg.getBeschreibung() + "', "
					+ "aenderungsDatum = '" + new Timestamp(date.getTime())
					+ "', " + "letzterBearbeiter = '"
					+ bg.getLetzterBearbeiter() + "' WHERE id=" + bg.getId());

			result.add(bg);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		for (int i = 0; i < bg.connectedBauteile.size(); i++) {

			int id = pruefeObBtBgVorhanden(bg.connectedBauteile.get(i).getId(),
					bg.getId());

			if (id == 0) {
				insertZwischentabelleBauteilBaugruppe(
						bg.connectedBauteile.get(i).getId(),
						bg.connectedBauteile.get(i).getAnzahl(), bg.getId());
			} else {
				updateBauteilBaugruppe(id, bg.connectedBauteile.get(i)
						.getAnzahl());
			}
		}

		for (int i = 0; i < bg.connectedBaugruppen.size(); i++) {

			int id = pruefeObBgBgVorhanden(bg.getId(), bg.connectedBaugruppen
					.get(i).getId());

			if (id == 0) {
				insertZwischentabelleBaugruppeBaugruppe(bg.getId(),
						bg.connectedBaugruppen.get(i).getId(),
						bg.connectedBaugruppen.get(i).getAnzahl());
			} else {
				updateBaugruppeBaugruppe(id, bg.connectedBaugruppen.get(i)
						.getAnzahl());
			}
		}

		// Um Analogie zu insert(Baugruppe bg) zu wahren, geben wir bg zurueck
		return result;
	}

	public int pruefeObBtBgVorhanden(int bauteilID, int baugruppeID) {

		int id = 0;

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id "
					+ "FROM bauteilBaugruppe WHERE bauteil = '" + bauteilID
					+ "' AND baugruppe = '" + baugruppeID + "'");

			if (rs.next() == false) {

				id = 0;
			} else {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public int pruefeObBgBgVorhanden(int uebergeordneteBaugruppeID,
			int untergeordneteBaugruppeID) {

		int id = 0;

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */
			ResultSet rs = stmt
					.executeQuery("SELECT id "
							+ "FROM baugruppeBaugruppe WHERE uebergeordneteBaugruppe = '"
							+ uebergeordneteBaugruppeID
							+ "' AND untergeordneteBaugruppe = '"
							+ untergeordneteBaugruppeID + "'");

			if (rs.next() == false) {

				id = 0;
			} else {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public void insertZwischentabelleBauteilBaugruppe(int bauteilID,
			int anzahl, int baugruppeID) {

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM bauteilBaugruppe ");

			if (rs.next()) {
				/*
				 * bt erhaelt den bisher maximalen, nun um 1 inkrementierten
				 * Primaerschluessel.
				 */

				int bgbt = rs.getInt("maxid") + 1;

				// Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
				stmt.executeUpdate("INSERT INTO bauteilBaugruppe (id, bauteil, anzahl, baugruppe) "
						+ "VALUES ("
						+ bgbt
						+ ",'"
						+ bauteilID
						+ "','"
						+ anzahl
						+ "','" + baugruppeID + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertZwischentabelleBaugruppeBaugruppe(
			int uebergeordneteBaugruppeID, int untergeordneteBaugruppeID,
			int anzahl) {

		Connection con = DbConnection.connection();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunaechst schauen wir nach, welches der momentan hoechste
			 * Primaerschluesselwert ist.
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM baugruppeBaugruppe ");

			if (rs.next()) {

				/*
				 * bt erhaelt den bisher maximalen, nun um 1 inkrementierten
				 * Primaerschluessel.
				 */

				int bgbg = rs.getInt("maxid") + 1;

				// Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
				stmt.executeUpdate("INSERT INTO baugruppeBaugruppe (id, uebergeordneteBaugruppe, untergeordneteBaugruppe, anzahl) "
						+ "VALUES ("
						+ bgbg
						+ ",'"
						+ uebergeordneteBaugruppeID
						+ "','"
						+ untergeordneteBaugruppeID
						+ "','"
						+ anzahl
						+ "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String deleteBaugruppe(int id) {

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

	public Vector<TeileListe> findConnectedBauteile(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<TeileListe> list = new Vector<TeileListe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, bauteil, anzahl FROM bauteilBaugruppe "
							+ "WHERE baugruppe=" + id);

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				TeileListe tl = new TeileListe();
				tl.setId(rs.getInt("bauteil"));
				tl.setAnzahl(rs.getInt("anzahl"));

				list.add(tl);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return list;
	}

	public Vector<TeileListe> findConnectedBaugruppen(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<TeileListe> list = new Vector<TeileListe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfuellen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, anzahl, uebergeordneteBaugruppe, untergeordneteBaugruppe FROM baugruppeBaugruppe "
							+ "WHERE uebergeordneteBaugruppe=" + id);
			/*
			 * Da id Primarschluessel ist, kann max. nur ein Tupel
			 * zurueckgegeben werden. Pruefe, ob ein Ergebnis vorliegt.
			 */

			while (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				TeileListe tl = new TeileListe();
				tl.setId(rs.getInt("untergeordneteBaugruppe"));
				tl.setAnzahl(rs.getInt("anzahl"));

				list.add(tl);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return list;
	}
}