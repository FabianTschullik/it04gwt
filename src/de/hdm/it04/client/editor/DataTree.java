
package de.hdm.it04.client.editor;

import java.util.Vector;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.it04.client.editor.MainViewEditor;
import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.client.service.It04gwtServiceClientImpl;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Enderzeugnis;


public class DataTree extends MainGUI{
	
	private static It04gwtServiceClientImpl serviceImpl;
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanelTree = new VerticalPanel();
	

	Vector v= new Vector();
	
	/**
	 * Konstruktor
	 */
	public DataTree(VerticalPanel vPanel){
		
		super(serviceImpl);
		this.vPanel = vPanel;

	
	}
	
	
	
	public void setTree(Vector<Baugruppe> baugruppen){
	
		
	v= baugruppen;
	Label platzhalterLabel = new Label ("Tree Platzhalter");
	this.vPanel.add(platzhalterLabel);
	
	
	
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

    
    
    this.vPanel.add(t);
	
	}
}
