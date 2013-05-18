/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelle
 */
class FinancieelOverzicht extends JPanel implements ActionListener{
    private JComboBox selectie;
    private JLabel jaar;
    private Calendar now;
    private JTable info;
            
    public FinancieelOverzicht() {
        //instancieer velden;
        this.now = Calendar.getInstance();
        

        //instancieer JTable met datamodel van hierboven.
        this.info = new JTable(dataModel);
        
        //maak combobox voor jaren selecteren.
        DefaultComboBoxModel jaren = new DefaultComboBoxModel();
        jaren.addElement("Kies een jaar.");
        
        //voeg jaren toe aan de hand van het huidige jaar na 2013.
        if (this.now.get(Calendar.YEAR) > 2013) {
            for (int h = 2013; h < (this.now.get(Calendar.YEAR) + 1); h++) {
                jaren.addElement(h);
            }
        } else {
            jaren.addElement("2013");
        }
        //instancieer combobox met gevulde jaren erin.
        this.selectie = new JComboBox(jaren);
        
        //set de layout van het panel op borderlayout
        this.setLayout(new BorderLayout());
        //voeg layout to aan jpanel top
        JPanel top =new JPanel();
        top.add(new JLabel("Kies een jaar"));
        top.add(this.selectie);
        //voeg layout toe aan Jpanel mid
        JPanel mid = new JPanel();
        mid.add(this.info);
        //voeg uiteindelijk alles toe aan het centrale geheel.
        this.add(top, BorderLayout.NORTH);
        this.add(mid, BorderLayout.CENTER);
        
        //add actionlisteners
        this.selectie.addActionListener(this);
        
        //tonen maar
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
    /**
     * @author Jelle
     * @description Gebruikt om de JTabel mee te vullen.
     */
    TableModel dataModel = new AbstractTableModel() {
          //geef aantal kolomen terug
          public int getColumnCount() { return 10; }
          //geef aantal rijen terug.
          public int getRowCount() { return 10;}
          //geef waarde terug om rij mee te vullen
            public Object getValueAt(int row, int col) { return new Integer(row*col); }
        };
    
}
