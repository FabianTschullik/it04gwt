package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Schwab
 */
public class StuecklisteMain extends Composite {

	private VerticalPanel vPanelDashboard = new VerticalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	private MainViewEditor main;
	
	private HorizontalPanel hPanelDetailsButton = new HorizontalPanel();
	private HorizontalPanel hPanelDashboard = new HorizontalPanel();
	
	/**
	 * Konstruktor
	 */
	public StuecklisteMain() {

		initWidget(this.vPanelDashboard);
		this.main = main;
			
		/**
		 * Anlegen der Buttons fürs Anlegen, Bearbeiten, Löschen und Abbrechen
		 */
		Button AnlegenBtn1 = new Button("Neu");
		AnlegenBtn1.addClickHandler(new AnlegenBtn1ClickHandler());
		this.hPanelDetailsButton.add(AnlegenBtn1);
		
		Button BearbeitenBtn1 = new Button("Bearbeiten");
		BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.hPanelDetailsButton.add(BearbeitenBtn1);
	  
		Button LoeschenBtn1 = new Button("Loeschen");
		LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.hPanelDetailsButton.add(LoeschenBtn1);	
		
		Button AbbrechenBtn1 = new Button("Abbrechen");
		AbbrechenBtn1.addClickHandler(new AbbrechenBtn1ClickHandler());
		this.hPanelDetailsButton.add(AbbrechenBtn1);
		
		Label NameLabel = new Label("Name:");
		this.vPanelDetails.add(NameLabel);
		
		TextBox NameTextBox = new TextBox();
		this.vPanelDetails.add(NameTextBox);
		
		Label BeschreibungLabel = new Label("Beschreibung:");
		this.vPanelDetails.add(BeschreibungLabel);
		
		TextBox BeschreibungTextBox = new TextBox();
		this.vPanelDetails.add(BeschreibungTextBox);
		
		Label BearbeiterLabel = new Label("Letzter Bearbeiter:");
		this.vPanelDetails.add(BearbeiterLabel);
		
		Label DatumLabel = new Label("Letzter Zugriff am:");
		this.vPanelDetails.add(DatumLabel);
		
		Label PlatzhalterLabel = new Label ("Tree Platzhalter");
		this.vPanelTree.add(PlatzhalterLabel);
		
		/**
		 * vPanel um HPanel ergänzen
		 */
		this.vPanelDashboard.add(hPanelDashboard);
		
		/**
		 * Linke u. Rechte Spalte anordnen in einem HorizontalPanel
		 */
		this.hPanelDashboard.add(vPanelTree);
		vPanelTree.setBorderWidth(1);
		
		this.hPanelDashboard.add(vPanelDetails);
		vPanelDetails.setBorderWidth(1);
		
		/**
		 * hPanel mit Buttons der rechten Spalte zufügen
		 */
		this.vPanelDetails.add(hPanelDetailsButton);		
	}
	
	public class AnlegenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
						
		}	
	}
	
	public class BearbeitenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		}
	}
	
	public class LoeschenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
		}
	}
	
	public class AbbrechenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		
		}
	}
}
