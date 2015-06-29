package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.baugruppe.BaugruppeMainForm;
import de.hdm.it04.client.bauteil.BauteilMainForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisMainForm;


public class Navigation  {

	private static Widget navigation;
	

	
	public static void load() {

		navigation = createNavigation();
		
	}

	
	
	
	private static Widget createNavigation() {
		
		
		Command cmd = new Command() {
			 public void execute() {
			        Window.alert("You selected a menu item!");
			      }
		};
		

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("paddedHorizontalPanel");
		
		  MenuBar bauteilMenu = new MenuBar(true);
		
		bauteilMenu.addItem("anlegen", cmd);
	    bauteilMenu.addItem("suchen", cmd);
	    bauteilMenu.addItem("anzeigen", cmd);

	    MenuBar baugruppeMenu = new MenuBar(true);
	    baugruppeMenu.addItem("anlegen", cmd);
	    baugruppeMenu.addItem("suchen", cmd);
	    baugruppeMenu.addItem("anzeigen", cmd);

	    MenuBar enderzeugnisMenu = new MenuBar(true);
	    enderzeugnisMenu.addItem("anlegen", cmd);
	    enderzeugnisMenu.addItem("suchen", cmd);
	    enderzeugnisMenu.addItem("anzeigen", cmd);
	    
	    MenuBar impressumMenu = new MenuBar(true);
	    

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("Bauteil", bauteilMenu);
	    menu.addItem("Baugruppe", baugruppeMenu);
	    menu.addItem("Enderzeugnis", enderzeugnisMenu);
	    menu.addItem("Impressum", cmd);
	    
	    hPanel.add(menu);
		

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
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new BaugruppeMainForm());
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
