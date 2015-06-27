package de.hdm.it04.client.gui;

import java.util.Date;

import com.google.gwt.editor.client.impl.RootEditorContext;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.gui.MainGUI.AbbrechenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.AnlegenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.BearbeitenBtn1ClickHandler;
import de.hdm.it04.client.gui.MainGUI.LoeschenBtn1ClickHandler;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends MainGUI {
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	
	FlexTable flex = new FlexTable();
	
	private VerticalPanel vPanel = new VerticalPanel();
	
	
	public BauteilGUI(VerticalPanel vPanel){
		
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
	
	
	
	
	public void menue (){
		
		/**
		 * Anlegen der Buttons fürs Anlegen, Bearbeiten, Löschen und Abbrechen
		 */

		Button AnlegenBtn = new Button("Neu");
		AnlegenBtn.addClickHandler(new AnlegenBtnClickHandler());
		this.vPanel.add(AnlegenBtn);

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
	
	
	public void updateBauteil(Bauteil bt){
		
		HTML topic = new HTML("<h2>Detailansicht Baugruppe</h2>");

		this.vPanel.add(topic);
		
		

				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "erstellt am");
				flex.setText(0, 4, "geändert am");
				
				
				
			
				
					
					/**
					 * Formatiert Timestamp zu String
					 */
				/*
					Date d1 = new Date();
					d1 = bt.getErstellungsDatum();
					String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
					
					*/
					/**
					 * Formatiert Timestamp zu String
					 */
				
				/*
					Date d2 = new Date();
					d2 = bt.getAenderungsDatum();
					String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
					*/
				
					/**
					 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
					 */
				
					flex.setText(1, 0, Integer.toString(bt.getId()));
					flex.setText(1, 1, bt.getName());
					flex.setText(1, 2, bt.getBeschreibung());
					//flex.setText(1, 3, s1);
					//flex.setText(1, 4, s2);
				
				
					//this.vPanel.add(flex);
					
					/**
					 * Verknüpfung zu style.css
					 */
				
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanel.add(flex);
					
			}
	
	
	
	public class AnlegenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			serviceImpl.createBauteil();
				
		}
	}
	

		

}

