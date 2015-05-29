package de.hdm.it04.server.db;

import java.sql.*;

/**
 * Mapper-Klasse, die <code>Stueckliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verfuegung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * geloescht werden koennen. Das Mapping ist bidirektional. D.h., Objekte koennen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, EnderzeugnisMapper, BenutzerMapper, BauteilMapper}
 *
 * @author Schneider, Maehler, Schwab, Thies
 */
public class StuecklisteMapper {

  /**
   * Die Klasse StuecklisteMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal fuer
   * saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see stuecklisteMapper()
   */
  private static StuecklisteMapper stuecklisteMapper = null;

  /**
   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected StuecklisteMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>StuecklisteMapper.stuecklisteMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine einzige
   * Instanz von <code>StuecklisteMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> StuecklisteMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>StuecklisteMapper</code>-Objekt.
   * @see StuecklisteMapper
   */
  public static StuecklisteMapper stuecklisteMapper() {
    if (stuecklisteMapper == null) {
      stuecklisteMapper = new StuecklisteMapper();
    }

    return stuecklisteMapper;
  }
}