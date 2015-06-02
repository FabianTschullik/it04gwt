package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * 
 * @author Schwab, Tschullik
 *
 */
public class MenuViewReport extends Composite {
	
	//Für die horizontale Anorderung der Buttons
	private HorizontalPanel hPanel = new HorizontalPanel();
	private MainViewReport main2;
	
	//Konstruktor
	public MenuViewReport(MainViewReport main2) {
		
		initWidget(this.hPanel);
		this.main2 = main2;
		
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
			main2.openStrukturstuecklisteMain();
			
		}
	}
	//ClickHandler Button Materialbedarf
	private class MatBedarfBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main2.openMaterialbedarfMain();
		}
	}
	//ClickHandler Button Impressum
	private class ImpressumBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main2.openImpressumMain();
		}
	}
}
