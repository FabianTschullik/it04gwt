package de.hdm.it04.client;
import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.it04.client.service.It04gwtServiceClientImpl;







public class It04gwt implements EntryPoint {
	
	public void onModuleLoad() {
		It04gwtServiceClientImpl clientImpl = new It04gwtServiceClientImpl(GWT.getModuleBaseURL()+ "sms");
		RootPanel.get().add(clientImpl.getMainGUI());
		
		
		
		
		
		
	}
}






