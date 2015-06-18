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



		Button StuecklisteBtn1 = new Button("Stueckliste");
		StuecklisteBtn1.addClickHandler(new StuecklisteBtn1ClickHandler());
		this.hPanel.add(StuecklisteBtn1);

		Button ImpressumBtn1 = new Button("Impressum");
		ImpressumBtn1.addClickHandler(new ImpressumBtn1ClickHandler());
		this.hPanel.add(ImpressumBtn1);

	}


	private class StuecklisteBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openStuecklisteMain();

		}

	}

	private class ImpressumBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openImpressumMain();
		}
	}

}
