package vector;

import static java.lang.Math.*;

/**
 * Deze klasse bevat reken methodes voor vectoren.
 *
 * @author Jeffrey Wiltjer
 */
public class Vector {
	public double x;// public omdat de test klasse in een aparte package staat
	public double y; // idem

	public static final double EPSILON = 1e-16;

	/**
	 * constructor van een vector object en kent de waarde x en de waarde y toe aan
	 * dit object
	 * 
	 * @param x 	de x coordinaat van deze vector
	 * @param y 	de y coordinaat van deze vector
	 * @return 		vector object met een geinitialiseerde x,y waarde.
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * methode om de lengte van deze vector te berekenen
	 * 
	 * @return 		de lengte van de vector
	 */

	public double getLength() {
		return sqrt((pow(x, 2.0) + (pow(y, 2.0))));
	}

	/**
	 * Berekent de afstand tussen deze vector en vector v
	 * 
	 * @param v		een vector object met een x en y coordinaat
	 * @return 		de afstand tussen de 2 vectoren
	 */
	public double getAfstand(Vector v) {
		return sqrt((pow((x - v.x), 2.0) + (pow((y - v.y), 2.0))));
	}

	/**
	 * methode om een nieuwe vector op te leveren die de som is van deze vector en
	 * v.
	 * 
	 * @param v 	een vector object met een x en y coordinaat
	 * @return 		een nieuw vector object met de som van de 2 vectoren
	 */
	public Vector plus(Vector v) {
		return new Vector((x + v.x), (y + v.y));
	}

	/**
	 * methode om een nieuwe vector op te leveren die dezelfde x- en y-waarde heeft
	 * als deze vector.
	 * 
	 * @return 		een nieuw object met dezelfde x en y waarden als het gekopieerde
	 *         		object.
	 */
	public Vector copy() {
		return new Vector(x, y);
	}

	/**
	 * methode om te bepalen of deze vector gelijk is aan v.
	 * 
	 * @param v 	een vector object met een x en y coordinaat
	 * @return 		een boolean die aangeeft of de vector v gelijk is aan deze vector.
	 */
	public boolean equals(Vector v) {
		return ((abs(x - v.x) < EPSILON) && ((abs(y - v.y)) < EPSILON));
	}

	/**
	 * methode om een nieuwe vector op te leveren verkregen door de x-waarde en de
	 * y-waarde van deze vector te vermenigvuldigen met d.
	 * 
	 * @param d		een double die gebruikt wordt om mee te worden vermenigvuldigt.
	 * @return 		een nieuw vector object met de nieuwe x en y waardes na
	 *         		vermenigvuldiging.
	 */
	public Vector maal(double d) {
		return new Vector(x * d, y * d);
	}

	/**
	 * methode om aan te geven of deze vector en v dezelfde richting hebben.
	 * 
	 * @param v 	een vector object met een x en y coordinaat
	 * @return 		een boolean die aangeeft of deze vector dezelfde richtung heeft als
	 *         		vector v.
	 */
	public boolean heeftZelfdeRichting(Vector v) {
		return (abs(v.x / x) - (v.y / y)) >= EPSILON;
	}

	/**
	 * methode om het inwendig product van deze vector en v te bepalen.
	 * @param v 	een vector object met een x en y coordinaat
	 * @return		het inwendig product van vector v
	 */
	public double getInproduct(Vector v) {
		return (x * v.x + y * v.y);
	}

	/**
	 * methode om de hoek van deze vector met de x-as te bepalen.
	 * @return		de hoek van theta in radialen 
	 */
	public double getHoek() {
		return atan(abs(y / x));
	}
}
