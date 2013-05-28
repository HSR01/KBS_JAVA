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
    private JLabel gewicht, omschrijving;
    private JTextField  tgewicht, tomschrijving;
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
        gewicht = new JLabel("Gewicht");
        omschrijving = new JLabel("Omschrijving");
        
        tgewicht = new JTextField();
        tomschrijving = new JTextField();
        
        //voeg de velden toe aan het panel.
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
        if (ae.getSource() == submit) {
            String errors = "<html>";
            if (tgewicht.getText().equals(""))
                errors += "<br>Geen gewicht opgegeven.";
            if (tomschrijving.getText().equals(""))
                errors += "<br>Geen omschrijving opgegeven.";
            if (afzender.getPersoon() == null)
                errors += "<br>Geen afzender geselecteerd.";
            if (ontvanger.getPersoon() == null)
                errors += "<br>Geen ontvanger geselecteerd.";
            if (errors.equals("<html>")) {
                DbConnect dbc = new DbConnect();
                String[] pakket = new String[2];
                pakket[0] = tgewicht.getText().toString();
                pakket[1] = tomschrijving.getText().toString();
                try {
                    dbc.newVerzending(afzender.getPersoon(), ontvanger.getPersoon(), afzender.getLocatie(), ontvanger.getLocatie(), pakket);
                } catch (MultipleAdressesFoundException ex) {
                    Logger.getLogger(NieuweVerzending.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                errors += "</html>";
                JDialog jd = new JDialog();
                jd.setSize(400,175);
                jd.setTitle("Foutmelding - Verplichte velden niet ingevuld.");
                jd.add(new JLabel(errors));
                jd.setVisible(true);
            }
        }
    }
}
