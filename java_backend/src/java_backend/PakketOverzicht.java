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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
 *
 * @author Laurens
 */
public class PakketOverzicht extends JFrame implements ListSelectionListener, ActionListener {

    private Object geselecteerdeWaarde;
    JTable aTable;
    protected JComboBox Begin, Eind;
    private JPanel North, South;
    private JLabel van, naar;
    private JButton Start;
    private String begin, eind;

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

        DefaultComboBoxModel Beginn = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {
            Beginn.addElement(stad[i]);
        }
        Begin = new JComboBox(Beginn);

        DefaultComboBoxModel Einde = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {

            Einde.addElement(stad[i]);
        }
        
        Eind = new JComboBox(Einde);




        aTable = new JTable(dataModel);
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        this.North = new JPanel();
        this.South = new JPanel();
        this.van = new JLabel("Van:");
        this.naar = new JLabel("Naar:");
        this.Start = new JButton("Start");
        
        South.add(van);
        South.add(Begin);
        South.add(naar);
        South.add(Eind);
        South.add(Start);

        this.add(new JScrollPane(aTable));



        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);


        this.add(North, BorderLayout.NORTH);
        this.add(South, BorderLayout.SOUTH);

        Start.addActionListener(this);

        this.setVisible(true);



    }
    
    public TableModel VerVerstabel(){
        
        DbConnect dbc = new DbConnect();
        final String[] tabelinhoud = {"PakketID", "Gewicht", "Prijs", "Omschrijving", "Datum", "VerzendingID", "TrajectID", "Begin", "Eind"};
        begin = (String) Begin.getSelectedItem();
        eind = (String) Eind.getSelectedItem();
        final Object[][] data = dbc.getSpecifiekPakket(begin, eind);
        
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Start) { //Aantal pakketen op een traject
            //this.hide();
            DbConnect dbc = new DbConnect();
            
            aTable.setModel(VerVerstabel()); //Ververst tabel, maakt hem leeg
            aTable.repaint(); 
            //final Object[][] data = dbc.getPakket();
            
            System.out.println("Start");
	}   
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
