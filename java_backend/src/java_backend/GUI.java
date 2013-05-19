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
 * @author Daniel is de best
 */
public class GUI extends JFrame implements ActionListener {
    private JLabel loginlogo, startimg, lWachtwoord, lEmailadres, contenttext;
    private JButton btNieuw, btLogin, btNorth, btNieuwBPS, btSluiten, btZoek;
    private CardLayout cl = new CardLayout();
    private JPanel cardHolder = new JPanel(cl);
    private JPanel login, loginholder, buttonssouth, buttonsnorth, homelogo, homecontent;
    private JTextField tfEmailadres, tfZoekveld;
    private JPasswordField pfWachtwoord;
    private Persoon persoon;
    
    //menu onderdelen.
    private JMenuBar menubar;
    private JMenu Bestand, Verzending, Pakket, Accounts, Statistieken;
    private JMenuItem Afmelden, Afsluiten, NieuweVerzending, StatusVerzending, VerzendingAnnuleren, PakketStatus, PakketWijzigen, AccountToevoegen, AccountWijzigen, AccountBlokeren, GebruikStatistieken, PakketStatistieken, FinancieelOverzicht;

    public GUI() {
        //Standaard instellingen hoofdscherm
        super();
        this.setTitle("TZT Post");
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setSize(800, 600);  
        
        //Centreren in venster
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);          
        
        //Er is standaard geen persoon ingelogd.
        this.persoon = null;
               
        //instancieer menu
        this.menubar = new JMenuBar();
        
        //instancieer menu items
        this.Bestand = new JMenu("Bestand");
        this.Verzending = new JMenu("Verzending");
        this.Pakket = new JMenu("Pakket");
        this.Accounts = new JMenu("Accounts");
        this.Statistieken = new JMenu("Statistieken");           
        
        //instancieer menu onderdelen voor bestand
        this.Afmelden = new JMenuItem("Afmelden");
        this.Afsluiten = new JMenuItem("Afsluiten");
        
        //instancieer menu onderdelen voor verzending        
        this.NieuweVerzending = new JMenuItem("Nieuwe verzending");
        this.StatusVerzending = new JMenuItem("Status verzending");
        this.VerzendingAnnuleren = new JMenuItem("Verzending annuleren");
        
        //instancieer menu onderdelen voor pakket        
        this.PakketStatus = new JMenuItem("Pakket status");
        this.PakketWijzigen = new JMenuItem("Pakket wijzigen");
    
        //instancieer menu onderdelen voor accounts 
        this.AccountToevoegen = new JMenuItem("Account toevoegen");        
        this.AccountWijzigen = new JMenuItem("Account wijzigen");  
        this.AccountBlokeren = new JMenuItem("Account blokeren");
        
        //instancieer menu onderdelen voor statistieken
        this.GebruikStatistieken = new JMenuItem("Gebruik statistieken");
        this.PakketStatistieken = new JMenuItem("Pakket statistieken");
        this.FinancieelOverzicht = new JMenuItem("Financieel overzicht");
 
        //voeg onderdelen aan bestand menu toe.
        this.Bestand.add(this.Afmelden);
        this.Bestand.add(this.Afsluiten);
        
        //voeg onderdelen aan verzending
        this.Verzending.add(NieuweVerzending);
        this.Verzending.add(StatusVerzending);
        this.Verzending.add(VerzendingAnnuleren);
        
        //voeg onderdelen aan pakket toe
        this.Pakket.add(PakketStatus);
        this.Pakket.add(PakketWijzigen);
       
        //voeg onderdelen aan pakket accounts
        this.Accounts.add(AccountToevoegen);
        this.Accounts.add(AccountWijzigen);
        this.Accounts.add(AccountBlokeren);
        
        //voeg onderdelen aan statistieken menu toe
        this.Statistieken.add(GebruikStatistieken);
        this.Statistieken.add(PakketStatistieken);
        this.Statistieken.add(FinancieelOverzicht);
           
