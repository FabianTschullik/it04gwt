package de.hdm.it04.client.bauteil;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.shared.AdministrationCommonAsync;



public class BauteilAnlegenForm {
	
	
	private static Widget anlegen;

	public static void load() {

		anlegen = createAnlegenForm();
	}

	private static Widget createAnlegenForm() {
		
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel hPanel1 = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		HorizontalPanel hPanel3 = new HorizontalPanel();
		
		
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
				
	
				//administration.
			}
		});
		vPanel.add(btnAnlegen);
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(vPanel);
		
		return vPanel;

	}
}
