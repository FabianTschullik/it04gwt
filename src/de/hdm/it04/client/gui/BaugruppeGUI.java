package de.hdm.it04.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import de.hdm.it04.client.gui.BauteilGUI.AendernBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.AnlegenBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.LoeschenBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.ShowAllBtn1ClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.SpeichernBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.SuchenBtnClickHandler;
import de.hdm.it04.client.gui.EnderzeugnisGUI.BtnAnlegenClickHandler;
import de.hdm.it04.client.gui.EnderzeugnisGUI.BtnBearbeitenClickHandler;
import de.hdm.it04.client.gui.EnderzeugnisGUI.BtnLoeschenClickHandler;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

public class BaugruppeGUI extends MainGUI {
	
	private TextBox txtSuchen = new TextBox();
	private TextBox txtName = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	public Baugruppe bg;
	FlexTable baugruppeTable = new FlexTable();
	CheckBox cb = new CheckBox();
	
	

	


	public BaugruppeGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
public void menue(){
		
	
	this.hPanel.clear();
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML("<h2>Was wollen Sie mit der Baugruppe tun?</h2>");
		

		this.vPanel.add(topic);
		
		/**
		 * Men� Buttons um weiter Aktivitäten f�r Bauteil zu w�hlen
		 */
		
		Button btnAnlegen = new Button("Anlegen");
		btnAnlegen.addClickHandler(new BtnAnlegenClickHandler());
		this.hPanel.add(btnAnlegen);

			
		txtSuchen.setText("id oder Name");
		this.hPanel.add(txtSuchen);
		
		Button btnSuchen = new Button("Baugruppe suchen");
		btnSuchen.addClickHandler(new BtnSuchenClickHandler());
		this.hPanel.add(btnSuchen);	
		
		Button btnShowAll = new Button("Alle Baugruppen anzeigen");
		btnShowAll.addClickHandler(new BtnShowAllClickHandler());
		this.hPanel.add(btnShowAll);	
		
		this.vPanel.add(hPanel);
		
		RootPanel.get("content").add(this.vPanel);	
	}

//----------------------------------------------------------------------------
//----------------------- Form zum Anlegen einer Baugruppe --------------------------
//----------------------------------------------------------------------------
public void showAnlegenForm(Baugruppe bg){
	
	
	this.bg = bg;
	txtName.setText(bg.getName());
	txtBeschreibung.setText(bg.getBeschreibung());
	
	
	
	// Create a table to layout the form options
  FlexTable layout = new FlexTable();
  layout.setCellSpacing(6);
  FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

  // Add a title to the form
  layout.setHTML(0, 0, "<h3>Baugruppe anlegen<h3>");
  cellFormatter.setColSpan(0, 0, 2);
  cellFormatter.setHorizontalAlignment(
      0, 0, HasHorizontalAlignment.ALIGN_CENTER);

  Button btnSuchen = new Button("Suchen");
	btnSuchen.addClickHandler(new BtnSuchenClickHandler());
	
	Button btnSpeichern = new Button("Speichern");
	btnSpeichern.addClickHandler(new BtnSpeichernClickHandler());
	
	Button btnAbbrechen = new Button("Abbrechen");
	btnAbbrechen.addClickHandler(new BtnAbbrechenClickHandler());
	
	
	
	
  // Add some standard form options
  layout.setHTML(1, 0, "ID");
  layout.setText(1, 1, Integer.toString(bg.getId()));
  layout.setHTML(2, 0, "Name");
  layout.setWidget(2, 1, txtName);
  layout.setHTML(3, 0, "Beschreibung");
  layout.setWidget(3, 1, txtBeschreibung);
  layout.setHTML(4, 0, "Baugruppe zuordnen");
  //layout.setWidget(4, 1, createMultiBox());
  //layout.setWidget(4, 2, btnSuchen);
  layout.setWidget(5, 0, btnSpeichern);
  layout.setWidget(5, 1, btnAbbrechen);
  

  // Wrap the content in a DecoratorPanel
  DecoratorPanel decPanel = new DecoratorPanel();
  decPanel.setWidget(layout);
  
  this.vPanel.add(decPanel);
}
//----------------------------------------------------------------------------
//----------------------- Ende Form zum Anlegen eines EZ --------------------------
//----------------------------------------------------------------------------


public void showBaugruppeForm(Baugruppe bg){
	
	
	
	this.bg = bg;
	//this.vPanel.clear();
	
	/**
	 *�berschriften der Tabelle 
	 */
	flex.setText(0, 0, "ID");
	flex.setText(0, 1, "Name");
	flex.setText(0, 2, "Beschreibung");
	flex.setText(0, 3, "erstellt am");
	flex.setText(0, 4, "geändert am");
	flex.setText(0, 5, "Bearbeiten");
	flex.setText(0, 6, "Löschen");
	
	Button btnBearbeiten = new Button("Bearbeiten");
	btnBearbeiten.addClickHandler(new BtnBearbeitenClickHandler());
	
	Button btnLoeschen = new Button("Loeschen");
	//btnLoeschen.addClickHandler(new BtnLoeschenClickHandler());
	
	flex.setText(1, 0, Integer.toString(bg.getId()));
	flex.setText(1, 1, bg.getName());
	flex.setText(1, 2, bg.getBeschreibung());
	flex.setText(1, 3, "erstellt am");
	flex.setText(1, 4, "geändert am");
	flex.setWidget(1, 5, btnBearbeiten);
	flex.setWidget(1, 6, btnLoeschen);
	
	/**
	 * Verknüpfung zu style.css
	 */

	flex.setCellPadding(6);
	flex.getRowFormatter().addStyleName(0,  "watchListHeader");
	flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
	flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	this.vPanel.add(flex);
}

public void showAllBaugruppen(Vector<Baugruppe> baugruppen){
	
	this.hPanel.clear();
	/**
	 * neuer HTML Bereich
	 */
	HTML topic = new HTML("<h2>Was wollen Sie mit dem Enderzeugnis tun?</h2>");
	this.vPanel.add(topic);
	
	Button btnAnlegen = new Button("Anlegen");
	btnAnlegen.addClickHandler(new BtnAnlegenClickHandler());
	this.hPanel.add(btnAnlegen);
	
	this.vPanel.add(this.hPanel);
	
	
	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
	 */
	
	baugruppeTable.setText(0,0,"ID");
	baugruppeTable.setText(0,1,"Name");
	baugruppeTable.setText(0,2,"Beschreibung");
	baugruppeTable.setText(0,3,"Erstellt am");
	baugruppeTable.setText(0,4,"Zuletzt geaendert am");
	baugruppeTable.setText(0,5,"letzter Bearbeiter");
	baugruppeTable.setText(0,6,"Edit");
	baugruppeTable.setText(0,7,"Delete");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < baugruppen.size(); j++ ){
		
		/**
		 * Button, um Bauteil innerhalb der Tabelle zu löschen
		 */
		Button btnLoeschen = new Button ("Löschen");
		btnLoeschen.addClickHandler(new BtnLoeschenClickHandler());
		
		/**
		 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
		 */
		
		Button btnBearbeiten = new Button ("Bearbeiten");
		btnBearbeiten.addClickHandler(new BtnBearbeitenClickHandler());
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
		baugruppeTable.setText(j+1, 0, Integer.toString(baugruppen.elementAt(j).getId()));
		baugruppeTable.setText(j+1, 1, baugruppen.elementAt(j).getName());
		baugruppeTable.setText(j+1, 2, baugruppen.elementAt(j).getBeschreibung());
		//bauteileTable.setText(j+1, 3, s1);
		//bauteileTable.setText(j+1, 4, s2);
		/**
		 * Einfuegen der Buttons in die Tabelle
		 */
		baugruppeTable.setWidget(j+1, 5, btnLoeschen);
		baugruppeTable.setWidget(j+1, 6, btnBearbeiten );
		
		
		/**
		 * Verknuepfung zu style.css
		 */
		baugruppeTable.setCellPadding(6);
		baugruppeTable.getRowFormatter().addStyleName(0,  "watchListHeader");
		baugruppeTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		baugruppeTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	}	
	
