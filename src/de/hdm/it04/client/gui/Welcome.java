package de.hdm.it04.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Welcome {
	
	private VerticalPanel vPanel = new VerticalPanel();

	public HTML load() {

		HTML html = new HTML(
				"<h3>Herzlich Willkommen</h3>");
						
		
		return html;

}}
