/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import GUI_helpers.CustomJTable;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jelle
 */
class AccountWijzigen extends JPanel {

    public AccountWijzigen() {
        DbConnect dbc = new DbConnect();
        
        String[] columnnames = {"ID", "Voornaam", "Tussenvoegsel", "Achternaam", "Emailadres", "Wachtwoord", "Geboortedatum", "Mobiel", "Foto", "IBAN", "Rechten"};
        int[] columnsizes = {25, 70, 40, 70, 150, 70, 70, 70, 40, 25, 50};
        Object[][] data = dbc.getUsers();
        
        this.add(new CustomJTable(columnnames, columnsizes, data));
        this.setVisible(true);
    }
    
}
