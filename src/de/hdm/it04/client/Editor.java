package de.hdm.it04.client;

import com.google.gwt.core.client.EntryPoint;

import de.hdm.it04.shared.AdministrationCommonAsync;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
	public class Editor implements EntryPoint {
		
		

		/**
		 * Entry Point Methode!
		 */
		
		
		public void onModuleLoad() {
			
			
			
			Navigation.load();
			DataTree.load();
			
			
			
			
			AdministrationCommonAsync administration = ClientsideSettings
					.getAdministration();
			
			
	
		

			
		}
	}
	
	
	
	











