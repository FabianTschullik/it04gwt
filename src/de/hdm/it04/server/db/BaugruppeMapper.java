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
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur VerfÃ¼gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelÃ¶scht werden kÃ¶nnen. Das Mapping ist bidirektional. D.h., Objekte koennen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * @see BauteilMapper, EnderzeugnisMapper, BenutzerMapper
 * @author Geier, Maehler, Voelker, Tschullik, Thies
 */
public class BaugruppeMapper {

	/**
	 * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt Ã¼ber die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fÃ¼r sÃ¤mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static BaugruppeMapper baugruppeMapper = null;

	/**
	 * GeschÃ¼tzter Konstruktor - verhindert die MÃ¶glichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected BaugruppeMapper() {
	}

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>BaugruppeMapper.baugruppeMapper()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafÃ¼r sorgt, dass nur eine
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
	 * genau ein Vektor-Objekt zurÃ¼ckgegeben.
	 * 
	 * Warum Vektor? Da im spÃ¤teren Verlauf die Methode findByKey und findByName
	 * zusammen gefÃ¼hrt werden. So ist es mÃ¶glich Ã¼ber das Suchfeld per Name und
	 * id zusuchen. Der Vektor ist notwendig, da der Name nicht als primÃ¤r
	 * SchlÃ¼ssel gekennzeichnet ist. Daher kÃ¶nnen auch mehrere Ergebnise zurÃ¼ck
	 * gegeben werden. Der Vektor ist fÃ¼r die findByKey Methode im prinzip nicht
	 * notwendig.
	 * 
	 * @param id
	 *            PrimÃ¤rschlÃ¼sselattribut (->DB)
	 * @return Konto-Objekt-Vektor, das dem Ã¼bergebenen SchlÃ¼ssel entspricht,
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

			// Statement ausfï¿½llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM baugruppe "
							+ "WHERE id=" + id);
			/*
			 * Da id Primarschlï¿½ssel ist, kann max. nur ein Tupel zurï¿½ckgegeben
			 * werden. Prï¿½fe, ob ein Ergebnis vorliegt.
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
	
	
	
	
	
	
/*
	public Vector<Bauteil> findConnectedBauteileByKey(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Bauteil> result = new Vector<Bauteil>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfï¿½llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT bauteil.id, bauteil.name, bauteil.materialBezeichnung,"
							+ " bauteil.beschreibung, bauteil.materialBezeichnung, bauteil.erstellungsDatum, bauteil.aenderungsDatum " +
									"FROM bauteil, baugruppe, bauteilBaugruppe " +
									"WHERE bauteil.id = bauteilBaugruppe.bauteil " +
									"AND baugruppe.id = bauteilBaugruppe.baugruppe " +
									"AND baugruppe.id=" + id);
							
							
			

			if (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Bauteil bt = new Bauteil();
				bt.setId(rs.getInt("id"));
				bt.setName(rs.getString("name"));
				bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
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

*/
	/**
	 * EinfÃ¼gen eines <code>Baugruppen</code>-Objekts in die Datenbank. Dabei wird
	 * auch der PrimÃ¤rschlÃ¼ssel des Ã¼bergebenen Objekts geprÃ¼ft und ggf.
	 * berichtigt.
	 * 
	 * @param bt
	 *            das zu speichernde Objekt
	 * @return das bereits Ã¼bergebene Objekt, jedoch mit ggf. korrigierter
	 *         <code>id</code>.
	 */
	public Baugruppe insert() {
		Connection con = DbConnection.connection();
		
		Baugruppe bg = new Baugruppe();

		try {
			Statement stmt = con.createStatement();

			/*
			 * Zunï¿½chst schauen wir nach, welches der momentan hï¿½chste
			 * Primï¿½rschlï¿½sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM baugruppe ");

			// Wenn wir etwas zurï¿½ckerhalten, kann dies nur einzeilig sein
			if (rs.next()) {
				/*
				 * bt erhï¿½lt den bisher maximalen, nun um 1 inkrementierten
				 * Primï¿½rschlï¿½ssel.
				 */
				bg.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Aktuelle Zeit fï¿½r Timestamp erstellungsDatum, aenderungsDatum
				// holen

				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				
				bg.setAenderungsDatum(timestamp);
				bg.setErstellungsDatum(timestamp);

				// Jetzt erst erfolgt die tatsï¿½chliche Einfï¿½geoperation
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
		 * Rï¿½ckgabe, der evtl. korrigierten Baugruppe.
		 * 
		 * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
		 * Objekte ï¿½bergeben werden, wï¿½re die Anpassung des Bauteil-Objekts auch
		 * ohne diese explizite Rï¿½ckgabe auï¿½erhalb dieser Methode sichtbar. Die
		 * explizite Rï¿½ckgabe von be ist eher ein Stilmittel, um zu
		 * signalisieren, dass sich das Objekt evtl. im Laufe der Methode
		 * verï¿½ndert hat.
		 */
		return bg;
	}

	/**
	 * Auslesen aller Bauteile.
	 * 
	 * @return Ein Vektor mit Bauteil-Objekten, die sÃ¤mtliche Bauteile
	 *         reprÃ¤sentieren. Bei evtl. Exceptions wird ein partiell gefÃ¼llter
	 *         oder ggf. auch leerer Vetor zurÃ¼ckgeliefert.
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

			// FÃ¼r jeden Eintrag im Suchergebnis wird nun ein Baugruppen-Objekt
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

				// HinzufÃ¼gen des neuen Objekts zum Ergebnisvektor
				result.addElement(bg);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnisvektor zurÃ¼ckgeben
		return result;
	}

	
	public Vector<Baugruppe> findByName(String name) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();

		Vector<Baugruppe> result = new Vector<Baugruppe>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausfï¿½llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, aenderungsDatum, letzterBearbeiter FROM baugruppe "
							+ "WHERE name=" + "'" + name + "'");
			/*
			 * Da id Primarschlï¿½ssel ist, kann max. nur ein Tupel zurï¿½ckgegeben
			 * werden. Prï¿½fe, ob ein Ergebnis vorliegt.
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

	public void updateBauteilBaugruppe(int id, int anzahl){
		
		Connection con = DbConnection.connection();

		try {			
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			Date date = new Date();
			new Timestamp(date.getTime());

			
			stmt.executeUpdate("UPDATE bauteilBaugruppe SET anzahl = '" + anzahl + "' " 
					+ " WHERE id=" + id);
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	
	
	
public void updateBaugruppeBaugruppe(int id, int anzahl){
		
		Connection con = DbConnection.connection();

		try {			
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			

			stmt.executeUpdate("UPDATE baugruppeBaugruppe SET anzahl = '" + anzahl + "' " 
					+ " WHERE id=" + id);
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Wiederholtes Schreiben eines Objekts in die Datenbank.
	 * 
	 * @param bg
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter Ã¼bergebene Objekt
	 */
	public Vector<Baugruppe> updateBaugruppe(Baugruppe bg) {

		Vector<Baugruppe> result = new Vector<Baugruppe>();
		Connection con = DbConnection.connection();

		try {			
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			
			Date date = new Date();
			new Timestamp(date.getTime());

			
			stmt.executeUpdate("UPDATE baugruppe SET name = '" + bg.getName()+ "', " 
					+ "beschreibung = '" + bg.getBeschreibung() + "', "
					+ "aenderungsDatum = '" + new Timestamp(date.getTime()) + "', "
					+ "letzterBearbeiter = '" + bg.getLetzterBearbeiter()
					+ "' WHERE id=" + bg.getId());
			
			result.add(bg);
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		
		
		for(int i=0; i<bg.connectedBauteile.size(); i++){
			
			int id = prüfeObBtBgVorhanden(bg.connectedBauteile.get(i).getId(), bg.getId());
			
			if(id == 0){
				insertZwischentabelleBauteilBaugruppe (bg.connectedBauteile.get(i).getId(), bg.connectedBauteile.get(i).getAnzahl(), bg.getId());
			}
			else{
				updateBauteilBaugruppe(id, bg.connectedBauteile.get(i).getAnzahl());
			}
			
			
		}
		
		for(int i=0; i<bg.connectedBaugruppen.size(); i++){
			
			int id = prüfeObBgBgVorhanden(bg.getId(), bg.connectedBaugruppen.get(i).getId());
			

			if(id == 0){
				insertZwischentabelleBaugruppeBaugruppe (bg.getId(), bg.connectedBaugruppen.get(i).getId(), bg.connectedBaugruppen.get(i).getAnzahl());
			}
			else{
				updateBaugruppeBaugruppe(id, bg.connectedBaugruppen.get(i).getAnzahl());
			}
			
			
		}
		
		// Um Analogie zu insert(Baugruppe bg) zu wahren, geben wir bg zurÃ¼ck
		return result;
	}

	
	
	public int prüfeObBtBgVorhanden(int bauteilID, int baugruppeID){
		
		 int id = 0;
		
		Connection con = DbConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
					
			/*
			 * Zunï¿½chst schauen wir nach, welches der momentan hï¿½chste
			 * Primï¿½rschlï¿½sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id "
					+ "FROM bauteilBaugruppe WHERE bauteil = '" + bauteilID
					+ "' AND baugruppe = '" + baugruppeID + "'");
			
			
			if (rs.next()==false) {
			
				id=0;
			}
			else{
				id = rs.getInt("id");
			}
			
		

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	
	
public int prüfeObBgBgVorhanden(int uebergeordneteBaugruppeID,int untergeordneteBaugruppeID){
		
		int id = 0;
		
		Connection con = DbConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
					
			/*
			 * Zunï¿½chst schauen wir nach, welches der momentan hï¿½chste
			 * Primï¿½rschlï¿½sselwert ist.
			 */
			ResultSet rs = stmt.executeQuery("SELECT id "
					+ "FROM baugruppeBaugruppe WHERE uebergeordneteBaugruppe = '" + uebergeordneteBaugruppeID
					+ "' AND untergeordneteBaugruppe = '" + untergeordneteBaugruppeID + "'");
			
			
			
			if (rs.next()==false) {
				
				id=0;
			}
			else{
				id = rs.getInt("id");
			}
			
		

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	
public void insertZwischentabelleBauteilBaugruppe(int bauteilID, int anzahl, int baugruppeID){
		
		Connection con = DbConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
					
			/*
			 * Zunï¿½chst schauen wir nach, welches der momentan hï¿½chste
			 * Primï¿½rschlï¿½sselwert ist.
			 */

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM bauteilBaugruppe ");

			
			
			if (rs.next()) {		
				/*
				 * bt erhï¿½lt den bisher maximalen, nun um 1 inkrementierten
				 * Primï¿½rschlï¿½ssel.
				 */

				int bgbt = rs.getInt("maxid") + 1;

				
				//stmt = con.createStatement();


				// Jetzt erst erfolgt die tatsï¿½chliche Einfï¿½geoperation
				stmt.executeUpdate("INSERT INTO bauteilBaugruppe (id, bauteil, anzahl, baugruppe) "
						+ "VALUES ("
						+ bgbt
						+ ",'"
						+ bauteilID
						+ "','"
						+ anzahl
						+ "','"
						+ baugruppeID + "')");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}






public void insertZwischentabelleBaugruppeBaugruppe(int uebergeordneteBaugruppeID, int untergeordneteBaugruppeID, int anzahl){
	
	Connection con = DbConnection.connection();
	
	try {
		Statement stmt = con.createStatement();
				
		/*
		 * Zunï¿½chst schauen wir nach, welches der momentan hï¿½chste
		 * Primï¿½rschlï¿½sselwert ist.
		 */

		ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
				+ "FROM baugruppeBaugruppe ");

		
		        
		if (rs.next()) {
			  
			
			
			/*
			 * bt erhï¿½lt den bisher maximalen, nun um 1 inkrementierten
			 * Primï¿½rschlï¿½ssel.
			 */

			int bgbg = rs.getInt("maxid") + 1;

			
			//stmt = con.createStatement();


			// Jetzt erst erfolgt die tatsï¿½chliche Einfï¿½geoperation
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

		// Statement ausf�llen und als Query an die DB schicken

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

		// Statement ausf�llen und als Query an die DB schicken

		ResultSet rs = stmt
				.executeQuery("SELECT id, anzahl, uebergeordneteBaugruppe, untergeordneteBaugruppe FROM baugruppeBaugruppe "
						+ "WHERE uebergeordneteBaugruppe=" + id);
		/*
		 * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
		 * werden. Pr�fe, ob ein Ergebnis vorliegt.
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

