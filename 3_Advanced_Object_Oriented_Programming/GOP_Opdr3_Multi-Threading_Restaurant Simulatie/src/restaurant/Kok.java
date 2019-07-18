package restaurant;

import java.util.Random;

/**
 * Klasse representeerd een Kok
 * 
 * @author jwiltjer
 *
 */
public class Kok implements Runnable {
	private String naam;
	private Uitgiftebalie balie;
	private final static int BEREIDINGSTIJD = 4000;

	private volatile boolean stopkoken = false;

	/**
	 * Constructor: Instantieert een kok
	 * 
	 * @param naam van de kok
	 * @param uitgiftebalie in het Restaurant
	 */
	public Kok(String naam, Uitgiftebalie balie) {
		this.naam = naam;
		this.balie = balie;
	}

	/**
	 * laat een kok starten met koken in een thread
	 */
	@Override
	public void run() {
		while (!stopkoken) {
			kook();
		}
		System.out.println("Kok " + naam + " is gestopt met pizza bakken");
	}

	/**
	 * Instantieert een nieuwe Thread en start deze
	 */
	public void startKoken() {
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("Kok " + naam + " is gestart met pizza bakken");
	}

	/**
	 * laat de kok stoppen met koken
	 */
	public void stopKoken() {
		this.stopkoken = true;
		
	}

	// private methode: laat een Kok achter elkaar maaltijden koken
	private void kook() {
		Maaltijd maaltijd = new Maaltijd(kiesPizza(), kiesTafel());
		System.out.println("Kok " + naam + " bakt " + maaltijd.toString());
		try {
			Thread.sleep(BEREIDINGSTIJD);

			System.out.println("Kok " + naam + " zet " + maaltijd.toString()+" op de balie");

		} catch (InterruptedException e) {

		}
		balie.plaatsMaaltijd(maaltijd);
	}

	// private methode: genereerd een random tafelnummer
	private int kiesTafel() {
		Random rn = new Random();
		int randomTafelnummer = rn.nextInt(Restaurant.AANTAL_TAFELS) + 1;
		return randomTafelnummer;
	}

	// private methode: genereerd een random Maaltijd
	private String kiesPizza() {
		String[] pizzas = { "Pizza Calzone", "Pizza Salami", "Pizza Margherita", "Pizza Tonno", "Pizza Hawaii" };
		Random rn = new Random();
		int randomMaaltijd = rn.nextInt(pizzas.length);
		return pizzas[randomMaaltijd];
	}

}
