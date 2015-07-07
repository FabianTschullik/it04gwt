package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Die Klasse ContentContainer
 * 
 * @author Tschullik
 *
 */
public class ContentContainer {

	private static ContentContainer singleton = new ContentContainer();

	private ContentContainer() {
	}

	/** 
	 * Static 'instance' method
	 * @return singleton 
	*/
	public static ContentContainer getInstance() {
		return singleton;
	}

	/**
	 * Setter-Methode, die dem content-Container ein Widget setzt.
	 * @param widget
	 */
	public void setContent(Widget widget) {

		RootPanel.get("content").clear();
		RootPanel.get("content").add(widget);
	}
}