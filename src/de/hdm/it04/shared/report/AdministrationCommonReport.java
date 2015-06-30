package de.hdm.it04.shared.report;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

@RemoteServiceRelativePath(value = "AdministrationCommonReport")



public interface AdministrationCommonReport extends RemoteService {
	
	
	public void init() throws IllegalArgumentException;
	
	Baugruppe createStrukturstuecklisteReport(int id);
	
	Enderzeugnis createMaterialbedarfReport(int id, int anzahl);

}
