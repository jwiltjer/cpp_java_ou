package studentadmin;

/**
 * Abstracte klasse Programma, representeert een Programma
 * 
 * @author jwiltjer
 *
 */
abstract class Programma {

	private String naam;

	/**
	 * setter naam
	 * 
	 * @param naam van het Programma
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * getter naam
	 * 
	 * @return naam van het Programma
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Geeft het programma object terug
	 * 
	 * @return een programma object
	 */
	public abstract Programma getProgramma();

	/**
	 * @return String representatie van het Programma
	 */
	public abstract String toString();
}