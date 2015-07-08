package de.hdm.it04.client.report;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MenuBar;

import de.hdm.it04.client.editor.AlertGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.editor.Impressum;


/**
 * 
 * @author Schwab
 *
 */

public class MenuFormReport extends HorizontalPanel {
	
	AlertGUI alertGUI = new AlertGUI();

	/*
	 * Im Konstruktor werden die Widgets z.T. erzeugt. Alle werden in einem
	 * Raster angeordnet, dessen Groesse sich aus dem Platzbedarf der
	 * enthaltenen Widgets bestimmt.
	 */
	public MenuFormReport() {

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		this.add(buttonsPanel);

		Command openImpressum = new Command() {
			public void execute() {

				ContentContainer.getInstance().setContent(
						new Impressum().load());
			}
		};

		Command openSucheStrukturstueckliste = new Command() {

			@Override
			public void execute() {
				ContentContainer.getInstance().setContent(
						new StrukturstuecklisteGUI().suchen());
			}
		};

		Command openSucheMaterialbedarf = new Command() {

			@Override
			public void execute() {
				ContentContainer.getInstance().setContent(
						new MaterialbedarfGUI().suchen());
			}
		};

		MenuBar strukturstuecklisteMenu = new MenuBar(true);
		strukturstuecklisteMenu.addItem("suchen", openSucheStrukturstueckliste);

		MenuBar materialbedarfMenu = new MenuBar(true);
		materialbedarfMenu.addItem("suche", openSucheMaterialbedarf);

		// Make a new menu bar, adding a few cascading menus to it.
		MenuBar menu = new MenuBar();
		menu.addItem("Strukturstueckliste", strukturstuecklisteMenu);
		menu.addItem("Materialbedarf", materialbedarfMenu);
		menu.addItem("Impressum", openImpressum);

		buttonsPanel.add(menu);
	}
}