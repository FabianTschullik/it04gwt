package de.hdm.it04.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.it04.shared.bo.Bauteil;

/**
 * Mapper-Klasse, die <code>Bauteil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, St�cklisteMapper, EnderzeugnisMapper, BenutzerMapper}
 *
 * @author Schneider, M�hler, Thies
 */
public class BauteilMapper {

  /**
   * Die Klasse BauteilMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see bauteilMapper()
   */
  private static BauteilMapper bauteilMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected BauteilMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>BauteilMapper.bauteilMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>BauteilMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> BauteilMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>BauteilMapper</code>-Objekt.
   * @see BauteilMapper
   */
  public static BauteilMapper bauteilMapper() {
    if (bauteilMapper == null) {
      bauteilMapper = new BauteilMapper();
    }

    return bauteilMapper;
     }
  
  /**
   * Suchen eines Bauteils mit vorgegebener id. Da diese eindeutig ist,
   * wird genau ein Objekt zurückgegeben.
   *
   * @param id Primarschlüsselattribut (->DB)
   * @return Bauteil-Objekt, das dem übergebenen Schlüssel entspricht, null bei
   * nicht vorhandenem DB-Tupel.
   */
   public Bauteil findByKey(int id) {
 	  
   // DB-Verbindung holen
   Connection con = DbConnection.connection();
   
   try {
 	  
   // Leeres SQL-Statement (JDBC) anlegen
 	  
   Statement stmt = con.createStatement();
   
   // Statement ausfüllen und als Query an die DB schicken
   
   
   ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, änderungsDatum, letzterBearbeiter, materialBezeichnung, teilNummer FROM bauteil "
   + "WHERE id=" + id);
   /*
   * Da id Primarschlüssel ist, kann max. nur ein Tupel zurückgegeben
   * werden. Prüfe, ob ein Ergebnis vorliegt.
   */
   
   if (rs.next()) {
 	  
   // Ergebnis-Tupel in Objekt umwandeln
 	  
   Bauteil bt = new Bauteil();
   bt.setId(rs.getInt("id"));
   bt.setName(rs.getString("name"));
   bt.setBeschreibung(rs.getString("beschreibung"));
   bt.setErstellungsDatum(rs.getDate("erstellungsDatum"));
   bt.setÄnderungsDatum(rs.getDate("änderungsDatum"));
   // Letzter Benutzer muss noch eingefügt werden!!!
   bt.setMaterialbzeichnung(rs.getString("materialbezeichnung"));
   bt.setTeilNummer(rs.getInt("teilnummer"));
   
   
   return bt;
   }
   
   }
   catch (SQLException e2) {
   e2.printStackTrace();
   return null;
   }
   return null;
   }
   
   /**
   * Auslesen aller Benutzer.
   *
   * @return Ein Vektor mit Benutzer-Objekten, die samtliche Benutzer
   * repräsentieren. Bei evtl. Exceptions wird ein partiell gefällter
   * oder ggf. auch leerer Vetor zurückgeliefert.
   */
   public Vector<Benutzer> findAll() {
 	  
   Connection con = DbConnection.connection();
   
   // Ergebnisvektor vorbereiten
   
   Vector<Benutzer> result = new Vector<Benutzer>();
   
   try {
 	  
   Statement stmt = con.createStatement();
   
   ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, aktivität, mail, erstellungsDatum FROM benutzer "
   + " ORDER BY id");
   
   // Für jeden Eintrag im Suchergebnis wird nun ein Benutzer-Objekt erstellt.
   
   while (rs.next()) {
 	  
   Benutzer be = new Benutzer();
   be.setId(rs.getInt("id"));
   be.setNachname(rs.getString("nachname"));
   be.setVorname(rs.getString("vorname"));
   be.setAktiv(rs.getBoolean("aktivität"));
   be.setMail(rs.getString("mail"));
   be.setErstellungsDatum(rs.getDate("erstellungsDatum"));
   
   // Hinzufügen des neuen Objekts zum Ergebnisvektor
   
   result.addElement(be);
   }
   }
   catch (SQLException e2) {
   e2.printStackTrace();
   }
   // Ergebnisvektor zurückgeben
   return result;
   }
   
   /**
   * Einfügen eines <code>Benutzer</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   *
   * @param be das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   * <code>id</code>.
   */

   public Benutzer insert(Benutzer be) {
     Connection con = DbConnection.connection();

     try {
       Statement stmt = con.createStatement();

       /*
        * Zunächst schauen wir nach, welches der momentan höchste
        * Primärschlüsselwert ist.
        */
       ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
           + "FROM benutzer ");

       // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
       if (rs.next()) {
         /*
          * be erhält den bisher maximalen, nun um 1 inkrementierten
          * Primärschlüssel.
          */
         be.setId(rs.getInt("maxid") + 1);

         stmt = con.createStatement();

         // Jetzt erst erfolgt die tatsächliche Einfügeoperation
         stmt.executeUpdate("INSERT INTO benutzer (id, vorname, nachname, aktivität, mail, erstellungsDatum) "
             + "VALUES ("
         	+ be.getId()
         	+ ",'" 
         	+ be.getVorname()
         	+ "','"
         	+ be.getNachname()
         	+ "','"
         	+ be.isAktiv()
         	+ "','"
         	+ be.getMail()
         	+ "','"
         	+ be.getErstellungsDatum()
         	+ "')");
       }
     }
     catch (SQLException e) {
       e.printStackTrace();
     }

     /*
      * Rückgabe, des evtl. korrigierten Benutzers.
      * 
      * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
      * Objekte übergeben werden, wäre die Anpassung des Benutzer-Objekts auch
      * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
      * explizite Rückgabe von be ist eher ein Stilmittel, um zu signalisieren,
      * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
      */
     return be; }
   
   /**
    * Wiederholtes Schreiben eines Objekts in die Datenbank.
    * 
    * @param be das Objekt, das in die DB geschrieben werden soll
    * @return das als Parameter übergebene Objekt
    */
   
   public Benutzer update(Benutzer be) {
 	  
     Connection con = DbConnection.connection();

     try {
     	
       Statement stmt = con.createStatement();

       stmt.executeUpdate("UPDATE benutzer " 
     	+ "SET vorname= '" + be.getVorname() + "', " 
     	+ "nachname = '" + be.getNachname() + "', "
     	+ "aktivität = '" + be.isAktiv() + "', "
     	+ "mail = '" + be.getMail() + "', "
     	+ "erstellungsDatum = '" + be.getErstellungsDatum() + "' "
         + "WHERE id=" + be.getId());

     }
     catch (SQLException e) {
       e.printStackTrace();
     }

     // Um Analogie zu insert(Benutzer be) zu wahren, geben wir be zurück
     return be;
   }

   /**
    * Löschen der Daten eines <code>Benutzer</code>-Objekts aus der Datenbank.
    * 
    * @param be das aus der DB zu löschende "Objekt"
    */
   public void delete(Benutzer be) {
 	  
     Connection con = DbConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM benutzer " + "WHERE id=" + be.getId());
     }
     catch (SQLException e) {
       e.printStackTrace();
     }
   }
 }



  
  
  
}
