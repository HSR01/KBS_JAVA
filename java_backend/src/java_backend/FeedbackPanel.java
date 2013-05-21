/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
 * @author Jelle
 */
class FeedbackPanel extends JPanel implements ActionListener{
    private JPanel top, mid;
    private JLabel toplabel, zoeklabel;
    private JTextField zoekveld;
    private JButton zoek;
    private JTable info;
    private JScrollPane sp;
    
    public FeedbackPanel() {
        //set standaard layout instellingen.
        this.setLayout(new BorderLayout());
        
        
        //instancieer layout objecten
        this.top = new JPanel();
        this.mid = new JPanel();
        this.info = new JTable();
        this.sp = new JScrollPane(this.info);
        
        
        //instancieer onderdelen
        this.toplabel = new JLabel("Feedback");
        this.zoeklabel = new JLabel("PakketID");
        this.zoekveld = new JTextField(10);
        this.zoek = new JButton("Zoek");
        
        //voeg onderdelen toe aan de JPanels
        //top
        this.top.add(this.toplabel);
        this.top.add(this.zoeklabel);
        this.top.add(this.zoekveld);
        this.top.add(this.zoek);
        //mid
        this.mid.add(this.sp);
        
        //voeg onderdelen toe aan layout
        this.add(top, BorderLayout.NORTH);
        this.add(mid, BorderLayout.CENTER);

        //voeg action listeners toe
        this.zoek.addActionListener(this);
    }
    
    public TableModel FillTable(final int PakketID){
         TableModel dataModel = new AbstractTableModel() {
         //set colum names.
         final String[] tabelinhoud = {"FeedbackID", "PakketID", "Waardering", "Omschrijving", "Ontvangststatus"};
         //instancier database connectie en haal alle feedback op.
         private DbConnect dbc = new DbConnect();
         Object[][] results = dbc.getFeedback(PakketID);
         
         /**
          * @author Jelle
          * @description get number of rows.
          */
            @Override
            public int getRowCount() {
                //krijg lengte van feedbacklist
                //als results null is is er geen resultaat gevonden
                if(results == null){
                    //return 0 rijen.
                    return 0;
                }else{
                    //return de rijen van results
                    return results.length;
                }
            }
            /**
             * @author Jelle
             * @description Get number of columns
             */
            @Override
            public int getColumnCount() {
                return 5;
                //throw new UnsupportedOperationException("Not supported yet.");
            }
            /*
             * @author Jelle
             * @description get value at row and colum number.
             */
            @Override
            public Object getValueAt(int row, int col) {
                if(results == null){
                    return 0;
                }else{
                    return results[row][col];
                }
                
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        //als er op de zoekbutton geklikt is.
        if(ae.getSource() == zoek){
            //vul de tabel opnieuw met het pakket id.
            try{
                info.setModel(FillTable(Integer.parseInt(zoekveld.getText())));
                info.repaint();
            }catch(NumberFormatException e){
                //toon melding dat het ingevulde pakketid geen nummer is.
                //toon dit in een dialog box.
              JDialog jd = new JDialog();
              jd.setSize(200,175);
              jd.setTitle("Foutmelding");
              jd.add(new JLabel("Het PakketID is geen nummer!"));
              jd.setVisible(true);
            }
        }
    }
    
}
