package de.hdm.it04.client.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.Impressum;

public class NavigationReport {

		private static Widget navigation;
		
		public static void load() {

			navigation = createNavigation();
		}

		private static Widget createNavigation() {

			HorizontalPanel hPanel = new HorizontalPanel();

			Button btnStrukliste = new Button("Strukturstueckliste");
			btnStrukliste.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
				}
			});
			hPanel.add(btnStrukliste);

			Button btnMatbedarf = new Button("Materialbedarf");
			btnMatbedarf.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
				}
			});
			hPanel.add(btnMatbedarf);

			Button btnImpressum = new Button("Impressum");
			btnImpressum.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Impressum.load();
					
				}
			});
			hPanel.add(btnImpressum);
			
			RootPanel.get("topmenu").add(hPanel);

			return hPanel;
		}
	}
