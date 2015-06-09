package de.hdm.it04.server.db;

import java.sql.*;

import de.hdm.it04.shared.Bauteil;







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
	   
	   // Statement ausfüllen und als Query an die DB schicken
	   
	   
	   ResultSet rs = stmt.executeQuery("SELECT id, name FROM bauteil "
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
	      * Rückgabe, des evtl. korrigierten Bauteils.
	      * 
	      * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
	      * Objekte übergeben werden, wäre die Anpassung des Bauteil-Objekts auch
	      * ohne diese explizite Rückgabe außerhalb dieser Methode sichtbar. Die
	      * explizite Rückgabe von be ist eher ein Stilmittel, um zu signalisieren,
	      * dass sich das Objekt evtl. im Laufe der Methode verändert hat.
	      */
	     return bt; }
   
   
   
   
   }



