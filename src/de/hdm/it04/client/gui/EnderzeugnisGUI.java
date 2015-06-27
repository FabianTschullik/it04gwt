package de.hdm.it04.client.gui;

import java.security.Timestamp;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Enderzeugnis;

public class EnderzeugnisGUI extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();

	private It04gwtServiceClientImpl serviceImpl;
	private Label lbl;
	private FlexTable flex = new FlexTable();
	private TextBox name = new TextBox();
	private TextBox preis = new TextBox();
	private MainGUI main;

	public EnderzeugnisGUI(It04gwtServiceClientImpl serviceImpl) {

		this.serviceImpl = serviceImpl;
		initWidget(this.vPanel);

	}

	public void showInsertForm(Enderzeugnis ez) {

		HTML topic = new HTML("<h3>Enderzeugnis anlegen</h3>");
		this.vPanel.add(topic);

		RootPanel.get().add(topic);

		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Preis");
		flex.setText(0, 3, "erstellt am");
		flex.setText(0, 4, "geändert am");
		flex.setText(0, 5, "speichern");
		flex.setText(0, 6, "X");

		/**
		 * Anlegen der Buttons für speichern und löschen.
		 * 
		 */

		Button btnSpeichern = new Button("speichern");
		btnSpeichern.addClickHandler(new BtnSpeichernClickHandler());

		Button btnLoeschen = new Button("X");
		btnLoeschen.addClickHandler(new BtnLoeschenClickHandler());

		/**
		 * Timestamp wird für die Tabelle formatiert.
		 * 
		 */

		Date erstelltam = new Date();
		erstelltam = ez.getErstellungsDatum();
		String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(
				erstelltam);

		Date geaendertam = new Date();
		erstelltam = ez.getAenderungsDatum();
		String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
				geaendertam);

		/**
		 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
		 */
		flex.setText(1, 0, Integer.toString(ez.getId()));
		flex.setWidget(1, 1, name);
		flex.setWidget(1, 2, preis);
		flex.setText(1, 3, erstelltams);
		flex.setText(1, 4, geaendertams);
		flex.setWidget(1, 5, btnSpeichern);
		flex.setWidget(1, 6, btnLoeschen);

		/**
		 * Verknüpfung zu style.css
		 */
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0, "EnderzeugnisHeader");
		flex.getCellFormatter().addStyleName(0, 2, "EnderzeugnisNumericColumn");
		flex.getCellFormatter()
				.addStyleName(0, 3, "EnderzeugnistNumericColumn");

		/**
		 * Anzeigen
		 */
		RootPanel.get().add(flex);

	}
	
	/**
	 * Meldung, wenn Bauteil erfolgreich angelegt wurde.
	 */
	public void showSuccess() {

		Window.alert("Bauteil wurde erfolgreich angelegt");
	}
	
	/**
	 * Methode um Enderzeugnis anzuzeigen und zu bearbeiten.
	 */
	
	public void showEnderzeugnis(Enderzeugnis ez) {
		
		
		
	}
	
	
	
	
	
	
	
	
	

	/**
	 * CkickHandler
	 */

	public class BtnSpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Enderzeugnis ez = new Enderzeugnis();

			ez.setId(Integer.parseInt(flex.getText(1, 0)));
			ez.setName(name.getText());
			ez.setPreis(44.0);
			ez.setPreis(Double.parseDouble(preis.getText()));

			serviceImpl.updateEnderzeugnis(ez);

		}
	}

	public class BtnLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

		}
	}

}
