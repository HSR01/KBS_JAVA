package GUI_helpers;

import Database.DbConnect;
import Geolocatie.Geocoding;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author LeonHuzen
 */
public class AvailabilityCheckers implements WindowListener, Runnable {   
    int seconden;
    boolean showing;
    Thread t;
    DbConnect dbc = new DbConnect();
    Geocoding geo = new Geocoding();
    
    /**
     * Om de hoe veel seconden hij moet checken
     * @param aantal_seconden 
     */
    public AvailabilityCheckers (int aantal_seconden) {
        this.seconden = aantal_seconden;
        this.showing = false;
        t = new Thread(this);
        t.start();
    }
    
    private boolean Database () {
        return dbc.checkConnection();
    }
    
    private boolean CloudMade () {
        return geo.checkConnection();
    }
    
    @Override
    public void run() {
        while(true) {
            // Is er al een window actief?
            if (!this.showing) {
                // Error string
                String errors = "<html>";
                JDialog errorDialog = new JDialog();

                // Error dialog properties
                errorDialog.setSize(400,175);
                errorDialog.setTitle("Foutmelding - Connectie.");

                // Is er een connectie met de database
                if (!Database()) {
                    errors += "<p>Database is niet bereikbaar.</p>";
                }
                // Is er een connectie met Cloudmade?
                if (!CloudMade()) {
                    errors += "<p>CloudMade is niet bereikbaar.</p>";
                }
                
                // Toon de error dialog, als er errors zijn
                if (!errors.equals("<html>")) {
                    errors += "</html>";
                    errorDialog.setAlwaysOnTop(true);
                    errorDialog.add(new JLabel(errors));
                    errorDialog.addWindowListener(this);
                    errorDialog.setVisible(true);
                }
            }
            try {
                t.sleep(1000 * this.seconden);
            } catch (InterruptedException ex) {
                System.out.println("Thread AvailabilityCheckers is geinterrupt");
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        this.showing = true;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.showing = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        this.showing = false;
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
