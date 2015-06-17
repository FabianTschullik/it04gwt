package de.hdm.it04.client.gui;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;

/**
 * Diese Klasse ist die Haupt-GUI Klasse. Composite wird erweitert, um einfach neue Widgets zu erzeugen.
 * @author Geier, Maehler, Schneider, Schwab, Tschullik, Voelker
 * @version 0.9
 */

public class MainGUI extends Composite {
	
	private VerticalPanel vPanelMain = new VerticalPanel();
	private VerticalPanel vPanelFlexTable = new VerticalPanel();
	private VerticalPanel vPanelCreate = new VerticalPanel();
	private TextBox txt1;
	private TextBox insertname;
	private TextBox insertBeschreibung;
	private TextBox insertMaterialBezeichnung;
	
	private TextBox insertNameUpdate = new TextBox();
	private TextBox insertBeschreibungUpdate = new TextBox();
	private TextBox insertMaterialBezeichnungUpdate = new TextBox();

	
	private Label lblinsertname;
	private Label lblinsertBeschreibung;
	private Label lblinsertMaterialBezeichnung;

	private Label resultlbl;
	private Label resultid;
	private Label erfolg;
	private Label getAllError;
	private FlexTable bauteileTable;
	private FlexTable findBauteilTable;

	private DialogBox dBox;

