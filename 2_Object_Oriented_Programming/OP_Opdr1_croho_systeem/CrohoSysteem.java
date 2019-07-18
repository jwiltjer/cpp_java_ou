import java.util.ArrayList;

import croho.Croho;
import croho.Instelling;
import croho.Instellingssoort;
import croho.Plaats;

public class CrohoSysteem {

	public static void main(String[] args) {
		Croho croho = new Croho();
		croho.init();
		
		// opdracht A
		croho.voegtoeInstelling("Rijksuniversiteit Groningen", Instellingssoort.WO, "Groningen", 7654);

		// opdracht B
		System.out.println("opdracht b");
		ArrayList<Instelling> lijst = croho.getInstellingen();
		for (Instelling i : lijst) {
			Plaats stad = i.getPlaats();
			System.out.println(i.getNaam() + "(" + i.getSoort() + ") te " + (stad.getNaam()));
		}
		
		// opdracht c
		System.out.println("\nopdracht c");

		int aantalStudenten = 0;
		int aantalWoStudenten = 0;
		int aantalHboStudenten = 0;

		for (Instelling i : lijst) {
			aantalStudenten = aantalStudenten + i.getAantalStudenten();
			if (i.getSoort().equals(Instellingssoort.WO)) {
				aantalWoStudenten = aantalWoStudenten + i.getAantalStudenten();
			} else {
				aantalHboStudenten = aantalHboStudenten + i.getAantalStudenten();
			}
		}

		System.out.println("Totaal aantal WO-Studenten is " + aantalWoStudenten);
		System.out.println("Totaal aantal HBO-Studenten is " + aantalHboStudenten);

		// opdracht d
		System.out.println("\nopdracht d");

		ArrayList<Plaats> lijstPlaatsen = croho.getPlaatsen();
		for (Plaats p : lijstPlaatsen) {

			int aantal = 0;

			for (Instelling i : croho.getInstellingen()) {
				if (p.equals(i.getPlaats())) {
					aantal = aantal + i.getAantalStudenten();
				}
			}
			
			System.out.println("In " + (p.getNaam() + " studeren " + aantal + " studenten"));
		}
	}
}
