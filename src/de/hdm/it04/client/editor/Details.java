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
public class Details extends Composite {

	private VerticalPanel vPanelDashboard = new VerticalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	private VerticalPanel vPanelDetails = new VerticalPanel();
	private MainViewEditor main;
	
	private HorizontalPanel hPanelDetailsButton = new HorizontalPanel();
	private HorizontalPanel hPanelDashboard = new HorizontalPanel();
	
	/**
	 * Konstruktor
	 */
	public Details() {

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
