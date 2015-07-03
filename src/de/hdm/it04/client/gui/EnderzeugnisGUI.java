package de.hdm.it04.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public class EnderzeugnisGUI extends MainGUI{
	
	private TextBox txtName = new TextBox();
	private TextBox txtPreis = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	public Enderzeugnis ez;
	FlexTable baugruppeTable = new FlexTable();
	FlexTable enderzeugnisseTable = new FlexTable();
	
	


	public EnderzeugnisGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
//----------------------------------------------------------------------------
//----------------------- Form zum Anlegen eines EZ --------------------------
//----------------------------------------------------------------------------
public void showAnlegenForm(Enderzeugnis enderzeugnis){
	
	this.vPanel.clear();
	this.ez = enderzeugnis;
	txtName.setText(ez.getName());
	txtBeschreibung.setText(ez.getBeschreibung());
	txtPreis.setText(Double.toString(ez.getPreis()));
	
	
	// Create a table to layout the form options
    FlexTable layout = new FlexTable();
    layout.setCellSpacing(6);
    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

    // Add a title to the form
    layout.setHTML(0, 0, "<h3>Enderzeugnis anlegen<h3>");
    cellFormatter.setColSpan(0, 0, 2);
    cellFormatter.setHorizontalAlignment(
        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	
	Button btnSpeichern = new Button("Speichern");
	btnSpeichern.addClickHandler(new BtnSpeichernClickHandler());
	
	Button btnAbbrechen = new Button("Abbrechen");
	btnAbbrechen.addClickHandler(new BtnAbbrechenClickHandler());
	
	
	
	
    // Add some standard form options
    layout.setHTML(1, 0, "ID");
    layout.setText(1, 1, Integer.toString(ez.getId()));
    layout.setHTML(2, 0, "Name");
    layout.setWidget(2, 1, txtName);
    layout.setHTML(3, 0, "Beschreibung");
    layout.setWidget(3, 1, txtBeschreibung);
    layout.setHTML(4, 0, "Preis");
    layout.setWidget(4, 1, txtPreis);
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








public void showZuordnungsForm (Vector <Baugruppe> baugruppe){
	
	
	/**
	 * neuer HTML Bereich
	 */
	HTML topic = new HTML("<h2>Welche Baugruppe möchten Sie dem Enderzeugnis zuordnen?</h2>");
	this.vPanel.add(topic);
	
	Button btnZuordnung = new Button("Jetzt zuordnen");
	btnZuordnung.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			serviceImpl.updateEnderzeugnis(ez);
		}
	});
	
	this.vPanel.add(btnZuordnung);
	
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
		
		RadioButton rb = new RadioButton("Zuordnung");
	
		rb.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				boolean checked = ((CheckBox) event.getSource()).getValue();
				
				if (checked == true){
					
					Cell cell = baugruppeTable.getCellForEvent(event);
					
					int rowIndex = cell.getRowIndex();
					String id1 = baugruppeTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);
					ez.setBaugruppe(id);
				}
			}
		});
		
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

		baugruppeTable.setWidget(j+1, 6, rb);
		
		if(Integer.parseInt(baugruppeTable.getText(j+1, 0)) == ez.getBaugruppe()){
			rb.setValue(true);
		}
		
		
		
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



public void showEnderzeugnisForm(Enderzeugnis enderzeugnis){
	
		this.ez = enderzeugnis;
		//this.vPanel.clear();
		
		/**
		 *�berschriften der Tabelle 
		 */
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "Preis");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		flex.setText(0, 6, "Bearbeiten");
		flex.setText(0, 7, "Löschen");
		
		Button btnBearbeiten = new Button("Bearbeiten");
		btnBearbeiten.addClickHandler(new BtnBearbeitenClickHandler());
		
		Button btnLoeschen = new Button("Loeschen");
		btnLoeschen.addClickHandler(new BtnLoeschenClickHandler());
		
		Button btnZuordnung = new Button("Zuordnung");
		btnZuordnung.addClickHandler(new BtnZuordnungClickHandler());
		
		flex.setText(1, 0, Integer.toString(ez.getId()));
		flex.setText(1, 1, ez.getName());
		flex.setText(1, 2, ez.getBeschreibung());
		flex.setText(1, 3, Double.toString(ez.getPreis()));
		flex.setText(1, 4, "erstellt am");
		flex.setText(1, 5, "geändert am");
		flex.setWidget(1, 6, btnBearbeiten);
		flex.setWidget(1, 8, btnZuordnung);
		flex.setWidget(1, 9, btnLoeschen);
		
		/**
		 * Verknüpfung zu style.css
		 */
	
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0,  "watchListHeader");
		flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		this.vPanel.add(flex);
}






