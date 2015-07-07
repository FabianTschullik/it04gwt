package de.hdm.it04.client.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
/**
 * Die Klasse BenutzerHandbuch ermoeglicht es dem User, sich das Benutzerhandbuch anzeigen zu lassen.
 * Hier wird das User Interface dafuer definiert.
 * @author Maehler
 *
 */
public class BenutzerHandbuch {
	private VerticalPanel vPanel = new VerticalPanel();
	DialogBox box = new DialogBox();
	

	public Widget load() {		
		
		HTML html = new HTML(
				"<h1>Hier finden Sie das Benutzerhandbuch: <h1> ");
		
		vPanel.add(html);
		
		Frame frame = new Frame(GWT.getModuleBaseURL()+"images/Benutzerhandbuch.pdf");

		frame.setPixelSize(Window.getClientWidth()/2, 700);
		vPanel.add(frame);
			
		box.add(vPanel);
		
		box.setGlassEnabled(true);
		box.setAnimationEnabled(true);
		
		int left = Window.getClientWidth()/2;
		int top = Window.getClientHeight()/2;
		box.setPopupPosition(left, top);
		
		box.show();
		
		return box;
	}
}