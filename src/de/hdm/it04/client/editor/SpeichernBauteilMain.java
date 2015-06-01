package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SpeichernBauteilMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	
	public SpeichernBauteilMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Das Element wurde gespeichert");
		this.vPanel.add(label1);

	}
}