/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    
    //menu onderdelen.
    private JMenuBar menubar;
    private JMenu bestand;
    private JMenuItem afsluiten, acbeheer, meldenpakket, feedbackbeheer, statistieken, blokbps, aanmeldenpakket;

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
        
        //instancieer menu
        this.menubar = new JMenuBar();
        this.bestand = new JMenu("Bestand");
        
        //instancieer menuitems
        this.acbeheer = new JMenuItem("Accounts beheren");
        this.meldenpakket = new JMenuItem("Volgen pakket");
        this.feedbackbeheer = new JMenuItem("Feedback beheren");
        this.statistieken = new JMenuItem("Statistieken");
        this.blokbps = new JMenuItem("Blokkeren BPS'ers");
        this.aanmeldenpakket = new JMenuItem("Aanmelden pakket");
        this.afsluiten = new JMenuItem("Afsluiten");
        
        //voeg items zo aan het menu.
        this.bestand.add(this.acbeheer);
        this.bestand.add(this.meldenpakket);
        this.bestand.add(this.feedbackbeheer);
        this.bestand.add(this.statistieken);
        this.bestand.add(this.blokbps);
        this.bestand.add(this.aanmeldenpakket);
        this.bestand.add(this.afsluiten);
        this.menubar.add(this.bestand);

        this.setJMenuBar(menubar);
        
        //try catch om logo van internet op te halen.
        try{
        BufferedImage img = ImageIO.read(new URL("http://www.tztpost.nl/tztklein.png"));
        //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
        this.card1logo.setIcon(new javax.swing.ImageIcon(img));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


        
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
        
        this.btNieuwBPS = new JButton("Nieuw");
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
        
        //VUL KAARTEN
        //voeg logo toe aan loginpanel
        Card1.add(this.card1logo, BorderLayout.NORTH);
        //voeg panel toe aan loginscherm.
        Card1.add(this.login, BorderLayout.CENTER);

        JPanel Card3 = new JPanel();
        JPanel Card4 = new JPanel();
        JPanel Card5 = new JPanel();
        JPanel Card6 = new JPanel();
        JPanel Card7 = new AanmeldenPakket();


        JPanel Card2 = new JPanel();
        Card2.setLayout(new BorderLayout());
        Card2.add( new AccountsBeherenTabel());
        Card2.add(buttonssouth, BorderLayout.SOUTH);
        Card2.add(buttonsnorth, BorderLayout.NORTH);
        


        

        
        //controle of DB connectie geset is.
        DbConnect dbc = new DbConnect();
        //als er connectie is laad dan ook alle items die daar bij horen.
        if(dbc.checkConnection()){
       //voeg kaarten toe aan kaartenhouder de string is voor naam om deze later aan te roepen.
       cardHolder.add(Card1, "login");
       cardHolder.add(Card2, "acbeheer");   
       cardHolder.add(Card3, "meldenpakket");  
       cardHolder.add(Card4, "feedbackbeheer");
       cardHolder.add(Card5, "statistieken");
       cardHolder.add(Card6, "blokbps");
       cardHolder.add(Card7, "aanmeldenpakket");   
            this.add(cardHolder);
        }else{
            //als er geen connectie is laad dan een scherm met foutmelding.
            JPanel error = new JPanel();
            error.setLayout(new FlowLayout());
            JLabel label = new JLabel("Er is geen verbinding met de database! Herstart de applicatie om het nog een keer te proberen.");
            error.add(label);
            this.add(error);
        }
        

        
        
        //Actionlistener toevoegen
        btLogin.addActionListener(this);
        btNieuwBPS.addActionListener(this);
        afsluiten.addActionListener(this);
        acbeheer.addActionListener(this);
        meldenpakket.addActionListener(this);
        feedbackbeheer.addActionListener(this);
        statistieken.addActionListener(this);
        blokbps.addActionListener(this);
        aanmeldenpakket.addActionListener(this);

        //alles tonen en menu toevoegen
        
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
        
            //VANAF HIER MENU ITEMS AFVANGEN.
            }else if (ae.getSource() == afsluiten){
                //afsluiten button 
               System.exit(0);
            }else if(ae.getSource() == acbeheer){
                //account beheer button.
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "acbeheer");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }
            }else if (ae.getSource() == meldenpakket){
                //melden pakket button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "meldenpakket");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == feedbackbeheer){
                //feedbackbeheer button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "feedbackbeheer");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == statistieken){
                //statistieken button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "statistieken");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == blokbps){
                //blokkeer bps'er button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "blokbps");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == aanmeldenpakket){
                //aanmeldenpakket button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "aanmeldenpakket");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
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
