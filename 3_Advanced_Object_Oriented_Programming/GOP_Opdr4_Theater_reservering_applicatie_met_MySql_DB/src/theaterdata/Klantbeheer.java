package theaterdata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import theater.Klant;
import theater.TheaterException;

/**
 * Deze klasse beheert klanten.
 */
public class Klantbeheer {

	private static PreparedStatement prepMaxNR;
	private static PreparedStatement prepKlant;
	private static PreparedStatement prepVoegToe;

	/**
	 * Initialiseert de klanten.
	 * 
	 * @throws TheaterException
	 *
	 */
	public static void init() throws TheaterException {

		if (Connectiebeheer.con == null) {
			Connectiebeheer.openDB();
		}

		if (Connectiebeheer.con != null) {
			initPrepStatements();
		}

	}

	// private methode: initialiseert de opdrachten voor de database
	private static void initPrepStatements() throws TheaterException {

		try {
			String sql = "SELECT MAX(klantnummer) FROM Klant ";
			prepMaxNR = Connectiebeheer.con.prepareCall(sql);

			String sql2 = "SELECT klantnummer FROM klant WHERE naam = ? AND telefoon = ?";
			prepKlant = Connectiebeheer.con.prepareStatement(sql2);

			String sql3 = "INSERT INTO Klant (klantnummer, naam, telefoon) values (?,?,?)";
			prepVoegToe = Connectiebeheer.con.prepareStatement(sql3);

		} catch (SQLException e) {
			Connectiebeheer.closeDB();
			throw new TheaterException("fout bij het uitvoeren van een SQL opdracht");
		}

	}

	/**
	 * Genereert het volgende beschikbare klantnummer.
	 * 
	 * @return nieuw klantnummer
	 * @throws TheaterException
	 */
	public static int getVolgendKlantNummer() throws TheaterException {
		int hoogsteKlantnummer = -1;
		try {
			ResultSet res = prepMaxNR.executeQuery();
			if (res.next())
				hoogsteKlantnummer = res.getInt(1);

		} catch (SQLException e) {
			throw new TheaterException("MaxNR ophalen mislukt");
		}
		return ++hoogsteKlantnummer;
	}

	/**
	 * 
	 * Geeft een klant met de gegeven naam en het gegeven telefoonnummer Als de
	 * klant al in de lijst zat, wordt die teruggegeven; anders wordt er een nieuwe
	 * klant gemaakt.
	 * 
	 * @param naam           van de klant
	 * @param telefoonnummer van de klant
	 * @return klant object
	 * @throws TheaterException
	 */
	public static Klant geefKlant(String naam, String telefoon) throws TheaterException {
		// zoek een klant
		Klant klant = zoekKlant(naam, telefoon);

		// als deze niet gevonden is (null) maak een nieuwe klant aan.
		if (klant == null) {
			klant = nieuweKlant(naam, telefoon);
		}
		// geef het klant object terug
		return klant;
	}

	/**
	 * Zoekt klant met gegeven naam in de lijst met klanten.
	 * 
	 * @param naam     naam van te zoeken klant
	 * @param telefoon telefoonnummer van te zoeken klant
	 * @return de klant of null wanneer klant niet is gevonden
	 * @throws TheaterException
	 */
	private static Klant zoekKlant(String naam, String telefoon) throws TheaterException {
		// vraag de klant op in de DB op basis van een naam en telefoonnummer
		try {
			prepKlant.setString(1, naam);
			prepKlant.setString(2, telefoon);
			ResultSet reszoek = prepKlant.executeQuery();

			while (reszoek.next()) {
				int klantnummer = reszoek.getInt("klantnummer");
				return new Klant(klantnummer, telefoon, naam);
			}
		} catch (SQLException e) {
			throw new TheaterException("Klant ophalen op basis van naam en telefoon niet gelukt.");
		}
		// geef null terug als de klant niet gevonden is
		return null;
	}

	/**
	 * Voegt een nieuwe klant toe aan theater.
	 * 
	 * @param naam     naam van de nieuwe klant
	 * @param telefoon telefoonnummer van de nieuwe klant
	 * @throws TheaterException
	 * 
	 */
	private static Klant nieuweKlant(String naam, String telefoon) throws TheaterException {
		// volgend klant nummer
		int nextKN = getVolgendKlantNummer();

		// check eerst of het volgende klantnummer ophalen gelukt voor het wegschrijven
		// van een nieuwe klant
		if (nextKN != -1) {

			// schrijf een nieuwe klant weg
			try {
				prepVoegToe.setInt(1, nextKN);
				prepVoegToe.setString(2, naam);
				prepVoegToe.setString(3, telefoon);
				prepVoegToe.executeUpdate();

			} catch (SQLException e) {
				throw new TheaterException("Wegschrijven nieuwe klant niet gelukt.");
			}
			// geef het nieuwe klant object terug nadat deze is bijgeschreven in de DB
			return new Klant(nextKN, naam, telefoon);

		}
		return null;
	}

}
