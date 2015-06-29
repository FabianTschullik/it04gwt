package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.Baugruppe;

public class StrukturstuecklisteSearchResultForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public StrukturstuecklisteSearchResultForm() {
		this.headlineText = "Bitte Daten eintragen.";
		this.headlineTextStyle = "formTitle";
	}
	

	protected String getHeadlineText() {
		return this.headlineText;
	}

	
	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
	}
	
	protected void run() {

	}
	
	private static FlexTable showInsertForm(Baugruppe bg) {
		
		
		FlexTable flex = new FlexTable();

		HTML topic = new HTML("<h3>Baugruppe auswaehlen</h3> <br>");
		

		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "erstellt am");
		flex.setText(0, 3, "geändert am");
		flex.setText(0, 4, "Auswählen");
		flex.setText(0, 5, "Erzeugen");
		

		Button btnErzeugen = new Button("Erzeugen");
		btnErzeugen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
			
					}
				});
		
		/**
		 * Timestamp wird für die Tabelle formatiert.
		 * 
		 */

		
		
		/**
		 * Verknüpfung zu style.css
		 */
		flex.getRowFormatter().addStyleName(0, "BaugruppeHeader"); //BEZEICHNUNG?
		flex.getRowFormatter().addStyleName(0, "Row1");
		flex.getRowFormatter().addStyleName(1, "Row2");

		flex.getCellFormatter().addStyleName(0, 2, "BaugruppeNumericColumn"); //BEZEICHNUNG?
		flex.getCellFormatter()
				.addStyleName(0, 3, "BaugruppetNumericColumn"); //BEZEICHNUNG?
		
		return flex;
	}

}
