package de.hdm.it04.shared.bo;

import java.sql.Date;

public class BusinessObject {
	
	private int id;
	
	private Date erstellungsDatum;
	
	private Date ÄnderungsDatum;
	
	private Benutzer letzterbenutzer;

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
	 * @return the �nderungsDatum
	 */
	public Date getÄnderungsDatum() {
		return ÄnderungsDatum;
	}

	/**
	 * @param �nderungsDatum the �nderungsDatum to set
	 */
	public void setÄnderungsDatum(Date ÄnderungsDatum) {
		this.ÄnderungsDatum = ÄnderungsDatum;
	}

	/**
	 * @return the letzterBenutzer
	 */
	public Benutzer getLetzterBenutzer() {
		return letzterbenutzer;
	}

	/**
	 * @param letzterBenutzer the letzterBenutzer to set
	 */
	public void setLetzterBenutzer(Benutzer letzterbenutzer) {
		this.letzterbenutzer = letzterbenutzer;
	}

}
