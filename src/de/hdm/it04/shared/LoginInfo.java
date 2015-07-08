package de.hdm.it04.shared;

//TODO #12: add LoginInfo helper class

import java.io.Serializable;

/**
 * Die Klasse LoginInfo wird fuer den Login benoetigt und ist im
 * <code>shared</code> Package.
 * 
 * @author Tschullik
 * @author Quelle http://www.sw-engineering-candies.com/blog-1/howtogetuserinformationwithoauth2inagwtandgoogleappenginejavaapplication
 *
 */

public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean loggedIn = false;

	private String loginUrl;

	private String logoutUrl;

	private String emailAddress;

	private String nickname;

	private String pictureUrl;

	/**
	 * Die Methode ueberprueft, ob der User am System angemeldet ist oder nicht.
	 * 
	 * @return loggedIn als boolean
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * Setter-Methode, die den erfolgreichen Login vermerkt.
	 * 
	 * @param loggedIn
	 */
	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * Getter-Methode, die die Google LoginUrl zurueckgibt.
	 * 
	 * @return loginUrl als String
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * Setter-Methode, die die Google LoginUrl setzt.
	 * 
	 * @param loginUrl
	 */
	public void setLoginUrl(final String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * Getter-Methode, die die Google LogoutUrl zurueckgibt.
	 * 
	 * @return logoutUrl als String
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * Setter-Methode, die die Google LogoutUrl setzt.
	 * 
	 * @param logoutUrl
	 */
	public void setLogoutUrl(final String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * Getter-Methode, die die E-Mail Adresse des Users zurueckgibt
	 * 
	 * @return emailAddress als String
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Setter-Methode, die die E-Mail Adresse des Users setzt.
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Getter-Methode die den Login Nickname des Users zurueckgibt
	 * 
	 * @return nickname als String
	 */
	public String getName() {
		return nickname;
	}

	/**
	 * Setter-Methode die den Nickname des Users setzt.
	 * 
	 * @param nickname
	 */
	public void setName(final String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Setter-Methode, die den Pfad zum User-Picture setzt.
	 * 
	 * @param pictureUrl
	 */
	public void setPictureUrl(final String pictureUrl) {
		this.pictureUrl = pictureUrl;

	}

	/**
	 * Getter-Methode die den Pfad zum User-Picture zurueckgibt.
	 * 
	 * @return pictureUrl als String
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}
}
// TODO #12:> end