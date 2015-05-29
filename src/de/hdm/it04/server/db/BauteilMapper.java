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
   bt.set�nderungsDatum(rs.getDate("änderungsDatum"));
   
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
   * Auslesen aller Bauteile.
   *
   * @return Ein Vektor mit Bauteil-Objekten, die samtliche Bauteile
   * repräsentieren. Bei evtl. Exceptions wird ein partiell gefällter
   * oder ggf. auch leerer Vetor zurückgeliefert.
   */
   public Vector<Bauteil> findAll() {
 	  
   Connection con = DbConnection.connection();
   
   // Ergebnisvektor vorbereiten
   
   Vector<Bauteil> result = new Vector<Bauteil>();
   
   try {
 	  
   Statement stmt = con.createStatement();
   
   ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, erstellungsDatum, änderungsDatum, letzterBearbeiter, materialBezeichnung, teilNummer FROM bauteil "
   + " ORDER BY id");
   
   // Für jeden Eintrag im Suchergebnis wird nun ein Benutzer-Objekt erstellt.
   
   while (rs.next()) {
 	  
   Bauteil bt = new Bauteil();
   bt.setId(rs.getInt("id"));
   bt.setName(rs.getString("name"));
   bt.setBeschreibung(rs.getString("beschreibung"));
   bt.setErstellungsDatum(rs.getDate("erstellungsDatum"));
   bt.set�nderungsDatum(rs.getDate("änderungsDatum"));
   
   // Letzter Benutzer muss noch eingefügt werden!!!
   
   bt.setMaterialbzeichnung(rs.getString("materialbezeichnung"));
   bt.setTeilNummer(rs.getInt("teilnummer"));
   
   // Hinzufügen des neuen Objekts zum Ergebnisvektor
   
   result.addElement(bt);
   }
   }
   catch (SQLException e2) {
   e2.printStackTrace();
   }
   // Ergebnisvektor zurückgeben
   return result;
   }
   
   /**
   * Einfügen eines <code>Bauteil</code>-Objekts in die Datenbank. Dabei wird
   * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
   * berichtigt.
   *
   * @param be das zu speichernde Objekt
   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
   * <code>id</code>.
   */

   public Bauteil insert(Bauteil bt) {
     Connection con = DbConnection.connection();

     try {
       Statement stmt = con.createStatement();

       /*
        * Zunächst schauen wir nach, welches der momentan höchste
        * Primärschlüsselwert ist.
        */
       ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
           + "FROM bauteil ");

       // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
       if (rs.next()) {
         /*
          * bt erhält den bisher maximalen, nun um 1 inkrementierten
          * Primärschlüssel.
          */
         bt.setId(rs.getInt("maxid") + 1);

         stmt = con.createStatement();

         // Jetzt erst erfolgt die tatsächliche Einfügeoperation
         stmt.executeUpdate("INSERT INTO benutzer (id, name, beschreibung, erstellungsDatum, änderungsDatum, letzterBearbeiter, materialBezeichnung, teilNummer) "
             + "VALUES ("
         	+ bt.getId()
         	+ ",'" 
         	+ bt.getName()
         	+ "','"
         	+ bt.getBeschreibung()
         	+ "','"
         	+ bt.getErstellungsDatum()
         	+ "','"
         	+ bt.get�nderungsDatum()
         	// Hier muss noch der letzte Bearbeiter eingefügt werden.
         	+ "','"
         	+ bt.getMaterialbzeichnung()
         	+ "','"
         	+ bt.getTeilNummer()
         	+ "')");
       }
     }
     catch (SQLException e) {
       e.printStackTrace();
     }

     /*
      * Rückgabe, des evtl. korrigierten Bauteils.
      * 
      * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
      * Objekte übergeben werden, wäre die Anpassung des Bauteil-Objekts auch
      * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
      * explizite Rückgabe von be ist eher ein Stilmittel, um zu signalisieren,
      * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
      */
     return bt; }
   
   /**
    * Wiederholtes Schreiben eines Objekts in die Datenbank.
    * 
    * @param bt das Objekt, das in die DB geschrieben werden soll
    * @return das als Parameter übergebene Objekt
    */
   
   public Bauteil update(Bauteil bt) {
 	  
     Connection con = DbConnection.connection();

     try {
     	
       Statement stmt = con.createStatement();

       stmt.executeUpdate("UPDATE bauteil " 
     	+ "SET name= '" + bt.getName() + "', " 
     	+ "beschreibung = '" + bt.getBeschreibung() + "', "
     	+ "erstellungsDatum = '" + bt.getErstellungsDatum() + "', "
     	+ "änderungsDatum = '" + bt.get�nderungsDatum() + "', "
     	+ "materialBezeichnung = '" + bt.getMaterialbzeichnung() + "', "
     	+ "teilNummer = '" + bt.getTeilNummer() + "' "
        + "WHERE id=" + bt.getId());

     }
     catch (SQLException e) {
       e.printStackTrace();
     }

     // Um Analogie zu insert(Bauteil bt) zu wahren, geben wir be zurück
     return bt;
   }

   /**
    * Löschen der Daten eines <code>Bauteil</code>-Objekts aus der Datenbank.
    * 
    * @param bt das aus der DB zu löschende "Objekt"
    */
   public void delete(Bauteil bt) {
 	  
     Connection con = DbConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM bauteil " + "WHERE id=" + bt.getId());
     }
     catch (SQLException e) {
       e.printStackTrace();
     }
   }
 }



  
  
  

