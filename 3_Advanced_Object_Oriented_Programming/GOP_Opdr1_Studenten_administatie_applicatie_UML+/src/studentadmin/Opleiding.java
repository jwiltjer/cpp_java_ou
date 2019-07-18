package studentadmin;

/**
 * Klasse representeert een Opleiding
 * 
 * @author jwiltjer
 *
 */
class Opleiding extends Programma {

	private final double STUDIEPUNTEN;

	/**
	 * constructor, instantieert een Opleiding
	 * 
	 * @param naam, naam van de Opleiding
	 * @param STUDIEPUNTEN, aantal studiepunten van de Opleiding
	 */
	public Opleiding(String naam, double STUDIEPUNTEN) {
		super.setNaam(naam);
		this.STUDIEPUNTEN = STUDIEPUNTEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaxScore() {
		return STUDIEPUNTEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Opleiding getProgramma() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "" + super.getNaam() + " aantal studiepunten: " + this.STUDIEPUNTEN;
	}

}
