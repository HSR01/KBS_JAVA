/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Jelle
 */
class AanmeldenPakket extends JPanel implements ActionListener{
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
        
        //voeg velden toe aan bottompanel
        bottompanel.add(submit);
        
        //activeer action listeners voor 2 buttons
        zoek.addActionListener(this);
        submit.addActionListener(this);
        
        //voeg alle onderdelen toe aan layout.
        this.add(toppanel, BorderLayout.NORTH);
        this.add(midpanel, BorderLayout.CENTER);
        this.add(bottompanel, BorderLayout.SOUTH);
        

        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        DbConnect dbc = new DbConnect();
       if (ae.getSource() == zoek) {
           //zoek button

           if (zoekveld.getText().equals("")) {
               //er is niks ingevuld toon foutmelding
               //maak jdialog omdat joptionpane niet werk in jpanel.
              JDialog jd = new JDialog();
              jd.setSize(200,100);
              jd.setTitle("Foutmelding");
              jd.add(new JLabel("U heeft geen zoekterm ingevuld."));
              jd.setVisible(true);
              
           } else {
               //er is wat ingevuld voer query uit.
               
           }
           
       } else if (ae.getSource() == submit) {
           //verstuur buttom
       }
    }
    
}
