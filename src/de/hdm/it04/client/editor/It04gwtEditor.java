package de.hdm.it04.client.editor;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
	public class It04gwtEditor implements EntryPoint {
		
		

		/**
		 * Entry Point Methode!
		 */
		
		
		public void onModuleLoad() {
			
			
			MainViewEditor mainView = new MainViewEditor();
			RootPanel.get().add(mainView);
		
			
		}
	}
	
	
	
	











