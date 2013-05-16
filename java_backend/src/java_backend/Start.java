/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jelle
 */
class Start extends JPanel {
    private JPanel buttonssouth, buttonsnorth;
    private JButton btNieuwBPS, btSluiten, btZoek;
    private JTextField tfZoekveld;
    public Start() {
        
        this.setLayout(new BorderLayout());
        //this.add( new AccountsBeherenTabel());
        
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
      
        this.add(buttonssouth, BorderLayout.SOUTH);
        this.add(buttonsnorth, BorderLayout.NORTH);    
        
        this.setVisible(true);
    }
    
}
