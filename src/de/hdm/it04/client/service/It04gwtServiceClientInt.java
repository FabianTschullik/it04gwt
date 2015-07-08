package de.hdm.it04.client.service;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * Interface für RPC: Alle Methoden, die auf Serverseite vorhanden sind, muessen
 * hier gelistet werden, damit RPC funktioniert.
 * 
 * @author Tschullik, Voelker
 */
public interface It04gwtServiceClientInt {

	// ------------------------------------------------------------------------------------
	// ----------------------------------Bauteil
	// Methoden----------------------------------
	// ------------------------------------------------------------------------------------
	/**
	 * Diese Getter-Methode wird benoetigt, um ein Bauteil mit einer bestimmten
	 * ID zu finden.
	 * 
	 * @param ID
	 *            als Integer
	 * @return void
	 */
	void getBauteil(int id);

	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 */
	void createBauteil();

	/**
	 * Die Getter-Methode findet alle angelegten Bauteilel
	 */
	void getAll();

	/**
	 * Die Getter-Methode wird benoetigt, um ein Bauteil mit einem bestimmten
	 * Namen zu finden.
	 * 
	 * @param Ein
	 *            Name eines Bauteils als String, welches gefunden werden soll
	 */
	void getBauteil(String name);

	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil
	 */
	void updateBauteil(Bauteil bt);

	/**
	 * Die Methode loescht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Bauteil als Integer,
	 */
	void deleteBauteil(int id);

	// ------------------------------------------------------------------------------------
	// ------------------------------ENDE-Bauteil
	// Methoden----------------------------------
	// ------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------
	// ------------------------------Baugruppe----------------------------------
	// ------------------------------------------------------------------------------------
	/**
	 * Die Methode legt eine Baugruppe an.
	 */
	void createBaugruppe();

	/**
	 * Die Methode aktualisiert eine Baugruppe.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Baugruppe
	 */
	void updateBaugruppe(Baugruppe bg);

	/**
	 * Die Methode loescht eine Baugruppe.
	 * 
	 * @param id
	 */
	void deleteBaugruppe(int id);

	/**
	 * Die Getter-Methode wird benoetigt, um eine Baugruppe mit einer bestimmten
	 * ID zu finden.
	 * 
	 * @param id
	 */
	void getBaugruppe(int id);

	/**
	 * Die Getter-Methode wird benoetigt, um eine Baugruppe mit einem bestimmten
	 * Namen zu finden.
	 * 
	 * @param name
	 */
	void getBaugruppe(String name);

	/**
	 * Die Getter-Methode findet alle angelegten Baugruppen.
	 */
	void getAllBaugruppen();

	// ------------------------------------------------------------------------------------
	// ------------------------------ENDE
	// Baugruppe----------------------------------
	// ------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------
	// ------------------------------Enderzeugnis----------------------------------
	// ------------------------------------------------------------------------------------

	/**
	 * Die Methode legt ein Enderzeugnis an.
	 */
	void createEnderzeugnis();

	/**
	 * Die Methode aktualisiert ein Enderzeugnis
	 * 
	 * @param Ein
	 *            Objekt vom Typ Enderzeugnis
	 */
	void updateEnderzeugnis(Enderzeugnis ez);

	/**
	 * Die Methode loescht ein Enderzeugnis.
	 * 
	 * @param id
	 */
	void deleteEnderzeugnis(int id);

	/**
	 * Die Getter-Methode wird benoetigt, um ein Enderzeugnis mit einer
	 * bestimmten ID zu finden.
	 * 
	 * @param id
	 */
	void getEnderzeugnis(int id);

	/**
	 * Die Getter-Methode wird benoetigt, um ein Enderzeugnis mit einem
	 * bestimmten Namen zu finden.
	 * 
	 * @param name
	 */
	void getEnderzeugnis(String name);

	/**
	 * Die Getter-Methode findet alle Enderzeugnisse.
	 */
	void getAllEnderzeugnisse();

	// ------------------------------------------------------------------------------------
	// ------------------------------Ende
	// Enderzeugnis----------------------------------
	// ------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------
	// ------------------------------Benutzer----------------------------------
	// ------------------------------------------------------------------------------------

	/**
	 * Die Methode speichert einen Benutzer.
	 * 
	 * @param Objekt
	 *            vom Typ String
	 */
	void saveBenutzer(String name);

	/**
	 * Die Methode ueberprueft den Benutzer.
	 * 
	 * @param Objekt
	 *            vom Typ String
	 */
	void checkBenutzer(String name);

	// ------------------------------------------------------------------------------------
	// ------------------------------Ende
	// Benutzer----------------------------------
	// ------------------------------------------------------------------------------------

}
