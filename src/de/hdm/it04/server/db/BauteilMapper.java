package de.hdm.it04.server.db;

import java.sql.*;

/**
 * Mapper-Klasse, die <code>Bauteil</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, StücklisteMapper, EnderzeugnisMapper, BenutzerMapper}
 *
 * @author Schneider, Mähler, Thies
 */
public class BauteilMapper {

  /**
   * Die Klasse BauteilMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see bauteilMapper()
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
   * @return Das <code>BauteilMapper</code>-Objekt.
   * @see BauteilMapper
   */
  public static BauteilMapper bauteilMapper() {
    if (bauteilMapper == null) {
      bauteilMapper = new BauteilMapper();
    }

    return bauteilMapper;
  }
}
