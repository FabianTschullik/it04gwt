package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.ImpressumMain;
import de.hdm.it04.client.report.MaterialbedarfGUI;


public class ReportGUI extends Composite {
	VerticalPanel vPanel = new VerticalPanel();
	HorizontalPanel hPanelButtons = new HorizontalPanel();
	VerticalPanel vPanelContent = new VerticalPanel();
	
	/**
	 * Konstruktor
	 */
	public ReportGUI() {
		
		initWidget(this.vPanel);
		this.vPanel.add(hPanelButtons);
		this.vPanel.add(vPanelContent);
		
		/**
		 * Buttons fuer Stueckliste, Materialbedarf und Impressum
		 */
		Button BtnStueckliste = new Button("Strukturstueckliste");
		BtnStueckliste.addClickHandler(new BtnStuecklisteClickHandler());
		this.hPanelButtons.add(BtnStueckliste);
		
		Button BtnMaterialbedarf = new Button("Materialbedarf");
		BtnMaterialbedarf.addClickHandler(new BtnMaterialbedarfClickHandler());
		this.hPanelButtons.add(BtnMaterialbedarf);
		
		Button BtnImpressum = new Button("Impressum");
		BtnImpressum.addClickHandler(new BtnImpressumClickHandler());
		this.hPanelButtons.add(BtnImpressum);
		
	}

	
	public class BtnStuecklisteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vPanelContent.clear();	
			StrukturstuecklisteGUI strukturstuecklistegui = new StrukturstuecklisteGUI();
			vPanelContent.add(strukturstuecklistegui);
		}	
	}
	
	public class BtnMaterialbedarfClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vPanelContent.clear();
			MaterialbedarfGUI materialbedarfgui = new MaterialbedarfGUI();
			vPanelContent.add(materialbedarfgui);
			
		}	
	}
	
	public class BtnImpressumClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vPanelContent.clear();
			ImpressumMain impmain = new ImpressumMain();
			vPanelContent.add(impmain);
		}	
	}	
}

