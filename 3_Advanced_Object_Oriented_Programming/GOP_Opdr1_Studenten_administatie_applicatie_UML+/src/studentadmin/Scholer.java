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
	 * @param naam, naam van de Scholer
	 * @param cpp, het programma van de Scholer
	 */
	public Scholer(String naam, Programma cpp) {
		super.setNaam(naam);
		this.cpp = cpp;
	}

	/**
	 * verhoogt het aantal behaalde modules met 1
	 */
	public void setModule() {
		behaaldeModules++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String geslaagd() {
		if (isModulesBehaald() == true) {
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
			s = "" + super.getNaam() + ", " + cpp.getNaam() + ", " + this.behaaldeModules + " module behaald, " + this.geslaagd();
					;
		} else {
			s = "" + super.getNaam() + ", " + cpp.getNaam() + ", " + this.behaaldeModules + " modules behaald, " + this.geslaagd();
		}
		return s;
	}

	/*
	 * private methode, geeft aan of de Scholer de totaal aantal modules heeft
	 * behaald
	 */
	private boolean isModulesBehaald() {
		Programma p = cpp.getProgramma();
		if (behaaldeModules == (p.getMaxScore())) {
			return true;
		}
		return false;
	}

}
