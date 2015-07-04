package de.hdm.it04.client.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.editor.AlertGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.editor.EnderzeugnisGUI;
import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.client.service.report.It04gwtServiceReportAsync;
import de.hdm.it04.shared.Enderzeugnis;


public class MaterialbedarfGUI {
	
	AlertGUI alertGUI = new AlertGUI();
	
	TextBox txtSuchen = new TextBox();
	private final It04gwtServiceReportAsync smsReport = GWT.create(It04gwtServiceReport.class);
	
	
	/**
	 * Suchfunktion
	 */
	public Widget suchen(){
		VerticalPanel vPanel = new VerticalPanel();
		
		txtSuchen.setText("id oder Name");
		vPanel.add(txtSuchen);
		
		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String ezSuche = txtSuchen.getText();
				
				if (ezSuche.matches("[0-9]+")){
					int id = Integer.parseInt(ezSuche);
					smsReport.getEnderzeugnis(id, new AsyncCallback<Vector<Enderzeugnis>>() {

						public void onFailure(Throwable arg0) {
							
						}

						public void onSuccess(Vector<Enderzeugnis> result) {
							ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));
							
						}
					});	
				}
				String name = ezSuche;
			
				smsReport.getEnderzeugnis(name, new AsyncCallback<Vector<Enderzeugnis>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Enderzeugnis> result) {
						ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));;		
					}
				});	
			}
		} );
		return vPanel;
	}	
	
	public Widget showEnderzeugnisseMaterialbedarf(Vector<Enderzeugnis> enderzeugnisse){
		
			/**
			 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
			 */
			FlexTable enderzeugnisTable = new FlexTable();
			enderzeugnisTable.setText(0,0,"ID");
			enderzeugnisTable.setText(0,1,"Name");
			enderzeugnisTable.setText(0,2,"Beschreibung");
			enderzeugnisTable.setText(0,3,"Preis");	
			enderzeugnisTable.setText(0,4,"Menge");
			enderzeugnisTable.setText(0,5,"Materialbedarf berechnen");
			
			/**
			 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
			 */
			for(int j=0; j < enderzeugnisse.size(); j++ ){
				

				/**
				 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
				 */
				Button btnMaterialbedarfBerechnen = new Button("Berechnen");
				btnMaterialbedarfBerechnen.addClickHandler(new btnMaterialbedarfBerechnenClickHandler());
				//this.vPanelCreate.add(editBtn);

				/**
				 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
				 */
				enderzeugnisTable.setText(j+1, 0, Integer.toString(enderzeugnisse.elementAt(j).getId()));
				enderzeugnisTable.setText(j+1, 1, enderzeugnisse.elementAt(j).getName());
				enderzeugnisTable.setText(j+1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
				enderzeugnisTable.setText(j+1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
				//enderzeugnisTable.setText(j+1, 4, TEXTBOX MISSING);

			
				/**
				 * Einfuegen der Buttons in die Tabelle
				 */
				enderzeugnisTable.setWidget(j+1, 5, btnMaterialbedarfBerechnen);
				
				
				/**
				 * Verknuepfung zu style.css
				 */
				enderzeugnisTable.setCellPadding(6);
				enderzeugnisTable.getRowFormatter().addStyleName(0,  "watchListHeader");
				enderzeugnisTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
				enderzeugnisTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
			}	
			
			/**
			 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
			 */
			
			
			return enderzeugnisTable;
			
		}
	
	public class btnMaterialbedarfBerechnenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
		}	
	}
	
}