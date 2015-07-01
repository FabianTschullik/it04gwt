package de.hdm.it04.client.gui;







import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;
import java.util.Vector;

import de.hdm.it04.client.gui.BaugruppeGUI.BtnAbbrechenClickHandler;
import de.hdm.it04.client.gui.BaugruppeGUI.BtnSpeichernClickHandler;
import de.hdm.it04.client.gui.BaugruppeGUI.BtnSuchenClickHandler;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends MainGUI {
	
	/**
	 * lokal Instanz eines Bauteils
	 */
	
	Bauteil bt = new Bauteil();
	Vector<Bauteil> btV = new Vector<Bauteil>();
	FlexTable bauteileTable = new FlexTable();
	
	/**
	 * TextBox zum Suchen von Bauteilen
	 */
	
	TextBox suchen = new TextBox();
	TextBox textBoxSuchen = new TextBox();
	
	/**
	 * TextBoxe, in denen Name, Materialbezeichnung und Beschreibung
	 * hinzugef�gt und ge�ndert werden k�nnen
	 */
	
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextArea beschreibung = new TextArea();
	
	/**
	 * FlexTable zum anzeigen der Bauteile/ des Bauteils
	 */
	
	FlexTable flex = new FlexTable();
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public BauteilGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
	/**
	 * Bauteil Men�
	 * @return gibt nichts zur�ck, da alles gleich dem vPanel hinzugef�gt wird
	 */
	
	public void menue(){
		
		btV = null;
		bt= null;
		bauteileTable.removeAllRows();
		
		
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML("<h2>Was wollen Sie mit dem Bauteil tun?</h2>");
		
		/**
		 * vPanel wird dem HTML Bereich zugeordnet
		 */

		this.vPanel.add(topic);
		
		/**
		 * Men� Buttons um weiter Aktivit�ten f�r Bauteil zu w�hlen
		 */
		
		Button AnlegenBtn = new Button("Anlegen");
		AnlegenBtn.addClickHandler(new AnlegenBtnClickHandler());
		this.hPanel.add(AnlegenBtn);

		
		suchen.setText("id oder Name");
		this.hPanel.add(suchen);
		
		Button SuchenBtn = new Button("Bauteil suchen");
		SuchenBtn.addClickHandler(new SuchenBtnClickHandler());
		this.hPanel.add(SuchenBtn);	
		
		Button ShowAllBtn1 = new Button("Alle Bauteile anzeigen");
		ShowAllBtn1.addClickHandler(new ShowAllBtn1ClickHandler());
		this.hPanel.add(ShowAllBtn1);	
		
		this.vPanel.add(hPanel);
		
	}
	
	
	/**
	 * Anlegen eines Bauteils bzw. F�llung des Leeren Bauteils 
	 * Spalten Name, Beschreibung und Materialbezeichnung sind leer
	 * @param bt (leeres Objekt bzw. ohne Name, Beschreibung und Materialbezeichnung)
	 */
	
	public void updateBauteil(Bauteil bauteil){
		
		this.bt = bauteil;
			
		
		// Create a table to layout the form options
	  FlexTable layout = new FlexTable();
	  layout.setCellSpacing(6);
	  FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
	  
	  Button btnSpeichern = new Button("Speichern");
		btnSpeichern.addClickHandler(new SpeichernBtnClickHandler());
		
		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new BtnAbbrechenClickHandler());
		
	  
	  if (bauteil.getName() == null){

	  // Add a title to the form
	  layout.setHTML(0, 0, "<h3>Bauteil anlegen<h3>");
	  cellFormatter.setColSpan(0, 0, 2);
	  cellFormatter.setHorizontalAlignment(
	      0, 0, HasHorizontalAlignment.ALIGN_CENTER);
			
		
	  // Add some standard form options
	  layout.setHTML(1, 0, "ID");
	  layout.setText(1, 1, Integer.toString(bt.getId()));
	  layout.setHTML(2, 0, "Name");
	  layout.setWidget(2, 1, name);
	  layout.setHTML(3, 0, "Beschreibung");
	  layout.setWidget(3, 1, beschreibung);
	  layout.setHTML(4, 0, "Materialbezeichnung");
	  layout.setWidget(4, 1, materialBezeichnung);
	  //layout.setWidget(4, 2, btnSuchen);
	  layout.setWidget(5, 0, btnSpeichern);
	  layout.setWidget(5, 1, btnAbbrechen);
	  
	  
	  }
	  else{
		  // Add a title to the form
		  layout.setHTML(0, 0, "<h3>Bauteil �ndern<h3>");
		  cellFormatter.setColSpan(0, 0, 2);
		  cellFormatter.setHorizontalAlignment(
		      0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		  name.setText(bt.getName());
		  beschreibung.setText(bt.getBeschreibung());
		  materialBezeichnung.setText(bt.getMaterialBezeichnung());
			
		  // Add some standard form options
		  layout.setHTML(1, 0, "ID");
		  layout.setText(1, 1, Integer.toString(bt.getId()));
		  layout.setHTML(2, 0, "Name");
		  layout.setWidget(2, 1, name);
		  layout.setHTML(3, 0, "Beschreibung");
		  layout.setWidget(3, 1, beschreibung);
		  layout.setHTML(4, 0, "MaterialBezeichnung");
		  layout.setWidget(4, 1, materialBezeichnung);
		  //layout.setWidget(4, 2, btnSuchen);
		  layout.setWidget(5, 0, btnSpeichern);
		 
		  
	  }
	  
	  
	  
	  
	  

	  // Wrap the content in a DecoratorPanel
	  DecoratorPanel decPanel = new DecoratorPanel();
	  decPanel.setWidget(layout);
	  
	  this.vPanel.add(decPanel);

			}
		



			

			
/**
 * Ver�ndern eines schon vorhanden Objekts		
 * @param bt Vollst�ndiges Bauteil wird �bergeben
 * Daten werden in die jeweiligen Felder eingef�llt. 
 * Name, Beschreibung und Materialbezeichnung werden in TextBoxen eingef�gt,
 * damit sie ver�nderbar sind.
 */
	

		

/**
 * Ein Bauteil wird angezeigt					
 * @param bt
 */
	
	
public void getBauteil(Vector<Bauteil> bauteile){
	
	/**
	 * ID wird lokal gepseichert, 
	 * falls das Objekt ge�ndert werden soll
	 */
		bauteileTable.removeAllRows();
		this.btV= bauteile;	
		
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
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		 */
		for(int j=0; j < btV.size(); j++ ){
			
			/**
			 * Button, um Bauteil innerhalb der Tabelle zu löschen
			 */
			Button loeschenBtn = new Button("X");
			loeschenBtn.addClickHandler(new LoeschenBtnClickHandler());
			//this.vPanelCreate.add(btnDelete);
			
			
			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
			 */
			Button aendernBtn = new Button("Editieren");
			aendernBtn.addClickHandler(new AendernBtnClickHandler());
			//this.vPanelCreate.add(editBtn);
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			/*Date d1 = new Date();
			d1 = bauteile.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);*/
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			/*Date d2 = new Date();
			d2 = bauteile.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);*/
			
		
			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			bauteileTable.setText(j+1, 0, Integer.toString(bauteile.elementAt(j).getId()));
			bauteileTable.setText(j+1, 1, btV.elementAt(j).getName());
			bauteileTable.setText(j+1, 2, btV.elementAt(j).getBeschreibung());
			bauteileTable.setText(j+1, 3, btV.elementAt(j).getMaterialBezeichnung());
			//bauteileTable.setText(j+1, 4, s1);
			//bauteileTable.setText(j+1, 5, s2);

			
			/**
			 * Einfuegen der Buttons in die Tabelle
			 */
			bauteileTable.setWidget(j+1, 8, loeschenBtn );
			bauteileTable.setWidget(j+1, 7, aendernBtn);
			
			
			/**
			 * Verknuepfung zu style.css
			 */
			bauteileTable.setCellPadding(6);
			bauteileTable.getRowFormatter().addStyleName(0,  "watchListHeader");
			bauteileTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
			bauteileTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		}	
		
		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
		 */
		this.vPanel.add(bauteileTable);
	}

/**
 * Anzeigen von allen Bauteilen, die in der DB enthalten sind
 * @param Vektor mit Bauteilen gef�llt
 */

public void showAllBauteile(Vector<Bauteil> bauteile) {
		
		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
		 */
		FlexTable bauteileTable = new FlexTable();
		bauteileTable.setText(0,0,"ID");
		bauteileTable.setText(0,1,"Name");
		bauteileTable.setText(0,2,"Beschreibung");
		bauteileTable.setText(0,3,"Bezeichnung");	
		bauteileTable.setText(0,4,"Erstellt am");
		bauteileTable.setText(0,5,"Zuletzt geaendert am");
		bauteileTable.setText(0,6,"letzter Bearbeiter");
		//bauteileTable.setText(0,7,"Edit");
		//bauteileTable.setText(0,8,"Delete");
		
		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		 */
		for(int j=0; j < bauteile.size(); j++ ){
			
			/**
			 * Button, um Bauteil innerhalb der Tabelle zu löschen
			 */
			//Button btnDelete = new Button("X");
			//btnDelete.addClickHandler(new DeleteClickHandler());
			//this.vPanelCreate.add(btnDelete);
			
			
			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
			 */
			//Button editBtn = new Button("Editieren");
			//editBtn.addClickHandler(new EditClickHandler());
			//this.vPanelCreate.add(editBtn);
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			/*Date d1 = new Date();
			d1 = bauteile.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);*/
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			/*Date d2 = new Date();
			d2 = bauteile.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);*/
			
		
			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			bauteileTable.setText(j+1, 0, Integer.toString(bauteile.elementAt(j).getId()));
			bauteileTable.setText(j+1, 1, bauteile.elementAt(j).getName());
			bauteileTable.setText(j+1, 2, bauteile.elementAt(j).getBeschreibung());
			bauteileTable.setText(j+1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
			//bauteileTable.setText(j+1, 4, s1);
			//bauteileTable.setText(j+1, 5, s2);

			
			/**
			 * Einfuegen der Buttons in die Tabelle
			 */
			//bauteileTable.setWidget(j+1, 8, btnDelete);
			//bauteileTable.setWidget(j+1, 7, editBtn);
			
			
			/**
			 * Verknuepfung zu style.css
			 */
			bauteileTable.setCellPadding(6);
			bauteileTable.getRowFormatter().addStyleName(0,  "watchListHeader");
			bauteileTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
			bauteileTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		}	
		
		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
		 */
		this.vPanel.add(bauteileTable);
	
}

public void showSearchResult(Vector<Baugruppe> bg){
	
	VerticalPanel vPanel = new VerticalPanel();
	FlexTable flex = new FlexTable();

	HTML topic = new HTML("<h3>Wählen Siedie Baugruppe zu, welcher Sie das Bauteil zuordnen möchten.</h3> <br>");
	

	flex.setText(0, 0, "ID");
	flex.setText(0, 1, "Name");
	flex.setText(0, 2, "Beschreibung");
	flex.setText(0, 3, "erstellt am");
	flex.setText(0, 4, "geändert am");
	flex.setText(0, 5, "zu EZ zuordnen");
	
	

	/**
	 * Konvertieren der Bauteil-Daten und befüllen der Tabelle
	 */
	
	CheckBox checkBox = new CheckBox();
	
	//flex.setText(1, 0, Integer.toString(bg.getId()));
	//flex.setText(1, 1, bg.getName());
	//flex.setText(1, 3, bg.getBeschreibung());
	//flex.setText(1, 3, ); 
	//flex.setText(1, 4, );
	flex.setWidget(1, 5, checkBox);
	
	
	
	
	
	
	/**
	 * Verknüpfung zu style.css
	 */
	flex.getRowFormatter().addStyleName(0, "EnderzeugnisHeader");
	flex.getRowFormatter().addStyleName(0, "Row1");
	flex.getRowFormatter().addStyleName(1, "Row2");

	flex.getCellFormatter().addStyleName(0, 2, "EnderzeugnisNumericColumn");
	flex.getCellFormatter()
			.addStyleName(0, 3, "EnderzeugnistNumericColumn");

	
	
	
	
	
	vPanel.add(flex);
	
	Button btnSpeichern = new Button("Speichern");
	btnSpeichern.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
		
				}
			});
	
	vPanel.add(btnSpeichern);
	
	
}

