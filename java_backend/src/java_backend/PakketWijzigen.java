
package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Daniel
 */
class PakketWijzigen extends JPanel implements ActionListener, ListSelectionListener{
    private Object geselecteerdeWaarde; 
    private JLabel zoeklabel;
    private JTextField zoekveld;
    private JButton zoek, opslaan;  
    public static JTable info; 
            
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
        final Frame frame = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, this);
        info.addMouseListener(
            new MouseAdapter() {
                @Override
                // Deze word geactiveerd als je klikt
                public void mouseClicked(MouseEvent e) {
                    // Zodra je 2x achter elkaar snel klikt word deze code uitgevoerd
                    if (e.getClickCount() == 2) {
                        PakketWijzigenPopUp wijzig = new PakketWijzigenPopUp(geselecteerdeWaarde);                        

                    }
                }
            }
        );
        
        // Dit is de list selecetioner, die kijkt of je iets selecteert
        ListSelectionModel listMod = info.getSelectionModel();
        // Hierdoor kan je maar 1 regel selecteren
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Voegt de listener toe aan het frame
        listMod.addListSelectionListener( this );        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //selectie button geladen.
        if (ae.getSource() == zoek) {
            if(zoekveld.getText().equals("")){
                 foutmelding("Foutmelding", "U heeft geen waarde ingevoerd");
            }else if(!isInteger(zoekveld.getText())){
                foutmelding("Foutmelding", "Het pakketnummer moet een getal zijn.");
            }else{
                //succesvol
                //jaar geselecteerd.          
                this.info.setModel(FillTabel(Integer.parseInt(zoekveld.getText())));
                //repaint de tabel om het opnieuw weer te geven.
                this.info.repaint();
                
            }

            }
     }


    public TableModel FillTabel(final int pakketID){
                     final String[] tabelinhoud = {"Traject ID", "Type", "Vervoerderder ID", "Van", "Naar", "Status"};
            DbConnect dbc = new DbConnect();       
            final Object[][] data = dbc.getPakketWijzigen(pakketID);
            if(data.length == 0 && pakketID != 0){
                foutmelding("Foutmelding", "Er zijn geen resultaten gevonden.");
            }
        TableModel dataModel = new AbstractTableModel() {
            //instancieer columnnamen

            
            public int getColumnCount() { return tabelinhoud.length; }

            public int getRowCount() { return data.length;}
            
            public Object getValueAt(int row, int col) { return data[row][col]; }
            
            public String getColumnName(int column){ return tabelinhoud[column]; };
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
    }
        /**
     * Toon foutmelding aan de hand van meegegeven parameters.
     * @author Jelle Smeets
     * @param titel
     * @param melding 
     */
    public static void foutmelding(String titel, String melding){
        JDialog jd = new JDialog();
        jd.setSize(400,175);
        jd.setTitle(titel);
        jd.add(new JLabel(melding));
        jd.setVisible(true);
    }
    
    /**
     * Controleer of string numeriek is.
     * @author Jelle
     * @param String input
     */
    
    public boolean isInteger( String input ){  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch( Exception e)  
       {  
          return false;  
       }  
   }
}
