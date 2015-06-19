package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Schwab, Tschullik
 *
 */

public class MainViewEditor extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	
	/**
	 * Konstruktor
	 */
	public MainViewEditor() {                         
		initWidget(this.vPanel);
		//this.vPanel.setBorderWidth(1);
		
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanel.add(menu);
		
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
		contentPanel.setBorderWidth(1);
		
		Label label1 = new Label("Herzlich willkommen beim Stuecklistenmanagementsystem der IT-Projektgruppe 4.");
		this.contentPanel.add(label1);	
	}
	
	public void openImpressumMain() {
		this.contentPanel.clear();
		ImpressumMain ImpressumMain = new ImpressumMain();
		this.contentPanel.add(ImpressumMain);
	}
	
	public void openStuecklisteMain() {
		this.contentPanel.clear();
		StuecklisteMain StuecklisteMain = new StuecklisteMain();
		this.contentPanel.add(StuecklisteMain);
	}
}
	


	
	
	
	
	
	
	
