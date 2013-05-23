package GUI_helpers;

import java_backend.GUI;

/**
 *
 * @author LeonHuzen
 */
public class RunGUI implements Runnable {

    public RunGUI () {
        Thread t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run() {
        //instancieer gui.
        GUI gui = new GUI();
    }
}
