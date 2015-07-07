package de.hdm.it04.client.service;


import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;


/**
 * Interface für RPC: Alle Methoden die auf Serverseite vorhanden sind,
 * müssen hier gelistet werden, damit RPC funktioniert.
 * @author 
 */
public interface It04gwtServiceClientInt {

//------------------------------------------------------------------------------------
//----------------------------------Bauteil Methoden----------------------------------
//------------------------------------------------------------------------------------
	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. 
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	void getBauteil(int id);
	void getBauteilForUpdate(int id);
	
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return void
	 */
	void createBauteil();
	
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
	void getBauteil(String name);
	
	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein Objekt vom Typ Bauteil
	 * @return void
	 */
	void updateBauteil(Bauteil bt);
	
	
	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID von einem Bauteil als Integer,
	 * @return void
	 */
	void deleteBauteil(int id);

//------------------------------------------------------------------------------------
//------------------------------ENDE-Bauteil Methoden----------------------------------
//------------------------------------------------------------------------------------

	
//------------------------------------------------------------------------------------
//------------------------------Baugruppe----------------------------------
//------------------------------------------------------------------------------------
	void createBaugruppe();
	void updateBaugruppe(Baugruppe bg);
	void deleteBaugruppe(int id);
	void getBaugruppe(int id);
	void getBaugruppe(String name);
	void getBaugruppeForUpdate (int id);
	void getAllBaugruppen();
//------------------------------------------------------------------------------------
//------------------------------ENDE Baugruppe----------------------------------
//------------------------------------------------------------------------------------
	
	
//------------------------------------------------------------------------------------
//------------------------------Enderzeugnis----------------------------------
//------------------------------------------------------------------------------------	
	void createEnderzeugnis();
	void updateEnderzeugnis(Enderzeugnis ez);
	void deleteEnderzeugnis(int id);
	void getEnderzeugnis(int id);
	void getEnderzeugnis(String name);
	void getEnderzeugnisForUpdate (int id);
	void getAllEnderzeugnisse();
//------------------------------------------------------------------------------------
//------------------------------Ende Enderzeugnis----------------------------------
//------------------------------------------------------------------------------------	
	
	//------------------------------------------------------------------------------------
	//------------------------------Benutzer----------------------------------
	//------------------------------------------------------------------------------------	
	
	void saveBenutzer(String name);
	void checkBenutzer(String name);
	
	//------------------------------------------------------------------------------------
		//------------------------------Ende Benutzer----------------------------------
		//------------------------------------------------------------------------------------	
	
	
}
