package de.hdm.it04.server;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.AdministrationCommon;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

public class AdministrationCommonImpl extends RemoteServiceServlet implements
AdministrationCommon {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private BaugruppeMapper baugruppeMapper = null;
	private EnderzeugnisMapper enderzeugnisMapper = null;
	private BauteilMapper bauteilMapper = null;
	
	
	public AdministrationCommonImpl() throws IllegalArgumentException {

	}
	

	
	/**
	 * Initialsierungsmethode. Siehe dazu Anmerkungen zum
	 * No-Argument-Konstruktor {@link #AdministrationCommonImpl()}. Diese
	 * Methode muss f�r jede Instanz von <code>AdministrationCommonImpl</code>
	 * aufgerufen werden.
	 */
	
	
	
	public void init() throws IllegalArgumentException {
		/*
		 * Ganz wesentlich ist, dass die Administration einen vollst�ndigen
		 * Satz von Mappern besitzt, mit deren Hilfe sie dann mit der Datenbank
		 * kommunizieren kann.
		 */
		this.baugruppeMapper = BaugruppeMapper.getBaugruppeMapper();
		this.enderzeugnisMapper = EnderzeugnisMapper.getEnderzeugnisMapper();
		this.bauteilMapper = BauteilMapper.getBauteilMapper();
	}

//------------------------------------------------------------------------------
//-------------------------- Bauteil -------------------------------------------
//------------------------------------------------------------------------------
	
	@Override
	public Bauteil createBauteil() {
		return this.bauteilMapper.getBauteilMapper().insert();
		
	}


	@Override
	public Vector<Bauteil> getBauteil(int id) {
		return this.bauteilMapper.getBauteilMapper().findByKey(id);
	}
	
	@Override

	
	public Vector<Bauteil> getBauteilByName(String name) {
		return this.bauteilMapper.getBauteilMapper().findByName(name);
		

	}
		
	
	
	
	@Override
	public Vector<Bauteil> getAllBauteile() {
		return this.bauteilMapper.getBauteilMapper().findAll();
	}

		
	@Override
	public Bauteil updateBauteil(Bauteil bt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public String deleteBauteil(int id) {
		// TODO Auto-generated method stub
		return null;
	}
//------------------------------------------------------------------------------
//-------------------------- Ende Bauteil -------------------------------------------
//------------------------------------------------------------------------------

	@Override
	public Baugruppe createBaugruppe() {
		
		return this.baugruppeMapper.insert();
	}
	

	


	@Override
	public Enderzeugnis createEnderzeugnis() throws IllegalArgumentException {
		
		return this.enderzeugnisMapper.createEnderzeugnis();
	}



	private BaugruppeMapper enderzeugnisMapper() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Enderzeugnis getEnderzeugnisById(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Baugruppe findConnectedBaugruppe(int id) {
		return null;
		
		
	}



	@Override
	public Vector<Bauteil> findConnectedBauteileByKey(int id) {
		// TODO Auto-generated method stub
		return null;
	}



	



	


	@Override
	public Enderzeugnis updateEnderzeugnis(Enderzeugnis ez) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Baugruppe update(Baugruppe bg, Bauteil bt) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Baugruppe getBaugruppeDetails(int id) {
	
		return this.baugruppeMapper.getBaugruppeMapper().getBaugruppeDetails(id);
	}



	
	
	
}