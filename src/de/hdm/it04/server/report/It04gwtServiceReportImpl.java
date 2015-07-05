package de.hdm.it04.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * 
 * @author Schwab
 *
 */

public class It04gwtServiceReportImpl extends RemoteServiceServlet implements It04gwtServiceReport {
	
	/**
	 * Strukturstueckliste
	 */

	@Override
	public Baugruppe createStrukturstuecklisteReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Baugruppe> getBaugruppe(int id) {
		return BaugruppeMapper.baugruppeMapper().findByKey(id);
	}

	@Override
	public Vector<Baugruppe> getBaugruppe(String name) {
		return BaugruppeMapper.baugruppeMapper().findByName(name);
		
	}

	/**
	 * Materialbedarf
	 */
	
	@Override
	public Vector<Enderzeugnis> getEnderzeugnis(int id) {
		return EnderzeugnisMapper.enderzeugnisMapper().getEnderzeugnisById(id);
	}

	@Override
	public Vector<Enderzeugnis> getEnderzeugnis(String name) {
		//return EnderzeugnisMapper.enderzeugnisMapper().getEnderzeugnisByName(name);
		return null;
	}
	
	@Override
	public Enderzeugnis createMaterialbedarfReport(int id, int anzahl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
