package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.MainViewEditor;
/**
 * @author Schwab
 */


public class StuecklisteMain extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanelLeft = new VerticalPanel();
	private VerticalPanel vPanelRight = new VerticalPanel();
	private MainViewEditor main;
	
	private HorizontalPanel hPanelBtn = new HorizontalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();

	public StuecklisteMain() {

		initWidget(this.vPanel);
		this.main = main;
		
		//vPanel um HPanel ergänzen
		this.vPanel.add(hPanel);	
		
		//Anlegen der Buttons fürs Anzeigen, Editieren und Löschen
		Button show = new Button("Anzeigen");
		this.hPanelBtn.add(show);
		
		Button edit = new Button("Bearbeiten");
		this.hPanelBtn.add(edit);
		
		Button delete = new Button("Loeschen");
		this.hPanelBtn.add(delete);		
		
		
		//Linke u. Rechte Spalte anordnen in einem HorizontalPanel
		this.hPanel.add(vPanelLeft);
		vPanelLeft.setBorderWidth(1);
		
		this.hPanel.add(vPanelRight);
		vPanelRight.setBorderWidth(1);
		
		//hPanel mit Buttons der rechten Spalte zufügen
		this.vPanelRight.add(hPanelBtn);
		
		
		Label label1 = new Label("Hier werden alle Stuecklisten aufgelistet!");
		Label label2 = new Label("Stueckliste Motor");
		Label label3 = new Label("Stueckliste Getriebe");
		Label label4 = new Label("Stueckliste Sitze");
		Label label5 = new Label("Stueckliste Achsaufhaengung");
		Label label6 = new Label("Stueckliste Elektronik");
		this.vPanelLeft.add(label1);
		this.vPanelLeft.add(label2);
		this.vPanelLeft.add(label3);
		this.vPanelLeft.add(label4);
		this.vPanelLeft.add(label5);
		this.vPanelLeft.add(label6);

	}
}
