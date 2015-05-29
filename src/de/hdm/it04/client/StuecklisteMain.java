package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StuecklisteMain extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;

	public StuecklisteMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Hallo StuecklisteMain");
		this.vPanel.add(label1);

	}
}
