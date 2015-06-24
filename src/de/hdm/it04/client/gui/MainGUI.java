package de.hdm.it04.client.gui;



import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Element;

public class MainGUI extends Composite {
	
	private It04gwtServiceClientImpl serviceImpl;
	private VerticalPanel vPanel = new VerticalPanel();
	FlexTable flex = new FlexTable();
	
	
	public MainGUI(It04gwtServiceClientImpl serviceImpl){
		initWidget(this.vPanel);
		this.serviceImpl = serviceImpl;
		
		Button testBtn = new Button("Test");
		testBtn.addClickHandler(new TestBtnClickHandler());
		this.vPanel.add(testBtn);
		
		Label lbl = new Label("Hallo");
		this.vPanel.add(lbl);
		
	}
	
	
	public void showConnectedBauteil(Vector<Element> elemente){
		
		this.flex = new FlexTable();
		flex.setText(0, 0, "Beschreibung");
		flex.setText(1, 0, elemente.elementAt(1).getBeschreibung());
		this.vPanel.add(flex);
		
		
	}
	
	
	private class TestBtnClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			

		serviceImpl.findConnectedBauteileByKey(1);
		
		
		}
		
		
		
	}
	
	
	

}
