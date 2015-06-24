package de.hdm.it04.client.gui;

import java.awt.TextField;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Anlegen {
	
	

	

	public Anlegen(VerticalPanel vPanelDetails){
		
		
			
		
		//testBtn.addClickHandler(new TestBtnClickHandler());
		vPanelDetails.add(anlegen());
		
		
		
		
		
		
	}
	
	public Widget anlegen(){
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
		
		Button testBtn = new Button("Speichern");
		
		layout.setWidget(1, 1, new TextBox());
		layout.setWidget(2, 1, new TextBox());
		layout.setWidget(3, 1, testBtn);
		
		
		
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(layout);
		return decPanel;
	}
	
	
	
	
	
}
