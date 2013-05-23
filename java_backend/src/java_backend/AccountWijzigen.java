package java_backend;

import javax.swing.JPanel;

/**
 *
 * @author Jelle
 */
class AccountWijzigen extends JPanel {

    public AccountWijzigen() {
        
        this.add(new AccountsBeherenTabel());
    /*    DbConnect dbc = new DbConnect();
        
        String[] columnnames = {"ID", "Voornaam", "Tussenvoegsel", "Achternaam", "Emailadres", "Wachtwoord", "Geboortedatum", "Mobiel", "Foto", "IBAN", "Rechten"};
        int[] columnsizes = {25, 70, 40, 70, 150, 70, 70, 70, 40, 25, 50};
        Object[][] data = dbc.getUsers();
        
        this.add(new CustomJTable(columnnames, columnsizes, data));
        this.setVisible(true);
    */
        
    }
    
}
