package de.hdm.it04.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Schwab
 *
 */

public class BearbeitenStuecklisteMain extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private MainViewEditor main;
	
	
	public BearbeitenStuecklisteMain() {
		initWidget(vPanel);
		this.main = main;
		
		Button SpeichernBtn1 = new Button ("Speichern");
		this.hPanel.add(SpeichernBtn1);
		
		Button AbbrechenBtn1 = new Button ("Abbrechen");
		this.hPanel.add(AbbrechenBtn1);
	}
	
	

}
