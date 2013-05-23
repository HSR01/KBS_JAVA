package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * @author Status verzending maker
 */
class StatusVerzending extends JPanel implements ListSelectionListener, ActionListener {
private Object geselecteerdeWaarde;
    static JTable aTable;
    protected JComboBox Begin, Eind;
    private JPanel North, South;
    private JLabel van, naar;
    private JButton Start;
    private String begin, eind;
    private TableModel dataModel;
    
    
    public StatusVerzending() {
                
        super();

        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"TrajectID", "VerzendingID", "PakketID", "KoerierID", "Bedrijfsnaam", "Begin", "Eind", "Beginplaats", "Eindplaats", "Aankomsttijd", "Aflevertijd", "Status"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getPakketStatus();

        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getColumnCount() {
                return tabelinhoud.length;
            }

            @Override
            public int getRowCount() {
                return data.length;
            }

            @Override
            public Object getValueAt(int row, int col) {
                return data[row][col];
            }

            @Override
            public String getColumnName(int column) {
                return tabelinhoud[column];
            }
            //@Override
            //public Class getColumnClass(int col) {
            //  return getValueAt(0,col).getClass();
            //}

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };
        
    
        aTable = new JTable(dataModel);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(75);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(85);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(75);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(85);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(7).setPreferredWidth(125);
        aTable.getColumnModel().getColumn(8).setPreferredWidth(125);
        aTable.getColumnModel().getColumn(9).setPreferredWidth(125);
        aTable.getColumnModel().getColumn(10).setPreferredWidth(125);
         aTable.getColumnModel().getColumn(11).setPreferredWidth(80);
         
        this.add(new JScrollPane(aTable));

        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);
      

        

        this.setVisible(true);

}

       public void valueChanged(ListSelectionEvent e) {
        TableModel tm = aTable.getModel();
        int[] selRows = aTable.getSelectedRows();
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0], 0);
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }

    public void getSelecteerdeWaarde(Object string) {
        this.geselecteerdeWaarde = string;
    }

    
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
        
    
   


    
    

