package de.hdm.it04.client.service;


import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;


/**
 * Interface für RPC: Alle Methoden die auf Serverseite vorhanden sind,
 * müssen hier gelistet werden, damit RPC funktioniert.
 */
public interface It04gwtServiceClientInt {
	
	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. 
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	void getBauteil(int id);
	
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return void
	 */
	void createBauteil();
	
	void findConnectedBaugruppe(int id);
	
	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return void
	 */
	void getAll();
	
	/**
	 * Die Methode wird benötigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden. 
	 * 
	 * @param Ein Name eines Bauteils als String, welches gefunden werden soll
	 * @return void
	 */
	void findByName(String name);
	
	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein Objekt vom Typ Bauteil
	 * @return void
	 */
	void updateBauteil(Bauteil bt);
	
	void findConnectedBauteileByKey(int id);
	
	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID von einem Bauteil als Integer,
	 * @return void
	 */
	void delete(int id);
	
	void getBauteilDetails(int id);
	void getBaugruppeDetails(int id);
	void update (Baugruppe bg, Bauteil bt);
}
