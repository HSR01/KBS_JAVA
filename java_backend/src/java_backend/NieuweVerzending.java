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
        zoek.addActionListener(this);
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
        DbConnect dbc = new DbConnect();
       if (ae.getSource() == zoek) {
           //zoek button
           if (zoekveld.getText().equals("")) {
               //er is niks ingevuld toon foutmelding
               //maak jdialog omdat joptionpane niet werk in jpanel.
              JDialog jd = new JDialog();
              jd.setSize(200,175);
              jd.setTitle("Foutmelding");
              jd.add(new JLabel("U heeft geen zoekterm ingevuld."));
              jd.setVisible(true);
           } else {
               //   Initialiseer de gegevens en toon daarna de SelectDialog.
               Object[][] data = dbc.getPersonenWithCoordinates(zoekveld.getText().toString());
               String[] columnnames = { "PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "Postcode", "Huisnummer", "Toevoeging", "IBAN" };
               int[] columnsizes = { 40, 120, 50, 120, 50, 40, 40, 40, 10 };
               
               CustomJTable popup = new CustomJTable(columnnames, columnsizes, data);
               afzenderLocatie = dbc.getLocatieFromPersoonId(popup.result.toString());
           }
        } else if (ae.getSource() == submit) {
            //verstuur button
            if (afzenderLocatie == null
                    || tvoornaam.getText().equals("") 
                    || tachternaam.getText().equals("")
                    || tstraatnaam.getText().equals("")
                    || thuisnr.getText().equals("")
                    || tplaats.getText().equals("")
                    || tpostcode.getText().equals("")
                ) {
                String errors = "<html>";
                if (afzenderLocatie == null)
                    errors += "<br>Selecteer een verzender";
                if (tvoornaam.getText().equals(""))
                    errors += "<br>Voornaam is verplicht";
                if (tachternaam.getText().equals(""))
                    errors += "<br>Achternaam is verplicht";
                if (tstraatnaam.getText().equals(""))
                    errors += "<br>Straatnaam is verplicht";
                if (thuisnr.getText().equals(""))
                    errors += "<br>Huisnummer is verplicht";
                if (tplaats.getText().equals(""))
                    errors += "<br>Plaats is verplicht";
                if (tpostcode.getText().equals(""))
                    errors += "<br>Postcode is verplicht";
                
                errors += "</html>";
                JDialog jd = new JDialog();
                jd.setSize(400,175);
                jd.setTitle("Foutmelding - Verplichte velden niet ingevuld.");
                jd.add(new JLabel(errors));
                jd.setVisible(true);
            } else {
                // Valide formulier
                String[] data = new String[11];
                data[0] = tvoornaam.getText().toString();
                data[1] = ttussenvoegsel.getText().toString();
                data[2] = tachternaam.getText().toString();
                data[3] = tstraatnaam.getText().toString();
                data[4] = thuisnr.getText().toString();
                data[5] = ttoevoeging.getText().toString();
                data[6] = tpostcode.getText().toString();
                data[7] = tplaats.getText().toString();
                data[8] = ttelefoonnummer.getText().toString();
                data[9] = tgewicht.getText().toString();
                data[10] = tomschrijving.getText().toString();
                
                try {
                    dbc.newVerzending(afzenderLocatie, data);
                } catch (MultipleAdressesFoundException ex) {
                    Logger.getLogger(NieuweVerzending.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       }
    }
}
