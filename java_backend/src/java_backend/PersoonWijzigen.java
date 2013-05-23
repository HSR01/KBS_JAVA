package java_backend;

import Geolocatie.MultipleAdressesFoundException;
import Geolocatie.Coordinaten;
import Geolocatie.Geocoding;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

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
        btVerwijder  = new JButton("Verwijder");
        
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
            //Controleer of de verplichte velden zijn ingevuld            
            if (tfVoornaam.getText().equals("")     ||
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
                
		datum = datumDag + "-" + datumMaand + "-" + datumJaar;
                
                // Rechten
                
                rechten = (String) recht.getSelectedItem();
                String gebruikerniveau = "0";
                if( rechten.equals("Geblokkeerd")                       ){ gebruikerniveau = "0"; }
                if( rechten.equals("Verzender / Ontvanger / Klanten")   ){ gebruikerniveau = "1"; }
                if( rechten.equals("TZT Point / BPS'er")                ){ gebruikerniveau = "2"; }
                if( rechten.equals("TZT Medewerker")                    ){ gebruikerniveau = "3"; }
                if( rechten.equals("Hoofdkantoor")                      ){ gebruikerniveau = "4"; }
                
                //Sla de gegevens op in de database
                Geocoding geo = new Geocoding();
                try {
                    cords = geo.QueryAndGetCoordinates(tfPlaatsnaam.getText(), tfStraatnaam.getText(), Integer.parseInt(tfHuisnummer.getText()));
                    this.latitude = "" + cords.Latitude;
                    this.longitude = "" + cords.Longitude;
                } catch (MultipleAdressesFoundException ex) {
                    Logger.getLogger(PersoonNieuw.class.getName()).log(Level.SEVERE, null, ex);
                }
                                    
                String Persoongegevens[] = new String[18];
                Persoongegevens[0] = tfId.getText();
                Persoongegevens[1] = tfVoornaam.getText();
                Persoongegevens[2] = tfTussenvoegsel.getText();
                Persoongegevens[3] = tfAchternaam.getText();
                Persoongegevens[4] = wachtwoord;
                Persoongegevens[5] = tfEmailadres.getText();
                Persoongegevens[6] = datum;
                Persoongegevens[7] = gebruikerniveau;
                Persoongegevens[8] = tfMobielnummer.getText();
                Persoongegevens[9] = tfIBANnummer.getText();
                
                Persoongegevens[10] = tfHuisnummer.getText();
                Persoongegevens[11] = tfPlaatsnaam.getText();
                Persoongegevens[12] = tfStraatnaam.getText();
                Persoongegevens[13] = tfToevoeging.getText();
                
                if( tztpoint.getSelectedItem().equals("Nee")){
                    Persoongegevens[14] = "0";
                }
                else{
                    Persoongegevens[14] = "1";
                }
                
                Persoongegevens[15] = tfPostcode.getText();
                Persoongegevens[16] = latitude;
                Persoongegevens[17] = longitude;
                
                // alle queries
                // Connectie aanmaken
                
                DbConnect a = new DbConnect();
                if (pfWachtwoord.getText().equals("")){ a.updateGebruikerAccount2(Persoongegevens); }
                else { a.updateGebruikerAccount(Persoongegevens); }
                
                AccountsBeherenTabel.aTable.setModel(AccountsBeherenTabel.FillTabel());
                AccountsBeherenTabel.aTable.repaint();
                this.setVisible(false);
            }
        }
        
        // Trajecten wijzigen
        if (ae.getSource() == btTraject) {
            System.out.println("Traject");
            PersoonTraject personenOverzicht = new PersoonTraject(Integer.parseInt(tfId.getText()));
        }
        if (ae.getSource() == btVerwijder){
            DbConnect dbc = new DbConnect();
            
            dbc.verwijderPersoon("DELETE FROM Persoon WHERE PersoonID = " + tfId.getText());
            this.setVisible(false);
        }
        if (ae.getSource() == btSluiten){
            this.setVisible(false);
        }
    }
    
    public void vulTekstvelden(String[] specifiekePersoonGegevens, String[] specifiekePersoonLocatie) {
        this.tfId.setText(specifiekePersoonGegevens[0]);
        this.tfVoornaam.setText(specifiekePersoonGegevens[1]);
        this.tfTussenvoegsel.setText(specifiekePersoonGegevens[2]);
        this.tfAchternaam.setText(specifiekePersoonGegevens[3]);
        this.tfEmailadres.setText(specifiekePersoonGegevens[4]);
        
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
       
        setSelectedValue(dag, DatumDag);
        setSelectedValueMaand(maand, DatumMaand);
        setSelectedValue(jaar, DatumJaar);
        
        this.tfIBANnummer.setText(specifiekePersoonGegevens[7]);
        this.tfMobielnummer.setText(specifiekePersoonGegevens[9]);
        
        String dropdownRechten = specifiekePersoonGegevens[10];
        setSelectedValueRecht(recht, dropdownRechten);
        
        this.tfPlaatsnaam.setText(specifiekePersoonLocatie[0]);
        this.tfStraatnaam.setText(specifiekePersoonLocatie[1]);
        this.tfHuisnummer.setText(specifiekePersoonLocatie[2]);
        this.tfToevoeging.setText(specifiekePersoonLocatie[3]);
        this.tfPostcode.setText(specifiekePersoonLocatie[4]);
        setSelectedValueTZT(tztpoint, specifiekePersoonLocatie[5]);
    }
    
    public void setSelectedValue(JComboBox comboBox, String value){
        String item;
        for (int i = 0; i < comboBox.getItemCount(); i++){
            item = "" + comboBox.getItemAt(i);
            if( item.length() < 2){ item = "0" + item;}
            if (item.equals(value)){
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public void setSelectedValueMaand(JComboBox comboBox, String value){
        if( value.equals("01")){ comboBox.setSelectedIndex(0); }
        if( value.equals("02")){ comboBox.setSelectedIndex(1); }
        if( value.equals("03")){ comboBox.setSelectedIndex(2); }
        if( value.equals("04")){ comboBox.setSelectedIndex(3); }
        if( value.equals("05")){ comboBox.setSelectedIndex(4); }
        if( value.equals("06")){ comboBox.setSelectedIndex(5); }
        if( value.equals("07")){ comboBox.setSelectedIndex(6); }
        if( value.equals("08")){ comboBox.setSelectedIndex(7); }
        if( value.equals("09")){ comboBox.setSelectedIndex(8); }
        if( value.equals("10")){ comboBox.setSelectedIndex(9); }
        if( value.equals("11")){ comboBox.setSelectedIndex(10); }
        if( value.equals("12")){ comboBox.setSelectedIndex(11); }
    }
    
    public void setSelectedValueTZT(JComboBox comboBox, String value){
        if( value.equals("0")){ comboBox.setSelectedIndex(0); }
        else { comboBox.setSelectedIndex(1); }
    }
    
    public void setSelectedValueRecht(JComboBox comboBox, String value){
        if( value.equals("0")){ comboBox.setSelectedIndex(0); }
        if( value.equals("1")){ comboBox.setSelectedIndex(1); }
        if( value.equals("2")){ comboBox.setSelectedIndex(2); }
        if( value.equals("3")){ comboBox.setSelectedIndex(3); }
        if( value.equals("4")){ comboBox.setSelectedIndex(4); }
    }     
}