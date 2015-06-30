package de.hdm.it04.client.report;

import com.google.gwt.core.client.EntryPoint;

import de.hdm.it04.client.report.ClientSideSettingsReport;
import de.hdm.it04.shared.report.AdministrationCommonReportAsync;

	/**
	 * Entry point classes define <code>onModuleLoad()</code>.
	 */
public class ReportGenerator implements EntryPoint {

		@Override
		public void onModuleLoad() {
			
			NavigationReport.load();
			
			AdministrationCommonReportAsync administration = ClientSideSettingsReport
					.getAdministration();

		}

}
