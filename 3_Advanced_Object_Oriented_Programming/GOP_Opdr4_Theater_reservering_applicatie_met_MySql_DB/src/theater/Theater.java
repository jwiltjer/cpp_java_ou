package theater;

import java.sql.SQLException;
import java.util.*;

import theater.Plaats.Status;
import theaterdata.*;

/**
 * Representeert (de kassa van) een theater.
 * 
 * @author Open Universiteit
 */
public class Theater {
	public static final int AANTALPERRIJ = 10;
	public static final int AANTALRIJEN = 15;
	private String naam = null;
	private Voorstelling huidigeVoorstelling = null;

	/**
	 * Creeert een theater.
	 * 
	 * @param naam theaternaam
	 * @throws TheaterException
	 * @throws SQLException
	 */
	public Theater(String naam) throws TheaterException {
		this.naam = naam;
	}

	/**
	 * initialiseert de prepStatements in de Klantbeheer en Voorstellingbeheer
	 * klassen
	 * 
	 * @throws TheaterException
	 */
	public void initQueries() throws TheaterException {
		Klantbeheer.init();
		Voorstellingbeheer.init();

	}

	/**
	 * sluit de connectie met de database
	 * 
	 * @throws TheaterException
	 */
	public void sluitDB() throws TheaterException {
		Connectiebeheer.closeDB();
	}

	/**
	 * Geeft de naam van het theater.
	 * 
	 * @return naam van het theater
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Geeft een lijst van data waarop voorstellingen zijn gepland.
	 * 
	 * @return lijst met data.
	 * @throws SQLException
	 * @throws TheaterException
	 */
	public ArrayList<GregorianCalendar> geefVoorstellingsData() throws TheaterException {
		return Voorstellingbeheer.geefVoorstellingsData();
	}

	/**
	 * Wisselt de huidige voorstelling naar voorstelling met gegeven datum.
	 * 
	 * @param datum datum van gevraagde voorstelling
	 * @throws TheaterException
	 */
	public void wisselVoorstelling(GregorianCalendar datum) throws TheaterException {
		huidigeVoorstelling = Voorstellingbeheer.geefVoorstelling(datum);
	}

	/**
	 * Geeft de huidige voorstelling.
	 * 
	 * @return de huidige voorstelling
	 */
	public Voorstelling getHuidigeVoorstelling() {
		return huidigeVoorstelling;
	}

	/**
	 * Plaatst een klant op alle gereserveerde stoelen van de huidige uitvoering.
	 * Wanneer een klant nog niet bestaat, dan wordt deze eerst gecreeerd.
	 * 
	 * @param naam     naam van klant
	 * @param telefoon telefoonnummer van klant
	 * @throws TheaterException
	 */
	public void plaatsKlant(String naam, String telefoon) throws TheaterException {
		if (isGereserveerdInZaal()) {
			Klant klant = Klantbeheer.geefKlant(naam, telefoon);
			huidigeVoorstelling.plaatsKlant(klant);
		} else {
			throw new TheaterException("er zijn geen stoelen gereserveerd, probeer opnieuw");
		}
	}

	/**
	 * Verandert de reserveringsstatus (VRIJ<=>GERESERVEERD) van een plaats in de
	 * huidige voorstelling.
	 * 
	 * @param rijnummer   rijnummer van plaats
	 * @param stoelnummer stoelnummer van plaats
	 * @return true als verandering is gelukt, anders false
	 */
	public boolean veranderReservering(int rijnummer, int stoelnummer) {
		return huidigeVoorstelling.veranderReservering(rijnummer, stoelnummer);

	}

	/**
	 * Geeft informatie over een plaats in de huidige voorstelling.
	 * 
	 * @param rijnummer   rijnummer van plaats
	 * @param stoelnummer stoelnummer van plaats
	 * @return informatie over plaats
	 */
	public String geefPlaatsInfo(int rijnummer, int stoelnummer) {
		Plaats plaats = huidigeVoorstelling.getPlaats(rijnummer, stoelnummer);
		return plaats.toString();
	}

	/**
	 * geeft een voorstelling op een bepaalde datum
	 * 
	 * @param datum van de voorstelling
	 * @return voorstellings object met de voorstelling van de gegeven datum
	 * @throws TheaterException voorwaarde:voorstelling moet wel bestaan op de
	 *                          gegeven datum anders wordt null returned
	 */
	public Voorstelling geefVoorstelling(GregorianCalendar datum) throws TheaterException {
		return Voorstellingbeheer.geefVoorstelling(datum);

	}
//private methode 

	/**
	 * Controleert of er plaatsen zijn met de status GERESERVEERD
	 * 
	 * @return true als er plaatsen gereserveerd zijn
	 * @return false als er geen plaatsen gereserveerd zijn
	 */
	private boolean isGereserveerdInZaal() {
		for (int i = 1; i < Theater.AANTALRIJEN + 1; i++) {
			for (int j = 1; j < Theater.AANTALPERRIJ + 1; j++) {
				if (getHuidigeVoorstelling().getPlaats(i, j).getStatus() == Status.GERESERVEERD)
					return true;
			}
		}
		return false;
	}

}
