package opdr2;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.AbstractListModel;

/**
 * Beheert de synoniemen ingevoerd in de GUI
 * 
 * @author jwiltjer
 *
 */
public class SynoniemenBeheer extends AbstractListModel<Object> {
	private static final long serialVersionUID = 1L;

	private TreeMap<String, TreeSet<String>> tree_map = null;

	/**
	 * instantieerd een nieuwe klasse en initialiseert een Treemap met een string
	 * als key en array van string als value
	 */
	public SynoniemenBeheer() {
		tree_map = new TreeMap<String, TreeSet<String>>();
	}

	/**
	 * methode voegt synoniemen toe aan een collectie van Strings op basis van een
	 * woord als key en de synoniemen als values
	 * 
	 * @param key    van de referentie naar de synoniemen
	 * @param values de synoniemen van het woord(key)
	 */
	public void addSynoniem(String key, String values) {
		TreeSet<String> value = new TreeSet<String>(new MyComparator());
		List<String> input = Arrays.asList(values.split("\\s"));
		value.addAll(input);
		tree_map.put(key, value);
	}

	/**
	 * getter van de keys(woorden) in de collectie
	 * 
	 * @return String array met alle keys
	 */
	public String[] getKeys() {
		return tree_map.keySet().toArray(new String[1]);
	}

	/**
	 * getter van de values(synoniemen) in de collectie op basis van een key
	 * 
	 * @param keyIndex de index van de key(woord)
	 * @return String array met alle values(synoniemen)
	 */
	public String[] displayValues(int keyIndex) {
		return tree_map.get(getElementAt(keyIndex)).toArray(new String[1]);
	}

	/**
	 * implementatie van getElementAt om een Object terug te geven die hoort bij het
	 * geselecteerde woord/keyIndex in de JList in de GUI.
	 */
	@Override
	public Object getElementAt(int index) {
		return tree_map.keySet().toArray(new String[1])[index];
	}

	/**
	 * geeft grote van de Treemap terug
	 */
	@Override
	public int getSize() {
		return tree_map.size();
	}

	/**
	 * kijkt of de key(woord) al in de collectie voorkomt
	 * 
	 * @param element de key
	 * @return true als het woord al voorkomt anders false
	 */
	public boolean contains(String key) {
		return tree_map.containsKey(key);
	}

}