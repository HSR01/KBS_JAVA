/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package java_backend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class BPSTrajectToewijzen extends JFrame implements ActionListener, ListSelectionListener {

    // Hoofdframe
    private JPanel jpButtons, jpTable, jpHoofd, jpZoek, jpZoekLabels, jpZoekInvulvelden;
    private JButton jbVervers, jbZoek;
    private JTable jtTabel;
    private JLabel jlVoornaam, jlAchternaam, jlID, jlTrajectID, jlTrajectBegin, jlTrajectEind;
    private JTextField tfVoornaam, tfAchternaam, tfID, tfTrajectID;
    
    // Popupframe
    private JPanel jpTrajecten;
    private JButton jbAnnuleer, jbWijzig;
    private JFrame jfScherm;
    public BPSTrajectToewijzen(){
        // Kan later verwijderd worden
        jfScherm = new JFrame();
        jfScherm.setSize(600,400);
        
        // Panels
        jpButtons = new JPanel();
        jpTable = new JPanel();
        jpHoofd = new JPanel();
        jpZoek = new JPanel();
        jpZoekLabels = new JPanel();
        jpZoekInvulvelden = new JPanel();
        
        // Tabel
        jtTabel = new JTable();
        
        // Buttons
        jbVervers = new JButton("Ververs");
        jbVervers.addActionListener( this );
        jbZoek = new JButton("Zoek");
        jbZoek.addActionListener( this );
        
        // Labels
        jlVoornaam = new JLabel("Voornaam:");
        jlAchternaam = new JLabel("Achternaam:");
        jlID = new JLabel("PersoonID:");
        jlTrajectID = new JLabel("TrajectID:");
        //Invulvelden
        tfVoornaam = new JTextField();
        tfAchternaam = new JTextField();
        tfID = new JTextField();
        tfTrajectID = new JTextField();
        
        jpZoekLabels.setLayout(new FlowLayout());
        jpZoekLabels.add(jlVoornaam);
        jpZoekLabels.add(jlAchternaam);
        jpZoekLabels.add(jlID);
        jpZoekLabels.add(jlTrajectID);
        
        jpZoekInvulvelden.setLayout( new GridLayout(1,5));
        jpZoekInvulvelden.add(tfVoornaam);
        jpZoekInvulvelden.add(tfAchternaam);
        jpZoekInvulvelden.add(tfID);
        jpZoekInvulvelden.add(tfTrajectID);
        jpZoekInvulvelden.add(jbZoek);

        jpZoek.setLayout(new BorderLayout());
        jpZoek.add(jpZoekLabels, BorderLayout.NORTH);
        jpZoek.add(jpZoekInvulvelden, BorderLayout.SOUTH);
        jpZoek.add(jbZoek, BorderLayout.EAST);
        
        jpButtons.add(jbVervers);
         
        jpTable.add(jtTabel);
        jpHoofd.setLayout(new BorderLayout());
        jpHoofd.add(jpButtons, BorderLayout.SOUTH);
        jpHoofd.add(jpTable, BorderLayout.CENTER);
        jpHoofd.add(jpZoek, BorderLayout.NORTH);
        jfScherm.add(jpHoofd);
          
        jpTrajecten = new JPanel();
        jbAnnuleer = new JButton("Annuleren");
        jbAnnuleer.addActionListener( this );
        
        jbWijzig = new JButton("Wijzigen");
        jbWijzig.addActionListener( this );
          
       // SetVisible
           jfScherm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
