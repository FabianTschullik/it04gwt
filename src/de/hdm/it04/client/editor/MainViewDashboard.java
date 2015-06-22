package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Diese Klasse dient der Anordnung der Panels für die Baumstruktur und das Detailfenster
 * @author Schwab
 *
 */

public class MainViewDashboard extends Composite {
	
	private HorizontalPanel hPanelDashboardView = new HorizontalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	
	/**
	 * Konstruktor
	 */
	public MainViewDashboard(){
		initWidget(this.hPanelDashboardView);
		this.hPanelDashboardView.setBorderWidth(1);
		
		this.hPanelDashboardView.add(vPanelTree);
		this.hPanelDashboardView.add(vPanelDetails);
		
		/**
		 * Erzeugen einer DataTree Instanz
		 */
		DataTree DataTree = new DataTree();
		this.vPanelTree.add(DataTree);
		
		/**
		 * Erzeugen einer Details Instanz
		 */
		Details Details = new Details();
		this.vPanelDetails.add(Details);
		
		
	}

}
