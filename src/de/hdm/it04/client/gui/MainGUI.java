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

	public MainGUI(It04gwtServiceClientImpl serviceImpl) {

		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;

		this.txt1 = new TextBox();
		txt1.setText("Bitte hier ID eintragen...");
		this.vPanel.add(txt1);

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

		Button btn2 = new Button("Speichern");
		btn2.addClickHandler(new Btn2ClickHandler());
		this.vPanel3.add(btn2);

		
		

		Button btn1EditBauteil = new Button("Edit");
		btn1EditBauteil.addClickHandler(new Btn1EditBauteilClickHandler());
		//this.Btn1EditBauteilPanel.add(btn1EditBauteil);

	}
	
	
	
	// Methode um alle Bauteile anzeigen zu lassen in einer FlexTable
	public void showAllBauteile(Vector<Bauteil> bauteile) {
		
		//Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
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
		
		
		
		//Für jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		for(int j=0; j < bauteile.size(); j++ ){
			
			
			//Formatiert Timestamp zu String
			Date d1 = new Date();
			d1 = bauteile.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
			
			//Formatiert Timestamp zu String
			Date d2 = new Date();
			d2 = bauteile.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
			
		
			
			
			bauteileTable.setText(j+1, 0, Integer.toString(bauteile.elementAt(j).getId()));
			bauteileTable.setText(j+1, 1, bauteile.elementAt(j).getName());
			bauteileTable.setText(j+1, 2, bauteile.elementAt(j).getBeschreibung());
			bauteileTable.setText(j+1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
			bauteileTable.setText(j+1, 4, s1);
			bauteileTable.setText(j+1, 5, s2);
			//bauteileTable.setWidget(j+1, 7, new Button "X");
			
			
			
			
			//Verknüpfung zu style.css damit die Tabelle richtig geilo aussieht!
			bauteileTable.setCellPadding(6);
			bauteileTable.getRowFormatter().addStyleName(0,  "watchListHeader");
			bauteileTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
			bauteileTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		}	
		
		//Bauteil-Tabelle zum Panel hinzugefügt damit das Ganze auch angezeigt wird 
		this.vPanel2.add(bauteileTable);
		
		Button btn3 = new Button("Update Table");
		btn3.addClickHandler(new Btn3ClickHandler());
		this.vPanel2.add(btn3);
		
		this.vPanel.add(vPanel2);
		this.vPanel.add(vPanel3);
		
	}
	
	

	public void showBauteil(Vector<Bauteil> bauteile) {

		
		//Objekt der Klasse FlexTable erstellen und mit Spaltenüberschriften belegen
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
				
				
				
				
				//Für jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
				for(int j=0; j < bauteile.size(); j++ ){
					
					//Formatiert Timestamp zu String
					Date d1 = new Date();
					d1 = bauteile.elementAt(j).getErstellungsDatum();
					String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
					
					//Formatiert Timestamp zu String
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
					
					
					
					
					
					//Verknüpfung zu style.css damit die Tabelle richtig geilo aussieht!
					findBauteilTable.setCellPadding(6);
					findBauteilTable.getRowFormatter().addStyleName(0,  "watchListHeader");
					findBauteilTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					findBauteilTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					
				
				//Bauteil-Tabelle zum Panel hinzugefügt damit das Ganze auch angezeigt wird 
				this.vPanel2.add(findBauteilTable);
				//this.vPanel.add(vPanel2);
		}
	}

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

		}
	}

	private class Btn3ClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			vPanel2.clear();
			serviceImpl.getAll();
		}
	}



	private class Btn1EditBauteilClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

		}
	}

}
