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

	/**
	 * Getter-Methode, die den Preis eines Enderzeugnisses zurueckgibt
	 * 
	 * @return Der Preis als Double
	 */
	public double getPreis() {
		return Preis;
	}

	/**
	 * Setter-Methode, die dem Enderzeugnis einen Preis hinzufuegt
	 * 
	 * @param preis
	 */
	public void setPreis(double preis) {
		this.Preis = preis;
	}

	/**
	 * Getter-Methode, die die zugehoerige Baugruppe zurueckgibt
	 * 
	 * @return Die Baugruppe als Integer
	 */
	public int getBaugruppe() {
		return baugruppe;
	}

	/**
	 * Setter-Methode, die dem Enderzeugnis eine Baugruppe hinzufuegt
	 * 
	 * @param baugruppe
	 */
	public void setBaugruppe(int baugruppe) {
		this.baugruppe = baugruppe;
	}
}