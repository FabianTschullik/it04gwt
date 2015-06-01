package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoeschenBaugruppeMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	
	public LoeschenBaugruppeMain() {

		initWidget(this.vPanel);
		this.main = main;

		Label label1 = new Label("Das Element wurde geloescht");
		this.vPanel.add(label1);

	}
}