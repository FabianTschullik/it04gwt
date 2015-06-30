package de.hdm.it04.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.baugruppe.BaugruppeAnlegenForm;
import de.hdm.it04.client.baugruppe.BaugruppeSuchenForm;
import de.hdm.it04.client.bauteil.BauteilAnlegenForm;
import de.hdm.it04.client.bauteil.BauteilSuchenForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisAnlegenForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisSuchenForm;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.Enderzeugnis;

public class Navigation {

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
				RootPanel.get("content").add(new EnderzeugnisAnlegenForm());

				
			}
		};

		Command newBauteil = new Command() {
			public void execute() {
				BauteilAnlegenForm.load();
			}
		};

		Command newBaugruppe = new Command() {
			public void execute() {
				BaugruppeAnlegenForm.load();
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

		RootPanel.get("topmenu").add(hPanel);

		return hPanel;

	}
	
	
	

}
