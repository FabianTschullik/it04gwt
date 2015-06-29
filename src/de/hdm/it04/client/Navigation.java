package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.bauteil.BauteilMainForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisMainForm;


public class Navigation  {

	private static Widget navigation;
	

	
	public static void load() {

		navigation = createNavigation();
	}

	private static Widget createNavigation() {

		HorizontalPanel hPanel = new HorizontalPanel();

		Button btnBauteil = new Button("Bauteil");
		btnBauteil.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new BauteilMainForm());
				
			}
		});
		hPanel.add(btnBauteil);

		Button btnBaugruppe = new Button("Baugruppe");
		btnBaugruppe.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
			}
		});
		hPanel.add(btnBaugruppe);

		Button btnEnderzeugnis = new Button("Enderzeugnis");
		btnEnderzeugnis.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new EnderzeugnisMainForm());
				
			}
		});
		hPanel.add(btnEnderzeugnis);

		Button btnImpressum = new Button("Impressum");
		btnImpressum.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Impressum.load();
				
			}
		});
		hPanel.add(btnImpressum);
		
		Button btnTest = new Button("Test");
		btnTest.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Impressum.load();
			}
		});
		hPanel.add(btnTest);

		RootPanel.get("topmenu").add(hPanel);

		return hPanel;

	}

}
