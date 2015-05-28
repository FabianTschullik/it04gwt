package de.hdm.it04.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 * @author Benjamin, Fabian
	 */
	public class It04gwtReport implements EntryPoint {
		
		//Entrypoint Methode
		public void onModuleLoad() {
			//Erzeugen einer MainViewReport Instanz
			MainViewReport mainViewReport = new MainViewReport();
			//Zuweisung der neu erstellten Instanz an das RootPanel
			RootPanel.get().add(mainViewReport);
		}
	}