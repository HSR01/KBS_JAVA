package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
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
 *
 * @author Laurens
 */
public class PakketOverzicht extends JPanel implements ListSelectionListener, ActionListener {

    private Object geselecteerdeWaarde;
    static JTable aTable;
    protected JComboBox Begin, Eind;
    private JPanel North, South;
    private JLabel van, naar, info;
    private JButton Start;
    private String begin, eind;
    private TableModel dataModel;

    public PakketOverzicht() {
        super();
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"PakketID", "VerzendingID", "TrajectID", "Omschrijving", "Beginplaats", "BeginStraatnaam", "BeginHuisnummer", "BeginToevoeging", "BeginPostcode", "TZTpointBegin", "Eindplaats", "EindStraatnaam", "EindHuisnummer", "EindToevoeging", "EindPostcode", "TZTpointEind"};
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




        aTable = new JTable(dataModel);
        //functie die breedte kolommen zet
        tabel(aTable);

        this.North = new JPanel();
        this.South = new JPanel();
        this.van = new JLabel("Van:");
        this.naar = new JLabel("Naar:");
        this.Start = new JButton("Start");
        this.info = new JLabel("Dit is een overzicht van alle pakketten. Selecteer een begin- en eindplaats om specifieke trajecten te tonen.");

        South.add(van);
        South.add(Begin);
        South.add(naar);
        South.add(Eind);
        South.add(Start);
        North.add(info);

        this.add(new JScrollPane(aTable));



        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);


        this.add(North, BorderLayout.NORTH);
        this.add(South, BorderLayout.SOUTH);

        Start.addActionListener(this);

        this.setVisible(true);



    }

    public TableModel VerVerstabel() {

        DbConnect dbc = new DbConnect();
        final String[] tabelinhoud = {"PakketID", "VerzendingID", "TrajectID", "Omschrijving", "Beginplaats", "BeginStraatnaam", "BeginHuisnummer", "BeginToevoeging", "BeginPostcode", "TZTpointBegin", "Eindplaats", "EindStraatnaam", "EindHuisnummer", "EindToevoeging", "EindPostcode", "TZTpointEind"};
        begin = (String) Begin.getSelectedItem();
        eind = (String) Eind.getSelectedItem();

        final Object[][] data = dbc.getSpecifiekPakket(begin, eind);

        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getColumnCount() {
                return tabelinhoud.length;
            }

            @Override
            public int getRowCount() {
                if (data == null) {
                    return 0;
                } else {
                    return data.length;
                }
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
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Start) { //Aantal pakketen op een traject
            //this.hide();
            DbConnect dbc = new DbConnect();

            PakketOverzicht.aTable.setModel(VerVerstabel()); //Ververst tabel, maakt hem leeg
            tabel(aTable);
            PakketOverzicht.aTable.repaint();






        }
    }

    public void tabel(JTable aTable) {
        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(60);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(60);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(175);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(7).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(8).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(9).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(10).setPreferredWidth(125);
        aTable.getColumnModel().getColumn(11).setPreferredWidth(150);
        aTable.getColumnModel().getColumn(12).setPreferredWidth(125);
        aTable.getColumnModel().getColumn(13).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(14).setPreferredWidth(90);
        aTable.getColumnModel().getColumn(15).setPreferredWidth(90);
        
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
