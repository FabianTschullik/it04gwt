package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Schwab
 *
 */

public class AnlegenStuecklisteMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	
	public AnlegenStuecklisteMain() {
		initWidget(vPanel);
		this.main = main;
		
		Label label1 = new Label ("Seite Anlegen Stueckliste");
		vPanel.add(label1);
		
		Label label2 = new Label ("Name");
		vPanel.add(label2);
		
		TextBox box1 = new TextBox();
		this.vPanel.add(box1);
		
		TextBox box2 = new TextBox();
		this.vPanel.add(box2);
		
		vPanel.add(hPanel);
		Button SpeichernBtn1 = new Button ("Speichern");
		hPanel.add(SpeichernBtn1);
		
		Button AbbrechenBtn1 = new Button ("Abbrechen");
		hPanel.add(AbbrechenBtn1);
		
	}
	
	public void openStuecklisteMain() {
		vPanel.clear();
		StuecklisteMain StuecklisteMain = new StuecklisteMain();
		vPanel.add(StuecklisteMain);
	}
}
