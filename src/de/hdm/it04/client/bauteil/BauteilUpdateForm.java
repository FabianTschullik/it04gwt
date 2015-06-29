package de.hdm.it04.client.bauteil;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;


public class BauteilUpdateForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public BauteilUpdateForm() {
		this.headlineText = "Bitte Daten eintragen.";
		this.headlineTextStyle = "formTitle";
	}

	@Override
	protected String getHeadlineText() {
		return this.headlineText;
	}

	@Override
	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
	}

	@Override
	protected void run() {

		AdministrationCommonAsync administration = ClientsideSettings
				.getAdministration();
		

		//administration.getBauteil(id, new GetBauteilCallback());
		
		

	}

	class GetBauteilCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			Alert.load("Leeres Bauteil konnte nicht angelegt werden", "red");
		}

		@Override
		public void onSuccess(Object result) {
			Bauteil bt = new Bauteil();
			Alert.load("Leeres Bauteil wird angezeigt", "green");
			RootPanel.get("content").add(BauteilUpdateForm.showInsertForm(bt));
			

		}

	}
	
	private static FlexTable showInsertForm(Bauteil bt) {
		
		
		FlexTable flex = new FlexTable();

		HTML topic = new HTML("<h3>Enderzeugnis anlegen</h3> <br>");
		

		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "Materialbezeichnung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		flex.setText(0, 6, "speichern");
		flex.setText(0, 7, "X");
		
		
		/**
		 * Anlegen der Buttons für speichern und löschen.
		 * 
		 */

		Button btnSpeichern = new Button("Speichern");
		btnSpeichern.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
			
					}
				});
		
		
		Button btnLoeschen = new Button("Loeschen");
		btnLoeschen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
			
					}
				});
		
		/**
		 * Timestamp wird für die Tabelle formatiert.
		 * 
		 */

	
		
		
		/**
		 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
		 */
		
		TextBox name = new TextBox();
		TextBox materialBezeichnung = new TextBox();
		TextBox beschreibung = new TextBox();
		name.setText(bt.getName());
		beschreibung.setText(bt.getBeschreibung());
		materialBezeichnung.setText(bt.getMaterialBezeichnung());
		
		
		
		flex.setText(1, 0, Integer.toString(bt.getId()));
		flex.setWidget(1, 1, name);
		flex.setWidget(1, 2, beschreibung);
		flex.setWidget(1, 3, materialBezeichnung); 
		//flex.setText(1, 4, geaendertams);
		flex.setWidget(1, 6, btnSpeichern);
		flex.setWidget(1, 7, btnLoeschen);
		
		
		
		
		
		/**
		 * Verknüpfung zu style.css
		 */
		flex.getRowFormatter().addStyleName(0, "EnderzeugnisHeader");
		flex.getRowFormatter().addStyleName(0, "Row1");
		flex.getRowFormatter().addStyleName(1, "Row2");

		flex.getCellFormatter().addStyleName(0, 2, "EnderzeugnisNumericColumn");
		flex.getCellFormatter()
				.addStyleName(0, 3, "BauteiltNumericColumn");
		
		

		
		return flex;

	}
	
}
