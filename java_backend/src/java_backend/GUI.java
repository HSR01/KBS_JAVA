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
    private JLabel  loginlogo, startimg, lWachtwoord, lEmailadres, contenttext;
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
        this.setResizable(false);
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
        JPanel Card1 = new JPanel(); 
        JPanel Card2 = new JPanel();
        JPanel Card3 = new JPanel();
        JPanel Card4 = new JPanel();
        JPanel Card5 = new JPanel();
        JPanel Card6 = new JPanel(); 
        JPanel Card7 = new AanmeldenPakket();
        
        //Laad de database plugin
        DbConnect dbc = new DbConnect();
        
        //Controleer of een verbinding met de database gemaakt kan worden
        if(dbc.checkConnection()){
        //voeg kaarten toe aan kaartenhouder de string is voor naam om deze later aan te roepen.
        cardHolder.add(Card1, "login");
        cardHolder.add(Card2, "home");   
        cardHolder.add(Card3, "meldenpakket");  
        cardHolder.add(Card4, "feedbackbeheer");
        cardHolder.add(Card5, "statistieken");
        cardHolder.add(Card6, "blokbps");
   
            this.add(cardHolder);
        }else{
            //als er geen connectie is laad dan een scherm met foutmelding.
            JPanel error = new JPanel();
            error.setLayout(new FlowLayout());
            JLabel label = new JLabel("Er is geen verbinding met de database! Herstart de applicatie om het nog een keer te proberen.");
            error.add(label);
            this.add(error);
        }
//-------------------------->Card 1: Login venster<--------------------------//
        //Layoutmanager
        Card1.setLayout(new BorderLayout());
        
        //Initialiseer header
        this.loginlogo = new JLabel(); 

    
        //Haal de afbeelding voor de header op
        try{
            BufferedImage img = ImageIO.read(new URL("http://www.tztpost.nl/user_login.png"));
            //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
            this.loginlogo.setIcon(new javax.swing.ImageIcon(img));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //loginscherm voor card1
        this.login = new JPanel();
        this.loginholder = new JPanel();
        
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
        
//-------------------------->Card 2: Home<--------------------------// 
         Card2.setLayout(new BorderLayout());       
        homelogo = new JPanel();
        homecontent = new JPanel();
        contenttext = new JLabel();
        startimg = new JLabel();
        
        try{
            BufferedImage start = ImageIO.read(new URL("http://www.tztpost.nl/start.png"));
            //nog even kijken naar logo bovenaan de pagina met laden afbeelding van internet.
            this.startimg.setIcon(new javax.swing.ImageIcon(start));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        contenttext.setText("Welkom bij TZT Post.");
        homecontent.add(contenttext);
        homelogo.add(startimg);
        Card2.add(this.homelogo, BorderLayout.WEST);
        Card2.add(this.homecontent, BorderLayout.CENTER);





     

//-------------------------->Card 3: Start scherm<--------------------------//     
        Card3.setLayout(new BorderLayout());
        Card3.add( new AccountsBeherenTabel());
        
        this.buttonssouth = new JPanel();
        this.buttonsnorth = new JPanel();
        this.buttonssouth.setLayout(new FlowLayout());
        this.buttonsnorth.setLayout(new FlowLayout());       
        this.btNieuwBPS = new JButton("Nieuw");
        this.btSluiten = new JButton("Sluit");
        
        this.tfZoekveld = new JTextField(20);
        this.btZoek = new JButton("Zoek");
        
        

        
        this.buttonssouth.add(this.btNieuwBPS);
        this.buttonssouth.add(this.btSluiten);
        
        this.buttonsnorth.add(this.tfZoekveld);
        this.buttonsnorth.add(this.btZoek);
        
        //controle of DB connectie geset is.
      
        Card3.add(buttonssouth, BorderLayout.SOUTH);
        Card3.add(buttonsnorth, BorderLayout.NORTH);      

//-------------------------->ActionListeners<--------------------------//           
        
        //Actionlistener voor login
        btLogin.addActionListener(this);
        btNieuwBPS.addActionListener(this);
        
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
                        //succesvol ingelogd menu weergeven
                            if(this.persoon.getRechten() == 0){
                               this.cl.show(this.cardHolder, "acbeheer");
                           }else if(this.persoon.getRechten() == 1){
                               this.setJMenuBar(menubar);
                               this.menubar.add(this.Bestand);
                               this.menubar.add(this.Verzending);           
                           }else if(this.persoon.getRechten() == 2){
                               this.setJMenuBar(menubar);
                               this.menubar.add(this.Bestand);
                               this.menubar.add(this.Verzending);      
                               this.menubar.add(this.Pakket);                
                           }else if(this.persoon.getRechten() == 3){
                               this.setJMenuBar(menubar);
                               this.menubar.add(this.Bestand);
                               this.menubar.add(this.Verzending);      
                               this.menubar.add(this.Pakket);
                               this.menubar.add(this.Accounts);                
                           }else if(this.persoon.getRechten() == 4){
                               this.setJMenuBar(menubar);
                               this.menubar.add(this.Bestand);
                               this.menubar.add(this.Verzending);      
                               this.menubar.add(this.Pakket);
                               this.menubar.add(this.Accounts);
                               this.menubar.add(this.Statistieken);               
                           }                       
                        //alleen bij succesvolle inlog mag iemand door naar de volgende card.
                        this.cl.show(this.cardHolder, "home");
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
            }else if (ae.getSource() == Afsluiten){
                //afsluiten button 
               System.exit(0);
            }else if(ae.getSource() == AccountWijzigen){
                //account beheer button.
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "home");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }
            }else if (ae.getSource() == PakketStatus){
                //melden pakket button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "meldenpakket");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == AccountToevoegen){
                //feedbackbeheer button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "feedbackbeheer");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == AccountWijzigen){
                //statistieken button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "statistieken");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == FinancieelOverzicht){
                //blokkeer bps'er button
                
                //als persoon niet null is er een persoon ingelogd dus mag er geswitched worden.
                if(this.persoon != null){
                    //laad de card van accountbeheer.
                    this.cl.show(this.cardHolder, "blokbps");
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Voor deze pagina moet je ingelogd zijn", "Waarschuwing", 2);
                }  
            }else if (ae.getSource() == GebruikStatistieken){
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
