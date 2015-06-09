package de.hdm.it04.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface It04gwtServiceAsync {

	//Asyn geben niemals einen wert zur�ck, da dieser �ber callback zur�ck kommt.
	

	
		void getBauteil(int id, AsyncCallback callback);
		
		void create(String name, AsyncCallback callback);
}
