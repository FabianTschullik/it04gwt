package de.hdm.it04.shared.bo;

public class Bauteil extends Element {
	
	private String materialbzeichnung;
	
	// test123
	
	private int teilNummer;

	/**
	 * @return the materialbzeichnung
	 */
	public String getMaterialbzeichnung() {
		return materialbzeichnung;
	}

	/**
	 * @param materialbzeichnung the materialbzeichnung to set
	 */
	public void setMaterialbzeichnung(String materialbzeichnung) {
		this.materialbzeichnung = materialbzeichnung;
	}

	/**
	 * @return the teilNummer
	 */
	public int getTeilNummer() {
		return teilNummer;
	}

	/**
	 * @param teilNummer the teilNummer to set
	 */
	public void setTeilNummer(int teilNummer) {
		this.teilNummer = teilNummer;
	}
	
	

}
