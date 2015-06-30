package de.hdm.it04.client.bauteil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.Alert;
import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.ShowCase;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Bauteil;



public class BauteilAnlegenForm extends ShowCase {
	
	
	private String headlineText;
	private String headlineTextStyle;
	public Bauteil btl;
	
	public BauteilAnlegenForm() {
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
		AdministrationCommonAsync administration = ClientsideSettings
				.getAdministration();	
		
		administration.createBauteil(new createBauteilCallBack());
		
	}

	
	

	private static Widget createAnlegenForm(Bauteil bt) {
		

		
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel hPanel4= new HorizontalPanel();
		HorizontalPanel hPanel1 = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		HorizontalPanel hPanel3 = new HorizontalPanel();
		
		Label lblid1 = new Label("ID: ");
		Label lblID2 = new Label();
		lblID2.setText(Integer.toString(bt.getId()));
		hPanel4.add(lblid1);
		hPanel4.add(lblID2);
		vPanel.add(hPanel4);
		
		Label lblname = new Label("Name: ");
		hPanel1.add(lblname);
		TextBox txtname = new TextBox();
		hPanel1.add(txtname);
		vPanel.add(hPanel1);
		
		
		Label lblMaterialBezeichnung = new Label("Materialbezeichnung: ");
		hPanel2.add(lblMaterialBezeichnung);
		TextBox txtBoxMaterialBezeichnung = new TextBox();
		hPanel2.add(txtBoxMaterialBezeichnung);
		vPanel.add(hPanel2);
		
		
		Label lblBeschreibung = new Label("Beschreibung: ");
		hPanel3.add(lblBeschreibung);
		TextArea txtAreaBeschreibung = new TextArea();
		hPanel3.add(txtAreaBeschreibung);
		vPanel.add(hPanel3);
		
		Button btnAnlegen = new Button("Anlegen");
		btnAnlegen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				AdministrationCommonAsync administration = ClientsideSettings
						.getAdministration();
				//bt.setName(txtname.getText());
	
				//administration.updateBauteil(bt, updateBauteilCallback);
			}
		});
		vPanel.add(btnAnlegen);
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(vPanel);
		
		return vPanel;

	}
	
	private class createBauteilCallBack implements AsyncCallback<Bauteil> {

		@Override
		public void onFailure(Throwable caught) {
			Alert.load("Fehler bei der Erstellung", "red");
		}

		
		@Override
		public void onSuccess(Bauteil result) {
			Alert.load("Enderzeugnis wurde erfolgreich angelegt", "green");
			Bauteil bt = new Bauteil();
			bt = result;
			
			BauteilAnlegenForm.createAnlegenForm(bt);
			
		}
	}


}
