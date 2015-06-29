package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.it04.client.ShowCase;

public class MaterialbedarfMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public MaterialbedarfMainForm() {
		this.headlineText = "Suchen Sie ein Enderzeugnis und lassen Sie sich die Materialbedarfsliste erzeugen";
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