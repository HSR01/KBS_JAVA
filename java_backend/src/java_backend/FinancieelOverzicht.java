package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
    private int year;
            
    public FinancieelOverzicht() {
        //instancieer velden;
        this.now = Calendar.getInstance();
        

        //instancieer JTable met datamodel van hierboven.
        this.info = new JTable(FillTabel(0));
        //Set de breedte van elke colom.
        this.info.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.info.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.info.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.info.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.info.getColumnModel().getColumn(3).setPreferredWidth(50);
        this.info.getColumnModel().getColumn(4).setPreferredWidth(50);


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
        mid.add(new JScrollPane(this.info));
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
            String inputcombo = this.selectie.getSelectedItem().toString();
            if (!inputcombo.equals("Kies een jaar.")) {
                //als de waarde een jaar heeft kan de JTable opnieuw geladen worden.
                //parse year naar een integer.
                this.year = Integer.parseInt(inputcombo);
                //set een nieuwe model om data op te halen en geef jaar mee.
                this.info.setModel(FillTabel(year));
                //repaint de tabel om het opnieuw weer te geven.
                this.info.repaint();
                //set breedte van kolommen.
                this.info.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                this.info.getColumnModel().getColumn(0).setPreferredWidth(100);
                this.info.getColumnModel().getColumn(1).setPreferredWidth(100);
                this.info.getColumnModel().getColumn(2).setPreferredWidth(150);
                this.info.getColumnModel().getColumn(3).setPreferredWidth(50);
                this.info.getColumnModel().getColumn(4).setPreferredWidth(50);
                
            }
        }
    }
    /**
     * @author Jelle
     * @description gebruikt om de tabel te verversen.
     */
        public TableModel FillTabel(final int selectyear){
            
         TableModel dataModel = new AbstractTableModel() {
                    //instancieer columnnamen
        final String[] tabelinhoud = {"Aantal pakken", "Aantal keer BPS", "Aantal keer koerier", "omzet", "winst"};

          /*
           * @autor Jelle
           * @description Return the number of columns needed for the JTable
           */
          public int getColumnCount() { return 5; }

          /*
           * @autor Jelle
           * @description Return the number of rows needed for the JTable
           */
          public int getRowCount() { return 1;}
          
          /*
           * @author Jelle
           * @description fill the JTable with values.
           */
            public Object getValueAt(int row, int col) {
                DbConnect dbc = new DbConnect();
                int[][] returnval = dbc.getFinance(selectyear);
                return returnval[0][col];
            }
          //set kolom namen
            /*
             * @autor Jelle
             * @description set the column names with the data from tabelinhoud string.
             */
            public String getColumnName(int column){
            return tabelinhoud[column];
            };
        };
        return dataModel;
        };  
}
