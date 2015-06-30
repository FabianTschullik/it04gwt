package de.hdm.it04.shared.report;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

import de.hdm.it04.*;

public interface AdministrationCommonReportAsync {
	
	public void init(AsyncCallback<Void> callback);
	void createStrukturstuecklisteReport(int id, AsyncCallback<Baugruppe> callback);
	void createMaterialbedarfReport(int id, int anzahl, AsyncCallback<Enderzeugnis> callback);

}
