package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.hdm.it04.client.editor.Details;
import de.hdm.it04.client.gui.AnlegenBauteil;
import de.hdm.it04.client.gui.BauteilGUI;
import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.client.gui.MainGUIEditor;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;

/**
 * Klasse wird benötigt, da diese alle Methoden auf Serverseite enthält. Auf
 * diese Methoden greift die GUI zu.
 */
public class It04gwtServiceClientImpl implements It04gwtServiceClientInt {

	private It04gwtServiceAsync service;
	private MainGUI maingui;
	private BauteilGUI bauteilgui;
	

	/**
	 * Konstruktor vom Servlet
	 * 
	 * @param URL als String
	 */
	public It04gwtServiceClientImpl(String url) {
		System.out.println(url);
		this.service = GWT.create(It04gwtService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);

		this.maingui = new MainGUI(this);
		this.bauteilgui = new BauteilGUI(this.maingui.getvPanelDetailsContent());
	}

	/**
	 * Diese Methode wird benötigt, um die MainGUI anzuzeigen.
	 * 
	 * @param void
	 * @return MainGUI
	 */
	public MainGUI getMainGUI() {
		return this.maingui;
	}

	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden.
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	public void getBauteil(int id) {
		this.service.getBauteil(id, new GetBauteilCallback());
	}
	
	public void findConnectedBaugruppe(int id){
		this.service.findConnectedBaugruppe(id, new FindConnectedBaugruppeCallback());
	}
	
	@Override
	public void update(Baugruppe bg, Bauteil bt) {
		this.service.update(bg, bt, new UpdateCallback());
		
	}
	
	public void findConnectedBauteileByKey(int id){
		this.service.findConnectedBauteileByKey(id, new FindConnectedBauteileByKeyCallback());
	}

	/**
	 * Die Methode wird benötigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden.
	 * 
	 * @param Ein Name eines Bauteils als String, welches gefunden werden soll
	 * @return void
	 */
	public void findByName(String name) {
		this.service.findByName(name, new GetBauteilCallback());
	}

	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein Objekt vom Typ Bauteil
	 * @return void
	 */
	public void updateBauteil(Bauteil bt) {
		this.service.updateBauteil(bt, new UpdateBauteilCallback());
	}

	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return void
	 */
	public void getAll() {
		this.service.getAll(new GetAllCallback());
	}

	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID von einem Bauteil als Integer,
	 * @return void
	 */
	public void delete(int id) {
		this.service.delete(id, new DeleteBauteilCallback());
	}
	
	public void getBauteilDetails(int id) {
		this.service.getBauteilDetails(id, new GetBauteilDetailsCallback());
		
	}
	
	public void getBaugruppeDetails(int id) {
		this.service.getBaugruppeDetails(id, new GetBaugruppeDetailsCallback());
		
	}

	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return void
	 */
	public void createBauteil() {
		this.service.createBauteil(new CreateBauteilCallback());
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode getBauteil(). Wenn sie
	 * eine Antwort erhält, war die Kommunikation mit der Datenbank erfolgreich
	 * und kann weiter verarbeitet werden.
	 */
	public class GetBauteilCallback implements AsyncCallback<Vector<Bauteil>> {

		// Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt
		@Override
		public void onFailure(Throwable caught) {
			//maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector<Bauteil> result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Vector) {

				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				Bauteil bt = new Bauteil();
				bt = bauteile.firstElement();
				
				
				for (int i=1; i < 3; i++){	
				maingui.showBauteil(bt);}
			}
		}
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode createBauteil(). Wenn
	 * sie eine Antwort erhält, war die Kommunikation mit der Datenbank
	 * erfolgreich und kann weiter verarbeitet werden.
	 */
	public class CreateBauteilCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
				
		}

