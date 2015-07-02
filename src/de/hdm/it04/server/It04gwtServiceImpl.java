package de.hdm.it04.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.it04.client.service.It04gwtService;
import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.BauteilMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Bauteil;
import de.hdm.it04.shared.Enderzeugnis;
import de.hdm.it04.shared.LoginInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.net.URLConnection;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import java.util.Vector;

/**
 * Diese Klasse wird benötigt, um die Kommunikation zwischen der Datenbank und
 * dem Client zu ermöglichen. Hierbei werden die Methoden der Mapper-Klasse
 * aufgerufen.
 */
public class It04gwtServiceImpl extends RemoteServiceServlet implements
		It04gwtService {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(It04gwtServiceImpl.class
			.getCanonicalName());

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	// TODO #11: implement login helper methods in service implementation

	@Override
	public String getUserEmail(final String token) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		if (null != user) {
			return user.getEmail();
		} else {
			return "noreply@sample.com";
		}
	}

	@Override
	public LoginInfo login(final String requestUri) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public LoginInfo loginDetails(final String token) {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="
				+ token;

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				log.log(Level.SEVERE, ex.getMessage());
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					log.log(Level.SEVERE, ex.getMessage());
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

		final LoginInfo loginInfo = new LoginInfo();
		try {
			final JsonFactory f = new JsonFactory();
			JsonParser jp;
			jp = f.createJsonParser(r.toString());
			jp.nextToken();
			while (jp.nextToken() != JsonToken.END_OBJECT) {
				final String fieldname = jp.getCurrentName();
				jp.nextToken();
				if ("picture".equals(fieldname)) {
					loginInfo.setPictureUrl(jp.getText());
				} else if ("name".equals(fieldname)) {
					loginInfo.setName(jp.getText());
				} else if ("email".equals(fieldname)) {
					loginInfo.setEmailAddress(jp.getText());
				}
			}
		} catch (final JsonParseException e) {
			log.log(Level.SEVERE, e.getMessage());
		} catch (final IOException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		return loginInfo;
	}

	// TODO #11:> end

	// --------------------------------------------------------------------------
	// ------------------------- Bauteil
	// ----------------------------------------
	// --------------------------------------------------------------------------
	/**
	 * Die Methode legt ein Bauteil an.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil welches gespeichert werden soll
	 * @return Ein Objekt vom Typ Bauteil
	 */
	public Bauteil createBauteil() {

		return BauteilMapper.bauteilMapper().insert();
	}

	/**
	 * Diese Methode wird benötigt, um ein Bauteil mit einer bestimmten ID zu
	 * finden. Die Methode gibt ein Bauteil als Vektor zurück. Da die ID
	 * übergeben wird steht in diesem Vektor lediglich ein Objekt vom Typ
	 * Bauteil.
	 * 
	 * @param ID
	 *            als Integer
	 * @return Vektor mit Bauteil-Objekten
	 */
	public Vector<Bauteil> getBauteil(int id) {

		return BauteilMapper.bauteilMapper().findByKey(id);
	}

	public Vector<Bauteil> getBauteilForUpdate(int id) {

		return BauteilMapper.bauteilMapper().findByKey(id);
	}

	/**
	 * Die Methode wird benötigt, um ein Bauteil mit einem bestimmten Namen zu
	 * finden. Da mehrere Bauteile mit dem selben Namen exisitieren können, wird
	 * das Bauteil in einem Vektor gespeichert.
	 * 
	 * @param Ein
	 *            Name eines Bauteils, welches gefunden werden soll
	 * @return Vektor vom Typ Bauteil, welches alle Bauteile mit dem übergebenen
	 *         Namen enthält
	 */
	public Vector<Bauteil> getBauteil(String name) {

		return BauteilMapper.bauteilMapper().findByName(name);
	}

	/**
	 * Die Methode findet alle angelegten Bauteile und speichert diese in einem
	 * Vektor.
	 * 
	 * @param void
	 * @return Ein Vektor vom Typ Bauteil, welcher alle Bauteile enthält
	 */
	public Vector<Bauteil> getAll() {

		return BauteilMapper.bauteilMapper().findAll();
	}

	/**
	 * Die Methode aktualisiert ein Bauteil.
	 * 
	 * @param Ein
	 *            Objekt vom Typ Bauteil
	 * @return Objekt vom Typ Bauteil
	 */
	public Vector<Bauteil> updateBauteil(Bauteil bt) {

		return BauteilMapper.bauteilMapper().update(bt);
	}

	public String deleteBauteil(int id) {
		return BauteilMapper.bauteilMapper().delete(id);
	}

	// -----------------------------------------------------------------------------
	// --------------------------- Ende Bauteil
	// ------------------------------------
	// -----------------------------------------------------------------------------

	// -----------------------------------------------------------------------------
	// ----------------------------Baugruppe----------------------------------------
	// -----------------------------------------------------------------------------

	public Baugruppe createBaugruppe() {

		return BaugruppeMapper.baugruppeMapper().insert();
	}

	public Vector<Baugruppe> updateBaugruppe(Baugruppe bg) {

		return BaugruppeMapper.baugruppeMapper().updateBaugruppe(bg);
	}

	public String deleteBaugruppe(int id) {

		return BaugruppeMapper.baugruppeMapper().deleteBaugruppe(id);
	}

	public Vector<Baugruppe> getBaugruppe(int id) {

		return BaugruppeMapper.baugruppeMapper().findByKey(id);
	}

	public Vector<Baugruppe> getBaugruppe(String name) {

		return BaugruppeMapper.baugruppeMapper().findByName(name);
	}

	public Vector<Baugruppe> getAllBaugruppen() {

		return BaugruppeMapper.baugruppeMapper().findAll();
	}

	@Override
	public Vector<Baugruppe> getBaugruppeForUpdate(int id) {

		return BaugruppeMapper.baugruppeMapper().findByKey(id);
	}

	// -----------------------------------------------------------------------------
	// ----------------------------Ende
	// Baugruppe----------------------------------------
	// -----------------------------------------------------------------------------

	// -----------------------------------------------------------------------------
	// ----------------------------Enderzeugnis----------------------------------------
	// -----------------------------------------------------------------------------
	@Override
	public String deleteEnderzeugnis(int id) {
		EnderzeugnisMapper.enderzeugnisMapper().deleteEnderzeugnis(id);
		return null;
	}

	@Override
	public Vector<Enderzeugnis> getAllEnderzeugnisse() {

		return EnderzeugnisMapper.enderzeugnisMapper().findAll();
	}

	@Override
	public Vector<Enderzeugnis> getEnderzeugnisForUpdate(int id) {
		// TODO Auto-generated method stub blabla
		return null;
	}

	public Vector<Enderzeugnis> getEnderzeugnis(int id) {

		return EnderzeugnisMapper.enderzeugnisMapper().getEnderzeugnisById(id);
	}

	public Enderzeugnis createEnderzeugnis() {

		return EnderzeugnisMapper.enderzeugnisMapper().insert();
	}

	public Vector<Enderzeugnis> updateEnderzeugnis(Enderzeugnis ez) {

		return EnderzeugnisMapper.enderzeugnisMapper().updateEnderzeugnis(ez);
	}

	// -----------------------------------------------------------------------------
	// ----------------------------Ende
	// Enderzeugnis----------------------------------------
	// -----------------------------------------------------------------------------

	/**
	 * Die Methode löscht ein Bauteil mit einer bestimmten ID.
	 * 
	 * @param ID
	 *            von einem Bauteil als Integer,
	 * @return Ein String mit einer Meldung, ob Bauteil erfolgreich gelöscht
	 *         wurde
	 */
	public String delete(int id) {

		return BauteilMapper.bauteilMapper().delete(id);
	}

}
