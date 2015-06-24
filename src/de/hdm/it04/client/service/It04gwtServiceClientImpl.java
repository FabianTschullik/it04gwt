package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.hdm.it04.client.editor.Details;
import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.client.gui.MainGUIEditor;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Element;

/**
 * Klasse wird benötigt, da diese alle Methoden auf Serverseite enthält. Auf
 * diese Methoden greift die GUI zu.
 */
public class It04gwtServiceClientImpl implements It04gwtServiceClientInt {

	private It04gwtServiceAsync service;
	private MainGUI maingui;

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

				//maingui.showBauteil(bauteile);
			}
		}
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode createBauteil(). Wenn
	 * sie eine Antwort erhält, war die Kommunikation mit der Datenbank
	 * erfolgreich und kann weiter verarbeitet werden.
	 */
	private class CreateBauteilCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			//maingui.showError();
		}

		@Override
		public void onSuccess(Object result) { // Object result ent�hlt, was
												// vom server zur�ck kommt
												// clientImpl updatet das GUI
												// anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");
			if (result instanceof Bauteil) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				Bauteil bt = (Bauteil) result;
				bauteile.add(bt);

				// maingui.showSucess();
				//maingui.showBauteil(bauteile);
			}
			else {
				//maingui.showError();
			}
		}
	}
	
	
	private class FindConnectedBauteileByKeyCallback implements AsyncCallback<Vector<Element>>{

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector<Element> result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Vector) {
				Vector<Element> bauteile = new Vector<Element>();
				bauteile = (Vector<Element>) result;

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

				//maingui.showAllBauteile(bauteile);
			}
		}
	}

	/**
	 * Diese Klasse wartet auf eine Antwort der Methode updateBauteil(). Wenn
	 * sie eine Antwort erhält, war die Kommunikation mit der Datenbank
	 * erfolgreich und kann weiter verarbeitet werden.
	 */
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

				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				Bauteil bt = (Bauteil) result;
				bauteile.add(bt);

				// maingui.showSucess();
				//maingui.showBauteil(bauteile);
			}
			else {
				//maingui.showError();
			}
		}
	}
}
