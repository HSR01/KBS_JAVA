/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Jelle
 */
class AanmeldenPakket extends JPanel {
    private JLabel zoeklabel, adres, postcode, huisnr, plaats, telefoonnummer, naam;
    private JTextField zoekveld, tadres, tpostcode, thuisnr, tplaats, ttelefoonnummer, tnaam;
    private JButton zoek, submit;
    
    
    public AanmeldenPakket() {
        //standaard instellingen
        this.setLayout(new BorderLayout());
        //instancieer top panel voor border layout
        JPanel toppanel = new JPanel();
        toppanel.setLayout(new FlowLayout());
        
        //instacnier alle velden voor top panel
        zoeklabel = new JLabel("Zoek Afzender");
        zoekveld = new JTextField(10);
        zoek = new JButton("Zoek");
        
        //voeg onderdelen toe aan toppanel
        toppanel.add(zoeklabel);
        toppanel.add(zoekveld);
        toppanel.add(zoek);
        
        //instancieer midpanel voor formulier
        JPanel midpanel = new JPanel();
        midpanel.setLayout(new GridLayout(10, 1));
        
        //instancieer velden voor midpanel
        adres = new JLabel("Adres");
        postcode = new JLabel("Postcode");
        huisnr = new JLabel("Huisnr");
        plaats = new JLabel("Plaats");
        telefoonnummer = new JLabel("Telefoonnummer");
        naam = new JLabel("Naam");
        
        tadres = new JTextField();
        tpostcode = new JTextField();
        thuisnr = new JTextField();
        tplaats = new JTextField();
        ttelefoonnummer = new JTextField();
        tnaam = new JTextField();
        
        //voeg de velden toe aan het panel.
        midpanel.add(adres);
        midpanel.add(tadres);
        midpanel.add(postcode);
        midpanel.add(tpostcode);
        midpanel.add(huisnr);
        midpanel.add(thuisnr);
        midpanel.add(plaats);
        midpanel.add(tplaats);
        midpanel.add(telefoonnummer);
        midpanel.add(ttelefoonnummer);
        midpanel.add(naam);
        midpanel.add(tnaam);
        
        //instancieer botompanel
        JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new FlowLayout());
        
        //instancieer velden voor bottompanel
        submit = new JButton("Verstuur");
        
        bottompanel.add(submit);
        //voeg alle onderdelen toe aan layout.
        this.add(toppanel, BorderLayout.NORTH);
        this.add(midpanel, BorderLayout.CENTER);
        this.add(bottompanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }
    
}
