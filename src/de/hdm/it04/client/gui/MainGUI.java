package de.hdm.it04.client.gui;



import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Element;
import de.hdm.it04.shared.Bauteil;

public class MainGUI extends Composite {
	
	private It04gwtServiceClientImpl serviceImpl;
	private VerticalPanel vPanel = new VerticalPanel();
	FlexTable flex = new FlexTable();
	
	
	public MainGUI(It04gwtServiceClientImpl serviceImpl){
		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;
		
		Button testBtn = new Button("Test");
		testBtn.addClickHandler(new TestBtnClickHandler());
		this.vPanel.add(testBtn);
		
		Label lbl = new Label("Hallo");
		this.vPanel.add(lbl);
		
	}
	
	
	public void showConnectedBauteil(Vector<Bauteil> elemente){
		
		
this.flex.clear();
		
		this.flex = new FlexTable();
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Materialezeichnung");
		flex.setText(0, 3, "Beschreibung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		
		/**
		 * Für jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		 */
		for(int j=0; j < elemente.size(); j++ ){
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			Date d1 = new Date();
			d1 = elemente.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			Date d2 = new Date();
			d2 = elemente.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
			
		
			/**
			 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
			 */
			flex.setText(j+1, 0, Integer.toString(elemente.elementAt(j).getId()));
			flex.setText(j+1, 1, elemente.elementAt(j).getName());
			flex.setText(j+1, 2, elemente.elementAt(j).getMaterialBezeichnung());
			flex.setText(j+1, 3, elemente.elementAt(j).getBeschreibung());
			flex.setText(j+1, 4, s1);
			flex.setText(j+1, 5, s2);


			
		}
		

		/**
		 * Verknüpfung zu style.css
		 */
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0,  "watchListHeader");
		flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		this.vPanel.add(flex);
		
		
	}
	
public void error(){
		
	
	//Label lbl2 = new Label("fehler");
	//this.vPanel.add(lbl2);
		
	}
	
	
	
	
	private class TestBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			

		serviceImpl.findConnectedBauteileByKey(1);
		
		
		
		}
		
		
		
	}
	
	
	

}
