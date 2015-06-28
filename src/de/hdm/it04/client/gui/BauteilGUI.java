package de.hdm.it04.client.gui;






import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

import de.hdm.it04.client.gui.EnderzeugnisGUI.BtnSpeichernClickHandler;
import de.hdm.it04.shared.Bauteil;

public class BauteilGUI extends MainGUI {
	
	Bauteil btt = new Bauteil();
	
	TextBox suchen = new TextBox();
	
	
	TextBox name = new TextBox();
	TextBox materialBezeichnung = new TextBox();
	TextBox beschreibung = new TextBox();
	
	FlexTable flex = new FlexTable();
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public BauteilGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	
	
	
	public void menue(){
		
		
		HTML topic = new HTML("<h2>Was wollen Sie mit dem Bauteil tun?</h2>");

		this.vPanel.add(topic);
		
		

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
		
		this.vPanel.add(hPanel);
		
	}
	
	
	public void updateBauteil(Bauteil bt){
		
		btt.setId(bt.getId());
		btt.setErstellungsDatum(bt.getErstellungsDatum());
		btt.setAenderungsDatum(bt.getAenderungsDatum());
		
		

		
				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "Materialbezeichnung");
				flex.setText(0, 4, "erstellt am");
				flex.setText(0, 5, "ge채ndert am");
				flex.setText(0, 6, "Speichern");
				
				
				
				/**
				 * Timestamp wird f체r die Tabelle formatiert.
				 * 
				 */
				
				/*Date erstelltam = new Date();
				erstelltam = bt.getErstellungsDatum();
				String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(erstelltam);
				
				
				

				Date geaendertam = new Date();
				geaendertam = bt.getAenderungsDatum();
				String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
						geaendertam);*/
				Button Speichernbtn = new Button("speichern");
				Speichernbtn.addClickHandler(new SpeichernBtnClickHandler());
					
					
					/**
					 * Konvertieren der Bauteil-Daten und bef체llen der Tabelle
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
					 * Verkn체pfung zu style.css
					 */
				
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanel.add(flex);
					
			}
	
public void getBauteil(Bauteil bt){
		
		
				flex.setText(0, 0, "ID");
				flex.setText(0, 1, "Name");
				flex.setText(0, 2, "Beschreibung");
				flex.setText(0, 3, "Materialbezeichnung");
				flex.setText(0, 4, "erstellt am");
				flex.setText(0, 5, "ge채ndert am");
				flex.setText(0, 6, "훞dern");
				
				
				
				/**
				 * Timestamp wird f체r die Tabelle formatiert.
				 * 
				 */
				
				/*Date erstelltam = new Date();
				erstelltam = bt.getErstellungsDatum();
				String erstelltams = DateTimeFormat.getMediumDateTimeFormat().format(erstelltam);
				
				
				

				Date geaendertam = new Date();
				geaendertam = bt.getAenderungsDatum();
				String geaendertams = DateTimeFormat.getMediumDateTimeFormat().format(
						geaendertam);*/
				Button aendernBtn = new Button("Aendern");
				aendernBtn.addClickHandler(new AendernBtnClickHandler());
					
					
					/**
					 * Konvertieren der Bauteil-Daten und bef체llen der Tabelle
					 */
				
					flex.setText(1, 0, Integer.toString(bt.getId()));
					flex.setText(1, 1, bt.getName());
					flex.setText(1, 2, bt.getBeschreibung());
					flex.setText(1, 3, bt.getMaterialBezeichnung());
					//flex.setText(1, 4, erstelltams);
					//flex.setText(1, 5, geaendertams);
					flex.setWidget(1, 6, aendernBtn);
					
					
					
				
				
					//this.vPanel.add(flex);
					
					/**
					 * Verkn체pfung zu style.css
					 */
				
					flex.setCellPadding(6);
					flex.getRowFormatter().addStyleName(0,  "watchListHeader");
					flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
					flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
					this.vPanel.add(flex);
					
			}
	
	
	





	public class AnlegenBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			serviceImpl.createBauteil();
				
		}
	}
	public class SpeichernBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			
			
			btt.setName(name.getText());
			btt.setBeschreibung(beschreibung.getText());
			btt.setMaterialBezeichnung(materialBezeichnung.getText());
			
			serviceImpl.updateBauteil(btt);
			
		}
	}
	
	public class AendernBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			vPanel.clear();
			
			
			btt.setName(name.getText());
			btt.setBeschreibung(beschreibung.getText());
			btt.setMaterialBezeichnung(materialBezeichnung.getText());
			
			serviceImpl.updateBauteil(btt);
			
		}
	}
	
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

