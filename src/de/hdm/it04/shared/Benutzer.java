package de.hdm.it04.shared;

/**
 * Die Klasse Benutzer repräsentiert einen Benutzer und ist im
 * <code>shared</code> Package. Da die Klasse von <code>BusinessObject</code>
 * erbt, enthält sie auch deren Attribute.
 * 
 * @author Tschullik
 */
public class Benutzer extends BusinessObject {

	private String email;

	/**
	 * E-mail Adresse des Benutzers wird eingelesen.
	 * 
	 * @return E-Mail Adresse des Benutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * E-mail Adresse des Benutzers wird gesetzt
	 * 
	 * @param email
	 *            als String
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}