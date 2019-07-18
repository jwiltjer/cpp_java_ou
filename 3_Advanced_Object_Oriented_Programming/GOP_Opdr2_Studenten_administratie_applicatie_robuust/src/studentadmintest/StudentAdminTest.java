package studentadmintest;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import studentadmin.StudentAdmin;
import studentadmin.StudentAdminException;

public class StudentAdminTest {
	StudentAdmin sa;

	@Before
	public void setUp() throws StudentAdminException {
		sa = new StudentAdmin();
		// geldige toevoeging om te testen
		sa.addStudent("Jeffrey", "CPP JAVA");
	}

	// lege opleiding toevoegen
	@Test
	public void legeOpleiding() {
		try {
			sa.addStudent("Jurgen", "");
			fail("StudentAdminException wordt niet gegooid");
		} catch (StudentAdminException e) {
			e.printStackTrace();
		}
	}

	// lege naam toevoegen
	@Test
	public void legeLeerling() {
		try {
			sa.addStudent("", "CPP JAVA");
			fail("StudentAdminException wordt niet gegooid");
		} catch (StudentAdminException e) {
			e.printStackTrace();
		}
	}

	// nieuwe leerling toevoegen, programma bestaat niet
	@Test

	public void nieuweLeerling() {
		try {
			sa.addStudent("Johan", "C#");
			fail("StudentAdminException wordt niet gegooid");
		} catch (StudentAdminException e) {
			e.printStackTrace();
		}
	}

	// bestaande leerling toevoegen, programma bestaan niet
	@Test
	public void bestaandeLeerling() {
		try {
			sa.addStudent("Jeffrey", "C#");
			fail("StudentAdminException wordt niet gegooid");
		} catch (StudentAdminException e) {
			e.printStackTrace();
		}
	}
	
	//verhoog punten van een Scholer met meer dan de maximale modules
	@Test
	public void meerModulesHalenDanMogelijk() {
		try {
			
			for(int i = 0; i <= 7; i++ ) { // 
				sa.verhoogModule("Jeffrey");
			}
		
			fail("StudentAdminException wordt niet gegooid");
		} catch (StudentAdminException e) {
			e.printStackTrace();
		}
	}

}
