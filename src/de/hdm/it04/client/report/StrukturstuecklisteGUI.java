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
import de.hdm.it04.client.editor.BaugruppeGUI;
import de.hdm.it04.client.editor.BauteilGUI;
import de.hdm.it04.client.editor.ContentContainer;
import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.client.service.report.It04gwtServiceReportAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;


public class StrukturstuecklisteGUI {
	
	AlertGUI alertGUI = new AlertGUI();
	
	TextBox txtSuchen = new TextBox();
	private final It04gwtServiceReportAsync smsReport = GWT.create(It04gwtServiceReport.class);
	
	/**
	 * Suche nach Baugruppe per Name oder ID
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
				String bgSuche = txtSuchen.getText();
				
				if (bgSuche.matches("[0-9]+")){
					int id = Integer.parseInt(bgSuche);
					smsReport.getBaugruppe(id, new AsyncCallback<Vector<Baugruppe>>() {

						public void onFailure(Throwable arg0) {
							
						}

						public void onSuccess(Vector<Baugruppe> result) {
							//ContentContainer.getInstance().setContent(new BaugruppeGUI().showBaugruppeStrukturstueckliste(result));
							
						}
					});	
				}
				String name = bgSuche;
			
				smsReport.getBaugruppe(name, new AsyncCallback<Vector<Baugruppe>>() {

					
					public void onFailure(Throwable arg0) {
											
					}

					public void onSuccess(Vector<Baugruppe> result) {
						//ContentContainer.getInstance().setContent(new BaugruppeGUI().showAllBaugruppen(result));;		
					}
				});	
			}
		} );
		return vPanel;
	}
}