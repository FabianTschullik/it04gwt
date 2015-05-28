package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainViewEditor extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	
	
	
	
	public MainViewEditor() {                         //Constructor
		initWidget(this.vPanel);
		this.vPanel.setBorderWidth(1);
		
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanel.add(menu);
		
		
		
		
		
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
		
		Label label1 = new Label("Herzlich willkommen beim Stuecklistenmanagementsystem der IT-Projektgruppe 4.");
		this.contentPanel.add(label1);
		
		
		
	}
		
		
		public void openBaugruppeMain() {
			this.contentPanel.clear();
			BaugruppeMain BaugruppeMain = new BaugruppeMain();
			this.contentPanel.add(BaugruppeMain);
			
		}
		
		
		public void openBauteilMain() {
			this.contentPanel.clear();
			BauteilMain bauteilmain = new BauteilMain();
			this.contentPanel.add(bauteilmain);
		
		
		
		}
		
	}
	
	
	
	
	
	
	
	
