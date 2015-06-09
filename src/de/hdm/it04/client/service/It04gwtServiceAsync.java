package de.hdm.it04.client.service;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface It04gwtServiceAsync {

	//Asyn geben niemals einen wert zur�ck, da dieser �ber callback zur�ck kommt.
	

	
		void getBauteil(int id, AsyncCallback callback);
		
		void create(String name, String beschreibung, String materialBezeichnung, Long erstellungsZeit, AsyncCallback callback);
}
