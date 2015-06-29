package de.hdm.it04.client.report;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;


	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
public class It04gwtReport implements EntryPoint {
		
		public void onModuleLoad() {
			ReportGUI reportgui = new ReportGUI();
			RootPanel.get().add(reportgui);
			
			//It04gwtServiceClientImpl clientImpl = new It04gwtServiceClientImpl(GWT.getModuleBaseURL()+"sms");
			//RootPanel.get().add(clientImpl.getMainGUI());
		}	
}
