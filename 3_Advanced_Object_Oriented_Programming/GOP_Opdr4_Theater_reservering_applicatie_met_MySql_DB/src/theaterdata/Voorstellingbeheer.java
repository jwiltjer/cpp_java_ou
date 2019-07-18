package theaterdata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import theater.Klant;
import theater.TheaterException;
import theater.Voorstelling;

/**
 * Klasse die met voorstellingen beheert. Op elke datum is er maar één
 * voorstelling.
 */
public class Voorstellingbeheer {

	private static PreparedStatement prepDataVoorstellingen;
	private static PreparedStatement prepNaamVoorstellingen;
	private static PreparedStatement prepBezetting;
	private static PreparedStatement prepKlant;
	private static PreparedStatement prepNieuweBezetting;

	/**
	 * Vult voorstellingbeheer met een aantal voorstellingen.
	 * 
	 * @throws TheaterException
	 */
	public static void init() throws TheaterException {

		if (Connectiebeheer.con == null) {
			Connectiebeheer.openDB();
			throw new TheaterException("Database verbinding tot stand brengen niet gelukt");
		}
		if (Connectiebeheer.con != null) {
			initPrepStatements();
		}

	}

	// private methode om de prepared statement te initialiseren met opdrachten voor
	// de MySQL database
	private static void initPrepStatements() throws TheaterException {

		try {
			String sql1 = "SELECT datum, naam FROM Voorstelling ";
			prepDataVoorstellingen = Connectiebeheer.con.prepareStatement(sql1);

			String sql2 = "SELECT naam, datum FROM voorstelling WHERE datum = ? ";
			prepNaamVoorstellingen = Connectiebeheer.con.prepareStatement(sql2);

			String sql3 = "SELECT rijnummer, stoelnummer, klant FROM bezetting WHERE voorstelling = ? ";
			prepBezetting = Connectiebeheer.con.prepareStatement(sql3);

			String sql4 = "SELECT naam, telefoon FROM klant WHERE klantnummer = ? ";
			prepKlant = Connectiebeheer.con.prepareStatement(sql4);

			String sql5 = "INSERT INTO Bezetting (voorstelling, rijnummer, stoelnummer, klant) values (?,?,?,?)";
			prepNieuweBezetting = Connectiebeheer.con.prepareStatement(sql5);

		} catch (SQLException e) {
			Connectiebeheer.closeDB();
			throw new TheaterException("fout bij het uitvoeren van een SQL opdracht");
		}
	}

	/**
	 * Levert alle data op waarop voorstellingen zijn (voor zover die data in de
	 * toekomst liggen).
	 * 
	 * @return lijst met data van voorstellingen
	 * @throws TheaterException
	 */
	public static ArrayList<GregorianCalendar> geefVoorstellingsData() throws TheaterException {
		ArrayList<GregorianCalendar> datumlijst = new ArrayList<GregorianCalendar>();
		if (prepDataVoorstellingen != null) {
			try {
				ResultSet res = prepDataVoorstellingen.executeQuery();

				while (res.next()) {
					java.sql.Date sqlDatum = res.getDate("datum");
					GregorianCalendar datum = new GregorianCalendar();
					datum.setTimeInMillis(sqlDatum.getTime());

					GregorianCalendar nu = new GregorianCalendar();
					if (datum.after(nu)) {
						datumlijst.add(datum);
					}
				}
			} catch (SQLException e) {
				throw new TheaterException("Data voorstelling kan niet worden opgehaald");

			}
			return datumlijst;
		} else {
			throw new TheaterException("Er zijn geen toekomstige voorstellingen");
		}
	}

	/**
	 * Zoekt een voorstelling op de gegeven datum.
	 * 
	 * @param datum
	 * @return een voorstelling op de gegeven datum of null wanneer die voorstelling
	 *         er niet is.
	 * @throws TheaterException
	 */

	public static Voorstelling geefVoorstelling(GregorianCalendar datum) throws TheaterException {

		Voorstelling voorstelling = null;
		java.sql.Date sqlDate0 = new java.sql.Date(datum.getTimeInMillis());

		try {
			prepNaamVoorstellingen.setDate(1, sqlDate0);
			ResultSet res = prepNaamVoorstellingen.executeQuery();

			while (res.next()) {
				String naam = res.getString("naam");
				java.sql.Date sqlDate1 = res.getDate("datum");

				GregorianCalendar gdatum = new GregorianCalendar();
				gdatum.setTimeInMillis(sqlDate1.getTime());

				voorstelling = new Voorstelling(naam, datum);
			}

		} catch (SQLException e) {
			throw new TheaterException("inlezen naam niet gelukt");
		}

		try {
			if (voorstelling != null) {
				// ophalen bezetting gegevens van de voorstelling op de gegeven datum
				prepBezetting.setDate(1, sqlDate0);
				ResultSet res1 = prepBezetting.executeQuery();

				String naamKlant = "";
				String telefoonKlant = "";
				while (res1.next()) {
					int klantnr = res1.getInt("klant");
					int rijnr = res1.getInt("rijnummer");
					int stoelnr = res1.getInt("stoelnummer");

					// ophalen klantgegevens op basis van het klantnummer uit de bezetting
					prepKlant.setInt(1, klantnr);
					ResultSet res2 = prepKlant.executeQuery();

					while (res2.next()) {
						naamKlant = res2.getString("naam");
						telefoonKlant = res2.getString("telefoon");
						voorstelling.veranderReservering(rijnr, stoelnr);

						// plaats klant
						voorstelling.plaatsKlant(rijnr, stoelnr, new Klant(klantnr, naamKlant, telefoonKlant));
					}
				}
			}

		} catch (SQLException e) {
			throw new TheaterException("Reserveringen inlezen niet gelukt.");

		}
		return voorstelling;

	}

	/**
	 * Schrijft een nieuwe bezetting weg naar de database
	 * 
	 * @param datum       van van voorstelling
	 * @param rijnummer   in de zaal die bij de te reserveren stoel hoort
	 * @param stoelnummer in de zaal die bij de te reserveren stoel hoort
	 * @param klantnummer van de klant die bij de te reserveren stoel hoort
	 * @throws TheaterException
	 */
	public static void nieuweBezetting(GregorianCalendar datum, int rijnummer, int stoelnummer, int klantnummer)
			throws TheaterException {

		java.sql.Date sqlDate2 = new java.sql.Date(datum.getTimeInMillis());
		try {

			prepNieuweBezetting.setDate(1, sqlDate2);
			prepNieuweBezetting.setInt(2, rijnummer);
			prepNieuweBezetting.setInt(3, stoelnummer);
			prepNieuweBezetting.setInt(4, klantnummer);

			prepNieuweBezetting.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new TheaterException("nieuwe bezetting wegschrijven niet gelukt");
		}
	}

}
