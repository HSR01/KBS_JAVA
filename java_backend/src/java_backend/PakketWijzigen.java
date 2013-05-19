/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Daniel
 */
class PakketWijzigen extends JPanel implements ActionListener{
        private JLabel zoeklabel;
        private JTextField zoekveld;
        private JButton zoek;  
        
    public PakketWijzigen() {
        zoeklabel = new JLabel("Pakket ID: ");
        zoekveld = new JTextField(4);
        zoek = new JButton("Zoek");
        
        this.add(zoeklabel);
        this.add(zoekveld);
        this.add(zoek);
      
        zoek.addActionListener(this);
        this.setVisible(true);
        
        this.add(new PakketWijzigenTabel(16)); 
    }   
    
    public void actionPerformed(ActionEvent ae) {
	// Nieuw Account

        if (ae.getSource() == zoek) {

                 System.out.println(zoekveld.getText());

        }
    }
    
}
