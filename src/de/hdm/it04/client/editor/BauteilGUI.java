package de.hdm.it04.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;
import java.util.Vector;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;
/**
 * Die Klasse BauteilGUI ermöglicht es dem User, Bauteil Objekte zu erstellen und zu verwalten.
 * Hier wird das User Interface dafuer definiert. 
 * @author Geier, Voelker
 *
 */
public class BauteilGUI {
	
	AlertGUI alertGUI = new AlertGUI();
	
	TextBox textBoxSuchen = new TextBox();
	
	 String user = It04gwtEditor.user;
	
	
	/**
	 * lokal Instanz eines Bauteils
	 */
	
	Bauteil bt = new Bauteil();
	Vector<Bauteil> btV = new Vector<Bauteil>();
	FlexTable bauteileTable = new FlexTable();
	
	/**
	 * TextBox zum Suchen von Bauteilen
	 */
	
	TextBox suchen = new TextBox();
	
	
	/**
	 * TextBoxe, in denen Name, Materialbezeichnung und Beschreibung
	 * hinzugef�gt und ge�ndert werden k�nnen
	 */
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	private TextBox txtSuchen = new TextBox();
	TextBox txtName = new TextBox();
	TextBox txtMaterialBezeichnung = new TextBox();
	TextArea txtBeschreibung = new TextArea();
	
	/**
	 * FlexTable zum anzeigen der Bauteile/ des Bauteils
	 */
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public Widget suchen(){
		VerticalPanel vPanel = new VerticalPanel();
		
		txtSuchen.setText("id oder Name");
		vPanel.add(txtSuchen);
		
		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
	String btSuche = txtSuchen.getText();
				
				if (btSuche.matches("[0-9]+")){
					int id = Integer.parseInt(btSuche);
					sms.getBauteil(id, new AsyncCallback<Vector<Bauteil>>() {

						public void onFailure(Throwable arg0) {
						
							
						}

						
						public void onSuccess(Vector<Bauteil> result) {
							ContentContainer.getInstance().setContent(new BauteilGUI().showAllBauteile(result));
							
						}
					});
					
				}
			String name = btSuche;
			
				sms.getBauteil(name, new AsyncCallback<Vector<Bauteil>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Bauteil> result) {
						ContentContainer.getInstance().setContent(new BauteilGUI().showAllBauteile(result));;
						
						
					}
				});
				
				
				
			}
		} );
			
		
		
		return vPanel;
	}
	

	
	// ----------------------------------------------------------------------------
		// ----------------------- Form zum Anlegen eines BT
		// --------------------------
		// ----------------------------------------------------------------------------
		public Widget showAnlegenForm(Bauteil bauteil) {

			
			

			this.bt = bauteil;
			txtName.setText(bt.getName());
			txtBeschreibung.setText(bt.getBeschreibung());
			txtMaterialBezeichnung.setText(bt.getMaterialBezeichnung());

			// Create a table to layout the form options
			FlexTable layout = new FlexTable();
			layout.setCellSpacing(6);
			FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

			// Add a title to the form
			layout.setHTML(0, 0, "<h3>Bauteil anlegen/bearbeiten<h3>");
			cellFormatter.setColSpan(0, 0, 2);
			cellFormatter.setHorizontalAlignment(0, 0,
					HasHorizontalAlignment.ALIGN_CENTER);

			Button btnSpeichern = new Button("Speichern");
			btnSpeichern.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					bt.setName(txtName.getText());
					bt.setMaterialBezeichnung(txtMaterialBezeichnung.getText());
					bt.setBeschreibung(txtBeschreibung.getText());
					bt.setLetzterBearbeiter(user);
					
					sms.updateBauteil(bt, new AsyncCallback<Vector<Bauteil>>() {

						@Override
						public void onFailure(Throwable caught) {
							new AlertGUI().load("Bauteil konnte nicht gespeichert werden", "red");
							
						}

						@Override
						public void onSuccess(Vector<Bauteil> result) {
							new AlertGUI().load("Bauteil wurde erfolgreich gespeichert", "green");
							ContentContainer.getInstance().setContent(new BauteilGUI().showAllBauteile(result));
							
						}
					});

			

				}
			});

			Button btnAbbrechen = new Button("Abbrechen");
			btnAbbrechen.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					sms.deleteBauteil(bt.getId(), new AsyncCallback() {

						@Override
						public void onFailure(Throwable caught) {
							new AlertGUI()
									.load("Bauteil konnte nicht gelöscht werden",
											"red");

						}

						@Override
						public void onSuccess(Object result) {
							new AlertGUI().load("Vorgang wurde erfolgreich abgebrochen",
									"green");
							
						}

						
						
						
						
					});
					
					ContentContainer.getInstance().setContent(new Welcome().load());
					

				}
			});

			// Add some standard form options
			layout.setHTML(1, 0, "ID");
			layout.setText(1, 1, Integer.toString(bt.getId()));
			layout.setHTML(2, 0, "Name");
			layout.setWidget(2, 1, txtName);
			layout.setHTML(3, 0, "Beschreibung");
			layout.setWidget(3, 1, txtBeschreibung);
			layout.setHTML(4, 0, "Materialbezeichnung");
			layout.setWidget(4, 1, txtMaterialBezeichnung);
			layout.setWidget(5, 0, btnSpeichern);
			layout.setWidget(5, 1, btnAbbrechen);

			// Wrap the content in a DecoratorPanel
			DecoratorPanel decPanel = new DecoratorPanel();
			decPanel.setWidget(layout);

			this.vPanel.add(decPanel);
			return vPanel;
		}

		// ----------------------------------------------------------------------------
		// ----------------------- Ende Form zum Anlegen eines BT
		// --------------------------
		// ----------------------------------------------------------------------------




			

			
