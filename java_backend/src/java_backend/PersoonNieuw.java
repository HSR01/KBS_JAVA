/**
 *
 * @author: Dominique
 */
package java_backend;

import Database.DbConnect;
import Geolocatie.Coordinaten;
import Geolocatie.Geocoding;
import Geolocatie.MultipleAdressesFoundException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PersoonNieuw extends JPanel implements ActionListener{
    private JTextField tfId, tfVoornaam, tfTussenvoegsel, tfAchternaam, tfEmailadres, tfMobielnummer, tfIBANnummer;
    private JPasswordField pfWachtwoord;
    private JLabel lVoornaam, lTussenvoegsel, informatie, lAchternaam, lEmailadres, lWachtwoord, lGeboortedatum, lMobielnummer, lIBANnummer, lRechten, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lTztpoint;
    private JPanel jInputfields, jFieldPanel, jButtons, jDatums;        
    private JTextField tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam;
    private JButton btOpslaan;
    private String datumDag, datumMaand, datumJaar, datDag, datMaand, datum, rechten, latitude, longitude;
    private JComboBox maand, dag, jaar, recht, tztpoint;
    private Coordinaten cords;
    
    public PersoonNieuw() {
        super();
        
        // Aanmaken Panels
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

        this.btOpslaan = new JButton("Opslaan");
        btOpslaan.addActionListener(this);
        
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
        
        this.jFieldPanel.add(new JLabel(""));
        this.jFieldPanel.add(lRechten);
        this.jFieldPanel.add(new JLabel(""));
        
        this.jFieldPanel.add(new JLabel(""));
        this.jFieldPanel.add((JComboBox) recht);
        this.jFieldPanel.add(new JLabel(""));
        
        this.jInputfields.add(jFieldPanel);

        this.jButtons.add(btOpslaan);
        
        this.informatie =  new JLabel("Vul alle gegevens in om een account aan te maken.");
        
        JPanel top = new JPanel();
        top.add(informatie);

        this.add(jInputfields, BorderLayout.WEST);
        this.add(jButtons, BorderLayout.SOUTH);
        this.add(top, BorderLayout.NORTH);
        
        this.tfId.setEditable(false);
        
        this.jInputfields.add(jFieldPanel);

        this.add(jInputfields, BorderLayout.CENTER);
        
        this.setVisible(true);       
    }    
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btOpslaan) {
            //Controleer of de verplichte velden zijn ingevuld            
            if (pfWachtwoord.getText().equals("")   ||
                tfVoornaam.getText().equals("")     ||
                tfAchternaam.getText().equals("")   ||
                tfPlaatsnaam.getText().equals("")   ||
                tfPostcode.getText().equals("")     ||
                tfStraatnaam.getText().equals("")   ||
                tfHuisnummer.getText().equals("")   ||
                tfIBANnummer.getText().equals("")   ||                    
                tfMobielnummer.getText().equals("") ||
                tfEmailadres.getText().equals("")   ||
                tfAchternaam.getText().equals("")   ||
                tfMobielnummer.getText().equals("")){ 
                JOptionPane.showMessageDialog( this,"Niet alle verplichte velden zijn ingevuld, Controleer de velden en probeer het opnieuw.");
            }else if(!isInteger(tfHuisnummer.getText())){
                 JOptionPane.showMessageDialog( this,"Het huisnummer is geen nummer.");
            }else if(!isValidEmailAddress(tfEmailadres.getText())){
                JOptionPane.showMessageDialog( this,"Het e-mail adres is ongeldig.");
            }else {
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
                
		datum = datumDag + "-" + datumMaand + "-" + datumJaar;
                
                // Rechten
                
                rechten = (String) recht.getSelectedItem();
                int gebruikerniveau = 0;
                if( rechten.equals("Geblokkeerd")                       ){ gebruikerniveau = 0; }
                if( rechten.equals("Verzender / Ontvanger / Klanten")   ){ gebruikerniveau = 1; }
                if( rechten.equals("TZT Point / BPS'er")                ){ gebruikerniveau = 2; }
                if( rechten.equals("TZT Medewerker")                    ){ gebruikerniveau = 3; }
                if( rechten.equals("Hoofdkantoor")                      ){ gebruikerniveau = 4; }
                
                //Sla de gegevens op in de database
                Geocoding geo = new Geocoding();
                Boolean geosucces = null;
                try {
                    cords = geo.QueryAndGetCoordinates(tfPlaatsnaam.getText(), tfStraatnaam.getText(), Integer.parseInt(tfHuisnummer.getText()));
                    this.latitude = "" + cords.Latitude;
                    this.longitude = "" + cords.Longitude;
                    geosucces = true;
                } catch (MultipleAdressesFoundException ex) {
                    JOptionPane.showMessageDialog( this,"Er gaat iets mis met toevoegen van de locatie.");
                    geosucces = false;
                }
                //als er geen locatie is mag de persoon ook niet worden toegevoegd.
                if(geosucces){
                    // alle queries
                    // Connectie aanmaken
                    DbConnect dbc = new DbConnect();
                    // Locatie inserten
                    dbc.insertData("Locatie", this.latitude, this.longitude ,tfPlaatsnaam.getText(), tfStraatnaam.getText(), tfHuisnummer.getText(), tfToevoeging.getText(), tfPostcode.getText(), tfMobielnummer.getText(), "0");  
                    // LocatieID ophalen van de insert query
                    int locatieID = dbc.getLocatieID("SELECT LocatieID From Locatie where Plaatsnaam = '" + tfPlaatsnaam.getText() + "' and Straatnaam = '" + tfStraatnaam.getText() + "' and Huisnummer = '" + tfHuisnummer.getText() + "' and Toevoeging = '" + tfToevoeging.getText() + "' and Postcode = '" + tfPostcode.getText() + "'");
                    // Gebruiekr inserten met bijgevoegd locatieID
                    dbc.nieuweGebruiker("INSERT INTO `Persoon` (`PersoonID`, `LocatieID`, `Voornaam`, `Tussenvoegsel`, `Achternaam`, `Emailadres`, `Wachtwoord`, `Geboortedatum`, `Mobielnummer`, `Profielfoto`, `IBAN`, `Rechten`) VALUES ( '0','" + locatieID + "','" + tfVoornaam.getText() + "','" + tfTussenvoegsel.getText() + "','" + tfAchternaam.getText() + "','" + tfEmailadres.getText() + "','" + wachtwoord + "','" + datum + "','" + tfMobielnummer.getText() + "','" + null + "','" + tfIBANnummer.getText() + "','" + gebruikerniveau + "')");
                    // PersoonID ophalen
                    int persoonID = dbc.getPersoonID("SELECT PersoonID FROM `Persoon`  Where Emailadres = '" + tfEmailadres.getText() + "'");
                    // Insert naar Persoon_Locatie voor koppeling
                    dbc.nieuweGebruiker("INSERT INTO `Persoon_Locatie` (LocatieID, PersoonID) VALUES (" + locatieID + "," + persoonID + ")");
                    JOptionPane.showMessageDialog( this,"De persoon is toegevoegd!");
                }

            }
        }
    }    public boolean isInteger( String input ){  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch( Exception e)  
       {  
          return false;  
       }  
    } 
   public  boolean isValidEmailAddress(String email){
    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    Matcher m = p.matcher(email);
    boolean matchFound = m.matches();

    if(matchFound){
        return true;
    }else{
        return false;
        }
    }
}