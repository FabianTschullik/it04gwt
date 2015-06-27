package de.hdm.it04.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.gui.MainGUI.AbbrechenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.AnlegenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.BearbeitenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.LoeschenBtn1ClickHandler;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends Composite {
	
	private FlexTable flex;
	private Label lbl;
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	

	private VerticalPanel vPanel = new VerticalPanel();
	
	private It04gwtServiceClientImpl impl;
	
	public BauteilGUI(){
		
		/**
		 * Anlegen der Buttons fürs Anlegen, Bearbeiten, Löschen und Abbrechen
		 */
		Button AnlegenBtn1 = new Button("Neu");
		//AnlegenBtn1.addClickHandler(new AnlegenBtn1ClickHandler());
		this.vPanel.add(AnlegenBtn1);
		
		Button BearbeitenBtn1 = new Button("Bearbeiten");
		//BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.vPanel.add(BearbeitenBtn1);
	  
		Button LoeschenBtn1 = new Button("Loeschen");
		//LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.vPanel.add(LoeschenBtn1);	
		
	}
	

	public BauteilGUI(Bauteil bt){
		initWidget(this.vPanel);
		
	
		
		
		updateBauteil(bt);
			
			
			
		}
	
	public void updateBauteil(Bauteil bt){
		
		Label lbl = new Label("Es geht ihr Ficker. Hier ist die BT-ID des leeren Buteils: "
				+Integer.toString(bt.getId()));
		this.vPanel.add(lbl);
		
	}

		

}

