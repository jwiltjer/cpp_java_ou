package restaurant;

/**
 * Klasse representeert een Ober
 * 
 * @author jwiltjer
 *
 */
public class Ober implements Runnable {
	private static final int LOOPTIJD = 500;
	private static final int WACHTTIJD = 1000;
	private String naam;
	private Uitgiftebalie balie;

	private volatile boolean stopserveren = false;

	/**
	 * constructor: Instantieert een Ober
	 * 
	 * @param naam van de Ober
	 * @param balie in het Restaurant
	 */
	public Ober(String naam, Uitgiftebalie balie) {
		this.naam = naam;
		this.balie = balie;
	}

// private methode: laat een Ober Maaltijden serveren
	private void serveer(Maaltijd maaltijd) {
		if (maaltijd != null) {
			final int looptijd = maaltijd.getTafelnummer() * LOOPTIJD;

			System.out.println("Ober " + naam + " pakt " + maaltijd.toString());
			try {
				System.out.println("Ober " + naam + " loopt naar tafel " + maaltijd.getTafelnummer());
				Thread.sleep(looptijd);
				System.out.println("Ober " + naam + " serveert " + maaltijd.toString());
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			System.out.println("Ober " + naam + " loopt terug naar de uitgiftebalie");
			try {
				Thread.sleep(looptijd);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Instantieert een nieuwe Thread en start deze
	 */
	public void startServeren() {
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("Ober " + naam + " is gestart met serveren");
	}

	/**
	 * laat de ober stoppen met koken
	 */
	public void stopServeren() {
		this.stopserveren = true;
	}

	/**
	 * laat een ober starten met serveren in een thread
	 */
	@Override
	public void run() {
		while (!stopserveren) {
			if (balie.pakMaaltijd() != null) {
				serveer(balie.pakMaaltijd());
			} else {
				System.out.println("Ober " + naam + " wacht op de volgende bestelling");
				try {
					Thread.sleep(WACHTTIJD);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		System.out.println("Ober " + naam + " is gestopt met serveren");
	}
}
