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
    private String[] geselecteerdeWaardes;
    private int persoonID;
    
    JPanel jpHoofd, jpCenter, jpVelden, jpKnoppen, jpLabels;
    JTextField jtBegin, jtEind;
    JLabel jlBegin, jlEind;
    JButton jbVerwijder, jbSluit, jbWijzig, jbNieuw;
    private int PersoonID;
    
    public BPSTrajectToewijzen(String[] geselecteerdeWaardes, int persoonID) {
        this.persoonID = persoonID;
        this.setSize(250,150);
        this.geselecteerdeWaardes = geselecteerdeWaardes;
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
        
        vulVelden(this.geselecteerdeWaardes);
        this.add(jpHoofd);
        this.setVisible(true);   
    }
    
    public BPSTrajectToewijzen(int persoonID) {
        this.persoonID = persoonID;
        this.setSize(250,150);
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
        
        vulVelden(this.geselecteerdeWaardes);
        this.add(jpHoofd);
        this.setVisible(true);   
    }    

    public void vulVelden(String[] geselecteerdeWaardes) {
        jtBegin.setText(geselecteerdeWaardes[1]);
        jtEind.setText(geselecteerdeWaardes[2]);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( ae.getSource() == jbSluit) {
            this.setVisible(false);
        }
        if ( ae.getSource() == jbWijzig) {
            String[] gewijzigd = new String[3];
            gewijzigd[0] = jtBegin.getText();
            gewijzigd[1] = jtEind.getText();
            gewijzigd[2] = geselecteerdeWaardes[0];
            DbConnect dbc = new DbConnect();
            dbc.verwijderPersoon("INSERT INTO Traject (Begin, Eind) Values ( " + gewijzigd[0] + gewijzigd[1]);
            
            PersoonTraject.aTable.setModel(PersoonTraject.VerVerstabel(persoonID));
            PersoonTraject.aTable.repaint();            
            this.setVisible(false);
            
        }
        if ( ae.getSource() == jbVerwijder) {
            
        }
        if ( ae.getSource() == jbNieuw){
            
        }
    }
}