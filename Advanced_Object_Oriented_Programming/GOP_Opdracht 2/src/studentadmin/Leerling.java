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
	 * @param naam van de Leerling
	 * @throws StudentAdminException
	 */
	public void setNaam(String naam) throws StudentAdminException {
		if (naam.isEmpty()) {
			throw new StudentAdminException("Voer een naam in");
		} else if (!naam.matches("[a-zA-Z]+")) {
			throw new StudentAdminException("Een naam mag alleen letters bevatten, vul een geldige naam in");
		} else if (naam.length() < 3) {
			throw new StudentAdminException("naam \"" + naam + "\" is te kort, vul een naam in met meer dan 2 letters");
		} else {
			this.naam = naam;
		}
	}

	/**
	 * getter naam
	 * 
	 * @return naam van de Leerling
	 * 
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