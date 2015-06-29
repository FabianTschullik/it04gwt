package de.hdm.it04.client.enderzeugnis;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.AdministrationCommonAsync;

public class EnderzeugnisMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public EnderzeugnisMainForm() {
		this.headlineText = "Bitte wählen Sie aus, was Sie anlegen möchten!";
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
		
		Button btnCreate = new Button("New Enderzeugnis");
		btnCreate.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						RootPanel.get("content").add(new EnderzeugnisUpdateForm());
					}
				});
		
		Button btnEdit = new Button("Edit Enderzeugnis");
		btnEdit.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		Button btnConnect = new Button("Connect to Baugruppe");
		btnConnect.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						//RootPanel.get("main").add(new EnderzeugnisForm(new Element(),1));
					}
				});
		
		
		
	
		
		
		this.add(btnCreate);
		this.add(btnEdit);
		this.add(btnConnect);
	}
	
	
	

}
