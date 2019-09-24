package opdr2;

import java.util.TreeMap;
import javax.swing.AbstractListModel;

public class SynoniemenBeheer extends AbstractListModel {
	private static final long serialVersionUID = 1L;

	private TreeMap<String, String[]> tree_map = null;

	public SynoniemenBeheer() {
		tree_map = new TreeMap<String, String[]>();
	}

	@Override
	public Object getElementAt(int index) {
		return tree_map.keySet().toArray()[index];
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public TreeMap<String, String[]> getTree_map() {
		return tree_map;
	}
	// contains methode toevoegen en controleren op dubbele invoer
	

}