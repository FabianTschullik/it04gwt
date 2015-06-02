package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

public class StrukturstuecklisteMain extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewReport main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	//Konstruktor
	public StrukturstuecklisteMain() {
		initWidget(this.vPanel);
		this.main = main;
		
		Label label1 = new Label("Hallo StrukturstuecklisteMain");
		this.vPanel.add(label1);
		
		Button AnzeigenBtn1 = new Button ("Anzeigen");
		AnzeigenBtn1.addClickHandler(new AnzeigenBtn1ClickHandler());
		this.hPanel.add(AnzeigenBtn1);
		
		this.vPanel.add(hPanel);
	}
	
	public void openStrukturstuecklisteMain(){
		vPanel.clear();
		StrukturstuecklisteMain StrukturstuecklisteMain = new StrukturstuecklisteMain();
		vPanel.add(StrukturstuecklisteMain);
	}
	
	private class AnzeigenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openStrukturstuecklisteMain();

		}
	}

}
