package de.hdm.it04.server.db;

import java.sql.*;

/**
 * Mapper-Klasse, die <code>Baugruppe</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfügung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gelöscht werden können. Das Mapping ist bidirektional. D.h., Objekte können
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BauteilMapper, StücklisteMapper, EnderzeugnisMapper, BenutzerMapper}
 *
 * @author Schneider, Mähler, Thies
 */
public class BaugruppeMapper {

  /**
   * Die Klasse BaugruppeMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
   * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see baugruppeMapper()
   */
  private static BaugruppeMapper baugruppeMapper = null;

  /**
   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected BaugruppeMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>BaugruppeMapper.baugruppeMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafür sorgt, dass nur eine einzige
   * Instanz von <code>BaugruppeMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> BaugruppeMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>BaugruppeMapper</code>-Objekt.
   * @see BaugruppeMapper
   */
  public static BaugruppeMapper baugruppeMapper() {
    if (baugruppeMapper == null) {
      baugruppeMapper = new BaugruppeMapper();
    }

    return baugruppeMapper;
  }
}
