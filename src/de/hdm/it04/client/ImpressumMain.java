package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Diese Klasse enthält das Impressum.
 * Das Impressum wird zur eindeutigen Identifizierung der Website nach §5 TMG benötigt.
 *  
 * @author Benjamin
 */

public class ImpressumMain extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	
	public ImpressumMain() {
		initWidget(this.vPanel);
		this.main = main;

		HTML html = new HTML(
				"<h1>Impressum nach §5 TMG</h1>"
						+ "<p><b>IT-Projekt Sommersemster 2015</b></p>"
						+ "<p><b>Studiengang:</b></p>"
						+ "<p>Wirtschaftsinformatik und digitale Medien</p><br>"
						+ "<p><b>Betreuende Professoren:</b></p>"
						+ "<p>Prof. Dr. Peter Thies, Prof. Dr. Christian Rathke</p><br>"
						+ "<p><b>Team 04</p></b>"
						+ "<p>Florin Geier, Christian Maehler, Friedrich Schneider, Benjamin Schwab, Fabian Tschullik, Manuel Voelker</p><br>"
						+ "<h2>Kontaktdaten</h2>" + "<table>" + "<tr>"
						+ "<td>Adresse:</td>"
						+ "<td>Nobelstraße 8-10<br>70569 Stuttgart</td>"
						+ "</tr>" + "<tr>" + "<td>Tel:</td>"
						+ "<td>0711 8923 10</td>" + "</tr>" + "<tr>"
						+ "<td>E-Mail:</td>" + "<td>fg047@hdm-stuttgart.de<br>"
						+ "cm086@hdm-stuttgart.de<br>"
						+ "fs088@hdm-stuttgart.de<br>"
						+ "bs079@hdm-stuttgart.de<br>"
						+ "ft027@hdm-stuttgart.de<br>"
						+ "mv045@hdm-stuttgart.de<br>" + "</td>" + "</tr>"
						+ "</table>");

		this.vPanel.add(html);
	}
	 
}