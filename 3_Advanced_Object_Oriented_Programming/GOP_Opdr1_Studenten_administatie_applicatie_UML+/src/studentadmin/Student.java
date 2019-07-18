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
	 * Constructor, instantieert een nieuwe Student
	 * 
	 * @param naam, naam van de student
	 * @param programma, programma van de student
	 */
	public Student(String naam, Programma programma) {
		super.setNaam(naam);
		this.programma = programma;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String geslaagd() {
		if (isPuntenBehaald() == true) {
			return "is geslaagd";
		} else {
			return "is niet geslaagd";
		}
	}
/*
 * private methode, geeft aan of het maximaal aantal punten van het programma behaald is
 */
	private boolean isPuntenBehaald() {
		Programma p = programma.getProgramma();
		if (studiepunten == (p.getMaxScore())) {
			return true;
		}
		return false;
	}
/**
 * Verhoogd de studiepunten met de nieuw behaalde punten
 * @param punt, de nieuw behaalde punten
 */
	public void setPunten(double punt) {
		studiepunten = +punt;
	}
/**
 * {@inheritDoc}
 */
	@Override
	public String toString() {
		return "" + super.getNaam() + ", " + programma.getNaam() + ", " + this.studiepunten
				+ " studiepunten, " + this.geslaagd();
	}

}
