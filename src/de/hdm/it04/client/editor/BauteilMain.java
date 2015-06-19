package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class BauteilMain extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public BauteilMain() {

		initWidget(this.vPanel);
		this.main = main;
		
		
		Label label1 = new Label("Hallo BauteilMain");
		this.vPanel.add(label1);
		
		Button AnzeigenBtn1 = new Button("Anzeigen");
		AnzeigenBtn1.addClickHandler(new AnzeigenBtn1ClickHandler());
		this.hPanel.add(AnzeigenBtn1);
		
		Button BearbeitenBtn1 = new Button("Bearbeiten");
		BearbeitenBtn1.addClickHandler(new BearbeitenBtn1ClickHandler());
		this.hPanel.add(BearbeitenBtn1);
		
		Button LoeschenBtn1 = new Button("Loeschen");
		LoeschenBtn1.addClickHandler(new LoeschenBtn1ClickHandler());
		this.hPanel.add(LoeschenBtn1);
		
		this.vPanel.add(hPanel);
		
	}
	
	
		public void openBearbeitenBauteilMain() {
			vPanel.clear();
			BearbeitenBauteilMain BearbeitenBauteilMain = new BearbeitenBauteilMain();
			vPanel.add(BearbeitenBauteilMain);
		}
		
		public void openLoeschenBauteilMain() {
			vPanel.clear();
			LoeschenBauteilMain LoeschenBauteilMain = new LoeschenBauteilMain();
			vPanel.add(LoeschenBauteilMain);
		}

	
	
	private class AnzeigenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//main.openBauteilMain();

		}
	}
	
	private class BearbeitenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openBearbeitenBauteilMain();

		}
	}
	
	private class LoeschenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openLoeschenBauteilMain();

		}
	}
	
	
	
}
