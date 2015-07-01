package de.hdm.it04.client.service;

import java.util.Vector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import de.hdm.it04.client.gui.AlertGUI;
import de.hdm.it04.client.gui.BaugruppeGUI;
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
	private AlertGUI alertgui;
	private BaugruppeGUI baugruppegui;


	

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
		this.enderzeugnisgui = new EnderzeugnisGUI(this.maingui.getvPanelDetailsContent());
		this.alertgui = new AlertGUI(this.maingui.getvPanelDetailsContent());
		this.baugruppegui = new BaugruppeGUI(this.maingui.getvPanelDetailsContent());
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
	
//------------------------------------------------------------------------------------
//----------------------------------Bauteil Methoden----------------------------------
//------------------------------------------------------------------------------------
	/**
	 * Diese Methode wird ben�tigt um ein leeres Objekt aus der DB zu holen
	 */
	public void createBauteil() {
		this.service.createBauteil(new CreateBauteilCallback());
	}
	
	/**
	 * Diese Methode wird ben�tigt um ein Bauteil zu aktualisieren
	 * @param Bauteil, welches ge�ndert wird
	 * @return void
	 */	
	public void updateBauteil(Bauteil bt) {
		this.service.updateBauteil(bt, new GetBauteilCallback());
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
	public void getBauteilForUpdate(int id){
		this.service.getBauteil(id, new GetBauteilForUpdateCallback());
	}
	
	/**
	 * Die Methode wird benoetigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden.
	 * @param Ein Name eines Bauteils als String, welches gefunden werden soll
	 * @return void
	 */
	public void getBauteil(String name) {
		this.service.getBauteil(name, new GetBauteilCallback());
	}
	
	/**
	 * Die Methode wird benoetigt, alle angelegten Bauteile zu finden.
	 * @param void
	 * @return void
	 */
	public void getAll() {
		this.service.getAll(new GetBauteilCallback());
	}
	
	/**
	 * Diese Methode wird benötigt um ein Bauteil zu löschen
	 * @param id 
	 * @return void
	 */
	public void deleteBauteil(int id){
		this.service.deleteBauteil(id, new DeleteBauteilCallback());
	}
//------------------------------------------------------------------------------------
//----------------------------------Ende Bauteil----------------------------------
//------------------------------------------------------------------------------------
	

//------------------------------------------------------------------------------------
//----------------------------------Baugruppe----------------------------------
//------------------------------------------------------------------------------------
	
	public void createBaugruppe(){
		this.service.createBaugruppe(new CreateBaugruppeCallback());
	}
	
	public void updateBaugruppe(Baugruppe bg){
		this.service.updateBaugruppe(bg, new UpdateBaugruppeCallback());
	}
	
	public void deleteBaugruppe(int id){
		this.service.deleteBaugruppe(id, new DeleteBaugruppeCallback());
	}
	
	
	public void getBaugruppe(int id) {
		this.service.getBaugruppe(id, new GetBaugruppeCallback());
		
	}
	public void getBaugruppe(String name){
		this.service.getBaugruppe(name, new GetBaugruppeCallback());
	}
	
	public void getBaugruppeForUpdate(int id){
		this.service.getBaugruppe(id, new GetBaugruppeForUpdateCallback());
	}
	
	public void getAllBaugruppen(){
		this.service.getAllBaugruppen(new GetAllBaugruppenCallback());	
	}

	
	
	
	
//------------------------------------------------------------------------------------
//----------------------------------Ende Baugruppe----------------------------------
//------------------------------------------------------------------------------------
	

	
	
//------------------------------------------------------------------------------------
//----------------------------------Enderzeugnis----------------------------------
//------------------------------------------------------------------------------------
	public void updateEnderzeugnis(Enderzeugnis ez) {
		this.service.updateEnderzeugnis(ez, new UpdateEnderzeugnisCallback());
	}
	
	public void getEnderzeugnis(int id) {
		this.service.getEnderzeugnis(id, new getEnderzeugnisCallback());
	}
	/*
	public void getEnderzeugnis(String name) {
		this.service.getEnderzeugnis(name, new GetEnderzeugnisCallback());
	}
	*/
	
	public void getEnderzeugnisForUpdate(int id) {
		this.service.getEnderzeugnis(id, new GetEnderzeugnisForUpdateCallback());
	}
	
	public void getAllEnderzeugnisse(){
		this.service.getAllEnderzeugnisse(new GetAllEnderzeugnisseCallback());
	}
	
	
	public void createEnderzeugnis() {
		this.service.createEnderzeugnis(new CreateEnderzeugnisCallback());
	}
	
	public void deleteEnderzeugnis(int id){
		this.service.deleteEnderzeugnis(id, new DeleteEnderzeugnisCallback());
	}
//------------------------------------------------------------------------------------
//----------------------------------Ende Enderzeugnis----------------------------------
//------------------------------------------------------------------------------------

	
	

	
	

	

	
	
	
/*-----------------------------------------------------------------------------------*/
/*------------------------------------------Callbacks--------------------------------*/
/*-----------------------------------------------------------------------------------*/

/*-------------------------------------------------------------------------------------------*/
/*------------------------------------------Bauteil Callbacks--------------------------------*/
/*-------------------------------------------------------------------------------------------*/
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
		public void onSuccess(Vector result) {

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				
				bauteilgui.getBauteil(bauteile);				
			}
			
			
		}
	}
	
	public class GetBauteilForUpdateCallback implements AsyncCallback<Vector<Bauteil>> {

		// Fehlermeldung ausgeben, wenn keine RÜckmeldung kommt
		@Override
		public void onFailure(Throwable caught) {
			//maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector result) {

			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschlie�end
			System.out.println("R�ckmeldung vom Server erhalten");

			if (result instanceof Vector) {
				Vector<Bauteil> bauteile = new Vector<Bauteil>();
				bauteile = (Vector<Bauteil>) result;
				Bauteil bt = new Bauteil();
				bt = bauteile.elementAt(0);
				bauteilgui.updateBauteil(bt);
				
				
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

				bauteilgui.showAllBauteile(bauteile);
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
			bauteilgui.menue();
		}

		@Override
		public void onSuccess(Object result) { 
			
			System.out.println("Rückmeldung vom Server erhalten");
			
			if (result instanceof String) {
				String meldung = (String) result;
				 bauteilgui.showMeldung(meldung);
			
			}
			else {
				bauteilgui.menue();
			}
		}
	}
/*-------------------------------------------------------------------------------------------*/
/*------------------------------------------Ende Bauteil Callbacks--------------------------------*/
/*-------------------------------------------------------------------------------------------*/	

	
	

	

	
/*-------------------------------------------------------------------------------------------*/
/*------------------------------------------Baugruppe Callbacks--------------------------------*/
/*-------------------------------------------------------------------------------------------*/	
	private class GetBaugruppeForUpdateCallback implements AsyncCallback<Vector<Baugruppe>>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Vector<Baugruppe> result) {
		// TODO Auto-generated method stub
		
	}
		
   }
	
		
	private class GetBaugruppeCallback implements AsyncCallback<Vector<Baugruppe>>{

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(Vector<Baugruppe> result) {
		
		Baugruppe bg = result.firstElement();
		
		
		
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
	
	
	private class CreateBaugruppeCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Object result) { 
			if (result instanceof Baugruppe) {
				
				Baugruppe bg = (Baugruppe) result;
				
				baugruppegui.showAnlegenForm(bg);			
			}
		else {
			//maingui.showError();
		}				
	}
}	


	
	private class UpdateBaugruppeCallback implements AsyncCallback<Vector<Baugruppe>>{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Vector<Baugruppe> result) {
			
			//Da nur ein Enderzeugnis geupdatet wird, kann sich nur ein
			//EZ im Vektor befinden. Das Element wird gespeichert.
			Baugruppe bg = result.firstElement();
			
			baugruppegui.showBaugruppeForm(bg);
	}		
	}
	
	private class GetAllBaugruppenCallback implements AsyncCallback<Vector<Baugruppe>> {

		@Override
		public void onFailure(Throwable caught) {
			// maingui.GetAllError();
		}

		@Override
		public void onSuccess(Vector<Baugruppe> result) {
			
			// Object result entählt, was vom server zurück kommt clientImpl
			// updatet das GUI anschließend
			Vector<Baugruppe> baugruppe = new Vector<Baugruppe>();
			baugruppe = (Vector<Baugruppe>) result;

			baugruppegui.showAllBaugruppen(baugruppe);
					
		
	}
		}
	
	

	private class DeleteBaugruppeCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Object result) {

			System.out.println("Rueckmeldung vom Server erhalten");
			if (result instanceof String){
				
			String meldung = (String) result;
			baugruppegui.menue();
			alertgui.load(meldung, "green");
		}
		else {
			//maingui.showError();
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
	
	
	
	
	
/*-------------------------------------------------------------------------------------------*/
/*------------------------------------------Ende Baugruppe Callbacks--------------------------------*/
/*-------------------------------------------------------------------------------------------*/		
	
	
			
	
	
	
	
	
	
	
	
	
	
	
/*-------------------------------------------------------------------------------------------*/
/*------------------------------------------Enderzeugnis Callbacks--------------------------------*/
/*-------------------------------------------------------------------------------------------*/		
	
	private class DeleteEnderzeugnisCallback implements AsyncCallback{

	@Override
	public void onFailure(Throwable caught) {
		
	}

	@Override
	public void onSuccess(Object result) {
		
		enderzeugnisgui.menue();
		//alertgui.load(text, color);

	}
		
	}
	
	
	
	private class GetEnderzeugnisForUpdateCallback implements AsyncCallback<Vector<Enderzeugnis>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Enderzeugnis> result) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class GetAllEnderzeugnisseCallback implements AsyncCallback<Vector<Enderzeugnis>>{

		@Override
		public void onFailure(Throwable caught) {
			alertgui.load("Fehler", "red");
			
		}

		@Override
		public void onSuccess(Vector<Enderzeugnis> result) {
			
				// Object result entählt, was vom server zurück kommt clientImpl
				// updatet das GUI anschließend
				Vector<Enderzeugnis> enderzeugnisse = new Vector<Enderzeugnis>();
				enderzeugnisse = (Vector<Enderzeugnis>) result;

				enderzeugnisgui.showAllEnderzeugnisse(enderzeugnisse);
						
			
		}
		
	}
	
	
	private class CreateEnderzeugnisCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Object result) { 
			if (result instanceof Enderzeugnis) {
				
				Enderzeugnis ez = (Enderzeugnis) result;
				
				enderzeugnisgui.showAnlegenForm(ez);			
			}
		}
	}
	
	public class getEnderzeugnisCallback implements AsyncCallback<Vector<Enderzeugnis>> {

		@Override
		public void onFailure(Throwable caught) {
			alertgui.load("Fehler", "red");
		}

		@Override
		public void onSuccess(Vector<Enderzeugnis> result) {
			
			//Da nur ein Enderzeugnis geupdatet wird, kann sich nur ein
			//EZ im Vektor befinden. Das Element wird gespeichert.
			Enderzeugnis ez = result.firstElement();
		
			enderzeugnisgui.showEnderzeugnisForm(ez);
		}
	}
	
	
	
	
	private class UpdateEnderzeugnisCallback implements AsyncCallback<Vector<Enderzeugnis>> {

		@Override
		public void onFailure(Throwable caught) {
		
		}

		@Override
		public void onSuccess(Vector<Enderzeugnis> result) {
			
				//Da nur ein Enderzeugnis geupdatet wird, kann sich nur ein
				//EZ im Vektor befinden. Das Element wird gespeichert.
				Enderzeugnis ez = result.firstElement();
				
				enderzeugnisgui.showEnderzeugnisForm(ez);
		}
	}




	
}


	

