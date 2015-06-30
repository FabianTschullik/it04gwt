package de.hdm.it04.client.enderzeugnis;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.ClientsideSettings;
import de.hdm.it04.client.baugruppe.BaugruppeMainForm;
import de.hdm.it04.shared.AdministrationCommonAsync;

public class EnderzeugnisSuchenForm {
	
	
	private static Widget suchen;

	public static void load() {

		suchen = createSuche();
	}

	private static Widget createSuche() {
		
		
		
		HorizontalPanel hPanelSearch = new HorizontalPanel();
		hPanelSearch.addStyleName("paddedHorizontalPanel");
		
		TextBox txt = new TextBox();
		txt.setVisibleLength(10);
		hPanelSearch.add(txt);

		Button btnSearch = new Button("Suchen");
		btnSearch.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AdministrationCommonAsync administration = ClientsideSettings
						.getAdministration();
				
			
				
				
				//administration.
				
			}
		});

		hPanelSearch.add(btnSearch);
		
		
		
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(hPanelSearch);

		return hPanelSearch;
	}

}
