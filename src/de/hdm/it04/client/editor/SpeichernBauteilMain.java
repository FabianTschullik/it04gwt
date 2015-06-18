package de.hdm.it04.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Tschullik
 *
 */
//BASCHDELKLASSE FUER TREE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class SpeichernBauteilMain extends Composite {
	
	private VerticalPanel vPanel = new VerticalPanel();
	private MainViewEditor main;
	
	public SpeichernBauteilMain() {

		initWidget(this.vPanel);
		this.main = main;
		
		
		TreeItem root = new TreeItem();
        root.setText("root");
        root.addTextItem("item0");
        root.addTextItem("item1");
        root.addTextItem("item2");
        
        Tree t = new Tree();
        t.addItem(root);
        

        // Add it to the root panel.
        //RootPanel.get().add(t);
     
       
       
		
		this.vPanel.add(t);

	}
}