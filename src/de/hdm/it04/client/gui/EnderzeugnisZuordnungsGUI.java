package de.hdm.it04.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EnderzeugnisZuordnungsGUI extends MainGUI {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public EnderzeugnisZuordnungsGUI(VerticalPanel vPanel){	
		super(serviceImpl);
		this.vPanel = vPanel;
	}
	

}
