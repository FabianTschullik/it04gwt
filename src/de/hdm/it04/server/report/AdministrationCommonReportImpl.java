package de.hdm.it04.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.report.AdministrationCommonReport;

public class AdministrationCommonReportImpl extends RemoteServiceServlet implements AdministrationCommonReport{
	
	private static final long serialVersionUID = 1L;
	
	private BaugruppeMapper baugruppeMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	
	public AdministrationCommonReportImpl() throws IllegalArgumentException {

	}
	
	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum
	 * No-Argument-Konstruktor {@link #AdministrationCommonImpl()}. Diese
	 * Methode muss fuer jede Instanz von <code>AdministrationCommonImpl</code>
	 * aufgerufen werden.
	 */
	
	public void init() throws IllegalArgumentException {

		this.baugruppeMapper = BaugruppeMapper.getBaugruppeMapper();
		this.enderzeugnisMapper = EnderzeugnisMapper.getEnderzeugnisMapper();
	}

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