        //Initialiseer kaarten
        //inlogscherm
        JPanel Card1 = new JPanel(); 
        //home
        JPanel Card2 = new Home();
        //Startscherm
        JPanel Card3 = new Start();
        
        //overige kaarten ingeladen vanuit aparte subklasse.
        JPanel Card4 = new AanmeldenPakket();
        JPanel Card5 = new NieuweVerzending();
        JPanel Card6 = new StatusVerzending();
        JPanel Card7 = new VerzendingAnnuleren();
        JPanel Card8 = new PakketWijzigen();
        JPanel Card9 = new AccountWijzigen();
        JPanel Card10 = new AccountBlokkeren();
        JPanel Card11 = new GebruikStatistieken();
        JPanel Card12 = new PakketStatistieken();
        JPanel Card13 = new FinancieelOverzicht();
        JPanel Card14 = new PakketStatus();
        JPanel Card15 = new AccountToevoegen();
        
        //Laad de database plugin
        DbConnect dbc = new DbConnect();
        //Controleer of een verbinding met de database gemaakt kan worden
        if (dbc.checkConnection()) {
        //voeg kaarten toe aan kaartenhouder de string is voor naam om deze later aan te roepen.
        cardHolder.add(Card1, "login");
        cardHolder.add(Card2, "home");   
        cardHolder.add(Card3, "start");  
        cardHolder.add(Card4, "aanmeldenpakket");
        cardHolder.add(Card5, "nieuweverzending");
        cardHolder.add(Card6, "statusverzending");
        cardHolder.add(Card7, "verzendingannuleren");
        cardHolder.add(Card8, "pakketwijzigen");
        cardHolder.add(Card9, "accountwijzigen");
        cardHolder.add(Card10, "accountblokkeren");
        cardHolder.add(Card11, "gebruikstatistieken");
        cardHolder.add(Card12, "pakketstatistieken");
        cardHolder.add(Card13, "financieeloverzicht");
        cardHolder.add(Card14, "pakketstatus");
        cardHolder.add(Card15, "accounttoevoegen");
        //voeg kaartenhouder toe aan layout.
        this.add(cardHolder);
        
        
        } else {
            //als er geen connectie is laad dan een scherm met foutmelding.
            JPanel error = new JPanel();
            error.setLayout(new FlowLayout());
            JLabel label = new JLabel("Er is geen verbinding met de database! Herstart de applicatie om het nog een keer te proberen.");
            error.add(label);
            this.add(error);
        }
//-------------------------->Card 1: Login venster<--------------------------//
        //De loginpagina is de enige card die in de GUI staat.
        //Dit vanwege het wisselen van kaarten in combinatie met action listeners.
        //bij andere paginas gebeurd dit via het menu en wordt dit dus ook geregeld in het menu en niet de losse klasse.
        //
        
        
        
        //Layoutmanager
        Card1.setLayout(new BorderLayout());
        
