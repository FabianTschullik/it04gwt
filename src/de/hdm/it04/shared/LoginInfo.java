package de.hdm.it04.shared;

//TODO #12: add LoginInfo helper class


import java.io.Serializable;

/**
 * 
 * @author Tschullik
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(final boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(final String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(final String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return nickname;
	}

	public void setName(final String nickname) {
		this.nickname = nickname;
	}

	public void setPictureUrl(final String pictureUrl) {
		this.pictureUrl = pictureUrl;

	}

	public String getPictureUrl() {
		return pictureUrl;
	}
}
//TODO #12:> end