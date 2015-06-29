package de.hdm.it04.client.baugruppe;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisUpdateForm;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public class BaugruppeMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public BaugruppeMainForm() {
		this.headlineText = "Welche Aktion wollen Sie durchfuehren?";
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
		
		Button btnCreate = new Button("Neue Baugruppe anlegen");
		btnCreate.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new BaugruppeUpdateForm());
					}
				});
		
		TextBox suchen = new TextBox();
		suchen.setText("ID oder Name eingeben");
		
		Button btnsuchen = new Button("Baugruppe suchen");
		btnsuchen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		Button btnAlleBaugruppen = new Button("Alle Baugruppen anzeigen");
		btnAlleBaugruppen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		
		
	
		
		
		this.add(btnCreate);
		this.add(suchen);
		this.add(btnsuchen);
		this.add(btnAlleBaugruppen);
	}
	
	
}



