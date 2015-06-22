package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.*;

/**
 * 
 * @author Schwab, Tschullik
 *
 */

public class MainViewEditor extends Composite {
	
	
	private VerticalPanel vPanelDashboard = new VerticalPanel();
	private HorizontalPanel hPanelDashboard = new HorizontalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	
	/**
	 * Konstruktor
	 */
	public MainViewEditor() {                         
		initWidget(this.vPanelDashboard);
		this.vPanelDashboard.setBorderWidth(1);
		
		Label label1 = new Label("Herzlich willkommen beim Stuecklistenmanagementsystem der IT-Projektgruppe 4.");
		this.vPanelDashboard.add(label1);
		
		
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanelDashboard.add(menu);
		
		this.vPanelDashboard.add(hPanelDashboard);
		this.hPanelDashboard.setBorderWidth(1);
		
		this.hPanelDashboard.add(vPanelTree);
		this.vPanelTree.setBorderWidth(1);
		
		this.hPanelDashboard.add(vPanelDetails);
		this.vPanelDetails.setBorderWidth(1);	
		
	}
	
	public void openImpressumMain() {
		this.hPanelDashboard.clear();
		ImpressumMain ImpressumMain = new ImpressumMain();
		this.hPanelDashboard.add(ImpressumMain);
	}
	
	public void openDashboard() {
		this.hPanelDashboard.clear();
		

	}
}

	


	
	
	
	
	
	
	
