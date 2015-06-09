package de.hdm.it04.client.gui;




import java.sql.Timestamp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;





import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Bauteil;





public class MainGUI extends Composite {
private VerticalPanel vPanel = new VerticalPanel();
private TextBox txt1;
private TextBox insertname;
private Label resultlbl;
private Label resultid;
private Label erfolg;
private TextBox insertbeschreibung;
private TextBox insertmaterialBezeichnung;





private DialogBox dBox;

private It04gwtServiceClientImpl serviceImpl;

public MainGUI(It04gwtServiceClientImpl serviceImpl) {
	
	initWidget(this.vPanel);
	this.serviceImpl = serviceImpl;
	
	this.txt1 = new TextBox();
	txt1.setText("Bitte hier ID eintragen...");
	this.vPanel.add(txt1);
	
	Button btn1 = new Button("Suchen");
	btn1.addClickHandler(new Btn1ClickHandler());
	this.vPanel.add(btn1);
	
	this.resultlbl = new Label("Ergebnis wird hier stehen");
	this.vPanel.add(resultlbl);
	
	this.resultid = new Label("Hier wird ID stehen");
	this.vPanel.add(resultid);

	
	
	
	this.dBox = new DialogBox();
	this.dBox.setTitle("Achtung");
	this.dBox.setText("Bauteil wurde nicht gefunden");
	
	this.insertname = new TextBox();
	this.vPanel.add(insertname);
	
	this.insertbeschreibung = new TextBox();
	this.vPanel.add(insertbeschreibung);
	
	this.insertmaterialBezeichnung = new TextBox();
	this.vPanel.add(insertmaterialBezeichnung);
	
	
	
	
	
	Button btn2 = new Button("Speichern");
	btn2.addClickHandler(new Btn2ClickHandler());
	this.vPanel.add(btn2);
	
	

	
	
	
}

public void showBauteil(Bauteil bt) {
	this.resultlbl.setText(bt.getName());
	
	int id = bt.getId();
	
	this.resultid.setText(new Integer(id).toString());
	this.vPanel.add(resultid);
	
	
	
	
}


public void showError() {
	this.vPanel.add(dBox);
	
}

public void showSucess() {
	
	this.erfolg = new Label("Erfolg");
	this.vPanel.add(erfolg);
	

	
}




	
	

	

	







private class Btn1ClickHandler implements ClickHandler {

	@Override
	public void onClick(ClickEvent event) {
		String stringid = txt1.getText();
		int id;
		id = Integer.parseInt(stringid);
		serviceImpl.getBauteil(id);
		
	}
}
	
	
	private class Btn2ClickHandler implements ClickHandler{
		
	
	public void onClick(ClickEvent event) {
		
		Timestamp aktuelleZeit = null;
		
		
		
		String name = insertname.getText();
		String beschreibung = insertbeschreibung.getText();
		String materialBezeichnung = insertmaterialBezeichnung.getText();
		Long erstellungsZeit = aktuelleZeit.getTime();
		
		
		
		serviceImpl.create(name, beschreibung, materialBezeichnung, erstellungsZeit);
		
	}}
	
	}





	
	



