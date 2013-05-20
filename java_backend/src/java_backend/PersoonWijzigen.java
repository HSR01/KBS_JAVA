package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class PersoonWijzigen extends JFrame implements ActionListener{
    private JTextField tfId, tfVoornaam, tfTussenvoegsel, tfAchternaam, tfEmailadres, tfGeboortedatum, tfMobielnummer, tfProfielfoto, tfIBANnummer, tfRechten;
    private JPasswordField pfWachtwoord;
    private JLabel logo, lId, lVoornaam, lTussenvoegsel, lAchternaam, lEmailadres, lWachtwoord, lGeboortedatum, lMobielnummer, lProfielfoto, lIBANnummer, lRechten, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lTztpoint;
    private JPanel jInputfields, jrecht, jFieldPanel, jBtnsOnderFoto, jLogo, jProfielfoto, jButtons, jDatums;        
    private JTextField tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam;
    private JButton btSluiten, btOpslaan, btTraject, btVerwijder , btWijzig;
    private String datumDag, datumMaand, datumJaar, datDag, datMaand, datum, rechten, latitude, longitude;
    private JComboBox maand, dag, jaar, recht, tztpoint;
    private Coordinaten cords;

    public PersoonWijzigen(String[] specifiekePersoonGegevens, String[] specifiekePersoonLocatie) {
        super();
        this.setSize(970,550);
        
        // Aanmaken panels
        this.jLogo          = new JPanel();
        this.jInputfields   = new JPanel();
        this.jFieldPanel    = new JPanel();
        this.jProfielfoto   = new JPanel();
        this.jButtons       = new JPanel();
        this.jDatums        = new JPanel();
        this.jBtnsOnderFoto = new JPanel();
        
        // Setten layouts
        this.jProfielfoto.setLayout( new BorderLayout());
        this.setLayout(new BorderLayout());
        jFieldPanel.setLayout(new GridLayout(15, 1, 18, 1));
        jBtnsOnderFoto.setLayout( new GridLayout(2,1));   
        
        //Laad profielfoto uit web omgeving
        try {
            URL url = new URL("http://tztpost.nl/images/Profielfotos/geenfoto.jpg");
            Image im = ImageIO.read(url);
            JLabel lblimage = new JLabel(new ImageIcon(im));
            jProfielfoto.add(lblimage, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println("Profielfoto Error: " + e);
        }
                
        // Aanmaken tekstvelden
        this.tfVoornaam         = new JTextField(4);
        this.tfTussenvoegsel    = new JTextField("",4);
        this.tfAchternaam       = new JTextField(4);
        this.tfGeboortedatum    = new JTextField(4);
        this.tfPostcode         = new JTextField(4);
        this.tfStraatnaam       = new JTextField(4);
        this.tfHuisnummer       = new JTextField(4);
        this.tfToevoeging       = new JTextField(4);
        this.tfPlaatsnaam       = new JTextField(4);
        this.tfEmailadres       = new JTextField(4);
        this.pfWachtwoord       = new JPasswordField(4);
        this.tfIBANnummer       = new JTextField(4);
        this.tfMobielnummer     = new JTextField(4);
        this.tfId               = new JTextField(4);
        
        // Aanmaken Labels
        this.lVoornaam          = new JLabel("Voornaam");
        this.lTussenvoegsel     = new JLabel("Tussenv");
        this.lAchternaam        = new JLabel("Achternaam");
        this.lGeboortedatum     = new JLabel("Geboortedatum");
        this.lPostcode          = new JLabel("Postcode");
        this.lStraatnaam        = new JLabel("Straatnaam");
        this.lHuisnummer        = new JLabel("Huisnummer");
        this.lToevoeging        = new JLabel("Toevoeging");
        this.lPlaatsnaam        = new JLabel("Plaatsnaam");
        this.lEmailadres        = new JLabel("E-mailadres");
        this.lWachtwoord        = new JLabel("Wachtwoord");
        this.lIBANnummer        = new JLabel("IBAN-nummer");
        this.lMobielnummer      = new JLabel("Mobielnummer");
        this.lId                = new JLabel("Persoons ID");
        this.lRechten           = new JLabel("Rechten");
        this.lTztpoint          = new JLabel("TZT Punt");
        
        // De dropdown voor tzt point
        DefaultComboBoxModel tzt = new DefaultComboBoxModel();
        tzt.addElement("Nee");
        tzt.addElement("Ja");
        this.tztpoint = new JComboBox(tzt);
        
        // De Dropdown box voor rechten
        DefaultComboBoxModel rechten = new DefaultComboBoxModel();
        rechten.addElement("Geblokkeerd");
        rechten.addElement("Verzender / Ontvanger / Klanten");
        rechten.addElement("TZT Point / BPS'er");
        rechten.addElement("TZT Medewerker");
        rechten.addElement("Hoofdkantoor");
        this.recht = new JComboBox(rechten);
        
        // De Dropdown box voor dag
        DefaultComboBoxModel dagen = new DefaultComboBoxModel();
        for (int dag = 1; dag < 32; dag++) {
            dagen.addElement(dag);
        }
        dag = new JComboBox(dagen);

        // De Dropdown box voor maand      
        DefaultComboBoxModel maanden = new DefaultComboBoxModel();
        maanden.addElement("Januari");
        maanden.addElement("Februari");
        maanden.addElement("Maart");
        maanden.addElement("April");
        maanden.addElement("Mei");
        maanden.addElement("Juni");
        maanden.addElement("Juli");
        maanden.addElement("Augustus");
        maanden.addElement("September");
        maanden.addElement("Oktober");
        maanden.addElement("November");
        maanden.addElement("December");
        maand = new JComboBox(maanden);

        // De Dropdown box voor jaar
        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        for (int jaarbegin = 2013; jaarbegin > 1899; jaarbegin --) {
            jaren.addElement(jaarbegin);
        }
        jaar = new JComboBox(jaren);
        
        // Vullen datum panel
        jDatums.add(dag);
        jDatums.add(maand);
        jDatums.add(jaar);

        // Buttons aanmaken
        this.btSluiten = new JButton("Sluiten");
        this.btWijzig = new JButton("Wijzig");
        btTraject = new JButton("Trajecten");
        btVerwijder  = new JButton("Placeholder");
        
        // Action listeners aan de buttons
        btSluiten.addActionListener(this);
        btWijzig.addActionListener(this);
        btTraject.addActionListener( this );
        btVerwijder .addActionListener( this );
        
        // Opbouwen jFieldPanel, rij voor rij
        this.jFieldPanel.add(lVoornaam);
        this.jFieldPanel.add(lTussenvoegsel);
        this.jFieldPanel.add(lAchternaam);
            this.jFieldPanel.add(tfVoornaam);  
            this.jFieldPanel.add(tfTussenvoegsel);        
            this.jFieldPanel.add(tfAchternaam);
        this.jFieldPanel.add(lPlaatsnaam);
        this.jFieldPanel.add(lPostcode);
        this.jFieldPanel.add(lTztpoint);
            this.jFieldPanel.add(tfPlaatsnaam);
            this.jFieldPanel.add(tfPostcode);
            this.jFieldPanel.add(tztpoint);
        this.jFieldPanel.add(lStraatnaam);
        this.jFieldPanel.add(lHuisnummer);
        this.jFieldPanel.add(lToevoeging);
            this.jFieldPanel.add(tfStraatnaam);
            this.jFieldPanel.add(tfHuisnummer);
            this.jFieldPanel.add(tfToevoeging);
        this.jFieldPanel.add(lGeboortedatum);
        this.jFieldPanel.add(lIBANnummer);
        this.jFieldPanel.add(new JLabel(""));
            this.jFieldPanel.add(jDatums);
            this.jFieldPanel.add(tfIBANnummer);
            this.jFieldPanel.add(new JLabel(""));
        this.jFieldPanel.add(lMobielnummer);
        this.jFieldPanel.add(lEmailadres);
        this.jFieldPanel.add(lWachtwoord);
            this.jFieldPanel.add(tfMobielnummer);
            this.jFieldPanel.add(tfEmailadres);
            this.jFieldPanel.add(pfWachtwoord);
        this.jFieldPanel.add(lId);
        this.jFieldPanel.add(lRechten);
        this.jFieldPanel.add(new JLabel(""));
            this.jFieldPanel.add(tfId);
            this.jFieldPanel.add((JComboBox) recht);
            this.jFieldPanel.add(new JLabel(""));
        
        // Opbouwen jButtons
        this.jButtons.add(btSluiten);
        this.jButtons.add(btWijzig);        
        
        // Opbouwen jBtnsOnderfoto
        this.jBtnsOnderFoto.add ( btTraject );
        this.jBtnsOnderFoto.add ( btVerwijder );
        
        // Koppelen buttons onder foto aan profielfoto
        jProfielfoto.add(jBtnsOnderFoto, BorderLayout.SOUTH);
        
        // Opbouw Frame
        this.add(jFieldPanel, BorderLayout.WEST);
        this.add(jProfielfoto, BorderLayout.EAST);
        this.add(jButtons, BorderLayout.SOUTH);
        
        // Id field onaanpasbaar maken
        this.tfId.setEditable(false);
        
        // Tekstvelden vullen met de gegevens van de geselecteerde gebruiker
        this.vulTekstvelden(specifiekePersoonGegevens, specifiekePersoonLocatie);
        
        // Frame zichtbaar maken
        this.setVisible(true);       
    }
    
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getSource() == btWijzig) {
             DbConnect dbc = new DbConnect();
             String[] waardes = this.getTekstvelden();
             dbc.updateGebruikerAccount(waardes);
             this.setVisible(false);
	}
        
        // Trajecten wijzigen
        if (ae.getSource() == btTraject) {
            System.out.println("Traject");
            PersonenOverzicht personenOverzicht = new PersonenOverzicht(Integer.parseInt(tfId.getText()));
        }   
    }
    
    public String[] getTekstvelden() {
        String[] waardes = new String[11];
        
        ArrayList foutLijst = new ArrayList();
        int lijstAantal = 0;
        int lijstScroller = 0;
        boolean valide = true;
        
        // Check Voornaam
            if (tfVoornaam.getText().length() == 0) {
                valide = false;
                foutLijst.add("Het veld \"Voornaam\" moet ingevuld zijn. Bv.: Harry.");
            }
            else {
                waardes[1] = this.tfVoornaam.getText();
            }
        // Check Tussenvoegsel
        waardes[2] = this.tfTussenvoegsel.getText();
        // Check Achternaam
            if (tfAchternaam.getText().length() == 0) {
                valide = false;
                foutLijst.add("Het veld \"Achternaam\" moet ingevuld zijn. Bv.: Versloten.");
            }
            else {
                waardes[3] = this.tfAchternaam.getText();
            }
        // Check Geboortedatum        
  //      waardes[4] = this.tfGeboortedatum.getText();
        // Check Emailadres
        waardes[5] = this.tfEmailadres.getText();
        // Check Wachtwoord
        waardes[6] = this.pfWachtwoord.getText();
        // Check IBANnummer
        waardes[7] = this.tfIBANnummer.getText();
        // Check Mobielnummer
        waardes[8] = this.tfMobielnummer.getText();
        // Check Profielfoto
//        waardes[9] = this.tfProfielfoto.getText();
        // Check Rechten
//        waardes[10] = this.tfRechten.getText();
        if (!valide) {
            lijstAantal = foutLijst.size();
            for (lijstScroller = 0; lijstScroller < lijstAantal; lijstScroller++) {
                System.out.println("Account number:" + foutLijst.get(lijstScroller)); 
            }
        }
        return waardes;
    }
    
    public void vulTekstvelden(String[] specifiekePersoonGegevens, String[] specifiekePersoonLocatie) {
        this.tfId.setText(specifiekePersoonGegevens[0]);
        this.tfVoornaam.setText(specifiekePersoonGegevens[1]);
        this.tfTussenvoegsel.setText(specifiekePersoonGegevens[2]);
        this.tfAchternaam.setText(specifiekePersoonGegevens[3]);
        this.tfEmailadres.setText(specifiekePersoonGegevens[4]);
        // Wachtwoord bewust niet laden
        //this.tfWachtwoord.setText(specifiekePersoon[4]);
        
        String Datum = specifiekePersoonGegevens[6];
        
        String dataChars[] = new String[10];
        dataChars[0] = "" + Character.toString(Datum.charAt(0));
        dataChars[1] = "" + Character.toString(Datum.charAt(1));
        dataChars[2] = "" + Character.toString(Datum.charAt(2));
        dataChars[3] = "" + Character.toString(Datum.charAt(3));
        dataChars[4] = "" + Character.toString(Datum.charAt(4));
        dataChars[5] = "" + Character.toString(Datum.charAt(5));
        dataChars[6] = "" + Character.toString(Datum.charAt(6));
        dataChars[7] = "" + Character.toString(Datum.charAt(7));
        dataChars[8] = "" + Character.toString(Datum.charAt(8));
        dataChars[9] = "" + Character.toString(Datum.charAt(9));
        
        String DatumDag = dataChars[0] + dataChars[1];
        String DatumMaand = dataChars[3] + dataChars[4];
        String DatumJaar = dataChars[6] + dataChars[7] + dataChars[8] + dataChars[9];
        
        System.out.println(DatumDag + DatumMaand + DatumJaar);
        
        
        this.dag.setSelectedItem(DatumDag);
        this.maand.setSelectedItem(DatumMaand);
        this.jaar.setSelectedItem(DatumJaar);
        setSelectedValue(dag, DatumDag);
        setSelectedValue(maand, DatumMaand);
        setSelectedValue(jaar, DatumJaar);
        
        
        this.tfIBANnummer.setText(specifiekePersoonGegevens[7]);
        this.tfMobielnummer.setText(specifiekePersoonGegevens[8]);
        // Word anders geregeld
        //this.tfProfielfoto.setText(specifiekePersoon[9]);
        //this.tfRechten.setText(specifiekePersoonGegevens[10]);
        
        this.tfPlaatsnaam.setText(specifiekePersoonLocatie[0]);
        this.tfStraatnaam.setText(specifiekePersoonLocatie[1]);
        this.tfHuisnummer.setText(specifiekePersoonLocatie[2]);
        this.tfToevoeging.setText(specifiekePersoonLocatie[3]);
        this.tfPostcode.setText(specifiekePersoonLocatie[4]);
    }
    
    public void setSelectedValue(JComboBox comboBox, String value){
        String item;
        for (int i = 0; i < comboBox.getItemCount(); i++){
            item = "" + comboBox.getItemAt(i);
            if (item.equals(value)){
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }    
}