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

	private VerticalPanel vPanelDetails = new VerticalPanel();
	private MainViewEditor main;
	
	private HorizontalPanel hPanelDetailsButton = new HorizontalPanel();
	
	/**
	 * Konstruktor
	 */
	public Details() {

		initWidget(this.vPanelDetails);
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
		
		
		TreeItem root = new TreeItem();
	    root.setText("Baugruppe");
	    
	    	TreeItem sub = new TreeItem();
	    	sub.setText("Unterbaugruppe");
	    
	    		sub.addTextItem("Bauteil 1");
	    		sub.addTextItem("Bauteil 2");
	    root.addItem(sub);
	    root.addTextItem("Bauteil 1");
	    root.addTextItem("Bauteil 2");
	    root.addTextItem("Bauteil 3");
	    
	   
	    
	    sub.addTextItem("untergruppe");
	    
	    
	    
	    Tree t = new Tree();
	    t.addItem(root);

	    
	    
	    this.vPanelDetails.add(t);
		
		
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
