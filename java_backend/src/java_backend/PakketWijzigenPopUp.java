/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class PakketWijzigenPopUp extends JFrame{
    
    public PakketWijzigenPopUp(Object aa){
        
        super();
        this.setTitle("Pakket wijzigen");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setResizable(false);
        this.setSize(800, 600);  
        System.out.println(aa);
        PakketWijzigen pw = new PakketWijzigen();
           
        this.setLayout(new BorderLayout());
        this.add(new JLabel("PakketID - Status wijzigen."), BorderLayout.NORTH);  
        
        
        String[] statussen = {"Aangemeld", "Onderweg", "Verwacht", "Afgeleverd", "Onbekend"};
        JPanel diacontent = new JPanel();
 
        diacontent.setLayout(new FlowLayout());        
 	diacontent.add(new JComboBox(statussen));
        
        this.add(diacontent, BorderLayout.CENTER);

        this.setSize(300, 180); 
        this.setVisible(true);
    }
    
}
