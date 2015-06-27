package de.hdm.it04.client.editor;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.it04.client.gui.AnlegenBauteil;
import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
	public class It04gwtEditor implements EntryPoint {
		
		

		/**
		 * Entry Point Methode!
		 */
		
		
		public void onModuleLoad() {
			
			
			
			It04gwtServiceClientImpl clientImpl = new It04gwtServiceClientImpl(GWT.getModuleBaseURL()+"sms");
			RootPanel.get().add(clientImpl.getMainGUI());
		}
	}
	
	
	
	











