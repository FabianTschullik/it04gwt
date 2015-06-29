package de.hdm.it04.client.bauteil;

import java.util.Vector;

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
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

public class BauteilMainForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	TextBox suchen = new TextBox();
	Bauteil bt = new Bauteil();
	
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
		
		
		suchen.setText("ID oder Name eingeben");
		
		Button btnsuchen = new Button("Bauteil suchen");
		btnsuchen.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						
						String name = suchen.getText();
						if (name.matches("[0-9]+")){
							int id = Integer.parseInt(name);
							AdministrationCommonAsync administration = ClientsideSettings
									.getAdministration();

							administration.getBauteil(id, new GetBauteilCallback());
							
							
							
							
						}
						
						else {
							AdministrationCommonAsync administration = ClientsideSettings
									.getAdministration();
							
							administration.getBauteilByName(name, new GetBauteilCallback());
							
							
						}
						
						
						RootPanel.get("content").clear();
						
					}
				});
		
		Button btnAlleBauteile = new Button("Alle Bauteile anzeigen");
		btnAlleBauteile.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						RootPanel.get("content").clear();
						AdministrationCommonAsync administration = ClientsideSettings
								.getAdministration();

						administration.getAllBauteile(new GetBauteilCallback());
						
						
					}
				});
		
		
		
	
		
		
		this.add(btnCreate);
		this.add(suchen);
		this.add(btnsuchen);
		this.add(btnAlleBauteile);
	}
	
	
	
	class GetBauteilCallback implements AsyncCallback<Vector<Bauteil>> {

		@Override
		public void onFailure(Throwable caught) {
			Alert.load("Bauteil wurde nicht gefunden", "red");
			
		}

		@Override
		public void onSuccess(Vector<Bauteil> result) {
		
			
			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				RootPanel.get("main").add(new ShowBauteileForm(bauteile));
				
				
			

			}

		}
	}

	
	
	
	
	
	
	
	
	
}
	
	




	
	


