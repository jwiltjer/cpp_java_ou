package studentadmintest;

import studentadmin.StudentAdmin;
import studentadmin.StudentAdminException;
/**
 * klasse test op fout afhandeling welke niet via de GUI zijn te testen.
 *
 * @author jwiltjer
 *
 *
 */
public class Test {

	public static void main(String[] args) {
		StudentAdmin sa = new StudentAdmin();

		// test 1 student inschrijven voor niet bestaande opleiding:
		try {
			sa.addStudent("Jeffrey", "C#");
		} catch (StudentAdminException e) {
			System.out.println("test 1 geslaagd");
			e.printStackTrace();
		} 

		// test 2 student inschrijven voor een bestaande opleiding
		try {
			sa.addStudent("Jan", "Wiskunde");
		} catch (StudentAdminException e) {
			System.out.println("test niet geslaagd");
			e.printStackTrace();
		} finally {
			System.out.println("test 2 geslaagd");
		}

		// test 3 bestaande student opnieuw inschrijven voor niet-bestaande opleiding
		try {
			sa.addStudent("Jan", "JavaScript");
		} catch (StudentAdminException e) {
			System.out.println("test 3 geslaagd");
			e.printStackTrace();
		} 
	}

}
