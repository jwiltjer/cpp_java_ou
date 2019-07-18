package theaterdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import theater.TheaterException;

/**
 * Beheert de connectie met de database. Bevat methoden voor openen en sluiten
 * van connectie met database, en voor opvragen van de connectie.
 * 
 * @author Open Universiteit
 */
public class Connectiebeheer {
	public static Connection con = null;

	/**
	 * Maakt een connectie met de database en initialiseert Klantbeheer en
	 * VoostellingBeheer.
	 * 
	 * @throws TheaterException       als de initialisatie mislukt.
	 * @throws SQLException           als het verbinding maken mislukt is
	 * @throws ClassNotFoundException als de driver niet kan worden geladen
	 */
	public static void openDB() throws TheaterException {

		try {
			Class.forName(DBConst.DRIVERNAAM);
			String url = DBConst.URL;
			con = DriverManager.getConnection(url, DBConst.GEBRUIKERSNAAM, DBConst.WACHTWOORD);
		} catch (ClassNotFoundException e) {
			throw new TheaterException("Driver niet geladen");

		} catch (SQLException e) {
			throw new TheaterException("Verbinding met de database maken mislukt");
		}

	}

	/**
	 * Sluit de connectie met de database
	 */
	public static void closeDB() throws TheaterException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new TheaterException("Verbinding sluiten mislukt");
			}
		}
	}
}
