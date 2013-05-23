/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Justin
 */
class GebruikStatistieken extends JPanel implements ActionListener {

    static JTable aTable;
    protected JComboBox Begin, Eind;
    private JPanel North, South;
    private JLabel van, naar;
    private JButton Start;
    private String begin, eind;
    private TableModel dataModel;

    public GebruikStatistieken() {
        super();
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Beginplaats", "Eindplaats"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getGebruikStatistiek();
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

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };

        aTable = new JTable(dataModel);
        //Functie dat breedte kollomen zet
        tabel(aTable);

        //voegt Steden toe aan het dropdown menu
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



        this.add(North, BorderLayout.NORTH);
        this.add(South, BorderLayout.SOUTH);

        Start.addActionListener(this);

        this.setVisible(true);
    }

    public TableModel Vernieuwtabel() {


        try {
            DbConnect dbc = new DbConnect();
            final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Beginplaats", "Eindplaats"};
            begin = (String) Begin.getSelectedItem();
            eind = (String) Eind.getSelectedItem();

            final Object[][] data = dbc.getSpecifiekGebruikStatistiek(begin, eind);

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

            tabel(aTable);


            return dataModel;
        } catch (Exception NPE) {
            JOptionPane.showMessageDialog(this, "Er zijn geen waardes voor de opgegeven filter.");
        }
        return null;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Start) { //Aantal pakketen op een traject
            //this.hide();
            DbConnect dbc = new DbConnect();

            GebruikStatistieken.aTable.setModel(Vernieuwtabel()); //Ververst tabel, maakt hem leeg
            tabel(aTable);
            GebruikStatistieken.aTable.repaint();

        }
    }

    public static void tabel(JTable aTable) {

        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(65);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(61);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(140);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(140);

    }
}
