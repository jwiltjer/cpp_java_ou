package opdr2;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JButton;

public class SynoniemenFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel woordLabel = null;
	private JLabel synoniemenLabel = null;
	private JScrollPane woordScrollPane = null;
	private JScrollPane synomiemenScrollPane = null;
	private JList<String> woordList = null;
	private JList<String> synoniemenList = null;
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
	 * Event handler die gecontroleerd of een woord uit letters bestaat, en een
	 * woordenlijst uit woorden gescheiden door spaties, al bestaan in de lijst en
	 * vervolgens het woord in de lijst toevoegt.
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
		} else if (synoniem.contains(woordVeld.getText())) {
			foutLabel.setText("Synoniem bestaat al, vul een andere in");
		} else {
			synoniem.addSynoniem(woordVeld.getText(), synoniemenVeld.getText());
		}
		// weergave keys in woordlijst
		woordList.setListData(synoniem.getKeys());

		// velden leeg maken na operatie en syno lijst leeg maken en woord deselecteren
		woordVeld.setText("");
		synoniemenVeld.setText("");
		woordList.setSelectedIndex(-1);
		synoniemenList.setListData(new String[0]);
	}

	/**
	 * Event Handler die in de synoniemenList de synoniemen van het geselecteerde
	 * woord weergeeft.
	 * 
	 */
	private void woordListPressed() {
		synoniemenList.setListData(synoniem.displayValues(woordList.getSelectedIndex()));
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
	private JList<String> getWoordList() {
		if (woordList == null) {
			woordList = new JList<String>();
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
	private JList<String> getSynoniemenList() {
		if (synoniemenList == null) {
			synoniemenList = new JList<String>();
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
