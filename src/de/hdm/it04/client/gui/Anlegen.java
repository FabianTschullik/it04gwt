package de.hdm.it04.client.gui;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.shared.Baugruppe;


public class Anlegen {
	
public VerticalPanel vPanelDetails;
	

	

	public Anlegen(VerticalPanel vPanelDetails){
		
		this.vPanelDetails = vPanelDetails;
		
		Label nameLbl = new Label("Name");
		this.vPanelDetails.add(nameLbl);
		
		TextBox nameTxt = new TextBox();
		this.vPanelDetails.add(nameTxt);
		
		Label beschreibungLbl = new Label("Beschreibung");
		this.vPanelDetails.add(beschreibungLbl);
		
		TextBox beschreibungTxt = new TextBox();
		this.vPanelDetails.add(beschreibungTxt);
		
		
		Button speichernBtn = new Button("Speichern");
		this.vPanelDetails.add(speichernBtn);
		
		
		//this.vPanelDetails.add(anlegen());		
		
			
		
		//testBtn.addClickHandler(new TestBtnClickHandler());
		//vPanelDetails.add(anlegen());
		
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		
		//Button speichernBtn = new Button("Speichern");
		//speichernBtn.addClickHandler(new SpeichernBtnClickHandler());
		
		
		
		
		layout.setWidget(0,1, (IsWidget) new Label("Name"));
		layout.setWidget(1, 1, new TextBox());
		layout.setWidget(2, 1, (IsWidget) new Label("Beschreibung"));
		layout.setWidget(3, 1, new TextBox());
		layout.setWidget(4, 1, speichernBtn);
		
		
		
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		
		
	}
	
	
	
	public class SpeichernBtnClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				
				Baugruppe bg = new Baugruppe();
				//--> Hole Text aus NameTxt und beschreibungTxt und erstelle neues Objekt
				//danach Clear() des vPanelDetails
			
			
		
				
						
		}	
	}
	
	
	
	
}
