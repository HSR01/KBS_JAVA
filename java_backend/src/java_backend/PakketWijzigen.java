
package java_backend;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Daniel
 */
class PakketWijzigen extends JPanel implements ActionListener{
    private Object geselecteerdeWaarde; 
    private JLabel zoeklabel;
    private JTextField zoekveld;
    private JButton zoek;  
    private JTable info; 
            
    public PakketWijzigen() {
        zoeklabel = new JLabel("Pakket ID :");
        zoekveld = new JTextField(4);
        zoek = new JButton("Zoek");

        //instancieer JTable met datamodel van hierboven.
        this.info = new JTable(FillTabel(0));
        
        //set de layout van het panel op borderlayout
        this.setLayout(new BorderLayout());
        //voeg layout to aan jpanel top
        JPanel top =new JPanel();
        top.add(zoeklabel);
        top.add(this.zoekveld);
        top.add(this.zoek);
        //voeg layout toe aan Jpanel mid
        JPanel mid = new JPanel();
        mid.add(new JScrollPane(this.info));
        //voeg uiteindelijk alles toe aan het centrale geheel.
        this.add(top, BorderLayout.NORTH);
        this.add(mid, BorderLayout.CENTER);
        
        //add actionlisteners
        this.zoek.addActionListener(this);
        
        //tonen maar
        this.setVisible(true);
        
        info.addMouseListener(
            new MouseAdapter() {
                @Override
                // Deze word geactiveerd als je klikt
                public void mouseClicked(MouseEvent e) {
                    // Zodra je 2x achter elkaar snel klikt word deze code uitgevoerd
                    if (e.getClickCount() == 2) {
                        // Database functie
                        DbConnect dbc = new DbConnect();
                        // Haalt specifieke data op uit de databse
                        final String[] specifiekeGebruikerGegevens = dbc.getSpecifiekeGebruikerGegevens(geselecteerdeWaarde);
                        final String[] specifiekeGebruikerLocatie = dbc.getSpecifiekeGebruikerLocatie(geselecteerdeWaarde);
                        // Voert de update query uit.
                        WijzigPersoon wijzigData = new WijzigPersoon(specifiekeGebruikerGegevens, specifiekeGebruikerLocatie);
                    }
                }
            }
        );
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //selectie button geladen.
        if (ae.getSource() == zoek) {
            //jaar geselecteerd.          
                this.info.setModel(FillTabel(Integer.parseInt(zoekveld.getText())));
                //repaint de tabel om het opnieuw weer te geven.
                this.info.repaint();
                
            }
        }


    public TableModel FillTabel(final int pakketID){
            
         TableModel dataModel = new AbstractTableModel() {
                    //instancieer columnnamen
        final String[] tabelinhoud = {"Pakket ID", "Type", "Vervoerder", "Van", "Naar", "Status"};
                DbConnect dbc = new DbConnect();       
                final Object[][] data = dbc.getPakketWijzigen(pakketID);

            public int getColumnCount() { return tabelinhoud.length; }

            public int getRowCount() { return data.length;}
          

            public Object getValueAt(int row, int col) {

                Object[][] returnval = dbc.getPakketWijzigen(pakketID);
                return returnval[row][col];
            }
            
            public String getColumnName(int column){
            return tabelinhoud[column];
            
            };
            
            
        };
         
        return dataModel;
   };  
    
    public void valueChanged(ListSelectionEvent e) {
        // Pakt de tablemodel van aTable
        TableModel tm = info.getModel();
        // Bepaalt de geselecteerde rij en vult een array met alle waardes
        int[] selRows = info.getSelectedRows();
        // Dit vult geselecteerdeWaarde
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0],0);
        // maakt de geselcteerde waarde openbaar
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }
    
    public void getSelecteerdeWaarde(Object string) {
    this.geselecteerdeWaarde = string;
        System.out.println(geselecteerdeWaarde);
    }
}
