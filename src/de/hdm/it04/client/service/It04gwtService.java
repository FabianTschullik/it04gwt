package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Benutzer;
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.LoginInfo;

/**
 * Das Interface It04gwtService wird von der Klasse It04gwtServiceImpl implementiert.
 * Es enthält alle Methoden, die auf der Serverseite vorhanden sein müssen. 
 * Das Interface wird für die RPCs benötigt.
 * @author Geier, Maehler, Schwab, Tschullik, Voelker
 *
 */
@RemoteServiceRelativePath("sms")
public interface It04gwtService extends RemoteService {

	
	// TODO #09: start create login helper methods in service interface	
		String getUserEmail(String token);	
		
		LoginInfo login(String requestUri);	
		
		LoginInfo loginDetails(String token);
		// TODO #09:> end
	
	
	
	
	
//---------------------------------------------------------------------------
//--------------------------- Bauteil ---------------------------------------
//---------------------------------------------------------------------------
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
	
//---------------------------------------------------------------------------
//--------------------------- Bauteil ---------------------------------------
//---------------------------------------------------------------------------	
	
//---------------------------------------------------------------------------
//---------------------------Baugruppe---------------------------------------
//----------------------------------------------------------------------------
	Baugruppe createBaugruppe();
	Vector <Baugruppe> updateBaugruppe(Baugruppe bg);
	String deleteBaugruppe(int id);
	Vector <Baugruppe> getBaugruppe(int id);
	Vector <Baugruppe> getBaugruppe(String name);
	Vector <Baugruppe> getAllBaugruppen();
//---------------------------------------------------------------------------
//---------------------------Ende Baugruppe---------------------------------------
//----------------------------------------------------------------------------

	
//---------------------------------------------------------------------------
//---------------------------Enderzeugnis---------------------------------------
//----------------------------------------------------------------------------	
	
	Enderzeugnis createEnderzeugnis();
	String deleteEnderzeugnis(int id);
	Vector <Enderzeugnis> getAllEnderzeugnisse();
	Vector <Enderzeugnis> getEnderzeugnis(int id);
	Vector <Enderzeugnis> getEnderzeugnis(String name);
	Vector <Enderzeugnis> updateEnderzeugnis(Enderzeugnis ez);
	Vector <Baugruppe> getBaugruppeForZuordnungDetails(int id);
	
	
	//---------------------------------------------------------------------------
	//---------------------------Ende Enderzeugnis-------------------------------------
	//----------------------------------------------------------------------------	
	
	
	//---------------------------------------------------------------------------
	//---------------------------User-------------------------------------
	//----------------------------------------------------------------------------	
	
	Benutzer saveBenutzer(String mail);
	Benutzer checkBenutzer(String mail);
	
}
