package GUI_helpers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java_backend.DbConnect;
import java_backend.WijzigPersoon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class CustomJTable extends JPanel implements ListSelectionListener {
    //  Initialiseren van CustomJTable
    JTable jTable;
    //  De ID van de geselecteerde rij.
    private Object selectedID;
    //  De database connectie
    DbConnect dbc = new DbConnect();
    
    /**
     * Haal een CustomJTable op gevuld met de data die je mee geeft.
     * @param columnnames De namen van de kolommen.
     * @param columnsizes De grote van de kolommen. (evenveel opgeven als namen)
     * @param data De data uit de database.
     */
    public CustomJTable(final String[] columnnames, final int[] columnsizes, final Object[][] data) {        
        TableModel dataModel = new AbstractTableModel() {
            // Met de volgende functies wordt het tablemodel gemaakt, niets wijzigen
            /**
             * Haalt het aantal kolommen op
             */
            @Override
            public int getColumnCount() { return columnnames.length; }
            /**
             * Haalt het aantal rijen op
             */
            @Override
            public int getRowCount() { return data.length; }
            /**
             * Haalt een bepaalde waarde op van een rij en een kolom (beide als intergers opgeven)
             * @param row Interger van de rij
             * @param column Interger van de kolom
             */
            @Override
            public Object getValueAt(int row, int column) { return data[row][column]; }
            /**
             * Haal de kolomnaam op
             * @param column Interger van de kolom
             */
            @Override
            public String getColumnName(int column) { return columnnames[column]; }
            /**
             * Zet een waarde op de plaats van een rij en een kolom (beide als intergers opgeven)
             * @param value Waarde die geplaatst moet worden
             * @param row Interger van de rij van de plaats
             * @param colum Interger van de kolom van de plaats
             */
            @Override
            public void setValueAt(Object value, int row, int column) { data[row][column] = value; }
        };

        // Maakt de tabel aan
        jTable = new javax.swing.JTable(dataModel);
        jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        // Implementeer de grote van de kolommen
        for (int i = 0; i <= (dataModel.getColumnCount() - 1); i++) {
            jTable.getColumnModel().getColumn(i).setPreferredWidth(columnsizes[i]);
        }
        
        // Voegt de tabel toe aan het panel in een scrollpane
        this.add(new JScrollPane(jTable));
      
        // Dit is de list selectioner, die kijkt of je iets selecteert
        ListSelectionModel listMod = jTable.getSelectionModel();
        // Hierdoor kan je maar 1 regel selecteren
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Voegt de listener toe aan het frame
        listMod.addListSelectionListener(this);
        
        // Voegt een mouselistener toe aan het frame
        jTable.addMouseListener(
            new MouseAdapter() {
                @Override
                // Deze word geactiveerd als je klikt
                public void mouseClicked(MouseEvent e) {
                    // Zodra je 2x achter elkaar snel klikt word deze code uitgevoerd
                    if (e.getClickCount() == 2) {
                        // Haalt specifieke data op uit de databse
                        final String[] specifiekeGebruikerGegevens = dbc.getSpecifiekeGebruikerGegevens(selectedID);
                        final String[] specifiekeGebruikerLocatie = dbc.getSpecifiekeGebruikerLocatie(selectedID);
                        // Voert de update query uit.
                        WijzigPersoon wijzigData = new WijzigPersoon(specifiekeGebruikerGegevens, specifiekeGebruikerLocatie);
                    }
                }
            }
        );
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Pakt de tablemodel van jTable
        TableModel tm = jTable.getModel();
        // Bepaalt de geselecteerde rij en vult een array met alle waardes
        int[] selRows = jTable.getSelectedRows();
        // Dit vult selectedID
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0],0);
        // maakt de geselcteerde waarde openbaar
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }
    
    /**
     * Set de geselecteerde waarde
     * @param string 
     */
    public void getSelecteerdeWaarde(Object string) {
        this.selectedID = string;
        System.out.println(selectedID);
    }
}