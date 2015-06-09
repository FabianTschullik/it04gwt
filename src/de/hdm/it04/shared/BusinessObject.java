package de.hdm.it04.shared;

import java.io.Serializable;

public abstract class  BusinessObject implements Serializable {


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
}


	
