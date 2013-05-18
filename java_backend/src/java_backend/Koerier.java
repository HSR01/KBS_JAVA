/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

/**
 *
 * @author Jelle
 */
public class Koerier {
    private String Bedrijfsnaam;
    private String Bedrijfstelefoonnummer;
    
    public Koerier() {
        this.Bedrijfsnaam = "";
        this.Bedrijfstelefoonnummer = "";
    }
    public Koerier(String bn, String bt) {
        this.Bedrijfsnaam = bn;
        this.Bedrijfstelefoonnummer = bt;
    }
    
    public static Koerier getKoerierById(int id) {
        Koerier k = new Koerier();
        k.setBedrijfsnaam("Bassies Diensten");
        k.setBedrijfstelefoonnummer("25846148415");
        return null;
    }
    
    /**
     * @return the Bedrijfsnaam
     */
    public String getBedrijfsnaam() {
        return Bedrijfsnaam;
    }

    /**
     * @param Bedrijfsnaam the Bedrijfsnaam to set
     */
    public void setBedrijfsnaam(String Bedrijfsnaam) {
        this.Bedrijfsnaam = Bedrijfsnaam;
    }

    /**
     * @return the Bedrijfstelefoonnummer
     */
    public String getBedrijfstelefoonnummer() {
        return Bedrijfstelefoonnummer;
    }

    /**
     * @param Bedrijfstelefoonnummer the Bedrijfstelefoonnummer to set
     */
    public void setBedrijfstelefoonnummer(String Bedrijfstelefoonnummer) {
        this.Bedrijfstelefoonnummer = Bedrijfstelefoonnummer;
    }
}