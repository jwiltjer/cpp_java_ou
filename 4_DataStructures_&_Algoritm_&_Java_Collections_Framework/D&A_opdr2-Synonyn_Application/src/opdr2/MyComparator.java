package opdr2;

import java.util.Comparator;

/**
 * Klasse zorgt voor de sortering van Strings, eerst op lengte en dan leximaal
 * 
 * @author jwiltjer
 *
 */
public class MyComparator implements Comparator<String> {
	/**
	 * methode om de sortering uit te voeren eerst op lengte en dan leximaal
	 */
	@Override
	public int compare(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return 0;
		}
		if (s1.length() > s2.length()) {
			return 1;
		} else if (s1.length() < s2.length()) {
			return -1;
		}
		return s1.compareToIgnoreCase(s2);
	}
}
