package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import vector.Vector;

class VectorTest {

	Vector vector1;
	Vector vector2;
	Vector vector3;
	Vector vector4;
	Vector vector5;
	Vector vector6;
	

	private static final double DELTA = 0.1; // kleine DELTA gekozen met opzet.

	@BeforeEach
	public void setUp() {
		vector1 = new Vector(3, 4);
		vector2 = new Vector(0, 8);
		vector3 = new Vector(-3, -4);
		vector4 = new Vector(0, 0);
		vector5 = new Vector(1, 7);
		vector6 = new Vector(-2, -4);
		
		
	}

	/*
	 * Test de lengte bij de vector met vectoren groter kleiner en gelijk aan 0.
	 */
	@Test
	public void TestgetLength() {

		assertEquals(5, vector1.getLength(), DELTA); // vector groter dan 0

		assertEquals(8, vector2.getLength(), DELTA); // vector met xas van 0

		assertEquals(5, vector3.getLength(), DELTA); // vector kleiner dan 0

		assertEquals(0, vector4.getLength(), DELTA); // vector van 0

		assertEquals(7.0, vector5.getLength(), DELTA);
	}

	@Test
	public void testGetAfstand() {

		assertEquals(10, vector1.getAfstand(vector3), DELTA);
		assertEquals(3.6, vector1.getAfstand(vector5), DELTA);
	}

	@Test
	public void testVectorplus() {
		// test met 2 positieve vectoren
		Vector vectorPlus = vector1.plus(vector2);
		assertEquals(3, vectorPlus.x, DELTA);
		assertEquals(12, vectorPlus.y, DELTA);

		// test met een negatieve vector en een 0.0 vector
		vectorPlus = vector3.plus(vector4);
		assertEquals(-3, vectorPlus.x, DELTA);
		assertEquals(-4, vectorPlus.y, DELTA);

		// test met een negatieve vector en een positieve vector
		vectorPlus = vector3.plus(vector5);
		assertEquals(-2, vectorPlus.x, DELTA);
		assertEquals(3, vectorPlus.y, DELTA);

		// test met 2 negatieve vectoren
		vectorPlus = vector3.plus(vector6);
		assertEquals(-5, vectorPlus.x, DELTA);
		assertEquals(-8, vectorPlus.y, DELTA);

	}

	@Test
	public void testCopy() {
		Vector kopie = vector1.copy();
		assertEquals(3, kopie.x, DELTA);
		assertEquals(4, kopie.y, DELTA);
	}

	@Test
	public void testEquals() {
		Vector equals = new Vector(0, 0);
		assertFalse(vector1.equals(vector2));
		assertFalse(vector1.equals(vector4));
		assertTrue(vector4.equals(equals));

	}

	@Test
	public void testMaal() {
		Vector maal = vector1.maal(4.0);
		assertEquals(12, maal.x, DELTA);
		assertEquals(16, maal.y, DELTA);

		maal = vector6.maal(3.0);
		assertEquals(-6, maal.x, DELTA);
		assertEquals(-12, maal.y, DELTA);

		maal = vector4.maal(3.0);
		assertEquals(0, maal.x, DELTA);
		assertEquals(0, maal.y, DELTA);

	}
	
	@Test 
	public void testZelfdeRichting()  {
		Vector vector7 = new Vector(1,2);
		assertTrue(vector1.heeftZelfdeRichting(vector3));
		assertFalse(vector2.heeftZelfdeRichting(vector4));
		assertTrue(vector6.heeftZelfdeRichting(vector7));
		
	}

	@Test 
	public void testGetInproduct()  {
	
	assertEquals(-25, vector1.getInproduct(vector3), DELTA);
	assertEquals( 56, vector2.getInproduct(vector5), DELTA);
	assertEquals(  0, vector4.getInproduct(vector6), DELTA);
	
	}
	@Test
	public void testGetHoek()  {
		System.out.println(Math.atan2(-4,-3)); // Atan2(double y, double x) Returns the angle theta from the conversion of rectangular coordinates (x, y)
		
		assertEquals(0.92, vector1.getHoek(), DELTA);
		assertEquals(1.57, vector2.getHoek(), DELTA);
		assertEquals(0.92, vector3.getHoek(), DELTA);
		assertEquals(1.42, vector5.getHoek(), DELTA);
		
	}
}
