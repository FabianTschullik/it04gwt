package de.hdm.it04.client.bauteil;

import java.util.Vector;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

import de.hdm.it04.client.ShowCase;
//import de.hdm.it04.client.gui.BauteilGUI.AendernBtnClickHandler;
//import de.hdm.it04.client.gui.BauteilGUI.LoeschenBtnClickHandler;
import de.hdm.it04.shared.Bauteil;

public class ShowBauteileForm extends ShowCase {

	
	private String headlineText;
	private String headlineTextStyle;
	Vector<Bauteil> bauteile;
	
	
	public ShowBauteileForm(){
		this.headlineText = "Sie sehen das gesuchte Bauteil";
		this.headlineTextStyle = "formTitle";
	}
	
	
	public ShowBauteileForm(Vector<Bauteil> bauteile1) {
		
		this.bauteile = bauteile1;
		
		
		
	}
	@Override
	protected String getHeadlineText() {
		return this.headlineText;
	}

	@Override
	protected String getHeadlineTextStyle() {
		return this.headlineTextStyle;
		
	}

	@Override
	protected void run() {
		
		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
		 */
		FlexTable bauteileTable = new FlexTable();
		bauteileTable.setText(0,0,"ID");
		bauteileTable.setText(0,1,"Name");
		bauteileTable.setText(0,2,"Beschreibung");
		bauteileTable.setText(0,3,"Materialbezeichnung");	
		bauteileTable.setText(0,4,"Erstellt am");
		bauteileTable.setText(0,5,"Zuletzt geaendert am");
		bauteileTable.setText(0,6,"letzter Bearbeiter");
		//bauteileTable.setText(0,7,"Edit");
		//bauteileTable.setText(0,8,"Delete");
		
		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		 */
		//for(int j=0; j < bauteile.size(); j++ ){
			
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
			//bauteileTable.setText(j+1, 0, Integer.toString(bauteile.elementAt(j).getId()));
			//bauteileTable.setText(j+1, 1, bauteile.elementAt(j).getName());
			//bauteileTable.setText(j+1, 2, bauteile.elementAt(j).getBeschreibung());
			//bauteileTable.setText(j+1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
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
			
			this.add(bauteileTable);
		}				
	}

	
			
		
		
	

