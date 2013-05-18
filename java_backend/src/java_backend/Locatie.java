/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

/**
 *
 * @author Jelle
 */
public class Locatie {
    private String Postcode;
    private String Plaatsnaam;
    private String Straatnaam;
    private int Huisnummer;
    private String Toevoeging;
    private String Telefoonnummer;
    private Coordinaten Coordinaten;
    
    public Locatie() {
        this.Postcode = "";
        this.Plaatsnaam = "";
        this.Straatnaam = "";
        this.Huisnummer = 0;
        this.Toevoeging = "";
        this.Telefoonnummer = "";
    }
    public Locatie(String po, String pl, String sr, int h, String to, String tl) {
        this.Postcode = po;
        this.Plaatsnaam = pl;
        this.Straatnaam = sr;
        this.Huisnummer = h;
        this.Toevoeging = to;
        this.Telefoonnummer = tl;        
    }
    public Locatie(String po, String pl, String sr, int h, String to, String tl, Coordinaten coordinaten) {
        this.Postcode = po;
        this.Plaatsnaam = pl;
        this.Straatnaam = sr;
        this.Huisnummer = h;
        this.Toevoeging = to;
        this.Telefoonnummer = tl;    
        this.Coordinaten = coordinaten;
    }
    
    /**
     * @return the Postcode
     */
    public String getPostcode() {
        return Postcode;
    }

    /**
     * @param Postcode the Postcode to set
     */
    public void setPostcode(String Postcode) {
        this.Postcode = Postcode;
    }

    /**
     * @return the Plaatsnaam
     */
    public String getPlaatsnaam() {
        return Plaatsnaam;
    }

    /**
     * @param Plaatsnaam the Plaatsnaam to set
     */
    public void setPlaatsnaam(String Plaatsnaam) {
        this.Plaatsnaam = Plaatsnaam;
    }

    /**
     * @return the Straatnaam
     */
    public String getStraatnaam() {
        return Straatnaam;
    }

    /**
     * @param Straatnaam the Straatnaam to set
     */
    public void setStraatnaam(String Straatnaam) {
        this.Straatnaam = Straatnaam;
    }

    /**
     * @return the Huisnummer
     */
    public int getHuisnummer() {
        return Huisnummer;
    }

    /**
     * @param Huisnummer the Huisnummer to set
     */
    public void setHuisnummer(int Huisnummer) {
        this.Huisnummer = Huisnummer;
    }

    /**
     * @return the Toevoeging
     */
    public String getToevoeging() {
        return Toevoeging;
    }

    /**
     * @param Toevoeging the Toevoeging to set
     */
    public void setToevoeging(String Toevoeging) {
        this.Toevoeging = Toevoeging;
    }

    /**
     * @return the Telefoonnummer
     */
    public String getTelefoonnummer() {
        return Telefoonnummer;
    }

    /**
     * @param Telefoonnummer the Telefoonnummer to set
     */
    public void setTelefoonnummer(String Telefoonnummer) {
        this.Telefoonnummer = Telefoonnummer;
    }

    /**
     * @return the Coordinaten
     */
    public Coordinaten getCoordinaten() {
        return Coordinaten;
    }

    /**
     * @param Coordinaten the Coordinaten to set
     */
    public void setCoordinaten(Coordinaten Coordinaten) {
        this.Coordinaten = Coordinaten;
    }
    
    /**
     * @return Boolean if the Coordinaten are set
     */
    public Boolean hasCoordinaten() {
        return (this.Coordinaten != null ? true : false);
    }
}