public void showMeldung (String meldung){
	
	Label lbl1 = new Label(meldung);
	vPanel.add(lbl1);
}




/**
 * 
 * ClickHandler der Klasse BauteilGUI
 *
 */

/**
 * ClickHandler zum Men�Button Anlegen
 * Es wird �ber createBauteil() ein leeres Bauteil erstellt
 * und an die GUI zur�ck gesendet an die Methode
 * Update(Bauteil bt)
 *
 */


	public class AnlegenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			serviceImpl.createBauteil();
				
		}
	}
	
	/**
	 * ClickHandler zum Men�button Speichern
	 * Wenn ein Bauteil angelegt oder ver�ndert wurde, 
	 * wird das Bauteil in der DB aktualisiert �ber die Methode
	 *  updateBauteil() (Client Impl)
	 * Danach wird das Objekt, welches ver�ndert wurde durch die Methode
	 * getBauteil(Bauteil bt) (BauteilGUI) auf der GUI sichtbar gemacht
	 *
	 */
	public class SpeichernBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			
			/**
			 * Es werden die ver�nderbaren Parameter 
			 * aus den TextBoxen geholt
			 */
			
	
			
			
			bt.setName(name.getText());
			bt.setBeschreibung(beschreibung.getText());
			bt.setMaterialBezeichnung(materialBezeichnung.getText());
			
			name.setText("");
			beschreibung.setText("");
			materialBezeichnung.setText("");
			
			/**
			 * Das Objekt wird an die ClientImpl weiter gegeben
			 */
			serviceImpl.updateBauteil(bt);
			
			bt = null;
			
		}
	}
	
	/**
	 * Wird ein Objekt auf der Bauteilgui angezeigt, 
	 * kann es �ber den Aendern Button ver�ndert werden.
	 * Hierzu wird das Bauteil �ber getBauteil2(int id) (Client Impl) aus der DB geholt
	 * und �ber die Methode UpdateBauteil2(Bauteil bt)(BauteilGUI) 
	 * auf der Bauteilgui sichtbar gemacht
	 */
	
	public class AendernBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
