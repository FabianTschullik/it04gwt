package de.hdm.it04.client.service;

import java.sql.Timestamp;

public interface It04gwtServiceClientInt {
	
	void getBauteil(int id);
	void create(String name, String beschreibung, String materialBezeichnung, Long erstellungsZeit);



}
