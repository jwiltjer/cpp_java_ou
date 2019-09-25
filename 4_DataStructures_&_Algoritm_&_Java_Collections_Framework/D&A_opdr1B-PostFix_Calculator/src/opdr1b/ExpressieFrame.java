package opdr1b;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ExpressieFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel expressieLabel = null;
	private JLabel waardeLabel = null;
	private JTextField expressieVeld = null;
	private JTextField waardeVeld = null;
	private JButton berekenKnop = null;
	private JLabel foutLabel = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ExpressieFrame thisClass = new ExpressieFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ExpressieFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			foutLabel = new JLabel();
			foutLabel.setBounds(new Rectangle(5, 75, 323, 19));
			foutLabel.setText("");
			waardeLabel = new JLabel();
			waardeLabel.setBounds(new Rectangle(5, 47, 62, 19));
			waardeLabel.setText("Waarde:");
			expressieLabel = new JLabel();
			expressieLabel.setBounds(new Rectangle(5, 15, 74, 19));
			expressieLabel.setText("Expressie:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(expressieLabel, null);
			jContentPane.add(waardeLabel, null);
			jContentPane.add(getExpressieVeld(), null);
			jContentPane.add(getWaardeVeld(), null);
			jContentPane.add(getBerekenKnop(), null);
			jContentPane.add(foutLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method fires the calculation of the postfix expression entered
	 */
	private void berekenKnopAction() {
		foutLabel.setText("");
		PostFixBerekening bereken = new PostFixBerekening(expressieVeld.getText());

		try {
			waardeVeld.setText(bereken.berekenPostFix());
		} catch (PostfixException e) {
			expressieVeld.setText("");
			waardeVeld.setText("");
			foutLabel.setText(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes expressieVeld
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getExpressieVeld() {
		if (expressieVeld == null) {
			expressieVeld = new JTextField();
			expressieVeld.setBounds(new Rectangle(87, 15, 242, 19));
		}
		return expressieVeld;
	}

	/**
	 * This method initializes waardeVeld
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getWaardeVeld() {
		if (waardeVeld == null) {
			waardeVeld = new JTextField();
			waardeVeld.setBounds(new Rectangle(87, 47, 69, 19));
		}
		return waardeVeld;
	}

	/**
	 * This method initializes berekenKnop
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getBerekenKnop() {
		if (berekenKnop == null) {
			berekenKnop = new JButton();
			berekenKnop.setBounds(new Rectangle(184, 47, 147, 19));
			berekenKnop.setText("Bereken waarde");
			berekenKnop.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					berekenKnopAction();
				}
			});
		}
		return berekenKnop;
	}

}
