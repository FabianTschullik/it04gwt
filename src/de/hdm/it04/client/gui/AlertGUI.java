package de.hdm.it04.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;

public class AlertGUI extends MainGUI {

	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();

	public AlertGUI(VerticalPanel vPanel) {
		super(serviceImpl);
		this.vPanel = vPanel;
	}

	public void load(String text, String color) {

		Label lbl = new Label();

		lbl.setText(text);

		if (color == "red") {

			lbl.setStyleName("alertRed");
		}

		else {

			if (color == "green") {

				lbl.setStyleName("alertGreen");
			}

		}
		
		hPanel.add(lbl);
		
		Button btn = new Button("Close");
		btn.addClickHandler(new BtnClickHandler());
		
		vPanel.add(hPanel);
		
		hPanel.add(btn);
		this.vPanel.add(hPanel);
		
		
		
	}
	
	public class BtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
		
			
		}
	}
}
