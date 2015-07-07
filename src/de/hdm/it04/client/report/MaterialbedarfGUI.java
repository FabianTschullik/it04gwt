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
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.client.service.report.It04gwtServiceReportAsync;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * 
 * @author Schwab
 *
 */

public class MaterialbedarfGUI {
	
	AlertGUI alertGUI = new AlertGUI();
	
	TextBox txtSuchen = new TextBox();
	private final It04gwtServiceReportAsync smsReport = GWT.create(It04gwtServiceReport.class);
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	VerticalPanel vPanel = new VerticalPanel();
	TextBox mengeMaterial = new TextBox();
	
	/**
	 * Suchfunktion
	 */
	public Widget suchen(){
		VerticalPanel vPanel = new VerticalPanel();
		
		txtSuchen.setText("ID/Name Enderzeugnis");
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
							//alertGUI.load("Enderzeugnis konnte nicht gefunden werden", "red");	
						}

						public void onSuccess(Vector<Enderzeugnis> result) {
							ContentContainer.getInstance().setContent(new MaterialbedarfGUI().showEnderzeugnisseMaterialbedarf(result));
							alertGUI.load("Enderzeugnis wurde gefunden", "green");
						}
					});	
				}
				String name = ezSuche;		
				
				smsReport.getEnderzeugnis(name, new AsyncCallback<Vector<Enderzeugnis>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Enderzeugnis> result) {
						ContentContainer.getInstance().setContent(new MaterialbedarfGUI().showEnderzeugnisseMaterialbedarf(result));;
						alertGUI.load("Enderzeugnis wurde gefunden", "green");
						
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
			FlexTable enderzeugnisReportTable = new FlexTable();
			enderzeugnisReportTable.setText(0,0,"ID");
			enderzeugnisReportTable.setText(0,1,"Name");
			enderzeugnisReportTable.setText(0,2,"Beschreibung");
			enderzeugnisReportTable.setText(0,3,"Preis");	
			enderzeugnisReportTable.setText(0,4,"Menge");
			enderzeugnisReportTable.setText(0,5,"Materialbedarf berechnen");
			
			/**
			 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
			 */
			for(int j=0; j < enderzeugnisse.size(); j++ ){
				

				/**
				 * Button, um Editieren des Bauteils innerhalb der Tabelle aufzurufen
				 */
				Button btnMaterialbedarfBerechnen = new Button("Berechnen");
				btnMaterialbedarfBerechnen.addClickHandler(new btnMaterialbedarfBerechnenClickHandler());

				/**
				 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
				 */
				enderzeugnisReportTable.setText(j+1, 0, Integer.toString(enderzeugnisse.elementAt(j).getId()));
				enderzeugnisReportTable.setText(j+1, 1, enderzeugnisse.elementAt(j).getName());
				enderzeugnisReportTable.setText(j+1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
				enderzeugnisReportTable.setText(j+1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
				
				/**
				 * Einfuegen des Eingabefeldes fuer die zu berechnende Menge Enderzeugnisse
				 */
				enderzeugnisReportTable.setWidget(j+1, 4, mengeMaterial);

			
				/**
				 * Einfuegen der Buttons in die Tabelle
				 */
				enderzeugnisReportTable.setWidget(j+1, 5, btnMaterialbedarfBerechnen);
				
				
				/**
				 * Verknuepfung zu style.css
				 */
				enderzeugnisReportTable.setCellPadding(6);
				enderzeugnisReportTable.getRowFormatter().addStyleName(0,  "watchListHeader");
				enderzeugnisReportTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
				enderzeugnisReportTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
			}	
			
			/**
			 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
			 */

			this.vPanel.add(enderzeugnisReportTable);

			return enderzeugnisReportTable;
			
		}
	
	public class btnMaterialbedarfBerechnenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
		}	
	}
	
}
