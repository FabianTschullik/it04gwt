package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;
import de.hdm.it04.shared.Enderzeugnis;

public interface It04gwtServiceAsync {

	//Asyncs geben niemals einen wert zurück, da dieser über callback zurück kommt.
		
		//------------------------------------------------------
		//-------------Bauteil----------------------------------
		//------------------------------------------------------
		void createBauteil(AsyncCallback callback);
		void getBauteil(int id, AsyncCallback callback);
		void getBauteil2(int id, AsyncCallback callback);
		void getBauteil (String name, AsyncCallback<Vector<Bauteil>> callback);
		void getAll(AsyncCallback<Vector<Bauteil>> callback);
		void update(Bauteil bt, AsyncCallback callback);
		void deleteBauteil(int id,AsyncCallback callback);
		//------------------------------------------------------
		//-------------Ende Bauteil-----------------------------
		//------------------------------------------------------
		
		
		
		void createEnderzeugnis(AsyncCallback<Enderzeugnis> callback);
		void getEnderzeugnisById(int id, AsyncCallback<Enderzeugnis> callback );
		void findConnectedBaugruppe(int id, AsyncCallback callback);
		void findConnectedBauteileByKey(int id, AsyncCallback<Vector<Bauteil>> callback);		
		void updateEnderzeugnis(Enderzeugnis ez, AsyncCallback<Enderzeugnis> callback);
		void getBaugruppeDetails(int id, AsyncCallback<Baugruppe> callback);
		void update (Baugruppe bg, Bauteil bt, AsyncCallback callback);
}
