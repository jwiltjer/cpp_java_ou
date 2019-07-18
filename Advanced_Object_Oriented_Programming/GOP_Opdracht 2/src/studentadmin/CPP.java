package studentadmin;

/**
 * Klasse representeert een CPP
 * 
 * @author jwiltjer
 *
 */
class CPP extends Programma {

	private final int aantal_modules;

	/**
	 * instantieert een CPP
	 * 
	 * @param naam van het CPP
	 * @param aantal modules het CPP
	 */
	public CPP(String naam, int aantal_modules) {
		super.setNaam(naam);
		this.aantal_modules = aantal_modules;
	}

	/**
	 * {@inheritDoc}
	 */

	public double getMaxModules() {
		return aantal_modules;
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
		return "" + super.getNaam() + " aantal Modules: " + this.aantal_modules;
	}

}
