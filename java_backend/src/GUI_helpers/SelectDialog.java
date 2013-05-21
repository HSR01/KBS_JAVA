package GUI_helpers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 *
 * @author LeonHuzen
 */
public class SelectDialog extends JDialog implements ActionListener, ListSelectionListener {
    public Object result;
    
    CustomJTable customJTable;
    JPanel North = new JPanel(), Center = new JPanel(), South = new JPanel();
    JButton selecteer = new JButton("Selecteer rij");
    
    public SelectDialog(String[] columnnames, int[] columnsizes, Object[][] data) {
        this.setSize(800, 600);
        this.setModal(true);
        this.setLayout(new BorderLayout());
        
        customJTable = new CustomJTable(columnnames, columnsizes, data);
        Center.add(customJTable);
        South.add(selecteer);
      
        // Dit is de list selectioner, die kijkt of je iets selecteert
        ListSelectionModel listMod = customJTable.getSelectionModel();
        // Voegt de listener toe aan het frame
        listMod.addListSelectionListener(this);
        
        this.add(North, BorderLayout.NORTH);
        this.add(Center, BorderLayout.CENTER);
        this.add(South, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selecteer) {
            this.setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Pakt de tablemodel van customJTable
        TableModel tm = customJTable.getModel();
        // Bepaalt de geselecteerde rij en vult een array met alle waardes
        int[] selRows = customJTable.getSelectedRows();
        
        if(selRows.length > 0) {
            // Dit vult de return waarde
            this.result = tm.getValueAt(selRows[0],0);
            System.out.println(this.result.toString());
        }
    }
}
