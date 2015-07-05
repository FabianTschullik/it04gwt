package de.hdm.it04.client.report;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.editor.AlertGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.editor.EnderzeugnisGUI;
import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.client.service.report.It04gwtServiceReportAsync;
import de.hdm.it04.shared.Enderzeugnis;


public class MaterialbedarfGUI {
	
	AlertGUI alertGUI = new AlertGUI();
	
	TextBox txtSuchen = new TextBox();
	private final It04gwtServiceReportAsync smsReport = GWT.create(It04gwtServiceReport.class);
	
	
	/**
	 * Suchfunktion
	 */
	public Widget suchen(){
		VerticalPanel vPanel = new VerticalPanel();
		
		txtSuchen.setText("id oder Name");
		vPanel.add(txtSuchen);
		
		Button btnSuchen = new Button("Suchen");
		vPanel.add(btnSuchen);
		btnSuchen.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String ezSuche = txtSuchen.getText();
				
				if (ezSuche.matches("[0-9]+")){
					int id = Integer.parseInt(ezSuche);
					smsReport.getEnderzeugnis(id, new AsyncCallback<Vector<Enderzeugnis>>() {

						public void onFailure(Throwable arg0) {
							
						}

						public void onSuccess(Vector<Enderzeugnis> result) {
							ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));
							
						}
					});	
				}
				String name = ezSuche;
			
				smsReport.getEnderzeugnis(name, new AsyncCallback<Vector<Enderzeugnis>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Enderzeugnis> result) {
						ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(result));;		
					}
				});	
			}
		} );
		return vPanel;
	}	
}