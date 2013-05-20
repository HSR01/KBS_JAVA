package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
public class WijzigPersoon extends JPanel implements ActionListener{
    private JTextField tfId, tfVoornaam, tfTussenvoegsel, tfAchternaam, tfEmailadres, tfGeboortedatum, tfMobielnummer, tfProfielfoto, tfIBANnummer, tfRechten;
    private JPasswordField pfWachtwoord;
    private JLabel logo, lId, lVoornaam, lTussenvoegsel, lAchternaam, lEmailadres, lWachtwoord, lGeboortedatum, lMobielnummer, lProfielfoto, lIBANnummer, lRechten, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam;
    private JPanel jInputfields, jFieldPanel, jBtnsOnderFoto, jLogo, jProfielfoto, jButtons, jDatums;        
    private JTextField tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam;
    private JButton btSluiten, btOpslaan, btTraject, btPlaceholder, btWijzig;
    private String datumDag, datumMaand, datumJaar, datDag, datMaand;
    private JComboBox maand, dag, jaar;

    public WijzigPersoon(String[] specifiekePersoonGegevens, String[] specifiekePersoonLocatie) {
        super();
        this.setLayout(new BorderLayout());

        this.jLogo = new JPanel();
        this.jInputfields = new JPanel();
        this.jFieldPanel = new JPanel();
        this.jProfielfoto = new JPanel();
        this.jProfielfoto.setLayout( new BorderLayout());
        this.jButtons = new JPanel();
        this.jDatums = new JPanel();
        this.jBtnsOnderFoto = new JPanel();
        
        //Laad profielfoto uit web omgeving
        try {
            URL url = new URL("http://tztpost.nl/images/Profielfotos/geenfoto.jpg");
            Image im = ImageIO.read(url);
            JLabel lblimage = new JLabel(new ImageIcon(im));
            jProfielfoto.add(lblimage, BorderLayout.NORTH);
        } catch (Exception e) {
            System.out.println("Profielfoto Error: " + e);
        }
        
        //Layout voor inputfields
        jFieldPanel.setLayout(new GridLayout(15, 1, 18, 1));

        this.tfVoornaam = new JTextField(4);
        this.tfTussenvoegsel = new JTextField("",4);
     
        this.tfAchternaam = new JTextField(4);
        this.tfGeboortedatum = new JTextField(4);
        this.tfPostcode = new JTextField(4);
        this.tfStraatnaam = new JTextField(4);
        this.tfHuisnummer = new JTextField(4);
        this.tfToevoeging = new JTextField(4);
        this.tfPlaatsnaam = new JTextField(4);
        this.tfEmailadres = new JTextField(4);
        this.pfWachtwoord = new JPasswordField(4);
        this.tfIBANnummer = new JTextField(4);
        this.tfMobielnummer = new JTextField(4);
        this.tfId = new JTextField(4);
        
        this.lVoornaam = new JLabel("Voornaam");
        this.lTussenvoegsel = new JLabel("Tussenv");
        this.lAchternaam = new JLabel("Achternaam");
        this.lGeboortedatum = new JLabel("Geboortedatum");
        this.lPostcode = new JLabel("Postcode");
        this.lStraatnaam = new JLabel("Straatnaam");
        this.lHuisnummer = new JLabel("Huisnummer");
        this.lToevoeging = new JLabel("Toevoeging");
        this.lPlaatsnaam = new JLabel("Plaatsnaam");
        this.lEmailadres = new JLabel("E-mailadres");
        this.lWachtwoord = new JLabel("Wachtwoord");
        this.lIBANnummer = new JLabel("IBAN-nummer");
        this.lMobielnummer = new JLabel("Mobielnummer");
        this.lId = new JLabel("Persoons ID");
        this.lRechten = new JLabel ("Rechten");
        
        // De Dropdown box voor rechten
        DefaultComboBoxModel rechten = new DefaultComboBoxModel();
        rechten.addElement("BPS Koerier");
        rechten.addElement("Koeriersdienst");
        rechten.addElement("Kantoor");
        rechten.addElement("Service Desk");
        JComboBox recht = new JComboBox(rechten);
        
        DefaultComboBoxModel dagen = new DefaultComboBoxModel();
        for (int dag = 1; dag < 32; dag++) {
            dagen.addElement(dag);
        }
        JComboBox dag = new JComboBox(dagen);
        
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
        JComboBox maand = new JComboBox(maanden);
        
        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        for (int jaar = 2013; jaar > 1899; jaar --) {
            jaren.addElement(jaar);
        }
        JComboBox jaar = new JComboBox(jaren);
        jDatums.add(dag);
        jDatums.add(maand);
        jDatums.add(jaar);
        
        
        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));

        this.btSluiten = new JButton("Sluiten");
        this.btWijzig = new JButton("Wijzig");
       
        btSluiten.addActionListener(this);
        btWijzig.addActionListener(this);
        
        this.jFieldPanel.add(lVoornaam);
        this.jFieldPanel.add(lTussenvoegsel);
        this.jFieldPanel.add(lAchternaam); 
        
        this.jFieldPanel.add(tfVoornaam);  
        this.jFieldPanel.add(tfTussenvoegsel);        
        this.jFieldPanel.add(tfAchternaam); 
        
        this.jFieldPanel.add(lPlaatsnaam);
        this.jFieldPanel.add(lPostcode);
        this.jFieldPanel.add(new JLabel(""));    
        
        this.jFieldPanel.add(tfPlaatsnaam);
        this.jFieldPanel.add(tfPostcode);
        this.jFieldPanel.add(new JLabel(""));        
        
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
        this.jFieldPanel.add(recht);
        this.jFieldPanel.add(new JLabel(""));
        
        this.jInputfields.add(jFieldPanel);

        this.jButtons.add(btSluiten);
        this.jButtons.add(btWijzig);

        this.jLogo.add(logo);
        
        this.jBtnsOnderFoto.setLayout( new GridLayout(2,1));
        btTraject = new JButton("Trajecten");
        btPlaceholder = new JButton("Placeholder");
        
        this.jBtnsOnderFoto.add ( btTraject );
        this.jBtnsOnderFoto.add ( btPlaceholder);
        btTraject.addActionListener( this );
        btPlaceholder.addActionListener( this );
        jProfielfoto.add(jBtnsOnderFoto, BorderLayout.SOUTH);
        
        this.add(jLogo, BorderLayout.NORTH);
        this.add(jInputfields, BorderLayout.WEST);
        this.add(jProfielfoto, BorderLayout.EAST);
        this.add(jButtons, BorderLayout.SOUTH);
        
        this.tfId.setEditable(false);
        
        this.jInputfields.add(jFieldPanel);

        this.add(jInputfields, BorderLayout.CENTER);

        this.vulTekstvelden(specifiekePersoonGegevens, specifiekePersoonLocatie);
        
        this.setVisible(true);       
    }
    
    public WijzigPersoon() {
        super();
        
        // Aanmaken Panels
        this.jLogo          = new JPanel();
        this.jInputfields   = new JPanel();
        this.jFieldPanel    = new JPanel();
        this.jButtons       = new JPanel();
        this.jDatums        = new JPanel();
        
        // Setten layouts
        this.setLayout(         new BorderLayout());
        jFieldPanel.setLayout(  new GridLayout(12,1,3,1));
        
        // Aanmaken JTextFields
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
        this.tfIBANnummer       = new JTextField(4);
        this.tfMobielnummer     = new JTextField(4);
        this.tfId               = new JTextField(4);
        
        // Aanmaken JPassWordField
        this.pfWachtwoord       = new JPasswordField(4);
        
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
        this.lRechten           = new JLabel ("Rechten");
        
        // De Dropdown box voor rechten
        DefaultComboBoxModel rechten = new DefaultComboBoxModel();
        rechten.addElement("BPS Koerier");
        rechten.addElement("Koeriersdienst");
        rechten.addElement("Kantoor");
        rechten.addElement("Service Desk");
        JComboBox recht = new JComboBox(rechten);
        
        DefaultComboBoxModel dagen = new DefaultComboBoxModel();
        for (int dag = 1; dag < 32; dag++) {
            dagen.addElement(dag);
        }
        this.dag = new JComboBox(dagen);
        
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
        this.maand = new JComboBox(maanden);
        
        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        for (int jaar = 2013; jaar > 1899; jaar --) {
            jaren.addElement(jaar);
        }
        this.jaar = new JComboBox(jaren);
        jDatums.add(dag);
        jDatums.add(maand);
        jDatums.add(jaar);
        
        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));

        this.btSluiten = new JButton("Sluiten");
        this.btOpslaan = new JButton("Opslaan");
        
        btOpslaan.addActionListener(this);
        btSluiten.addActionListener(this);
        
        
        this.jFieldPanel.add(lVoornaam);
        this.jFieldPanel.add(lTussenvoegsel);
        this.jFieldPanel.add(lAchternaam); 
        
        this.jFieldPanel.add(tfVoornaam);  
        this.jFieldPanel.add(tfTussenvoegsel);        
        this.jFieldPanel.add(tfAchternaam); 
        
        this.jFieldPanel.add(lPlaatsnaam);
        this.jFieldPanel.add(lPostcode);
        this.jFieldPanel.add(new JLabel(""));    
        
        this.jFieldPanel.add(tfPlaatsnaam);
        this.jFieldPanel.add(tfPostcode);
        this.jFieldPanel.add(new JLabel(""));        
        
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
        this.jFieldPanel.add(recht);
        this.jFieldPanel.add(new JLabel(""));
        
        this.jInputfields.add(jFieldPanel);

        this.jButtons.add(btOpslaan);

        this.jLogo.add(logo);

        this.add(jLogo, BorderLayout.NORTH);
        this.add(jInputfields, BorderLayout.WEST);
        this.add(jButtons, BorderLayout.SOUTH);
        
        this.tfId.setEditable(false);
        
        this.jInputfields.add(jFieldPanel);

        this.add(jInputfields, BorderLayout.CENTER);
        
        this.setVisible(true);       
    }    
    
    public void actionPerformed(ActionEvent ae) {
	// Nieuw Account

        if (ae.getSource() == btOpslaan) {
            //Controleer of de verplichte velden zijn ingevuld
            
            if (pfWachtwoord.getText().equals("")) { 
                JOptionPane.showMessageDialog( this,"Niet alle verplichte velden zijn ingevuld, Controleer de velden en probeer het opnieuw.");
            } else {
                //Hash het wachtwoord naar MD5
                String wachtwoord = pfWachtwoord.getText();
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(wachtwoord.getBytes(), 0, wachtwoord.length());
                    wachtwoord = new BigInteger(1, md.digest()).toString(16);
                } catch (Exception o) {
                    System.out.println("Hash Error:" + o);
                }
				
		// Datum maken
                datDag = "" + this.dag.getSelectedItem();
                if(datDag.length() < 2){ datumDag = (String) "0" + dag.getSelectedItem();}
                else{ datumDag = (String) "" + dag.getSelectedItem(); }
                
		datMaand = (String) maand.getSelectedItem();
                     if( datMaand.equals("Januari")		){ datumMaand = "01"; }
		else if( datMaand.equals("Februari")            ){ datumMaand = "02"; }
		else if( datMaand.equals("Maart")		){ datumMaand = "03"; }
		else if( datMaand.equals("April")		){ datumMaand = "04"; }
		else if( datMaand.equals("Mei")			){ datumMaand = "05"; }
		else if( datMaand.equals("Juni")		){ datumMaand = "06"; }
		else if( datMaand.equals("Juli")		){ datumMaand = "07"; }
		else if( datMaand.equals("Augustus")            ){ datumMaand = "08"; }
		else if( datMaand.equals("September")           ){ datumMaand = "09"; }
		else if( datMaand.equals("Oktober")		){ datumMaand = "10"; }
		else if( datMaand.equals("November")            ){ datumMaand = "11"; }
		else 						 { datumMaand = "12"; }
                     
                datumJaar = "" + jaar.getSelectedItem();
                
		String datum = datumDag + "-" + datumMaand + "-" + datumJaar;
                System.out.println(datum);
                //Sla de gegevens op in de database
                DbConnect a = new DbConnect();
                a.insertData("Locatie","00000", "00000" ,tfPlaatsnaam.getText(), tfStraatnaam.getText(), tfHuisnummer.getText(), tfToevoeging.getText(), tfPostcode.getText(), tfMobielnummer.getText(), "0");  
                String locatieIDs = a.getLocatieID("SELECT LocatieID From Locatie where Plaatsnaam = '" + tfPlaatsnaam.getText() + "' and Straatnaam = '" + tfStraatnaam.getText() + "' and Huisnummer = '" + tfHuisnummer.getText() + "' and Toevoeging = '" + tfToevoeging.getText() + "' and Postcode = '" + tfPostcode.getText() + "'");
                int locatieID = Integer.parseInt(locatieIDs);
                a.nieuweGebruiker("INSERT INTO `Persoon` (`PersoonID`, `LocatieID`, `Voornaam`, `Tussenvoegsel`, `Achternaam`, `Emailadres`, `Wachtwoord`, `Geboortedatum`, `Mobielnummer`, `Profielfoto`, `IBAN`, `Rechten`) VALUES ( '0','" + locatieID + "','" + tfVoornaam.getText() + "','" + tfTussenvoegsel.getText() + "','" + tfAchternaam.getText() + "','" + tfEmailadres.getText() + "','" + wachtwoord + "','" + datum + "','" + tfMobielnummer.getText() + "','" + null + "','" + tfIBANnummer.getText() + "', '0')");
                        //"Persoon", Integer.parseInt(locatieID), tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), wachtwoord, datum, tfMobielnummer.getText(), tfIBANnummer.getText(), "aaaaa");   
            }
        }
        
        if (ae.getSource() == btWijzig) {
             DbConnect dbc = new DbConnect();
             String[] waardes = this.getTekstvelden();
             dbc.updateGebruikerAccount(waardes);
             this.setVisible(false);
	}
        
        // Account wijzigen
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
        this.tfGeboortedatum.setText(specifiekePersoonGegevens[6]);
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
}