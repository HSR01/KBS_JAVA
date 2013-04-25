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
 * @author Jelle
 * @author Daniel
 */
public class GUI extends JFrame implements ActionListener {
    private JLabel  logo;
    private JButton btNieuw, btOpslaan, btNorth;
     private CardLayout cl = new CardLayout();
    private JPanel cardHolder = new JPanel(cl);
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
        Card1.setLayout(new FlowLayout());
        //Card1.add();
        this.btOpslaan = new JButton("Opslaan");    
        Card1.add(this.btOpslaan);
        
        //instellingen tweede pagina.
        JPanel Card2 = new JPanel();
        
        Card2.add(table);
        //Voeg kaarten toe aan de cardlayout.
        cardHolder.add(Card1);
        cardHolder.add(Card2);
        this.add(cardHolder);
        /*this.jInputfields = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jFieldPanel = new JPanel();
        jFieldPanel.setLayout(new GridLayout(13, 2));

        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));
        this.btNieuw = new JButton("Nieuw");
           */
        
        btOpslaan.addActionListener(this);
        /*
        btNieuw.addActionListener(this);    
        this.jWest.add(table);
        this.jInputfields.add(jFieldPanel);
        this.jSouth.add(btNieuw);
        this.jSouth.add(btOpslaan);
        this.jNorth.add(logo);
        this.add(jNorth, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.WEST);
        this.add(jInputfields, BorderLayout.EAST);
        this.add(jSouth, BorderLayout.SOUTH);
*/
        this.setVisible(true);
    }
        public void actionPerformed(ActionEvent ae) {
            //button opslaan action performed
            if (ae.getSource() == btOpslaan) {
              this.cl.next(this.cardHolder);
                
            }                
        }
}
