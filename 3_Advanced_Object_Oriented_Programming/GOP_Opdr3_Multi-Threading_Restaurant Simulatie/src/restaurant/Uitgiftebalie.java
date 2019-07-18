package restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse representeert een Uitgiftebalie
 * 
 * @author jwiltjer
 *
 */
public class Uitgiftebalie {
	private List<Maaltijd> bestellingen;

	/**
	 * constructor: instantieert een Uitgiftebalie en maakt een ArrayList van
	 * Maaltijden aan.
	 */
	public Uitgiftebalie() {
		this.bestellingen = new ArrayList<Maaltijd>();
	}

	/**
	 * pakt de Maaltijd uit de ArrayList die het langst in de lijst staat
	 * 
	 * @return langst wachtende Maaltijd op de uitgifte balie
	 */
	public synchronized Maaltijd pakMaaltijd() {
		Maaltijd maaltijd = null;
		if (bestellingen.size() > 0) {
			maaltijd = bestellingen.get(0);
			bestellingen.remove(0);
		}
		return maaltijd;
	}

	/**
	 * plaatst een maaltijd achteraan in de ArrayList van Maaltijden
	 * 
	 * @param maaltijd die op de uitgiftebalie geplaatst wordt
	 */
	public synchronized void plaatsMaaltijd(Maaltijd maaltijd) {
		bestellingen.add(maaltijd);
	}
	
	public List<Maaltijd> getList (){
		return bestellingen;
	}

}
