package de.hdm.it04.client.service.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public interface It04gwtServiceReportAsync {
	void createStrukturstuecklisteReport(int id, AsyncCallback<Baugruppe> callback);
	void createMaterialbedarfReport(int id, int anzahl, AsyncCallback<Enderzeugnis> callback);

}