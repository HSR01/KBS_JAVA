package java_backend;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class AccountsBeherenTabel extends JPanel implements ListSelectionListener {
    JTable aTable;
    // private String tableinhoud[] = {"ID", "Naam"};
    
    public AccountsBeherenTabel() {
        final String[] tabelinhoud = {"First Name", "Last Name"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getUsers();
        
        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getColumnCount() { return tabelinhoud.length; }
            @Override
            public int getRowCount() { return data.length;}
            @Override
            public Object getValueAt(int row, int col) { return data[row][col]; }
            @Override
            public String getColumnName(int column) {return tabelinhoud[column];}
            @Override
            public Class getColumnClass(int col) {
                return getValueAt(0,col).getClass();
            }
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };

        aTable = new JTable(dataModel);
        this.add(aTable);
        ListSelectionModel listMod =  aTable.getSelectionModel();
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMod.addListSelectionListener( this );
         
        aTable.addMouseListener(
            new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2){
                        // System.out.println(" double click" );
                           JOptionPane.showMessageDialog(aTable, "Hoi");
                        AccountsBeherenTabel tabel2;
                        tabel2 = new AccountsBeherenTabel();
                    }
                }
            }
        );

        this.setVisible(true);
   }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int maxRows;
        int[] selRows;
        Object value;

        if (!e.getValueIsAdjusting()) {        
            selRows = aTable.getSelectedRows();
            if (selRows.length > 0) {
                for (int i= 0; i < 3 ; i++) {
                    // get Table data
                    TableModel tm = aTable.getModel();
                    value = tm.getValueAt(selRows[0],i);
                    System.out.println("Selection : " + value );
                }
            System.out.println();
            }
        }
    }
}