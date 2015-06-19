package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class EnderzeugnisMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public EnderzeugnisMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Hallo EnderzeugnisMain");
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
	
	
		public void openBearbeitenEnderzeugnisMain() {
			vPanel.clear();
			BearbeitenEnderzeugnisMain BearbeitenEnderzeugnisMain = new BearbeitenEnderzeugnisMain();
			vPanel.add(BearbeitenEnderzeugnisMain);
		}
		
		public void openLoeschenEnderzeugnisMain() {
			vPanel.clear();
			LoeschenEnderzeugnisMain LoeschenEnderzeugnisMain = new LoeschenEnderzeugnisMain();
			vPanel.add(LoeschenEnderzeugnisMain);
		}

	
	
	private class AnzeigenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//main.openEnderzeugnisMain();

		}
	}
	
	private class BearbeitenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openBearbeitenEnderzeugnisMain();

		}
	}
	
	private class LoeschenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openLoeschenEnderzeugnisMain();

		}
	}
	
	
	
}


