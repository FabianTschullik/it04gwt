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

import de.hdm.it04.client.baugruppe.BaugruppeAnlegenFrom;
import de.hdm.it04.client.baugruppe.BaugruppeMainForm;
import de.hdm.it04.client.baugruppe.BaugruppeSuchenForm;
import de.hdm.it04.client.bauteil.BauteilAnlegenForm;
import de.hdm.it04.client.bauteil.BauteilMainForm;
import de.hdm.it04.client.bauteil.BauteilSuchenForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisAnlegenForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisMainForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisSuchenForm;



public class Navigation  {

	private static Widget navigation;
	

	
	public static void load() {

		navigation = createNavigation();
		
	}

	
	
	
	private static Widget createNavigation() {
		
		
		Command cmd = new Command() {
			 public void execute() {
				 Impressum.load();
				
			       
			      }
		};
		
		
		Command openImpressum = new Command() {
			 public void execute() {
				
			        Impressum.load();
			        Alert.load("asdad", "red");
			       
		      
		      }
			      
		};
		
		
		Command openSucheEnderzeugnis = new Command() {
			 public void execute() {
				
			       EnderzeugnisSuchenForm.load();
			       
		      
		      }
			      
		};
		
		Command openSucheBaugruppe = new Command() {
			 public void execute() {
				
			       BaugruppeSuchenForm.load();
			       
		      
		      }
			      
		};
		
		
		Command openSucheBauteil = new Command() {
			 public void execute() {
				
			       BauteilSuchenForm.load();
			       
		      
		      }
			      
		};
		
		Command newEnderzeugnis = new Command() {
			 public void execute() {
				 EnderzeugnisAnlegenForm.load();
			      }
		};
		
		Command newBauteil = new Command() {
			 public void execute() {
				 BauteilAnlegenForm.load();
			      }
		};
		
		Command newBaugruppe = new Command() {
			 public void execute() {
				BaugruppeAnlegenFrom.load();
			      }
		};
		
		

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("paddedHorizontalPanel");
		
		  MenuBar bauteilMenu = new MenuBar(true);
		
		bauteilMenu.addItem("anlegen", newBauteil);
	    bauteilMenu.addItem("suchen", openSucheBaugruppe);
	    bauteilMenu.addItem("anzeigen", cmd);

	    MenuBar baugruppeMenu = new MenuBar(true);
	    baugruppeMenu.addItem("anlegen", newBaugruppe);
	    baugruppeMenu.addItem("suchen", openSucheBaugruppe);
	    baugruppeMenu.addItem("anzeigen", cmd);

	    MenuBar enderzeugnisMenu = new MenuBar(true);
	    enderzeugnisMenu.addItem("anlegen", newEnderzeugnis);
	    enderzeugnisMenu.addItem("suchen", openSucheEnderzeugnis);
	    enderzeugnisMenu.addItem("anzeigen", cmd);
	    
	    MenuBar impressumMenu = new MenuBar(true);
	    

	    // Make a new menu bar, adding a few cascading menus to it.
	    MenuBar menu = new MenuBar();
	    menu.addItem("Bauteil", bauteilMenu);
	    menu.addItem("Baugruppe", baugruppeMenu);
	    menu.addItem("Enderzeugnis", enderzeugnisMenu);
	    menu.addItem("Impressum", openImpressum);
	    
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
