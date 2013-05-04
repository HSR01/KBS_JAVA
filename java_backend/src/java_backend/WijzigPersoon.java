/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class WijzigPersoon extends JDialog implements ActionListener{
    private JTextField tfVoornaam, tfTussenvoegsel, tfAchternaam, tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam, tfEmailadres, tfWachtwoord, tfIBANnummer, tfMobielnummer, tfGeboortedatum;
    private JLabel lVoornaam, lTussenvoegsel, lAchternaam, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lEmailadres, lWachtwoord, lIBANnummer, lMobielnummer, lGeboortedatum, logo;
    private JButton btSluiten, btOpslaan;
    private JPanel jLogo, jFieldPanel, jInputfields, jProfielfoto, jButtons;

    

    public WijzigPersoon(String ... value)  {
        super();
        this.setTitle("TZT Post - Nieuwe Klant");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 550);   

        this.jLogo = new JPanel();
        this.jInputfields = new JPanel();
        this.jFieldPanel = new JPanel();
        this.jProfielfoto = new JPanel();
        this.jButtons = new JPanel();
        

        //Laad profielfoto uit web omgeving
        try{
            URL url = new URL("http://tztpost.nl/images/Profielfotos/geenfoto.jpg");
            Image im = ImageIO.read(url);
            JLabel lblimage = new JLabel(new ImageIcon(im));
            jProfielfoto.add(lblimage, BorderLayout.CENTER);
        }catch(Exception e){
            System.out.println("Profielfoto Error: " + e);
        }
        
        //Layout voor inputfields
        jFieldPanel.setLayout(new GridLayout(10, 8, 18, 6));

        this.tfVoornaam = new JTextField(8);
        this.tfTussenvoegsel = new JTextField("",8);
     
        this.tfAchternaam = new JTextField(8);
        this.tfGeboortedatum = new JTextField(8);
        this.tfPostcode = new JTextField(8);
        this.tfStraatnaam = new JTextField(8);
        this.tfHuisnummer = new JTextField(8);
        this.tfToevoeging = new JTextField(8);
        this.tfPlaatsnaam = new JTextField(8);
        this.tfEmailadres = new JTextField(8);
        this.tfWachtwoord = new JTextField(8);
        this.tfIBANnummer = new JTextField(8);
        this.tfMobielnummer = new JTextField(8);

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

        this.jFieldPanel.add(tfGeboortedatum);
        this.jFieldPanel.add(tfIBANnummer);
        this.jFieldPanel.add(new JLabel(""));  

        this.jFieldPanel.add(lMobielnummer);
        this.jFieldPanel.add(lEmailadres);
        this.jFieldPanel.add(lWachtwoord);

        this.jFieldPanel.add(tfMobielnummer);
        this.jFieldPanel.add(tfEmailadres);
        this.jFieldPanel.add(tfWachtwoord);
        
        this.jInputfields.add(jFieldPanel);

        this.jButtons.add(btSluiten);
        this.jButtons.add(btOpslaan);

        this.jLogo.add(logo);
        

        this.add(jLogo, BorderLayout.NORTH);

        this.add(jInputfields, BorderLayout.WEST);
        this.add(jProfielfoto, BorderLayout.EAST);
        this.add(jButtons, BorderLayout.SOUTH);

        this.setVisible(true);
        

        
    }
        public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btOpslaan) {
            //Controleer of de verplichte velden zijn ingevuld
            if (tfWachtwoord.getText().equals("")){ 
                JOptionPane.showMessageDialog( this,"Niet alle verplichte velden zijn ingevuld, Controleer de velden en probeer het opnieuw.");
            }else{
                //Hash het wachtwoord naar MD5
                String wachtwoord = tfWachtwoord.getText();
                try{
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(wachtwoord.getBytes(), 0, wachtwoord.length());
                    wachtwoord = new BigInteger(1, md.digest()).toString(16);
                }catch(Exception o){
                    System.out.println("Hash Error:" + o);
                }
                //Sla de gegevens op in de database
                DbConnect a = new DbConnect();
                a.insertData("Persoon",tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), wachtwoord, tfGeboortedatum.getText(), tfMobielnummer.getText(), tfIBANnummer.getText(), "aaaaa");   
                a.insertData("Locatie","00000", "00000" ,tfPlaatsnaam.getText(), tfStraatnaam.getText(), tfHuisnummer.getText(), tfToevoeging.getText(), tfPostcode.getText(), tfMobielnummer.getText(), "0");  
                
            }
        }
        
        if (ae.getSource() == btSluiten) {
            //this.hide();
            this.setVisible(false);
            GUI a = new GUI();
             
	}        
            
    }
    
}
