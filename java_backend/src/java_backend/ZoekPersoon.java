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
    private Locatie locatie;
    private JLabel info;

    public ZoekPersoon(String type){
        //als this persoon null is is er geen persoon geselecteerd dus veld tonen.
        //instancieer objecten
        if(this.persoon != null){
            System.out.println("persoon null");
            this.info = new JLabel(this.persoon.getAchternaam());
        }else{
            System.out.println("else");
            this.zoekveld = new JTextField(10);
            this.submit = new JButton("Zoek");
        
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Zoek: " + type));
            this.add(this.zoekveld);
            this.add(this.submit);
        
            this.submit.addActionListener(this); 
        }

        this.setVisible(true);
    }
    public void setPersoon(Persoon p){
        this.persoon = p;
    }
    
    public Persoon getPersoon(){
        return this.persoon;
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
               Object[][] data = dbc.getPersonenWithCoordinates(zoekveld.getText().toString());
               String[] columnnames = { "PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "Postcode", "Huisnummer", "Toevoeging", "IBAN" };
               int[] columnsizes = { 40, 120, 50, 120, 50, 40, 40, 40, 10 };
               
               CustomJTable popup = new CustomJTable(columnnames, columnsizes, data);
               this.persoon = dbc.getPersoonById(Integer.parseInt(popup.result.toString()));
               
               locatie = dbc.getLocatieFromPersoonId(popup.result.toString());
               
               super.revalidate();
               super.repaint();
               System.out.println("MOET REPAINTEN");
            }
        }
    }
    
}
