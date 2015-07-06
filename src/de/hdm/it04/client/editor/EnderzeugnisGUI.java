package de.hdm.it04.client.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;

public class EnderzeugnisGUI {

	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	AlertGUI alertGUI = new AlertGUI();
	
	String user = It04gwtEditor.user;

	private TextBox txtSuchen = new TextBox();
	private TextBox txtName = new TextBox();
	private TextBox txtPreis = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	static Enderzeugnis ez;
	FlexTable enderzeugnisseTable = new FlexTable();
	FlexTable baugruppeTable = new FlexTable();

	public Widget showZuordnungsForm(Vector<Baugruppe> baugruppe) {

		VerticalPanel vPanel = new VerticalPanel();
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML(
				"<h2>Welche Baugruppe möchten Sie dem Enderzeugnis zuordnen?</h2>");
		vPanel.add(topic);

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
		

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < baugruppe.size(); j++) {

			RadioButton rb = new RadioButton("Zuordnung");

			rb.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {

					boolean checked = ((CheckBox) event.getSource()).getValue();

					if (checked == true) {

						Cell cell = baugruppeTable.getCellForEvent(event);

						int rowIndex = cell.getRowIndex();
						String id1 = baugruppeTable.getText(rowIndex, 0);
						int id = Integer.parseInt(id1);
						ez.setBaugruppe(id);
					}
				}
			});

			/**
			 * Formatiert Timestamp zu String
			 */
			
			 Date d1 = new Date(); d1 =
			 baugruppe.elementAt(j).getErstellungsDatum(); 
			 String s1 =DateTimeFormat.getMediumDateTimeFormat().format(d1);
			 

			/**
			 * Formatiert Timestamp zu String
			 */
			
			 Date d2 = new Date(); d2 =
			 baugruppe.elementAt(j).getAenderungsDatum(); String s2 =
			 DateTimeFormat.getMediumDateTimeFormat().format(d2);
			 

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			baugruppeTable.setText(j + 1, 0,
			Integer.toString(baugruppe.elementAt(j).getId()));
			baugruppeTable.setText(j + 1, 1, baugruppe.elementAt(j).getName());
			baugruppeTable.setText(j + 1, 2, baugruppe.elementAt(j).getBeschreibung());
			baugruppeTable.setText(j+1, 3, s1);
			baugruppeTable.setText(j+1, 4, s2);
			baugruppeTable.setWidget(j + 1, 6, rb);

			if (Integer.parseInt(baugruppeTable.getText(j + 1, 0)) == ez.getBaugruppe()) {
				rb.setValue(true);
			}

			/**
			 * Verknuepfung zu style.css
			 */
			baugruppeTable.setCellPadding(6);
			baugruppeTable.getRowFormatter().addStyleName(0, "watchListHeader");
			baugruppeTable.getCellFormatter().addStyleName(0, 2,"watchListNumericColumn");
			baugruppeTable.getCellFormatter().addStyleName(0, 3,"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		vPanel.add(baugruppeTable);

		Button btnSuchen = new Button("Baugruppe zuordnen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				sms.updateEnderzeugnis(ez,
						new AsyncCallback<Vector<Enderzeugnis>>() {

							@Override
							public void onFailure(Throwable caught) {
								new AlertGUI()
										.load("Enderzeugnis konnte nicht gespeichert werden",
												"red");

							}

							@Override
							public void onSuccess(Vector<Enderzeugnis> result) {

								ContentContainer.getInstance().setContent(
										new EnderzeugnisGUI()
												.showAllEnderzeugnisse(result));
								new AlertGUI()
										.load("Enderzeugnis wurde erfolgreich gespeichert",
												"green");

							}
						});

			}
		});

		vPanel.add(btnSuchen);

		return vPanel;
	}

	public Widget suchen() {
		VerticalPanel vPanel = new VerticalPanel();

		txtSuchen.setText("id oder Name");
		vPanel.add(txtSuchen);

		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String ezSuche = txtSuchen.getText();
				String name = ezSuche;

				if (ezSuche.matches("[0-9]+")) {
					int id = Integer.parseInt(ezSuche);
					sms.getEnderzeugnis(id,
							new AsyncCallback<Vector<Enderzeugnis>>() {

								public void onFailure(Throwable arg0) {

								}

								public void onSuccess(
										Vector<Enderzeugnis> result) {
									ContentContainer
											.getInstance()
											.setContent(
													new EnderzeugnisGUI()
															.showAllEnderzeugnisse(result));

								}
							});

				}

				sms.getEnderzeugnis(name,
						new AsyncCallback<Vector<Enderzeugnis>>() {

							public void onFailure(Throwable arg0) {

							}

							public void onSuccess(Vector<Enderzeugnis> result) {
								ContentContainer.getInstance().setContent(
										new EnderzeugnisGUI()
												.showAllEnderzeugnisse(result));
								;

							}
						});

			}
		});

		return vPanel;
		
		
		
	}

	// ----------------------------------------------------------------------------
	// ----------------------- Form zum Anlegen eines EZ
	// --------------------------
	// ----------------------------------------------------------------------------
	public Widget showAnlegenForm(Enderzeugnis enderzeugnis) {

		
		

		this.ez = enderzeugnis;
		txtName.setText(ez.getName());
		txtBeschreibung.setText(ez.getBeschreibung());
		txtPreis.setText(Double.toString(ez.getPreis()));

		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		layout.setHTML(0, 0, "<h3>Enderzeugnis anlegen<h3>");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		Button btnSpeichern = new Button("Weiter");
		btnSpeichern.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ez.setName(txtName.getText());
				ez.setPreis(Double.parseDouble(txtPreis.getText()));
				ez.setBeschreibung(txtBeschreibung.getText());
				ez.setLetzterBearbeiter(user);

				sms.getAllBaugruppen(new AsyncCallback<Vector<Baugruppe>>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(Vector<Baugruppe> result) {

						ContentContainer.getInstance().setContent(
								new EnderzeugnisGUI()
										.showZuordnungsForm(result));

					}
				});

			}
		});

		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Welcome().load());
				new AlertGUI().load("Vorgang wurde erfolgreich abgebrochen",
						"green");

			}
		});

		// Add some standard form options
		layout.setHTML(1, 0, "ID");
		layout.setText(1, 1, Integer.toString(ez.getId()));
		layout.setHTML(2, 0, "Name");
		layout.setWidget(2, 1, txtName);
		layout.setHTML(3, 0, "Beschreibung");
		layout.setWidget(3, 1, txtBeschreibung);
		layout.setHTML(4, 0, "Preis");
		layout.setWidget(4, 1, txtPreis);
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



	public Widget showAllEnderzeugnisse(Vector<Enderzeugnis> enderzeugnisse) {

		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML(
				"<h2>Was wollen Sie mit dem Enderzeugnis tun?</h2>");
		this.vPanel.add(topic);

		

		this.vPanel.add(this.hPanel);

		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften
		 * belegen
		 */

		enderzeugnisseTable.setText(0, 0, "ID");
		enderzeugnisseTable.setText(0, 1, "Name");
		enderzeugnisseTable.setText(0, 2, "Beschreibung");
		enderzeugnisseTable.setText(0, 3, "Preis");
		enderzeugnisseTable.setText(0, 4, "Erstellt am");
		enderzeugnisseTable.setText(0, 5, "Zuletzt geaendert am");
		enderzeugnisseTable.setText(0, 6, "letzter Bearbeiter");
		enderzeugnisseTable.setText(0, 7, "Bearbeiten");
		enderzeugnisseTable.setText(0, 8, "Löschen");
		enderzeugnisseTable.setText(0, 9, "Zuordnungdetails");

		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem
		 * Vektor belegt
		 */
		for (int j = 0; j < enderzeugnisse.size(); j++) {

			Button btnLoeschen = new Button("Löschen");
			btnLoeschen.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					Cell cell = enderzeugnisseTable.getCellForEvent(event);

					int rowIndex = cell.getRowIndex();
					String id1 = enderzeugnisseTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);

					sms.deleteEnderzeugnis(id, new AsyncCallback() {

						@Override
						public void onFailure(Throwable caught) {
							new AlertGUI()
									.load("Enderzeugnis konnte nicht gelöscht werden",
											"red");

						}

						@Override
						public void onSuccess(Object result) {
							new AlertGUI().load(
									"Enderzeugnis wurde erfolgreich gelöscht",
									"green");
							sms.getAllEnderzeugnisse(new AsyncCallback<Vector<Enderzeugnis>>() {

								@Override
								public void onFailure(Throwable caught) {

								}

								@Override
								public void onSuccess(
										Vector<Enderzeugnis> result) {

									ContentContainer
											.getInstance()
											.setContent(
													new EnderzeugnisGUI()
															.showAllEnderzeugnisse(result));

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
					Cell cell = enderzeugnisseTable.getCellForEvent(event);

					int rowIndex = cell.getRowIndex();
					String id1 = enderzeugnisseTable.getText(rowIndex, 0);
					int id = Integer.parseInt(id1);

					sms.getEnderzeugnis(id,
							new AsyncCallback<Vector<Enderzeugnis>>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(
										Vector<Enderzeugnis> result) {

									ContentContainer.getInstance().setContent(
											new EnderzeugnisGUI()
													.showAnlegenForm(result
															.firstElement()));

								}
							});

				}
			});
			
			
			Button btnZuordnungDetails = new Button("Zuordnungdetails");
			btnZuordnungDetails.addClickHandler(new ClickHandler(){
				
					public void onClick(ClickEvent event) {
						Cell cell = enderzeugnisseTable.getCellForEvent(event);

						int rowIndex = cell.getRowIndex();
						String id1 = enderzeugnisseTable.getText(rowIndex, 0);
						int id = Integer.parseInt(id1);
	
						sms.getEnderzeugnis(id,new AsyncCallback<Vector<Enderzeugnis>>() {

							@Override
							public void onFailure(Throwable caught) {
								
								
							}

							@Override
							public void onSuccess(Vector<Enderzeugnis> result) {
								ez = result.firstElement();
								int id = ez.getBaugruppe();
								sms.getBaugruppeForZuordnungDetails(id, 
										new AsyncCallback<Vector<Baugruppe>>(){

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(Vector<Baugruppe> result) {
										ContentContainer.getInstance().setContent(
												new EnderzeugnisGUI()
														.tree(result));
										
										
									}
									
								});
								
								
							}
							
						});	
				}
			}
			
				
			);

			/**
			 * Formatiert Timestamp zu String
			 */
			
			 Date d1 = new Date(); d1 =
			 enderzeugnisse.elementAt(j).getErstellungsDatum(); String s1 =
			 DateTimeFormat.getMediumDateTimeFormat().format(d1);
			 

			/**
			 * Formatiert Timestamp zu String
			 */
			
			 Date d2 = new Date(); d2 =
			 enderzeugnisse.elementAt(j).getAenderungsDatum(); String s2 =
			 DateTimeFormat.getMediumDateTimeFormat().format(d2);
			 
			 
			 

			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			enderzeugnisseTable.setText(j + 1, 0,
					Integer.toString(enderzeugnisse.elementAt(j).getId()));
			enderzeugnisseTable.setText(j + 1, 1, enderzeugnisse.elementAt(j).getName());
			enderzeugnisseTable.setText(j + 1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
			enderzeugnisseTable.setText(j + 1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
			enderzeugnisseTable.setText(j+1, 4, s1);
			enderzeugnisseTable.setText(j+1, 5, s2);
			enderzeugnisseTable.setText(j + 1, 6, user);
			enderzeugnisseTable.setWidget(j + 1, 7, btnBearbeiten);
			enderzeugnisseTable.setWidget(j + 1, 8, btnLoeschen);
			enderzeugnisseTable.setWidget(j+1, 9, btnZuordnungDetails);

			/**
			 * Verknuepfung zu style.css
			 */
			enderzeugnisseTable.setCellPadding(6);
			enderzeugnisseTable.getRowFormatter().addStyleName(0,
					"watchListHeader");
			enderzeugnisseTable.getCellFormatter().addStyleName(0, 2,
					"watchListNumericColumn");
			enderzeugnisseTable.getCellFormatter().addStyleName(0, 3,
					"watchListNumericColumn");
		}

		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch
		 * angezeigt wird
		 */
		vPanel.add(enderzeugnisseTable);

		return vPanel;

	}


public Widget tree(Vector<Baugruppe> baugruppe){
		Baugruppe[] bg = new Baugruppe[baugruppe.size()];
		baugruppe.copyInto(bg);
	
	
		TreeItem root = new TreeItem();
	
		root.setUserObject(ez);
		root.setText(ez.getName());
    
		TreeItem sub = new TreeItem();
		sub.setUserObject(bg[0]);
		sub.setText(bg[0].getName());
		root.addItem(sub);
    
    
    
    /*for(int i = 0; i< baugruppe.length; i++){
    	//root.addTextItem(baugruppe[i].getName());
    	TreeItem sub = new TreeItem();
    	sub.setText(baugruppe[i].getName());
    	for(int z = 0; z<baugruppe.length; z ++)
    	sub.addTextItem(baugruppe[z].getName());
    	root.addItem(sub);
 }
    
   
  		for(int i = 0; i< baugruppe.length; i++){
    	sub.addTextItem(baugruppe[i].getName());
	   	root.addItem(sub);
 }*/
    
    	Tree t = new Tree();
    	t.addSelectionHandler(new SelectionHandler<TreeItem>(){
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				 TreeItem selectedItem = event.getSelectedItem();
					
					
					Object result = selectedItem.getUserObject();
					if (result instanceof Baugruppe){
						int id = ((Baugruppe) result).getId();
						sms.getBaugruppe(id, new AsyncCallback<Vector<Baugruppe>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Baugruppe> result) {
								ContentContainer.getInstance().setContent(new BaugruppeGUI().showAnlegenForm(result.firstElement()));
								
							}
						});
					}
					else if (result instanceof Enderzeugnis){
						int id = ((Enderzeugnis) result).getId();
						sms.getEnderzeugnis(id,
								new AsyncCallback<Vector<Enderzeugnis>>() {

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub

									}

									@Override
									public void onSuccess(
											Vector<Enderzeugnis> result) {

										ContentContainer.getInstance().setContent(
												new EnderzeugnisGUI()
														.showAnlegenForm(result
																.firstElement()));

									}
								});

					}
				
				
				}
    		
    		});
    		t.addItem(root);

    
    
    		this.vPanel.add(t);
	
	
    	return this.vPanel;
	
	}

}
