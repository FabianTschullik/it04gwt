package de.hdm.it04.client.service;


import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;


/**
 * Interface für RPC: Alle Methoden die auf Serverseite vorhanden sind,
 * müssen hier gelistet werden, damit RPC funktioniert.
 */
public interface It04gwtServiceClientInt {

//------------------------------------------------------------------------------------
//----------------------------------Bauteil Methoden----------------------------------
//------------------------------------------------------------------------------------
	void getBauteil(int id);
	void getBauteilForUpdate(int id);
	void createBauteil();
	void getAll();
	void getBauteil(String name);
	void updateBauteil(Bauteil bt);
	void deleteBauteil(int id);
	void getAllBauteileForZuordnung();

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
	//void getEnderzeugnis(String name);
	void getEnderzeugnisForUpdate (int id);
	void getAllEnderzeugnisse();
	void getAllBaugruppenForZuordnung();
//------------------------------------------------------------------------------------
//------------------------------Ende Enderzeugnis----------------------------------
//------------------------------------------------------------------------------------	
	
	
}
