package studentadmin;

/**
 * Klasse representeert een Scholer
 * 
 * @author jwiltjer
 *
 */
class Scholer extends Leerling {
	private Programma cpp = null;
	private int behaaldeModules = 0;

	/**
	 * constructor, instantieert een Scholer met een naam en een programma
	 * 
	 * @param naam van de Scholer
	 * @param cpp van de Scholer
	 * @throws StudentAdminException
	 */
	public Scholer(String naam, Programma cpp) throws StudentAdminException {
		super.setNaam(naam);
		this.cpp = cpp;
	}

	/**
	 * verhoogt het aantal behaalde modules met 1
	 */
	public void verhoogModule() {
		behaaldeModules++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String geslaagd() {
		if (isModulesBehaald()) {
			return "is geslaagd";
		} else {
			return "is niet geslaagd";
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String s = "";
		if (behaaldeModules == 1) {
			s = "" + super.getNaam() + ", " + cpp.getNaam() + ", " + this.behaaldeModules + " module behaald, "
					+ this.geslaagd();
			;
		} else {
			s = "" + super.getNaam() + ", " + cpp.getNaam() + ", " + this.behaaldeModules + " modules behaald, "
					+ this.geslaagd();
		}
		return s;
	}

	/**
	 * private methode, geeft aan of de Scholer de totaal aantal modules heeft
	 * behaald
	 */
	public boolean isModulesBehaald() {
		if (behaaldeModules == ((CPP) cpp).getMaxModules()) {
			return true;
		}
		return false;
	}

}
