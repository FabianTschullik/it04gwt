package de.hdm.it04.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.it04.shared.bo.Enderzeugnis;

/**
 * Mapper-Klasse, die <code>Enderzeugnis</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, St�cklisteMapper, BenutzerMapper, BauteilMapper}
 *
 * @author Schneider, M�hler, Thies
 */
public class EnderzeugnisMapper {

  /**
   * Die Klasse EnderzeugnisMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see enderzeugnisMapper()
   */
  private static EnderzeugnisMapper enderzeugnisMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected EnderzeugnisMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>EnderzeugnisMapper.enderzeugnisMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>EnderzeugnisMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> EnderzeugnisMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>EnderzeugnisMapper</code>-Objekt.
   * @see EnderzeugnisMapper
   */
  public static EnderzeugnisMapper enderzeugnisMapper() {
    if (enderzeugnisMapper == null) {
      enderzeugnisMapper = new EnderzeugnisMapper();
    }

    return enderzeugnisMapper;
  }
  
}
	  
	  

