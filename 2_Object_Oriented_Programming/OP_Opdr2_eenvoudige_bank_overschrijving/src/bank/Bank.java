package bank;

import java.util.ArrayList;

/*
 * Deze klasse faciliteert het overmaken, storten en opnemen tussen 2 gegeven rekeningen.
 */
public class Bank {
	ArrayList<Rekening> rekeninglijst;
	
	/*
	 * initialiseert een bank object maakt een nieuwe lege arraylist aan.
	 */
	public Bank() {
		this.rekeninglijst = new ArrayList<Rekening>();
	}

	/*
	 * voegt een nieuw rekening object toe aan de ArrayList met een gegeven naam en
	 * een gegeven nummer.
	 */
	public void voegRekeningToe(String naam, int nummer) {
		Rekening rekening = new Rekening(naam, nummer);
		rekeninglijst.add(rekening);
	}

	/*
	 * loopt door de ArrayList rekeninglijst heen een geeft een rekening object
	 * terug die overeenkomt met het rekeningnummer en controleert eerst of dit een
	 * nummeriek getal is.
	 */
	private Rekening zoekRekeningNr(int rekeningnummer) {
		for (Rekening rekening : rekeninglijst) {
			if ((rekeningnummer) == rekening.getNummer()) {
				return rekening;
			}
		}
		return null;
	}

	/*
	 * geeft een de naam terug die hoort bij het rekening object.
	 */
	public String geefRekeningNaam(int rekeningnummer) {
		Rekening rekening0 = zoekRekeningNr(rekeningnummer);

		if (rekening0 == null) {
			// doe niks want het rekening nummer bestaat niet 
		} else if ((rekeningnummer) == rekening0.getNummer()) {
			return rekening0.getNaam();
		}
		return null;
	}

	/*
	 * geef het saldo bij de invoer rekeningnummer
	 */
	public double geefSaldo(int rekeningnummer) {

		Rekening rekening0 = zoekRekeningNr(rekeningnummer);
		if (rekening0 == null) {
			// doe niks want het rekening nummer bestaat niet
		} else if ((rekeningnummer) == rekening0.getNummer()) {
			return rekening0.getSaldo();
		}
		return 0.0;
	}

	/*
	 * voert een storting uit op een rekeningnummer met een gegeven bedrag
	 */
	public void stortBank(int rekeningnummer, double bedrag) {
		Rekening rekening = zoekRekeningNr(rekeningnummer);
		if (rekening == null) {
			// doe niks want het rekening nummer bestaat niet
		} else {
			rekening.stort(bedrag);
		}
	}

	/*
	 * voert een opname uit van een rekeningnummer met een gegeven bedrag
	 */
	public void neemOpBank(int rekeningnummer, double bedrag) {
		Rekening rekening = zoekRekeningNr(rekeningnummer);
		if (rekening == null) {
			// doe niks want het rekening nummer bestaat niet
		} else {
			rekening.neemOp(bedrag);

		}
	}

	/*
	 * schrijft een bedrag over tussen twee gegeven rekeningnummers
	 */
	public void maakOver(int vanRekening, int naarRekening, double bedrag) {
		Rekening vanRekening1 = zoekRekeningNr(vanRekening);
		Rekening naarRekening2 = zoekRekeningNr(naarRekening);

		if (vanRekening1 == null || naarRekening2 == null || vanRekening1.equals(naarRekening2)
				|| naarRekening2.equals(vanRekening1)) {
			// doe niks omdat de rekeningen niet bestaan of de rekeningen gelijk zijn

		} else if (vanRekening1.getNummer() == vanRekening && vanRekening1.getSaldo() > 0
				&& bedrag <= vanRekening1.getSaldo()) {
			// wanneer het rekening nummer klopt en het saldo groter is dan 0 en het bedrag
			// gelijk aan of kleiner is dan het saldo, voer dan de overschrijving uit.

			vanRekening1.neemOp(bedrag);
			naarRekening2.stort(bedrag);
		}

	}

}
