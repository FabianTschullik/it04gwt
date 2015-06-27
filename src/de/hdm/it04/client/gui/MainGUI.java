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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;

public class MainGUI extends Composite {
	
	static It04gwtServiceClientImpl serviceImpl;
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanelButtons = new HorizontalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	private VerticalPanel vPanelDetailsContent = new VerticalPanel();
	public VerticalPanel getvPanelDetailsContent() {
		return vPanelDetailsContent;
	}





	public void setvPanelDetailsContent(VerticalPanel vPanelDetailsContent) {
		this.vPanelDetailsContent = vPanelDetailsContent;
	}



	private HorizontalPanel hPanelDetailsButtons = new HorizontalPanel();
	private AnlegenBauteil anlegenBauteil;
	FlexTable flex = new FlexTable();
	FlexTable findBauteilTable  = new FlexTable();
	
	
	
	public void updateBauteil(Bauteil bt){
		
		
		AnlegenBauteil anlegenBauteil = new AnlegenBauteil(bt);
		this.vPanel.add(anlegenBauteil);
	}
	
	
	
	
	
	public MainGUI(It04gwtServiceClientImpl serviceImpl){
		
		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;
		
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
		
		
		
		//-----------------------------------------------------------------
		//------------------- Buttons obere Leiste ------------------------
		//-----------------------------------------------------------------
		
		Button btnBauteil = new Button("Bauteil");
		btnBauteil.addClickHandler(new BtnBauteilClickHandler());
		this.hPanelButtons.add(btnBauteil);
		
		Button btnBaugruppe = new Button("Baugruppe");
		btnBaugruppe.addClickHandler(new BtnBaugruppeClickHandler());
		this.hPanelButtons.add(btnBaugruppe);
		
		Button btnEnderzeugnis = new Button("Enderzeugnis");
		btnEnderzeugnis.addClickHandler(new BtnEnderzeugnisClickHandler());
		this.hPanelButtons.add(btnEnderzeugnis);
		
		Button btnImpressum = new Button("Impressum");
		btnImpressum.addClickHandler(new BtnImpressumClickHandler());
		this.hPanelButtons.add(btnImpressum);
		
		//-----------------------------------------------------------------
		//------------------- Ende Buttons obere Leiste ------------------------
		//-----------------------------------------------------------------
		
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
	
	

 	/**
 	 * Methode, um alle Bauteile anzeigen zu lassen in einer FlexTable
 	 * @param bauteile
 	 */
 	public void showAllBauteile(Vector<Bauteil> bauteile) {
 		
 		/**
 		 * Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
 		 */
 		FlexTable bauteileTable = new FlexTable();
 		bauteileTable.setText(0,0,"ID");
 		bauteileTable.setText(0,1,"Name");
 		bauteileTable.setText(0,2,"Beschreibung");
 		bauteileTable.setText(0,3,"Bezeichnung");	
 		bauteileTable.setText(0,4,"Erstellt am");
 		bauteileTable.setText(0,5,"Zuletzt geaendert am");
 		bauteileTable.setText(0,6,"letzter Bearbeiter");
 		bauteileTable.setText(0,7,"Edit");
 		bauteileTable.setText(0,8,"Delete");
 		
 		/**
 		 * Für jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
 		 */
 		for(int j=0; j < bauteile.size(); j++ ){
 			
 			/**
 			 * Button, um Bauteil innerhalb der Tabelle zu löschen
 			 */
 			Button btnDelete = new Button("X");
 			//btnDelete.addClickHandler(new DeleteClickHandler());
 			//this.vPanelCreate.add(btnDelete);
 			
 			
 			/**
 			 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
 			 */
 			Button editBtn = new Button("Editieren");
 			//editBtn.addClickHandler(new EditClickHandler());
 			//this.vPanelCreate.add(editBtn);
 			
 			
 			/**
 			 * Formatiert Timestamp zu String
 			 */
 			Date d1 = new Date();
 			d1 = bauteile.elementAt(j).getErstellungsDatum();
 			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
 			
 			
 			/**
 			 * Formatiert Timestamp zu String
 			 */
 			Date d2 = new Date();
 			d2 = bauteile.elementAt(j).getAenderungsDatum();
 			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
 			
 		
 			/**
 			 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
 			 */
 			bauteileTable.setText(j+1, 0, Integer.toString(bauteile.elementAt(j).getId()));
 			bauteileTable.setText(j+1, 1, bauteile.elementAt(j).getName());
 			bauteileTable.setText(j+1, 2, bauteile.elementAt(j).getBeschreibung());
 			bauteileTable.setText(j+1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
 			bauteileTable.setText(j+1, 4, s1);
 			bauteileTable.setText(j+1, 5, s2);
 
 			
 			/**
 			 * Einfügen der Buttons in die Tabelle
 			 */
 			bauteileTable.setWidget(j+1, 8, btnDelete);
 			bauteileTable.setWidget(j+1, 7, editBtn);
 			
 			
 			/**
 			 * Verknüpfung zu style.css
 			 */
 			bauteileTable.setCellPadding(6);
 			bauteileTable.getRowFormatter().addStyleName(0,  "watchListHeader");
 			bauteileTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
 			bauteileTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
 		}	
 		
 		/**
 		 * Bauteil-Tabelle zum Panel hinzugefügt damit das Ganze auch angezeigt wird 
 		 */
 		this.vPanelDetails.add(bauteileTable);
 		
 		/**
 		 * Buttons
 		 */
 		Button btnNeu = new Button("Neu");
 		//btnNeu.addClickHandler(new NeuClickHandler());
 		this.hPanelButtons.add(btnNeu);
 		
 		Button btnEdit = new Button("Bearbeiten");
 		//btnEdit.addClickHandler(new EditClickHandler());
 		this.hPanelButtons.add(btnEdit);
 		
 		Button btnDelete = new Button("Loeschen");
 		//btnDelete.addClickHandler(new DeleteClickHandler());
 		this.hPanelButtons.add(btnDelete);
 		
 		Button btnAbbrechen = new Button("Abbrechen");
 		//btnAbbrechen.addClickHandler(new AbbrechenClickHandler());
 		this.hPanelButtons.add(btnAbbrechen);
 		
 		Button btnUpdate = new Button("Aktualisieren");
 		//btnUpdate.addClickHandler(new UpdateClickHandler());
 		this.hPanelButtons.add(btnUpdate);
 		
 		//this.hPanelMain.add(vPanelFlexTable);
 		//this.vPanelMain.add(vPanelCreate);
 		//this.vPanelFlexTable.add(hPanelButtons);
 		this.vPanelDetails.add(bauteileTable);
 	}

	
 	//-------------------------------------------------------
 	//----------------Anzeigen des Bauteils(FlexTable)-------
 	//-------------------------------------------------------
 	
 	public void showBauteil(Bauteil bt){
 		
 		this.vPanelDetailsContent.clear();
		this.flex.clear();

		HTML topic = new HTML("<h2>Detailansicht Bauteil</h2>");

		this.vPanelDetailsContent.add(topic);

			Label lbl = new Label(Integer.toString(bt.getId()));
			
			this.vPanelDetailsContent.add(lbl);
 		
			
 	}
 	
 	//-------------------------------------------------------
 	 	//----------------Anzeigen des Bauteils(FlexTable)-------
 	 	//-------------------------------------------------------
 	
 	
	
			
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
	
	
	public class AnlegenBtn1ClickHandler implements ClickHandler {



		@Override
		public void onClick(ClickEvent event) {
						
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
	
	
	//-------------------------------------------------------------------------
	//--------------------- ClickHandler Buttons obere Leiste -----------------
	//-------------------------------------------------------------------------
	
	public class BtnBauteilClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanelDetailsContent.clear();
			BauteilGUI bauteilgui = new BauteilGUI(vPanelDetailsContent);
			bauteilgui.menue();
		}
	}
	
	
	public class BtnBaugruppeClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
				
		}
	}
	
	public class BtnEnderzeugnisClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
	}
	
	public class BtnImpressumClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
	}
	
	
	
	//----------------------------------------------------------------------------
	//-------------------------- Ende ClickHandler Buttons -----------------------
	//----------------------------------------------------------------------------
	
	
	public void showError(){
		//Label lbl = new Label();
		//lbl.setText("Fehler");
		//vPanelDetailsContent.clear();
		//vPanelDetailsContent.add(lbl);
		
		vPanel.clear();
	}
}
