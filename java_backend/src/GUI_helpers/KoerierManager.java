package GUI_helpers;

import Database.DbConnect;

/**
 * Zorgt ervoor dat verzendingen door worden gezet naar de goedkoopste koerier
 * @author LeonHuzen
 */
public class KoerierManager implements Runnable {
    private int UrenOpenstaand = 2;
    private int Seconden = 120;
    DbConnect dbc = new DbConnect();
    Thread t;
    
    /**
     * Zorgt ervoor dat verzendingen door worden gezet naar de goedkoopste koerier
     * @param urenOpenstaand Hoe veel uren er nog over moeten zijn
     * @param seconden Na hoe veel seconden er weer gecheckt wordt
     * @author LeonHuzen
     */
    public KoerierManager (int urenOpenstaand, int seconden) {
        this.UrenOpenstaand = urenOpenstaand;
        this.Seconden = seconden;
        t = new Thread(this);
        t.start();
    }
    
    /**
     * Zorgt ervoor dat verzendingen door worden gezet naar de goedkoopste koerier
     * @author LeonHuzen
     */
    public KoerierManager () {
        t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        while(true) {
            dbc.setKoeriersAlsErGeenBPSerIs(this.UrenOpenstaand);
            try {
                t.sleep(1000 * this.Seconden);
            } catch (InterruptedException ex) {
                System.out.println("Thread KoerierManager is geinterrupt");
            }
        }
    }
}
