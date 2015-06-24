package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;

public interface It04gwtServiceAsync {

	//Asyn geben niemals einen wert zurück, da dieser über callback zurück kommt.
	
		void getBauteil(int id, AsyncCallback<Vector<Bauteil>> callback);
		
		void createBauteil(AsyncCallback callback);
		
		void findConnectedBaugruppe(int id, AsyncCallback callback);
		
		void findConnectedBauteileByKey(int id, AsyncCallback<Vector<Bauteil>> callback);
		
		void delete(int id, AsyncCallback callback);
		
		void getAll(AsyncCallback<Vector<Bauteil>> callback);
		
		void findByName(String name, AsyncCallback<Vector<Bauteil>> callback);
		
		void updateBauteil(Bauteil bt, AsyncCallback callback);
		void getBauteilDetails(int id, AsyncCallback<Bauteil> callback);
		void getBaugruppeDetails(int id, AsyncCallback<Baugruppe> callback);
}
