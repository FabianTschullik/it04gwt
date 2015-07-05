package de.hdm.it04.client.service.report;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * @author Schwab
 *
 */
@RemoteServiceRelativePath("smsReport")
public interface It04gwtServiceReport extends RemoteService{
	
	/**
	 *Strukturstückliste
	 */
	Vector <Baugruppe> getBaugruppe(int id);
	Vector <Baugruppe> getBaugruppe(String name);
	Baugruppe createStrukturstuecklisteReport(int id);
	
	
	
	/**
	 * Materialbedarf
	 */
	Vector <Enderzeugnis> getEnderzeugnis(int id);
	Vector <Enderzeugnis> getEnderzeugnis(String name);
	Enderzeugnis createMaterialbedarfReport(int id, int anzahl);
	

}
