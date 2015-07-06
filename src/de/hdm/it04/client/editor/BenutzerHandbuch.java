package de.hdm.it04.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BenutzerHandbuch {
	private VerticalPanel vPanel = new VerticalPanel();
	

	public Widget load() {
				
		
		Frame frame = new Frame(GWT.getModuleBaseURL()+"images/Benutzerhandbuch.pdf");

		frame.setPixelSize(700, 700);
		
		HTML html = new HTML(
				"<h1>Hier finden Sie das Benutzerhandbuch: <h1> "
				+ "<img src='/it04gwt/war/background.jpg'>");
		
		return frame;
		

	}
}

