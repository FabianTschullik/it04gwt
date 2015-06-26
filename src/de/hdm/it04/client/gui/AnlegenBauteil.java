package de.hdm.it04.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;

public class AnlegenBauteil extends Composite {
	
	
	private FlexTable flex;
	private Label lbl;
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	

private VerticalPanel vPanel = new VerticalPanel();
	
	private It04gwtServiceClientImpl impl;
	

	public AnlegenBauteil(Bauteil bt){
		initWidget(this.vPanel);
		
	
		
		
		updateBauteil(bt);
			
			
			
		}
	
	public void updateBauteil(Bauteil bt){
		
		Label lbl = new Label("Es geht ihr Ficker. Hier ist die BT-ID des leeren Buteils: "
				+Integer.toString(bt.getId()));
		this.vPanel.add(lbl);
		
	}

		

}
