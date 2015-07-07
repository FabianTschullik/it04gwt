package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Die Klasse AlertGUI stellt ein Panel fuer das Anzeigen von Statusmeldungen
 * bereit.
 * 
 * @author Tschullik
 *
 */

public class AlertGUI {

	/**
	 * Methode die augerufen wird, wenn ein Alert erstellt wird. Diese Methode
	 * hat keinen Rückgabewert, da das erstellte Panel dem RootPannel
	 * hinzugefügt wird.
	 * 
	 * @param text
	 * @param color
	 */

	public void load(String text, String color) {

		RootPanel.get("alert").clear();
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel hPanel = new HorizontalPanel();

		Label lbl = new Label();

		lbl.setText(text);

		if (color == "red") {

			lbl.setStyleName("alertRed");
		}

		else {

			if (color == "green") {

				lbl.setStyleName("alertGreen");
			}
		}

		/**
		 * Widgets werden hinzugefuegt.
		 */

		hPanel.add(lbl);

		Button btn = new Button("Close");
		btn.addClickHandler(new BtnClickHandler());

		vPanel.add(hPanel);

		hPanel.add(btn);
		vPanel.add(hPanel);

		RootPanel.get("alert").add(vPanel);

	}

	public class BtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			RootPanel.get("alert").clear();
		}
	}
}