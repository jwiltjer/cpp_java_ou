package bank;

/*
 * Klasse die verantwoordelijk is voor het beheer van het saldo op een rekening.
 */
public class Rekening {
	private int nummer = 0;
	private String naam = null;
	private double saldo = 0.0;

	/*
	 * constructor, initialiseert een rekening object met een naam en nummer.
	 */
	public Rekening(String naam, int nummer) {
		this.naam = naam;
		this.nummer = nummer;
	}

	/*
	 * geeft de naam van de rekening terug.
	 */
	public String getNaam() {
		return naam;
	}

	/*
	 * geeft het nummer van de rekening terug.
	 */
	public int getNummer() {
		return nummer;
	}

	/*
	 * geeft het saldo van de rekening terug
	 */
	public double getSaldo() {
		return saldo;
	}

	/*
	 * Verhoogt het saldo van deze Rekening met een gegeven bedrag.
	 */
	public void stort(double bedrag) {
		if (bedrag >= 0) {
			saldo = saldo + bedrag;
		}
	}

	/*
	 * Verlaagt het saldo van deze Rekening met een gegeven bedrag.
	 */
	public void neemOp(double bedrag) {
		if (saldo >= bedrag && bedrag > 0 && saldo > 0) { 
			saldo = saldo - bedrag;
		}
	}

}
