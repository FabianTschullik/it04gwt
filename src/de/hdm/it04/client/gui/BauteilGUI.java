package de.hdm.it04.client.gui;






import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;
import java.util.Vector;

import de.hdm.it04.client.gui.EnderzeugnisGUI.BtnSpeichernClickHandler;
import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends MainGUI {
	
	/**
	 * lokal Instanz eines Bauteils
	 */
	
	Bauteil btt = new Bauteil();
	
	/**
	 * TextBox zum Suchen von Bauteilen
	 */
	
	TextBox suchen = new TextBox();
	
	/**
	 * TextBoxe, in denen Name, Materialbezeichnung und Beschreibung
	 * hinzugef�gt und ge�ndert werden k�nnen
	 */
	
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	
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

		Button BearbeitenBtn1 = new Button("Bearbeiten");
		//BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.hPanel.add(BearbeitenBtn1);
	  
		Button LoeschenBtn1 = new Button("Loeschen");
		//LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.hPanel.add(LoeschenBtn1);	
		
		
		suchen.setText("id");
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
	
	public void updateBauteil(Bauteil bt){
		
		/**
		 * Parameter des leeren Objekts werden f�r kurze Zeit 
		 * in ein lokales Objekt gespeichert, welches dann
		 * den eingegebenen Parametern gef�llt wird
		 * 
		 */
		btt.setId(bt.getId());
		btt.setErstellungsDatum(bt.getErstellungsDatum());
		btt.setAenderungsDatum(bt.getAenderungsDatum());
		
		/**
		 * �berschriften der Tabelle
		 */
		
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "Materialbezeichnung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		flex.setText(0, 6, "Speichern");
		
		/**
		 * Erstellen des Speicher Buttons incl. ClickHandler
		 */
		
		Button Speichernbtn = new Button("speichern");
		Speichernbtn.addClickHandler(new SpeichernBtnClickHandler());
		
			
				/**
				 * Timestamp wird für die Tabelle formatiert.
				 * 
				 */
				
				/*Date erstelltam = new Date();
				erstelltam = bt.getErstellungsDatum();
				String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(erstelltam);
				
				
				

				Date geaendertam = new Date();
				geaendertam = bt.getAenderungsDatum();
				String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
						geaendertam);*/
				
					
					
					/**
					 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
					 * letzte Spalte zeigt den Speichern Button
					 */
				
					flex.setText(1, 0, Integer.toString(bt.getId()));
					flex.setWidget(1, 1, name);
					flex.setWidget(1, 2, beschreibung);
					flex.setWidget(1, 3, materialBezeichnung);
					//flex.setText(1, 4, erstelltams);
					//flex.setText(1, 5, geaendertams);
					flex.setWidget(1, 6, Speichernbtn);
					
					
					
				
				
					//this.vPanel.add(flex);
					
					/**
					 * Verknuepfung zu style.css
					 */
				
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanel.add(flex);
		
			
	}
			
/**
 * Ver�ndern eines schon vorhanden Objekts		
 * @param bt Vollst�ndiges Bauteil wird �bergeben
 * Daten werden in die jeweiligen Felder eingef�llt. 
 * Name, Beschreibung und Materialbezeichnung werden in TextBoxen eingef�gt,
 * damit sie ver�nderbar sind.
 */
	
public void updateBauteil2(Bauteil bt){
	
	/**
	 * Nur Id und Erstellungdatum werden lokal gespeichert,
	 * da diese unver�ndert an die DB zur�ck gehen
	 */
		
		btt.setId(bt.getId());
		btt.setErstellungsDatum(bt.getErstellungsDatum());

		/**
		 *�berschriften der Tabelle 
		 */
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "Materialbezeichnung");
		flex.setText(0, 4, "erstellt am");
		flex.setText(0, 5, "geändert am");
		flex.setText(0, 6, "Speichern");
		
		/**
		 * Speichern Button erstellen mit ClickHandler
		 */
		Button Speichernbtn = new Button("speichern");
		Speichernbtn.addClickHandler(new SpeichernBtnClickHandler());
		
		/**
		 * Timestamp wird für die Tabelle formatiert.
		 * 
		 */
		
		/*Date erstelltam = new Date();
		erstelltam = bt.getErstellungsDatum();
		String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(erstelltam);
		
		
		

		Date geaendertam = new Date();
		geaendertam = bt.getAenderungsDatum();
		String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
				geaendertam);*/
		
			
			
			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 * Name, Beschreibung und Materialbezeichnung werden in TextBoxen ausgegeben
			 */
		name.setText(bt.getName());
		beschreibung.setText(bt.getBeschreibung());
		materialBezeichnung.setText(bt.getMaterialBezeichnung());
		
		flex.setText(1, 0, Integer.toString(bt.getId()));
		flex.setWidget(1, 1, name);
		flex.setWidget(1, 2, beschreibung);
		flex.setWidget(1, 3, materialBezeichnung);
		//flex.setText(1, 4, erstelltams);
		//flex.setText(1, 5, geaendertams);
		flex.setWidget(1, 6, Speichernbtn);
		
		
		
	
	
		//this.vPanel.add(flex);
		
		/**
		 * Verknüpfung zu style.css
		 */
	
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0,  "watchListHeader");
		flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		this.vPanel.add(flex);
}
		

