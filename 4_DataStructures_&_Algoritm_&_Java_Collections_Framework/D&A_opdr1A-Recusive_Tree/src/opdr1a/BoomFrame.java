package opdr1a;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyEvent;

public class BoomFrame extends JFrame {

  private BoomPanel boomPanel;
  
  private static final long serialVersionUID = 1L;
  private JPanel jContentPane = null;
  private JLabel lengteLabel = null;
  private JLabel hoekLabel = null;
  private JLabel diepteLabel = null;
  private JTextField stamVeld = null;
  private JTextField hoekVeld = null;
  private JTextField diepteVeld = null;
  private JButton tekenKnop = null;
  private JLabel foutLabel = null;

  /**
   * This method initializes stamVeld	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getStamVeld() {
    if (stamVeld == null) {
      stamVeld = new JTextField();
      stamVeld.setBounds(new Rectangle(186, 17, 63, 27));
    }
    return stamVeld;
  }

  /**
   * This method initializes hoekVeld	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getHoekVeld() {
    if (hoekVeld == null) {
      hoekVeld = new JTextField();
      hoekVeld.setBounds(new Rectangle(186, 61, 63, 27));
    }
    return hoekVeld;
  }

  /**
   * This method initializes diepteVeld	
   * 	
   * @return javax.swing.JTextField	
   */
  private JTextField getDiepteVeld() {
    if (diepteVeld == null) {
      diepteVeld = new JTextField();
      diepteVeld.setBounds(new Rectangle(186, 105, 63, 27));
    }
    return diepteVeld;
  }

  /**
   * This method initializes tekenKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getTekenKnop() {
    if (tekenKnop == null) {
      tekenKnop = new JButton();
      tekenKnop.setBounds(new Rectangle(15, 146, 144, 31));
      tekenKnop.setText("Teken boom ");
      tekenKnop.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
          tekenKnopAction();
        }
      });
    }
    return tekenKnop;
  }
  
  /**
   * Leest stamlengte, hoek en vermenigvuldigingsfactor en tekent de boom Er
   * moet gelden 0 < factor < 1 en 0 < hoek <= 180; zo niet dan verschijnt een
   * foutmelding
   */
  private void tekenKnopAction() {
    foutLabel.setText("");
    try {
      int stam = Integer.parseInt(stamVeld.getText());
      int hoek = Integer.parseInt(hoekVeld.getText());
      int diepte = Integer.parseInt(diepteVeld.getText());
      if (diepte <= 0)
        foutLabel.setText("FOUT: diepte moet groter dan 0 zijn");
      else if (hoek <= 0 || hoek >= 360)
        foutLabel.setText("FOUT: hoek moet tussen 1 en 360 liggen");
      else {
        if (boomPanel != null)
          this.getContentPane().remove(boomPanel);
        boomPanel = new BoomPanel(stam, hoek, diepte);
        boomPanel.setLocation(225, 25);
        this.getContentPane().add(boomPanel);
        repaint();
      }
    }
    catch (NumberFormatException nfe) {
      foutLabel.setText("FOUT: " + nfe + "");
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        BoomFrame thisClass = new BoomFrame();
        thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisClass.setVisible(true);
      }
    });
  }

  /**
   * This is the default constructor
   */
  public BoomFrame() {
    super();
    initialize();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    this.setSize(500, 407);
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
      foutLabel.setBounds(new Rectangle(15, 347, 468, 25));
      foutLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
      foutLabel.setText("");
      diepteLabel = new JLabel();
      diepteLabel.setBounds(new Rectangle(17, 105, 144, 27));
      diepteLabel.setText("Diepte boom");
      hoekLabel = new JLabel();
      hoekLabel.setBounds(new Rectangle(17, 61, 144, 27));
      hoekLabel.setText("Hoek tussen takken");
      lengteLabel = new JLabel();
      lengteLabel.setBounds(new Rectangle(17, 17, 144, 27));
      lengteLabel.setText("Beginlengte stam");
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.add(lengteLabel, null);
      jContentPane.add(hoekLabel, null);
      jContentPane.add(diepteLabel, null);
      jContentPane.add(getStamVeld(), null);
      jContentPane.add(getHoekVeld(), null);
      jContentPane.add(getDiepteVeld(), null);
      jContentPane.add(getTekenKnop(), null);
      jContentPane.add(foutLabel, null);
    }
    return jContentPane;
  }

}  //  @jve:decl-index=0:visual-constraint="10,10"
