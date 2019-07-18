package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import bank.Rekening;

class RekeningTest {

	private static final double DELTA = 0.01;

	private Rekening rekening;

	@BeforeEach
	public void setUp() {
		rekening = new Rekening("jeffrey", 4591);
	}

	@Test
	void testGetNaam() {
		String a = rekening.getNaam();
		assertTrue(a.equals("jeffrey"));
	}

	@Test
	void testGetNummer() {

		// geeft het rekening nummer
		rekening.getNummer();
		assertEquals(4591, rekening.getNummer());
	}

	@Test
	void testGetSaldo() {

		//
		rekening.getSaldo();
		assertEquals(0.00, rekening.getSaldo());

		// saldo 10.00 na storting van 10.00
		rekening.stort(10.00);
		assertEquals(10.00, rekening.getSaldo());
	}

	@Test

	void testStort() {
		// stort 0.00
		rekening.stort(0.00);
		assertEquals(0.00, rekening.getSaldo(), DELTA);

		// stort een negatief bedrag
		rekening.stort(-10.00);
		assertEquals(0.00, rekening.getSaldo(), DELTA);

		// stort een positief bedrag
		rekening.stort(100.00);
		assertEquals(100.00, rekening.getSaldo(), DELTA);

		// sort meer dan het saldo
		rekening.stort(200.00);
		assertEquals(300.00, rekening.getSaldo(), DELTA);
	}

	@Test
	void testNeemOp() {
		// stort een begin bedrag om de methode te testen
		rekening.stort(10.00);

		// neem 0.00 op
		rekening.neemOp(0.00);
		assertEquals(10.00, rekening.getSaldo(), DELTA);

		// neem een negatief bedrag op
		rekening.neemOp(-10.00);
		assertEquals(10.00, rekening.getSaldo(), DELTA); // zou niet mogen en saldo blijft gelijk

		// neem een positief bedrag op
		rekening.neemOp(3.00);
		assertEquals(7.00, rekening.getSaldo(), DELTA);

		// neem meer op dan het saldo
		rekening.neemOp(100.00);
		assertEquals(7.00, rekening.getSaldo(), DELTA); // zou niet mogen en het saldo blijft gelijk
	}

}
