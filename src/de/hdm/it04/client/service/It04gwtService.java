package de.hdm.it04.client.service;





import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.it04.shared.Bauteil;


//alle methoden, die auch auf dem server sein sollen



@RemoteServiceRelativePath("exampleservice")
public interface It04gwtService extends RemoteService {
	

	
	Bauteil getBauteil(int id);

	Bauteil create(String name, String beschreibung, String materialBezeichnung, Long erstellungsZeit);
	

}
