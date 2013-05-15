package java_backend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class BPSTrajectToewijzen extends JFrame implements ActionListener{

    private JTable aTable;
    private String geselecteerdeWaarde;
    
    JPanel jpHoofd, jpCenter, jpVelden, jpKnoppen, jpLabels;
    JTextField jtBegin, jtEind;
    JLabel jlBegin, jlEind;
    JButton jbVerwijder, jbSluit, jbWijzig;
    
    public BPSTrajectToewijzen(){     
        this.setSize(800,600);
        
        jpVelden = new JPanel();
        jpVelden.setLayout( new GridLayout(2,1));
        
        jpLabels = new JPanel();
        jpLabels.setLayout( new GridLayout(2,1));
        
        jtBegin = new JTextField(12);
        jtEind = new JTextField(12);
        jlBegin = new JLabel("Beginpunt: ");
        jlEind = new JLabel( "Eindpunt : ");
        
        jpLabels.add( jlBegin );
        jpVelden.add( jtBegin );
        jpLabels.add( jlEind );
        jpVelden.add( jtEind );
        
        jpKnoppen = new JPanel();
        jbSluit = new JButton("Sluit");
        jbVerwijder = new JButton("Verwijder");
        jbWijzig = new JButton("Wijzig");
        
        jbSluit.addActionListener ( this );
        jbVerwijder.addActionListener ( this );
        jbWijzig.addActionListener ( this );
        
        jpKnoppen.add( jbSluit );
        jpKnoppen.add( jbVerwijder );
        jpKnoppen.add( jbWijzig );
        
        jpHoofd = new JPanel();
        jpHoofd.setLayout(new BorderLayout());
        
        jpCenter = new JPanel();
        jpCenter.setLayout(new BorderLayout());
        
        jpCenter.add( jpLabels, BorderLayout.WEST );
        jpCenter.add( jpVelden, BorderLayout.EAST );
        jpHoofd.add( jpCenter, BorderLayout.WEST );
        jpHoofd.add( jpKnoppen, BorderLayout.SOUTH );
        
        this.add(jpHoofd);
        this.setVisible(true);   
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if( ae.getSource() == jbSluit){
            
        }
        if( ae.getSource() == jbWijzig){
            
        }
        if( ae.getSource() == jbVerwijder){
            
        }
    }
}