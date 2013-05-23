package java_backend;

import java.util.Date;

/**
 *
 * @author Laurens
 */
public class Pakket {
    private Double Gewicht;
    private Double Prijs;
    private String Omschrijving;
    private Date Datum;
    
    
    public Pakket() {
        this.Gewicht = 0.0;
        this.Prijs = 0.0;
        this.Omschrijving = "";
        this.Datum = new Date();
   }
    
    public Pakket(Double g, Double p, String o, Date d) {
        Gewicht = g;
        Prijs = p;
        Omschrijving = o;
        Datum = d;
    }

    /**
     * @return the Gewicht
     */
    public Double getGewicht() {
        return Gewicht;
    }

    /**
     * @return the Prijs
     */
    public Double getPrijs() {
        return Prijs;
    }

    /**
     * @return the Omschrijving
     */
    public String getOmschrijving() {
        return Omschrijving;
    }

    /**
     * @return the Datum
     */
    public Date getDatum() {
        return Datum;
    }
}
