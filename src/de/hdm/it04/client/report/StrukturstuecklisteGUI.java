package de.hdm.it04.client.report;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StrukturstuecklisteGUI extends Composite {

		VerticalPanel vPanel = new VerticalPanel();
		VerticalPanel vPanelContent = new VerticalPanel();
		HorizontalPanel hPanelBaugruppe = new HorizontalPanel();
		
		
		public StrukturstuecklisteGUI() {
			initWidget(vPanel);
			
			this.vPanel.add(hPanelBaugruppe);
			
			Label name = new Label("Baugruppe:");
			this.hPanelBaugruppe.add(name);
			
			ListBox listBox = new ListBox();
			this.hPanelBaugruppe.add(listBox);
			
			
			this.vPanel.add(vPanelContent);
			Label platzhalter = new Label("Hier wird die Strukturstueckliste stehen");
			this.vPanelContent.add(platzhalter);
		}
	}
