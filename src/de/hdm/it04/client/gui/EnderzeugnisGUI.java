package de.hdm.it04.client.gui;

import java.security.Timestamp;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.gui.BauteilGUI.AnlegenBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.ShowAllBtn1ClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.SpeichernBtnClickHandler;
import de.hdm.it04.client.gui.BauteilGUI.SuchenBtnClickHandler;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

public class EnderzeugnisGUI extends MainGUI{

	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();

	
	

	public EnderzeugnisGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
public void menue(){
		
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML("<h2>Was wollen Sie mit dem Bauteil tun?</h2>");
		
		/**
		 * vPanel wird dem HTML Bereich zugeordnet
		 */
		
		TextBox suchen = new TextBox();

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


public void updateEnderzeugnis(Enderzeugnis ez){
	
	/**
	 * Nur Id und Erstellungdatum werden lokal gespeichert,
	 * da diese unver�ndert an die DB zur�ck gehen
	 */
		
		/**
		 *�berschriften der Tabelle 
		 */
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Preis");
		flex.setText(0, 3, "erstellt am");
		flex.setText(0, 4, "geändert am");
		flex.setText(0, 5, "Speichern");
		flex.setText(0, 5, "Abbrechen");
		
		Button Speichernbtn = new Button("speichern");
		Speichernbtn.addClickHandler(new SpeichernBtnClickHandler());
		
		/**
	
		
		/**
		 * Verknüpfung zu style.css
		 */
	
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0,  "watchListHeader");
		flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		this.vPanel.add(flex);
}


	
	
	public void showSuccess() {

		Window.alert("Bauteil wurde erfolgreich angelegt");
	}
	
	
	
	public void showEnderzeugnis(Enderzeugnis ez) {
		
		
		
		
	}
	
	


		public class AnlegenBtnClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				vPanel.clear();
				serviceImpl.createEnderzeugnis();
			
					
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
				
				

			
			}
		}
	

}