	private It04gwtServiceClientImpl serviceImpl;

	
	/**
	 * Konstruktor der Klasse MainGUI
	 * @param serviceImpl
	 */
	public MainGUI(It04gwtServiceClientImpl serviceImpl) {

		initWidget(this.vPanelMain);
		this.serviceImpl = serviceImpl;

		this.txt1 = new TextBox();
		txt1.setText("Bitte hier ID eintragen...");
		this.vPanelMain.add(txt1);
		
		/**
		 * Erzeugen eines Buttons für die Suchfunktion
		 */
		Button btnSearch = new Button("Suchen");
		btnSearch.addClickHandler(new SearchClickHandler());
		this.vPanelMain.add(btnSearch);
		
		serviceImpl.getAll();
		

		this.resultlbl = new Label("Ergebnis wird hier stehen");
		

		this.resultid = new Label("Hier wird ID stehen");
		

		this.dBox = new DialogBox();
		this.dBox.setTitle("Achtung");
		this.dBox.setText("Zu Ihrer Suche wurden keine Daten gefunden.");

		this.lblinsertname = new Label("Name");
		this.vPanelCreate.add(lblinsertname);
		this.insertname = new TextBox();
		this.vPanelCreate.add(insertname);

		this.lblinsertBeschreibung = new Label("Beschreibung");
		this.vPanelCreate.add(lblinsertBeschreibung);
		this.insertBeschreibung = new TextBox();
		this.vPanelCreate.add(insertBeschreibung);

		this.lblinsertMaterialBezeichnung = new Label("Materialbezeichnung");
		this.vPanelCreate.add(lblinsertMaterialBezeichnung);
		this.insertMaterialBezeichnung = new TextBox();
		this.vPanelCreate.add(insertMaterialBezeichnung);

		/**
		 * Erzeugen eines Buttons für die Speichernfunktion
		 */
		Button btnSave = new Button("Speichern");
		btnSave.addClickHandler(new CreateClickHandler());
		this.vPanelCreate.add(btnSave);
	}
	
	
	/**
	 * Methode, um alle Bauteile anzeigen zu lassen in einer FlexTable
	 * @param bauteile
	 */
	public void showAllBauteile(Vector<Bauteil> bauteile) {
		
		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
		 */
		this.bauteileTable = new FlexTable();
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
			btnDelete.addClickHandler(new DeleteClickHandler());
			this.vPanelCreate.add(btnDelete);
			
			
			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
			 */
			Button editBtn = new Button("Editieren");
			editBtn.addClickHandler(new EditClickHandler());
			this.vPanelCreate.add(editBtn);
			
			
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
		this.vPanelFlexTable.add(bauteileTable);
		
		/**
		 * Button, um die Tabelle zu aktualisieren
		 */
		Button btnUpdate = new Button("Update Table");
		btnUpdate.addClickHandler(new UpdateClickHandler());
		this.vPanelFlexTable.add(btnUpdate);
		
		this.vPanelMain.add(vPanelFlexTable);
		this.vPanelMain.add(vPanelCreate);	
	}
	
	
	public void showBauteil(Vector<Bauteil> bauteile) {

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
		 */
				this.findBauteilTable = new FlexTable();
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
				 * Für jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
				 */
				for(int j=0; j < bauteile.size(); j++ ){
					
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
					
				
					int id = bauteile.elementAt(j).getId();
					findBauteilTable.setText(j+1, 0, new Integer(id).toString());
					findBauteilTable.setText(j+1, 1, bauteile.elementAt(j).getName());
					findBauteilTable.setText(j+1, 2, bauteile.elementAt(j).getBeschreibung());
					findBauteilTable.setText(j+1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
					findBauteilTable.setText(j+1, 4, s1);
					findBauteilTable.setText(j+1, 5, s2);
					
				
					
					this.vPanelMain.add(vPanelFlexTable);
					this.vPanelMain.add(vPanelCreate);	
					
					
					/**
					 * Verknüpfung zu style.css
					 */
					findBauteilTable.setCellPadding(6);
					findBauteilTable.getRowFormatter().addStyleName(0,  "watchListHeader");
					findBauteilTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					findBauteilTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					
				
				/**
				 * Bauteil-Tabelle zum Panel hinzugefügt damit das Ganze auch angezeigt wird 
				 */
				this.vPanelFlexTable.add(findBauteilTable);
				
				/**
				 * Zurück-Button zur Hauptseite Bauteil
				 */
				Button btnZurueck = new Button("Zurueck");
				btnZurueck.addClickHandler(new ZurueckClickHandler());
				this.vPanelFlexTable.add(btnZurueck);
				
		}
	}
	
	/**
	 * Ausgabe einer Fehlermeldung
	 */
	public void showError() {
		
		
		this.vPanelFlexTable.add(dBox);
		
	}

	public void GetAllError() {

		this.getAllError = new Label("Get All hat nicht funktioniert!");
		this.vPanelMain.add(getAllError);
	}

	public void showSucess() {

		this.erfolg = new Label("Erfolg");
		this.vPanelMain.add(erfolg);
	}
	
	public void showMeldung(String meldung) {

		this.erfolg = new Label(meldung);
		this.vPanelMain.add(erfolg);
	}

	/**
	 * ClickHandler für den Bauteil-Search-Button
	 */
	private class SearchClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vPanelFlexTable.clear();
			String stringid = txt1.getText();
			
			/** 
			 * Es wird überprüft ob sich in dem Attribut
			 * <b>stringid<b> eine Zahl befindet.
			 * Falls nein wird findByName angestoßen
			 */
			
			if (stringid.matches(".*[1-9].*")){
				int id;
				id = Integer.parseInt(stringid);
				serviceImpl.getBauteil(id);
			}
			else {
				serviceImpl.findByName(stringid);
			}
		}
	}
	
	/**
	 * ClickHandler für den Bauteil-Create-Button
	 */
	private class CreateClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			String name = insertname.getText();
			String beschreibung = insertBeschreibung.getText();
			String materialBezeichnung = insertMaterialBezeichnung.getText();

			Bauteil bt = new Bauteil();
			bt.setName(name);
			bt.setBeschreibung(beschreibung);
			bt.setMaterialBezeichnung(materialBezeichnung);

			serviceImpl.create(bt);
			vPanelFlexTable.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler für den Bauteil-Lösch-Button
	 */
	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			int rowIndex = bauteileTable.getCellForEvent(event).getRowIndex();
			int id = Integer.parseInt(bauteileTable.getText(rowIndex, 0));

			serviceImpl.delete(id);
			vPanelFlexTable.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler für den Editieren-Button
	 */
	private class EditClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			int rowIndex = bauteileTable.getCellForEvent(event).getRowIndex();
			int id = Integer.parseInt(bauteileTable.getText(rowIndex, 0));
		
			/**
			 * Daten werden in Strings gespeichert
			 */
			String sinsertname = bauteileTable.getText(rowIndex, 1);
			String sinsertBeschreibung = bauteileTable.getText(rowIndex, 2);
			String sinsertMaterialBezeichnung = bauteileTable.getText(rowIndex, 3);
			
			/**
			 * Erzeugen von Eingabefeldern, in die initial die Strings gesetzt werden und im jeweiligen Eingabefeld
			 * dann editiert werden können.
			 */
			insertNameUpdate.setText(sinsertname);
			bauteileTable.setWidget(rowIndex, 1, insertNameUpdate);
			
		
			insertBeschreibungUpdate.setText(sinsertBeschreibung);
			bauteileTable.setWidget(rowIndex, 2, insertBeschreibungUpdate);
			
			
			insertMaterialBezeichnungUpdate.setText(sinsertMaterialBezeichnung);
			bauteileTable.setWidget(rowIndex, 3, insertMaterialBezeichnungUpdate);
			
			/**
			 * Button, um die Änderungen an den Bauteil-Daten zu speichern
			 */
			Button saveEditedBauteil = new Button("Speichern");
			saveEditedBauteil.addClickHandler(new saveEditedClickHandler());
			bauteileTable.setWidget(rowIndex, 7, saveEditedBauteil);
		}
	}
	
	/**
	 * ClickHandler für den Speicher-Button beim editieren
	 */
	private class saveEditedClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			/**
			 *
			 * Die eingegeben Daten werden in String-Variablen gespeichert
			 */
			int rowIndex = bauteileTable.getCellForEvent(event).getRowIndex();
			int id = Integer.parseInt(bauteileTable.getText(rowIndex, 0));
			
			
			String name = insertNameUpdate.getText();
			String beschreibung = insertBeschreibungUpdate.getText();
			String materialBezeichnung = insertMaterialBezeichnungUpdate.getText();

			/**
			 * Erzeugen einer Bauteil-Instanz und Ersetzen der bisherigen Daten mit den neuen Daten aus den Strings.
			 */
			Bauteil bt = new Bauteil();
			bt.setId(id);
			bt.setName(name);
			bt.setBeschreibung(beschreibung);
			bt.setMaterialBezeichnung(materialBezeichnung);
			
			serviceImpl.updateBauteil(bt);	
			vPanelFlexTable.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler für den Tabellen-Aktualisierungsbutton
	 */
	private class UpdateClickHandler implements ClickHandler {
		
		/**
		 * Panel-Inhalt wird gelöscht, anschließend neu befüllt
		 */
		public void onClick(ClickEvent event) {
			vPanelFlexTable.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler um zurück aus der Suche in Bauteil Main zu kommen
	 */
	private class ZurueckClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			vPanelFlexTable.clear();
			serviceImpl.getAll();
			
		}
	}
}