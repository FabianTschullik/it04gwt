package de.hdm.it04.client.service;



import java.sql.Timestamp;

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


	
	
	
	public void getBauteil(int id){
		
		this.service.getBauteil(id, new DefaultCallback());
		
		
	}
	
	
	
	
	public void create(String name, String beschreibung, String materialBezeichnung, Long erstellungsZeit){
		
		this.service.create(name, beschreibung, materialBezeichnung, erstellungsZeit, new DefaultCallback2());
	}
	
	
	public MainGUI getMainGUI() {
		
		return this.maingui;
	}
	
	
	//wenn was vom server zur�ck kommt, dann:
	
	private class DefaultCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			maingui.showError();
			
		}

		@Override
		public void onSuccess(Object result) {     ///Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");
			if(result instanceof Bauteil) {
				Bauteil bt = (Bauteil) result;
				
				maingui.showBauteil(bt);
			}
			
			else {
				
				maingui.showError();
			}
			
		}
		
		
		
	}
	
	
	

	
	
	private class DefaultCallback2 implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			maingui.showError();
			
		}

		@Override
		public void onSuccess(Object result) {     ///Object result ent�hlt, was vom server zur�ck kommt  clientImpl updatet das GUI anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");
			if(result instanceof Bauteil) {
				Bauteil bt = (Bauteil) result;
				
				maingui.showSucess();
				maingui.showBauteil(bt);
			}
			
			else {
				
				maingui.showError();
			}
			
		}
		
		
		
	}
	
	
	
	
		
		
		
		
	}



	




	






	
	


	



