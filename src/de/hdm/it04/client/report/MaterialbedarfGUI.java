package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MaterialbedarfGUI extends Composite {
	VerticalPanel vPanel = new VerticalPanel();
	VerticalPanel vPanelContent = new VerticalPanel();
	HorizontalPanel hPanelName = new HorizontalPanel();
	HorizontalPanel hPanelAnzahl = new HorizontalPanel();
	
	
	public MaterialbedarfGUI() {
		initWidget(vPanel);
		
		this.vPanel.add(hPanelName);
		this.vPanel.add(hPanelAnzahl);
		
		Label name = new Label("Enderzeugnis:");
		this.hPanelName.add(name);
		
		ListBox listBox = new ListBox();
		this.hPanelName.add(listBox);
		
		Label anzahl = new Label("Anzahl:");
		this.hPanelAnzahl.add(anzahl);
		
		TextBox textBox = new TextBox();
		this.hPanelAnzahl.add(textBox);
		
		Button BtnMatBedarf = new Button("Materialbedarf berechnen");
		BtnMatBedarf.addClickHandler(new BtnMatBedarfClickHandler());
		this.vPanel.add(BtnMatBedarf);
		
		
		this.vPanel.add(vPanelContent);
		Label platzhalter = new Label("Hier wird die berechnete Liste stehen");
		this.vPanelContent.add(platzhalter);
	}
	
	public class BtnMatBedarfClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
		}	
	}
}
