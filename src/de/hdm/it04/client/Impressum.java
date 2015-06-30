package de.hdm.it04.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Impressum {

	private static HTML impressum;

	public static void load() {

		impressum = createImpressum();
	}

	private static HTML createImpressum() {
		final HTML html = new HTML(
				"<h2>Impressum nach §5 TMG</h2>"
						+ "<p><b>IT-Projekt Sommersemster 2015</b></p>"
						+ "<p><b>Studiengang:</b></p>"
						+ "<p>Wirtschaftsinformatik und digitale Medien</p><br>"
						+ "<p><b>Betreuende Professoren:</b></p>"
						+ "<p>Prof. Dr. Peter Thies, Prof. Dr. Christian Rathke</p><br>"
						+ "<p><b>Team 04</p></b>"
						+ "<p>Florin Geier, Christian Maehler, Benjamin Schwab, Fabian Tschullik, Manuel Voelker</p><br>"
						+ "<h2>Kontaktdaten</h2>" + "<table>" + "<tr>"
						+ "<td>Adresse:</td>"
						+ "<td>Nobelstraße 8-10<br>70569 Stuttgart</td>"
						+ "</tr>" + "<tr>" + "<td>Tel:</td>"
						+ "<td>0711 8923 10</td>" + "</tr>" + "<tr>"
						+ "<td>E-Mail:</td>" + "<td>fg047@hdm-stuttgart.de<br>"
						+ "cm086@hdm-stuttgart.de<br>"
						+ "bs079@hdm-stuttgart.de<br>"
						+ "ft027@hdm-stuttgart.de<br>"
						+ "mv045@hdm-stuttgart.de<br>" + "</td>" + "</tr>"
						+ "</table>");

		html.setStyleName("impressum");
		RootPanel.get("content").clear();
		RootPanel.get("content").add(html);

		return html;
	}

}