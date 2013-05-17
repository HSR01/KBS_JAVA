/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import javax.swing.JButton;
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
class GebruikStatistieken extends JPanel {

    static JTable aTable;
    protected JComboBox Begin, Eind;
 
    public GebruikStatistieken() {

        this.add(new JLabel("Gebruik Statistieken"));

        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"PersoonID, Voornaam, Tussenvoegsel, Achternaam, TrajectID, Begin, Eind"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getGebruikStatistiek();

        TableModel datamodels = new AbstractTableModel() {
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
            @Override
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };

        aTable = new JTable(datamodels);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(140);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(70);
        
         this.add(new JScrollPane(aTable));
         
         

        this.setVisible(true);
    }
}
