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
	 * default constructor, constructs een nieuwe studenten administratie
	 */
	public StudentAdmin() {
		this.leerlingLijst = new ArrayList<Leerling>();
		this.programmaLijst = new ArrayList<Programma>();
	}

	/**
	 * voegt een nieuwe student toe aan de lijst met studenten
	 * 
	 * @param naam, naam van de student
	 * @param programma, programma dat de student volgt
	 */
	public void addStudent(String naam, String programma) {
		if (isProgramma(programma) == true) {
			if (zoekProgramma(programma) instanceof Opleiding) {
				leerlingLijst.add(new Student(naam, zoekProgramma(programma)));
			} else if (zoekProgramma(programma) instanceof CPP) {
				leerlingLijst.add(new Scholer(naam, zoekProgramma(programma)));
			}
			// doe niks
		}
	}

	/**
	 * voegt een opleiding programma toe aan de lijst met Programma objecten
	 * 
	 * @param naam, naam van de opleiding
	 * @param STUDIEPUNTEN, totaal aantal studiepunten in de opleiding te behalen
	 */
	public void addOpleiding(String naam, double STUDIEPUNTEN) {
		programmaLijst.add(new Opleiding(naam, STUDIEPUNTEN));

	}

	/**
	 * voegt een CPP programma toe aan de lijst met Programma objecten
	 * 
	 * @param naam, naam de CPP
	 * @param AANTAL_MODULES, aantal modules in het CPP
	 */
	public void addCPP(String naam, int AANTAL_MODULES) {
		programmaLijst.add(new CPP(naam, AANTAL_MODULES));
	}

	/**
	 * verhoogt het aantal behaalde modules binnen een CPP programma van een gegeven
	 * student
	 * 
	 * @param naam, naam de van student
	 */
	public void verhoogModule(String naam) {
		Leerling l = zoekLeerling(naam);
		if (l instanceof Scholer && l != null) {
			((Scholer) l).setModule();
		}
		// doe niks, leerling is geen scholer
	}

	/**
	 * verhoogt het aantal behaalde studiepunten binnen een opleiding programma van
	 * een gegeven student
	 * 
	 * @param naam, naam van de student
	 * @param punten, aantal behaalde studiepunten
	 */
	public void verhoogPunten(String naam, double punten) {
		Leerling l = zoekLeerling(naam);
		if (l instanceof Student && l != null) {
			((Student) l).setPunten(punten);
		}
		// doe niks, leerling is geen student
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
	 * @param naam, naam van de student
	 * @return een String representatie van de informatie over de student
	 */
	public String printStudentInfo(String naam) {
		Leerling l = zoekLeerling(naam);
		String info = "";
		if (l != null) {
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
	 * @returnen ArrayList van Strings met de opleiding namen uit de programmalijst
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
	private Boolean isProgramma(String prog) {
		for (Programma s : programmaLijst) {
			if (s.getNaam().equals(prog)) {
				return true;
			}
			// doe niks, programma bestaat niet
		}
		return false;
	}

	/*
	 * private methode om een leerling op te zoeken op basis van zijn of haar naam
	 */
	private Leerling zoekLeerling(String naam) {
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
