package de.hdm.it04.server.report;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.it04.client.service.report.It04gwtServiceReport;
import de.hdm.it04.client.service.report.It04gwtServiceReportAsync;
import de.hdm.it04.server.It04gwtServiceImpl;
import de.hdm.it04.server.db.BaugruppeMapper;
import de.hdm.it04.server.db.EnderzeugnisMapper;
import de.hdm.it04.shared.Baugruppe;
import de.hdm.it04.shared.Benutzer;
import de.hdm.it04.shared.Enderzeugnis;

/**
 * <p>
 * Implementierungsklasse des Interface <code>It04gwtServiceReport</code>. Diese
 * Klasse ist <em>die</em> Klasse, die neben {@link It04gwtServiceImpl}
 * sämtliche Applikationslogik (oder engl. Business Logic) aggregiert. Sie ist
 * wie eine Spinne, die sämtliche Zusammenhänge in ihrem Netz (in unserem Fall
 * die Daten der Applikation) überblickt und für einen geordneten Ablauf und
 * dauerhafte Konsistenz der Daten und Abläufe sorgt.
 * </p>
 * <p>
 * Die Applikationslogik findet sich in den Methoden dieser Klasse. Jede dieser
 * Methoden kann als <em>Transaction Script</em> bezeichnet werden. Dieser Name
 * lässt schon vermuten, dass hier analog zu Datenbanktransaktion pro
 * Transaktion gleiche mehrere Teilaktionen durchgeführt werden, die das System
 * von einem konsistenten Zustand in einen anderen, auch wieder konsistenten
 * Zustand überführen. Wenn dies zwischenzeitig scheitern sollte, dann ist das
 * jeweilige Transaction Script dafür verwantwortlich, eine Fehlerbehandlung
 * durchzuführen.
 * </p>
 * <p>
 * Diese Klasse steht mit einer Reihe weiterer Datentypen in Verbindung. Dies
 * sind:
 * <ol>
 * <li>{@link It04gwtServiceReport}: Dies ist das <em>lokale</em> - also
 * Server-seitige - Interface, das die im System zur Verfügung gestellten
 * Funktionen deklariert.</li>
 * <li>{@link It04gwtServiceReportAsync}: <code>It04gwtServiceReportImpl</code> und
 * <code>It04gwtServiceReport</code> bilden nur die Server-seitige Sicht der
 * Applikationslogik ab. Diese basiert vollständig auf synchronen
 * Funktionsaufrufen. Wir müssen jedoch in der Lage sein, Client-seitige
 * asynchrone Aufrufe zu bedienen. Dies bedingt ein weiteres Interface, das in
 * der Regel genauso benannt wird, wie das synchrone Interface, jedoch mit dem
 * zusätzlichen Suffix "Async". Es steht nur mittelbar mit dieser Klasse in
 * Verbindung. Die Erstellung und Pflege der Async Interfaces wird durch das
 * Google Plugin semiautomatisch unterstützt. Weitere Informationen unter
 * {@link It04gwtServiceReportAsync}.</li>
 * <li> {@link RemoteServiceServlet}: Jede Server-seitig instantiierbare und
 * Client-seitig über GWT RPC nutzbare Klasse muss die Klasse
 * <code>RemoteServiceServlet</code> implementieren. Sie legt die funktionale
 * Basis für die Anbindung von <code>BankVerwaltungImpl</code> an die Runtime
 * des GWT RPC-Mechanismus.</li>
 * </ol>
 * </p>
 * <p>
 * <b>Wichtiger Hinweis:</b> Diese Klasse bedient sich sogenannter
 * Mapper-Klassen. Sie gehören der Datenbank-Schicht an und bilden die
 * objektorientierte Sicht der Applikationslogik auf die relationale
 * organisierte Datenbank ab. Zuweilen kommen "kreative" Zeitgenossen auf die
 * Idee, in diesen Mappern auch Applikationslogik zu realisieren. Siehe dazu
 * auch die Hinweise in {@link #delete(Benutzer)} Einzig nachvollziehbares
 * Argument für einen solchen Ansatz ist die Steigerung der Performance
 * umfangreicher Datenbankoperationen. Doch auch dieses Argument zieht nur dann,
 * wenn wirklich große Datenmengen zu handhaben sind. In einem solchen Fall
 * würde man jedoch eine entsprechend erweiterte Architektur realisieren, die
 * wiederum sämtliche Applikationslogik in der Applikationsschicht isolieren
 * würde. Also, keine Applikationslogik in die Mapper-Klassen "stecken" sondern
 * dies auf die Applikationsschicht konzentrieren!
 * </p>
 * <p>
 * Beachten Sie, dass sämtliche Methoden, die mittels GWT RPC aufgerufen werden
 * können ein <code>throws IllegalArgumentException</code> in der
 * Methodendeklaration aufweisen. Diese Methoden dürfen also Instanzen von
 * {@link IllegalArgumentException} auswerfen. Mit diesen Exceptions können z.B.
 * Probleme auf der Server-Seite in einfacher Weise auf die Client-Seite
 * transportiert und dort systematisch in einem Catch-Block abgearbeitet werden.
 * </p>
 * 
 * @see It04gwtServiceReport
 * @see It04gwtServiceReportAsync
 * @see RemoteServiceServlet
 * @author Schwab, Thies
 */

