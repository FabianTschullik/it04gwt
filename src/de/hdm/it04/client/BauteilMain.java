package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BauteilMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private Image BauteilMain;
	public BauteilMain(MainViewEditor main) {
		
		initWidget(this.vPanel);
		this.main = main;
		
		//Label landscape1 =new Label("Landscape1");
		
		Image BauteilMain = new Image("/war/images/baugruppe.jpg");
		BauteilMain.setWidth("600px");
		this.vPanel.add(BauteilMain);
	
			

}





	private void BauteilMain() {
		
		main.openBauteilMain();
	}
}
