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
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 */
public class BauteilMapper {
	
	/**
	   * Die Klasse BauteilMapper wird nur einmal instantiiert. Man spricht hierbei
	   * von einem sogenannten <b>Singleton</b>.
	   * Hierbei kann global auf das Objekt über die Instanzoperation zugegriffen
	   * werden.
	   * <p>
	   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
	   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
	   * einzige Instanz dieser Klasse.

	   */
  private static BauteilMapper bauteilMapper = null;
  
  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected BauteilMapper() {
  }
  
  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>BauteilMapper.bauteilMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>BauteilMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> BauteilMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return DAS <code>BauteilMapper</code>-Objekt.
   * @see bauteilMapper
   */
  public static BauteilMapper bauteilMapper() {
    if (bauteilMapper == null) 
      bauteilMapper = new BauteilMapper();
    

    return bauteilMapper;
     }
  
 
   public Vector<Bauteil> findByKey(int id) {
 	  
	   // DB-Verbindung holen
	   Connection con = DbConnection.connection();
	   
	   // Ergebnisvektor vorbereiten
	    Vector<Bauteil> result = new Vector<Bauteil>();
	   
	   try {
	 	  
	   // Leeres SQL-Statement (JDBC) anlegen
	 	  
	   Statement stmt = con.createStatement();
	   
	   // Statement ausf�llen und als Query an die DB schicken
	   
	   
	   ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM bauteil "
	   + "WHERE id=" + id);
	   /*
	   * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
	   * werden. Pr�fe, ob ein Ergebnis vorliegt.
	   */
	   
	   if (rs.next()) {
	 	  
	   // Ergebnis-Tupel in Objekt umwandeln
	 	  
	   Bauteil bt = new Bauteil();
	   bt.setId(rs.getInt("id"));
	   bt.setName(rs.getString("name"));
	   bt.setBeschreibung(rs.getString("beschreibung"));
       bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
       bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
       bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));
       
       result.add(bt);
	   
	   return result;
	   }
	   
	   }
	   catch (SQLException e2) {
	   e2.printStackTrace();
	   return null;
	   }
	   return null;
	   }
   
   //Bauteil anlegen
   
   public Bauteil insert(Bauteil bt) {
	     Connection con = DbConnection.connection();

	     try {
	       Statement stmt = con.createStatement();

	       /*
	        * Zun�chst schauen wir nach, welches der momentan h�chste
	        * Prim�rschl�sselwert ist.
	        */
	       ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	           + "FROM bauteil ");

	       // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
	       if (rs.next()) {
	         /*
	          * bt erh�lt den bisher maximalen, nun um 1 inkrementierten
	          * Prim�rschl�ssel.
	          */
	         bt.setId(rs.getInt("maxid") + 1);

	         stmt = con.createStatement();
	         
	        
	         
	    
	        
	        
	         //Aktuelle Zeit f�r Timestamp erstellungsDatum, aenderungsDatum holen
	       
	        Date date = new Date();     
	        new Timestamp(date.getTime());
	         
	       
	         // Jetzt erst erfolgt die tats�chliche Einf�geoperation
	         stmt.executeUpdate("INSERT INTO bauteil (id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum) "
	             + "VALUES ("
	         	+ bt.getId()
	         	+ ",'" 
	         	+ bt.getName()
	         	+ "','" 
	         	+ bt.getBeschreibung()
	         	+ "','" 
	         	+ bt.getMaterialBezeichnung() 
	         	+ "','" 
	         	+new Timestamp(date.getTime())
	         	+ "','" 
	         	+new Timestamp(date.getTime())
	         	+ "')");
	       }
	     }
	     catch (SQLException e) {
	       e.printStackTrace();
	     }

	     /*
	      * R�ckgabe, des evtl. korrigierten Bauteils.
	      * 
	      * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	      * Objekte �bergeben werden, w�re die Anpassung des Bauteil-Objekts auch
	      * ohne diese explizite R�ckgabe au�erhalb dieser Methode sichtbar. Die
	      * explizite R�ckgabe von be ist eher ein Stilmittel, um zu signalisieren,
	      * dass sich das Objekt evtl. im Laufe der Methode ver�ndert hat.
	      */
	     return bt; }
   
   
   
   public Vector<Bauteil> findAll() {
	    Connection con = DbConnection.connection();

	    // Ergebnisvektor vorbereiten
	    Vector<Bauteil> result = new Vector<Bauteil>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM bauteil "
	          + " ORDER BY id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Bauteil-Objekt erstellt.
	      while (rs.next()) {
	        Bauteil bt = new Bauteil();
	        bt.setId(rs.getInt("id"));
	        bt.setName(rs.getString("name"));
	        bt.setBeschreibung(rs.getString("beschreibung"));
	        bt.setMaterialBezeichnung(rs.getString("materialBezeichnung"));
	        bt.setErstellungsDatum(rs.getTimestamp("erstellungsDatum"));
	        bt.setAenderungsDatum(rs.getTimestamp("aenderungsDatum"));

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
	  
   public Vector<Bauteil> findByName(String name) {
	 	  
	   // DB-Verbindung holen
	   Connection con = DbConnection.connection();
	   
	   Vector<Bauteil> result = new Vector<Bauteil>();
	   
	   try {
	 	  
	   // Leeres SQL-Statement (JDBC) anlegen
	 	  
	   Statement stmt = con.createStatement();
	   
	   // Statement ausf�llen und als Query an die DB schicken
	   
	   
	   ResultSet rs = stmt.executeQuery("SELECT id, name, beschreibung, materialBezeichnung, erstellungsDatum, aenderungsDatum FROM bauteil "
	   + "WHERE name=" + "'" + name + "'");
	   /*
	   * Da id Primarschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
	   * werden. Pr�fe, ob ein Ergebnis vorliegt.
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
       
       result.add(bt);
	   }
	   
	   }
	   catch (SQLException e2) {
	   e2.printStackTrace();
	   }
	   return result;
	   }
   
   public Bauteil update(Bauteil bt) {
	   
	    Connection con = DbConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE bauteil " 
	      + "SET name= '" + bt.getName() + "', "
	      + "beschreibung = '" + bt.getBeschreibung() + "', "
	      + "materialBezeichnung" + bt.getMaterialBezeichnung() + "', "
	      + "erstellungsDatum" + bt.getErstellungsDatum() + "', "
	      + "aenderungsDatum" + bt.getAenderungsDatum() + "', "
	          + "WHERE id=" + bt.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Um Analogie zu insert(Bauteil bt) zu wahren, geben wir bt zurück
	    return bt;
	  }
   
   
   
   public String delete(int id) {
	   
	   String ergebnis = "Bauteil wurde erfolgreich geloescht!";
	   
	    Connection con = DbConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM bauteil "
	    		  + "WHERE id = " + id);

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	    
	    return ergebnis;
	  }


	  

   
   
   
   
   }



