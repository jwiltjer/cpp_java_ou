package opdr1a;

/*
 * Klasse die een pen representeert.
 * Tekenen in Java is absoluut: aan de teken-
 * methoden van Graphics moeten coordinaten meegegeven.
 * Met behulp van deze klasse kan relatief (vanuit een
 * huidige positie) getekend worden. Er zijn methode om
 * de pen op te tillen, neer te zetten, over een gegeven
 * afstand te verplaatsen, en de pen te draaien.
 */

import java.awt.*;

public class Pen {
  // attributen

  private double x, y;
  // de huidige coordinaten van de pen in het stelsel
  // van Java (oorsprong linksboven)
  private double richting;
  // penrichting in radiaal; 0 rad is 3 uur;
  // positief = met de klok mee.
  private boolean neer = true;
  // is de pen neer?
  private Graphics context;

  // De grafische context waarin getekend wordt

  /**
   * Maak een nieuwe pen die tekent in een gegeven grafisch context. De pen
   * staat na creatie in de oorsprong, wijst naar rechts en heeft tekenkleur
   * zwart.
   * 
   * @param g
   *                de grafische context van de pen
   */
  public Pen(Graphics g) {
    x = 0;
    y = 0;
    richting = 0;
    context = g;
    context.setColor(Color.black);
  }

  /**
   * Maak een nieuwe pen die tekent in een gegeven grafisch context en die
   * begint op een gegeven punt en in een gegeven richting. De pen heeft
   * tekenkleur zwart.
   * 
   * @param g
   *                de grafische context van de pen
   * @param x
   *                de x-coördinaat van de penpositie
   * @param y
   *                de y-coördinaat van de penpositie
   * @param r
   *                de richting waarin de pen wijst, in graden.
   */
  public Pen(Graphics g, int x, int y, double r) {
    context = g;
    context.setColor(Color.black);
    this.x = x;
    this.y = y;
    richting = (r / 180) * Math.PI;
  }

  /**
   * Wijzig de tekenkleur
   * 
   * @param k
   *                de nieuwe tekenkleur
   */
  public void setKleur(Color k) {
    context.setColor(k);
  }

  /**
   * Zet de pen op het tekenblad: penbewegingen leiden tot zichtbare lijnen
   */
  public void aan() {
    neer = true;
  }

  /**
   * Haal de pen van het tekenblad: penbewegingen zijn onzichtbaar
   */
  public void uit() {
    neer = false;
  }

  /**
   * Beweeg de pen d pixels in de huidige tekenrichting. Als d < 0, gaat de pen
   * achteruit.
   * 
   * @param d
   *                het aantal pixels dat de pen moet bewegen (double om bij
   *                schuine lijnen zo nauwkeurig mogelijk te zijn)
   */
  public void vooruit(double d) {
    double xnieuw = x + d * Math.cos(richting);
    double ynieuw = y + d * Math.sin(richting);
    if (neer)
      context.drawLine((int) Math.round(x), (int) Math.round(y), (int) Math
          .round(xnieuw), (int) Math.round(ynieuw));
    x = xnieuw;
    y = ynieuw;
  }

  /**
   * Draai de pen over de gegeven hoek naar rechts. Als hoek < 0, draait de pen
   * naar links.
   * 
   * @param hoek
   *                de hoek waarover gedraaid wordt, in graden.
   */
  public void draai(double hoek) {
    richting = richting + (hoek / 180) * Math.PI;
  }

}