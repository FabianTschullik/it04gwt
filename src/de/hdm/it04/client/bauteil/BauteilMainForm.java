package de.hdm.it04.client.bauteil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.AdministrationCommonAsync;

public class BauteilMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public BauteilMainForm() {
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
		
		Button btnCreate = new Button("Neues Bauteil anlegen");
		btnCreate.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("content").add(new EnderzeugnisUpdateForm());
					}
				});
		
		TextBox suchen = new TextBox();
		suchen.setText("ID oder Name eingeben");
		
		Button btnsuchen = new Button("Bauteil suchen");
		btnsuchen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		Button btnAlleBauteile = new Button("Alle Bauteile anzeigen");
		btnAlleBauteile.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		
		
	
		
		
		this.add(btnCreate);
		this.add(suchen);
		this.add(btnsuchen);
		this.add(btnAlleBauteile);
	}
	
	
	

}
