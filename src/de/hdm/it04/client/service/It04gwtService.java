package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.HTML;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Benutzer;
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.LoginInfo;

/**
 * Das Interface It04gwtService wird von der Klasse It04gwtServiceImpl
 * implementiert. Es enthält alle Methoden, die auf der Serverseite vorhanden
 * sein müssen. Das Interface wird für die RPCs benötigt.
 * 
 * @author Geier, Maehler, Schwab, Tschullik, Voelker
 *
 */
@RemoteServiceRelativePath("administration")
public interface It04gwtService extends RemoteService {

	String getUserEmail(String token);

	LoginInfo login(String requestUri);

	LoginInfo loginDetails(String token);

	// ---------------------------------------------------------------------------
	// --------------------------- Bauteil
	// ---------------------------------------
	// ---------------------------------------------------------------------------
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Bauteil
	 */
	Bauteil createBauteil();

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
	Vector<Bauteil> getBauteil(int id);

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
	Vector<Bauteil> getBauteil(String name);

	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Bauteil, welcher alle Bauteile enthält
	 */
	Vector<Bauteil> getAll();

	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil
	 * @return Objekt vom Typ Bauteil
	 */
	Vector<Bauteil> updateBauteil(Bauteil bt);

	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Bauteil als Integer,
	 * @return Ein String mit einer Meldung, ob Bauteil erfolgreich gelöscht
	 *         wurde
	 */
	String deleteBauteil(int id);

	// ---------------------------------------------------------------------------
	// --------------------------- Bauteil
	// ---------------------------------------
	// ---------------------------------------------------------------------------

	// ---------------------------------------------------------------------------
	// ---------------------------Baugruppe---------------------------------------
	// ----------------------------------------------------------------------------

	/**
	 * Die Methode legt eine Baugruppe an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Baugruppe welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Baugruppe
	 */

	Baugruppe createBaugruppe();

	/**
	 * Die Methode aktualisiert eine Baugruppe.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Baugruppe
	 * @return Objekt vom Typ Baugruppe
	 */

	Vector<Baugruppe> updateBaugruppe(Baugruppe bg);

	/**
	 * Die Methode löscht eine Baugruppe mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einer Baugruppe als Integer,
	 * @return Ein String mit einer Meldung, ob die Baugruppe erfolgreich
	 *         gelöscht wurde
	 */

	String deleteBaugruppe(int id);

	/**
	 * Diese Methode wird benötigt, um eine Baugruppe mit einer bestimmten ID zu
	 * finden. Die Methode gibt eine Baugruppe als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Baugruppe.
	 * 
	 * @param ID
	 *            als Integer
	 * @return Vektor mit Bauteil-Objekten
	 */

	Vector<Baugruppe> getBaugruppe(int id);

	/**
	 * Die Methode wird benötigt, um eine Baugruppe mit einem bestimmten Namen
	 * zu finden. Da mehrere Baugruppen mit dem selben Namen exisitieren können,
	 * wird das Bauteil in einem Vektor gespeichert.
	 * 
	 * @param Ein
	 *            Name einer Baugruppe, welches gefunden werden soll
	 * @return Vektor vom Typ Baugruppe, welches alle Baugruppen mit dem
	 *         übergebenen Namen enthält
	 */

	Vector<Baugruppe> getBaugruppe(String name);

	/**
	 * Die Methode findet alle angelegten Baugruppen und speichert diese in
	 * einem Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Baugruppe, welcher alle Baugruppen enthält
	 */

	Vector<Baugruppe> getAllBaugruppen();

	// ---------------------------------------------------------------------------
	// ---------------------------Ende
	// Baugruppe---------------------------------------
	// ----------------------------------------------------------------------------

	// ---------------------------------------------------------------------------
	// ---------------------------Enderzeugnis---------------------------------------
	// ----------------------------------------------------------------------------

	/**
	 * Die Methode legt ein Enderzeugnis an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Enderzeugnis welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Enderzeugnis
	 */

	Enderzeugnis createEnderzeugnis();

	/**
	 * Die Methode löscht ein Enderzeugnis mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Enderzeugnis als Integer,
	 * @return Ein String mit einer Meldung, ob das Enderzeugnis erfolgreich
	 *         gelöscht wurde
	 */

	String deleteEnderzeugnis(int id);

	/**
	 * Die Methode findet alle angelegten Enderzeugnisse und speichert diese in
	 * einem Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Enderzeugnis, welcher alle Enderzeugnisse
	 *         enthält
	 */

	Vector<Enderzeugnis> getAllEnderzeugnisse();

	/**
	 * Diese Methode wird benötigt, um ein Enderzeugnis mit einer bestimmten ID
	 * zu finden. Die Methode gibt ein Enderzeugnis als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Enderzeugnis.
	 * 
	 * @param ID
	 *            als Integer
	 * @return Vektor mit Enderzeugnis-Objekten
	 */

	Vector<Enderzeugnis> getEnderzeugnis(int id);

	/**
	 * Die Methode wird benötigt, um ein Enderzeugnis mit einem bestimmten Namen
	 * zu finden. Da mehrere Enderzeugnisse mit dem selben Namen exisitieren
	 * können, wird das Enderzeugnis in einem Vektor gespeichert.
	 * 
	 * @param Ein
	 *            Name eines Enderzeugnisses, welches gefunden werden soll
	 * @return Vektor vom Typ Enderzeugnis, welches alle Enderzeugnisse mit dem
	 *         übergebenen Namen enthält
	 */

	Vector<Enderzeugnis> getEnderzeugnis(String name);

	/**
	 * Die Methode löscht ein Enderzeugnis mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Enderzeugnis als Integer,
	 * @return Ein String mit einer Meldung, ob das Enderzeugnis erfolgreich
	 *         gelöscht wurde
	 */

	Vector<Enderzeugnis> updateEnderzeugnis(Enderzeugnis ez);

	/**
	 * Die Methode findet die ausgewaehlte Baugruppe, damit diese zu einem
	 * Enderzeugnis hinzugefuegt werden kann.
	 * 
	 * @param id
	 * @return Ein Vektor vom Typ Baugruppe, welcher die Baugruppe mit der
	 *         zuordnung zu dem Enderzeugnis enthaelt.
	 */

	Vector<Baugruppe> getBaugruppeForZuordnungDetails(int id);

	// ---------------------------------------------------------------------------
	// ---------------------------Ende
	// Enderzeugnis-------------------------------------
	// ----------------------------------------------------------------------------

	// ---------------------------------------------------------------------------
	// ---------------------------User-------------------------------------
	// ----------------------------------------------------------------------------

	/**
	 * Diese Methode speichert die E-Mail mit der der aktuelle Nutzer eingeloggt
	 * ist in der Datenbank.
	 * 
	 * @param mail
	 * @return
	 */

	Benutzer saveBenutzer(String mail);

	Benutzer checkBenutzer(String mail);

	
	//---------------------------------------------------------------------------
	//---------------------------Report-------------------------------------
	//----------------------------------------------------------------------------	
	
	Vector <Bauteil> getMaterialbedarf(int id, int menge);
	String getStrukturstueckliste(int id);
	
	

}
