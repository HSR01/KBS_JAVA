/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

import java.util.Date;

/**
 *
 * @author Laurens
 */
public class Verzending {
    private Date Aankomsttijd;
    private Date Aflevertijd;
    private String Status;
    
    public Verzending(){
     this.Aankomsttijd = new Date();
     this.Aflevertijd = new Date();
     this.Status = "";
        
    }

    /**
     * @return the Aankomsttijd
     */
    public Date getAankomsttijd() {
        return Aankomsttijd;
    }

    /**
     * @return the Aflevertijd
     */
    public Date getAflevertijd() {
        return Aflevertijd;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }
    
    
}
