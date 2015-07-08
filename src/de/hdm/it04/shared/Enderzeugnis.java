package de.hdm.it04.shared;

/**
 * Die Klasse Bauteil repräsentiert ein Enderzeugnis und ist im
 * <code>shared</code> Package. Da die Klasse von <code>Element</code> und
 * <code>BusinessObject</code> erbt, enthält sie auch deren Attribute.
 * 
 * @author Tschullik
 */
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