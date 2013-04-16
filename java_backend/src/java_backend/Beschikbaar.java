/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.util.Date;

/**
 *
 * @author Jelle
 */
public class Beschikbaar {
    private boolean Beschikbaar;
    private Date Begin;
    private Date Eind;
    public int Weekend;
    
    public Beschikbaar(){
        this.Begin = new Date();
        this.Beschikbaar = false;
        this.Eind = new Date();
        this.Weekend = 0;
    }
    public Beschikbaar(boolean be, Date b, Date e, int w){
        
    }
}
