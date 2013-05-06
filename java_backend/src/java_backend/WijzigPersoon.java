package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class WijzigPersoon extends JDialog implements ActionListener{
    private JTextField
            tfId,
            tfVoornaam,
            tfTussenvoegsel, 
            tfAchternaam,
            tfEmailadres, 
            tfWachtwoord,
            tfGeboortedatum,
            tfMobielnummer,
            tfProfielfoto,
            tfIBANnummer,
            tfRechten;
    
    private JLabel
            logo,
            lId,
            lVoornaam,
            lTussenvoegsel, 
            lAchternaam,
            lEmailadres, 
            lWachtwoord,
            lGeboortedatum,
            lMobielnummer,
            lProfielfoto,
            lIBANnummer,
            lRechten;
    
    private JButton
            btTerug,
            btWijzig;
    
    private JPanel
            jInputfields,
            jFieldPanel,  // Bevat de labels en invulvelden
            jNorth,
            jWest,
            jEast,
            jSouth;

    public WijzigPersoon(String[] specifiekePersoon){
        super();
        this.setTitle("TZT Post - Account Beheer");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 550);
        
        this.jInputfields = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jFieldPanel = new JPanel();

        jFieldPanel.setLayout(new GridLayout(16, 2));

        this.tfId = new JTextField(20);
        this.tfVoornaam = new JTextField(20);
        this.tfTussenvoegsel = new JTextField(20);
        this.tfAchternaam = new JTextField(20);
        this.tfGeboortedatum = new JTextField(20);
        this.tfEmailadres = new JTextField(20);
        this.tfWachtwoord = new JTextField(20);
        this.tfIBANnummer = new JTextField(20);
        this.tfMobielnummer = new JTextField(20);
        this.tfProfielfoto = new JTextField(20);
        this.tfRechten = new JTextField(20);
        
        this.tfId.setText(specifiekePersoon[0]);
        this.tfVoornaam.setText(specifiekePersoon[1]);
        this.tfTussenvoegsel.setText(specifiekePersoon[2]);
        this.tfAchternaam.setText(specifiekePersoon[3]);
        this.tfGeboortedatum.setText(specifiekePersoon[4]);
        this.tfEmailadres.setText(specifiekePersoon[5]);
        this.tfWachtwoord.setText(specifiekePersoon[6]);
        this.tfIBANnummer.setText(specifiekePersoon[7]);
        this.tfMobielnummer.setText(specifiekePersoon[8]);
        this.tfProfielfoto.setText(specifiekePersoon[9]);
        this.tfRechten.setText(specifiekePersoon[10]);
        
        

        this.lId = new JLabel("Persoon ID:");
        this.lVoornaam = new JLabel("Voornaam:");
        this.lTussenvoegsel = new JLabel("Tussenvoegsel:");
        this.lAchternaam = new JLabel("Achternaam:");
        this.lGeboortedatum = new JLabel("Geboortedatum:");
        this.lEmailadres = new JLabel("E-mailadres:");
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lIBANnummer = new JLabel("IBAN-nummer:");
        this.lMobielnummer = new JLabel("Mobielnummer:");
        this.lProfielfoto = new JLabel("Profiel Foto:");
        this.lRechten = new JLabel("Rechten:");
        
        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));

        this.btTerug = new JButton("Terug");
        this.btWijzig = new JButton("Wijzig");
        
        btTerug.addActionListener(this);
        btWijzig.addActionListener(this);
        
        this.jFieldPanel.add(lId);
        this.jFieldPanel.add(tfId);
        this.jFieldPanel.add(lVoornaam);
        this.jFieldPanel.add(tfVoornaam);
        this.jFieldPanel.add(lTussenvoegsel);
        this.jFieldPanel.add(tfTussenvoegsel);
        this.jFieldPanel.add(lAchternaam);
        this.jFieldPanel.add(tfAchternaam);
        this.jFieldPanel.add(lGeboortedatum);
        this.jFieldPanel.add(tfGeboortedatum);
        this.jFieldPanel.add(lEmailadres);
        this.jFieldPanel.add(tfEmailadres);
        this.jFieldPanel.add(lWachtwoord);
        this.jFieldPanel.add(tfWachtwoord);
        this.jFieldPanel.add(lIBANnummer);
        this.jFieldPanel.add(tfIBANnummer);
        this.jFieldPanel.add(lMobielnummer);
        this.jFieldPanel.add(tfMobielnummer);
        this.jFieldPanel.add(lProfielfoto);
        this.jFieldPanel.add(tfProfielfoto);
        this.jFieldPanel.add(lRechten);
        this.jFieldPanel.add(tfRechten);


        this.jInputfields.add(jFieldPanel);

        this.jSouth.add(btTerug);
        this.jSouth.add(btWijzig);

        this.jNorth.add(logo);
        

        this.add(jNorth, BorderLayout.NORTH);

        this.add(jInputfields, BorderLayout.CENTER);

        this.add(jSouth, BorderLayout.SOUTH);

        this.setVisible(true);
        

        
    }
        public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btWijzig) {
             DbConnect a = new DbConnect();
             a.insertData("Persoon",tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), tfWachtwoord.getText(), tfGeboortedatum.getText(), tfMobielnummer.getText(), tfIBANnummer.getText(), "aaaaa");   
             
	} 
        if (ae.getSource() == btTerug) {
            //this.hide();
            this.setVisible(false);
            GUI a = new GUI();
             
	}        
            
    }
    
}


