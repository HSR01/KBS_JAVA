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
public class BPS extends Persoon{
    private String Iban;
    private String Profielfoto;
    
    public BPS(){
        super();
        this.Iban = "";
        this.Profielfoto = "";
    }
    public BPS(String v, String t, String a, String e, String w, Date g, String m, String i, String p){
        super(v, t, a, e, w, g, m);
        this.Iban = i;
        this.Profielfoto = p;
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

    /**
     * @return the Profielfoto
     */
    public String getProfielfoto() {
        return Profielfoto;
    }

    /**
     * @param Profielfoto the Profielfoto to set
     */
    public void setProfielfoto(String Profielfoto) {
        this.Profielfoto = Profielfoto;
    }
}
