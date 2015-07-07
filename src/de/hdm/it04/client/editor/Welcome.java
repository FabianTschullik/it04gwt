package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Die Klasse Welcome zeigt einen Begruessungstext auf der Startseite
 * @author Tschullik, Maehler
 *
 */
public class Welcome {
	
	private VerticalPanel vPanel = new VerticalPanel();

	public HTML load() {

		HTML html = new HTML(
				"<br>"
				+ "</br>"
				+ "<br>"
				+ "</br>"
				+ "<p></p>"
				+ "<center>"
				+ "<span style='color:#000000'>"
				+ "<b>"
				+ "<p style='font-family:courier'>Herzlich Willkommen zu dem Stücklisten Management System.</b>"
				+ "</p>"
				+ "<b>"
				+ "<i>"
				+ "<p style='font-family:courier'>Is it me you looking for?</b></i>"
				+ "</p>"
				+ "<b>"
				+ "<p style='font-family:courier'>Das innovative System der Maschinen!</b></p>"
				+ "</span style='color:#6E6E6E'>"
				+ "</center>");	
		return html;
	}
}
