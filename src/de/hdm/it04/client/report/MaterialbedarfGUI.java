package de.hdm.it04.client.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.Cell;

import de.hdm.it04.client.editor.AlertGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * Die Klasse MaterialbedarfGUI ermoeglicht es dem User, den Materialbedarf von
 * Enderzeugnissen als HTML erstellen und anzeigen zu lassen. Hier wird das User Interface dafuer definiert.
 * @author Schwab, Tschullik
 *
 */

public class MaterialbedarfGUI {

	AlertGUI alertGUI = new AlertGUI();

	FlexTable enderzeugnisReportTable = new FlexTable();

	TextBox txtSuchen = new TextBox();

	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	VerticalPanel vPanel = new VerticalPanel();
	TextBox mengeMaterial = new TextBox();

	/**
	 * Suchfunktion
	 */
	public Widget suchen() {
		VerticalPanel vPanel = new VerticalPanel();

		txtSuchen.setText("ID/Name Enderzeugnis");
		vPanel.add(txtSuchen);

		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String ezSuche = txtSuchen.getText();

				if (ezSuche.matches("[0-9]+")) {
					int id = Integer.parseInt(ezSuche);
					sms.getEnderzeugnis(id,
							new AsyncCallback<Vector<Enderzeugnis>>() {

								public void onFailure(Throwable arg0) {
									// alertGUI.load("Enderzeugnis konnte nicht gefunden werden",
									// "red");
								}

								public void onSuccess(
										Vector<Enderzeugnis> result) {
									ContentContainer
											.getInstance()
											.setContent(
													new MaterialbedarfGUI()
															.showEnderzeugnisseMaterialbedarf(result));
									alertGUI.load(
											"Enderzeugnis wurde gefunden",
											"green");
								}
							});
				}
				String name = ezSuche;

				sms.getEnderzeugnis(name,
						new AsyncCallback<Vector<Enderzeugnis>>() {

							public void onFailure(Throwable arg0) {

							}

							public void onSuccess(Vector<Enderzeugnis> result) {
								ContentContainer
										.getInstance()
										.setContent(
												new MaterialbedarfGUI()
														.showEnderzeugnisseMaterialbedarf(result));
								;
								alertGUI.load("Enderzeugnis wurde gefunden",
										"green");

							}
						});

			}
		});
		return vPanel;
	}

	public Widget showEnderzeugnisseMaterialbedarf(
			Vector<Enderzeugnis> enderzeugnisse) {

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */

		enderzeugnisReportTable.setText(0, 0, "ID");
		enderzeugnisReportTable.setText(0, 1, "Name");
		enderzeugnisReportTable.setText(0, 2, "Beschreibung");
		enderzeugnisReportTable.setText(0, 3, "Preis");
		enderzeugnisReportTable.setText(0, 4, "Menge");
		enderzeugnisReportTable.setText(0, 5, "Materialbedarf berechnen");

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < enderzeugnisse.size(); j++) {

			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle
			 * aufzurufen
			 */

			enderzeugnisReportTable.setText(0, 0, "ID");
			enderzeugnisReportTable.setText(0, 1, "Name");
			enderzeugnisReportTable.setText(0, 2, "Beschreibung");
			enderzeugnisReportTable.setText(0, 3, "Preis");
			enderzeugnisReportTable.setText(0, 4, "Menge");
			enderzeugnisReportTable.setText(0, 5, "Materialbedarf berechnen");

			Button btnMaterialbedarfBerechnen = new Button("Berechnen");
			btnMaterialbedarfBerechnen
					.addClickHandler(new btnMaterialbedarfBerechnenClickHandler());

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			enderzeugnisReportTable.setText(j + 1, 0,
					Integer.toString(enderzeugnisse.elementAt(j).getId()));
			enderzeugnisReportTable.setText(j + 1, 1,
					enderzeugnisse.elementAt(j).getName());
			enderzeugnisReportTable.setText(j + 1, 2,
					enderzeugnisse.elementAt(j).getBeschreibung());
			enderzeugnisReportTable.setText(j + 1, 3,
					Double.toString(enderzeugnisse.elementAt(j).getPreis()));

			/**
			 * Einfuegen des Eingabefeldes fuer die zu berechnende Menge
			 * Enderzeugnisse
			 */
			enderzeugnisReportTable.setWidget(j + 1, 4, mengeMaterial);

			/**
			 * Einfuegen der Buttons in die Tabelle
			 */
			enderzeugnisReportTable.setWidget(j + 1, 5,
					btnMaterialbedarfBerechnen);

			/**
			 * Verknuepfung zu style.css
			 */
			enderzeugnisReportTable.setCellPadding(6);
			enderzeugnisReportTable.getRowFormatter().addStyleName(0,
					"watchListHeader");
			enderzeugnisReportTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			enderzeugnisReportTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */

		this.vPanel.add(enderzeugnisReportTable);

		return enderzeugnisReportTable;

	}

	public class btnMaterialbedarfBerechnenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Cell cell = enderzeugnisReportTable.getCellForEvent(event);

			int rowIndex = cell.getRowIndex();
			String id1 = enderzeugnisReportTable.getText(rowIndex, 0);
			int id = Integer.parseInt(id1);
			int menge = Integer.parseInt(mengeMaterial.getText());

			sms.getMaterialbedarf(id, menge,
					new AsyncCallback<Vector<Bauteil>>() {

						@Override
						public void onFailure(Throwable caught) {

						}

						@Override
						public void onSuccess(Vector<Bauteil> result) {
							RootPanel.get("content").clear();
							int rows = result.size() + 1;

							Grid grid = new Grid(rows, 2);

							grid.setHTML(0, 0, "Bauteile");
							grid.setHTML(0, 1, "Anzahl");

							for (int i = 0; i < rows - 1; i++) {
								grid.setHTML(i + 1, 0, result.elementAt(i)
										.getName());
								grid.setHTML(i + 1, 1, Integer.toString(result
										.elementAt(i).getAnzahl()));

							}

							RootPanel.get("content").add(grid);

						}
					});

		}

		// TODO Auto-generated method stub
	}

}