/**
 * Ein Bauteil wird angezeigt					
 * @param bt
 */
	
	
public void getBauteil(Bauteil bt){
	
	/**
	 * ID wird lokal gepseichert, 
	 * falls das Objekt ge�ndert werden soll
	 */
			
		btt.setId(bt.getId());	
		
		/**
		 * �berschriften der Spaten
		 */
		
		
				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "Materialbezeichnung");
				flex.setText(0, 4, "erstellt am");
				flex.setText(0, 5, "geändert am");
				flex.setText(0, 6, "�ndern");
				
				
				
				/**
				 * Timestamp wird für die Tabelle formatiert.
				 * 
				 */
				
				/*Date erstelltam = new Date();
				erstelltam = bt.getErstellungsDatum();
				String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(erstelltam);
				
				
				

				Date geaendertam = new Date();
				geaendertam = bt.getAenderungsDatum();
				String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
						geaendertam);*/
				
				/**
				 * Erstellen eines Aendern und Loeschen Button mit ClickHandler
				 */
				Button aendernBtn = new Button("Aendern");
				aendernBtn.addClickHandler(new AendernBtnClickHandler());
				Button loeschenBtn = new Button("Loeschen");
				loeschenBtn.addClickHandler(new LoeschenBtnClickHandler());
					
					
					/**
					 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
					 */
				
					flex.setText(1, 0, Integer.toString(bt.getId()));
					flex.setText(1, 1, bt.getName());
					flex.setText(1, 2, bt.getBeschreibung());
					flex.setText(1, 3, bt.getMaterialBezeichnung());
					//flex.setText(1, 4, erstelltams);
					//flex.setText(1, 5, geaendertams);
					flex.setWidget(1, 6, aendernBtn);
					flex.setWidget(1, 7, loeschenBtn);
					
					
					
				
				
					//this.vPanel.add(flex);
					
					/**
					 * Verknüpfung zu style.css
					 */
				
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanel.add(flex);
					
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

/**
 * Anzeigen einer Meldung, wenn das Bauteil gel�scht wurde
 * @param meldung 
 * "Das Bauteil wurde erfolgreich gel�scht"
 */
public void showMeldung(String meldung){
	/**
	 * Erstellen eines Labels, damit die Meldung angezeigt werden kann
	 */
	Label meldung1= new Label(meldung);
	
	this.vPanel.add(meldung1);
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
			
			
			btt.setName(name.getText());
			btt.setBeschreibung(beschreibung.getText());
			btt.setMaterialBezeichnung(materialBezeichnung.getText());
			
			/**
			 * Das Objekt wird an die ClientImpl weiter gegeben
			 */
			
			serviceImpl.updateBauteil(btt);
			
			/**
			 * Danach werden die TextBoxen wieder geleert
			 */
			
			name.setText("");
			beschreibung.setText("");
			materialBezeichnung.setText("");
			

			
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
			int id = btt.getId();
			serviceImpl.getBauteil2(id);
			
			;
			
			
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
			int id = btt.getId();
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
			
			serviceImpl.showAllBauteile();
			
			
				
		}
	}
	
	/**
	 * �ber den Suchenbutton kann man Bauteile suchen
	 * indem man die ID eingibt.
	 *Dann wird die Methode getBauteile in der Klasse ClientImpl aufgerufen
	 */
	
	public class SuchenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			String id1 = suchen.getText();
			int id = Integer.parseInt(id1);
			serviceImpl.getBauteil(id);

		
		}
	}
}

