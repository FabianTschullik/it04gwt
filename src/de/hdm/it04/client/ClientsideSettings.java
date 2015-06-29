package de.hdm.it04.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.shared.AdministrationCommon;
import de.hdm.it04.shared.AdministrationCommonAsync;
import de.hdm.it04.shared.CommonSettings;

public class ClientsideSettings extends CommonSettings{

	private static AdministrationCommonAsync administration = null;

	public static AdministrationCommonAsync getAdministration() {

		if (administration == null) {

			administration = GWT.create(AdministrationCommon.class);

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