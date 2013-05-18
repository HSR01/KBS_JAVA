/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jelle
 */
class FinancieelOverzicht extends JPanel implements ActionListener{
    private JComboBox selectie;
    private JLabel jaar;
    private Calendar now;
    
            
    public FinancieelOverzicht() {
        //instancieer velden;
        now = Calendar.getInstance();
        //maak combobox voor jaren selecteren.

        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        jaren.addElement("Kies een jaar.");
        if (this.now.get(Calendar.YEAR) > 2013) {
            for (int h = 2013; h < (this.now.get(Calendar.YEAR) + 1); h++) {
                jaren.addElement(h);
            }
        } else {
            jaren.addElement("2013");
        }
        //instancieer combobox
        this.selectie = new JComboBox(jaren);
                
        //voeg layout to aan jpanel
        this.add(new JLabel("Kies een jaar"));
        this.add(this.selectie);
        
        //add actionlisteners
        this.selectie.addActionListener(this);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //selectie button geladen.
        if (ae.getSource() == selectie) {
            //jaar geselecteerd.
            String jaar = this.selectie.getSelectedItem().toString();
            if (!jaar.equals("Kies een jaar.")) {
                //een waarde heeft een jaar. Ga door voor query om de boel op te halen en alles.
                DbConnect dbc = new DbConnect();
                
            }
        }
    }
    
}