vPanel.clear();
			
			Cell cell = bauteileTable.getCellForEvent(event);
			
			int rowIndex = cell.getRowIndex();
			String id1 = bauteileTable.getText(rowIndex, 0);
			int id = Integer.parseInt(id1);
			serviceImpl.getBauteilForUpdate(id);
			
			
			
			
		}
	}
	
	/**
	 * Wenn ein Bauteil angezeigt wird,
	 * wird es �ber den L�schen Button entfernt
	 * dazu wird die ClientImpl Methode delte Bauteil(int id) aufgerufen
	 * Auf der Bauteil GUI kann man bei erfolgreichem L�schen den Satz
	 * "Bauteil wurde erfolgreich gel�scht" 
	 * (Methode showMeldung(String meldung) (Bauteil Gui) lesen
	 * lesen
	 */
	public class LoeschenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			
			Cell cell = bauteileTable.getCellForEvent(event);
			
			int rowIndex = cell.getRowIndex();
			String id1 = bauteileTable.getText(rowIndex, 0);
			int id = Integer.parseInt(id1);
			serviceImpl.deleteBauteil(id);
			
			
				
		}
	}
	
	/**
	 * Der ShowAll Button holt �ber die ShowAllBauteile Methode
	 * alle Bauteile in einem Vektor aus der DB 
	 * �ber die ShowAllBauteile( Vektor<Bauteile> bauteil) Methode
	 * auf der BauteilGUI werden die Bauteile sichtbar gemacht
	 */
	public class ShowAllBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			
			serviceImpl.getAll();
			
			
				
		}
	}
	
	/**
	 * �ber den Suchenbutton kann man Bauteile suchen
	 * indem man die ID oder ein Name eingibt.
	 *Dann wird die Methode getBauteile oder findBauteilByName in der Klasse ClientImpl aufgerufen
	 */
	
	public class SuchenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			String searchFor = suchen.getText();
			
			if (searchFor.matches("[0-9]+")){
				int id = Integer.parseInt(searchFor);
				serviceImpl.getBauteil(id);
			}
			
			else 
			
			serviceImpl.getBauteil(searchFor);

		
		}
	}
	
	
	
	public class BtnAbbrechenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				
				int id= bt.getId();
				serviceImpl.deleteBauteil(id);
				
			}

	}
}



