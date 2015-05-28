package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class MenuViewEditor extends Composite {

	private HorizontalPanel hPanel = new HorizontalPanel();
	private MainViewEditor main;

	public MenuViewEditor(MainViewEditor main) {

		initWidget(this.hPanel);
		this.main = main;

		Button BaugruppeBtn1 = new Button("Baugruppe");
		BaugruppeBtn1.addClickHandler(new BaugruppeBtn1ClickHandler());
		this.hPanel.add(BaugruppeBtn1);

		Button BauteilBtn1 = new Button("Bauteil");
		BauteilBtn1.addClickHandler(new BauteilBtn1ClickHandler());
		this.hPanel.add(BauteilBtn1);

		Button EnderzeugnisBtn1 = new Button("Enderzeugnis");
		EnderzeugnisBtn1.addClickHandler(new EnderzeugnisBtn1ClickHandler());
		this.hPanel.add(EnderzeugnisBtn1);

		Button StuecklisteBtn1 = new Button("Stueckliste");
		EnderzeugnisBtn1.addClickHandler(new StuecklisteBtn1ClickHandler());
		this.hPanel.add(StuecklisteBtn1);

		Button ImpressumBtn1 = new Button("Impressum");
		ImpressumBtn1.addClickHandler(new ImpressumBtn1ClickHandler());
		this.hPanel.add(ImpressumBtn1);

	}

	private class BaugruppeBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openBaugruppeMain();

		}

	}

	private class BauteilBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			main.openBauteilMain();

		}

	}

	private class EnderzeugnisBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// main.openEnderzeugnisMain();

		}

	}

	private class StuecklisteBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// main.openStuecklsiteMain();

		}

	}

	private class ImpressumBtn1ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// main.openImpressumMain();
		}
	}

}
