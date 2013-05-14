/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author Jelle
 * @author Daniel
 */
public class GUI extends JFrame implements ActionListener {
    private JLabel  card1logo, lWachtwoord, lEmailadres;
    private JButton btNieuw, btLogin, btNorth, btNieuwBPS, btSluiten, btZoek;
    private CardLayout cl = new CardLayout();
    private JPanel cardHolder = new JPanel(cl);
    private JPanel login, logincenter, buttonssouth, buttonsnorth;
    private JTextField tfEmailadres, tfZoekveld;
    private JPasswordField pfWachtwoord;
    private Persoon persoon;
    //private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;
    
    public GUI() {
        //Standaard instellingen hoofdscherm
        super();
        this.setTitle("TZT Post");
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);        
        //set persoon op null om aan te tonen dat er geen ingelogde is.
        this.persoon = null;
        //Instellingen eerste pagina.
        JPanel Card1 = new JPanel();
        Card1.setLayout(new BorderLayout());
        //Card1.add();
        this.card1logo = new JLabel();
        
        //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
        this.card1logo.setIcon(new javax.swing.ImageIcon("http://www.tztpost.nl/tztklein.png"));
        //this.card1logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jelle\\Pictures\\zoidberg.png"));

        
        //loginscherm voor card1
        this.login = new JPanel();
        this.logincenter = new JPanel();
        this.buttonssouth = new JPanel();
        this.buttonsnorth = new JPanel();

        //kies borderlayout
        this.login.setLayout(new BorderLayout()); 
        this.logincenter.setLayout(new FlowLayout());
        this.buttonssouth.setLayout(new FlowLayout());
        this.buttonsnorth.setLayout(new FlowLayout());
        
       // this.logincenter.setSize(50, 50);
        //instancieer buttons en velden.
        this.btLogin = new JButton("Log in");
        this.tfEmailadres = new JTextField(20);
        this.pfWachtwoord = new JPasswordField(20);
        
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lEmailadres = new JLabel("E-mailadres:");
        
        this.btNieuwBPS = new JButton("Nieuwe BPS");
        this.btSluiten = new JButton("Sluit");
        
        this.tfZoekveld = new JTextField(20);
        this.btZoek = new JButton("Zoek");
        
        
        //voeg velden toe aan login center
        this.logincenter.add(lEmailadres);
        this.logincenter.add(tfEmailadres);
        this.logincenter.add(lWachtwoord);
        this.logincenter.add(pfWachtwoord);
        this.logincenter.add(btLogin);
                
        //voeg velden toe aan loginpanel
        this.login.add(this.logincenter);
        
        this.buttonssouth.add(this.btNieuwBPS);
        this.buttonssouth.add(this.btSluiten);
        
        this.buttonsnorth.add(this.tfZoekveld);
        this.buttonsnorth.add(this.btZoek);
        
        
        //voeg logo toe aan loginpanel
        Card1.add(this.card1logo, BorderLayout.NORTH);
        //voeg panel toe aan loginscherm.
        Card1.add(this.login, BorderLayout.CENTER);
        
        
        //instellingen tweede pagina/card.
        JPanel Card2 = new JPanel();
        Card2.setLayout(new BorderLayout());
        Card2.add( new AccountsBeherenTabel(), BorderLayout.CENTER);
        Card2.add(buttonssouth, BorderLayout.SOUTH);
        Card2.add(buttonsnorth, BorderLayout.NORTH);
        
        //controle of DB connectie geset is.
        DbConnect dbc = new DbConnect();
        //als er connectie is laad dan ook alle items die daar bij horen.
        if(dbc.checkConnection()){
            cardHolder.add(Card1);
            cardHolder.add(Card2);   
            this.add(cardHolder);
        }else{
            //als er geen connectie is laad dan een scherm met foutmelding.
            JPanel error = new JPanel();
            error.setLayout(new FlowLayout());
            JLabel label = new JLabel("Er is geen verbinding met de database! Herstart de applicatie om het nog een keer te proberen.");
            error.add(label);
            this.add(error);
        }
        

        

        //Actionlistener toevoegen voor btLogin.
        btLogin.addActionListener(this);
        btNieuwBPS.addActionListener(this);
        
        //alles tonen
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
            //Acties voor login button
            if (ae.getSource() == btLogin) {
                //instancieer databaseconnectie
                DbConnect a = new DbConnect();
            try {
                //controleer de gegevens. allebei niet leeg.
                if(tfEmailadres.getText().equals("") || pfWachtwoord.getText().equals("")){
                    //show message dialog met foutmelding.
                    JOptionPane.showMessageDialog(rootPane, "Niet alle verplichte velden zijn ingevuld.", "Waarschuwing", 2);
                }else{
                    //maak Persoon aan aan de hand van inloggegevens en methode in inlogdata
                    //dit is een attribuut zodat deze beschikbaar is in de gehele GUI.
                    this.persoon = a.getLoginData(tfEmailadres.getText(), pfWachtwoord.getText(), true);
                    if(this.persoon != null){
                        //succesvol ingelogd
                        
                        //alleen bij succesvolle inlog mag iemand door naar de volgende card.
                        this.cl.next(this.cardHolder);
                    } else{
                        //show error dialog, inloggen is niet gelukt, object is leeg (controle en afhandeling in methode getLoginData
                        JOptionPane.showMessageDialog(rootPane, "Het inloggen is niet gelukt!", "Waarschuwing", 2);
                        //zet het formulier weer op leeg om opnieuw in te kunnen loggen.
                        tfEmailadres.setText("");
                        pfWachtwoord.setText("");    
                        //mag niet door naar de volgende card.
                    }
                }   
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }   
        
            }
            if (ae.getSource() == btNieuwBPS) {
             WijzigPersoon p = new WijzigPersoon();   
            }
            
            if (ae.getSource() == btSluiten) {
             this.setVisible(false);
            }
        }
}
