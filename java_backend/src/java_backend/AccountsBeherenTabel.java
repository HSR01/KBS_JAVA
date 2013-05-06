package java_backend;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class AccountsBeherenTabel extends JPanel implements ListSelectionListener {
    JTable aTable;
    private Object geselecteerdeWaarde;
    // private String tableinhoud[] = {"ID", "Naam"};
    
    public AccountsBeherenTabel() {
        final String[] tabelinhoud = {"ID", "Voornaam", "Tussenvoegsel", "Achternaam", "Emailadres", "Wachtwoord", "Geboortedatum", "Mobiel", "Foto", "IBAN", "Rechten"};
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
        aTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(7).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(9).setPreferredWidth(40);
        aTable.getColumnModel().getColumn(10).setPreferredWidth(25);

        this.add(new JScrollPane(aTable));
        
        ListSelectionModel listMod = aTable.getSelectionModel();
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMod.addListSelectionListener( this );
         
        aTable.addMouseListener(
            new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2){
                        DbConnect dbc = new DbConnect();
                        System.out.println(geselecteerdeWaarde);
                        final String[] specifiekeGebruiker = dbc.getSpecificUser(geselecteerdeWaarde);
                        System.out.println(specifiekeGebruiker);
                        WijzigPersoon wijzigData = new WijzigPersoon(specifiekeGebruiker);
                    }
                }
            }
        );
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        TableModel tm = aTable.getModel();
        int[] selRows = aTable.getSelectedRows();
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0],0);
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }
    
    public void getSelecteerdeWaarde(Object string){
        this.geselecteerdeWaarde = string;
    }
}