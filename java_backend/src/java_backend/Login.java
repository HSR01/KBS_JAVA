/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Laurens
 */
public class Login extends JDialog implements ActionListener {

     protected JTextField tfEmailadres, tfWachtwoord;
     private JLabel lEmailadres, lWachtwoord, leeg;
     private JPanel jInputfields, jNorth, jWest, jEast, jSouth, jFieldPanel;
     private JButton btLogin;
     protected JPasswordField pfWachtwoord;
     protected boolean succeeded = false;
     
    public Login() {

        super();
        this.setTitle("Login TZT Post");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 150);
        
        this.jInputfields = new JPanel();
        this.jNorth = new JPanel();
        this.jWest = new JPanel();
        this.jEast = new JPanel();
        this.jSouth = new JPanel();
        this.jFieldPanel = new JPanel();

        jFieldPanel.setLayout(new GridLayout(3, 2));
        
        this.tfEmailadres = new JTextField(20);
        this.pfWachtwoord = new JPasswordField(10);
        
        this.lWachtwoord = new JLabel("Wachtwoord:");
        this.lEmailadres = new JLabel("E-mailadres:");
        this.leeg = new JLabel("");
        
        this.btLogin = new JButton("Login");
        btLogin.addActionListener(this);
        
        
        this.jFieldPanel.add(lEmailadres);
        this.jFieldPanel.add(tfEmailadres);
        this.jFieldPanel.add(lWachtwoord);
        this.jFieldPanel.add(pfWachtwoord);
        this.jFieldPanel.add(leeg);
        this.jFieldPanel.add(btLogin);
        
        this.jInputfields.add(jFieldPanel);
        
        
        
        this.add(jNorth, BorderLayout.NORTH);
        this.add(jInputfields, BorderLayout.EAST);
        this.add(jSouth, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    
   
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btLogin){
         DbConnect a = new DbConnect();
            try {
                a.getLoginData(tfEmailadres.getText(), pfWachtwoord.getText(), false);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
         
         
        }
    }
}
