package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainViewEditor extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	private Label label1;
	private Label label2;
	private Image abaugruppeMain;
	
	
	
	public MainViewEditor() {                         //Constructor
		initWidget(this.vPanel);
		//this.vPanel.setBorderWidth(1);
		
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanel.add(menu);
		
		
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
		
		
					Label label1 = new Label("Herzlich willkommen beim Stuecklistenmanagementsystem der IT-Projektgruppe 4.");
		this.contentPanel.add(label1);
		
		

		
	}
	
	public void openBaugruppeMain() {
		this.contentPanel.clear();
		BaugruppeMain BaugruppeMain = new BaugruppeMain(this);
		this.contentPanel.add(BaugruppeMain);
	}	
	
	public void openImpressumMain() {
		this.vPanel.clear();
		ImpressumMain ImpressumMain = new ImpressumMain();
		this.contentPanel.add(ImpressumMain);
	}
	
	public void openStuecklisteMain() {
		this.vPanel.clear();
		StuecklisteMain StuecklisteMain = new StuecklisteMain();
		this.contentPanel.add(StuecklisteMain);
	}
}
	
	
	
	
	
	
	
	