public class It04gwtServiceReportImpl extends RemoteServiceServlet implements It04gwtServiceReport {
	
	/**
	 * Strukturstueckliste
	 */

	@Override
	public Baugruppe createStrukturstuecklisteReport(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Die Methode wird benötigt, um eine Baugruppe mit einer bestimmten ID zu
	 * finden. 
	 * @param Eine ID einer Baugruppe, welche gefunden werden soll
	 * @return Vektor vom Typ Baugruppe, welcher die Baugruppe mit der übergebenen
	 *         ID enthält
	 */
	@Override
	public Vector<Baugruppe> getBaugruppe(int id) {
		return BaugruppeMapper.baugruppeMapper().findByKey(id);
	}

	/**
	 * Die Methode wird benötigt, um eine Baugruppe mit einem bestimmten Namen zu
	 * finden. Da mehrere Baugruppen mit dem selben Namen existieren können, wird
	 * die Baugruppe in einem Vektor gespeichert.
	 * 
	 * @param Ein Name einer Baugruppe, welche gefunden werden soll
	 * @return Vektor vom Typ Baugruppe, welcher alle Baugruppen mit dem übergebenen
	 *         Namen enthält
	 */
	@Override
	public Vector<Baugruppe> getBaugruppe(String name) {
		return BaugruppeMapper.baugruppeMapper().findByName(name);
		
	}

	/**
	 * Die Methode wird benötigt, um ein Enderzeugnis mit einer bestimmten ID zu
	 * finden. 
	 * @param Eine ID eines Enderzeugnisses, welche gefunden werden soll
	 * @return Vektor vom Typ Enderzeugnis, welcher das Enderzeugnis mit der übergebenen
	 *         ID enthält
	 */
	@Override
	public Vector<Enderzeugnis> getEnderzeugnis(int id) {
		return EnderzeugnisMapper.enderzeugnisMapper().findByKey(id);
	}

	/**
	 * Die Methode wird benötigt, um ein Enderzeugnis mit einem bestimmten Namen zu
	 * finden. Da mehrere Enderzeugnisse mit dem selben Namen existieren können, wird
	 * das Enderzeugnis in einem Vektor gespeichert.
	 * 
	 * @param Ein Name eines Enderzeugnisses, welche gefunden werden soll
	 * @return Vektor vom Typ Enderzeugnis, welcher alle Enderzeugnisse mit dem übergebenen
	 *         Namen enthält
	 */
	@Override
	public Vector<Enderzeugnis> getEnderzeugnis(String name) {
		//return EnderzeugnisMapper.enderzeugnisMapper().findByName(name);
		return null;
	}
	
	@Override
	public Enderzeugnis createMaterialbedarfReport(int id, int anzahl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
