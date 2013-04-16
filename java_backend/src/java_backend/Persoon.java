package java_backend;

/**
 * @author Jelle Smeets
 */
import java.util.Date;
package java_backend;


public class Persoon {
  private String Voornaam;
  private String Tussenvoegsel;
  private String Achternaam;
  private String Emailadres;
  private String Wachtwoord;
  private Date Geboortedatum;
  private String Mobielnummer;
  
  public Persoon(){
      this.Voornaam = "";
      this.Tussenvoegsel = "";
      this.Achternaam = "";
      this.Emailadres = "";
      this.Wachtwoord = "";
      this.Geboortedatum = new Date();
      this.Mobielnummer = "";
  }
  public Persoon(String v, String t, String a, String e, String w, Date g, String m){
      this.Voornaam = v;
      this.Tussenvoegsel = t;
      this.Achternaam = a;
      this.Emailadres = e;
      this.Wachtwoord = w;
      this.Geboortedatum = g;
      this.Mobielnummer = m;
  }
    @Override
  public String toString(){
      return this.Voornaam + " "+this.Tussenvoegsel+ " "+this.Achternaam;
  }
    /**
     * @return the Voornaam
     */
    public String getVoornaam() {
        return Voornaam;
    }

    /**
     * @param Voornaam the Voornaam to set
     */
    public void setVoornaam(String Voornaam) {
        this.Voornaam = Voornaam;
    }

    /**
     * @return the Tussenvoegsel
     */
    public String getTussenvoegsel() {
        return Tussenvoegsel;
    }

    /**
     * @param Tussenvoegsel the Tussenvoegsel to set
     */
    public void setTussenvoegsel(String Tussenvoegsel) {
        this.Tussenvoegsel = Tussenvoegsel;
    }

    /**
     * @return the Achternaam
     */
    public String getAchternaam() {
        return Achternaam;
    }

    /**
     * @param Achternaam the Achternaam to set
     */
    public void setAchternaam(String Achternaam) {
        this.Achternaam = Achternaam;
    }

    /**
     * @return the Emailadres
     */
    public String getEmailadres() {
        return Emailadres;
    }

    /**
     * @param Emailadres the Emailadres to set
     */
    public void setEmailadres(String Emailadres) {
        this.Emailadres = Emailadres;
    }

    /**
     * @return the Wachtwoord
     */
    public String getWachtwoord() {
        return Wachtwoord;
    }

    /**
     * @param Wachtwoord the Wachtwoord to set
     */
    public void setWachtwoord(String Wachtwoord) {
        this.Wachtwoord = Wachtwoord;
    }

    /**
     * @return the Geboortedatum
     */
    public Date getGeboortedatum() {
        return Geboortedatum;
    }

    /**
     * @param Geboortedatum the Geboortedatum to set
     */
    public void setGeboortedatum(Date Geboortedatum) {
        this.Geboortedatum = Geboortedatum;
    }

    /**
     * @return the Mobielnummer
     */
    public String getMobielnummer() {
        return Mobielnummer;
    }

    /**
     * @param Mobielnummer the Mobielnummer to set
     */
    public void setMobielnummer(String Mobielnummer) {
        this.Mobielnummer = Mobielnummer;
    }
    
}
