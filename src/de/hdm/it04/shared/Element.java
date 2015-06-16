package de.hdm.it04.shared;

/**
 * Die abstrakte Klasse Element repräsentiert ein Element und ist im
 * <code>shared</code> Package. Da die Klasse von <code>BusinessObject</code>
 * erbt, enthält sie auch deren Attribute. Die Klasse wird benötigt, da sie alle
 * gemeinsamen Attribute enthält.
 */
public abstract class Element extends BusinessObject {

	// Variablendeklarationen
	private String name;
	private String beschreibung;

	/**
	 * Getter-Methode, welche den Namen eines Elements (bzw. der geerbten
	 * Klassen) zurückgibt.
	 * 
	 * @param void
	 * @return der Name des Elements als String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter-Methode, welche den Namen eines Elements (bzw. der Name der
	 * geerbten Klassen) setzt.
	 * 
	 * @param Name
	 *            als String
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter-Methode, welche die Beschreibung eines Elements (bzw. der geerbten
	 * Klassen) zurückgibt.
	 * 
	 * @param void
	 * @return die Beschreibung des Elements als String
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * Setter-Methode, welche die Beschreibung eines Elements (bzw. die
	 * Beschreibung der geerbten Klassen) setzt.
	 * 
	 * @param Beschreibung
	 *            als String
	 * @return void
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

}
