package de.hdm.it04.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;
import de.hdm.it04.shared.Enderzeugnis;

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
	public Bauteil getBauteil(int id) {

		return BauteilMapper.bauteilMapper().findByKey(id);
	}
	public Bauteil getBauteil2(int id) {

		return BauteilMapper.bauteilMapper().findByKey(id);
	}
	
	public String deleteBauteil(int id){
		return BauteilMapper.bauteilMapper().delete(id);
	}
	
	public Vector<Bauteil> showAllBauteile(){
		return BauteilMapper.bauteilMapper().findAll();
	}
	
	public Vector<Bauteil> findConnectedBauteileByKey(int id){
		
		return BaugruppeMapper.baugruppeMapper().findConnectedBauteileByKey(id);
	}
	
	public Baugruppe findConnectedBaugruppe(int id){
		
		return EnderzeugnisMapper.enderzeugnisMapper().findConnectedBaugruppe(id);
	}
	
public Enderzeugnis getEnderzeugnisById(int id){
		
		return EnderzeugnisMapper.enderzeugnisMapper().getEnderzeugnisById(id);
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
	public Bauteil createBauteil() {

		return BauteilMapper.bauteilMapper().insert();
	}
	
	public Enderzeugnis createEnderzeugnis() {

		return EnderzeugnisMapper.enderzeugnisMapper().insert();
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
	
	public Bauteil getBauteilDetails(int id) {
	
	return BauteilMapper.bauteilMapper().getBauteilDetails(id);
	}
	
	public Baugruppe getBaugruppeDetails(int id) {
		
		return BaugruppeMapper.baugruppeMapper().getBaugruppeDetails(id);
		}
	
	public Baugruppe update (Baugruppe bg, Bauteil bt){
		return BaugruppeMapper.baugruppeMapper().update(bg, bt);
	}

	
	public Enderzeugnis updateEnderzeugnis(Enderzeugnis ez) {
		
		return EnderzeugnisMapper.enderzeugnisMapper().update(ez);
	}



	




}
