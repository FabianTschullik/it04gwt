package de.hdm.it04.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.report.AdministrationCommonReport;
import de.hdm.it04.shared.report.AdministrationCommonReportAsync;
import de.hdm.it04.shared.CommonSettings;

public class ClientSideSettingsReport extends CommonSettings {

		private static AdministrationCommonReportAsync administration = null;

		public static AdministrationCommonReportAsync getAdministration() {

			if (administration == null) {

				administration = GWT.create(AdministrationCommonReport.class);

				final AsyncCallback<Void> initAdministrationCallback = new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(Void result) {

					}
				};

				administration.init(initAdministrationCallback);
			}

			return administration;
		}
	}