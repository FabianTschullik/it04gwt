package de.hdm.it04.server.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Verwalten einer Verbindung zur Datenbank.
 * <p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.
 * <p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.
 * <p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank
 * aus. Eine flexiblere Variante fuer mehrere gleichzeitige
 * Datenbank-Verbindungen waere sicherlich leistungsfaehiger. Dies wuerde
 * allerdings den Rahmen dieses Projekts sprengen bzw. die Software unnoetig
 * verkomplizieren, da dies fuer diesen Anwendungsfall nicht erforderlich ist.
 * 
 * @author Maehler
 */
public class DbConnection {

	/**
	 * Die Klasse DbConnection wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * fuer saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse. Diese Methode bietet einen
	 * globen Zugriff auf das Objekt ueber die Instanzoperation.
	 * 
	 * @see BauteilMapper.bauteilMapper()
	 */
	private static Connection con = null;

	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
	 * professionellen Applikation wuerde diese Zeichenkette aus einer
	 * Konfigurationsdatei eingelesen oder ueber einen Parameter von außen
	 * mitgegeben, um bei einer Veraenderung dieser URL nicht die gesamte
	 * Software neu komilieren zu muessen.
	 */
	// private static String googleUrl =
	// "jdbc:google:mysql://it04sms:db:3306/sms?user=root&password=root";
	// "jdbc:mysql://127.0.0.1:3306/sms?user=root&password=root"

	private static String localUrl = "jdbc:google:mysql://it04sms:db:3306/sms?user=root&password=root";

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>DbConnection.connection()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine
	 * einzige Instanz von <code>DbConnection</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> DbConnection sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * <p>
	 * 
	 * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
	 * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
	 * ausgeloest werden - wird keine neue Verbindung aufgebaut, so dass die in
	 * einem solchen Fall die gesamte Software neu zu starten ist. In einer
	 * robusten Loesung wuerde man hier die Klasse dahingehend modifizieren,
	 * dass bei einer nicht mehr funktionsfaehigen Verbindung stets versucht
	 * wuerde, eine neue Verbindung aufzubauen. Dies wuerde allerdings ebenfalls
	 * den Rahmen dieses Projekts sprengen.
	 * 
	 * @return DAS <code>DbConncetion</code>-Objekt.
	 * @see con
	 */
	public static Connection connection() {

		if (con == null) {
			String url = null;
			try {

				Class.forName("com.mysql.jdbc.GoogleDriver"); // fuer local use:
														// "com.mysql.jdbc.Driver"
														// or
														// "com.mysql.jdbc.GoogleDriver"
														// for cloud sql
				url = localUrl;

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}
		return con;
	}
}