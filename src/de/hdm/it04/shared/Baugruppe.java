package de.hdm.it04.shared;

import java.util.Vector;

/**
 * Die Klasse Baugruppe repräsentiert eine Baugruppe und ist im <code>shared</code> Package.
 * Da die Klasse von <code>Element</code> und <code>BusinessObject</code> erbt, 
 * enthält sie auch deren Attribute.
 */
@SuppressWarnings("serial")
public class Baugruppe extends Element {
	
		// Stückliste von Baugruppe mit all seinen Unterbaugruppen und Bauteilen
		public Vector<TeileListe> connectedBauteile = new Vector<TeileListe>();
		public Vector<TeileListe> connectedBaugruppen = new Vector<TeileListe>();
	}

