package opdr2;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class SynoniemenFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel woordLabel = null;
	private JLabel synoniemenLabel = null;
	private JScrollPane woordScrollPane = null;
	private JScrollPane synomiemenScrollPane = null;
	private JList woordList = null;
	private JList synoniemenList = null;
	private JTextField woordVeld = null;
	private JTextField synoniemenVeld = null;
	private JButton voegtoeKnop = null;
	private JLabel foutLabel = null;
	private SynoniemenBeheer synoniem = null;

	/**
	 * This is the default constructor
	 */
	public SynoniemenFrame() {
		super();
		initialize();
		mijnInit();
	}

	private void mijnInit() {
		synoniem = new SynoniemenBeheer();
	
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(294, 329);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * Onvolledieg event handler voor toevoegen. Er wordt gecontroleerd of een woord
	 * uit letters bestaat, en een woordenlijst uit woorden gescheiden door spaties.
	 */
	private void voegtoeKnopAction() {
		foutLabel.setText("");
		String woord = woordVeld.getText().trim();
		String alleSynoniemen = synoniemenVeld.getText().trim() + ' ';

		if (!Pattern.matches("[a-zA-Z]+", woord)) {
			foutLabel.setText("Woord bestaat niet uit letters");
			return;
		} else if (!Pattern.matches("([a-zA-Z]+ +)+", alleSynoniemen)) {
			foutLabel.setText("Synoniemenlijst bestaat niet uit woorden gescheiden door spaties");
			return;
		} else if (synoniemenVeld.getText().length() < 1) {
			foutLabel.setText("geen invoer");
		} else {
			String[] s = synoniemenVeld.getText().split("\\s+");

			synoniem.getTree_map().put(woordVeld.getText(), s);
			System.out.println(synoniem.getTree_map().keySet());

			ArrayList<String> str = new ArrayList<String>();
			for (String key : synoniem.getTree_map().keySet()) {
				str.add(key);
				System.out.println(key);
			}
			// weergave keys in woordlijst
			woordList = (JList) woordScrollPane.getViewport().getView();
			woordList.setListData(str.toArray());
		}

		// velden leeg maken na operatie
		woordVeld.setText("");
		synoniemenVeld.setText("");

	}

	/**
	 * Lege event handler voor klikken in woordList
	 * 
	 */
	private void woordListPressed() {
		int sel = woordList.getSelectedIndex();
		Object key = synoniem.getElementAt(sel);
		String[] synoLijst = synoniem.getTree_map().get(key);

		// naar gui schrijven
		synoniemenList = (JList) synomiemenScrollPane.getViewport().getView();
		synoniemenList.setListData(synoLijst);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			foutLabel = new JLabel();
			foutLabel.setBounds(new Rectangle(13, 252, 253, 25));
			foutLabel.setText("");
			synoniemenLabel = new JLabel();
			synoniemenLabel.setBounds(new Rectangle(154, 8, 109, 20));
			synoniemenLabel.setText("Synoniemen");
			woordLabel = new JLabel();
			woordLabel.setBounds(new Rectangle(14, 8, 109, 20));
			woordLabel.setText("Woorden");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(woordLabel, null);
			jContentPane.add(synoniemenLabel, null);
			jContentPane.add(getWoordScrollPane(), null);
			jContentPane.add(getSynomiemenScrollPane(), null);
			jContentPane.add(getWoordVeld(), null);
			jContentPane.add(getSynoniemenVeld(), null);
			jContentPane.add(foutLabel, null);
			jContentPane.add(getVoegtoeKnop(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes woordScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getWoordScrollPane() {
		if (woordScrollPane == null) {
			woordScrollPane = new JScrollPane();
			woordScrollPane.setBounds(new Rectangle(12, 40, 109, 125));
			woordScrollPane.setViewportView(getWoordList());
		}
		return woordScrollPane;
	}

	/**
	 * This method initializes synomiemenScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getSynomiemenScrollPane() {
		if (synomiemenScrollPane == null) {
			synomiemenScrollPane = new JScrollPane();
			synomiemenScrollPane.setBounds(new Rectangle(150, 40, 109, 125));
			synomiemenScrollPane.setViewportView(getSynoniemenList());
		}
		return synomiemenScrollPane;
	}

	/**
	 * This method initializes woordList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getWoordList() {
		if (woordList == null) {
			woordList = new JList();
			woordList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mousePressed(java.awt.event.MouseEvent e) {
					woordListPressed();
				}
			});
		}
		return woordList;
	}

	/**
	 * This method initializes synoniemenList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getSynoniemenList() {
		if (synoniemenList == null) {
			synoniemenList = new JList();
		}
		return synoniemenList;
	}

	/**
	 * This method initializes woordVeld
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getWoordVeld() {
		if (woordVeld == null) {
			woordVeld = new JTextField();
			woordVeld.setBounds(new Rectangle(14, 181, 109, 21));
		}
		return woordVeld;
	}

	/**
	 * This method initializes synoniemenVeld
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getSynoniemenVeld() {
		if (synoniemenVeld == null) {
			synoniemenVeld = new JTextField();
			synoniemenVeld.setBounds(new Rectangle(154, 177, 109, 21));
		}
		return synoniemenVeld;
	}

	/**
	 * This method initializes voegtoeKnop
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVoegtoeKnop() {
		if (voegtoeKnop == null) {
			voegtoeKnop = new JButton();
			voegtoeKnop.setText("Toevoegen");
			voegtoeKnop.setBounds(new Rectangle(82, 216, 96, 26));
			voegtoeKnop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					voegtoeKnopAction();
				}
			});
		}
		return voegtoeKnop;
	}

	public static void main(String[] args) {
		SynoniemenFrame fr = new SynoniemenFrame();
		fr.setVisible(true);
	}

} // @jve:decl-index=0:visual-constraint="10,10"
