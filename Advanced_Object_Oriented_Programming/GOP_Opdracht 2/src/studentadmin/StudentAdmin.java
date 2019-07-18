package studentadmin;

import java.util.ArrayList;
import java.util.List;

/**
 * Deze klasse beheert een studenten administratie
 * 
 * @author jwiltjer
 *
 */
public class StudentAdmin {
	private List<Leerling> leerlingLijst;
	private List<Programma> programmaLijst;

	/**
	 * creert een nieuwe administratie van studenten
	 */
	public StudentAdmin() {
		this.leerlingLijst = new ArrayList<Leerling>();
		this.programmaLijst = new ArrayList<Programma>();
		mijnInit();
	}

	/*
	 * private methode om de CPP's en opleidingen uit de opdracht toe te voegen aan de lijst van programma's.
	 */
	private void mijnInit() {
		addOpleiding("Wiskunde", 160);
		addOpleiding("Informatica", 200);
		addCPP("CPP JAVA", 6);
		addCPP("CPP Software Architect", 4);
		addCPP("CPP Systeem Ontwikkelaar", 3);
	}

	/**
	 * voegt een nieuwe leerling toe aan de lijst met leerlingen
	 * 
	 * @param naam      van de leerling
	 * @param programma dat de leerling volgt
	 */
	public void addStudent(String naam, String programma) throws StudentAdminException {
		if (!isProgramma(programma)) {
			throw new StudentAdminException("Opleiding bestaat niet");
		} else if (zoekLeerling(naam) != null) {
			throw new StudentAdminException("Leerling bestaat al");
		}

		if (zoekProgramma(programma) instanceof Opleiding) {
			leerlingLijst.add(new Student(naam, zoekProgramma(programma)));
		} else if (zoekProgramma(programma) instanceof CPP) {
			leerlingLijst.add(new Scholer(naam, zoekProgramma(programma)));

		}
	}

	/**
	 * voegt een opleiding programma toe aan de lijst met Programma objecten
	 * 
	 * @param naam         van de opleiding
	 * @param studiepunten in de opleiding te behalen
	 */
	public void addOpleiding(String naam, double studiepunten) {
		programmaLijst.add(new Opleiding(naam, studiepunten));
	}

	/**
	 * voegt een CPP programma toe aan de lijst met Programma objecten
	 * 
	 * @param naam   van de CPP
	 * @param aantal modules in het CPP
	 */
	public void addCPP(String naam, int aantal_modules) {
		programmaLijst.add(new CPP(naam, aantal_modules));
	}

	/**
	 * verhoogt het aantal behaalde modules binnen een CPP programma van een gegeven
	 * Scholer
	 * 
	 * @param naam van de Scholer
	 * 
	 */
	public void verhoogModule(String naam) throws StudentAdminException {
		Leerling l = zoekLeerling(naam);
		if (l == null) {
			throw new StudentAdminException("Leerling komt niet voor in de administratie");
		} else if (!(l instanceof Scholer)) {
			throw new StudentAdminException(naam + " is geen Scholer, vul een geldige naam in");
		} else if (((Scholer) l).isModulesBehaald()) {
			throw new StudentAdminException(naam + " is al geslaagd");
		} else {
			((Scholer) l).verhoogModule();
		}
	}

	/**
	 * verhoogt het aantal behaalde studiepunten binnen een opleiding programma van
	 * een gegeven student
	 * 
	 * @param naam     van de student
	 * @param behaalde aantal studiepunten
	 */
	public void verhoogPunten(String naam, double punten) throws StudentAdminException {
		Leerling l = zoekLeerling(naam);
		if (l == null) {
			throw new StudentAdminException("Leerling komt niet voor in de administratie");
		} else if (!(l instanceof Student)) {
			throw new StudentAdminException(naam + " is geen Student, vul een geldige naam in");
		} else if (((Student) l).isPuntenBehaald()) {
			throw new StudentAdminException(naam + " is al geslaagd");
		} else if (((Student) l).puntenTekort() <= punten) {
			throw new StudentAdminException(
					naam + " moet nog " + ((Student) l).puntenTekort() + " punten halen, vul een geldig aantal in");
		} else if (punten < 1) {
			throw new StudentAdminException("Vul een getal in tussen 1.0 - " + ((Student) l).puntenTekort());
		} else {
			((Student) l).verhoogPunten(punten);
		}
	}

	/**
	 * geeft een String met alle studenten in de administatie
	 * 
	 * @return een String representatie van alle studenten in de administratie
	 */
	public String printStudentenLijst() {
		StringBuilder lijst = new StringBuilder();
		for (Leerling l : leerlingLijst) {
			lijst.append(l.toString()).append("\n");
		}
		return lijst.toString();

	}

	/**
	 * geeft een String met de informatie over een gegeven student
	 * 
	 * @param naam van de student
	 * @return een String representatie van de informatie over de student
	 * @throws StudentAdminException
	 */
	public String printStudentInfo(String naam) throws StudentAdminException {
		String info = "";

		Leerling l = zoekLeerling(naam);
		if (l == null) {
			throw new StudentAdminException("Leerling komt niet voor in de administratie");
		} else {
			info = l.toString();
		}
		return info;

	}

	/**
	 * Creeert een lijst van Strings met alle CPP namen
	 * 
	 * @return een ArrayList van Strings met de CPP namen uit de programmalijst
	 */
	public ArrayList<String> comboLijstCPP() {
		ArrayList<String> combocpp = new ArrayList<String>();
		for (Programma p : programmaLijst) {
			if (p instanceof CPP) {
				combocpp.add(p.getNaam());
			}
			// p != CPP
		}
		return combocpp;
	}

	/**
	 * Creert een lijst van String met alle opleiding namen
	 * 
	 * @return een ArrayList van Strings met de opleiding namen uit de
	 *         programmalijst
	 */
	public ArrayList<String> comboLijstOpleiding() {
		ArrayList<String> comboOpleiding = new ArrayList<String>();
		for (Programma p : programmaLijst) {
			if (p instanceof Opleiding) {
				comboOpleiding.add(p.getNaam());
			}
			// p != Opleiding
		}
		return comboOpleiding;
	}

	/*
	 * private methode om na te gaan of het gegeven programma bestaat
	 */
	private Boolean isProgramma(String prog) throws StudentAdminException {
		for (Programma s : programmaLijst) {
			if (s.getNaam().equals(prog)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * private methode om een leerling op te zoeken op basis van zijn of haar naam
	 */
	private Leerling zoekLeerling(String naam) throws StudentAdminException {
		if (naam.isEmpty()) {
			throw new StudentAdminException("Naamveld is leeg, vul een naam in");
		} else if (!naam.matches("[a-zA-Z]+")) {
			throw new StudentAdminException("Een naam mag alleen letters bevatten, vul een geldige naam in");
		}

		for (Leerling l : leerlingLijst) {
			if (l.getNaam().equals(naam)) {
				return l;
			}

		}
		return null;

	}

	/*
	 * private methode om een programma op te zoeken op basis van een programma naam
	 */
	private Programma zoekProgramma(String programma) {
		for (Programma p : programmaLijst) {
			if (p.getNaam().equals(programma)) {
				return p;
			}
		}
		return null;
	}

}
