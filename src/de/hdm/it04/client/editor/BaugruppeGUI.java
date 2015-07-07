package de.hdm.it04.client.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.TeileListe;

/**
 * Die Klasse BaugruppeGUI ermoeglicht es dem User, Baugruppen Objekte zu
 * erstellen und zu verwalten. Hier wird das User Interface dafuer definiert.
 * 
 * @author Schwab, Tschullik, Voelker
 *
 */

public class BaugruppeGUI {

	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	AlertGUI alertGUI = new AlertGUI();

	private TextBox txtSuchen = new TextBox();
	private TextBox txtName = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	static Baugruppe bg;
	FlexTable baugruppeTable = new FlexTable();
	FlexTable bauteileTable = new FlexTable();
	public TreeItem root = new TreeItem();

	CheckBox cb = new CheckBox();
	TextBox txtMenge = new TextBox();

	String user = It04gwtEditor.user;

	/**
	 * Formular zum Suchen von Baugruppen per Name oder ID
	 * 
	 * @return vPanel
	 */

	public Widget suchen() {
		VerticalPanel vPanel = new VerticalPanel();

		txtSuchen.setText("id oder Name");
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
									new AlertGUI()
											.load("Baugruppe konnte nicht gespeichert werden",
													"red");
								}

								public void onSuccess(Vector<Baugruppe> result) {
									ContentContainer.getInstance().setContent(
											new BaugruppeGUI()
													.showAllBaugruppen(result));
								}
							});

				}

				sms.getBaugruppe(bgSuche,
						new AsyncCallback<Vector<Baugruppe>>() {

							public void onFailure(Throwable arg0) {
								new AlertGUI()
										.load("Baugruppe konnte nicht gespeichert werden",
												"red");

							}

							public void onSuccess(Vector<Baugruppe> result) {
								ContentContainer.getInstance().setContent(
										new BaugruppeGUI()
												.showAllBaugruppen(result));
								;

							}
						});
			}
		});
		return vPanel;
	}

	// ----------------------------------------------------------------------------
	// ----------------------- Form zum Anlegen einer Baugruppe
	// --------------------------
	// ----------------------------------------------------------------------------

	/**
	 * Formular, welches ermoeglicht ein Name und Beschreibung zu aendern bzw.
	 * hinzuzufuegen
	 * 
	 * @param baugruppe
	 * @return vPanel
	 */
	public Widget showAnlegenForm(Baugruppe baugruppe) {

		this.bg = baugruppe;
		txtName.setText(bg.getName());
		txtBeschreibung.setText(bg.getBeschreibung());

		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "<h3>Baugruppe anlegen<h3>");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		Button btnSpeichern = new Button("weiter");
		btnSpeichern.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				bg.setName(txtName.getText());
				bg.setBeschreibung(txtBeschreibung.getText());
				bg.setLetzterBearbeiter(user);

				sms.getAll(new AsyncCallback<Vector<Bauteil>>() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI().load(
								"Baugruppe konnte nicht gespeichert werden",
								"red");
					}

					@Override
					public void onSuccess(Vector<Bauteil> result) {
						ContentContainer.getInstance().setContent(
								new BaugruppeGUI()
										.showZuordnungsFormForBauteile(result));
					}
				});
			}
		});

		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				sms.deleteBaugruppe(bg.getId(), new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI()
								.load("Baugruppe konnte nicht gelöscht werden",
										"red");
					}

					@Override
					public void onSuccess(Object result) {
						new AlertGUI().load(
								"Vorgang wurde erfolgreich abgebrochen",
								"green");
					}
				});

				ContentContainer.getInstance().setContent(new Welcome().load());
			}
		});

		// Add some standard form options
		layout.setHTML(1, 0, "ID");
		layout.setText(1, 1, Integer.toString(bg.getId()));
		layout.setHTML(2, 0, "Name");
		layout.setWidget(2, 1, txtName);
		layout.setHTML(3, 0, "Beschreibung");
		layout.setWidget(3, 1, txtBeschreibung);
		layout.setWidget(5, 0, btnSpeichern);
		layout.setWidget(5, 1, btnAbbrechen);

		// Wrap the content in a DecoratorPanel
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);

		this.vPanel.add(decPanel);
		return vPanel;
	}

	// ----------------------------------------------------------------------------
	// ----------------------- Ende Form zum Anlegen eines EZ
	// --------------------------
	// ----------------------------------------------------------------------------

	/**
	 * Bauteile, aus denen die Baugruppe besteht, werden in der TeileListe
	 * hinzugefuegt
	 * 
	 * @param table
	 */
	public void fuelleTeileListe(FlexTable table) {

		// Durch den gesamten Vektor gehen
		for (int i = 0; i < bg.connectedBauteile.size(); i++) {

			// Durch alle Tabellenzeilen durchgehen
			for (int j = 1; j < table.getRowCount(); j++) {

				int aktuellesVektorElement = bg.connectedBauteile.elementAt(i)
						.getId();
				int aktuelleZeile = Integer.parseInt(table.getText(j, 0));
				TextBox tb = new TextBox();

				tb = (TextBox) bauteileTable.getWidget(j, 7);

				if (aktuelleZeile == aktuellesVektorElement) {

					int anzahl;

					if (tb.getText() == "") {
						anzahl = 1;
					} else {
						anzahl = Integer.parseInt(tb.getText());
					}

					bg.connectedBauteile.elementAt(i).setAnzahl(anzahl);
					j = table.getRowCount();
				}
			}
		}
	}

	/**
	 * Formular, welches alle Bauteile anzeigt, so dass diese der Baugruppe
	 * untergeordnet werden koennen
	 * 
	 * @param bauteile
	 * @return vPanel
	 */

	public Widget showZuordnungsFormForBauteile(Vector<Bauteil> bauteile) {

		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML(
				"<h2>Aus welchen Bauteilen besteht Ihre Baugruppe "
						+ bg.getName() + "?</h2>");
		this.vPanel.add(topic);

		Button btnZuordnung = new Button("weiter");
		btnZuordnung.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				fuelleTeileListe(bauteileTable);
				sms.getAllBaugruppen(new AsyncCallback<Vector<Baugruppe>>() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI().load(
								"Bauteile konnten nicht gespeichert werden",
								"red");
					}

					@Override
					public void onSuccess(Vector<Baugruppe> result) {

						// Ueberpruefe, ob Ueberbaugruppe in result und entferne
						// von Uebergabe
						// da diese nicht angezeigt werden soll in der Zuordnung
						for (int i = 0; i < result.size(); i++) {
							if (result.elementAt(i).getId() == bg.getId()) {
								result.remove(i);
							}
						}

						ContentContainer
								.getInstance()
								.setContent(
										new BaugruppeGUI()
												.showZuordnungsFormForBaugruppen(result));
					}
				});
			}
		});

		this.vPanel.add(btnZuordnung);

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */
		bauteileTable.setText(0, 0, "ID");
		bauteileTable.setText(0, 1, "Name");
		bauteileTable.setText(0, 2, "Beschreibung");
		bauteileTable.setText(0, 3, "Erstellt am");
		bauteileTable.setText(0, 4, "Zuletzt geaendert am");
		bauteileTable.setText(0, 5, "letzter Bearbeiter");
		bauteileTable.setText(0, 6, "Zuordnen");
		bauteileTable.setText(0, 7, "Menge");

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < bauteile.size(); j++) {

			final TextBox txtMenge = new TextBox();
			txtMenge.setText("1");

			CheckBox cb = new CheckBox();

			cb.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					TeileListe tl = new TeileListe();
					boolean checked = ((CheckBox) event.getSource()).getValue();

					if (checked == true) {

						// Bauteil der TeileListe hinzufuegen wenn gecheckt
						Cell cell = bauteileTable.getCellForEvent(event);

						// Aktuelle ID holen aus der Tabelle indem Reihe gesucht
						// wird
						int rowIndex = cell.getRowIndex();
						String id1 = bauteileTable.getText(rowIndex, 0);
						int id = Integer.parseInt(id1);

						tl.setId(id);

						// TeileListe in den Vektor speichern
						bg.connectedBauteile.add(tl);
					} else {
						// Bauteiil von TeileListe entfernen wenn nicht gecheckt
						bg.connectedBauteile.remove(tl.getId());
					}
				}
			});

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d1 = new Date();
			d1 = bauteile.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d2 = new Date();
			d2 = bauteile.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			bauteileTable.setText(j + 1, 0,
					Integer.toString(bauteile.elementAt(j).getId()));
			bauteileTable.setText(j + 1, 1, bauteile.elementAt(j).getName());
			bauteileTable.setText(j + 1, 2, bauteile.elementAt(j)
					.getBeschreibung());
			bauteileTable.setText(j + 1, 3, s1);
			bauteileTable.setText(j + 1, 4, s2);
			bauteileTable.setText(j + 1, 5, bauteile.elementAt(j)
					.getLetzterBearbeiter());

			bauteileTable.setWidget(j + 1, 6, cb);
			bauteileTable.setWidget(j + 1, 7, txtMenge);

			for (int i = 0; i < bg.connectedBauteile.size(); i++) {
				if (Integer.parseInt(bauteileTable.getText(j + 1, 0)) == bg.connectedBauteile
						.elementAt(i).getId()) {
					cb.setValue(true);
					txtMenge.setText(Integer.toString(bg.connectedBauteile
							.elementAt(i).getAnzahl()));
				}
			}

			/**
			 * Verknuepfung zu style.css
			 */
			bauteileTable.setCellPadding(6);
			bauteileTable.getRowFormatter().addStyleName(0, "watchListHeader");
			bauteileTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			bauteileTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		this.vPanel.add(bauteileTable);

		return vPanel;
	}

	/**
	 * Formular, welches alle Baugruppen anzeigt, so dass diese der Baugruppe
	 * untergeordnet werden koennen
	 * 
	 * @param baugruppen
	 * @return
	 */

	public Widget showZuordnungsFormForBaugruppen(Vector<Baugruppe> baugruppen) {

		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML(
				"<h2>Aus welchen Unterbaugruppen besteht Ihre Baugruppe "
						+ bg.getName() + "?</h2>");
		this.vPanel.add(topic);

		Button btnZuordnung = new Button("speichern");
		btnZuordnung.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// TeileListe in den Vektor speichern
				fuelleConnectedBaugruppen(baugruppeTable);

				sms.updateBaugruppe(bg, new AsyncCallback<Vector<Baugruppe>>() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI().load(
								"Baugruppe konnte nicht gespeichert werden",
								"red");
					}

					@Override
					public void onSuccess(Vector<Baugruppe> result) {
						new AlertGUI().load(
								"Baugruppe wurde erfolgreich gespeichert",
								"green");

						sms.getBaugruppe(bg.getId(),
								new AsyncCallback<Vector<Baugruppe>>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onSuccess(
											Vector<Baugruppe> result) {
										ContentContainer
												.getInstance()
												.setContent(
														new BaugruppeGUI()
																.showAllBaugruppen(result));
									}
								});
					}
				});
				vPanel.clear();
			}
		});

		this.vPanel.add(btnZuordnung);

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */
		baugruppeTable.setText(0, 0, "ID");
		baugruppeTable.setText(0, 1, "Name");
		baugruppeTable.setText(0, 2, "Beschreibung");
		baugruppeTable.setText(0, 3, "Erstellt am");
		baugruppeTable.setText(0, 4, "Zuletzt geaendert am");
		baugruppeTable.setText(0, 5, "letzter Bearbeiter");
		baugruppeTable.setText(0, 6, "Zuordnen");
		baugruppeTable.setText(0, 7, "Menge");

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < baugruppen.size(); j++) {

			final TextBox txtMenge = new TextBox();
			txtMenge.setText("1");

			CheckBox cb = new CheckBox();

			cb.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					TeileListe tl = new TeileListe();
					boolean checked = ((CheckBox) event.getSource()).getValue();

					if (checked == true) {

						// Bauteil der TeileListe hinzufügen wenn gecheckt
						Cell cell = baugruppeTable.getCellForEvent(event);

						// Aktuelle ID holen aus der Tabelle indem Reihe gesucht
						// wird
						int rowIndex = cell.getRowIndex();
						String id1 = baugruppeTable.getText(rowIndex, 0);
						int id = Integer.parseInt(id1);

						tl.setId(id);

						// TeileListe in den Vektor speichern
						bg.connectedBaugruppen.add(tl);
					} else {
						// Bauteiil von TeileListe entfernen wenn nicht gecheckt
						bg.connectedBaugruppen.remove(tl.getId());
					}
				}
			});

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d1 = new Date();
			d1 = baugruppen.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d2 = new Date();
			d2 = baugruppen.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			baugruppeTable.setText(j + 1, 0,
					Integer.toString(baugruppen.elementAt(j).getId()));
			baugruppeTable.setText(j + 1, 1, baugruppen.elementAt(j).getName());
			baugruppeTable.setText(j + 1, 2, baugruppen.elementAt(j)
					.getBeschreibung());
			baugruppeTable.setText(j + 1, 3, s1);
			baugruppeTable.setText(j + 1, 4, s2);
			baugruppeTable.setText(j + 1, 5, baugruppen.elementAt(j)
					.getLetzterBearbeiter());

			baugruppeTable.setWidget(j + 1, 6, cb);
			baugruppeTable.setWidget(j + 1, 7, txtMenge);

			// Falls eine Untergeordnete Baugruppe bereits zugeordnet wurde,
			// check sie an
			for (int i = 0; i < bg.connectedBaugruppen.size(); i++) {
				if (Integer.parseInt(baugruppeTable.getText(j + 1, 0)) == bg.connectedBaugruppen
						.elementAt(i).getId()) {
					cb.setValue(true);
					txtMenge.setText(Integer.toString(bg.connectedBaugruppen
							.elementAt(i).getAnzahl()));
				}
			}

			/**
			 * Verknuepfung zu style.css
			 */
			baugruppeTable.setCellPadding(6);
			baugruppeTable.getRowFormatter().addStyleName(0, "watchListHeader");
			baugruppeTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			baugruppeTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		this.vPanel.add(baugruppeTable);

		return vPanel;
	}

	/**
	 * Baugruppen, aus denen die Baugruppe besteht, werden der TeileListe
	 * hinzugefuegt
	 * 
	 * @param table
	 */
	public void fuelleConnectedBaugruppen(FlexTable table) {

		// Durch den gesamten Vektor gehen
		for (int i = 0; i < bg.connectedBaugruppen.size(); i++) {

			// Durch alle Tabellenzeilen durchgehen
			for (int j = 1; j < table.getRowCount(); j++) {

				int aktuellesVektorElement = bg.connectedBaugruppen
						.elementAt(i).getId();
				int aktuelleZeile = Integer.parseInt(table.getText(j, 0));

				TextBox tb = new TextBox();

				tb = (TextBox) baugruppeTable.getWidget(j, 7);

				if (aktuelleZeile == aktuellesVektorElement) {

					int anzahl;

					if (tb.getText() == "") {
						anzahl = 1;
					} else {
						anzahl = Integer.parseInt(tb.getText());
					}

					bg.connectedBaugruppen.elementAt(i).setAnzahl(anzahl);
					j = table.getRowCount();
				}
			}
		}
	}

	/**
	 * Formular, welches alle Baugruppen in tabellarischer Form wiedergibt
	 * 
	 * @param baugruppen
	 * @return
	 */
	public Widget showAllBaugruppen(Vector<Baugruppe> baugruppen) {

		this.hPanel.clear();
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML("<h2>Was wollen Sie mit der Baugruppe tun?</h2>");
		this.vPanel.add(topic);
		this.vPanel.add(this.hPanel);

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */
		baugruppeTable.setText(0, 0, "ID");
		baugruppeTable.setText(0, 1, "Name");
		baugruppeTable.setText(0, 2, "Beschreibung");
		baugruppeTable.setText(0, 3, "Erstellt am");
		baugruppeTable.setText(0, 4, "Zuletzt geaendert am");
		baugruppeTable.setText(0, 5, "letzter Bearbeiter");
		baugruppeTable.setText(0, 6, "Bearbeiten");
		baugruppeTable.setText(0, 7, "Löschen");
		baugruppeTable.setText(0, 8, "Zuordnungsdetails");

		if (baugruppen.size() == 0) {

			new AlertGUI().load("Keine Baugruppe vorhanden!", "red");
			vPanel.clear();

			return vPanel;
		}

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < baugruppen.size(); j++) {

			/**
			 * Button, um Bauteil innerhalb der Tabelle zu löschen
			 */
			Button btnLoeschen = new Button("Löschen");
			btnLoeschen.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Cell cell = baugruppeTable.getCellForEvent(event);

					int rowIndex = cell.getRowIndex();
					String id1 = baugruppeTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);

					vPanel.clear();
					baugruppeTable.removeAllRows();

					sms.deleteBaugruppe(id, new AsyncCallback() {

						@Override
						public void onFailure(Throwable caught) {
							new AlertGUI().load(
									"Baugruppe konnte nicht geloescht werden",
									"red");
						}

						@Override
						public void onSuccess(Object result) {
							new AlertGUI().load(
									"Baugruppe wurde erfolgreich geloescht",
									"green");

							sms.getAllBaugruppen(new AsyncCallback<Vector<Baugruppe>>() {

								@Override
								public void onFailure(Throwable caught) {
									new AlertGUI()
											.load("Baugruppen konnten nicht angezeigt werden",
													"red");
								}

								@Override
								public void onSuccess(Vector<Baugruppe> result) {
									ContentContainer.getInstance().setContent(
											new BaugruppeGUI()
													.showAllBaugruppen(result));
									new AlertGUI()
											.load("Baugruppe wurde erfolgreich geloescht",
													"green");
								}
							});
						}
					});
				}
			});

			/**
			 * Button, um Editieren des Bauteils innerhalb der Tabelle
			 * aufzurufen
			 */

			Button btnBearbeiten = new Button("Bearbeiten");
			btnBearbeiten.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Cell cell = baugruppeTable.getCellForEvent(event);
					int rowIndex = cell.getRowIndex();
					String id1 = baugruppeTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);

					sms.getBaugruppe(id,
							new AsyncCallback<Vector<Baugruppe>>() {

								@Override
								public void onFailure(Throwable caught) {
									new AlertGUI()
											.load("Baugruppe konnte nicht angezeigt werden",
													"red");
								}

								@Override
								public void onSuccess(Vector<Baugruppe> result) {
									ContentContainer.getInstance().setContent(
											new BaugruppeGUI()
													.showAnlegenForm(result
															.firstElement()));
								}
							});
				}
			});

			Button btnZuordnungDetails = new Button("Zuordnungdetails");
			btnZuordnungDetails.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Cell cell = baugruppeTable.getCellForEvent(event);
					int rowIndex = cell.getRowIndex();
					String id1 = baugruppeTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);

					sms.getBaugruppe(id,
							new AsyncCallback<Vector<Baugruppe>>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
								}

								@Override
								public void onSuccess(Vector<Baugruppe> result) {

									bg = result.firstElement();
									// tree (bg);
									ContentContainer.getInstance().setContent(
											new TreeGUI().tree());
								}
							});
				}
			});

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d1 = new Date();
			d1 = baugruppen.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);

			/**
			 * Formatiert Timestamp zu String
			 */
			Date d2 = new Date();
			d2 = baugruppen.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			baugruppeTable.setText(j + 1, 0,
					Integer.toString(baugruppen.elementAt(j).getId()));
			baugruppeTable.setText(j + 1, 1, baugruppen.elementAt(j).getName());
			baugruppeTable.setText(j + 1, 2, baugruppen.elementAt(j)
					.getBeschreibung());
			baugruppeTable.setText(j + 1, 3, s1);
			baugruppeTable.setText(j + 1, 4, s2);
			baugruppeTable.setText(j + 1, 5, baugruppen.elementAt(j)
					.getLetzterBearbeiter());
			/**
			 * Einfuegen der Buttons in die Tabelle
			 */
			baugruppeTable.setWidget(j + 1, 6, btnBearbeiten);
			baugruppeTable.setWidget(j + 1, 7, btnLoeschen);
			baugruppeTable.setWidget(j + 1, 8, btnZuordnungDetails);

			/**
			 * Verknuepfung zu style.css
			 */
			baugruppeTable.setCellPadding(6);
			baugruppeTable.getRowFormatter().addStyleName(0, "watchListHeader");
			baugruppeTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			baugruppeTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		this.vPanel.add(baugruppeTable);
		return vPanel;
	}

}
