package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


public class BearbeitenBaugruppeMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public BearbeitenBaugruppeMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Bearbeiten Sie die Baugruppe:");
		this.vPanel.add(label1);
		
		
		Label label2 = new Label("Feld 1");
		this.vPanel.add(label2);
		
		TextBox box1 = new TextBox();
		this.vPanel.add(box1);
		
		Label label3 = new Label("Feld 2");
		this.vPanel.add(label3);
		
		TextBox box2 = new TextBox();
		this.vPanel.add(box2);
		
		Label label4 = new Label("Feld 3");
		this.vPanel.add(label4);
		
		TextBox box3 = new TextBox();
		this.vPanel.add(box3);
		
		Label label5 = new Label("Feld 4");
		this.vPanel.add(label5);
		
		TextBox box4 = new TextBox();
		this.vPanel.add(box4);
		
		Button SpeichernBtn1 = new Button("Speichern");
		SpeichernBtn1.addClickHandler(new SpeichernBtn1ClickHandler());
		this.vPanel.add(SpeichernBtn1);

	}
	
	public void openSpeichernBaugruppeMain() {
		vPanel.clear();
		SpeichernBaugruppeMain SpeichernBaugruppeMain = new SpeichernBaugruppeMain();
		vPanel.add(SpeichernBaugruppeMain);
	}

	
	private class SpeichernBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openSpeichernBaugruppeMain();

		}
	
	}}