/**
 * Ver�ndern eines schon vorhanden Objekts		
 * @param bt Vollst�ndiges Bauteil wird �bergeben
 * Daten werden in die jeweiligen Felder eingef�llt. 
 * Name, Beschreibung und Materialbezeichnung werden in TextBoxen eingef�gt,
 * damit sie ver�nderbar sind.
 */
	

		

/**
 * Ein Bauteil wird angezeigt					
 * @param bt
 */
	
	

/**
 * Anzeigen von allen Bauteilen, die in der DB enthalten sind
 * @param Vektor mit Bauteilen gef�llt
 */

public Widget showAllBauteile(Vector<Bauteil> bauteile) {

	/**
	 * neuer HTML Bereich
	 */
	HTML topic = new HTML(
			"<h2>Was wollen Sie mit dem Bauteil tun?</h2>");
	this.vPanel.add(topic);

	

	this.vPanel.add(this.hPanel);

	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
	 * belegen
	 */

	bauteileTable.setText(0, 0, "ID");
	bauteileTable.setText(0, 1, "Name");
	bauteileTable.setText(0, 2, "Beschreibung");
	bauteileTable.setText(0, 3, "Materialbezeichnung");
	bauteileTable.setText(0, 4, "Erstellt am");
	bauteileTable.setText(0, 5, "Zuletzt geaendert am");
	bauteileTable.setText(0, 6, "letzter Bearbeiter");
	bauteileTable.setText(0, 7, "Bearbeiten");
	bauteileTable.setText(0, 8, "Löschen");

	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
	 * Vektor belegt
	 */
	for (int j = 0; j < bauteile.size(); j++) {

		Button btnLoeschen = new Button("Löschen");
		btnLoeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Cell cell = bauteileTable.getCellForEvent(event);

				int rowIndex = cell.getRowIndex();
				String id1 = bauteileTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);

				sms.deleteBauteil(id, new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI()
								.load("Bauteil konnte nicht gelöscht werden",
										"red");

					}

					@Override
					public void onSuccess(Object result) {
						new AlertGUI().load(
								"Bauteil wurde erfolgreich gelöscht",
								"green");
						sms.getAll(new AsyncCallback<Vector<Bauteil>>() {

							@Override
							public void onFailure(Throwable caught) {

							}

							@Override
							public void onSuccess(
									Vector<Bauteil> result) {

								ContentContainer
										.getInstance()
										.setContent(new BauteilGUI().showAllBauteile(result));

							}
						});

					}
				});

			}
		});

		Button btnBearbeiten = new Button("Bearbeiten");
		btnBearbeiten.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Cell cell = bauteileTable.getCellForEvent(event);

				int rowIndex = cell.getRowIndex();
				String id1 = bauteileTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);

				sms.getBauteil(id,
						new AsyncCallback<Vector<Bauteil>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(
									Vector<Bauteil> result) {

								ContentContainer.getInstance().setContent(
										new BauteilGUI().showAnlegenForm(result.firstElement()));

							}
						});

			}
		});

		/**
		 * Formatiert Timestamp zu String
		 */
		
		 Date d1 = new Date(); d1 =
		 bauteile.elementAt(j).getErstellungsDatum(); String s1 =
		 DateTimeFormat.getMediumDateTimeFormat().format(d1);
		 

		/**
		 * Formatiert Timestamp zu String
		 */
		
		 Date d2 = new Date(); d2 =
		 bauteile.elementAt(j).getAenderungsDatum(); String s2 =
		 DateTimeFormat.getMediumDateTimeFormat().format(d2);
		 
		

		/**
		 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
		 */
		bauteileTable.setText(j + 1, 0,Integer.toString(bauteile.elementAt(j).getId()));
		bauteileTable.setText(j + 1, 1, bauteile.elementAt(j).getName());
		bauteileTable.setText(j + 1, 2, bauteile.elementAt(j).getBeschreibung());
		bauteileTable.setText(j + 1, 3, bauteile.elementAt(j).getMaterialBezeichnung());
		bauteileTable.setText(j+1, 4, s1);
		bauteileTable.setText(j+1, 5, s2);
		bauteileTable.setText(j + 1, 6, user);
		bauteileTable.setWidget(j + 1, 7, btnBearbeiten);
		bauteileTable.setWidget(j + 1, 8, btnLoeschen);

		/**
		 * Verknuepfung zu style.css
		 */
		bauteileTable.setCellPadding(6);
		bauteileTable.getRowFormatter().addStyleName(0,
				"watchListHeader");
		bauteileTable.getCellFormatter().addStyleName(0, 2,
				"watchListNumericColumn");
		bauteileTable.getCellFormatter().addStyleName(0, 3,
				"watchListNumericColumn");
	}

	/**
	 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
	 * angezeigt wird
	 */
	vPanel.add(bauteileTable);

	return vPanel;

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


	
	
	
	/**
	 * ClickHandler zum Men�button Speichern
	 * Wenn ein Bauteil angelegt oder ver�ndert wurde, 
	 * wird das Bauteil in der DB aktualisiert �ber die Methode
	 *  updateBauteil() (Client Impl)
	 * Danach wird das Objekt, welches ver�ndert wurde durch die Methode
	 * getBauteil(Bauteil bt) (BauteilGUI) auf der GUI sichtbar gemacht
	 *
	 */

	
	
	


				
  }



