package de.hdm.it04.client.service.report;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * @author Schwab
 *
 */
public interface It04gwtServiceReportAsync {
	void createStrukturstuecklisteReport(int id, AsyncCallback<Baugruppe> callback);
	void getBaugruppe(int id, AsyncCallback<Vector<Baugruppe>> callback);
	void getBaugruppe(String name, AsyncCallback<Vector<Baugruppe>> callback);
	void createMaterialbedarfReport(int id, int anzahl, AsyncCallback<Enderzeugnis> callback);
	void getEnderzeugnis(int id, AsyncCallback<Vector<Enderzeugnis>> callback );
	void getEnderzeugnis(String name, AsyncCallback<Vector<Enderzeugnis>> callback );

}
