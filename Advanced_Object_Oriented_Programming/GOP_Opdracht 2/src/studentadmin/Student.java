package studentadmin;

/**
 * Klasse representeert een Student
 * 
 * @author jwiltjer
 *
 */
class Student extends Leerling {
	private Programma programma = null;
	private double studiepunten = 0.0;

	/**
	 * Instantieert een nieuwe Student
	 * 
	 * @param naam van de student
	 * @param programma van de student
	 * @throws StudentAdminException
	 */
	public Student(String naam, Programma programma) throws StudentAdminException {
		super.setNaam(naam);
		this.programma = programma;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String geslaagd() {
		if (isPuntenBehaald()) {
			return "is geslaagd";
		} else {
			return "is niet geslaagd";
		}
	}

	/**
	 * methode geeft aan of het aantal punten van de opleiding behaald is
	 * @return true wanneer het aantal punten behaald is
	 */
	public boolean isPuntenBehaald() {
		if (studiepunten == ((Opleiding)programma).getMaxStudiepunten()) {
			return true;
		}
		return false;
	}
/**
 * methode geeft aan hoeveel punten de student tekort komt om te slagen
 * @return aantal punten tekort om te slagen
 */
	public double puntenTekort() {
		return ((Opleiding)programma).getMaxStudiepunten() - studiepunten;

	}

	/**
	 * Verhoogd de studiepunten met de nieuw behaalde punten
	 * 
	 * @param punt, de nieuw behaalde punten
	 */
	public void verhoogPunten(double punt) {
		studiepunten += punt;
	}

	/**
	 * getter studiepunten
	 * 
	 * @return studiepunten
	 */
	public double getStudiepunten() {
		return studiepunten;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "" + super.getNaam() + ", " + programma.getNaam() + ", " + this.studiepunten + " studiepunten, "
				+ this.geslaagd();
	}

}
