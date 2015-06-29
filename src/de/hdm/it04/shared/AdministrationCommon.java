package de.hdm.it04.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("administration")
public interface AdministrationCommon extends RemoteService {
	
	
	
	public void init() throws IllegalArgumentException;
	
//------------------------------------------------------------------------------
//-------------------------- Bauteil -------------------------------------------
//------------------------------------------------------------------------------	
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Bauteil
	 */
	public Bauteil createBauteil();
	
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
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. Die Methode gibt ein Bauteil als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Bauteil.
	 * 
	 * @param ID
	 *            als Integer
	 * @return Vektor mit Bauteil-Objekten
	 */
	public Vector<Bauteil> getBauteil(String name);
	
	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Bauteil, welcher alle Bauteile enthält
	 */
	public Vector<Bauteil> getAllBauteile();
	
	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil
	 * @return Objekt vom Typ Bauteil
	 */
	public Bauteil updateBauteil(Bauteil bt);
	
	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Bauteil als Integer,
	 * @return Ein String mit einer Meldung, ob Bauteil erfolgreich gelöscht
	 *         wurde
	 */
	public String deleteBauteil(int id);
	
//------------------------------------------------------------------------------
//-------------------------- Ende Bauteil -------------------------------------------
//------------------------------------------------------------------------------
	
	
	
	
	public Enderzeugnis createEnderzeugnis() throws IllegalArgumentException;
	public Enderzeugnis getEnderzeugnisById(int id);
	public Baugruppe findConnectedBaugruppe(int id);
	public Vector<Bauteil> findConnectedBauteileByKey(int id);
	public Enderzeugnis updateEnderzeugnis(Enderzeugnis ez);
	public Baugruppe getBaugruppeDetails(int id);
	public Baugruppe update(Baugruppe bg, Bauteil bt);
}
