package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
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

   
public class BPSTrajectToewijzen extends JFrame implements ActionListener, ListSelectionListener {

    // Hoofdframe
    private JPanel jpButtons, jpTable, jpHoofd, jpZoek, jpZoekLabels, jpZoekInvulvelden;
    private JButton jbVervers, jbZoekBPS, jbZoekTraject;
    private JTable jtTabel;
    private JLabel jlVoornaam, jlAchternaam, jlID, jlTrajectID, jlTrajectBegin, jlTrajectEind;
    private JTextField tfVoornaam, tfAchternaam, tfBeginPunt, tfEindPunt;
    
    // Popupframe
    private JPanel jpTrajecten;
    private JButton jbAnnuleer, jbWijzig;
    private JFrame jfScherm;
    
    private String geselecteerdeWaarde;
    public BPSTrajectToewijzen(){
        // Kan later verwijderd worden
        jfScherm = new JFrame();
        jfScherm.setSize(600,400);
        
        // Panels
        jpButtons = new JPanel();
        jpTable = new JPanel();
        jpHoofd = new JPanel();
        jpZoek = new JPanel();
        jpZoekLabels = new JPanel();
        jpZoekInvulvelden = new JPanel();
        
        // Tabel
        jtTabel = new JTable();
        
        // Buttons
        jbVervers = new JButton("Ververs");
        jbVervers.addActionListener( this );
        jbZoekBPS = new JButton("Zoek");
        jbZoekBPS.addActionListener( this );        
        jbZoekTraject = new JButton("Zoek");
        jbZoekTraject.addActionListener( this );

        
        // Labels
        jlVoornaam = new JLabel("Voornaam:");
        jlAchternaam = new JLabel("Achternaam:");
        jlTrajectBegin = new JLabel("Beginpunt:");
        jlTrajectEind = new JLabel("Eindpunt:");
        //Invulvelden
        tfVoornaam = new JTextField();
        tfAchternaam = new JTextField();
        tfBeginPunt = new JTextField();
        tfEindPunt = new JTextField();
        
        jpZoekLabels.setLayout(new GridLayout(2,6));
        jpZoekLabels.add(jlVoornaam);
        jpZoekLabels.add(jlAchternaam);
        jpZoekLabels.add(new JLabel());
        jpZoekLabels.add(new JLabel()); 
        jpZoekLabels.add(new JLabel());
        jpZoekLabels.add(new JLabel());
        
        jpZoekLabels.add(tfVoornaam);
        jpZoekLabels.add(tfAchternaam);
        jpZoekLabels.add(tfBeginPunt);        
        jpZoekLabels.add(jbZoekBPS);
        jpZoekLabels.add(new JLabel()); 
        jpZoekLabels.add(new JLabel());

        jpZoekInvulvelden.setLayout( new GridLayout(2,6));
        jpZoekInvulvelden.add(jlTrajectBegin);
        jpZoekInvulvelden.add(jlTrajectEind);
        jpZoekInvulvelden.add(new JLabel());
        jpZoekInvulvelden.add(new JLabel());
        jpZoekInvulvelden.add(new JLabel());
        jpZoekInvulvelden.add(new JLabel());
        
        jpZoekInvulvelden.add(tfBeginPunt);
        jpZoekInvulvelden.add(tfEindPunt);
        jpZoekInvulvelden.add(jbZoekTraject);
        jpZoekInvulvelden.add(new JLabel());
        jpZoekInvulvelden.add(new JLabel());
        jpZoekInvulvelden.add(new JLabel());

        jpZoek.setLayout(new BorderLayout());
        jpZoek.add(jpZoekLabels, BorderLayout.NORTH);
        jpZoek.add(jpZoekInvulvelden, BorderLayout.SOUTH);
        
        jpButtons.add(jbVervers);
         

          
        jpTrajecten = new JPanel();
        jbAnnuleer = new JButton("Annuleren");
        jbAnnuleer.addActionListener( this );
        
        jbWijzig = new JButton("Wijzigen");
        jbWijzig.addActionListener( this );

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

        jtTabel = new JTable(dataModel);
        jtTabel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtTabel.getColumnModel().getColumn(0).setPreferredWidth(25);
        jtTabel.getColumnModel().getColumn(1).setPreferredWidth(70);
        jtTabel.getColumnModel().getColumn(2).setPreferredWidth(40);
        jtTabel.getColumnModel().getColumn(3).setPreferredWidth(70);
        jtTabel.getColumnModel().getColumn(4).setPreferredWidth(150);
        jtTabel.getColumnModel().getColumn(6).setPreferredWidth(70);
        jtTabel.getColumnModel().getColumn(7).setPreferredWidth(70);
        jtTabel.getColumnModel().getColumn(8).setPreferredWidth(70);
        jtTabel.getColumnModel().getColumn(9).setPreferredWidth(40);
        jtTabel.getColumnModel().getColumn(10).setPreferredWidth(25);

        this.add(new JScrollPane(jtTabel));
        
        ListSelectionModel listMod = jtTabel.getSelectionModel();
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMod.addListSelectionListener( this );
         
        jtTabel.addMouseListener(
            new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount() == 2){
                        DbConnect dbc = new DbConnect();
                        final String[] specifiekeGebruikerGegevens = dbc.getSpecifiekeGebruikerGegevens(geselecteerdeWaarde);
                        System.out.println(specifiekeGebruikerGegevens);
                        final String[] specifiekeGebruikerLocatie = dbc.getSpecifiekeGebruikerLocatie(geselecteerdeWaarde);
                        WijzigPersoon wijzigData = new WijzigPersoon(specifiekeGebruikerGegevens, specifiekeGebruikerLocatie);
                    }
                }
            }
        );
                jpTable.add(jtTabel);
        jpHoofd.setLayout(new BorderLayout());
        jpHoofd.add(jpButtons, BorderLayout.SOUTH);
        jpHoofd.add(jpTable, BorderLayout.CENTER);
        jpHoofd.add(jpZoek, BorderLayout.NORTH);
        jfScherm.add(jpHoofd);
         jfScherm.setVisible(true);
         
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        TableModel tm = jtTabel.getModel();
        int[] selRows = jtTabel.getSelectedRows();
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0],0);
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }
    
    public void getSelecteerdeWaarde(Object string){
        this.geselecteerdeWaarde = (String) string;
    }
       
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
