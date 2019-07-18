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
 * @return string representatie
 */
	@Override
	public String toString() {
		return naam + " voor tafelnummer " + tafelnummer;
	}
/**
 * geef aan naar welk tafelnummer de maaltijd moet worden bezorgd
 * @return tafelnummer van de maaltijd
 */
	public int getTafelnummer() {
		return tafelnummer;
	}
	
}
