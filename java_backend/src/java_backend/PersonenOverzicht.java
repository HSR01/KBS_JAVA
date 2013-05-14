/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Justin
 */
public class PersonenOverzicht extends JFrame implements ActionListener {
    JTable aTable;
    
    private Object geselecteerdeWaarde;
    
    public PersonenOverzicht(){ 
    //Standaard instellingen hoofdscherm
        super();
        this.setTitle("TZT Post");
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(800, 600);
    
  
        DbConnect dbc = new DbConnect();
          Object[][] a = dbc.getPersonen();  
        
        for (int z=0; z <a.length; z++) { 
            for(int i=0; i < a.length; i++){
                System.out.println(a[i][z]);
            }
        }

        
        
        setVisible(true);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}