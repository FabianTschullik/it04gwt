package de.hdm.it04.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends MainGUI {
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	
	FlexTable flex = new FlexTable();
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public BauteilGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
	
	
	public void menue(){
		
		
		HTML topic = new HTML("<h2>Was wollen Sie mit dem Bauteil tun?</h2>");

		this.vPanel.add(topic);
		
		

		Button AnlegenBtn = new Button("Anlegen");
		AnlegenBtn.addClickHandler(new AnlegenBtnClickHandler());
		this.hPanel.add(AnlegenBtn);

		Button BearbeitenBtn1 = new Button("Bearbeiten");
		//BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.hPanel.add(BearbeitenBtn1);
	  
		Button LoeschenBtn1 = new Button("Loeschen");
		//LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.hPanel.add(LoeschenBtn1);	
		
		this.vPanel.add(hPanel);
		
	}
	
	
	public void updateBauteil(Bauteil bt){
		
				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "erstellt am");
				flex.setText(0, 4, "geändert am");
				
				
				
				/**
				 * Timestamp wird für die Tabelle formatiert.
				 * 
				 */
				/*
				Date erstelltam = new Date();
				erstelltam = bt.getErstellungsDatum();
				//String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(
					//	erstelltam);
				
				
				

				Date geaendertam = new Date();
				geaendertam = bt.getAenderungsDatum();
				String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
						geaendertam);
*/
					
					
					/**
					 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
					 */
				
					flex.setText(1, 0, Integer.toString(bt.getId()));
					flex.setText(1, 1, bt.getName());
					flex.setText(1, 2, bt.getBeschreibung());
					flex.setText(1, 3, null);
					//flex.setText(1, 4, geaendertams);
				
				
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

