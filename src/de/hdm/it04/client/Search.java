package de.hdm.it04.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.baugruppe.BaugruppeMainForm;
import de.hdm.it04.client.enderzeugnis.EnderzeugnisAnlegenForm;

public class Search {
	
	private static Widget search;
	
	public static void load() {
		
		search = createSearch();
		

}
	
	private static Widget createSearch() {

HorizontalPanel hPanelBox = new HorizontalPanel();
hPanelBox.addStyleName("paddedHorizontalPanel");
HorizontalPanel hPanelSearch = new HorizontalPanel();
hPanelSearch.addStyleName("paddedHorizontalPanel");
VerticalPanel vPanel = new VerticalPanel();
vPanel.addStyleName("paddedVerticalPanel");

CheckBox checkBoxEz = new CheckBox("EZ");
CheckBox checkBoxBg = new CheckBox("BG");
CheckBox checkBoxBt = new CheckBox("BT");

checkBoxEz.addStyleName("checkbox");
checkBoxBg.addStyleName("checkbox");
checkBoxBt.addStyleName("checkbox");
hPanelBox.add(checkBoxEz);
hPanelBox.add(checkBoxBg);
hPanelBox.add(checkBoxBt);

vPanel.add(hPanelBox);

TextBox txt = new TextBox();
txt.setVisibleLength(10);
hPanelSearch.add(txt);

Button btnSearch = new Button("Suchen");
btnSearch.addClickHandler(new ClickHandler() {
	public void onClick(ClickEvent event) {
	}
});

hPanelSearch.add(btnSearch);
vPanel.add(hPanelSearch);
RootPanel.get("tree").add(vPanel);
return vPanel;

}}