	/**
	 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
	 */
	this.vPanel.add(baugruppeTable);
	
}


public void showZuordnungsForm (Vector <Baugruppe> baugruppe){
	
	this.vPanel.clear();
	
	/**
	 * neuer HTML Bereich
	 */
	HTML topic = new HTML("<h2>Welche Baugruppen möchten Sie der Baugruppe zuordnen?</h2>");
	this.vPanel.add(topic);
	
	Button btnBaugruppeZuordnung = new Button("Jetzt zuordnen");
	btnBaugruppeZuordnung.addClickHandler(new BaugruppeZuordnenClickHandler());
	
	this.vPanel.add(btnBaugruppeZuordnung);
	
	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
	 */
	baugruppeTable.setText(0,0,"ID");
	baugruppeTable.setText(0,1,"Name");
	baugruppeTable.setText(0,2,"Beschreibung");
	baugruppeTable.setText(0,3,"Erstellt am");
	baugruppeTable.setText(0,4,"Zuletzt geaendert am");
	baugruppeTable.setText(0,5,"letzter Bearbeiter");
	baugruppeTable.setText(0,6,"Zuordnen");
	//bauteileTable.setText(0,7,"Delete");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < baugruppe.size(); j++ ){
		
		CheckBox cb = new CheckBox();
		
		
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
		baugruppeTable.setText(j+1, 0, Integer.toString(baugruppe.elementAt(j).getId()));
		baugruppeTable.setText(j+1, 1, baugruppe.elementAt(j).getName());
		baugruppeTable.setText(j+1, 2, baugruppe.elementAt(j).getBeschreibung());
		//bauteileTable.setText(j+1, 3, s1);
		//bauteileTable.setText(j+1, 4, s2);

		baugruppeTable.setWidget(j+1, 6, cb);
		
		
		/**
		 * Verknuepfung zu style.css
		 */
		baugruppeTable.setCellPadding(6);
		baugruppeTable.getRowFormatter().addStyleName(0,  "watchListHeader");
		baugruppeTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		baugruppeTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	}	
	
	/**
	 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
	 */
	
	this.vPanel.add(baugruppeTable);
	
	
	
	
	
	
}






