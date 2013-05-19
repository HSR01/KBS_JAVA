package java_backend;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class PakketWijzigenTabel extends JPanel implements ListSelectionListener {
    // naamgeven JTable
    JTable aTable;
    // Dit word gevuld als je iets selecteerd in de tabel
    private Object geselecteerdeWaarde;
    
    public PakketWijzigenTabel(int pakketID) {
        // Hoe heten de kolommen?
        final String[] tabelinhoud = {"Pakket ID", "Type", "Vervoerder", "Van", "Naar", "Status"};
        // Database connectie
        DbConnect dbc = new DbConnect();
        
        // De data voor de tabel oproepem door middel van een sql functie
        final Object[][] data = dbc.getPakketWijzigen(pakketID);
        
            TableModel dataModel = new AbstractTableModel() {
            // Met de volgende functies word het tablemodel gemaakt, niets wijzigen
            @Override
            public int getColumnCount() { return tabelinhoud.length; }
            @Override
            public int getRowCount() { return data.length;}
            @Override
            public Object getValueAt(int row, int col) { return data[row][col]; }
            @Override
            public String getColumnName(int column) {return tabelinhoud[column];}

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;
            }
        };
        // Maakt de tabel aan
        aTable = new JTable(dataModel);
        // Komende stuk bepaalt de wijdte van de kolommen
      
        
        // Voegt de tabel toe aan het panel in een scrollpane
        this.add(new JScrollPane(aTable));
      
        // Dit is de list selecetioner, die kijkt of je iets selecteert
        ListSelectionModel listMod = aTable.getSelectionModel();
        // Hierdoor kan je maar 1 regel selecteren
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Voegt de listener toe aan het frame
        listMod.addListSelectionListener( this );
        
        // Voegt een mouselistener toe aan het frame
        aTable.addMouseListener(
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
    public void valueChanged(ListSelectionEvent e) {
        // Pakt de tablemodel van aTable
        TableModel tm = aTable.getModel();
        // Bepaalt de geselecteerde rij en vult een array met alle waardes
        int[] selRows = aTable.getSelectedRows();
        // Dit vult geselecteerdeWaarde
        Object geselecteerdeWaarde = tm.getValueAt(selRows[0],0);
        // maakt de geselcteerde waarde openbaar
        getSelecteerdeWaarde(geselecteerdeWaarde);
    }
    
    // Set de geselecteerde waarde
    public void getSelecteerdeWaarde(Object string) {
        this.geselecteerdeWaarde = string;
    }
}