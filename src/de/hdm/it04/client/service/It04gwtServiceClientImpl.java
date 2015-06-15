package de.hdm.it04.client.service;




import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;










import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.shared.Bauteil;

public class It04gwtServiceClientImpl implements It04gwtServiceClientInt{
	private It04gwtServiceAsync service;
	private MainGUI maingui;
	
	public It04gwtServiceClientImpl (String url) {  //Konstruktor //url location of  seervlet
		System.out.println(url);
		this.service = GWT.create(It04gwtService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}
	
	
	//auf diese Methoden greift das gui zu. ClientImpl gibt das weiter an Service und Async

	//Hier bekommt man ein Bauteil mit einer bestimmten ID zurück
	public void getBauteil(int id){
		this.service.getBauteil(id, new GetBauteilCallback());
	}
	
	public void findByName(String name){
		this.service.findByName(name, new GetBauteilCallback());
	}
	
	public void updateBauteil(Bauteil bt){
		this.service.updateBauteil(bt, new UpdateBauteilCallback());
	}
	
	
	//Hier bekommt man alle erzeugten Bauteile als Vektor zurück
	public void getAll(){
		this.service.getAll(new GetAllCallback());
	}
	
	public void delete(int id){
		this.service.delete(id, new DeleteBauteilCallback());
	}
	
	
	//Hier kann man ein Bauteil erzeugen
	public void create(Bauteil bt){
		this.service.create(bt, new CreateBauteilCallback());
	}
	
	
	public MainGUI getMainGUI() {
		return this.maingui;
	}
	
	
	
	
	
	
	
	
		//Klasse Callback
		public class GetBauteilCallback implements AsyncCallback<Vector<Bauteil>> {
				
		//Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt 
			@Override
			public void onFailure(Throwable caught){
				//maingui.GetAllError();	
			}

			@Override
			public void onSuccess(Vector<Bauteil> result) {
				
				
				
				//Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
				System.out.println("R�ckmeldung vom Server erhalten");
				
						if(result instanceof Vector) {
							
							Vector<Bauteil> bauteile = new Vector<Bauteil>();
							bauteile = (Vector<Bauteil>) result;
							
							maingui.showBauteil(bauteile);
							
				}			
			}	
			}
			
			
			private class CreateBauteilCallback implements AsyncCallback {

				@Override
				public void onFailure(Throwable caught) {
					maingui.showError();
					
				}

				@Override
				public void onSuccess(Object result) {     ///Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
					System.out.println("R�ckmeldung vom Server erhalten");
					if(result instanceof Bauteil) {
						Vector<Bauteil> bauteile = new Vector<Bauteil>();
						Bauteil bt = (Bauteil) result;
						bauteile.add(bt);
						
						maingui.showSucess();
						maingui.showBauteil(bauteile);
					}
					
					else {
						
						maingui.showError();
					}
					
				}
			}
				
			private class DeleteBauteilCallback implements AsyncCallback {

				@Override
				public void onFailure(Throwable caught) {
					maingui.showError();
					
				}

				@Override
				public void onSuccess(Object result) {     ///Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
					System.out.println("R�ckmeldung vom Server erhalten");
					if(result instanceof String) {
						
						String delete = result.toString();
						
						
						maingui.showMeldung(delete);
						
					}
					
					else {
						
						maingui.showError();
					}
					
				}
			}	
			
			
			private class GetAllCallback implements AsyncCallback<Vector<Bauteil>> {

			@Override
			public void onFailure(Throwable caught){
				//maingui.GetAllError();	
			}

			@Override
			public void onSuccess(Vector<Bauteil> result) {
				
				
				
				//Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
				System.out.println("R�ckmeldung vom Server erhalten");
				
						if(result instanceof Vector) {
							
							Vector<Bauteil> bauteile = new Vector<Bauteil>();
							bauteile = (Vector<Bauteil>) result;
							
							maingui.showAllBauteile(bauteile);
							
				}			
			}	
		}
			
			
			private class UpdateBauteilCallback implements AsyncCallback {

				@Override
				public void onFailure(Throwable caught) {
					maingui.showError();
					
				}

				@Override
				public void onSuccess(Object result) {     ///Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
					System.out.println("R�ckmeldung vom Server erhalten");
					if(result instanceof Bauteil) {
						
						Vector<Bauteil> bauteile = new Vector<Bauteil>();
						Bauteil bt = (Bauteil) result;
						bauteile.add(bt);
						
						maingui.showSucess();
						maingui.showBauteil(bauteile);
					}
					
					else {
						
						maingui.showError();
					}
					
				}
			}
}



	




	






	
	


	



