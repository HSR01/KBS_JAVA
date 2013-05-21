/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelle
 */
class FeedbackPanel extends JPanel {
    private JPanel top, mid;
    private JLabel toplabel;
    private JTable info;
    private JScrollPane sp;
    
    public FeedbackPanel() {
        //set standaard layout instellingen.
        this.setLayout(new BorderLayout());
        
        
        //instancieer layout objecten
        this.top = new JPanel();
        this.mid = new JPanel();
        this.info = new JTable(FillTable());
        this.sp = new JScrollPane(this.info);
        
        
        //instancieer onderdelen
        this.toplabel = new JLabel("Feedback");
        
        //voeg onderdelen toe aan de JPanels
        //top
        this.top.add(this.toplabel);
        //mid
        this.mid.add(this.sp);
        
        //voeg onderdelen toe aan layout
        this.add(top, BorderLayout.NORTH);
        this.add(mid, BorderLayout.CENTER);
        
        
        DbConnect dbc = new DbConnect();
        
        
    }
    
    public TableModel FillTable(){
         TableModel dataModel = new AbstractTableModel() {
             final String[] tabelinhoud = {"FeedbackID", "PakketID", "Waardering", "Omschrijving", "Ontvangststatus"};
            @Override
            public int getRowCount() {
                return 1;
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getColumnCount() {
                return 5;
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object getValueAt(int row, int col) {
               // throw new UnsupportedOperationException("Not supported yet.");
                return "testdata";
            }
            //set kolom namen
            /*
             * @autor Jelle
             * @description set the column names with the data from tabelinhoud string.
             */
            public String getColumnName(int col){
            return tabelinhoud[col];
            };
        };
         
         return dataModel;
    }
    
}
