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
public class Verzender extends Persoon{
    private String Iban;
    
    public Verzender(){
        super();
        this.Iban = "";
    }
    public Verzender(String v, String t, String a, String e, String w, Date g, String m){
        super(v, t, a, e, w, g, m);
        this.Iban = "";
    }
    @Override
    public String toString(){
        return super.toString()+" "+this.getIban(); 
        
    }

    /**
     * @return the Iban
     */
    public String getIban() {
        return Iban;
    }

    /**
     * @param Iban the Iban to set
     */
    public void setIban(String Iban) {
        this.Iban = Iban;
    }
}
