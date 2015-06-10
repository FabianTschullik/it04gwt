package de.hdm.it04.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.VectorSerializable;








public class BauteilMapper {

  
  private static BauteilMapper bauteilMapper = null;

  
  protected BauteilMapper() {
  }

  
  public static BauteilMapper bauteilMapper() {
    if (bauteilMapper == null) 
      bauteilMapper = new BauteilMapper();
    

    return bauteilMapper;
     }
  
 
   public Bauteil findByKey(int id) {
 	  
	   // DB-Verbindung holen
	   Connection con = DbConnection.connection();
	   
	   try {
	 	  
	   // Leeres SQL-Statement (JDBC) anlegen
	 	  
	   Statement stmt = con.createStatement();
	   
	   // Statement ausf�llen und als Query an die DB schicken
	   
	   
	   ResultSet rs = stmt.executeQuery("SELECT id, name FROM bauteil "
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
	   
	   return bt;
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

	         // Jetzt erst erfolgt die tats�chliche Einf�geoperation
	         stmt.executeUpdate("INSERT INTO bauteil (id, name) "
	             + "VALUES ("
	         	+ bt.getId()
	         	+ ",'" 
	         	+ bt.getName()
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

	      ResultSet rs = stmt.executeQuery("SELECT id, name FROM bauteil "
	          + " ORDER BY id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Bauteil-Objekt erstellt.
	      while (rs.next()) {
	        Bauteil bt = new Bauteil();
	        bt.setId(rs.getInt("id"));
	        bt.setName(rs.getString("name"));

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
	  
	  

   
   
   
   
   }



