package de.hdm.it04.client.enderzeugnis;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisZuordnenForm.getBaugruppeDetailsCallback;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;



public class EnderzeugnisAnlegenForm extends ShowCase {
	
	private String headlineText;
	private String headlineTextStyle;
	
	public EnderzeugnisAnlegenForm() {
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
		HorizontalPanel hPanel1 = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		  
		
		Label lbl1 = new Label("Name: ");
		hPanel1.add(lbl1);
		TextBox txtBox1 = new TextBox();
		hPanel1.add(txtBox1);
		
		vPanel.add(hPanel1);
		
		Label lbl2 = new Label("Preis: ");
		hPanel2.add(lbl2);
		TextBox txtBox2 = new TextBox();
		hPanel2.add(txtBox2);
		
		vPanel.add(hPanel2);
		
		Button btnAnlegen = new Button("Anlegen");
		btnAnlegen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				AdministrationCommonAsync administration = ClientsideSettings
						.getAdministration();
				
				
				//administration.createEnderzeugnis(new createEnderzeugnisCallBack);
				
				
			RootPanel.get("content").add(new EnderzeugnisZuordnenForm());
				
			}
		});
		vPanel.add(btnAnlegen);	
		this.add(vPanel);
		
	}
	
	
	class createEnderzeugnisCallBack implements AsyncCallback<Enderzeugnis> {

		@Override
		public void onFailure(Throwable caught) {
			Alert.load("Fehler bei der Erstellung", "red");
		}

		
		@Override
		public void onSuccess(Enderzeugnis result) {
			Alert.load("Enderzeugnis wurde erfolgreich angelegt", "green");
			Enderzeugnis ez = new Enderzeugnis();
			ez = result;
			
			
			
		}
	}
	
	
	}
	
	


	