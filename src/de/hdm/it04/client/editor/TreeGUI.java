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
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.TeileListe;
/**
 *Die Klasse TreeGUI 
 * @author Geier, Tschullik
 *
 */

public class TreeGUI {
	
	public Tree t = new Tree();
	public TreeItem root = new TreeItem();
	public Baugruppe bg;
	private final It04gwtServiceAsync sms = GWT.create(It04gwtService.class);
	
	public static Vector<Bauteil> allBt;
	public static Vector<Baugruppe> allBg;
	public static Vector<Enderzeugnis> allEz;
	
	
	public TreeGUI (){

	}
	/**
	 * Methode zum Speichern von allen Bauteilen und Baugruppen global, 
	 * um einen Vereinfachten Zugriff auf die Bauteile und Baugruppen
	 * zu erreichen.
	 * @return: void
	 * 
	 */
	private void getAllVectoren() {
		
		sms.getAll(new AsyncCallback<Vector<Bauteil>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Keine Bauteile");
				
			}

			@Override
			public void onSuccess(Vector<Bauteil> result) {
				allBt = result;	
				
				sms.getAllBaugruppen(new AsyncCallback<Vector<Baugruppe>>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Keine Baugruppen");
						
					}

					@Override
					public void onSuccess(Vector<Baugruppe> result) {
						allBg = result;
						sms.getAllEnderzeugnisse(new AsyncCallback<Vector<Enderzeugnis>>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Vector<Enderzeugnis> result) {
								allEz = result;
								
							}
						});
					}
				});
			}
		});
						
	}
	
	/**
	 * Rekursive Methode, welche die Tree Elemente setzt 
	 * und miteinander zu einem Tree zusammen fügt
	 * @param baugruppe: Baugruppe von der wir jeweils
	 * die unteren Baugruppen und Bauteile dem Baum hinzufügen wollen
	 * @param anzahl : Anzahl in welcher Menge ein Bauteil oder eine
	 * Baugruppe benötigt wird
	 * @return TreeItem, welches immer dem vorherigen TreeItem hinzugefügt
	 * über return
	 */
	public TreeItem treerek(Baugruppe baugruppe, int anzahl){
		Vector<TeileListe> stueckliste= baugruppe.connectedBaugruppen;
		TreeItem sub = new TreeItem();
		
		if(stueckliste.isEmpty()){
			stueckliste = baugruppe.connectedBauteile;
			for(int i = 0; i<stueckliste.size();i++){
				for(int j = 0; j<allBt.size(); j++){
					if(stueckliste.elementAt(i).getId() == allBt.elementAt(j).getId()){
						TreeItem root = new TreeItem();
						root.setText(allBt.elementAt(j).getName()+ "[" + Integer.toString(stueckliste.elementAt(i).getAnzahl())+"]");
						root.setUserObject(allBt.elementAt(j));
						sub.addItem(root);
					}
				}
			}
			sub.setText(baugruppe.getName() + "[" + Integer.toString(anzahl) + "]");
			sub.setUserObject(baugruppe);
			return sub;
		}
		
		sub.setText(baugruppe.getName() + "[" + Integer.toString(anzahl) + "]");
		sub.setUserObject(baugruppe);
			
			for(int i = 0; i<stueckliste.size();i++){
				for(int j = 0; j<allBg.size(); j++){
					if(stueckliste.elementAt(i).getId() == allBg.elementAt(j).getId()){
						//TreeItem root = new TreeItem();
						//root.setText(allBg.elementAt(j).getName());
						//sub.addItem(root);
						int anz = stueckliste.elementAt(i).getAnzahl();
						 sub.addItem(treerek(allBg.elementAt(j),anz));
					}
				}
				
			}
			stueckliste = baugruppe.connectedBauteile;
			if(stueckliste.isEmpty()){
				return sub;
			}
			
			else {
				for(int i = 0; i<stueckliste.size();i++){
					for(int j = 0; j<allBt.size(); j++){
						if(stueckliste.elementAt(i).getId() == allBt.elementAt(j).getId()){
							TreeItem root = new TreeItem();
							root.setText(allBt.elementAt(j).getName()+ "[" + Integer.toString(stueckliste.elementAt(i).getAnzahl())+"]");
							root.setUserObject(allBt.elementAt(j));
							sub.addItem(root);
							
						}
					}
				}
			}
			
		return sub;
	}
	
	
	/**
	 * Methode, die den Tree erstellt mit Selektion Handler
	 * @return Widget
	 */
	
	
	public Widget tree(){
		
		getAllVectoren();
	
		Baugruppe baugruppe = new Baugruppe();
		 
		for (int i = 0; i<allEz.size(); i++){
			TreeItem root = new TreeItem();
			root.setText(allEz.elementAt(i).getName());
			root.setUserObject(allEz.elementAt(i));
			int id = allEz.elementAt(i).getBaugruppe();
			for(int j = 0; j<allBg.size(); j++){
				if(id == allBg.elementAt(j).getId()){
					baugruppe = allBg.elementAt(j);
					int anzahl= 1;
					root.addItem(treerek(baugruppe, anzahl));
				}
			}
			
			t.addItem(root);
		}
		
    	t.addSelectionHandler(new SelectionHandler<TreeItem>(){
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
					TreeItem selectedItem = event.getSelectedItem();
					Object result = selectedItem.getUserObject();
					
					if (result instanceof Baugruppe){
						Baugruppe bg = new Baugruppe();
						bg = (Baugruppe) result;
						Vector<Baugruppe> bguebergabe = new Vector<Baugruppe>();
						bguebergabe.add(bg);
						ContentContainer.getInstance().setContent(new BaugruppeGUI().showAllBaugruppen(bguebergabe));
					}
					else if (result instanceof Bauteil){
						Bauteil bt = new Bauteil();
						bt = (Bauteil) result;
						Vector<Bauteil> btuebergabe = new Vector<Bauteil>();
						btuebergabe.add(bt);
						ContentContainer.getInstance().setContent(new BauteilGUI().showAllBauteile(btuebergabe));
					}
						else if(result instanceof Enderzeugnis){
							Enderzeugnis ez = new Enderzeugnis();
							ez = (Enderzeugnis) result;
							Vector<Enderzeugnis> ezuebergabe = new Vector<Enderzeugnis>();
							ezuebergabe.add(ez);
							ContentContainer.getInstance().setContent(new EnderzeugnisGUI().showAllEnderzeugnisse(ezuebergabe));
						}
				}
    	});
    	return t;
	}
	
}
