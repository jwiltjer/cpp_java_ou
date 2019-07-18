package bankgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bank.Bank;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class BankFrame extends JFrame {

	/**
	 * Gui klasse voor het storten, opnemen en overschrijven van een bedrag tussen 2
	 * rekeningen
	 */
	private static final long serialVersionUID = 1L;
	private Bank bank0 = new Bank();
	private JPanel contentPane;
	private JTextField rekeningNummerVeld1;
	private JTextField naamVeld1;
	private JTextField saldoVeld1;
	private JTextField bedragVeld1;
	private JTextField rekeningNummerVeld2;
	private JTextField naamVeld2;
	private JTextField saldoVeld2;
	private JTextField bedragVeld2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankFrame frame = new BankFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankFrame() {

		initialize();
		mijnInit();
		Bank();
	}

	private void initialize() {
		setTitle("Jeffrey's Bank App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(15, 41, 366, 198);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel rekeningNummerLabel1 = new JLabel("rekeningnummer:");
		rekeningNummerLabel1.setBounds(15, 52, 145, 20);
		panel.add(rekeningNummerLabel1);

		JLabel naamLabel1 = new JLabel("naam:");
		naamLabel1.setBounds(15, 93, 69, 20);
		panel.add(naamLabel1);

		JLabel saldoLabel1 = new JLabel("saldo:");
		saldoLabel1.setBounds(15, 134, 69, 20);
		panel.add(saldoLabel1);

		JLabel bedragLabel1 = new JLabel("bedrag:");
		bedragLabel1.setBounds(15, 175, 69, 20);
		panel.add(bedragLabel1);

		rekeningNummerVeld1 = new JTextField();
		rekeningNummerVeld1.setBounds(144, 49, 146, 26);
		panel.add(rekeningNummerVeld1);
		rekeningNummerVeld1.setColumns(10);

		naamVeld1 = new JTextField();
		naamVeld1.setEditable(false);
		naamVeld1.setBounds(144, 90, 146, 26);
		panel.add(naamVeld1);
		naamVeld1.setColumns(10);

		saldoVeld1 = new JTextField();
		saldoVeld1.setEditable(false);
		saldoVeld1.setBounds(144, 131, 146, 26);
		panel.add(saldoVeld1);
		saldoVeld1.setColumns(10);

		bedragVeld1 = new JTextField();
		bedragVeld1.setBounds(144, 172, 146, 26);
		panel.add(bedragVeld1);
		bedragVeld1.setColumns(10);

		JLabel vanLabel = new JLabel("Van");
		vanLabel.setBounds(127, 16, 69, 20);
		panel.add(vanLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(421, 41, 366, 198);
		contentPane.add(panel_1);

		JLabel rekeningNummerLabel2 = new JLabel("rekeningnummer:");
		rekeningNummerLabel2.setBounds(15, 52, 145, 20);
		panel_1.add(rekeningNummerLabel2);

		JLabel naamLabel2 = new JLabel("naam:");
		naamLabel2.setBounds(15, 93, 69, 20);
		panel_1.add(naamLabel2);

		JLabel saldoLabel2 = new JLabel("saldo:");
		saldoLabel2.setBounds(15, 134, 69, 20);
		panel_1.add(saldoLabel2);

		JLabel bedragLabel2 = new JLabel("bedrag:");
		bedragLabel2.setBounds(15, 175, 69, 20);
		panel_1.add(bedragLabel2);

		rekeningNummerVeld2 = new JTextField();
		rekeningNummerVeld2.setColumns(10);
		rekeningNummerVeld2.setBounds(144, 49, 146, 26);
		panel_1.add(rekeningNummerVeld2);

		naamVeld2 = new JTextField();
		naamVeld2.setEditable(false);
		naamVeld2.setColumns(10);
		naamVeld2.setBounds(144, 90, 146, 26);
		panel_1.add(naamVeld2);

		saldoVeld2 = new JTextField();
		saldoVeld2.setEditable(false);
		saldoVeld2.setColumns(10);
		saldoVeld2.setBounds(144, 131, 146, 26);
		panel_1.add(saldoVeld2);

		bedragVeld2 = new JTextField();
		bedragVeld2.setColumns(10);
		bedragVeld2.setBounds(144, 172, 146, 26);
		panel_1.add(bedragVeld2);

		JLabel naarLabel = new JLabel("Naar");
		naarLabel.setBounds(127, 16, 69, 20);
		panel_1.add(naarLabel);

		JButton stortKnop1 = new JButton("Stort");
		stortKnop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stortKnopAction();
			}

		});
		stortKnop1.setBounds(0, 271, 115, 29);
		contentPane.add(stortKnop1);

		JButton neemOpKnop1 = new JButton("Neem Op");
		neemOpKnop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				neemOpKnopAction();
			}
		});
		neemOpKnop1.setBounds(130, 271, 115, 29);
		contentPane.add(neemOpKnop1);

		JButton zoekKnop1 = new JButton("Zoek");
		zoekKnop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoekOpKnop();
			}
		});
		zoekKnop1.setBounds(260, 271, 121, 29);
		contentPane.add(zoekKnop1);

		JButton maakOverKnop = new JButton("Maak Over");
		maakOverKnop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				schrijfOverKnopAction();
			}
		});
		maakOverKnop.setBounds(350, 317, 115, 29);
		contentPane.add(maakOverKnop);

		JButton stortKnop2 = new JButton("Stort");
		stortKnop2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stortKnop2Action();
			}
		});
		stortKnop2.setBounds(421, 271, 115, 29);
		contentPane.add(stortKnop2);

		JButton neemOpKnop2 = new JButton("Neem Op");
		neemOpKnop2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neemOpKnop2Action();
			}
		});
		neemOpKnop2.setBounds(550, 271, 115, 29);
		contentPane.add(neemOpKnop2);

		JButton zoekKnop2 = new JButton("Zoek");
		zoekKnop2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zoekKnop2();
			}
		});
		zoekKnop2.setBounds(672, 271, 115, 29);
		contentPane.add(zoekKnop2);
	}

	public void Bank() {
		bank0.voegRekeningToe("Theo", 1111);
		bank0.voegRekeningToe("Jan", 2222);
		bank0.voegRekeningToe("Piet", 3333);
		bank0.voegRekeningToe("Henk", 1234);
		bank0.voegRekeningToe("Klaas", 2345);

	}

	private void mijnInit() {
		String initieelVeld1 = "1111", initieelVeld2 = "1234";
		rekeningNummerVeld1.setText(initieelVeld1);
		rekeningNummerVeld2.setText(initieelVeld2);

		naamVeld1.setText(null);
		saldoVeld1.setText("");
		bedragVeld1.setText("0.0");
		naamVeld2.setText(null);
		saldoVeld2.setText("");
		bedragVeld2.setText("0.0");
	}

	private void zoekOpKnop() {

		String initNummerStr = rekeningNummerVeld1.getText();
		if (initNummerStr.contentEquals("")) {
			// doe niks
		} else {
			int initNummerInt = Integer.parseInt(initNummerStr);

			String naam1 = bank0.geefRekeningNaam(initNummerInt);
			double saldo3 = bank0.geefSaldo(initNummerInt);

			naamVeld1.setText(naam1);
			saldoVeld1.setText("" + saldo3);
		}

	}

	private void zoekKnop2() {
		String initNummerStr = rekeningNummerVeld2.getText();
		if (initNummerStr.contentEquals("")) {
			// doe niks
		} else {
			int initNummerInt = Integer.parseInt(initNummerStr);

			String naam = bank0.geefRekeningNaam(initNummerInt);
			double saldo = bank0.geefSaldo(initNummerInt);

			naamVeld2.setText(naam);
			saldoVeld2.setText("" + saldo);
		}
	}

	private void stortKnopAction() {
		String rekeningnummer = rekeningNummerVeld1.getText();
		String bedrag = bedragVeld1.getText();
		if (bedrag.contentEquals("")) {
			// doe niks

		} else {
			int rekeningnummer1 = Integer.parseInt(rekeningnummer);
			double bedrag1 = Double.parseDouble(bedrag);
			bank0.stortBank(rekeningnummer1, bedrag1);

			double nieuwSaldo = bank0.geefSaldo(rekeningnummer1);

			saldoVeld1.setText("" + nieuwSaldo);
			bedragVeld1.setText("0.0");
		}
	}

	private void neemOpKnopAction() {
		String rekeningnummer = rekeningNummerVeld1.getText();
		String bedrag = bedragVeld1.getText();
		if (bedrag.contentEquals("")) {
			// doe niks

		} else {
			int rekeningnummer1 = Integer.parseInt(rekeningnummer);
			double bedrag1 = Double.parseDouble(bedrag);
			bank0.neemOpBank(rekeningnummer1, bedrag1);

			double nieuwSaldo = bank0.geefSaldo(rekeningnummer1);

			saldoVeld1.setText("" + nieuwSaldo);
			bedragVeld1.setText("0.0");
		}
	}

	private void stortKnop2Action() {
		String rekeningnummer = rekeningNummerVeld2.getText();
		String bedrag = bedragVeld2.getText();
		if (bedrag.contentEquals("")) {
			// doe niks

		} else {
			int rekeningnummer1 = Integer.parseInt(rekeningnummer);
			double bedrag1 = Double.parseDouble(bedrag);
			bank0.stortBank(rekeningnummer1, bedrag1);

			double nieuwSaldo = bank0.geefSaldo(rekeningnummer1);

			saldoVeld2.setText("" + nieuwSaldo);
			bedragVeld2.setText("0.0");
		}
	}

	private void neemOpKnop2Action() {
		String rekeningnummer = rekeningNummerVeld2.getText();
		String bedrag = bedragVeld2.getText();
		if (bedrag.contentEquals("")) {
			// doe niks
		} else {
			int rekeningnummer1 = Integer.parseInt(rekeningnummer);
			double bedrag1 = Double.parseDouble(bedrag);

			bank0.neemOpBank(rekeningnummer1, bedrag1);

			double nieuwSaldo = bank0.geefSaldo(rekeningnummer1);

			saldoVeld2.setText("" + nieuwSaldo);
			bedragVeld2.setText("0.0");
		}
	}

	private void schrijfOverKnopAction() {
		String rekeningnummerVeldLinks = rekeningNummerVeld1.getText();
		String rekeningNummerVeldRechts = rekeningNummerVeld2.getText();
		String bedragLinks = bedragVeld1.getText();
		if (bedragLinks.contentEquals("")) {
			// doe niks.
		} else {
			int rekeningNummer1 = Integer.parseInt(rekeningnummerVeldLinks);
			int rekeningNummer2 = Integer.parseInt(rekeningNummerVeldRechts);
			double bedrag1 = Double.parseDouble(bedragLinks);

			bank0.maakOver(rekeningNummer1, rekeningNummer2, bedrag1);

			double nieuwSaldoVeld1 = bank0.geefSaldo(rekeningNummer1);
			double nieuwSaldoVeld2 = bank0.geefSaldo(rekeningNummer2);

			saldoVeld1.setText("" + nieuwSaldoVeld1);
			saldoVeld2.setText("" + nieuwSaldoVeld2);
			bedragVeld1.setText("0.0");
			bedragVeld2.setText("0.0");

		}
	}
}