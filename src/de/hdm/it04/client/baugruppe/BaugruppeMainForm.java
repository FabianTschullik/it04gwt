package de.hdm.it04.client.baugruppe;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel hPanelSearch = new HorizontalPanel();
		
		Button btnCreate = new Button("Baugruppe anlegen");
		btnCreate.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new BaugruppeUpdateForm());
					}
				});
		vPanel.add(btnCreate);
		
		TextBox suchen = new TextBox();
		suchen.setText("ID oder Name eingeben");
		hPanelSearch.add(suchen);
		
		Button btnsuchen = new Button("Baugruppe suchen");
		btnsuchen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		hPanelSearch.add(btnsuchen);
		hPanelSearch.addStyleName("paddedHorizontalPanel");
		
		vPanel.add(hPanelSearch);
		Button btnAlleBaugruppen = new Button("Alle Baugruppen anzeigen");
		btnAlleBaugruppen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		vPanel.add(btnAlleBaugruppen);
		
		
		
	
		
		
		this.add(vPanel);
		
	}
	
	
}



