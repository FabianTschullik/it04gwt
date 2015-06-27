package de.hdm.it04.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public class It04gwtServiceReportImpl extends RemoteServiceServlet implements It04gwtServiceReport {

	@Override
	public Baugruppe createStrukturstuecklisteReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enderzeugnis createMaterialbedarfReport(int id, int anzahl) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
