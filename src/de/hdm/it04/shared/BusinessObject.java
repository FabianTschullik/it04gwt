package de.hdm.it04.shared;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Die abstrakte Klasse BusinessObject repraesentiert ein BusinessObject und ist
 * im <code>shared</code> Package. Die Klasse wird benoetigt, da sie alle
 * gemeinsamen Attribute enthaelt (auch die Attribute der Klasse Benutzer,
 * welches kein Element darstellt und deshalb nicht von der Klasse Element erben
 * kann).
 * 
 * Die Klasse BusinessObject implementiert Serializable, damit Objekte
 * uebergeben werden koennen. IsSerializable wandelt Objekte in Binaercode um,
 * damit die Kommunikation zwischen Client und Server funktioniert.
 * 
 * @author Maehler, Voelker
 */
@SuppressWarnings("serial")
public abstract class BusinessObject implements IsSerializable {

	// Variablendeklarationen
	private int id;
	private Timestamp erstellungsDatum;
	private Timestamp aenderungsDatum;
	private String letzterBearbeiter;

	public String getLetzterBearbeiter() {
		return letzterBearbeiter;
	}

	public void setLetzterBearbeiter(String letzterBearbeiter) {
		this.letzterBearbeiter = letzterBearbeiter;
	}

	/**
	 * Getter-Methode, welche die ID eines BusinessObjects (bzw. der geerbten
	 * Klassen) zurueckgibt.
	 * 
	 * @return die ID als Integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter-Methode, welche die ID eines BusinessObjects (bzw. die ID der
	 * geerbten Klassen) setzt.
	 * 
	 * @param id
	 *            als Integer
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter-Methode, welche das Erstellungsdatum (bzw. das Erstellungsdatum
	 * der geerbten Klassen) zurueckgibt.
	 * 
	 * @return das Erstellungsdatum als Timestamp
	 */
	public Timestamp getErstellungsDatum() {
		return erstellungsDatum;
	}

	/**
	 * Setter-Methode, welche die das Erstellungsdatum setzt.
	 * 
	 * @param erstellungsDatum
	 *            als Integer
	 */
	public void setErstellungsDatum(Timestamp erstellungsDatum) {
		this.erstellungsDatum = erstellungsDatum;
	}

	/**
	 * Getter-Methode, welche das Aenderungsdatum (bzw. das Aenderungsdatum der
	 * geerbten Klassen) zurückgibt.
	 * 
	 * @return das Aenderungsdatum als Timestamp
	 */
	public Timestamp getAenderungsDatum() {
		return aenderungsDatum;
	}

	/**
	 * Setter-Methode, welche das Aenderungsdatum eines BusinessObjects (bzw.
	 * das Aenderungsdatum der geerbten Klassen) setzt.
	 * 
	 * @param aenderungsDatum
	 *            als Timestamp
	 */
	public void setAenderungsDatum(Timestamp aenderungsDatum) {
		this.aenderungsDatum = aenderungsDatum;
	}
}