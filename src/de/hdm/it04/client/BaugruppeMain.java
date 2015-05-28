package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BaugruppeMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private Image BaugruppeMain;
	public BaugruppeMain(MainViewEditor main) {
		
		initWidget(this.vPanel);
		this.main = main;
		
		//Label landscape1 =new Label("Landscape1");
		
		Image BaugruppeMain = new Image("/war/images/baugruppe.jpg");
		BaugruppeMain.setWidth("600px");
		this.vPanel.add(BaugruppeMain);
	
			

}





	private void BaugruppeMain() {
		
		main.openBaugruppeMain();
	}
}
