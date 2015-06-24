package de.hdm.it04.client.editor;

import java.util.Vector;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.DataTree;
import de.hdm.it04.client.editor.MainViewDashboard;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Element;
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
		
		MainViewDashboard MainViewDashboard = new MainViewDashboard();
		this.hPanelDashboard.add(MainViewDashboard);
	}
	
	public void openImpressumMain() {
		this.hPanelDashboard.clear();
		ImpressumMain ImpressumMain = new ImpressumMain();
		this.hPanelDashboard.add(ImpressumMain);
	}
	
	public void openDashboard() {
		this.hPanelDashboard.clear();
		MainViewDashboard MainViewDashboard = new MainViewDashboard();
		this.hPanelDashboard.add(MainViewDashboard);

	}
	
}

	


	
	
	
	
	
	
	
