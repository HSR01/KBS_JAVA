/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import Database.DbConnect;
import GUI_helpers.CustomJTable;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Klasse om het zoeken van personen erg makkelijk te maken.
 * @author Jelle
 */
class ZoekPersoon extends JPanel implements ActionListener{
    private Persoon persoon = null;
    private JButton submit;
    private JTextField zoekveld;
    private Locatie locatie = null;
    private JLabel info;

    public ZoekPersoon(String type){
            this.zoekveld = new JTextField(10);
            this.submit = new JButton("Zoek");
        
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Zoek: " + type));
            this.add(this.zoekveld);
            this.add(this.submit);
        
            this.submit.addActionListener(this); 


        this.setVisible(true);
    }
    public void setPersoon(Persoon p){
        this.persoon = p;
    }
    
    public Persoon getPersoon(){
        return this.persoon;
    }
    public void setLocatie(Locatie l){
        this.locatie = l;
    }
    public Locatie getLocatie(){
        return this.locatie;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == submit){
            if(zoekveld.getText().equals("")){
               //jdialog voor foutmelding
              JDialog jd = new JDialog();
              jd.setSize(200,175);
              jd.setTitle("Foutmelding");
              jd.add(new JLabel("U heeft geen zoekterm ingevuld."));
              jd.setVisible(true);
            }else{
                //jdiaglog met personen.
               DbConnect dbc = new DbConnect();
               //vul object met data van persoon.
               Object[][] data = dbc.getPersonenWithCoordinates(zoekveld.getText().toString());
               //laad custom jtable
               String[] columnnames = { "PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "Postcode", "Huisnummer", "Toevoeging", "IBAN" };
               int[] columnsizes = { 40, 120, 50, 120, 50, 40, 40, 40, 10 };
               
               CustomJTable popup = new CustomJTable(columnnames, columnsizes, data);
               //haal persoon object op.
               this.persoon = dbc.getPersoonById(Integer.parseInt(popup.result.toString()));
               //haal locatie op..
               locatie = dbc.getLocatieFromPersoonId(popup.result.toString());
               
               //moet nog repainten.
            }
        }
    }
    
}
