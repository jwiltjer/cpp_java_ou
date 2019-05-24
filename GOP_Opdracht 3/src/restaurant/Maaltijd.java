package restaurant;
/**
 * Klasse representeerd een Maaltijd
 * 
 * @author jwiltjer
 *
 */
public class Maaltijd {
	private String naam;
	private int tafelnummer;

	/**
	 * constructor: Instantieert een Maaltijd
	 * @param naam naam van de maaltijd
	 * @param tafelnummer target tafel nummer in het restaurant
	 */
	public Maaltijd(String naam, int tafelnummer) {
		this.naam = naam;
		this.tafelnummer = tafelnummer;
	}
/**
 * String representatie van het Maaltijd object
 */
	@Override
	public String toString() {
		return naam + " voor tafelnummer " + tafelnummer;
	}
/**
 * getter tafelnummer
 */
	public int getTafelnummer() {
		return tafelnummer;
	}
	
}
