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
	
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanel2 = new VerticalPanel();
	private VerticalPanel vPanel3 = new VerticalPanel();
	private TextBox txt1;
	private TextBox insertname;
	private TextBox insertBeschreibung;
	private TextBox insertMaterialBezeichnung;

	
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

		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;

		this.txt1 = new TextBox();
		txt1.setText("Bitte hier ID eintragen...");
		this.vPanel.add(txt1);
		
		/**
		 * Erzeugen eines Buttons für die Suchfunktion
		 */
		Button btn1 = new Button("Suchen");
		btn1.addClickHandler(new Btn1ClickHandler());
		this.vPanel.add(btn1);
		
		serviceImpl.getAll();
		

		this.resultlbl = new Label("Ergebnis wird hier stehen");
		//this.vPanel.add(resultlbl);

		this.resultid = new Label("Hier wird ID stehen");
		//this.vPanel.add(resultid);

		this.dBox = new DialogBox();
		this.dBox.setTitle("Achtung");
		this.dBox.setText("Zu Ihrer Suche wurden keine Daten gefunden.");

		this.lblinsertname = new Label("Name");
		this.vPanel3.add(lblinsertname);
		this.insertname = new TextBox();
		this.vPanel3.add(insertname);

		this.lblinsertBeschreibung = new Label("Beschreibung");
		this.vPanel3.add(lblinsertBeschreibung);
		this.insertBeschreibung = new TextBox();
		this.vPanel3.add(insertBeschreibung);

		this.lblinsertMaterialBezeichnung = new Label("Materialbezeichnung");
		this.vPanel3.add(lblinsertMaterialBezeichnung);
		this.insertMaterialBezeichnung = new TextBox();
		this.vPanel3.add(insertMaterialBezeichnung);

		/**
		 * Erzeugen eines Buttons für die Speichernfunktion
		 */
		Button btn2 = new Button("Speichern");
		btn2.addClickHandler(new Btn2ClickHandler());
		this.vPanel3.add(btn2);

		/**
		 * Erzeugen eines Buttons für die Editierfunktion
		 */
		Button btn1EditBauteil = new Button("Edit");
		btn1EditBauteil.addClickHandler(new Btn1EditBauteilClickHandler());
		//this.Btn1EditBauteilPanel.add(btn1EditBauteil);
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
			Button deleteBtn = new Button("X");
			deleteBtn.addClickHandler(new DeleteBtnClickHandler());
			this.vPanel3.add(deleteBtn);
			
			
			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
			 */
			Button editBtn = new Button("Editieren");
			editBtn.addClickHandler(new EditBtnClickHandler());
			this.vPanel3.add(deleteBtn);
			
			
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
			bauteileTable.setWidget(j+1, 8, deleteBtn);
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
		this.vPanel2.add(bauteileTable);
		
		/**
		 * Button, um die Tabelle zu aktualisieren
		 */
		Button btn3 = new Button("Update Table");
		btn3.addClickHandler(new Btn3ClickHandler());
		this.vPanel2.add(btn3);
		
		this.vPanel.add(vPanel2);
		this.vPanel.add(vPanel3);	
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
				this.vPanel2.add(findBauteilTable);
				//this.vPanel.add(vPanel2);
		}
	}
	
	/**
	 * Ausgabe einer Fehlermeldung
	 */
	public void showError() {
		
		
		this.vPanel2.add(dBox);
		//Window.alert("Zu Ihrer Suche wurden keine Daten gefunden.");
		//this.vPanel.clear();
		//serviceImpl.getAll();
	}

	public void GetAllError() {

		this.getAllError = new Label("Get All hat nicht funktioniert!");
		this.vPanel.add(getAllError);
	}

	public void showSucess() {

		this.erfolg = new Label("Erfolg");
		this.vPanel.add(erfolg);
	}
	
	public void showMeldung(String meldung) {

		this.erfolg = new Label(meldung);
		this.vPanel.add(erfolg);
	}


	private class Btn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vPanel2.clear();
			String stringid = txt1.getText();
			
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

	private class Btn2ClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			String name = insertname.getText();
			String beschreibung = insertBeschreibung.getText();
			String materialBezeichnung = insertMaterialBezeichnung.getText();

			Bauteil bt = new Bauteil();
			bt.setName(name);
			bt.setBeschreibung(beschreibung);
			bt.setMaterialBezeichnung(materialBezeichnung);

			serviceImpl.create(bt);
			vPanel2.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler für den Bauteil-Lösch-Button
	 */
	private class DeleteBtnClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			int rowIndex = bauteileTable.getCellForEvent(event).getRowIndex();
			int id = Integer.parseInt(bauteileTable.getText(rowIndex, 0));

			serviceImpl.delete(id);
			vPanel2.clear();
			serviceImpl.getAll();
		}
	}
	
	/**
	 * ClickHandler für den Editieren-Button
	 */
	private class EditBtnClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			
			int rowIndex = bauteileTable.getCellForEvent(event).getRowIndex();
			int id = Integer.parseInt(bauteileTable.getText(rowIndex, 0));
			System.out.println("Das ist die ID"+ id);
			
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
			TextBox insertname = new TextBox();
			insertname.setText(sinsertname);
			bauteileTable.setWidget(rowIndex, 1, insertname);
			
			TextBox insertBeschreibung = new TextBox();
			insertBeschreibung.setText(sinsertBeschreibung);
			bauteileTable.setWidget(rowIndex, 2, insertBeschreibung);
			
			TextBox insertMaterialBezeichnung = new TextBox();
			insertMaterialBezeichnung.setText(sinsertMaterialBezeichnung);
			bauteileTable.setWidget(rowIndex, 3, insertMaterialBezeichnung);
			
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
			 * Die eingegeben Daten werden in String-Variablen gespeichert
			 */
			String name = insertname.getText();
			String beschreibung = insertBeschreibung.getText();
			String materialBezeichnung = insertMaterialBezeichnung.getText();

			/**
			 * Erzeugen einer Bauteil-Instanz und Ersetzen der bisherigen Daten mit den neuen Daten aus den Strings.
			 */
			Bauteil bt = new Bauteil();
			bt.setName(name);
			bt.setBeschreibung(beschreibung);
			bt.setMaterialBezeichnung(materialBezeichnung);
			
			serviceImpl.updateBauteil(bt);	
		}
	}
	
	/**
	 * ClickHandler für den Tabellen-Aktualisierungsbutton
	 */
	private class Btn3ClickHandler implements ClickHandler {
		
		/**
		 * Panel-Inhalt wird gelöscht, anschließend neu befüllt
		 */
		public void onClick(ClickEvent event) {
			vPanel2.clear();
			serviceImpl.getAll();
		}
	}

	/**
	 * ClickHandler für den Editierungs-Button
	 */
	private class Btn1EditBauteilClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

		}
	}
}