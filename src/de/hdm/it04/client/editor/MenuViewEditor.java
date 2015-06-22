package de.hdm.it04.client.editor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * 
 * @author Tschullik, Schwab
 *
 */

public class MenuViewEditor extends Composite {

	private HorizontalPanel hPanel = new HorizontalPanel();
	private MainViewEditor main;

	public MenuViewEditor(MainViewEditor main) {

		initWidget(this.hPanel);
		this.main = main;

		/**
		 * Buttons, um zwischen Editor und Impressum wechseln zu können
		 */
		Button EditorBtn1 = new Button("Editor");
		EditorBtn1.addClickHandler(new EditorBtn1ClickHandler());
		this.hPanel.add(EditorBtn1);

		Button ImpressumBtn1 = new Button("Impressum");
		ImpressumBtn1.addClickHandler(new ImpressumBtn1ClickHandler());
		this.hPanel.add(ImpressumBtn1);

	}


	private class EditorBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openDashboard();

		}

	}

	private class ImpressumBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openImpressumMain();
		}
	}

}
