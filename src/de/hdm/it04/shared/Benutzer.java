package de.hdm.it04.shared;
/**
 * Die Klasse Benutzer repräsentiert einen Benutzer und ist im <code>shared</code> Package.
 * Da die Klasse von <code>BusinessObject</code> erbt, 
 * enthält sie auch deren Attribute.
 */
public class Benutzer extends BusinessObject {
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}