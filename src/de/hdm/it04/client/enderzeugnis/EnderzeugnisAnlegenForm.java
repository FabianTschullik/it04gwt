package de.hdm.it04.client.enderzeugnis;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.Impressum;
import de.hdm.it04.client.ShowCase;



public class EnderzeugnisAnlegenForm 
{
	
	
	private static Widget anlegenForm;
	
	public static void load() {

		anlegenForm = createAnlegenForm();
	}
	
	
	

	private static Widget createAnlegenForm() {
	
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel hPanel1 = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		
		
		Label lbl1 = new Label("Name: ");
		hPanel1.add(lbl1);
		TextBox txtBox1 = new TextBox();
		hPanel1.add(txtBox1);
		
		vPanel.add(hPanel1);
		
		Label lbl2 = new Label("Preis: ");
		hPanel2.add(lbl2);
		TextBox txtBox2 = new TextBox();
		hPanel2.add(txtBox2);
		
		vPanel.add(hPanel2);
		
		Button btnAnlegen = new Button("Anlegen");
		btnAnlegen.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				
			}
		});
		vPanel.add(btnAnlegen);
		
		RootPanel.get("content").clear();
		RootPanel.get("content").add(vPanel);
		
		return vPanel;

	}
	
	

}
	