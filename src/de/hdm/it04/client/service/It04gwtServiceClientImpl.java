package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.hdm.it04.client.gui.BauteilGUI;
import de.hdm.it04.client.gui.EnderzeugnisGUI;
import de.hdm.it04.client.gui.MainGUI;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * Klasse wird benötigt, da diese alle Methoden auf Serverseite enthält. Auf
 * diese Methoden greift die GUI zu.
 */
public class It04gwtServiceClientImpl implements It04gwtServiceClientInt {

	private It04gwtServiceAsync service;
	private MainGUI maingui;
	private BauteilGUI bauteilgui;
	private EnderzeugnisGUI enderzeugnisgui;


	

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
		this.enderzeugnisgui = new EnderzeugnisGUI(this);
		

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
	
	/*----------------------------------Bauteil Methoden----------------------------------*/

	/**
	 * Diese Methode wird ben�tigt um ein leeres Objekt aus der DB zu holen
	 */
	public void createBauteil() {
		this.service.createBauteil(new CreateBauteilCallback());
	}
	/**
	 * Diese Methode wird benoetigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden und anzeigen zu lassen
	 * 
	 * @param ID als Integer
	 * @return void
	 */
	public void getBauteil(int id) {
		this.service.getBauteil(id, new GetBauteilCallback());
	}
	
	/**
	 * Diese Methode wird benoetigt, um ein Bauteil mit einer bestimmten ID zu finden
	 * und anzeigen zulassen. Hier werden bestimmte Daten in die vorgesehenen 
	 * TextBoxen auf der GUI gesetzt
	 * @param ID als Integer
	 * @return void
	 */
	public void getBauteil2(int id){
		this.service.getBauteil(id, new GetBauteil2Callback());
	}
	
	
	
	/**
	 * Die Methode wird benoetigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden.
	 * @param Ein Name eines Bauteils als String, welches gefunden werden soll
	 * @return void
	 */
	public void findBauteilByName(String name) {
		this.service.findBauteilByName(name, new GetBauteilByNameCallback());
	}
	
	/**
	 * Diese Methode wird ben�tigt um ein Bauteil zu aktualisieren
	 * @param Bauteil, welches ge�ndert wird
	 * @return void
	 */
	
	public void updateBauteil(Bauteil bt) {
		this.service.updateBauteil(bt, new UpdateCallback());
	}
	
	/**
	 * Diese Methode wird ben�tigt um ein Bauteil zu l�schen
	 * @param id 
	 * @return void
	 */
	
	public void deleteBauteil(int id){
		this.service.deleteBauteil(id, new DeleteBauteilCallback());
	}
	
	/**
	 * Diese Methode wird ben�tigt um alle Bauteile anzeigen zu lassen
	 */
	
	public void showAllBauteile() {
		this.service.showAllBauteile(new GetAllBauteileCallback());
	}
	
	
	
	
	
	/**nicht verwendete Methode*/
	public void findConnectedBauteileByKey(int id){
		this.service.findConnectedBauteileByKey(id, new FindConnectedBauteileByKeyCallback());
	}

	
	/*-------------------------------------------Baugruppen Methoden--------------------------*/
	
	public void findConnectedBaugruppe(int id){
		this.service.findConnectedBaugruppe(id, new FindConnectedBaugruppeCallback());
	}
	
	@Override
	public void update(Baugruppe bg, Bauteil bt) {
		this.service.update(bg, bt, new UpdateCallback());
	}
	
	public void getBaugruppeDetails(int id) {
		this.service.getBaugruppeDetails(id, new GetBaugruppeDetailsCallback());
		
	}

	
	/*-------------------------------------------Enderzeugnis Methoden--------------------------*/
	
	public void updateEnderzeugnis(Enderzeugnis ez) {
		this.service.updateEnderzeugnis(ez, new UpdateEnderzeugnisCallback());
	}
	
