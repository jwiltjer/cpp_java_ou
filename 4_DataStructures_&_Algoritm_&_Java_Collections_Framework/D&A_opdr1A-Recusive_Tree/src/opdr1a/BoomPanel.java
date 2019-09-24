package opdr1a;

/**
 * Klasse voor het panel waarop de boom wordt getekend.
 * De lengte van de stam, de hoek tussen de takken
 * en de diepte van de boom zijn parameters.
 * Er wordt gebruik gemaakt van een pen die relatief tekent
 * (vanuit een huidige positie en richting).
 */

import java.awt.*;
import javax.swing.*;

public class BoomPanel extends JPanel {
	// attributen

	private static final double FACTOR = 0.7; // de verkortingsfactor van
	// de takken

	private double hoek; // hoek tussen de takken
	private int diepte; // de diepte van de boom
	private int stamLengte; // lengte van de stam
	private Pen pen; // de pen waarmee getekend wordt

	/**
	 * Maak een nieuw BoomPanel.
	 * 
	 * @param stamlengte, de initiële lengte van de stam
	 * @param hoek,       de hoek tussen de takken
	 * @param diepte,     de diepte van de boom.
	 */
	BoomPanel(int stamLengte, int hoek, int diepte) {
		this.stamLengte = stamLengte;
		this.hoek = hoek;
		this.diepte = diepte;
		this.setSize(350, 300);
		this.setBackground(Color.white);
	}

	/**
	 * paintComponent maakt een nieuwe pen en roept de recursieve tekenmethode
	 * tekenBoom aan.
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		// Maak een nieuwe pen, die iets van de rand onderin het midden van
		// het panel staat en naar boven wijst.
		pen = new Pen(g, getSize().width / 2, getSize().height - 20, 270);
		pen.setKleur(Color.red);

		// hier moet de aanroep naar tekenBoom worden ingevuld.

		tekenBoom(diepte, stamLengte);
	}

	// Hier moet de methode tekenBoom worden ingevuld.
	public void tekenBoom(int n, int len) {
		if (n == 1) {
			pen.aan();
			pen.vooruit(len);			//teken stam
			pen.uit();
			pen.vooruit(-len);			//loop zelfde lengte terug
		
		} else {
			pen.aan();					//rode takken
			pen.vooruit(len);			//teken stam
			pen.draai(hoek / 2);		// draai rechtom
			tekenBoom(n - 1, (int) (len * FACTOR));  //teken rechter blad //pen blijft op snijpunt staan
			pen.draai(-hoek);			//draai linksom
		
			pen.setKleur(Color.BLUE);	//blauwe (recursieve) takken	
			tekenBoom(n - 1, (int) (len * FACTOR)); //teken linker blad
			pen.draai(hoek / 2);  		//zet hoek terug op de stam
			pen.uit();					
			pen.vooruit(-len);			//loop zelfde lengte terug over de stam
			
		}
	}
}
