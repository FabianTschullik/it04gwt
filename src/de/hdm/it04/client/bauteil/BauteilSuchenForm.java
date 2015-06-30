package de.hdm.it04.client.bauteil;



import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.client.bauteil.BauteilMainForm.GetBauteilCallback;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Bauteil;

public class BauteilSuchenForm extends ShowCase {
	
	
	TextBox txt = new TextBox();
	private String headlineText;
	private String headlineTextStyle;
	
	public BauteilSuchenForm() {
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



		

		@Override
		protected void run() {
			HorizontalPanel hPanelSearch = new HorizontalPanel();
			hPanelSearch.addStyleName("paddedHorizontalPanel");
			
			
			
			txt.setText("Name oder ID");
			txt.setVisibleLength(10);
			hPanelSearch.add(txt);

			Button btnSearch = new Button("Suchen");
			btnSearch.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
					
				String name = txt.getText();
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
					
					//administration.
					
				}
			});

			hPanelSearch.add(btnSearch);
			
			
			
			
			RootPanel.get("content").clear();
			RootPanel.get("content").add(hPanelSearch);

			
		}
		
		
		
			class GetBauteilCallback implements AsyncCallback<Vector<Bauteil>> {

			@Override
			public void onFailure(Throwable caught) {
				Alert.load("Bauteil wurde nicht gefunden", "red");
				
				
			}

			@Override
			public void onSuccess(Vector<Bauteil> result) {
				Alert.load("Bauteil wurde nicht gefunden", "green");
				
				if (result instanceof Vector) {
					
					
					/*Vector<Bauteil> bauteile = new Vector<Bauteil>();
					bauteile = (Vector<Bauteil>) result;
					RootPanel.get("content").add(new ShowBauteileForm(bauteile));*/
					
					
				

				}

			}
			
		}

	

}

