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
    private JButton btNieuw, btLogin, btNorth;
     private CardLayout cl = new CardLayout();
    private JPanel cardHolder = new JPanel(cl);
    private JPanel login, logincenter;
    private JTextField tfEmailadres;
    private JPasswordField pfWachtwoord;
    //private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;
   
    private JTable table;
    private String tableinhoud[] = {"ID", "Naam"};
    
    public GUI() {
        //Standaard instellingen hoofdscherm
        super();
        this.setTitle("TZT Post");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);        
        
        //try catch statement voor userdata in JTable te plaatsen.
        try{
            DbConnect dbc = new DbConnect();
            dbc.getUsers();
            table = new JTable(dbc.getUsers(), tableinhoud);
        }
        catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        //Instellingen eerste pagina.
        JPanel Card1 = new JPanel();
        Card1.setLayout(new BorderLayout());
        //Card1.add();
        this.card1logo = new JLabel();
        
        //nog even kijken naar logo bovenaan de pagina.
        //this.card1logo.setIcon(new javax.swing.ImageIcon("http://www.tztpost.nl/tztklein.png"));
        ////this.card1logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Jelle\\Pictures\\zoidberg.png"));

        //this.btNext = new JButton("Volgende");
        
        //loginscherm voor card1
        this.login = new JPanel();
        this.logincenter = new JPanel();
        //kies borderlayout
        this.login.setLayout(new BorderLayout());
        this.logincenter.setLayout(new GridLayout(3, 2));
        //instancieer buttons en velden.
        this.btLogin = new JButton("Log in");
        this.tfEmailadres = new JTextField(20);
        this.pfWachtwoord = new JPasswordField(10);
        
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lEmailadres = new JLabel("E-mailadres:");
        
        
        //voeg velden toe aan login center
        this.logincenter.add(lEmailadres);
        this.logincenter.add(tfEmailadres);
        this.logincenter.add(lWachtwoord);
        this.logincenter.add(pfWachtwoord);
        this.logincenter.add(btLogin);
                
        //voeg velden toe aan loginpanel
        this.login.add(this.logincenter);
        
        //voeg logo toe aan loginpanel
        Card1.add(this.card1logo, BorderLayout.NORTH);
        //voeg panel toe aan loginscherm.
        Card1.add(this.login, BorderLayout.CENTER);
        
        //instellingen tweede pagina.
        JPanel Card2 = new JPanel();
        
        Card2.add(new JScrollPane(table));
        //Voeg kaarten toe aan de cardlayout.
        cardHolder.add(Card1);
        cardHolder.add(Card2);
        this.add(cardHolder);

        //actionlisternes zetten
        btLogin.addActionListener(this);
        //btNext.addActionListener(this);

        this.setVisible(true);
    }
        public void actionPerformed(ActionEvent ae) {
            //button opslaan action performed
            System.out.println(ae.getSource());
            if (ae.getSource() == btLogin) {
                DbConnect a = new DbConnect();
            try {
                a.getLoginData(tfEmailadres.getText(), pfWachtwoord.getText(), false);
                
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }   
            this.cl.next(this.cardHolder);
                
            }                
        }
}
