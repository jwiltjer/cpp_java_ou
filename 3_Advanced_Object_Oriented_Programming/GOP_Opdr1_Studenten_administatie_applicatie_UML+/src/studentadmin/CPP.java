package studentadmin;

/**
 * Klasse representeert een CPP
 * 
 * @author jwiltjer
 *
 */
class CPP extends Programma {

	private final int AANTAL_MODULES;

	/**
	 * Constructor, instantieert een CPP
	 * 
	 * @param naam, naam van het CPP
	 * @param AANTAL_MODULES, aantal modules het CPP
	 */
	public CPP(String naam, int AANTAL_MODULES) {
		super.setNaam(naam);
		this.AANTAL_MODULES = AANTAL_MODULES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaxScore() {
		return AANTAL_MODULES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CPP getProgramma() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "" + super.getNaam() + " aantal Modules: " + this.AANTAL_MODULES;
	}

}
