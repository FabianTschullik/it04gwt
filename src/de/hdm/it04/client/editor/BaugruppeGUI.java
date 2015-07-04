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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;


public class BaugruppeGUI  {
	
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	AlertGUI alertGUI = new AlertGUI();
	
	private TextBox txtSuchen = new TextBox();
	private TextBox txtName = new TextBox();
	private TextArea txtBeschreibung = new TextArea();
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	public Baugruppe bg;
	
	
	public Widget suchen(){
		VerticalPanel vPanel = new VerticalPanel();
		
		txtSuchen.setText("id oder Name");
		vPanel.add(txtSuchen);
		
		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
	String bgSuche = txtSuchen.getText();
				
				if (bgSuche.matches("[0-9]+")){
					int id = Integer.parseInt(bgSuche);
					sms.getBaugruppe(id, new AsyncCallback<Vector<Baugruppe>>() {

						public void onFailure(Throwable arg0) {
						
							
						}

						
						public void onSuccess(Vector<Baugruppe> result) {
							ContentContainer.getInstance().setContent(new BaugruppeGUI().showAllBaugruppen(result));
							
						}
					});
					
				}
			
				sms.getBaugruppe(bgSuche, new AsyncCallback<Vector<Baugruppe>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Baugruppe> result) {
						ContentContainer.getInstance().setContent(new BaugruppeGUI().showAllBaugruppen(result));;
						
						
					}
				});
				
				
				
			}
		} );
			
		
		
		return vPanel;
	}


//----------------------------------------------------------------------------
//----------------------- Form zum Anlegen einer Baugruppe --------------------------
//----------------------------------------------------------------------------
public Widget showAnlegenForm(Baugruppe baugruppe){
	
	
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
  cellFormatter.setHorizontalAlignment(
      0, 0, HasHorizontalAlignment.ALIGN_CENTER);

 
	
	Button btnSpeichern = new Button("Speichern");
	btnSpeichern.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
		
			String user = It04gwtEditor.user;
			
			bg.setName(txtName.getText());
			bg.setBeschreibung(txtBeschreibung.getText());
			bg.setLetzterBearbeiter(user);
			sms.updateBaugruppe(bg, new AsyncCallback<Vector<Baugruppe>>() {

				@Override
				public void onFailure(Throwable caught) {
					alertGUI.load(
							"Enderzeugnis konnte nicht gespeichert werden",
							"red");
					
				}

				@Override
				public void onSuccess(Vector<Baugruppe> result) {
					alertGUI.load(
							"Enderzeugnis wurde erfolgreich gespeichert",
							"green");
					
					ContentContainer.getInstance().setContent(
							new BaugruppeGUI().showAllBaugruppen(result));

					
				}
			});
			
		}
	});
	

	
	
	
	
  // Add some standard form options
  layout.setHTML(1, 0, "ID");
  layout.setText(1, 1, Integer.toString(bg.getId()));
  layout.setHTML(2, 0, "Name");
  layout.setWidget(2, 1, txtName);
  layout.setHTML(3, 0, "Beschreibung");
  layout.setWidget(3, 1, txtBeschreibung);
  layout.setHTML(4, 0, "Baugruppe zuordnen");
  layout.setWidget(5, 0, btnSpeichern);
 // layout.setWidget(5, 1, btnAbbrechen);
  

  // Wrap the content in a DecoratorPanel
  DecoratorPanel decPanel = new DecoratorPanel();
  decPanel.setWidget(layout);
  
  this.vPanel.add(decPanel);
  
  return vPanel;
}
//----------------------------------------------------------------------------
//----------------------- Ende Form zum Anlegen eines EZ --------------------------
//----------------------------------------------------------------------------


