package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;

/**
 * Mapper-Klasse, die <code>Baugruppe</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class EnderzeugnisMapper {

	/**
	 * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht
	 * hierbei von einem sogenannten <b>Singleton</b>. Hierbei kann global auf
	 * das Objekt über die Instanzoperation zugegriffen werden.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 */
	private static EnderzeugnisMapper enderzeugnisMapper = null;

	/**
	 * Geschützter Konstruktor - verhindert die Möglichkeit, mit
	 * <code>new</code> neue Instanzen dieser Klasse zu erzeugen.
	 */
	protected EnderzeugnisMapper() {
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
	public static EnderzeugnisMapper enderzeugnisMapper() {
		if (enderzeugnisMapper == null)
			enderzeugnisMapper = new EnderzeugnisMapper();

		return enderzeugnisMapper;
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
	public Baugruppe findConnectedBaugruppe(int id) {

		// DB-Verbindung holen
		Connection con = DbConnection.connection();


		try {

			// Leeres SQL-Statement (JDBC) anlegen

			Statement stmt = con.createStatement();

			// Statement ausf�llen und als Query an die DB schicken

			ResultSet rs = stmt
					.executeQuery("SELECT baugruppe.id, baugruppe.name, baugruppe.beschreibung, baugruppe.erstellungsDatum, baugruppe.aenderungsDatum " + 
							"FROM enderzeugnis, baugruppe " + 
							"WHERE enderzeugnis.baugruppe = baugruppe.id " +
							"AND enderzeugnis.id = " + id);
							
							
			/*
			 * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
			 * werden. Pr�fe, ob ein Ergebnis vorliegt.
			 */

			if (rs.next()) {

				// Ergebnis-Tupel in Objekt umwandeln

				Baugruppe bg = new Baugruppe();
				bg.setId(rs.getInt("id"));
				bg.setName(rs.getString("name"));
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
}