	public void getEnderzeugnisById(int id) {
		this.service.getEnderzeugnisById(id, new getEnderzeugnisByIdCallback());
	}
	
	/**
	 * Die Methode legt ein Enderzeugnis an.
	 */
	
	
	public void createEnderzeugnis() {
		this.service.createEnderzeugnis(new CreateEnderzeugnisCallback());
	}

	
/*--------------------------------Nicht verwendete Methoden/ nicht zuordbar------------*/

	
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
	

	

	
	
	
	
/*------------------------------------------Callbacks--------------------------------*/
	
	/*------------------------------------------Bauteil Callbacks--------------------------------*/
	/**
	 * Diese Klasse wartet auf eine Antwort der Methode getBauteil(). Wenn sie
	 * eine Antwort erhält, war die Kommunikation mit der Datenbank erfolgreich
	 * und kann weiter verarbeitet werden.
	 */
	public class GetBauteilCallback implements AsyncCallback {

		// Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt
		@Override
		public void onFailure(Throwable caught) {
			//maingui.GetAllError();
		}

		@Override
		public void onSuccess(Object result) {

			

			if (result instanceof Bauteil) {
				Bauteil bt = (Bauteil) result;
				bauteilgui.getBauteil(bt);
				
			}
		
		}
	}
	public class GetBauteil2Callback implements AsyncCallback {

		// Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt
		@Override
		public void onFailure(Throwable caught) {
			//maingui.GetAllError();
		}

		@Override
		public void onSuccess(Object result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Bauteil) {
				Bauteil bt = (Bauteil) result;
				bauteilgui.updateBauteil2(bt);
				

		
			}
		}
	}

	public class GetBauteilByNameCallback implements AsyncCallback<Vector<Bauteil>> {

		// Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt
		@Override
		public void onFailure(Throwable caught) {
			//maingui.GetAllError();
		}
		public void onSuccess(Vector<Bauteil> result) {

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				
				bauteilgui.showAllBauteile(bauteile);
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
				 String delete = result.toString();
				bauteilgui.showMeldung(delete);
			}
			else {
				//maingui.showError();
			}
		}
	}
	
	private class GetAllBauteileCallback implements AsyncCallback<Vector<Bauteil>> {

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector<Bauteil> result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;

				bauteilgui.showAllBauteile(bauteile);
			}
		}
	}
	

	
	/*------------------------------------------Enderzeugnis Callbacks--------------------------------*/
	
	public class CreateEnderzeugnisCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			
			Enderzeugnis ez = new Enderzeugnis();
			
				
		}

		@Override
		public void onSuccess(Object result) { 
			if (result instanceof Enderzeugnis) {
				
				
				Enderzeugnis ez = (Enderzeugnis) result;
				
				enderzeugnisgui.showInsertForm(ez);
			}
			else {
				
				Enderzeugnis ez = new Enderzeugnis();
				
				
			}
		}
	}
	
	public class getEnderzeugnisByIdCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Object result) { 
			if (result instanceof Enderzeugnis) {
				
				Enderzeugnis ez = (Enderzeugnis) result;
				enderzeugnisgui.showEnderzeugnis(ez);
				
			}
			else {
				
				
				
			}
		}
	}
	
	/*------------------------------------------Baugruppe Callbacks--------------------------------*/
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

				//maingui.showBaugruppeDetails(bg);
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
	
	private class UpdateEnderzeugnisCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			enderzeugnisgui.showSuccess();
			
		}

		@Override
		public void onSuccess(Object result) {
			
			enderzeugnisgui.showSuccess();
		
			
		}
	}
	
	
			
		
	
	/*------------------------------------------gemeinsam Verwendbare Callbacks--------------------------------*/
	
	
	
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
			
			if (result instanceof Bauteil){
				Bauteil bt = (Bauteil) result;
				bauteilgui.getBauteil(bt);
			}
			else {
				//maingui.showError();
			}
		}
	}
}


	

