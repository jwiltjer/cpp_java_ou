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
	private MijnStack<String> stack = null;

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
		mijnInit();
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

	private void mijnInit() {
		stack = new MijnStack<String>();

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

	private void berekenKnopAction() throws PostfixException {
		String expressie = expressieVeld.getText();

		if (expressie.length() < 2) {
			foutLabel.setText("expressie moet uit minimaal 2 cijfers bestaan");
		} else if (!Pattern.matches("\\d\\s[*-+/]", expressie)) 
			foutLabel.setText("FOUT!");
		else {
			/*

			for (int i = 0; i < expressie.length(); i++) {
				char c = expressie.charAt(i);
				if (!Character.isDigit(c) || !(c == ' ' || c == '+' || c == '-' || c == '/' || c == '*')) {
					foutLabel.setText("vul een cijfer of operator in");
				}

			}

			// wat is de goede regex voor '-', '/', '+', '-', & 0-9

			// als stack size == 2 moet het volgende char een operator zijn

			// de stack moet eerst size 2 hebbeb voor voordat er een operator mag komen

			/*
			 * for(int i = 0; i < expressie.length();i++) { char c = expressie.charAt(i);
			 * if(!Character.isDigit(c)|!(c== ' '|| c == '+'| c =='-' |c =='/'|c =='*')) {
			 * foutLabel.setText("vul een cijfer of operator in"); }
			 */
			/*
			 * if (Pattern.matches("[a-zA-Z]+", expressie)) {
			 * foutLabel.setText("expressie mag geen letters bevatten"); }
			 */
			// check op geldige invoer('-', '/', '+', '-', & 0-9)
			/*
			 * 
			 * foutLabel.setText("Alleen *, /, +, -, 0-9 zijn geldige karakters");
			 */
			if (stack.isEmpty())

			{

				foutLabel.setText("Vul minmaal 2 getallen in voor een operator");
			}
			StreamTokenizer tkn = new StreamTokenizer(new StringReader(expressieVeld.getText()));
			boolean eind = false;

			// zorg ervoor dat '/' en '-' worden gelezen als operatoren
			tkn.ordinaryChar('/');
			tkn.ordinaryChar('-');

			while (!eind) {
				int token = -4;
				try {
					token = tkn.nextToken();
				} catch (IOException e) {
					foutLabel.setText("foutieve invoer, probeer opnieuw");
					e.printStackTrace();
				}
				switch (token) {
				case StreamTokenizer.TT_NUMBER:
					int getal = (int) Math.round(tkn.nval);
					stack.push("" + getal);
					break;

				case StreamTokenizer.TT_EOF:
					eind = true;
					break;

				default: // bij een operator * / + of -

					int y = 0;
					int x = 0;
					try {
						y = Integer.parseInt(stack.pop());
						x = Integer.parseInt(stack.pop());
					} catch (NumberFormatException e) {
						foutLabel.setText("Berekening mislukt, probeer opnieuw");
						e.printStackTrace();
					} catch (PostfixException e) {
						foutLabel.setText("Vul eerst 2 getallen in dan pas een operator");
						e.printStackTrace();
					}

					// hier wordt de waarde van de token aan een String toegekend.

					String waardeToken = Character.toString((char) tkn.ttype);

					// bereken met de laatste 2 getallen van de stack de uitkomst met de operator
					try {

						stack.push(rekenUit(waardeToken, x, y));
					} catch (PostfixException e) {
						foutLabel.setText("fout bij het maken van de berekening, probeer opnieuw");
						e.printStackTrace();
					}
					break;
				}
			}
			try {
				waardeVeld.setText(stack.pop());
			} catch (PostfixException e) {
				foutLabel.setText("geen uitkomst mogelijk, probeer opnieuw");
				e.printStackTrace();
			}
			foutLabel.setText("");
		}

	}

	private String rekenUit(String operation, int x, int y) throws PostfixException {
		int uitkomst = 0;

		switch (Operation.fromString(operation)) {
		case MIN:
			uitkomst = Operation.MIN.apply(x, y);
			break;
		case DELING:
			uitkomst = Operation.DELING.apply(x, y);
			break;
		case MAAL:
			uitkomst = Operation.MAAL.apply(x, y);
			break;
		case PLUS:
			uitkomst = Operation.PLUS.apply(x, y);
			break;
		}
		return ("" + uitkomst);
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
					try {
						berekenKnopAction();
					} catch (PostfixException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return berekenKnop;
	}

}
