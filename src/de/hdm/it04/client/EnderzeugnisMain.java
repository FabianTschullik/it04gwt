package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EnderzeugnisMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	
	public EnderzeugnisMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Hallo EnderzeugnisMain");
		this.vPanel.add(label1);

	}
}
