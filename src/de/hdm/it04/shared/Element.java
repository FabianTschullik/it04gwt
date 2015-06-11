package de.hdm.it04.shared;

public class Element extends BusinessObject{
	
	//Variablendeklarationen
	private String name;
	private String beschreibung;

	//getter setter Methoden
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}


}
