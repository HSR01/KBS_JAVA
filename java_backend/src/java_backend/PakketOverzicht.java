/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Laurens
 */
public class PakketOverzicht extends JFrame implements ListSelectionListener {

    private Object geselecteerdeWaarde;
    JTable aTable;
    protected JComboBox dag, maand, jaar;
    private JPanel North, South;

    public PakketOverzicht() {
        super();



        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"PakketID", "Gewicht", "Prijs", "Omschrijving", "Datum", "VerzendingID", "TrajectID", "Begin", "Eind"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getPakket();
        final Object[] stad = dbc.getLocatie();

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

        DefaultComboBoxModel dagen = new DefaultComboBoxModel();
        for (int dag = 1; dag < 32; dag++) {
            dagen.addElement(dag);
        }
        dag = new JComboBox(dagen);

        DefaultComboBoxModel maanden = new DefaultComboBoxModel();
        maanden.addElement(stad[0]);
        
         maand = new JComboBox(maanden);




        aTable = new JTable(dataModel);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        this.North = new JPanel();
        this.South = new JPanel();

        South.add(dag);
        South.add(maand);

        this.add(new JScrollPane(aTable));



        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);


        this.add(North, BorderLayout.NORTH);
        this.add(South, BorderLayout.SOUTH);



        this.setVisible(true);



    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
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
}
