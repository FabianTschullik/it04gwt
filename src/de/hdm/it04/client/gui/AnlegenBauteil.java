package de.hdm.it04.client.gui;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;

public class AnlegenBauteil extends Composite {
	
	private It04gwtServiceClientImpl serviceImpl;
	private VerticalPanel vPanelDetails = new VerticalPanel();
	private MainGUI maingui;
	
	
	public AnlegenBauteil(){
		initWidget(this.vPanelDetails);
		this.maingui = maingui;
				
		serviceImpl.createBauteil();
	}
	
	
	
	public void showBauteil (Bauteil bt){
		
		
		/**
 		 * Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
 		 */
 				FlexTable findBauteilTable = new FlexTable();
 				findBauteilTable.setText(0,0,"ID");
 				findBauteilTable.setText(0,1,"Name");
 				findBauteilTable.setText(0,2,"Beschreibung");
 				findBauteilTable.setText(0,3,"Bezeichnung");	
 				findBauteilTable.setText(0,4,"Erstellt am");
 				findBauteilTable.setText(0,5,"Zuletzt geaendert am");
 				findBauteilTable.setText(0,6,"letzter Bearbeiter");
 				findBauteilTable.setText(0,7,"Edit");
 				findBauteilTable.setText(0,8,"Delete");
 				
 				
 				
 					
 					/**
 					 * Formatiert Timestamp zu String
 					 */
 					Date d1 = new Date();
 					d1 = bt.getErstellungsDatum();
 					String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
 					
 					/**
 					 * Formatiert Timestamp zu String
 					 */
 					Date d2 = new Date();
 					d2 = bt.getAenderungsDatum();
 					String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
 					
 				
 					int id = bt.getId();
 					findBauteilTable.setText(1, 0, new Integer(id).toString());
 					findBauteilTable.setText(1, 1, bt.getName());
 					findBauteilTable.setText(1, 2, bt.getBeschreibung());
 					findBauteilTable.setText(1, 3, bt.getMaterialBezeichnung());
 					findBauteilTable.setText(1, 4, s1);
 					findBauteilTable.setText(1, 5, s2);
 					
 								
 					/**
 					 * Verknüpfung zu style.css
 					 */
 					findBauteilTable.setCellPadding(6);
 					findBauteilTable.getRowFormatter().addStyleName(0,  "watchListHeader");
 					findBauteilTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
 					findBauteilTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
 					
 				
 					Label lbl = new Label("Hallo");
 					this.vPanelDetails.add(lbl);
 					//this.vPanelDetails.add(findBauteilTable);	
	}
}
