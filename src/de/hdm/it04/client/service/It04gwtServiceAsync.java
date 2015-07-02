package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.LoginInfo;

public interface It04gwtServiceAsync {

	//Asyncs geben niemals einen wert zurück, da dieser über callback zurück kommt.
	
	// TODO #10: create login helper methods in service asynchronous interface	
		void getUserEmail(String token, AsyncCallback<String> callback);

		void login(String requestUri, AsyncCallback<LoginInfo> asyncCallback);

		void loginDetails(String token, AsyncCallback<LoginInfo> asyncCallback);
		// TODO #10:> end
	
	
	
	
		
		//------------------------------------------------------
		//-------------Bauteil----------------------------------
		//------------------------------------------------------
		void createBauteil(AsyncCallback callback);
		void getBauteil(int id, AsyncCallback<Vector<Bauteil>> callback);
		void getBauteilForUpdate(int id, AsyncCallback<Vector<Bauteil>> callback);
		void getBauteil (String name, AsyncCallback<Vector<Bauteil>> callback);
		void getAll(AsyncCallback<Vector<Bauteil>> callback);
		void updateBauteil(Bauteil bt, AsyncCallback<Vector<Bauteil>> callback);
		void deleteBauteil(int id,AsyncCallback callback);
		//------------------------------------------------------
		//-------------Ende Bauteil-----------------------------
		//------------------------------------------------------
		
		//------------------------------------------------------
		//------------Baugruppe---------------------------------
		//------------------------------------------------------
		void createBaugruppe(AsyncCallback<Baugruppe> callback);
		void updateBaugruppe(Baugruppe bg, AsyncCallback<Vector<Baugruppe>> callback);
		void deleteBaugruppe(int id, AsyncCallback callback);
		void getBaugruppe(int id, AsyncCallback<Vector<Baugruppe>> callback);
		void getBaugruppe(String name, AsyncCallback<Vector<Baugruppe>> callback);
		void getBaugruppeForUpdate(int id, AsyncCallback<Vector<Baugruppe>> callback);
		void getAllBaugruppen(AsyncCallback<Vector<Baugruppe>> callback);
		//------------------------------------------------------
		//------------Ende Baugruppe---------------------------------
		//------------------------------------------------------
		
		
		
		//------------------------------------------------------
		//------------Enderzeugnis---------------------------------
		//------------------------------------------------------
		void createEnderzeugnis(AsyncCallback<Enderzeugnis> callback);
		void getEnderzeugnis(int id, AsyncCallback<Vector<Enderzeugnis>> callback );	
		void getEnderzeugnisForUpdate(int id, AsyncCallback<Vector<Enderzeugnis>> callback );	
		//void getEnderzeugnis(String name, AsyncCallback<Vector<Enderzeugnis>> callback );
		void getAllEnderzeugnisse(AsyncCallback<Vector<Enderzeugnis>> callback );	
		void deleteEnderzeugnis(int id, AsyncCallback callback);
		void updateEnderzeugnis(Enderzeugnis ez, AsyncCallback<Vector<Enderzeugnis>> callback);
		//------------------------------------------------------
		//------------Ende Enderzeugnis---------------------------------
		//------------------------------------------------------
		
}
