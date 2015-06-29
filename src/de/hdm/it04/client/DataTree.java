package de.hdm.it04.client;

import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class DataTree extends Composite{
	
	
	private static Widget tree;
	
	
	public static void load() {

		tree = createTree();
	}
		
		
	private static Widget createTree() {
		
		VerticalPanel vPanel = new VerticalPanel();
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
	    
	 
		   
		
	

	   vPanel.add(t);
	   
	  
	   RootPanel.get("tree").add(vPanel);
	     
return vPanel;
}
}