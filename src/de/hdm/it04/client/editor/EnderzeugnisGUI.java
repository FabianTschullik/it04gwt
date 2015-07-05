package de.hdm.it04.client.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Enderzeugnis;

public class EnderzeugnisGUI {


	
	
	
	
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	AlertGUI alertGUI = new AlertGUI();
	
	private TextBox txtSuchen = new TextBox();
	private TextBox txtName = new TextBox();
	private TextBox txtPreis = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	public Enderzeugnis ez;
	
	It04gwtEditor user = new It04gwtEditor();
	
	

	


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
	String name = ezSuche;
				
				if (ezSuche.matches("[0-9]+")){
					int id = Integer.parseInt(ezSuche);
					sms.getEnderzeugnis(id, new AsyncCallback<Vector<Enderzeugnis>>() {

						public void onFailure(Throwable arg0) {
						
							
						}

						
						public void onSuccess(Vector<Enderzeugnis> result) {
							ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));
							
						}
					});
					
				}
			
				sms.getEnderzeugnis(name, new AsyncCallback<Vector<Enderzeugnis>>() {

					
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
	




//----------------------------------------------------------------------------
//----------------------- Form zum Anlegen eines EZ --------------------------
//----------------------------------------------------------------------------
public Widget showAnlegenForm(Enderzeugnis enderzeugnis){
	
	
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
    cellFormatter.setHorizontalAlignment(
        0, 0, HasHorizontalAlignment.ALIGN_CENTER);


	
	Button btnSpeichern = new Button("Speichern");
	btnSpeichern.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			
		String user = It04gwtEditor.user;

			
			ez.setName(txtName.getText());
			ez.setBeschreibung(txtBeschreibung.getText());
			ez.setPreis(Double.parseDouble(txtPreis.getText()));
			ez.setLetzterBearbeiter(user);
			sms.updateEnderzeugnis(ez, new AsyncCallback<Vector<Enderzeugnis>>() {

				@Override
				public void onFailure(Throwable caught) {
					alertGUI.load(
							"Enderzeugnis konnte nicht gespeichert werden",
							"red");
					
				}

				@Override
				public void onSuccess(Vector<Enderzeugnis> result) {
					alertGUI.load(
							"Enderzeugnis wurde erfolgreich gespeichert",
							"green");
					
					ContentContainer.getInstance().setContent(
							new EnderzeugnisGUI().showAllEnderzeugnisse(result));

					
				}
			});
			
		}
	});
	
	Button btnAbbrechen = new Button("Abbrechen");
	btnAbbrechen.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent event) {
			
			sms.deleteEnderzeugnis(ez.getId(), new AsyncCallback<Enderzeugnis>() {

				@Override
				public void onFailure(Throwable caught) {
					alertGUI.load(
							"Vorgang konnte nicht abgebrochen werden",
							"green");
					
				}

				@Override
				public void onSuccess(Enderzeugnis result) {
					alertGUI.load(
							"Vorgang wurde erfolgreich abgebrochen",
							"green");
					
					ContentContainer.getInstance().setContent(
							new Welcome().load());

					
					
				}
			});
			
			
			
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
    layout.setHTML(5, 0, "Baugruppe zuordnen");
    //layout.setWidget(5, 1, createMultiBox());
    //layout.setWidget(5, 2, btnSuchen);
    layout.setWidget(6, 0, btnSpeichern);
    layout.setWidget(6, 1, btnAbbrechen);
    

    // Wrap the content in a DecoratorPanel
    DecoratorPanel decPanel = new DecoratorPanel();
    decPanel.setWidget(layout);
    
    this.vPanel.add(decPanel);
    
    return vPanel;
}
//----------------------------------------------------------------------------
//----------------------- Ende Form zum Anlegen eines EZ --------------------------
//----------------------------------------------------------------------------



