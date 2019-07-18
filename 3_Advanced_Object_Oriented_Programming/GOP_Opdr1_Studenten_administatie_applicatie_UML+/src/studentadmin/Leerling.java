package studentadmin;

/**
 * Abstracte klasse Leerling, representeert een Leerling
 * 
 * @author jwiltjer
 *
 */
abstract class Leerling {
	private String naam;

	/**
	 * setter naam
	 * 
	 * @param naam, naam van de Leerling
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * getter naam
	 * 
	 * @return naam van de Leerling
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * geeft aan of de Leerling zijn of haar Programma heeft behaald
	 * 
	 * @return String die aangeeft of de Leerling geslaagd is
	 */
	public abstract String geslaagd();

	/**
	 * @return String representatie van de Leerling
	 */
	public abstract String toString();
}