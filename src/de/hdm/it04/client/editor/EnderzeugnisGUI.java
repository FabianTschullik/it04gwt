package de.hdm.it04.client.editor;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
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
	FlexTable enderzeugnisseTable = new FlexTable();
	FlexTable baugruppeTable  = new FlexTable();
	
	It04gwtEditor user = new It04gwtEditor();
	
	

	public Widget showZuordnungsForm (Vector <Baugruppe> baugruppe){
		
		
		/**
		 * neuer HTML Bereich
		 */
		HTML topic = new HTML("<h2>Welche Baugruppe möchten Sie dem Enderzeugnis zuordnen?</h2>");
		this.vPanel.add(topic);
		
		Button btnZuordnung = new Button("Jetzt zuordnen");
		btnZuordnung.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
				sms.updateEnderzeugnis(ez, new AsyncCallback<Vector<Enderzeugnis>>() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI().load("Enderzeugnis wurde erfolgreich angelegt", "green");
						
					}

					@Override
					public void onSuccess(Vector<Enderzeugnis> result) {
						new AlertGUI().load("Enderzeugnis wurde erfolgreich angelegt", "green");
						
					}
				});
			}
		});
		
		this.vPanel.add(btnZuordnung);
		
		/**
		 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
		 */
		baugruppeTable.setText(0,0,"ID");
		baugruppeTable.setText(0,1,"Name");
		baugruppeTable.setText(0,2,"Beschreibung");
		baugruppeTable.setText(0,3,"Erstellt am");
		baugruppeTable.setText(0,4,"Zuletzt geaendert am");
		baugruppeTable.setText(0,5,"letzter Bearbeiter");
		baugruppeTable.setText(0,6,"Zuordnen");
		//bauteileTable.setText(0,7,"Delete");
		
		/**
		 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
		 */
		for(int j=0; j < baugruppe.size(); j++ ){
			
			RadioButton rb = new RadioButton("Zuordnung");
		
			rb.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					
					boolean checked = ((CheckBox) event.getSource()).getValue();
					
					if (checked == true){
						
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
			/*Date d1 = new Date();
			d1 = bauteile.elementAt(j).getErstellungsDatum();
			String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);*/
			
			
			/**
			 * Formatiert Timestamp zu String
			 */
			/*Date d2 = new Date();
			d2 = bauteile.elementAt(j).getAenderungsDatum();
			String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);*/
			
		
			/**
			 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
			 */
			baugruppeTable.setText(j+1, 0, Integer.toString(baugruppe.elementAt(j).getId()));
			baugruppeTable.setText(j+1, 1, baugruppe.elementAt(j).getName());
			baugruppeTable.setText(j+1, 2, baugruppe.elementAt(j).getBeschreibung());
			//bauteileTable.setText(j+1, 3, s1);
			//bauteileTable.setText(j+1, 4, s2);

			baugruppeTable.setWidget(j+1, 6, rb);
			
			if(Integer.parseInt(baugruppeTable.getText(j+1, 0)) == ez.getBaugruppe()){
				rb.setValue(true);
			}
			
			
			
			/**
			 * Verknuepfung zu style.css
			 */
			baugruppeTable.setCellPadding(6);
			baugruppeTable.getRowFormatter().addStyleName(0,  "watchListHeader");
			baugruppeTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
			baugruppeTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
		}	
		
		/**
		 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
		 */
		this.vPanel.add(baugruppeTable);
		
		return vPanel;
	}


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

			
				ez.setName(txtName.getText());
				ez.setPreis(Double.parseDouble(txtPreis.getText()));
				ez.setBeschreibung(txtBeschreibung.getText());
				
				//sms.getAllBaugruppenForZuordnung();
				
			}
		});
		
		Button btnAbbrechen = new Button("Abbrechen");
		btnAbbrechen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				ContentContainer.getInstance().setContent(new Welcome().load());
				new AlertGUI().load("Vorgang wurde erfolgreich abgebrochen", "green");
				
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
	 * neuer HTML Bereich
	 */
	HTML topic = new HTML("<h2>Was wollen Sie mit dem Enderzeugnis tun?</h2>");
	this.vPanel.add(topic);
	
	//Button btnAnlegen = new Button("Anlegen");
	//btnAnlegen.addClickHandler(new BtnAnlegenClickHandler());
	//this.hPanel.add(btnAnlegen);
	
	this.vPanel.add(this.hPanel);
	
	
	
	
	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
	 */
	
	enderzeugnisseTable.setText(0,0,"ID");
	enderzeugnisseTable.setText(0,1,"Name");
	enderzeugnisseTable.setText(0,2,"Beschreibung");
	enderzeugnisseTable.setText(0,3,"Preis");	
	enderzeugnisseTable.setText(0,4,"Erstellt am");
	enderzeugnisseTable.setText(0,5,"Zuletzt geaendert am");
	enderzeugnisseTable.setText(0,6,"letzter Bearbeiter");
	enderzeugnisseTable.setText(0,7,"Bearbeiten");
	enderzeugnisseTable.setText(0,8,"Löschen");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < enderzeugnisse.size(); j++ ){
		
		Button btnLoeschen = new Button ("Löschen");
		btnLoeschen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
			
				Cell cell = enderzeugnisseTable.getCellForEvent(event);
				
				int rowIndex = cell.getRowIndex();
				String id1 = enderzeugnisseTable.getText(rowIndex, 0);
				int id = Integer.parseInt(id1);
				
				sms.deleteEnderzeugnis(id, new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						new AlertGUI().load("Enderzeugnis konnte nicht gelöscht werden", "red");
						
					}  

					@Override
					public void onSuccess(Object result) {
						new AlertGUI().load("Enderzeugnis wurde erfolgreich gelöscht", "green");
						sms.getAllEnderzeugnisse(new AsyncCallback<Vector<Enderzeugnis>>() {

							@Override
							public void onFailure(Throwable caught) {
								
								
							}

							@Override
							public void onSuccess(Vector<Enderzeugnis> result) {
							
								
								ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));
								
							}
						});
					
						
					}
				});
				
			}});
		
		Button btnBearbeiten = new Button ("Bearbeiten");
		btnBearbeiten.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}});
		
		
		/**
		 * Formatiert Timestamp zu String
		 */
		/*Date d1 = new Date();
		d1 = bauteile.elementAt(j).getErstellungsDatum();
		String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);*/
		
		
		/**
		 * Formatiert Timestamp zu String
		 */
		/*Date d2 = new Date();
		d2 = bauteile.elementAt(j).getAenderungsDatum();
		String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);*/
		
	
		/**
		 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
		 */
		enderzeugnisseTable.setText(j+1, 0, Integer.toString(enderzeugnisse.elementAt(j).getId()));
		enderzeugnisseTable.setText(j+1, 1, enderzeugnisse.elementAt(j).getName());
		enderzeugnisseTable.setText(j+1, 2, enderzeugnisse.elementAt(j).getBeschreibung());
		enderzeugnisseTable.setText(j+1, 3, Double.toString(enderzeugnisse.elementAt(j).getPreis()));
		//bauteileTable.setText(j+1, 4, s1);
		//bauteileTable.setText(j+1, 5, s2);
		enderzeugnisseTable.setWidget(j+1, 7, btnBearbeiten);
		enderzeugnisseTable.setWidget(j+1, 8, btnLoeschen);
		
		
		/**
		 * Verknuepfung zu style.css
		 */
		enderzeugnisseTable.setCellPadding(6);
		enderzeugnisseTable.getRowFormatter().addStyleName(0,  "watchListHeader");
		enderzeugnisseTable.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
		enderzeugnisseTable.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	}	
	
	/**
	 * Bauteil-Tabelle zum Panel hinzugefuegen damit das Ganze auch angezeigt wird 
	 */
	vPanel.add(enderzeugnisseTable);
	
	
	return vPanel;
	
}
}

		
	


