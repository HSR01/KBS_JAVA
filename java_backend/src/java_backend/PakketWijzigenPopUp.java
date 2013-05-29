package java_backend;

import Database.DbConnect;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class PakketWijzigenPopUp extends JFrame implements ActionListener{
    private JButton zoek;
    private Object trajectID;
    private JComboBox statussen;
    
    public PakketWijzigenPopUp(Object trajectID){
        this.trajectID = trajectID;
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

        String[] statusincombo = {"Aangemeld", "Onderweg", "Verwacht", "Afgeleverd", "Onbekend"};
        JPanel diacontent = new JPanel();
        statussen = new JComboBox(statusincombo);
        
        diacontent.add(new JLabel("Traject " + trajectID + " - Status "));
        diacontent.setLayout(new FlowLayout());        
 	diacontent.add(statussen);
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
            int status =4;
            String x = String.valueOf(statussen.getSelectedItem());
            if(x.equals("Aangemeld")){
                status = 0;
            }else if(x.equals("Onderweg")){
                status = 1;
            }else if(x.equals("Verwacht")){
                status = 2;
            }else if(x.equals("Afgeleverd")){
                status = 3;
            }else{
                status = 4;
            }                

            //jaar geselecteerd.          
            DbConnect dbc = new DbConnect();
            dbc.updateStatus(trajectID, status);
            //repaint de tabel om het opnieuw weer te geven.
            PakketWijzigen.info.repaint();
            JDialog jd = new JDialog();
            jd.setSize(400,175);
            jd.setTitle("Status wijzigen");
            jd.add(new JLabel("De status is succesvol gewijzigd."));
            jd.setVisible(true);
            this.dispose();    
            }
     }
    
}
