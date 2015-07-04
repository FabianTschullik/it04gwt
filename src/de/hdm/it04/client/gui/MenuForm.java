package de.hdm.it04.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.it04.client.editor.It04gwtEditor;
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Bauteil;

public class MenuForm extends HorizontalPanel {

	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	AlertGUI alertGUI = new AlertGUI();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf der enthaltenen
	 * Widgets bestimmt.
	 */
	public MenuForm() {

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);

		Command cmd = new Command() {
			public void execute() {

			}
		};

		Command openImpressum = new Command() {
			public void execute() {
				

			}

		};

		Command openSucheEnderzeugnis = new Command() {
			public void execute() {

			}

		};

		Command openSucheBaugruppe = new Command() {
			public void execute() {

			}

		};

		Command openSucheBauteil = new Command() {
			public void execute() {
				

			}

		};

		Command newEnderzeugnis = new Command() {
			public void execute() {

			}
		};

		Command newBauteil = new Command() {
			public void execute() {

				sms.createBauteil(new AsyncCallback<Bauteil>() {

					@Override
					public void onFailure(Throwable caught) {

						alertGUI.load(
								"Leeres Bauteil konnte nicht geladen werden",
								"red");

					}

					@Override
					public void onSuccess(Bauteil result) {

						ContentContainer.getInstance().setContent(
								new BauteilGUI().updateBauteil(result));
						

					}
				});

			}
		};

		Command newBaugruppe = new Command() {
			public void execute() {

			}
		};

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

		buttonsPanel.add(menu);

	}
}
