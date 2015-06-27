package de.hdm.it04.client.service.report;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public interface It04gwtServiceReport extends RemoteService{
	Baugruppe createStrukturstuecklisteReport(int id);
	Enderzeugnis createMaterialbedarfReport(int id, int anzahl);

}
