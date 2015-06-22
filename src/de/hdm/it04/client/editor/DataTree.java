package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.MainViewEditor;


public class DataTree extends Composite{
	
	private VerticalPanel vPanelTreeTree = new VerticalPanel();
	
	private MainViewEditor main;
	
	/**
	 * Konstruktor
	 */
	public DataTree(){
	initWidget(this.vPanelTreeTree);
	this.main = main;
	
	
	Label platzhalterLabel = new Label ("Tree Platzhalter");
	this.vPanelTreeTree.add(platzhalterLabel);
	
	}
}