public void showBaugruppeForm(Baugruppe bg){
	
	FlexTable flex = new FlexTable();
	
	this.bg = bg;
	//this.vPanel.clear();
	
	/**
	 *�berschriften der Tabelle 
	 */
	flex.setText(0, 0, "ID");
	flex.setText(0, 1, "Name");
	flex.setText(0, 2, "Beschreibung");
	flex.setText(0, 3, "erstellt am");
	flex.setText(0, 4, "geändert am");
	flex.setText(0, 5, "Letzter Bearbeiter");
	flex.setText(0, 6, "Bearbeiten");
	flex.setText(0, 7, "Löschen");

	
	flex.setText(1, 0, Integer.toString(bg.getId()));
	flex.setText(1, 1, bg.getName());
	flex.setText(1, 2, bg.getBeschreibung());
	flex.setText(1, 3, "erstellt am");
	flex.setText(1, 4, "geändert am");
	flex.setText(1, 5, bg.getLetzterBearbeiter());
	//flex.setWidget(1, 6, btnBearbeiten);
	//flex.setWidget(1, 7, btnLoeschen);
	
	/**
	 * Verknüpfung zu style.css
	 */

	flex.setCellPadding(6);
	flex.getRowFormatter().addStyleName(0,  "watchListHeader");
	flex.getCellFormatter().addStyleName(0,2, "watchListNumericColumn");
	flex.getCellFormatter().addStyleName(0,3, "watchListNumericColumn");	
	this.vPanel.add(flex);
}

public Widget showAllBaugruppen(Vector<Baugruppe> baugruppen){
	
	/**
	 * Objekt der Klasse FlexTable erstellen und mit Spaltenueberschriften belegen
	 */
	FlexTable baugruppeTable = new FlexTable();
	baugruppeTable.setText(0,0,"ID");
	baugruppeTable.setText(0,1,"Name");
	baugruppeTable.setText(0,2,"Beschreibung");
	baugruppeTable.setText(0,3,"Erstellt am");
	baugruppeTable.setText(0,4,"Zuletzt geaendert am");
	baugruppeTable.setText(0,5,"letzter Bearbeiter");
	//bauteileTable.setText(0,6,"Edit");
	//bauteileTable.setText(0,7,"Delete");
	
	/**
	 * Fuer jedes Bauteil werden die Tabellenspalten mit den Werten aus dem Vektor belegt
	 */
	for(int j=0; j < baugruppen.size(); j++ ){
		
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
		d1 = baugruppen.elementAt(j).getErstellungsDatum();
		String s1 = DateTimeFormat.getMediumDateTimeFormat().format(d1);
		
		
		/**
		 * Formatiert Timestamp zu String
		 */
		Date d2 = new Date();
		d2 = baugruppen.elementAt(j).getAenderungsDatum();
		String s2 = DateTimeFormat.getMediumDateTimeFormat().format(d2);
		
		
		String user = It04gwtEditor.user;
	
		/**
		 * Konvertieren der Bauteil-Daten und befuellen der Tabelle
		 */
		baugruppeTable.setText(j+1, 0, Integer.toString(baugruppen.elementAt(j).getId()));
		baugruppeTable.setText(j+1, 1, baugruppen.elementAt(j).getName());
		baugruppeTable.setText(j+1, 2, baugruppen.elementAt(j).getBeschreibung());
		baugruppeTable.setText(j+1, 3, s1);
		baugruppeTable.setText(j+1, 4, s2);
		baugruppeTable.setText(j+1, 5, user);

		
		/**
		 * Einfuegen der Buttons in die Tabelle
		 */
		//bauteileTable.setWidget(j+1, 5, btnDelete);
		//bauteileTable.setWidget(j+1, 6, editBtn);
		
		
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




public class BtnAnlegenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		vPanel.clear();
		//serviceImpl.createBaugruppe();
	}
}

public class BtnSuchenClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		//serviceImpl.getBaugruppe(Integer.parseInt(txtSuchen.getText()));
	}
}

public class BtnShowAllClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		
		vPanel.clear();
		//serviceImpl.getAllBaugruppen();	
	}
}

}
