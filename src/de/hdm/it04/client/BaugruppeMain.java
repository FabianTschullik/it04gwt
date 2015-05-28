package de.hdm.it04.client;


import com.google.gwt.user.client.ui.Composite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BaugruppeMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	//private MainViewEditor main;
	
	public BaugruppeMain() {
		
		initWidget(this.vPanel);
		//this.main = main;
		

		Label label1 =new Label("Hallo BaugruppeMain");
		this.vPanel.add(label1);
			

}


}
