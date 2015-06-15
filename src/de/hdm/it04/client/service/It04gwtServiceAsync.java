package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Bauteil;

public interface It04gwtServiceAsync {

	//Asyn geben niemals einen wert zur�ck, da dieser �ber callback zur�ck kommt.
	

	
		void getBauteil(int id, AsyncCallback<Vector<Bauteil>> callback);
		
		void create(Bauteil bt, AsyncCallback callback);
		
		void delete(int id, AsyncCallback callback);
		
		void getAll(AsyncCallback<Vector<Bauteil>> callback);
		
		void findByName(String name, AsyncCallback<Vector<Bauteil>> callback);
		
		void updateBauteil(Bauteil bt, AsyncCallback callback);
}
