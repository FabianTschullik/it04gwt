package de.hdm.it04.client.editor;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
/**
 * 
 * @author Tschullik
 *
 */
public class TreeGUI {
	
	public Tree t = new Tree();
	public TreeItem root = new TreeItem();
	public Baugruppe bg;
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	
	
	public Widget tree(Baugruppe baugruppe){
		
		
		this.bg = baugruppe;
		
    	t.addSelectionHandler(new SelectionHandler<TreeItem>(){
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
					TreeItem selectedItem = event.getSelectedItem();
					
					
					Object result = selectedItem.getUserObject();
					if (result instanceof Baugruppe){
						int id = ((Baugruppe) result).getId();
						//sms.getBaugruppeForUpdate(id);
					}
					else if (result instanceof Bauteil){
						int id = ((Bauteil) result).getId();
						//sms.getBauteilForUpdate(id);
					}
						else{	
				}
				
				
			}
    		
    	});
    	
    	
    	
    	
		
    	//root.removeItems();
    	root.setText(bg.getName());
    	t.addItem(root);
    	
    	
    	for(int i=0; i<bg.connectedBaugruppen.size(); i++){
    		
    		sms.getBaugruppe(bg.connectedBaugruppen.elementAt(i).getId(), new AsyncCallback<Vector<Baugruppe>>() {

    			@Override
    			public void onFailure(Throwable caught) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onSuccess(Vector<Baugruppe> result) {
    				
    				addNextBaugruppe(result.firstElement());
    					
    			}
    		});
    		
    		                                
    		
    	}
    	
    	
    	return t;	
	}
	
	
	
	
	private void addNextBaugruppe(Baugruppe bg){
		
		//final TreeItem parent = new TreeItem();
		
		for(int i=0; i<bg.connectedBaugruppen.size();i++){
    		
    		sms.getBaugruppe(bg.connectedBaugruppen.elementAt(i).getId(), new AsyncCallback<Vector<Baugruppe>>() {

    			@Override
    			public void onFailure(Throwable caught) {
    				// TODO Auto-generated method stub
    				
    			}

    			@Override
    			public void onSuccess(Vector<Baugruppe> result) {
    				
    				
    				//addChildItem(parent, result.firstElement().getName());
    					
    			}
    		});
    		
    		
    	}
	}
	
	
	
	private void addChildItem(TreeItem parent, String label) {
		    TreeItem section = parent.addTextItem(label);
		    }
	
}
