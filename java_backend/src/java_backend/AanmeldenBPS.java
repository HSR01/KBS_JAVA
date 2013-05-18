/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Laurens
 */
public class AanmeldenBPS extends JDialog implements ActionListener {

    private JLabel card1logo, btLeeg, lWachtwoord, lPostcode, lPlaatsnaam, lStraatnaam, lHuisnummer, lToevoeging, lEmailadres, lVoornaam, lTussenvoegsel, lAchternaam, lGeboortedatum, lMobielnummer, lIbannummer;
    private JButton btAanmelden;
    private CardLayout cl = new CardLayout();
    private JPanel cardHolder = new JPanel(cl);
    protected JPanel login, logincenter, jDatums, logo;
    protected JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9, label10, label11, label12, label13;
    protected JComboBox dag, maand, jaar;
    private JTextField tfEmailadres, tfVoornaam, tfPostcode, tfPlaatsnaam, tfStraatnaam, tfHuisnummer, tfToevoeging, tfTussenvoegsel, tfAchternaam, tfGeboortedatum, tfMobielnummer, tfIbannummer;
    private JPasswordField pfWachtwoord;

    public AanmeldenBPS() {
        super();
        this.setTitle("TZT Post - Aanmelden BPS-er");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 800);

        //Instellingen eerste pagina.
        JPanel Card1 = new JPanel();
        Card1.setLayout(new BorderLayout());
        //Card1.add();
        this.card1logo = new JLabel();
        this.logo = new JPanel();
        //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
        //this.card1logo.setIcon(new javax.swing.ImageIcon("http://www.tztpost.nl/tztklein.png"));
        ////this.card1logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jelle\\Pictures\\zoidberg.png"));
        //Laad profielfoto uit web omgeving
        try{
            URL url = new URL("http://tztpost.nl/images/Profielfotos/geenfoto.jpg");
            Image im = ImageIO.read(url);
            JLabel lblimage = new JLabel(new ImageIcon(im));
            this.logo.add(lblimage);
        }catch(Exception e){
            System.out.println("Profielfoto Error: " + e);
        }

        //loginscherm voor card1
        this.login = new JPanel();
        this.logincenter = new JPanel();
        this.jDatums = new JPanel();
        //kies borderlayout
        this.login.setLayout(new BorderLayout());
        this.logincenter.setLayout(new GridLayout(14, 3));
        //instancieer buttons en velden.
        this.btAanmelden = new JButton("Aanmelden");
        this.tfEmailadres = new JTextField(20);
        this.pfWachtwoord = new JPasswordField(10);
        this.tfVoornaam = new JTextField(20);
        this.tfTussenvoegsel = new JTextField(20);
        this.tfAchternaam = new JTextField(20);
        this.tfIbannummer = new JTextField(20);
        this.tfMobielnummer = new JTextField(20);
        this.tfGeboortedatum = new JTextField(20);
        this.tfPostcode = new JTextField(20);
        this.tfPlaatsnaam = new JTextField(20);
        this.tfStraatnaam = new JTextField(20);
        this.tfHuisnummer = new JTextField(20);
        this.tfToevoeging = new JTextField(20);


        this.btLeeg = new JLabel();
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lVoornaam = new JLabel("Voornaam:");
        this.lTussenvoegsel = new JLabel("Tussenvoegsel:");
        this.lAchternaam = new JLabel("Achternaam:");
        this.lEmailadres = new JLabel("E-mailadres:");
        this.lIbannummer = new JLabel("IBAN-nummer:");
        this.lGeboortedatum = new JLabel("Geboortedatum:");
        this.lMobielnummer = new JLabel("Mobielnummer:");
        this.lPostcode = new JLabel("Postcode(1234AB):");
        this.lPlaatsnaam = new JLabel("Plaatsnaam:");
        this.lStraatnaam = new JLabel("Straatnaam:");
        this.lHuisnummer = new JLabel("Huisnummer:");
        this.lToevoeging = new JLabel("Toevoeging:");

        this.label1 = new JLabel();
        this.label2 = new JLabel();
        this.label3 = new JLabel();
        this.label4 = new JLabel();
        this.label5 = new JLabel();
        this.label6 = new JLabel();
        this.label7 = new JLabel();
        this.label8 = new JLabel();
        this.label9 = new JLabel();
        this.label10 = new JLabel();
        this.label11 = new JLabel();
        this.label12 = new JLabel();
        this.label13 = new JLabel();


        //voeg velden toe aan login center

        DefaultComboBoxModel dagen = new DefaultComboBoxModel();
        for (int dag = 1; dag < 32; dag++) {
            dagen.addElement(dag);
        }
        dag = new JComboBox(dagen);

        DefaultComboBoxModel maanden = new DefaultComboBoxModel();

        for (int maand = 1; maand < 13; maand++) {
            maanden.addElement(maand);
        }

        maand = new JComboBox(maanden);

        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        for (int jaar = 2013; jaar > 1899; jaar--) {
            jaren.addElement(jaar);
        }
        jaar = new JComboBox(jaren);
        jDatums.add(dag);
        jDatums.add(maand);
        jDatums.add(jaar);





