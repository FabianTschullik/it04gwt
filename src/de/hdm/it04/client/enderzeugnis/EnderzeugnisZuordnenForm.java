package de.hdm.it04.client.enderzeugnis;


import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;



public class EnderzeugnisZuordnenForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	
	public EnderzeugnisZuordnenForm() {
		this.headlineText = "Bitte suchen Sie eine Baugruppe, welche Sie zuordnen m�chten.";
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
	

	protected void run() {
	
		HorizontalPanel hPanelSearch = new HorizontalPanel();
		hPanelSearch.addStyleName("paddedHorizontalPanel");
		
		final TextBox txt = new TextBox();
		txt.setVisibleLength(10);
		hPanelSearch.add(txt);

		Button btnSearch = new Button("Suchen");
		btnSearch.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final int id = Integer.parseInt(txt.getText());
				AdministrationCommonAsync administration = ClientsideSettings
						.getAdministration();
				
				
				administration.getBaugruppeDetails(id, new getBaugruppeDetailsCallback());
				
				
				
			}
		});

		
	    hPanelSearch.add(btnSearch);
		this.add(hPanelSearch);
		
	}
	
	
private static Widget showZuordnenForm(Baugruppe bg) {
		
		VerticalPanel vPanel = new VerticalPanel();
		FlexTable flex = new FlexTable();

		//HTML topic = new HTML("<h3>Enderzeugnis anlegen</h3> <br>");
		

		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "erstellt am");
		flex.setText(0, 4, "geändert am");
		flex.setText(0, 5, "zu EZ zuordnen");
		
		

		/**
		 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
		 */
		
		CheckBox checkBox = new CheckBox();
		
		flex.setText(1, 0, Integer.toString(bg.getId()));
		flex.setText(1, 1, bg.getName());
		flex.setText(1, 3, bg.getBeschreibung());
		//flex.setText(1, 3, ); 
		//flex.setText(1, 4, );
		flex.setWidget(1, 5, checkBox);
		
		
		
		
		
		
		/**
		 * Verknüpfung zu style.css
		 */
		flex.getRowFormatter().addStyleName(0, "EnderzeugnisHeader");
		flex.getRowFormatter().addStyleName(0, "Row1");
		flex.getRowFormatter().addStyleName(1, "Row2");

		flex.getCellFormatter().addStyleName(0, 2, "EnderzeugnisNumericColumn");
		flex.getCellFormatter()
				.addStyleName(0, 3, "EnderzeugnistNumericColumn");
	
		
		
		
		
		
		vPanel.add(flex);
		
		Button btnSpeichern = new Button("Speichern");
		btnSpeichern.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
			
					}
				});
		
		vPanel.add(btnSpeichern);
		
		
		

		
		return vPanel;

	}
	
	
class getBaugruppeDetailsCallback implements AsyncCallback<Baugruppe> {

	@Override
	public void onFailure(Throwable caught) {
		Alert.load("Fehler in der Suche", "red");
	}

	
	@Override
	public void onSuccess(Baugruppe result) {
		Alert.load("Es wurden Daten zu Ihrer Anfrage gefunden", "green");
		Baugruppe bg = new Baugruppe();
		bg = result;
		
		RootPanel.get("content").add(showZuordnenForm(bg));
		
	}
}




	}
	
	


	