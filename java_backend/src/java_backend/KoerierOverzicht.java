package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Laurens
 */
public class KoerierOverzicht extends JPanel implements ListSelectionListener, ActionListener {

    private Object geselecteerdeWaarde;
    static JTable aTable;
    private JLabel jaar, maand, label;
    private JTextField tjaar, tmaand;
    private JPanel North, South;
    protected JComboBox cjaar, cmaand;
    private TableModel dataModel;
    private JButton Start;
    private String m, j;

    public KoerierOverzicht() {

        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        final String[] tabelinhoud = {"Achternaam", "Bedrijfsnaam", "Emailadres", "Status", "Aankomsttijd", "Aflevertijd", "Kostprijs", "IBAN"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getKoerierKostprijs();


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
        aTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(60);
        aTable.getColumnModel().getColumn(7).setPreferredWidth(125);

        this.add(new JScrollPane(aTable));

        ListSelectionModel listModel = aTable.getSelectionModel();
        listModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel.addListSelectionListener(this);

        String[] jaren = {
            "2013",
            "2014",
            "2015",
            "2016"
        };  //gegevens voor in combobox

        cjaar = new JComboBox(jaren);


        String[] maanden = {
            "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };  //gegevens voor in combobox

        cmaand = new JComboBox(maanden);



        this.North = new JPanel();
        this.South = new JPanel();
        this.jaar = new JLabel("Jaar:");
        this.maand = new JLabel("Maand:");
        this.Start = new JButton("Start");
        this.label = new JLabel("Selecteer een maand en jaar om te zien hoeveel een koerier/bps'er te goed heeft. ");
        
        South.add(maand);
        South.add(cmaand);
        South.add(jaar);
        South.add(cjaar);
        South.add(Start);
        North.add(label);
        
        this.add(North, BorderLayout.NORTH);
        this.add(South, BorderLayout.SOUTH);
        
        //actionlisteners
        Start.addActionListener(this);

        this.setVisible(true);

    }

    public TableModel VerVerstabel() { 

        DbConnect dbc = new DbConnect();
        final String[] tabelinhoud = {"Achternaam", "Bedrijfsnaam", "Emailadres", "Status", "Aankomsttijd", "Aflevertijd", "Kostprijs", "IBAN"};
        j = (String) cjaar.getSelectedItem(); //ingegeven jaar
        m = (String) cmaand.getSelectedItem();//maand
        
        final Object[][] data = dbc.getSpecifiekKoerierKostprijs(m, j);

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

        return dataModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Start) {
            DbConnect dbc = new DbConnect();

            KoerierOverzicht.aTable.setModel(VerVerstabel()); //Ververst tabel, maakt hem leeg
            KoerierOverzicht.aTable.repaint();

            aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //set de size
            aTable.getColumnModel().getColumn(0).setPreferredWidth(80);
            aTable.getColumnModel().getColumn(1).setPreferredWidth(80);
            aTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            aTable.getColumnModel().getColumn(3).setPreferredWidth(50);
            aTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            aTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            aTable.getColumnModel().getColumn(6).setPreferredWidth(60);
            aTable.getColumnModel().getColumn(7).setPreferredWidth(125);

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