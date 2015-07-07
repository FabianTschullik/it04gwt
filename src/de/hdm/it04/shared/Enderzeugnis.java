package de.hdm.it04.shared;

public class Enderzeugnis extends Element {
	
	private double Preis;
	private int baugruppe;

	public double getPreis() {
		return Preis;
	}

	public void setPreis(double preis) {
		this.Preis = preis;
	}
		
	public int getBaugruppe() {
		return baugruppe;
	}

	public void setBaugruppe(int baugruppe) {
		this.baugruppe = baugruppe;
	}
}