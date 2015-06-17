package de.hdm.it04.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.shared.Bauteil;
import java.util.Vector;

/**
 * Diese Klasse wird benötigt, um die Kommunikation zwischen der Datenbank
 * und dem Client zu ermöglichen. Hierbei werden die Methoden der Mapper-Klasse
 * aufgerufen.
 */
public class It04gwtServiceImpl extends RemoteServiceServlet implements
		It04gwtService {

	private static final long serialVersionUID = 1L;

	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. Die Methode gibt ein Bauteil als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Bauteil.
	 * 
	 * @param ID als Integer
	 * @return Vektor mit Bauteil-Objekten
	 */
	public Vector<Bauteil> getBauteil(int id) {

		return BauteilMapper.bauteilMapper().findByKey(id);
	}

	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein Objekt vom Typ Bauteil
	 * @return Objekt vom Typ Bauteil
	 */
	public Bauteil updateBauteil(Bauteil bt) {

		return BauteilMapper.bauteilMapper().update(bt);
	}

	/**
	 * Die Methode wird benötigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden. Da mehrere Bauteile mit dem selben Namen exisitieren können, wird
	 * das Bauteil in einem Vektor gespeichert.
	 * 
	 * @param Ein Name eines Bauteils, welches gefunden werden soll
	 * @return Vektor vom Typ Bauteil, welches alle Bauteile mit dem übergebenen
	 *         Namen enthält
	 */
	public Vector<Bauteil> findByName(String name) {

		return BauteilMapper.bauteilMapper().findByName(name);
	}

	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Bauteil
	 */
	public Bauteil create(Bauteil bt) {

		bt = BauteilMapper.bauteilMapper().insert(bt);

		return bt;
	}

	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Bauteil, welcher alle Bauteile enthält
	 */
	public Vector<Bauteil> getAll() {

		return BauteilMapper.bauteilMapper().findAll();
	}

	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID von einem Bauteil als Integer,
	 * @return Ein String mit einer Meldung, ob Bauteil erfolgreich gelöscht
	 *         wurde
	 */
	public String delete(int id) {

		return BauteilMapper.bauteilMapper().delete(id);
	}
}
