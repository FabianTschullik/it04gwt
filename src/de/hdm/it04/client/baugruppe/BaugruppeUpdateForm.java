package de.hdm.it04.client.baugruppe;

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
import de.hdm.it04.shared.Baugruppe;

public class BaugruppeUpdateForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public BaugruppeUpdateForm() {
		this.headlineText = "Bitte Daten eintratgen.";
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

		administration.createBaugruppe(new CreateBaugruppeCallback());
	}

	class CreateBaugruppeCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			Alert.load("Leere Baugruppe konnte nicht angelegt werden", "red");
		}

		@Override
		public void onSuccess(Object result) {
			Baugruppe bg = new Baugruppe();
			Alert.load("Leere Baugruppe wurde angelegt", "green");
			RootPanel.get("content").add(BaugruppeUpdateForm.showInsertForm(bg));
		}
	}
	
	private static FlexTable showInsertForm(Baugruppe bg) {
		
		
		FlexTable flex = new FlexTable();

		HTML topic = new HTML("<h3>Enderzeugnis anlegen</h3> <br>");
		

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

		Button btnSpeichern = new Button("Speichern");
		btnSpeichern.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
			
					}
				});
		
		
		Button btnLoeschen = new Button("Löschen");
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
		TextBox preis = new TextBox();
		
		
		flex.setText(1, 0, Integer.toString(bg.getId()));
		flex.setWidget(1, 1, name);
		flex.setWidget(1, 2, preis);
		//flex.setText(1, 3, ); 
		//flex.setText(1, 4, geaendertams);
		flex.setWidget(1, 5, btnSpeichern);
		flex.setWidget(1, 6, btnLoeschen);
		
		
		
		
		
		/**
		 * Verknüpfung zu style.css
		 */
		flex.getRowFormatter().addStyleName(0, "EnderzeugnisHeader");
		flex.getRowFormatter().addStyleName(0, "Row1");
		flex.getRowFormatter().addStyleName(1, "Row2");

		flex.getCellFormatter().addStyleName(0, 2, "EnderzeugnisNumericColumn");
		flex.getCellFormatter()
				.addStyleName(0, 3, "EnderzeugnistNumericColumn");
		
		

		
		return flex;

	}
	
}
