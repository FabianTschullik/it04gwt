package de.hdm.it04.shared.bo;

import java.sql.Date;

public class BusinessObject {
	
	private int id;
	
	private Date erstellungsDatum;
	
	private Date änderungsDatum;
	
	private Benutzer letzterBenutzer;

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

	/**
	 * @return the erstellungsDatum
	 */
	public Date getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * @param erstellungsDatum the erstellungsDatum to set
	 */
	public void setErstellungsDatum(Date erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * @return the änderungsDatum
	 */
	public Date getÄnderungsDatum() {
		return änderungsDatum;
	}

	/**
	 * @param ÄnderungsDatum the ÄnderungsDatum to set
	 */
	public void setÄnderungsDatum(Date änderungsDatum) {
		this.änderungsDatum = änderungsDatum;
	}

	/**
	 * @return the letzterBenutzer
	 */
	public Benutzer getLetzterBenutzer() {
		return letzterBenutzer;
	}

	/**
	 * @param letzterBenutzer the letzterBenutzer to set
	 */
	public void setLetzterBenutzer(Benutzer letzterBenutzer) {
		this.letzterBenutzer = letzterBenutzer;
	}

}