public class BtnAnlegenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		vPanel.clear();
		baugruppeTable.removeAllRows();
		serviceImpl.createBaugruppe();
	}
}

public class BtnSuchenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		//serviceImpl.getBaugruppe(Integer.parseInt(txtSuchen.getText()));
	}
}

public class BtnShowAllClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		vPanel.clear();
		serviceImpl.getAllBaugruppen();	
	}
}

public class BtnSpeichernClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		vPanel.clear();
		/**
		 * Es werden die ver�nderbaren Parameter 
		 * aus den TextBoxen geholt
		 */
		bg.setName(txtName.getText());
		bg.setBeschreibung(txtBeschreibung.getText());
		
		serviceImpl.updateBaugruppe(bg);
	}
}

public class BtnAbbrechenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		vPanel.clear();
		serviceImpl.deleteBaugruppe(bg.getId());
	}
}

public class BtnBearbeitenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		Cell cell = baugruppeTable.getCellForEvent(event);
		
		int rowIndex = cell.getRowIndex();
		String id1 = baugruppeTable.getText(rowIndex, 0);
		int id = Integer.parseInt(id1);
		
		vPanel.clear();
		baugruppeTable.removeAllRows();
		serviceImpl.getBaugruppeForUpdate(id);
	}
}

public class BtnLoeschenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		Cell cell = baugruppeTable.getCellForEvent(event);
		
		int rowIndex = cell.getRowIndex();
		String id1 = baugruppeTable.getText(rowIndex, 0);
		int id = Integer.parseInt(id1);
		
		vPanel.clear();
		baugruppeTable.removeAllRows();
		serviceImpl.deleteBaugruppe(id);
	}
}




public class BaugruppeZuordnenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
	
	}
}

	

}