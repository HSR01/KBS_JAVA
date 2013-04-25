/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
public class GUI extends JFrame implements ActionListener {

    private JLabel  logo;
    private JButton btNieuw, btOpslaan, btNorth;
    private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;
    private JTable table;
    private String tableinhoud[] = {"ID", "Naam"};
    

    public GUI() {
        super();
        this.setTitle("TZT Post");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
        
        DbConnect dbc = new DbConnect();
        try{
            dbc.getUsers();
            table = new JTable(dbc.getUsers(), tableinhoud);
        }
        catch(Exception e){
           System.out.println(e.getMessage());
        }
        this.jInputfields = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jFieldPanel = new JPanel();

        jFieldPanel.setLayout(new GridLayout(13, 2));

       
        
        this.logo = new JLabel();
        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Daniel\\Google Drive\\KBS HSR\\tztklein.png"));

        this.btNieuw = new JButton("Nieuw");
        this.btOpslaan = new JButton("Opslaan");
        
        btOpslaan.addActionListener(this);
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

        this.setVisible(true);
        

        
    }
        public void actionPerformed(ActionEvent ae) {
	if (ae.getSource() == btOpslaan) {
             DbConnect a = new DbConnect();
            
             
             
	}            
            
        }
}
