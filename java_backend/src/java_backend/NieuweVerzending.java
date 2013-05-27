package java_backend;

import Database.DbConnect;
import GUI_helpers.CustomJTable;
import Geolocatie.MultipleAdressesFoundException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Leon Huzen en Jelle Smeets
 */
class NieuweVerzending extends JPanel implements ActionListener {
    private Locatie afzenderLocatie = null;
    private JLabel zoeklabel, voornaam, tussenvoegsel, achternaam, straatnaam, huisnr, toevoeging, postcode, plaats, telefoonnummer, gewicht, omschrijving, aankomst;
    private JTextField zoekveld, tvoornaam, ttussenvoegsel, tachternaam, tstraatnaam, thuisnr, ttoevoeging, tpostcode, tplaats, ttelefoonnummer, tgewicht, tomschrijving, taankomst;
    private JButton zoek, submit;
    private ZoekPersoon afzender, ontvanger;

    public NieuweVerzending() {
        this.setSize(200, 600);
        this.add(new JLabel("Nieuwe verzending"));
        this.setVisible(true);
        
        //standaard instellingen
        this.setLayout(new BorderLayout());
        //instancieer top panel voor border layout
        JPanel toppanel = new JPanel();
        toppanel.setSize(200, 200);
        toppanel.setLayout(new FlowLayout());
        
        //instancieer de 2 personen zoek classes.
        afzender = new ZoekPersoon("afzender");
        ontvanger = new ZoekPersoon("ontvanger");
        
        //voeg de zoekvelden toe aan het toppanel.
        toppanel.add(afzender);
        toppanel.add(ontvanger);
        
        //instancieer midpanel voor formulier
        JPanel midpanel = new JPanel();
        midpanel.setSize(200, 200);
        midpanel.setLayout(new GridLayout(13, 2));
        
        //instancieer velden voor midpanel
        voornaam = new JLabel("Voornaam");
        tussenvoegsel = new JLabel("Tussenvoegsel");
        achternaam = new JLabel("Achternaam");
        straatnaam = new JLabel("Straatnaam");
        huisnr = new JLabel("Huisnummer");
        toevoeging = new JLabel("Toevoeging");
        huisnr = new JLabel("Huisnr");
        postcode = new JLabel("Postcode");
        plaats = new JLabel("Plaats");
        telefoonnummer = new JLabel("Telefoonnummer");
        gewicht = new JLabel("Gewicht");
        omschrijving = new JLabel("Omschrijving");
        aankomst = new JLabel("Aankomst");
        
        tvoornaam = new JTextField();
        ttussenvoegsel = new JTextField();
        tachternaam = new JTextField();
        tstraatnaam = new JTextField();
        thuisnr = new JTextField();
        ttoevoeging = new JTextField();
        thuisnr = new JTextField();
        tpostcode = new JTextField();
        tplaats = new JTextField();
        ttelefoonnummer = new JTextField();
        tgewicht = new JTextField();
        tomschrijving = new JTextField();
        taankomst = new JTextField();
        
        //voeg de velden toe aan het panel.
        midpanel.add(voornaam);
        midpanel.add(tvoornaam);
        midpanel.add(tussenvoegsel);
        midpanel.add(ttussenvoegsel);
        midpanel.add(achternaam);
        midpanel.add(tachternaam);
        midpanel.add(straatnaam);
        midpanel.add(tstraatnaam);
        midpanel.add(huisnr);
        midpanel.add(thuisnr);
        midpanel.add(toevoeging);
        midpanel.add(ttoevoeging);
        midpanel.add(postcode);
        midpanel.add(tpostcode);
        midpanel.add(plaats);
        midpanel.add(tplaats);
        midpanel.add(telefoonnummer);
        midpanel.add(ttelefoonnummer);
        midpanel.add(gewicht);
        midpanel.add(tgewicht);
        midpanel.add(omschrijving);
        midpanel.add(tomschrijving);
        
        //instancieer botompanel
        JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new FlowLayout());
        bottompanel.setSize(50, 200);
        
        //instancieer velden voor bottompanel
        submit = new JButton("Verstuur");
        
        //voeg velden toe aan bottompanel
        bottompanel.add(submit);
        
        //activeer action listeners voor 2 buttons
        //zoek.addActionListener(this);
        submit.addActionListener(this);
        
        //voeg alle onderdelen toe aan layout.
        this.add(toppanel, BorderLayout.NORTH);
        this.add(midpanel, BorderLayout.CENTER);
        this.add(bottompanel, BorderLayout.SOUTH);
        
        // Nog even zichtbaar
        this.setVisible(true);
    }     

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit){
            DbConnect dbc = new DbConnect();
            if(afzender.getPersoon() != null && ontvanger.getPersoon() != null){
               String[] pakket = new String[2];
               pakket[0] = tgewicht.getText().toString();
               pakket[1] = tomschrijving.getText().toString();
               try {
                    dbc.newVerzending(afzender.getPersoon(), ontvanger.getPersoon(), afzender.getLocatie(), ontvanger.getLocatie(), pakket);
                } catch (MultipleAdressesFoundException ex) {
                    Logger.getLogger(NieuweVerzending.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //niet alles ingevuld.
            }
        }
    }
}
