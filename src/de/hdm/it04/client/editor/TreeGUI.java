package de.hdm.it04.client.editor;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.client.service.It04gwtServiceAsync;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;

import de.hdm.it04.shared.TeileListe;


public class TreeGUI {
	
	public Tree t = new Tree();
	public TreeItem root = new TreeItem();
	public Baugruppe bg;
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	
	public static Vector<Bauteil> allBt;
	public static Vector<Baugruppe> allBg;
	
	
	public TreeGUI (){
		sms.getAll(new AsyncCallback<Vector<Bauteil>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Keine Bauteile");
				
			}

			@Override
			public void onSuccess(Vector<Bauteil> result) {
				save(result);
				
				
			}

			
			
		});
		
		
		
	}
	
	private void save(Vector<Bauteil> result) {
		allBt = result;
		sms.getAllBaugruppen(new AsyncCallback<Vector<Baugruppe>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Keine Baugruppen");
				
			}

			@Override
			public void onSuccess(Vector<Baugruppe> result) {
				saveBg(result);
				
			}

			
			
		});
	}
	
	
	private void saveBg(Vector<Baugruppe> result) {
		allBg = result;

		
	}
	
	public TreeItem treerek(Baugruppe baugruppe){
		Vector<TeileListe> stueckliste= baugruppe.connectedBaugruppen;
		TreeItem sub = new TreeItem();
		
		if(stueckliste.isEmpty()){
			stueckliste = baugruppe.connectedBauteile;
			for(int i = 0; i<stueckliste.size();i++){
				for(int j = 0; j<allBt.size(); j++)
					if(stueckliste.elementAt(i).getId() == allBt.elementAt(j).getId()){
						TreeItem root = new TreeItem();
						root.setText(allBt.elementAt(j).getName());
						sub.addItem(root);
						
						
						
						
					}
			}
			sub.setText(baugruppe.getName());
			return sub;
				
		}
		sub.setText(baugruppe.getName());
			
			for(int i = 0; i<stueckliste.size();i++){
				for(int j = 0; j<allBg.size(); j++){
					if(stueckliste.elementAt(i).getId() == allBg.elementAt(j).getId()){
						//TreeItem root = new TreeItem();
						//root.setText(allBg.elementAt(j).getName());
						//sub.addItem(root);
						
						 sub.addItem(treerek(allBg.elementAt(j)));
						
					}
				}
			}
		
			
		
			
		
		return sub;
	}
	
	public Widget tree(Baugruppe baugruppe){
		
		
		
		VerticalPanel vPanel= new VerticalPanel();
		for(int i = 0; i<allBg.size(); i++){
			Label lbl = new Label(allBg.elementAt(i).getName());
			vPanel.add(lbl);
		}
		
		
		
		t.addItem(treerek(baugruppe));
		
		
		return t;
		
}
		/*
		
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
    	
    	
    	return t;	*/
	
	
	
	
	
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
