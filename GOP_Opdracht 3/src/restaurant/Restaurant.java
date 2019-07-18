package restaurant;

/**
 * Klasse representeert een restaurant
 * 
 * @author jwiltjer
 *
 */
public class Restaurant {
	public static final int AANTAL_TAFELS = 10;
	private static final int SIMULATIETIJD = 120000;

	public Uitgiftebalie balie = null;

	/**
	 * constructor: maakt een nieuw restaurant en instantieert een uitgiftebalie
	 */
	public Restaurant() {
		this.balie = new Uitgiftebalie();
		
	}

	/**
	 * Main methode: start het restaurant en zorgt dat de koks en obers beginnen met
	 * werken in verschillende threads en stoppen nadat de simulatietijd verstreken
	 * is.
	 * 
	 * @param args niet gebruikt
	 */
	public static void main(String[] args) {
		Restaurant ou = new Restaurant();

		Kok kok1 = new Kok("Jan", ou.balie);
		Kok kok2 = new Kok("Henk", ou.balie);
		Kok kok3 = new Kok("Klaas", ou.balie);

		Ober ober1 = new Ober("Piet", ou.balie);
		Ober ober2 = new Ober("Kees", ou.balie);

		kok1.startKoken();
		kok2.startKoken();
		kok3.startKoken();

		ober1.startServeren();
		ober2.startServeren();

		long t = System.currentTimeMillis();
		long einde = t + SIMULATIETIJD;
		while (System.currentTimeMillis() < einde) {
			try {
				Thread.sleep(SIMULATIETIJD);
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		
		kok1.stopKoken();
		kok2.stopKoken();
		kok3.stopKoken();

		ober1.stopServeren();
		ober2.stopServeren();
		int maaltijdenOver = ou.balie.getList().size();
		System.out.println(maaltijdenOver);
	}

}
