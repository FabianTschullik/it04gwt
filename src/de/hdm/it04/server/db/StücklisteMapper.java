package de.hdm.it04.server.db;

import java.sql.*;

/**
 * Mapper-Klasse, die <code>St�ckliste</code>-Objekte auf eine relationale
 * Datenbank abbildet. Hierzu wird eine Reihe von Methoden zur Verf�gung
 * gestellt, mit deren Hilfe z.B. Objekte gesucht, erzeugt, modifiziert und
 * gel�scht werden k�nnen. Das Mapping ist bidirektional. D.h., Objekte k�nnen
 * in DB-Strukturen und DB-Strukturen in Objekte umgewandelt werden.
 * 
 * {@link BaugruppeMapper, EnderzeugnisMapper, BenutzerMapper, BauteilMapper}
 *
 * @author Schneider, M�hler, Thies
 */
public class St�cklisteMapper {

  /**
   * Die Klasse St�cklisteMapper wird nur einmal instantiiert. Man spricht hierbei
   * von einem sogenannten <b>Singleton</b>.
   * <p>
   * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal f�r
   * s�mtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
   * einzige Instanz dieser Klasse.
   * 
   * @see st�cklisteMapper()
   */
  private static St�cklisteMapper st�cklisteMapper = null;

  /**
   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
   * neue Instanzen dieser Klasse zu erzeugen.
   */
  protected St�cklisteMapper() {
  }

  /**
   * Diese statische Methode kann aufgrufen werden durch
   * <code>St�cklisteMapper.st�cklisteMapper()</code>. Sie stellt die
   * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine einzige
   * Instanz von <code>St�cklisteMapper</code> existiert.
   * <p>
   * 
   * <b>Fazit:</b> St�cklisteMapper sollte nicht mittels <code>new</code>
   * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
   * 
   * @return Das <code>St�cklisteMapper</code>-Objekt.
   * @see St�cklisteMapper
   */
  public static St�cklisteMapper st�cklisteMapper() {
    if (st�cklisteMapper == null) {
      st�cklisteMapper = new St�cklisteMapper();
    }

    return st�cklisteMapper;
  }
}