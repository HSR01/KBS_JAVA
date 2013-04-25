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
    private JLabel  card1logo;
    private JButton btNieuw, btNext, btNorth;
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
        this.card1logo = new JLabel("Inloggen");
        this.btNext = new JButton("Volgende");
        Card1.add(this.card1logo);
        Card1.add(this.btNext);
        
        //instellingen tweede pagina.
        JPanel Card2 = new JPanel();
        
        Card2.add(table);
        //Voeg kaarten toe aan de cardlayout.
        cardHolder.add(Card1);
        cardHolder.add(Card2);
        this.add(cardHolder);

        
        btNext.addActionListener(this);

        this.setVisible(true);
    }
        public void actionPerformed(ActionEvent ae) {
            //button opslaan action performed
            if (ae.getSource() == btNext) {
              this.cl.next(this.cardHolder);
                
            }                
        }
}
