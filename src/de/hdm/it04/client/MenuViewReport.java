package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * 
 * @author Benjamin, Fabian
 *
 */
public class MenuViewReport extends Composite {
	
	//Für die horizontale Anorderung der Buttons
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	//Konstruktor
	public MenuViewReport() {
		
		initWidget(this.hPanel);
		
		//Erzeugung der Buttons
		Button StrukStueckListBtn = new Button("Strukturstueckliste anzeigen");
		StrukStueckListBtn.addClickHandler(new StrukStueckListBtnClickHandler());
		this.hPanel.add(StrukStueckListBtn);
		
		Button MatBedarfBtn = new Button("Materialbedarf berechnen");
		MatBedarfBtn.addClickHandler(new MatBedarfBtnClickHandler());
		this.hPanel.add(MatBedarfBtn);
		
		Button ImpressumBtn = new Button("Impressum");
		ImpressumBtn.addClickHandler(new ImpressumBtnClickHandler());
		this.hPanel.add(ImpressumBtn);
	}
	
	//ClickHandler Button Strukturstuecklisten
	private class StrukStueckListBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
	//ClickHandler Button Materialbedarf
	private class MatBedarfBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
		}
	}
	//ClickHandler Button Impressum
	private class ImpressumBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
		}
	}
}
