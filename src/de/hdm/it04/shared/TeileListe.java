package de.hdm.it04.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TeileListe implements Serializable {

	private int anzahl;
	private int id;
	

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
