package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Schwab
 *
 */

public class MaterialbedarfMain extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewReport main;
	
	//Konstruktor
	public MaterialbedarfMain() {
		initWidget(this.vPanel);
		this.main = main;
		
		Label label1 = new Label("Hallo MaterialbedarfMain");
		this.vPanel.add(label1);
		
		Button BerechnenBtn1 = new Button("Berechnen");
		BerechnenBtn1.addClickHandler(new BerechnenBtn1ClickHandler());
		vPanel.add(BerechnenBtn1);
	}

	
	private class BerechnenBtn1ClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
}