        this.logincenter.add(lVoornaam);
        this.logincenter.add(tfVoornaam);
        this.logincenter.add(label1);
        this.logincenter.add(lTussenvoegsel);
        this.logincenter.add(tfTussenvoegsel);
        this.logincenter.add(label2);
        this.logincenter.add(lAchternaam);
        this.logincenter.add(tfAchternaam);
        this.logincenter.add(label3);
        this.logincenter.add(lStraatnaam);
        this.logincenter.add(tfStraatnaam);
        this.logincenter.add(label4);
        this.logincenter.add(lHuisnummer);
        this.logincenter.add(tfHuisnummer);
        this.logincenter.add(label5);
        this.logincenter.add(lPostcode);
        this.logincenter.add(tfPostcode);
        this.logincenter.add(label6);
        this.logincenter.add(lPlaatsnaam);
        this.logincenter.add(tfPlaatsnaam);
        this.logincenter.add(label7);
        this.logincenter.add(lToevoeging);
        this.logincenter.add(tfToevoeging);
        this.logincenter.add(label8);
        this.logincenter.add(lGeboortedatum);
        this.logincenter.add(jDatums);
        this.logincenter.add(label9);
        this.logincenter.add(lIbannummer);
        this.logincenter.add(tfIbannummer);
        this.logincenter.add(label10);
        this.logincenter.add(lMobielnummer);
        this.logincenter.add(tfMobielnummer);
        this.logincenter.add(label11);
        this.logincenter.add(lEmailadres);
        this.logincenter.add(tfEmailadres);
        this.logincenter.add(label12);
        this.logincenter.add(lWachtwoord);
        this.logincenter.add(pfWachtwoord);
        this.logincenter.add(label13);
        this.logincenter.add(btLeeg);
        this.logincenter.add(btAanmelden);




        //voeg velden toe aan loginpanel
        this.login.add(this.logincenter);

        //voeg logo toe aan loginpanel
        Card1.add(this.logo, BorderLayout.NORTH);
        //voeg panel toe aan loginscherm.
        Card1.add(this.login, BorderLayout.CENTER);

        //instellingen tweede pagina/card.
        JPanel Card2 = new AccountsBeherenTabel();
        cardHolder.add(Card1);
        cardHolder.add(Card2);
        this.add(cardHolder);

        //Actionlistener toevoegen voor btLogin.
        btAanmelden.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btAanmelden) {


            ArrayList flijstje = new ArrayList();
           
            boolean fout = true;
            
            label3.setText("");
            label5.setText("");
            label13.setText("");
            


            //controleer de gegevens.
            if (tfEmailadres.getText().equals("") || tfVoornaam.getText().equals("") || tfTussenvoegsel.getText().equals("")
                    || tfAchternaam.getText().equals("") || tfHuisnummer.getText().equals("") || tfPostcode.getText().equals("") || tfStraatnaam.getText().equals("")
                    || tfPlaatsnaam.getText().equals("") || tfToevoeging.getText().equals("") || tfMobielnummer.getText().equals("") || tfIbannummer.getText().equals("")
                    || pfWachtwoord.getText().equals("")) {
                fout = false;
                label13.setText("  Niet alle velden zijn ingevuld.");
                flijstje.add(label13);


            } else {
                if (tfMobielnummer.getText().length() < 10 || tfMobielnummer.getText().length() > 10) {
                    fout = false;
                    label11.setText("  Een mobielnummer heeft 10 cijfers.");
                    flijstje.add(label11);

                    String Mobielcijfer = tfMobielnummer.getText();
                    int val;
                    try {
                        val = Integer.parseInt(Mobielcijfer);

                    } catch (NumberFormatException nfe) {
                        // bad data - set to sentinel
                        val = Integer.MIN_VALUE;
                        fout = false;
                        label11.setText("  Een mobielnummer bevat cijfers.");
                        flijstje.add(label11);


                    }



                    String Huisnummercijfer = tfHuisnummer.getText();
                    int val1;
                    try {
                        val1 = Integer.parseInt(Huisnummercijfer);

                    } catch (NumberFormatException nfe) {
                        // bad data - set to sentinel
                        val1 = Integer.MIN_VALUE;
                        fout = false;
                        label5.setText("  Een huisnummer bevat cijfers.");
                        flijstje.add(label5);

                    }
                }

            }


           // if (fout == false) {

             //   lijstAantal = flijstje.size();
             //   for (lijstScroller = 0; lijstScroller < lijstAantal; lijstScroller++) {
            //    }

        //    }

            if (fout == true) {
                //Hash het wachtwoord naar MD5
                String wachtwoord = pfWachtwoord.getText();
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(wachtwoord.getBytes(), 0, wachtwoord.length());
                    wachtwoord = new BigInteger(1, md.digest()).toString(16);
                } catch (Exception o) {
                    System.out.println("Hash Error:" + o);
                }

                DbConnect a = new DbConnect();
                String LocatieID = "1";
                String Profielfoto = "hoi";
                String Geboortedatum = dag.getSelectedItem() + "/" + maand.getSelectedItem() + "/" + jaar.getSelectedItem();


                a.insertData("Persoon", LocatieID, tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), wachtwoord, Geboortedatum, tfMobielnummer.getText(), Profielfoto, tfIbannummer.getText());

                //SELECT ID 
                a.insertData("Locatie", "00000", "00000", tfPlaatsnaam.getText(), tfStraatnaam.getText(), tfHuisnummer.getText(), tfToevoeging.getText(), tfPostcode.getText(), tfMobielnummer.getText(), "0");

            }



        }






    }
}