public void showAllEnderzeugnisse(Vector<Enderzeugnis> enderzeugnisse){
	
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
	
	enderzeugnisseTable.setText(0,0,"ID");
	enderzeugnisseTable.setText(0,1,"Name");
	enderzeugnisseTable.setText(0,2,"Beschreibung");
	enderzeugnisseTable.setText(0,3,"Preis");	
	enderzeugnisseTable.setText(0,4,"Erstellt am");
	enderzeugnisseTable.setText(0,5,"Zuletzt geaendert am");
	enderzeugnisseTable.setText(0,6,"letzter Bearbeiter");
	enderzeugnisseTable.setText(0,7,"Bearbeiten");
	enderzeugnisseTable.setText(0,8,"Löschen");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < enderzeugnisse.size(); j++ ){
		
		Button btnLoeschen = new Button ("Löschen");
		btnLoeschen.addClickHandler(new BtnLoeschenClickHandler());
		
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
		enderzeugnisseTable.setText(j+1, 0, Integer.toString(enderzeugnisse.elementAt(j).getId()));
		enderzeugnisseTable.setText(j+1, 1, enderzeugnisse.elementAt(j).getName());
		enderzeugnisseTable.setText(j+1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
		enderzeugnisseTable.setText(j+1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
		//bauteileTable.setText(j+1, 4, s1);
		//bauteileTable.setText(j+1, 5, s2);
		enderzeugnisseTable.setWidget(j+1, 7, btnBearbeiten);
		enderzeugnisseTable.setWidget(j+1, 8, btnLoeschen);
		
		
		/**
		 * Verknuepfung zu style.css
		 */
		enderzeugnisseTable.setCellPadding(6);
		enderzeugnisseTable.getRowFormatter().addStyleName(0,  "watchListHeader");
		enderzeugnisseTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		enderzeugnisseTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	}	
	
	/**
	 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
	 */
	this.vPanel.add(enderzeugnisseTable);
	
	
	
	
}
	
	
//--------------------------------------------------------------------------------------
//---------------------------- Click Handler Buttons------------------------------------
//--------------------------------------------------------------------------------------
		public class BtnAnlegenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				serviceImpl.createEnderzeugnis();
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
				ez.setName(txtName.getText());
				ez.setPreis(Double.parseDouble(txtPreis.getText()));
				ez.setBeschreibung(txtBeschreibung.getText());
				
				serviceImpl.getAllBaugruppenForZuordnung();
				
				
			}
		}
		
		
		public class BtnZuordnenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
				vPanel.clear();
				
				Cell cell = baugruppeTable.getCellForEvent(event);
				
				int rowIndex = cell.getRowIndex();
				String id1 = baugruppeTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);
				ez.setBaugruppe(id);
				serviceImpl.updateEnderzeugnis(ez);
				serviceImpl.deleteBauteil(id);
				
			}
		}
		
		
		public class BtnAbbrechenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				serviceImpl.deleteEnderzeugnis(ez.getId());
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
		public class BtnBearbeitenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				Cell cell = enderzeugnisseTable.getCellForEvent(event);
				
				int rowIndex = cell.getRowIndex();
				String id1 = enderzeugnisseTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);
				//vPanel.clear();
				serviceImpl.getEnderzeugnisForUpdate(id);
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
		public class BtnLoeschenClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
				
				
				Cell cell = enderzeugnisseTable.getCellForEvent(event);
				
				int rowIndex = cell.getRowIndex();
				String id1 = enderzeugnisseTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);
				vPanel.clear();
				serviceImpl.deleteEnderzeugnis(id);
	
			}
		}
		
		/**
		 * Der ShowAll Button holt �ber die ShowAllBauteile Methode
		 * alle Bauteile in einem Vektor aus der DB 
		 * �ber die ShowAllBauteile( Vektor<Bauteile> bauteil) Methode
		 * auf der BauteilGUI werden die Bauteile sichtbar gemacht
		 */
		public class BtnShowAllClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				serviceImpl.getAllEnderzeugnisse();	
			}
		}
		
		
		
		/**
		 * Der ShowAll Button holt �ber die ShowAllBauteile Methode
		 * alle Bauteile in einem Vektor aus der DB 
		 * �ber die ShowAllBauteile( Vektor<Bauteile> bauteil) Methode
		 * auf der BauteilGUI werden die Bauteile sichtbar gemacht
		 */
		public class BtnZuordnungClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				serviceImpl.getAllBaugruppenForZuordnung();	
			}
		}
}
