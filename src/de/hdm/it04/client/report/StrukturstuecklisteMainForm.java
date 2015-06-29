package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.ShowCase;

public class StrukturstuecklisteMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public StrukturstuecklisteMainForm() {
		this.headlineText = "Suchen Sie eine Baugruppe und lassen Sie sich die Strukturstueckliste erzeugen";
		this.headlineTextStyle = "formTitle";
	}
	

	protected String getHeadlineText() {
		return this.headlineText;
	}

	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
	}
	
	protected void run() {
		
		TextBox suche = new TextBox();
		
		
		Button btnSuche = new Button("Suchen");
		btnSuche.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
					//	RootPanel.get("content").add(new EnderzeugnisUpdateForm());
					}
				});
	
		this.add(suche);
		this.add(btnSuche);
	}
}