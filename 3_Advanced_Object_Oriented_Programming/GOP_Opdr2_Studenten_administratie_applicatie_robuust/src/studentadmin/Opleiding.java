package studentadmin;

/**
 * Klasse representeert een Opleiding
 * 
 * @author jwiltjer
 *
 */
class Opleiding extends Programma {

	private final double studiepunten;

	/**
	 * instantieert een Opleiding
	 * 
	 * @param naam   van de Opleiding
	 * @param aantal studiepunten van de Opleiding
	 */
	public Opleiding(String naam, double studiepunten) {
		super.setNaam(naam);
		this.studiepunten = studiepunten;
	}

	/**
	 * {@inheritDoc}
	 */
	public double getMaxStudiepunten() {
		return studiepunten;
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
		return "" + super.getNaam() + " aantal studiepunten: " + this.studiepunten;
	}

}
