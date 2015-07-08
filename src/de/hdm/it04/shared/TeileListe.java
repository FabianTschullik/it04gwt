package de.hdm.it04.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Die KlasseTeileListe repräsentiert eine TeileListe und ist im
 * <code>shared</code> Package.
 * 
 * @author Voelker
 *
 */
public class TeileListe implements IsSerializable {

	private int anzahl;
	private int id;

	/**
	 * Getter-Methode, die die Anzahl der Teileliste zurueckgibt 
	 * @return Die Anzahl als Integer
	 */
	public int getAnzahl() {
		return anzahl;
	}

	/**
	 * Setter-Methode die der Teileliste eine Anzahl hinzufuegt
	 * @param anzahl
	 */
	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	/**
	 * Getter-Methode die ID als Integer zurueckgibt
	 * @return Die ID als Integer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter-Methode die der Teileliste eine eindeutige ID hinzufuegt
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}