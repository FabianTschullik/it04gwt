package de.hdm.it04.client.gui;



import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Element;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

public class MainGUI extends Composite {
	
	private It04gwtServiceClientImpl serviceImpl;
	
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	FlexTable flex = new FlexTable();
	
	
	public MainGUI(It04gwtServiceClientImpl serviceImpl){
		initWidget(this.hPanel);
		
		this.hPanel.add(vPanelTree);
		this.hPanel.add(vPanelDetails);
		this.serviceImpl = serviceImpl;
		
		this.vPanelDetails.setBorderWidth(1);
		this.vPanelTree.setBorderWidth(1);
		this.hPanel.setBorderWidth(1);
		
		Button testBtn = new Button("Test");
		testBtn.addClickHandler(new TestBtnClickHandler());
		this.vPanelDetails.add(testBtn);
		
		Label lbl = new Label("Hallo");
		this.vPanelDetails.add(lbl);
		
		TreeItem root = new TreeItem();
	    root.setText("Baugruppe");
	    
	    	TreeItem sub = new TreeItem();
	    	sub.setText("Unterbaugruppe");
	    
	    		sub.addTextItem("Bauteil 1");
	    		sub.addTextItem("Bauteil 2");
	    root.addItem(sub);
	    root.addTextItem("Bauteil 1");
	    root.addTextItem("Bauteil 2");
	    root.addTextItem("Bauteil 3");
	    
	   
	    
	    sub.addTextItem("untergruppe");
	    
	    
	    
	    Tree t = new Tree();
	    t.addItem(root);

	    
	    
	    this.vPanelTree.add(t);
		
	}
	
	
	public void showEnderzeugnisDetails() {
this.flex.clear();
		
		this.flex = new FlexTable();
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Preis");
		flex.setText(0, 3, "erstellt am");
		flex.setText(0, 4, "geändert am");
	

			/**
			 * Formatiert Timestamp zu String
			 */
			
		
		
		
		
	}
	
	public void showBaugruppeDetails() {
		
	}
	
	public void showBauteilDetails() {
		
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
		this.vPanelDetails.add(flex);
		
		
		
	
		
		
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
