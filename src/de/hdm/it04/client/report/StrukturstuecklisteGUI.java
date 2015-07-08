package de.hdm.it04.client.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import de.hdm.it04.client.editor.AlertGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;

/**
 * 
 * @author Schwab
 *
 */

public class StrukturstuecklisteGUI {

	AlertGUI alertGUI = new AlertGUI();

	TextBox txtSuchen = new TextBox();


	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);



	VerticalPanel vPanel = new VerticalPanel();
	FlexTable baugruppeReportTable = new FlexTable();

	/**
	 * Suche nach Baugruppe per Name oder ID
	 */
	public Widget suchen() {
		VerticalPanel vPanel = new VerticalPanel();

		txtSuchen.setText("ID/Name Baugruppe");
		vPanel.add(txtSuchen);

		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String bgSuche = txtSuchen.getText();

				if (bgSuche.matches("[0-9]+")) {
					int id = Integer.parseInt(bgSuche);
					sms.getBaugruppe(id,
							new AsyncCallback<Vector<Baugruppe>>() {

								public void onFailure(Throwable arg0) {
									// alertGUI.load("Enderzeugnis konnte nicht gefunden werden",
									// "red");
								}

								public void onSuccess(Vector<Baugruppe> result) {
									ContentContainer
											.getInstance()
											.setContent(
													new StrukturstuecklisteGUI()
															.showBaugruppeStrukturstueckliste(result));
									alertGUI.load(
											"Enderzeugnis wurde gefunden",
											"green");
								}
							});
				}
				String name = bgSuche;

				sms.getBaugruppe(name,
						new AsyncCallback<Vector<Baugruppe>>() {

							public void onFailure(Throwable arg0) {
								// alertGUI.load("Enderzeugnis konnte nicht gefunden werden",
								// "red");
							}

							public void onSuccess(Vector<Baugruppe> result) {
								ContentContainer
										.getInstance()
										.setContent(
												new StrukturstuecklisteGUI()
														.showBaugruppeStrukturstueckliste(result));
								;
								alertGUI.load("Enderzeugnis wurde gefunden",
										"green");
							}
						});
			}
		});
		return vPanel;
	}

	public Widget showBaugruppeStrukturstueckliste(Vector<Baugruppe> baugruppen) {

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */

		
		baugruppeReportTable.setText(0,0,"ID");
		baugruppeReportTable.setText(0,1,"Name");
		baugruppeReportTable.setText(0,2,"Beschreibung");
		baugruppeReportTable.setText(0,3,"Strukturstueckliste erstellen");

		
		baugruppeReportTable.setText(0, 0, "ID");
		baugruppeReportTable.setText(0, 1, "Name");
		baugruppeReportTable.setText(0, 2, "Beschreibung");
		baugruppeReportTable.setText(0, 3, "Strukturstueckliste erstellen");


		/**
		 * Fuer jede Baugruppe werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < baugruppen.size(); j++) {

			/**
			 * Button, um Strukturstueckliste zu erstellen
			 */
			Button btnStrukturstuecklisteErstellen = new Button("Erstellen");
			btnStrukturstuecklisteErstellen.addClickHandler(new ClickHandlerErstellen());
						
					

			/**
			 * Konvertieren der Baugruppe-Daten und befuellen der Tabelle
			 */
			baugruppeReportTable.setText(j + 1, 0,
					Integer.toString(baugruppen.elementAt(j).getId()));
			baugruppeReportTable.setText(j + 1, 1, baugruppen.elementAt(j)
					.getName());
			baugruppeReportTable.setText(j + 1, 2, baugruppen.elementAt(j)
					.getBeschreibung());

			/**
			 * Einfuegen der Buttons in die Tabelle
			 */
			baugruppeReportTable.setWidget(j + 1, 3,
					btnStrukturstuecklisteErstellen);

			/**
			 * Verknuepfung zu style.css
			 */
			baugruppeReportTable.setCellPadding(6);
			baugruppeReportTable.getRowFormatter().addStyleName(0,
					"watchListHeader");
			baugruppeReportTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			baugruppeReportTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}
		
		
	

		/**
		 * Baugruppe-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		this.vPanel.add(baugruppeReportTable);

		return vPanel;
	}
	private class ClickHandlerErstellen implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
			Cell cell = baugruppeReportTable.getCellForEvent(event);

			int rowIndex = cell.getRowIndex();
			String id1 = baugruppeReportTable.getText(rowIndex, 0);
			int id = Integer.parseInt(id1);
			
			
			sms.getStrukturstueckliste(id, new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("asd");
					
				}

				@Override
				public void onSuccess(String result) {
					
					RootPanel.get("content").clear();
					HTML html = new HTML(result);
					RootPanel.get("content").add(html);
					
					
				}
			});
			
		}
		
		
		
	}

}
