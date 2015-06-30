package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DialogBoxEntree {
	
	private static Widget dialogBox;
	
	public static void load(){
		
		dialogBox = create();
		
	}
	
	
	private static Widget create(){
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);
		
		Button closeButton = new Button("Close");
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		
dialogBox.add(closeButton);
		
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(8);
	
		
		dialogContents.add(dialogBox);
		
		
		// Kommentare Deaktivieren und mehr Inhalt einfügen über HTML
		//dialogBox.show();
		
		return dialogContents;
	
}


}