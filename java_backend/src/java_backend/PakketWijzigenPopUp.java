/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class PakketWijzigenPopUp extends JFrame implements ActionListener{
    public JButton zoek;
    
    public PakketWijzigenPopUp(Object pakketID){
        
        super();  
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        this.setTitle("Status");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);

        PakketWijzigen pw = new PakketWijzigen();
           
        this.setLayout(new BorderLayout());
  
        zoek = new JButton("Opslaan");
        
        String[] statussen = {"Aangemeld", "Onderweg", "Verwacht", "Afgeleverd", "Onbekend"};
        JPanel diacontent = new JPanel();
        diacontent.add(new JLabel("Pakket " + pakketID + " - Status "));
        diacontent.setLayout(new FlowLayout());        
 	diacontent.add(new JComboBox(statussen));
        diacontent.add(zoek);
        
        this.zoek.addActionListener(this);
        
        this.add(diacontent, BorderLayout.CENTER);

        this.setSize(310, 80); 
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //selectie button geladen.
        if (ae.getSource() == zoek) {
            //jaar geselecteerd.          
              
            //repaint de tabel om het opnieuw weer te geven.
            PakketWijzigen.info.repaint();
                
            }
     }
    
}