/* Oude klasse staat hieronder
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 
public class WijzigPersoon extends JDialog implements ActionListener{
    private JTextField tfVoornaam, tfTussenvoegsel, tfAchternaam, tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam, tfEmailadres, tfWachtwoord, tfIBANnummer, tfMobielnummer, tfGeboortedatum;
    private JLabel lVoornaam, lTussenvoegsel, lAchternaam, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lEmailadres, lWachtwoord, lIBANnummer, lMobielnummer, lGeboortedatum, logo;
    private JButton btSluiten, btOpslaan, btNorth;
    private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;

    

    public WijzigPersoon(String ... value)  {
        super();
        this.setTitle("TZT Post - Nieuwe Klant");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 550);
        
        this.jInputfields = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jFieldPanel = new JPanel();

        jFieldPanel.setLayout(new GridLayout(13, 2));

        this.tfVoornaam = new JTextField(20);
        this.tfTussenvoegsel = new JTextField(20);
        this.tfAchternaam = new JTextField(20);
        this.tfGeboortedatum = new JTextField(20);
        this.tfPostcode = new JTextField(20);
        this.tfStraatnaam = new JTextField(20);
        this.tfHuisnummer = new JTextField(20);
        this.tfToevoeging = new JTextField(20);
        this.tfPlaatsnaam = new JTextField(20);
        this.tfEmailadres = new JTextField(20);
        this.tfWachtwoord = new JTextField(20);
        this.tfIBANnummer = new JTextField(20);
        this.tfMobielnummer = new JTextField(20);

        this.lVoornaam = new JLabel("Voornaam:");
        this.lTussenvoegsel = new JLabel("Tussenvoegsel:");
        this.lAchternaam = new JLabel("Achternaam:");
        this.lGeboortedatum = new JLabel("Geboortedatum:");
        this.lPostcode = new JLabel("Postcode:");
        this.lStraatnaam = new JLabel("Straatnaam:");
        this.lHuisnummer = new JLabel("Huisnummer:");
        this.lToevoeging = new JLabel("Toevoeging:");
        this.lPlaatsnaam = new JLabel("Plaatsnaam:");
        this.lEmailadres = new JLabel("E-mailadres:");
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lIBANnummer = new JLabel("IBAN-nummer:");
        this.lMobielnummer = new JLabel("Mobielnummer:");
        
        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));

        this.btSluiten = new JButton("Sluiten");
        this.btOpslaan = new JButton("Wijzig");
        
        btOpslaan.addActionListener(this);
        btSluiten.addActionListener(this);
        
        this.jFieldPanel.add(lVoornaam);
        this.jFieldPanel.add(tfVoornaam);
        this.jFieldPanel.add(lTussenvoegsel);
        this.jFieldPanel.add(tfTussenvoegsel);
        this.jFieldPanel.add(lAchternaam);
        this.jFieldPanel.add(tfAchternaam);
        this.jFieldPanel.add(lGeboortedatum);
        this.jFieldPanel.add(tfGeboortedatum);
        this.jFieldPanel.add(lPostcode);
        this.jFieldPanel.add(tfPostcode);
        this.jFieldPanel.add(lStraatnaam);
        this.jFieldPanel.add(tfStraatnaam);
        this.jFieldPanel.add(lHuisnummer);
        this.jFieldPanel.add(tfHuisnummer);
        this.jFieldPanel.add(lToevoeging);
        this.jFieldPanel.add(tfToevoeging);
        this.jFieldPanel.add(lPlaatsnaam);
        this.jFieldPanel.add(tfPlaatsnaam);
        this.jFieldPanel.add(lEmailadres);
        this.jFieldPanel.add(tfEmailadres);
        this.jFieldPanel.add(lWachtwoord);
        this.jFieldPanel.add(tfWachtwoord);
        this.jFieldPanel.add(lIBANnummer);
        this.jFieldPanel.add(tfIBANnummer);
        this.jFieldPanel.add(lMobielnummer);
        this.jFieldPanel.add(tfMobielnummer);


        this.jInputfields.add(jFieldPanel);

        this.jSouth.add(btSluiten);
        this.jSouth.add(btOpslaan);

        this.jNorth.add(logo);
        

        this.add(jNorth, BorderLayout.NORTH);

        this.add(jInputfields, BorderLayout.CENTER);

        this.add(jSouth, BorderLayout.SOUTH);

        this.setVisible(true);
        

        
    }
        public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btOpslaan) {
             DbConnect a = new DbConnect();
             a.insertData("Persoon",tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), tfWachtwoord.getText(), tfGeboortedatum.getText(), tfMobielnummer.getText(), tfIBANnummer.getText(), "aaaaa");   
             
	} 
        if (ae.getSource() == btSluiten) {
            //this.hide();
            this.setVisible(false);
            GUI a = new GUI();
             
	}        
            
    }
    
}
*/  