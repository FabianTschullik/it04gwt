package de.hdm.it04.client.report;

import com.google.gwt.core.client.EntryPoint;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
public class ReportGenerator implements EntryPoint {

		@Override
		public void onModuleLoad() {
			
			NavigationReport.load();

		}

}