public ListBox createMultiBox(){
	
	
	// Add a drop box with the list types jip
    final ListBox dropBox = new ListBox(false);
    
    Vector<String> test = new Vector<String>();
    test.addElement("Bla");
    test.addElement("Blubb");
    
    
    for (int i = 0; i < test.size(); i++) {
      dropBox.addItem(test.elementAt(i));
    }
    
    dropBox.ensureDebugId("cwListBox-dropBox");
    
    
    return dropBox;
}

public void showEnderzeugnisForm(Enderzeugnis enderzeugnis){
	
	FlexTable flex = new FlexTable();
	
		this.ez = enderzeugnis;
		//this.vPanel.clear();
		
		/**
		 *�berschriften der Tabelle 
		 */
		flex.setText(0, 0, "ID");
		flex.setText(0, 1, "Name");
		flex.setText(0, 2, "Beschreibung");
		flex.setText(0, 3, "Preis");
		flex.setText(0, 4, "Preis");
		flex.setText(0, 5, "letzter Bearbeiter");
		flex.setText(0, 6, "geändert am");
		flex.setText(0, 7, "Bearbeiten");
		flex.setText(0, 8, "Löschen");
		
		Button btnBearbeiten = new Button("Bearbeiten");
		
		btnBearbeiten.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				//Cell cell = flex.getCellForEvent(event);
				//int rowIndex = cell.getRowIndex();
				//String id1 = flex.getText(rowIndex, 0);
				//int id = Integer.parseInt(id1);
				//vPanel.clear();
				//sms.getEnderzeugnisForUpdate(id);
				
			}
			
			
			
		});
		
		
		
		Button btnLoeschen = new Button("Loeschen");
		
		
		flex.setText(1, 0, Integer.toString(ez.getId()));
		flex.setText(1, 1, ez.getName());
		flex.setText(1, 2, ez.getBeschreibung());
		flex.setText(1, 3, Double.toString(ez.getPreis()));
		flex.setText(1, 5, ez.getLetzterBearbeiter());
		flex.setText(1, 6, "erstellt am");
		flex.setText(1, 7, "geändert am");
		flex.setWidget(1, 8, btnBearbeiten);
		flex.setWidget(1, 9, btnLoeschen);
		
		/**
		 * Verknüpfung zu style.css
		 */
	
		flex.setCellPadding(6);
		flex.getRowFormatter().addStyleName(0,  "watchListHeader");
		flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		this.vPanel.add(flex);
}



public Widget showAllEnderzeugnisse(Vector<Enderzeugnis> enderzeugnisse){
	
	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
	 */
	FlexTable bauteileTable = new FlexTable();
	bauteileTable.setText(0,0,"ID");
	bauteileTable.setText(0,1,"Name");
	bauteileTable.setText(0,2,"Beschreibung");
	bauteileTable.setText(0,3,"Preis");	
	bauteileTable.setText(0,4,"Erstellt am");
	bauteileTable.setText(0,5,"Zuletzt geaendert am");
	bauteileTable.setText(0,6,"letzter Bearbeiter");
	bauteileTable.setText(0,7,"Edit");
	bauteileTable.setText(0,8,"Delete");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < enderzeugnisse.size(); j++ ){
		
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
		
		
		
		Date d1 = new Date();
		d1 = enderzeugnisse.elementAt(j).getErstellungsDatum();
		String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
		
		
		/**
		 * Formatiert Timestamp zu String
		 */
		Date d2 = new Date();
		d2 = enderzeugnisse.elementAt(j).getAenderungsDatum();
		String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
		
	
		/**
		 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
		 */
		bauteileTable.setText(j+1, 0, Integer.toString(enderzeugnisse.elementAt(j).getId()));
		bauteileTable.setText(j+1, 1, enderzeugnisse.elementAt(j).getName());
		bauteileTable.setText(j+1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
		bauteileTable.setText(j+1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
		bauteileTable.setText(j+1, 4, s1);
		bauteileTable.setText(j+1, 5, s2);
		bauteileTable.setText(j+1, 6, enderzeugnisse.elementAt(j).getLetzterBearbeiter());
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
	
	return vPanel;
	
}
}

		
	


