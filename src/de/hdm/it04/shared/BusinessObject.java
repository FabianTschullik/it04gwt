package de.hdm.it04.shared;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.api.server.spi.types.DateAndTime;

public abstract class  BusinessObject implements Serializable {
	
	private Long  erstellungsZeit;
	private Timestamp aenderungsZeit;


private int id;
	
	

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

	public Long getErstellungsZeit() {
		return erstellungsZeit;
	}

	public void setErstellungsZeit(Long erstellungsZeit) {
		this.erstellungsZeit = erstellungsZeit;
	}

	public Timestamp getAenderungsZeit() {
		return aenderungsZeit;
	}

	public void setAenderungsZeit(Timestamp aenderungsZeit) {
		this.aenderungsZeit = aenderungsZeit;
	}
}


	
