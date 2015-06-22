package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.MainViewEditor;


public class DataTree extends Composite{
	
	private VerticalPanel vPanelTreeTree = new VerticalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	
	private MainViewEditor main;
	
	/**
	 * Konstruktor
	 */
	public DataTree(){
	initWidget(this.vPanelTreeTree);
	this.main = main;
	
	
	Label platzhalterLabel = new Label ("Tree Platzhalter");
	this.vPanelTreeTree.add(platzhalterLabel);
	
	
	
	TreeItem root = new TreeItem();
    root.setText("Baugruppe");
    
    	TreeItem sub = new TreeItem();
    	sub.setText("Unterbaugruppe");
    
    		sub.addTextItem("Bauteil 1");
    		sub.addTextItem("Bauteil 2");
    root.addItem(sub);
    root.addTextItem("Bauteil 1");
    root.addTextItem("Bauteil 2");
    root.addTextItem("Bauteil 3");
    
   
    
    sub.addTextItem("untergruppe");
    
    
    
    Tree t = new Tree();
    t.addItem(root);
    
    
    this.vPanelTreeTree.add(t);
	
	}
}
