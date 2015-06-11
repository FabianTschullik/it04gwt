package de.hdm.it04.shared;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class  BusinessObject implements Serializable {
	
	//Variablendeklarationen
	private int id;
	private Timestamp erstellungsDatum;
	private Timestamp aenderungsDatum;
	
	
	//getter setter Methoden
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getErstellungsDatum() {
		
		return erstellungsDatum;
	}

	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	public Timestamp getAenderungsDatum() {
		return aenderungsDatum;
	}

	public void setAenderungsDatum(Timestamp aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}
}


	
