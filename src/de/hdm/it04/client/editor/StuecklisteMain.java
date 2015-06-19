package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
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
		 * vPanel um HPanel ergänzen
		 */
		this.vPanelDashboard.add(hPanelDashboard);	
		
		/**
		 * Anlegen der Buttons fürs Anlegen, Anzeigen, Editieren und Löschen
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
	
	public void openAnlegenStuecklisteMain() {
		vPanelDetails.clear();
		AnlegenStuecklisteMain AnlegenStuecklisteMain = new AnlegenStuecklisteMain();
		vPanelDetails.add(AnlegenStuecklisteMain);
	}
	
	public void openBearbeitenStuecklisteMain() {
		vPanelDetails.clear();
		BearbeitenStuecklisteMain BearbeitenStuecklisteMain = new BearbeitenStuecklisteMain();
		vPanelDetails.add(BearbeitenStuecklisteMain);
	}
	
	public void openLoeschenStuecklisteMain() {
		vPanelDetails.clear();
		LoeschenStuecklisteMain LoeschenStuecklisteMain = new LoeschenStuecklisteMain();
		vPanelDetails.add(LoeschenStuecklisteMain);
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
}
