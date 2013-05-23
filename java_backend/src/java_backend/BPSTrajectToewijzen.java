package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BPSTrajectToewijzen extends JFrame implements ActionListener {

    JComboBox Eind, Begin;    
    private String[] geselecteerdeWaardes;
    private int persoonID;
    private JPanel jpHoofd, jpCenter, jpVelden, jpKnoppen, jpLabels;
    private JTextField jtBegin, jtEind;
    private JLabel jlBegin, jlEind;
    private JButton jbVerwijder, jbSluit, jbWijzig, jbNieuw;
    private String query, query2;
    
    // Deze word aangeroepen als je een traject wilt wijzigen
    public BPSTrajectToewijzen(String[] geselecteerdeWaardes, int persoonID) {
        
        DbConnect dbc = new DbConnect();
        final Object[] stad = dbc.getLocatie();
        this.persoonID = persoonID;
        this.setSize(250, 150);
        this.geselecteerdeWaardes = geselecteerdeWaardes;

        jpVelden = new JPanel();
        jpVelden.setLayout(new GridLayout(2, 1));

        jpLabels = new JPanel();
        jpLabels.setLayout(new GridLayout(2, 1));

        jtBegin = new JTextField(12);
        jtEind = new JTextField(12);
        jlBegin = new JLabel("Beginpunt: ");
        jlEind = new JLabel("Eindpunt : ");
        
        //voegt Steden toe aan het dropdown menu
        DefaultComboBoxModel Beginn = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {
            Beginn.addElement(stad[i]);
        }
        Begin = new JComboBox(Beginn);

        DefaultComboBoxModel Einde = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {

            Einde.addElement(stad[i]);
        }

        Eind = new JComboBox(Einde);

        jpLabels.add(jlBegin);
        jpVelden.add(Begin);
        jpLabels.add(jlEind);
        jpVelden.add(Eind);

        jpKnoppen = new JPanel();
        jbSluit = new JButton("Sluit");
        jbVerwijder = new JButton("Verwijder");
        jbWijzig = new JButton("Wijzig");

        jbSluit.addActionListener(this);
        jbVerwijder.addActionListener(this);
        jbWijzig.addActionListener(this);

        jpKnoppen.add(jbSluit);
        jpKnoppen.add(jbVerwijder);
        jpKnoppen.add(jbWijzig);

        jpHoofd = new JPanel();
        jpHoofd.setLayout(new BorderLayout());

        jpCenter = new JPanel();
        jpCenter.setLayout(new BorderLayout());

        jpCenter.add(jpLabels, BorderLayout.WEST);
        jpCenter.add(jpVelden, BorderLayout.EAST);
        jpHoofd.add(jpCenter, BorderLayout.WEST);
        jpHoofd.add(jpKnoppen, BorderLayout.SOUTH);

        vulVelden(this.geselecteerdeWaardes);
        this.add(jpHoofd);
        this.setVisible(true);
    }

    // Deze word aangeroepen als je een nieuw traject wilt toevoegen
    public BPSTrajectToewijzen(int persoonID) {
        DbConnect dbc = new DbConnect();
        final Object[] stad = dbc.getLocatie();        
        this.persoonID = persoonID;
        this.setSize(250, 150);
        jpVelden = new JPanel();
        jpVelden.setLayout(new GridLayout(2, 1));

        jpLabels = new JPanel();
        jpLabels.setLayout(new GridLayout(2, 1));

        jtBegin = new JTextField(12);
        jtEind = new JTextField(12);
        jlBegin = new JLabel("Beginpunt: ");
        jlEind = new JLabel("Eindpunt : ");

        //voegt Steden toe aan het dropdown menu
        DefaultComboBoxModel Beginn = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {
            Beginn.addElement(stad[i]);
        }
        Begin = new JComboBox(Beginn);

        DefaultComboBoxModel Einde = new DefaultComboBoxModel();
        for (int i = 0; i < 26; i++) {

            Einde.addElement(stad[i]);
        }

        Eind = new JComboBox(Einde);        
        
        jpLabels.add(jlBegin);
        jpVelden.add(Begin);
        jpLabels.add(jlEind);
        jpVelden.add(Eind);

        jpKnoppen = new JPanel();
        jbSluit = new JButton("Sluit");
        jbNieuw = new JButton("Nieuw");

        jbSluit.addActionListener(this);
        jbNieuw.addActionListener(this);

        
        
        jpKnoppen.add(jbSluit);
        jpKnoppen.add(jbNieuw);

        jpHoofd = new JPanel();
        jpHoofd.setLayout(new BorderLayout());

        jpCenter = new JPanel();
        jpCenter.setLayout(new BorderLayout());

        jpCenter.add(jpLabels, BorderLayout.WEST);
        jpCenter.add(jpVelden, BorderLayout.EAST);
        jpHoofd.add(jpCenter, BorderLayout.WEST);
        jpHoofd.add(jpKnoppen, BorderLayout.SOUTH);

        this.add(jpHoofd);
        this.setVisible(true);
    }

    public void vulVelden(String[] geselecteerdeWaardes) {
        jtBegin.setText(geselecteerdeWaardes[1]);
        jtEind.setText(geselecteerdeWaardes[2]);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        DbConnect dbc = new DbConnect();
        int beginid = 0, eindid = 0;
        query = "";
        query2 = "";
        if (ae.getSource() == jbWijzig || ae.getSource() == jbNieuw){
            String beginplaats = (String) Begin.getSelectedItem();
            String eindplaats = (String) Eind.getSelectedItem();
            if( beginplaats.equals("'s Hertogenbosch")){ beginid = 6; }
            else{ beginid = dbc.getPersoonID("SELECT LocatieID AS PersoonID FROM Locatie WHERE Plaatsnaam = '" + beginplaats + "'"); }
            if( eindplaats.equals("'s Hertogenbosch")){ eindid = 6; }
            else { eindid = dbc.getPersoonID("SELECT LocatieID AS PersoonID FROM Locatie WHERE Plaatsnaam = '" + eindplaats + "'"); }
        }
        if (ae.getSource() == jbWijzig) {
            query = ""
                  + "UPDATE Traject "
                  + "SET Begin = '" + beginid + "', "
                  + "Eind = '" + eindid+ "' "
                  + "WHERE TrajectID = '" + geselecteerdeWaardes[0] + "'";
        }
        if (ae.getSource() == jbVerwijder) {
            query = ""
                  + "DELETE FROM Traject "
                  + "WHERE Begin = '" + geselecteerdeWaardes[1] + "' "
                  + "AND Eind = '" + geselecteerdeWaardes[2] + "' "
                  + "AND TrajectID = '" + geselecteerdeWaardes[0] + "'";
            
            query2 = ""
                    + "DELETE FROM Traject_BPS "
                    + "WHERE TrajectID = '" + geselecteerdeWaardes[0] + "'";
                    
        }
        if (ae.getSource() == jbNieuw){
            query = ""
                  + "INSERT INTO Traject (Begin, Eind) "
                  + "VALUES ( '" + Begin.getSelectedIndex() + "', '" + Eind.getSelectedIndex() + "')";
            
            int maxtrajectid = dbc.getPersoonID("SELECT MAX(TrajectID) AS PersoonID FROM Traject");
            
            query2 = ""
                   + "INSERT INTO Traject_BPS (TrajectID, PersoonID) "
                   + "VALUES ( '" + maxtrajectid + "', '" + persoonID + "')";
            System.out.println(query);
            System.out.println(query2);
        }
        if (!query.equals("")){ dbc.verwijderPersoon(query); }
        if (!query2.equals("")){ dbc.verwijderPersoon(query2); }
        
        PersoonTraject.aTable.setModel(PersoonTraject.VerVerstabel(persoonID));
        PersoonTraject.aTable.repaint();
        this.setVisible(false);
    }
}