/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

/**
 *
 * @author Laurens
 */
public class Feedback {
    private int Waardering;
    private String Omschrijving;
    private int Ontvangststatus;
    
    public Feedback(){
        this.Waardering = 0;
        this.Omschrijving = "";
        this.Ontvangststatus = 0;
        
    }
    
    public Feedback(int w, String om, int o){
        Waardering = w;
        Omschrijving = om;
        Ontvangststatus = o;
    }

    /**
     * @return the Waardering
     */
    public int getWaardering() {
        return Waardering;
    }

    /**
     * @return the Omschrijving
     */
    public String getOmschrijving() {
        return Omschrijving;
    }

    /**
     * @return the Ontvangststatus
     */
    public int getOntvangststatus() {
        return Ontvangststatus;
    }
    
    
}
