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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jelle
 */
public class GUI extends JFrame {

    private JTextField tfVoornaam, tfTussenvoegsel, tfAchternaam, tfPostcode, tfStraatnaam, tfHuisnummer, tfToevoeging, tfPlaatsnaam, tfEmailadres, tfWachtwoord, tfIBANnummer, tfMobielnummer, tfGeboortedatum;
    private JLabel lVoornaam, lTussenvoegsel, lAchternaam, lPostcode, lStraatnaam, lHuisnummer, lToevoeging, lPlaatsnaam, lEmailadres, lWachtwoord, lIBANnummer, lMobielnummer, lGeboortedatum, logo;
    private JButton btNieuw, btOpslaan, btNorth;
    private JPanel jIndex, jNorth, jWest, jEast, jSouth, jIndexPanel;

    public GUI() {
        super();
        this.setTitle("TZT Post");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 800);

        this.jIndex = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jIndexPanel = new JPanel();

        jIndexPanel.setLayout(new GridLayout(13, 2));



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
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Laurens\\Google Drive\\KBS HSR\\tztklein.png"));


        this.btNieuw = new JButton("Nieuw");
        this.btOpslaan = new JButton("Opslaan");
        this.btNorth = new JButton("Poep");

        this.jIndexPanel.add(lVoornaam);
        this.jIndexPanel.add(tfVoornaam);
        this.jIndexPanel.add(lTussenvoegsel);
        this.jIndexPanel.add(tfTussenvoegsel);
        this.jIndexPanel.add(lAchternaam);
        this.jIndexPanel.add(tfAchternaam);
        this.jIndexPanel.add(lGeboortedatum);
        this.jIndexPanel.add(tfGeboortedatum);
        this.jIndexPanel.add(lPostcode);
        this.jIndexPanel.add(tfPostcode);
        this.jIndexPanel.add(lStraatnaam);
        this.jIndexPanel.add(tfStraatnaam);
        this.jIndexPanel.add(lHuisnummer);
        this.jIndexPanel.add(tfHuisnummer);
        this.jIndexPanel.add(lToevoeging);
        this.jIndexPanel.add(tfToevoeging);
        this.jIndexPanel.add(lPlaatsnaam);
        this.jIndexPanel.add(tfPlaatsnaam);
        this.jIndexPanel.add(lEmailadres);
        this.jIndexPanel.add(tfEmailadres);
        this.jIndexPanel.add(lWachtwoord);
        this.jIndexPanel.add(tfWachtwoord);
        this.jIndexPanel.add(lIBANnummer);
        this.jIndexPanel.add(tfIBANnummer);
        this.jIndexPanel.add(lMobielnummer);
        this.jIndexPanel.add(tfMobielnummer);








        this.jIndex.add(jIndexPanel);

        this.jSouth.add(btNieuw);
        this.jSouth.add(btOpslaan);

        this.jNorth.add(logo);


        jWest.setPreferredSize(new Dimension(100, 200));
        jEast.setPreferredSize(new Dimension(100, 200));
        jSouth.setPreferredSize(new Dimension(200, 200));

        jIndex.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(jNorth, BorderLayout.NORTH);
        this.add(jIndex, BorderLayout.CENTER);
        this.add(jWest, BorderLayout.WEST);
        this.add(jEast, BorderLayout.EAST);
        this.add(jSouth, BorderLayout.SOUTH);




        this.setVisible(true);
    }
}
