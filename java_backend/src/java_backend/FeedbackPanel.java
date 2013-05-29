package java_backend;

import Database.DbConnect;
import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Jelle
 */
class FeedbackPanel extends JPanel implements ActionListener, ListSelectionListener{
    private JPanel top, mid;
    private JLabel toplabel, zoeklabel, informatie;
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
        this.informatie = new JLabel("Zoek op PakketID en vind de feedback.");
        
        JPanel bot = new JPanel();
        bot.add(informatie);
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
        this.add(bot, BorderLayout.SOUTH);

        //voeg action listeners toe
        this.zoek.addActionListener(this);
        
        //handel click event af.
        ListSelectionModel listMod = this.info.getSelectionModel();
        //zet selectie modus op enkele regel
        listMod.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Voegt de listener toe aan het frame
        listMod.addListSelectionListener(this);
        
        //voeg mouse listener toe op jtable
        this.info.addMouseListener(
            new MouseAdapter() {
                @Override
                // Mouseclick event.
                public void mouseClicked(MouseEvent e) {
                    // Zodra je 2x achter elkaar snel klikt word deze code uitgevoerd. 
                    //dubbelklik
                    if (e.getClickCount() == 2) {
                        //haal de geselecteerde rij op.
                        int row = info.rowAtPoint(e.getPoint());
                        //haal pakketid op vanuit formulier
                        int pakid = Integer.parseInt(zoekveld.getText());
                        
                        
                        //haal feedback object op aan de hand van row id.
                        //lelijke oplossing op het moment
                        DbConnect dbc = new DbConnect();
                        Feedback[] list = dbc.getFeedback(pakid);
                        Feedback spec = list[row];
                        
                        //maak een jdialog pop up box om alle info in te tonen.
                        
                        JDialog feedbackinfo = new JDialog();
                        feedbackinfo.setSize(300, 300);
                        feedbackinfo.setLayout(new GridLayout(6,2));
                        feedbackinfo.add(new JLabel("FeedbackID"));
                        feedbackinfo.add(new JLabel(""+spec.getFeedbackID()));
                        feedbackinfo.add(new JLabel("PakketID"));
                        feedbackinfo.add(new JLabel(""+spec.getPakketID()));
                        feedbackinfo.add(new JLabel("Waardering"));
                        feedbackinfo.add(new JLabel(spec.getSter()));
                        feedbackinfo.add(new JLabel("Omschrijving"));
                        feedbackinfo.add(new JLabel(spec.getOmschrijving()));
                        feedbackinfo.add(new JLabel("Ontvangststatus"));
                        feedbackinfo.add(new JLabel(spec.getOntvangstString()));
                        feedbackinfo.setVisible(true);
                    }
                }
            }
        );
        
    }
        @Override
    public void valueChanged(ListSelectionEvent e) {

    }
    public TableModel FillTable(final int PakketID){
         TableModel dataModel = new AbstractTableModel() {
         //set colum names.
         final String[] tabelinhoud = {"FeedbackID", "PakketID", "Waardering"};
         //instancier database connectie en haal alle feedback op.
         private DbConnect dbc = new DbConnect();
         Feedback[] results = dbc.getFeedback(PakketID);
         
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
                return 3;
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
                    //haal per kolom dmv getters de juiste waarde op om in het formulier te tonen.
                    if(col == 0){
                        return results[row].getFeedbackID();
                    }else if (col == 1){
                        return results[row].getPakketID();
                    }else{
                        return results[row].getSter();
                    }
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
                if(zoekveld.getText().equals("")){
                    JDialog jd = new JDialog();
                    jd.setSize(200,175);
                    jd.setTitle("Foutmelding");
                    jd.add(new JLabel("Er is niks ingevuld!"));
                    jd.setVisible(true);                   
                }else{
                    info.setModel(FillTable(Integer.parseInt(zoekveld.getText())));
                    info.repaint();
                }
            }catch(NumberFormatException e){
                //toon melding dat het ingevulde pakketid geen nummer is.
                //toon dit in een dialog box.
              JDialog jd = new JDialog();
              jd.setSize(200,175);
              jd.setTitle("Foutmelding");
              jd.add(new JLabel("Het PakketID is geen nummer!"));
              jd.setVisible(true);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
