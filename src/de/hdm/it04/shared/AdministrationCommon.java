package de.hdm.it04.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("administration")
public interface AdministrationCommon extends RemoteService {
	
	
	
	public void init() throws IllegalArgumentException;
	
	
	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. Die Methode gibt ein Bauteil als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Bauteil.
	 * 
	 * @param ID
	 *            als Integer
	 * @return Vektor mit Bauteil-Objekten
	 */
	public Vector<Bauteil> getBauteil(int id);
	
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Bauteil
	 */
	public Bauteil createBauteil();
	
	public Enderzeugnis createEnderzeugnis() throws IllegalArgumentException;
	
	public Enderzeugnis getEnderzeugnisById(int id);
	
	public Baugruppe findConnectedBaugruppe(int id);
	
	public Vector<Bauteil> findConnectedBauteileByKey(int id);
	
	
	
	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Bauteil als Integer,
	 * @return Ein String mit einer Meldung, ob Bauteil erfolgreich gelöscht
	 *         wurde
	 */
	public String delete(int id);
	
	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil
	 * @return Objekt vom Typ Bauteil
	 */
	public Bauteil updateBauteil(Bauteil bt);
	
	public Enderzeugnis updateEnderzeugnis(Enderzeugnis ez);
	
	
	
	
	
	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Bauteil, welcher alle Bauteile enthält
	 */
	public Vector<Bauteil> getAll();
	
	/**
	 * Die Methode wird benötigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden. Da mehrere Bauteile mit dem selben Namen exisitieren können, wird
	 * das Bauteil in einem Vektor gespeichert.
	 * 
	 * @param Ein
	 *            Name eines Bauteils, welches gefunden werden soll
	 * @return Vektor vom Typ Bauteil, welches alle Bauteile mit dem übergebenen
	 *         Namen enthält
	 */
	public Vector<Bauteil> findByName(String name);
	
	
	public Bauteil getBauteilDetails(int id);
	public Baugruppe getBaugruppeDetails(int id);

	public Baugruppe update(Baugruppe bg, Bauteil bt);
}
