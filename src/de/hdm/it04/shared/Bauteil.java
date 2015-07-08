package de.hdm.it04.shared;

/**
 * Die Klasse Bauteil repräsentiert ein Bauteil und ist im <code>shared</code>
 * Package. Da die Klasse von <code>Element</code> und
 * <code>BusinessObject</code> erbt, enthält sie auch deren Attribute.
 * 
 * @author Geier, Tschullik, Voelker
 */
@SuppressWarnings("serial")
public class Bauteil extends Element {

	// Variablendeklarationen
	private String materialBezeichnung;

	/**
	 * Getter-Methode, welche die Materialbezichnung eines Bauteils zurückgibt.
	 * 
	 * @param 
	 * @return die Materialbezeichnung als String
	 */
	public String getMaterialBezeichnung() {
		return materialBezeichnung;
	}

	/**
	 * Setter-Methode, welche die Materialbezeichnung eines Bauteils setzt.
	 * 
	 * @param Materialbezeichnung
	 *            als String
	 */
	public void setMaterialBezeichnung(String materialBezeichnung) {
		this.materialBezeichnung = materialBezeichnung;
	}

}