		@Override
		public void onSuccess(Object result) { 
			
			System.out.println("Rückmeldung vom Server erhalten");
			if (result instanceof Bauteil) {
				
				
				Bauteil bt = (Bauteil) result;
				//maingui.openAnlegenBauteil(bt);
				//maingui.updateBauteil(bt);
				
				bauteilgui.updateBauteil(bt);
			}
			else {
				
			}
		}
	}
	
	
	private class FindConnectedBauteileByKeyCallback implements AsyncCallback<Vector<Bauteil>>{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Vector<Bauteil> result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				//maingui.error();
				maingui.showConnectedBauteil(bauteile);
			}
		}
}	


	/**
	 * Diese Klasse wartet auf eine Antwort der Methode deleteBauteil(). Wenn
	 * sie eine Antwort erhält, war die Kommunikation mit der Datenbank
	 * erfolgreich und kann weiter verarbeitet werden.
	 */
	private class DeleteBauteilCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			//maingui.showError();
		}

		@Override
		public void onSuccess(Object result) { 
			
			System.out.println("Rückmeldung vom Server erhalten");
			
			if (result instanceof String) {
				// String delete = result.toString();
				// maingui.showMeldung(delete);
			}
			else {
				//maingui.showError();
			}
		}
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode getAll(). Wenn sie eine
	 * Antwort erhält, war die Kommunikation mit der Datenbank erfolgreich und
	 * kann weiter verarbeitet werden.
	 */
	private class GetAllCallback implements AsyncCallback<Vector<Bauteil>> {

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector<Bauteil> result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;

				maingui.showAllBauteile(bauteile);
			}
		}
	}
	
	private class GetBauteilDetailsCallback implements AsyncCallback<Bauteil> {

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Bauteil result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Bauteil) {
				Bauteil bt = new Bauteil();
				bt = result;

				//maingui.showBauteilDetails(bt);
			}
		}
	}
	
	private class GetBaugruppeDetailsCallback implements AsyncCallback<Baugruppe> {

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Baugruppe result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Baugruppe) {
				Baugruppe bg = new Baugruppe();
				bg = result;

				maingui.showBaugruppeDetails(bg);
			}
		}
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode updateBauteil(). Wenn
	 * sie eine Antwort erhält, war die Kommunikation mit der Datenbank
	 * erfolgreich und kann weiter verarbeitet werden.
	 */
	
	private class FindConnectedBaugruppeCallback implements AsyncCallback{
		

		@Override
		public void onFailure(Throwable caught) {
			//maingui.showError();

		}

		@Override
		public void onSuccess(Object result) { // /Object result ent�hlt, was
												// vom server zur�ck kommt
												// clientImpl updatet das GUI
												// anschlie�end
			System.out.println("Rueckmeldung vom Server erhalten");
			if (result instanceof Baugruppe) {

				Baugruppe bg = new Baugruppe();
				
				bg = (Baugruppe) result;
				
				//maingui.showConnectedBaugruppe(bg);
				//maingui.updateTree(bg);

				// maingui.showSucess();
				//maingui.showBauteil(bauteile);
			}
			else {
				//maingui.showError();
			}
		}
	}
		
		
	private class UpdateBauteilCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			//maingui.showError();

		}

		@Override
		public void onSuccess(Object result) { // /Object result ent�hlt, was
												// vom server zur�ck kommt
												// clientImpl updatet das GUI
												// anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");
			if (result instanceof Bauteil) {

				Bauteil bt = (Bauteil) result;
				//maingui.showBauteilDetails(bt);
			}
			else {
				//maingui.showError();
			}
		}
	}
	
	
	
	
	private class UpdateCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			//maingui.showError();

		}

		@Override
		public void onSuccess(Object result) { // /Object result ent�hlt, was
												// vom server zur�ck kommt
												// clientImpl updatet das GUI
												// anschlie�end
			System.out.println("Rückmeldung vom Server erhalten");
			if (result instanceof Baugruppe) {

				Baugruppe bg = (Baugruppe) result;
				//maingui.updateTree(bg);

				 //maingui.showSucess();
				//maingui.showBauteil(bauteile);
			
			}
			else {
				//maingui.showError();
			}
		}
	}


	
}
