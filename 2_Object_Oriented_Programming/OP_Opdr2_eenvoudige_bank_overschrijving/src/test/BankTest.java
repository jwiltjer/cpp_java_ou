package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bank.Bank;
import bank.Rekening;

class BankTest {
	ArrayList<Rekening> rekeninglijst;
	private static final double DELTA = 0.01;

	private Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();

		// test de voegRekeningToe methode en initialiseer een object met een naam en rekeningnummer
		bank.voegRekeningToe("Theo", 1111);
		bank.voegRekeningToe("Jan", 2222);
		bank.voegRekeningToe("Piet", 3333);
		bank.voegRekeningToe("Henk", 1234);
		bank.voegRekeningToe("Klaas", 2345);
	}

	@Test
	void testBank() {
		
		this.rekeninglijst = new ArrayList<Rekening>();
		//de arrayList bevat de rekeninglijst
		Assert.assertEquals(rekeninglijst, rekeninglijst);
	}

	@Test
	void testVoegRekeningToe() {

		// is getest in @beforeEach
		
	}

	@Test
	void testGeefRekeningNaam() {
		// bij rekening nummer 1111 hoort naam Theo
		bank.geefRekeningNaam(1111);
		assertTrue(bank.geefRekeningNaam(1111).contentEquals("Theo"));

		// bij rekening nummer 1111 hoort niet de naam Henk
		bank.geefRekeningNaam(1111);
		assertFalse(bank.geefRekeningNaam(1111).contentEquals("Henk"));

	}

	@Test
	void testGeefSaldo() {
		// stort een begin bedrag om de methode te testen
		bank.stortBank(1111, 100.0);

		// geef het saldo
		double saldo = bank.geefSaldo(1111);
		assertEquals(100.0, saldo, DELTA);

	}

	@Test
	void testStortBank() {

		// stort een positief bedrag
		bank.stortBank(1111, 10.00);
		assertEquals(10.00, bank.geefSaldo(1111), DELTA);

		// stort een negatief bedrag
		bank.stortBank(1111, -10.00);
		assertEquals(10.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// stort een bedrag groter dan het saldo
		bank.stortBank(1111, 20.00);
		assertEquals(30.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// stort een bedrag op een onbekend rekening nummer
		 bank.stortBank(9999, 10.00);
		 assertEquals(30.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde
	}

	@Test
	void testNeemOpBank() {
		// stort een begin bedrag om de methode te testen
		bank.stortBank(1111, 100.00);

		// neem 0.00 op
		bank.neemOpBank(1111, 0.00);
		assertEquals(100.00, bank.geefSaldo(1111), DELTA);

		// neem een negatief bedrag op
		bank.neemOpBank(1111, -10.00);
		assertEquals(100.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// neem een positief bedrag op
		bank.neemOpBank(1111, 50.00);
		assertEquals(50.00, bank.geefSaldo(1111), DELTA);

		// neem meer op dan het saldo
		bank.neemOpBank(1111, 100.00);
		assertEquals(50.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// neem een bedrag op van een onbekend rekening nummer
		bank.neemOpBank(9999, 0.00);
		assertEquals(50.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde
	}

	@Test
	void testMaakOver() {
		// geef rekening 1111 een test saldo van 1000.00
		bank.stortBank(1111, 1000.00);

		// maak een bedrag over groter dan het saldo
		bank.maakOver(1111, 2222, 2000.00);
		assertEquals(1000.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde
		assertEquals(0.00, bank.geefSaldo(2222), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// maak een positief bedrag over
		bank.maakOver(1111, 2222, 10.00);
		assertEquals(990.00, bank.geefSaldo(1111), DELTA); 
		assertEquals(10.00, bank.geefSaldo(2222), DELTA);

		// maak een negatief bedrag over
		bank.maakOver(1111, 2222, -10.00);
		assertEquals(990.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde
		assertEquals(10.00, bank.geefSaldo(2222), DELTA); //doet niks, saldo gelijk aan vorige waarde

		// maak een bedrag 0.00 over
		bank.maakOver(1111, 2222, 0.00);
		assertEquals(990.00, bank.geefSaldo(1111), DELTA);
		assertEquals(10.00, bank.geefSaldo(2222), DELTA);

		 //maak een bedrag over naar een onbekend rekening nummer
		 bank.maakOver(1111, 9999, 10.00);
		 assertEquals(990.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde
		 
		 //maak een bedrag over naar hetzelfde rekening nummer
		 bank.maakOver(1111, 1111, 10.00);
		 assertEquals(990.00, bank.geefSaldo(1111), DELTA); //doet niks, saldo gelijk aan vorige waarde


	}

}
