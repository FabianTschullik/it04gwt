package de.hdm.it04.server.db;

import java.sql.*;

/**
 * Mapper-Klasse, die <code>Stückliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, EnderzeugnisMapper, BenutzerMapper, BauteilMapper}
 *
 * @author Schneider, Mähler, Thies
 */
public class StücklisteMapper {

  /**
   * Die Klasse StücklisteMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see stücklisteMapper()
   */
  private static StücklisteMapper stücklisteMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected StücklisteMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>StücklisteMapper.stücklisteMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>StücklisteMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> StücklisteMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>StücklisteMapper</code>-Objekt.
   * @see StücklisteMapper
   */
  public static StücklisteMapper stücklisteMapper() {
    if (stücklisteMapper == null) {
      stücklisteMapper = new StücklisteMapper();
    }

    return stücklisteMapper;
  }
}