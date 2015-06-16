package de.hdm.it04.shared;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Die abstrakte Klasse BusinessObject repräsentiert ein BusinessObject und ist im
 * <code>shared</code> Package. Die Klasse wird benötigt, da sie alle
 * gemeinsamen Attribute enthält (auch die Attribute der Klasse Benutzer, welches
 * kein Element darstellt und deshalb nicht von der Klasse Element erben kann).
 * 
 * Die Klasse BusinessObject implementiert Serializable, damit Objekte übergeben werden
 * können. Serializable wandelt Objekte in Binärcode um, damit die Kommunikation
 * zwischen Client und Server funktioniert.
 */
@SuppressWarnings("serial")
public abstract class BusinessObject implements Serializable {

	// Variablendeklarationen
	private int id;
	private Timestamp erstellungsDatum;
	private Timestamp aenderungsDatum;

	/**
	 * Getter-Methode, welche die ID eines BusinessObjects (bzw. der geerbten
	 * Klassen) zurückgibt.
	 * 
	 * @param void
	 * @return die ID als Integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter-Methode, welche die ID eines BusinessObjects (bzw. die ID der
	 * geerbten Klassen) setzt.
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter-Methode, welche das Erstellungsdatum (bzw. das Erstellungsdatum
	 * der geerbten Klassen) zurückgibt.
	 * 
	 * @param void
	 * @return das Erstellungsdatum als Timestamp
	 */
	public Timestamp getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Setter-Methode, welche die ID eines BusinessObjects (bzw. die ID der
	 * geerbten Klassen) setzt.
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * Getter-Methode, welche das Änderungsdatum (bzw. das Änderungsdatum
	 * der geerbten Klassen) zurückgibt.
	 * 
	 * @param void
	 * @return das Änderungsdatum als Timestamp
	 */
	public Timestamp getAenderungsDatum() {
		return aenderungsDatum;
	}

	/**
	 * Setter-Methode, welche das Änderungsdatum eines BusinessObjects (bzw. das 
	 * Änderungsdatum der geerbten Klassen) setzt.
	 * 
	 * @param Änderungsdatum als Timestamp
	 * @return void
	 */
	public void setAenderungsDatum(Timestamp aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}
}
