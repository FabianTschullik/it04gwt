package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Schwab
 *
 */

public class LoeschenStuecklisteMain extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public LoeschenStuecklisteMain() {
		initWidget(this.vPanel);
		this.main = main;
		

		Label label1 = new Label("Seite Loeschen Stueckliste");
		vPanel.add(label1);
		
		vPanel.add(hPanel);
		Button LoeschenBtn1 = new Button ("Loeschen");
		this.hPanel.add(LoeschenBtn1);
		
		Button AbbrechenBtn1 = new Button ("Abbrechen");
		this.hPanel.add(AbbrechenBtn1);
	}

}
