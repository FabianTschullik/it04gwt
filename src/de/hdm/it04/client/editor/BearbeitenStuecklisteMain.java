package de.hdm.it04.client.editor;

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

public class BearbeitenStuecklisteMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	public BearbeitenStuecklisteMain() {
		initWidget(vPanel);
		this.main = main;
		
		
		Label label1 = new Label("Seite Bearbeiten Stueckliste");
		vPanel.add(label1);
		
		vPanel.add(hPanel);
		Button SpeichernBtn1 = new Button ("Speichern");
		this.hPanel.add(SpeichernBtn1);
		
		Button AbbrechenBtn1 = new Button ("Abbrechen");
		AbbrechenBtn1.addClickHandler(new AbbrechenBtn1ClickHandler());
		this.hPanel.add(AbbrechenBtn1);
	}
	
	public void openStuecklisteMain() {
		vPanel.clear();
		StuecklisteMain StuecklisteMain = new StuecklisteMain();
		vPanel.add(StuecklisteMain);
	}
	
	private class AbbrechenBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			openStuecklisteMain();
		}
	}
	

}
