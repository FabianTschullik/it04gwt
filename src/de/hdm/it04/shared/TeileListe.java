package de.hdm.it04.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class TeileListe implements IsSerializable {

	
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
