package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    private JLabel van, naar, zoeken, info;
    private JButton Start;
    private String begin, eind;
    private TableModel dataModel;
    private JTextField zoekveld;
    private JButton zoek;

    public GebruikStatistieken() {                                              //Lay-out & eerste weergave van tabel
        super();
        //     this.setSize(800, 600);
        this.setLayout(new BorderLayout());                                     //Border layout initialiseren

        final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Beginplaats", "Eindplaats"};
        DbConnect dbc = new DbConnect();
        final Object[][] data = dbc.getGebruikStatistiek();                     //Gaat data van database halen.
        final Object[] stad = dbc.getLocatie();                                 //Gaat plaatsnamen uit database halen voor comboboxen


        TableModel dataModel = new AbstractTableModel() {                       //Tabelmodel wordt hier gemaakt
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
        tabel(aTable);                                                          //functie voor kolombreedte

        //voegt Steden toe aan het dropdown menu
        DefaultComboBoxModel Beginn = new DefaultComboBoxModel();               //Combobox voor beginlocatie
        for (int i = 0; i < 26; i++) {
            Beginn.addElement(stad[i]);                                         //Wordt locatie uit DB gehaald (stad = dbc.getLocatie)
        }
        Begin = new JComboBox(Beginn);

        DefaultComboBoxModel Einde = new DefaultComboBoxModel();                //Extra combobox voor eindlocatie
        for (int i = 0; i < 26; i++) {
            Einde.addElement(stad[i]);                                          //Wordt locatie uit DB gehaald (stad = dbc.getLocatie)
        }

        Eind = new JComboBox(Einde);



        this.North = new JPanel();                                              //Panel/Label etc initialiseren
        this.South = new JPanel();
        this.van = new JLabel("Van:");
        this.naar = new JLabel("Naar:");
        this.Start = new JButton("Start");
        this.zoek = new JButton("Zoek");
        this.zoeken = new JLabel("Zoek:");
        this.zoekveld = new JTextField(4);
        this.info = new JLabel("Dit is een overzicht van alle gebruikers + trajecten.");

        South.setLayout(new GridLayout(3, 3));                                  //Grid Lay-out zetten voor "SOUTH" (onder tabel) zetten.

        South.add(zoeken);                                                      //Label/knoppen etc toevoegen/weergeven
        South.add(zoekveld);
        South.add(zoek);

        South.add(van);
        South.add(Begin);
        South.add(new JLabel(""));

        South.add(naar);
        South.add(Eind);
        South.add(Start);

        this.add(new JScrollPane(aTable));                                      //

        North.add(info);
        
        this.add(North, BorderLayout.NORTH);                                    //Koppelen van north/south aan Borderlayout
        this.add(South, BorderLayout.SOUTH);

        Start.addActionListener(this);                                          //ActionListeners voor Start / zoek knop
        zoek.addActionListener(this);

        this.setVisible(true);                                                  //Alles weergeven
    }

    public TableModel Vernieuwtabel() {                                         //Combo boxen


        try {
            DbConnect dbc = new DbConnect();
            final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Beginplaats", "Eindplaats"};
            begin = (String) Begin.getSelectedItem();                           //Aangegeven waarde pakken voor Beginplaats
            eind = (String) Eind.getSelectedItem();                             //Aangegeven waarde pakken voor Eindplaats

            final Object[][] data = dbc.getSpecifiekGebruikStatistiek(begin, eind); //Data wordt hier uit DbConnect (dmv SQL query) gehaald.
            if(data.length == 0){
                foutmelding("Geen zoekresultaten gevonden", "Voor uw zoekopdracht zijn geen resultaten gevonden.");
            }
            TableModel dataModel = new AbstractTableModel() {                   //Tabel model wordt hier gemaakt.
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

            tabel(aTable);                                                      //Kolomgrootte van tabel


            return dataModel;                                                   //Model van tabel teruggeven
        } catch (Exception NPE) {
            JOptionPane.showMessageDialog(this, "Er zijn geen waardes voor de opgegeven filter.");
        }
        return null;
    }

    public TableModel Vernieuwtabel(Object[][] datas) {                         //Zoek functie
        final Object[][] data = datas;            
        if(data.length == 0){
            foutmelding("Geen zoekresultaten gevonden", "Voor uw zoekopdracht zijn geen resultaten gevonden.");
        }
        //Data oproepen voor tabel (uit DbConnect / SQL query)
        try {
            DbConnect dbc = new DbConnect();
            final String[] tabelinhoud = {"PersoonID", "Voornaam", "Tussenvoegsel", "Achternaam", "TrajectID", "Beginplaats", "Eindplaats"};


            TableModel dataModel = new AbstractTableModel() {                   //Tabelmodel wordt hierin gemaakt
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

            tabel(aTable);                                                      //Kolomgrootte van tabel


            return dataModel;                                                   //Type tabel teruggeven
        } catch (Exception NPE) {
            JOptionPane.showMessageDialog(this, "Er zijn geen waardes voor de opgegeven filter.");
        }
        return null;
    }

    public void actionPerformed(ActionEvent ae) {
        DbConnect dbc = new DbConnect();                                        //DbConnectie
        if (ae.getSource() == Start) {                                          //Gaat kijken als knop 'Start' is ingedrukt (Combo boxen)

            GebruikStatistieken.aTable.setModel(Vernieuwtabel());               //Maakt tabel leeg
            tabel(aTable);                                                      //Kolomgrootte van tabel
            GebruikStatistieken.aTable.repaint();                               //Gaat tabel opnieuw invullen. 

        } else if (ae.getSource() == zoek) {                                    //Gaat kijken als knop 'zoek' is ingedrukt (zoekfunctie)
            if(zoekveld.getText().equals("")){
                //foutmelding tonene
                foutmelding("Foutmelding","U heeft geen zoekterm ingevuld.");
            }else{
                
            dbc.getZoekSpecifiekGebruikStatistiek(zoekveld.getText());          //Gaat in DbConnect naar de methode zoeken en ingevulde waarde meenenem.
            GebruikStatistieken.aTable.setModel(Vernieuwtabel(dbc.getZoekSpecifiekGebruikStatistiek(zoekveld.getText()))); //Maakt tabel leeg
            tabel(aTable);                                                      //Kolomgrootte van tabel
            GebruikStatistieken.aTable.repaint();   
            }
                               //Gaat tabel opnieuw invullen.
        }
    }

    public static void tabel(JTable aTable) {                                   //Kolomgrootte van tabel.

        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        aTable.getColumnModel().getColumn(0).setPreferredWidth(65);
        aTable.getColumnModel().getColumn(1).setPreferredWidth(110);
        aTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        aTable.getColumnModel().getColumn(3).setPreferredWidth(110);
        aTable.getColumnModel().getColumn(4).setPreferredWidth(61);
        aTable.getColumnModel().getColumn(5).setPreferredWidth(140);
        aTable.getColumnModel().getColumn(6).setPreferredWidth(140);

    }
    public static void foutmelding(String titel, String melding){
    JDialog jd = new JDialog();
    jd.setSize(400,175);
    jd.setTitle(titel);
    jd.add(new JLabel(melding));
    jd.setVisible(true);
    }
}
