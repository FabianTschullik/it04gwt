package de.hdm.it04.client.report;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.MaterialbedarfMain;

/**
 * 
 * @author Schwab, Tschullik
 *
 */
public class MainViewReport extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	
	
	//Kontruktor
	public MainViewReport() {
		initWidget(this.vPanel);
		
		//Erstellung einer neuen MenuViewReport Instanz und Zuweisung an das VerticalPanel
		MenuViewReport menu = new MenuViewReport();
		this.vPanel.add(menu);
		
		//Erstellung eines neuen ContentPanels und Zuweisung an das VerticalPanel
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
	}
	
	//Öffnen der Seite Strukturstückliste, nicht im Konstruktor, da die Seite nicht initial geladen werden soll,
	//sondern erst mit Betätigung des Buttons.
	public void openStrukturstuecklisteMain() {
		//Löschen des Panel-Inhaltes, um nicht mehrere Seiten anzulegen
		this.vPanel.clear();
		StrukturstuecklisteMain StrukturstuecklisteMain = new StrukturstuecklisteMain();
		this.contentPanel.add(StrukturstuecklisteMain);
	}
	//Öffnen der Seite Materialbedarf.
	public void openMaterialbedarfMain() {
		//Löschen des Panel-Inhaltes, um nicht mehrere Seiten anzulegen
		this.vPanel.clear();
		MaterialbedarfMain MaterialbedarfMain = new MaterialbedarfMain();
		this.contentPanel.add(MaterialbedarfMain);
	}

}
