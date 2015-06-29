package de.hdm.it04.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.it04.*;


//It04gwtServiceAsync


public interface AdministrationCommonAsync {
	
	public void init(AsyncCallback<Void> callback);
	
	public void getBauteil(int id, AsyncCallback<Vector<Bauteil>> callback);
	
	public void createBauteil(AsyncCallback callback);
	
	public void createEnderzeugnis(AsyncCallback callback);
	
	public void getEnderzeugnisById(int id, AsyncCallback<Enderzeugnis> callback );
	
	public void findConnectedBaugruppe(int id, AsyncCallback callback);
	
	public void findConnectedBauteileByKey(int id, AsyncCallback<Vector<Bauteil>> callback);
	
	public void delete(int id, AsyncCallback callback);
	
	public void getAll(AsyncCallback<Vector<Bauteil>> callback);
	
	public void findByName(String name, AsyncCallback<Vector<Bauteil>> callback);
	
	public void updateBauteil(Bauteil bt, AsyncCallback callback);
	
	public void updateEnderzeugnis(Enderzeugnis ez, AsyncCallback<Enderzeugnis> callback);
	
	public void getBauteilDetails(int id, AsyncCallback<Bauteil> callback);
	
	public void getBaugruppeDetails(int id, AsyncCallback<Baugruppe> callback);
	
	public void update (Baugruppe bg, Bauteil bt, AsyncCallback callback);

}
