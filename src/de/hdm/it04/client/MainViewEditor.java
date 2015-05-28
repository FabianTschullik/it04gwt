package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainViewEditor extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel contentPanel;
	private Label label1;
	private Image titelbild;
	
	
	
	public MainViewEditor() {                         //Constructor
		initWidget(this.vPanel);
		//this.vPanel.setBorderWidth(1);
		
		MenuViewEditor menu = new MenuViewEditor(this);
		this.vPanel.add(menu);
		
		
		
		
		
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
		
		Label label1 = new Label("Herzlich willkommen beim Stücklistenmanagementsystem der IT-Projektgruppe 4.");
		this.vPanel.add(label1);
		
	
		
		
		Image titelbild = new Image("war/images/baugruppe.jpg");
		titelbild.setWidth("600px");
		this.vPanel.add(titelbild);
		
		
	}
		
		
		public void openBaugruppeMain() {
			this.vPanel.clear();
			BaugruppeMain BaugruppeMain = new BaugruppeMain(this);
			this.contentPanel.add(BaugruppeMain);
			
		}
		
		
		public void openBauteilMain() {
			this.contentPanel.clear();
			BauteilMain bauteilmain = new BauteilMain(this);
			this.contentPanel.add(bauteilmain);
		
		
		
		}
		
	}
	
	
	
	
	
	
	
	
