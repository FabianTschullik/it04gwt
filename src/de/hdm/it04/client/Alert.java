package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Alert {

	private static Widget alert;

	public static void load(String text, String color) {

		alert = createAlert(text, color);
	}

	private static Widget createAlert(String text, String color) {

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

			else {
				return null;
			}

		}

		hPanel.add(lbl);
		
		Button btnClose = new Button("Close");
		btnClose.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get("alert").clear();
			}
		});
		
		btnClose.addStyleName("AlertCloseButton");
		hPanel.add(btnClose);
		hPanel.addStyleName("paddedHorizontalPanel");
		
		RootPanel.get("alert").clear();
		RootPanel.get("alert").add(hPanel);
		return hPanel;
	}
}
