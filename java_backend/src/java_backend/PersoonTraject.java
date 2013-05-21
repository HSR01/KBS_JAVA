package java_backend;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Justin
 */
public class PersoonTraject extends JFrame implements ListSelectionListener {
 private int geselecteerdeWaarde;
 private String[] geselecteerdeWaardes;
 public static  JTable aTable;  
    
    public PersoonTraject(final int geselecteerdeWaarde) {
        super();
        this.geselecteerdeWaarde = geselecteerdeWaarde;
        
        this.setSize(800, 600);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final String[] tabelinhoud = {"TrajectID", "Begin", "Eind"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getPersonen(geselecteerdeWaarde);
        
        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getColumnCount() { return tabelinhoud.length; }
            @Override
            public int getRowCount() { return data.length;}
            @Override
            public Object getValueAt(int row, int col) { return data[row][col]; }
            @Override
            public String getColumnName(int column) {return tabelinhoud[column];}
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
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(40);     
        
        this.add(new JScrollPane(aTable));
      
        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener( this );
        System.out.print(aTable);
        
        aTable.addMouseListener(
            new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        BPSTrajectToewijzen trajectwijzigen = new BPSTrajectToewijzen(geselecteerdeWaardes, geselecteerdeWaarde);
                        
                        final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Begin", "Eind"};
                        DbConnect dbc = new DbConnect();
                        final Object[][] data = dbc.getPersonen(geselecteerdeWaarde);
                        
                    }
                }
            }
        );           
        this.setVisible(true);   
    }
    
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
     
    public void valueChanged(ListSelectionEvent e) {
        
        TableModel tm = aTable.getModel();
        int[] selRows = aTable.getSelectedRows();
        if (selRows.length == 0) {
            return;
        }
        this.geselecteerdeWaardes = new String[3];
        this.geselecteerdeWaardes[0] = (String) tm.getValueAt(selRows[0],0);
        this.geselecteerdeWaardes[1] = (String) tm.getValueAt(selRows[0],1);
        this.geselecteerdeWaardes[2] = (String) tm.getValueAt(selRows[0],2);
      
        getSelecteerdeWaardes(geselecteerdeWaardes);
    }
    
    public void getSelecteerdeWaardes(String[] string) {
        this.geselecteerdeWaardes = string;
    }     
    
    public void getSelecteerdeWaarde(int string) {
        this.geselecteerdeWaarde = string;
    }
    
    public static TableModel VerVerstabel(int persoonID) {
        
        DbConnect dbc = new DbConnect();
        final String[] tabelinhoud = {"TrajectID", "Begin", "Eind"};
        final Object[][] data = dbc.getPersonen(persoonID);
            
            
                        TableModel dataModel = new AbstractTableModel() {
                            @Override
                            public int getColumnCount() { return tabelinhoud.length; }
                            @Override
                            public int getRowCount() { return data.length;}
                            @Override
                            public Object getValueAt(int row, int col) { return data[row][col]; }
                            @Override
                            public String getColumnName(int column) {return tabelinhoud[column];}
                            //@Override
                            //public Class getColumnClass(int col) {
                              //  return getValueAt(0,col).getClass();
                            //}
                            @Override
                            public void setValueAt(Object aValue, int row, int column) {
                                data[row][column] = aValue;
                            }
                        };
                        
                        return dataModel;
    }
}