        //Initialiseer header
        this.loginlogo = new JLabel(); 


          
        loginlogo.setHorizontalTextPosition(JLabel.CENTER);
        loginlogo.setVerticalTextPosition(JLabel.CENTER);
        //Haal de afbeelding voor de header op
        try {
            BufferedImage img = ImageIO.read(new URL("http://www.tztpost.nl/user_login.png"));
            //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
            this.loginlogo.setIcon(new javax.swing.ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        loginlogo.setHorizontalTextPosition(JLabel.LEFT);
        //loginscherm voor card1
        this.login = new JPanel();
        this.loginholder = new JPanel();
        
        loginlogo.setMaximumSize(new Dimension(50,50));
        loginlogo.setMinimumSize(new Dimension (50,50));
        loginlogo.setPreferredSize(new Dimension(50,50));
        loginlogo.setBorder(BorderFactory.createLineBorder(Color.black)); 
        //kies borderlayout
        this.login.setLayout(new FlowLayout());
   
        this.loginholder.setLayout(new GridLayout(0,2,2,6)); 

        //instancieer buttons en velden.
        this.btLogin = new JButton("Log in");
        this.tfEmailadres = new JTextField(15);
        this.pfWachtwoord = new JPasswordField(15);
        
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lEmailadres = new JLabel("E-mailadres:");

        //voeg velden toe aan login center
        this.loginholder.add(new JLabel(""));
        this.loginholder.add(new JLabel(""));
        this.loginholder.add(lEmailadres);
        this.loginholder.add(tfEmailadres);
        this.loginholder.add(lWachtwoord);
        this.loginholder.add(pfWachtwoord);
        this.loginholder.add(new JLabel(""));
        this.loginholder.add(new JLabel(""));
        this.loginholder.add(new JLabel(""));
        this.loginholder.add(btLogin);

        //voeg velden toe aan loginpanel
        this.login.add(this.loginholder);
       
        Card1.add(this.loginlogo, BorderLayout.NORTH);
        Card1.add(this.login, BorderLayout.CENTER);
//-------------------------->ActionListeners<--------------------------//           
        
        //Actionlistener voor login
        tfEmailadres.addActionListener(this);
        pfWachtwoord.addActionListener(this);
        btLogin.addActionListener(this);
        
        //HIER NOG NAAR KIJKEN -> START CLASS.
        //getRootPane().setDefaultButton(btLogin);
        //btNieuwBPS.addActionListener(this);
        //getRootPane().setDefaultButton(btNieuwBPS);
        
        //Actionlistener voor bestand
        Afmelden.addActionListener(this);
        Afsluiten.addActionListener(this);

        //Actionlistener voor verzending
        NieuweVerzending.addActionListener(this);
        StatusVerzending.addActionListener(this);
        VerzendingAnnuleren.addActionListener(this);
        
        //Actionlistener voor pakket
        PakketStatus.addActionListener(this);
        PakketWijzigen.addActionListener(this);        
        
        //Actionlistener voor account
        AccountToevoegen.addActionListener(this);
        AccountWijzigen.addActionListener(this);
        AccountBlokeren.addActionListener(this);
        
        //Actionlistener voor statistieken
        GebruikStatistieken.addActionListener(this);
        PakketStatistieken.addActionListener(this);
        FinancieelOverzicht.addActionListener(this);        
        //alles tonen en menu toevoegen
        
        this.setVisible(true);
    }
    
    public String test () {
        System.out.println("LOLLL");
        return "Lol";
    }
    
    public boolean logIn () {
        //instancieer databaseconnectie
        DbConnect a = new DbConnect();
        try {
            //controleer de gegevens. allebei niet leeg.
            if (tfEmailadres.getText().equals("") || pfWachtwoord.getText().equals("")) {
                //show message dialog met foutmelding.
                JOptionPane.showMessageDialog(rootPane, "Niet alle verplichte velden zijn ingevuld.", "Waarschuwing", 2);
            } else {
                //maak Persoon aan aan de hand van inloggegevens en methode in inlogdata
                //dit is een attribuut zodat deze beschikbaar is in de gehele GUI.
                this.persoon = a.getLoginData(tfEmailadres.getText(), pfWachtwoord.getText(), true);
                if (this.persoon != null) {
                    //succesvol ingelogd menu weergeven
                    if (this.persoon.getRechten() == 0) {
                       this.cl.show(this.cardHolder, "acbeheer");
                    } else if (this.persoon.getRechten() == 1) {
                        this.setJMenuBar(menubar);
                        this.menubar.add(this.Bestand);
                        this.menubar.add(this.Verzending);           
                    } else if (this.persoon.getRechten() == 2) {
                        this.setJMenuBar(menubar);
                        this.menubar.add(this.Bestand);
                        this.menubar.add(this.Verzending);      
                        this.menubar.add(this.Pakket);                
                    } else if (this.persoon.getRechten() == 3) {
                        this.setJMenuBar(menubar);
                        this.menubar.add(this.Bestand);
                        this.menubar.add(this.Verzending);      
                        this.menubar.add(this.Pakket);
                        this.menubar.add(this.Accounts);                
                    } else if (this.persoon.getRechten() == 4) {
                        this.setJMenuBar(menubar);
                        this.menubar.add(this.Bestand);
                        this.menubar.add(this.Verzending);      
                        this.menubar.add(this.Pakket);
                        this.menubar.add(this.Accounts);
                        this.menubar.add(this.Statistieken);               
                    }                       
                    //alleen bij succesvolle inlog mag iemand door naar de volgende card.
                    this.cl.show(this.cardHolder, "home");
                    return true;
                } else {
                    //show error dialog, inloggen is niet gelukt, object is leeg (controle en afhandeling in methode getLoginData
                    JOptionPane.showMessageDialog(rootPane, "Het inloggen is niet gelukt!", "Waarschuwing", 2);
                    //zet het formulier weer op leeg om opnieuw in te kunnen loggen.
                    tfEmailadres.setText("");
                    pfWachtwoord.setText("");    
                    //mag niet door naar de volgende card.
                    return false;
                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Acties voor login button'
        if (ae.getSource() == btLogin || ae.getSource() == tfEmailadres || ae.getSource() == pfWachtwoord) {
            logIn();
        //VANAF HIER MENU ITEMS AFVANGEN.
        } else if (ae.getSource() == Afsluiten) {
            //afsluiten button 
           System.exit(0);
        } else if (ae.getSource() == Afmelden) {
            //werk voor justin
            //Auteur Justin
            //JAAAAAAAAAAAA JUSTIN HEEFT EINDELIJK WAT AF IN JAVA JAAAAAAAA
            this.persoon = null;
            this.cl.show(this.cardHolder, "login");
            tfEmailadres.setText("");
            pfWachtwoord.setText("");
            this.setJMenuBar(null);
        } else if (ae.getSource() == NieuweVerzending) {
            ChangeMenu("nieuweverzending");
        } else if (ae.getSource() == StatusVerzending) {
            ChangeMenu("statusverzending");
        } else if (ae.getSource() == VerzendingAnnuleren) {
            ChangeMenu("verzendingannuleren");
        } else if (ae.getSource() == PakketStatus) {
             ChangeMenu("pakketstatus");           
        } else if (ae.getSource() == PakketWijzigen) {
            ChangeMenu("pakketwijzigen");
        } else if (ae.getSource() == AccountToevoegen) {
            ChangeMenu("accounttoevoegen");
        } else if (ae.getSource() == AccountWijzigen) {
            ChangeMenu("accountwijzigen");
        } else if (ae.getSource() == AccountBlokeren) {
            ChangeMenu("accountblokkeren");
        } else if (ae.getSource() == GebruikStatistieken) {
            ChangeMenu("gebruikstatistieken");
        } else if (ae.getSource() == PakketStatistieken) {
            ChangeMenu("pakketstatistieken");
        } else if (ae.getSource() == FinancieelOverzicht) {
            ChangeMenu("financieeloverzicht");
        }
        //EINDE MENU
        if (ae.getSource() == btNieuwBPS) {
            WijzigPersoon p = new WijzigPersoon();   
        }

        if (ae.getSource() == btSluiten) {
            this.setVisible(false);
        }
    }
    /**
     * @author Jelle
     * @param Card Die opgegeven is in het vullen van de cardholder.
     * @description Zorgt ervoor dat er gewijzijgd wordt naar een card die ingegeven wordt als string. Er wordt ook gecontroleerd of er al ingelogd is.
     * 
     */
    public void ChangeMenu(String Card) {
        //controleer inloggen
            if (this.persoon != null) {
                //laad de card.
                this.cl.show(this.cardHolder, Card);
            } else {
                //foutmelding niet ingelogd.
                JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
            }
    }
}
