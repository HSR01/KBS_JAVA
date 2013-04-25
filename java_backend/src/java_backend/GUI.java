/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class GUI extends JFrame implements ActionListener {

    private JTextField tfVoornaam, tfTussenvoegsel, tfAchternaam, tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam, tfEmailadres, tfWachtwoord, tfIBANnummer, tfMobielnummer, tfGeboortedatum;
    private JLabel lVoornaam, lTussenvoegsel, lAchternaam, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lEmailadres, lWachtwoord, lIBANnummer, lMobielnummer, lGeboortedatum, logo;
    private JButton btNieuw, btOpslaan, btNorth;
    private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;
    private JTable table;
    private String tableinhoud[] = {"ID", "Naam"};
    

    public GUI() {
        super();
        this.setTitle("TZT Post");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        
        DbConnect dbc = new DbConnect();
        try{
            dbc.getUsers();
            table = new JTable(dbc.getUsers(), tableinhoud);
        }
        catch(Exception e){
           System.out.println(e.getMessage());
        }
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

        this.btNieuw = new JButton("Nieuw");
        this.btOpslaan = new JButton("Opslaan");
        
        btOpslaan.addActionListener(this);
        btNieuw.addActionListener(this);
        
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
        this.jWest.add(table);

        this.jInputfields.add(jFieldPanel);

        this.jSouth.add(btNieuw);
        this.jSouth.add(btOpslaan);

        this.jNorth.add(logo);
        

        this.add(jNorth, BorderLayout.NORTH);
        this.add(table, BorderLayout.WEST);
        this.add(jInputfields, BorderLayout.EAST);

        this.add(jSouth, BorderLayout.SOUTH);

        this.setVisible(true);
        

        
    }
        public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btOpslaan) {
             DbConnect a = new DbConnect();
             a.insertData("Persoon",tfVoornaam.getText(), tfTussenvoegsel.getText(), tfAchternaam.getText(), tfEmailadres.getText(), tfWachtwoord.getText(), tfGeboortedatum.getText(), tfMobielnummer.getText(), tfIBANnummer.getText(), "aaaaa");
             
             
             
	}            
            
        }
}
