package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.DataTree;

import de.hdm.it04.client.editor.MainViewDashboard;

/**
 * Diese Klasse dient zur Verwaltung der Hauptansicht und gibt den Rahmen zur Anordnung der einzelnen Panels vor.
 * @author Schwab, Tschullik
 *
 */

public class MainViewEditor extends Composite {
	
	private VerticalPanel vPanelDashboard = new VerticalPanel();
	private HorizontalPanel hPanelDashboard = new HorizontalPanel();

	
	/**
	 * Konstruktor
	 */
	public MainViewEditor() {                         
		initWidget(this.vPanelDashboard);
		this.vPanelDashboard.setBorderWidth(1);
		
		Label label1 = new Label("Herzlich willkommen beim Stuecklistenmanagementsystem der IT-Projektgruppe 4.");
		this.vPanelDashboard.add(label1);
		
		/**
		 * Erzeugen einer MenuViewEditor Instanz und Zuweisung an das Hauptpanel
		 */
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanelDashboard.add(menu);
		
		this.vPanelDashboard.add(hPanelDashboard);
		this.hPanelDashboard.setBorderWidth(1);
		
		/**
		 * Erzeugen einer MainViewDashboard Instanz
		 */
		MainViewDashboard MainViewDashboard = new MainViewDashboard();
		this.hPanelDashboard.add(MainViewDashboard);
	}
	/**
	 * Methode, um das Impressum aufzurufen
	 */
	public void openImpressumMain() {
		this.hPanelDashboard.clear();
		ImpressumMain ImpressumMain = new ImpressumMain();
		this.hPanelDashboard.add(ImpressumMain);
	}
	
	/**
	 * Methode, um die Editoransicht aufzurufen
	 */
	public void openDashboard() {
		this.hPanelDashboard.clear();
		MainViewDashboard MainViewDashboard = new MainViewDashboard();
		this.hPanelDashboard.add(MainViewDashboard);

	}
}

	


	
	
	
	
	
	
	
