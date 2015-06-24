package de.hdm.it04.client.gui;



import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;




public class MainGUI extends Composite {
	
	private It04gwtServiceClientImpl serviceImpl;
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanelButtons = new HorizontalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	private VerticalPanel vPanelDetailsContent = new VerticalPanel();
	private HorizontalPanel hPanelDetailsButtons = new HorizontalPanel();
	FlexTable flex = new FlexTable();
	
	
	public MainGUI(It04gwtServiceClientImpl serviceImpl){
		initWidget(this.vPanel);
		
		this.vPanel.add(hPanelButtons);
		this.vPanel.add(hPanel);
		this.hPanel.add(vPanelTree);
		this.hPanel.add(vPanelDetails);
		this.vPanelDetails.add(hPanelDetailsButtons);
		this.vPanelDetails.add(vPanelDetailsContent);
		
		
		this.serviceImpl = serviceImpl;
		
		this.vPanelDetails.setBorderWidth(1);
		this.vPanelTree.setBorderWidth(1);
		this.hPanel.setBorderWidth(1);
		this.vPanelDetails.add(hPanelDetailsButtons);
		
		Button editorBtn = new Button("Editor");
		editorBtn.addClickHandler(new editorBtnClickHandler());
		this.hPanelButtons.add(editorBtn);
		
		Button impressumBtn = new Button("Impressum");
		impressumBtn.addClickHandler(new impressumBtnClickHandler());
		this.hPanelButtons.add(impressumBtn);
		
		
		Button testBtn = new Button("Test");
		testBtn.addClickHandler(new TestBtnClickHandler());
		this.hPanelButtons.add(testBtn);
		
		
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
	    
	    
		/**
		 * Anlegen der Buttons fürs Anlegen, Bearbeiten, Löschen und Abbrechen
		 */
		Button AnlegenBtn1 = new Button("Neu");
		AnlegenBtn1.addClickHandler(new AnlegenBtn1ClickHandler());
		this.hPanelDetailsButtons.add(AnlegenBtn1);
		
		Button BearbeitenBtn1 = new Button("Bearbeiten");
		BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.hPanelDetailsButtons.add(BearbeitenBtn1);
	  
		Button LoeschenBtn1 = new Button("Loeschen");
		LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.hPanelDetailsButtons.add(LoeschenBtn1);	
		
		Button AbbrechenBtn1 = new Button("Abbrechen");
		AbbrechenBtn1.addClickHandler(new AbbrechenBtn1ClickHandler());
		this.hPanelDetailsButtons.add(AbbrechenBtn1);
		
		
		this.vPanelDetails.add(hPanelDetailsButtons);
		
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
	
		
		flex.setText(0, 2, "Materialezeichnung");
		flex.setText(0, 3, "Beschreibung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
			
		}
		
	public void showBaugruppeDetails(Baugruppe bg) {
		
		this.vPanelDetailsContent.clear();
		this.flex.clear();
				

		HTML topic = new HTML("<h2>Detailansicht Baugruppe</h2>");

		this.vPanelDetailsContent.add(topic);

				this.flex = new FlexTable();
				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "erstellt am");
				flex.setText(0, 4, "geändert am");
				
			
					
					
					/**
					 * Formatiert Timestamp zu String
					 */
					Date d1 = new Date();
					d1 = bg.getErstellungsDatum();
					String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
					
					
					/**
					 * Formatiert Timestamp zu String
					 */
					Date d2 = new Date();
					d2 = bg.getAenderungsDatum();
					String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
					
				
					/**
					 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
					 */
					flex.setText(1, 0, Integer.toString(bg.getId()));
					flex.setText(1, 1, bg.getName());
					flex.setText(1, 2, bg.getBeschreibung());
					flex.setText(1, 3, s1);
					flex.setText(1, 4, s2);
				
					this.vPanelDetailsContent.add(flex);
					
					/**
					 * Verknüpfung zu style.css
					 */
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanelDetailsContent.add(flex);	
			}
		
	public void showBauteilDetails(Bauteil bt) {
		this.vPanelDetailsContent.clear();
		this.flex.clear();
		

		HTML topic = new HTML("<h2>Detailansicht Bauteil</h2>");

		this.vPanelDetailsContent.add(topic);

		this.flex = new FlexTable();
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Materialezeichnung");
		flex.setText(0, 3, "Beschreibung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		
	
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
			
		
			/**
			 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
			 */
			flex.setText(1, 0, Integer.toString(bt.getId()));
			flex.setText(1, 1, bt.getName());
			flex.setText(1, 2, bt.getMaterialBezeichnung());
			flex.setText(1, 3, bt.getBeschreibung());
			flex.setText(1, 4, s1);
			flex.setText(1, 5, s2);
		
			this.vPanelDetailsContent.add(flex);
			
			/**
			 * Verknüpfung zu style.css
			 */
			flex.setCellPadding(6);
			flex.getRowFormatter().addStyleName(0,  "watchListHeader");
			flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
			flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
			this.vPanelDetailsContent.add(flex);
			
		
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
		this.vPanelDetailsContent.add(flex);
		
	}
	
	
	public void error(){
		
	
	//Label lbl2 = new Label("fehler");
	//this.vPanel.add(lbl2);
		
	}
	
	
	
	
	private class TestBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			

		//serviceImpl.findConnectedBauteileByKey(1);

			//serviceImpl.findConnectedBaugruppe(2);

			//serviceImpl.getBauteilDetails(2);
			vPanelDetailsContent.clear();
			serviceImpl.getBaugruppeDetails(1);

		
		}	


		
		
		}
		
		
		

	public class AnlegenBtn1ClickHandler implements ClickHandler {



		@Override
		public void onClick(ClickEvent event) {
						vPanelDetailsContent.clear();
						Anlegen neu = new Anlegen(vPanelDetailsContent);
						vPanelDetailsContent.add((IsWidget)neu);
						
		}	
	}
	
	public class BearbeitenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
	}
	
	public class LoeschenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
		}
	}
	
	public class AbbrechenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		
		}
	}
	
	public class editorBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		
		}
	}
	
	public class impressumBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
	}
}
