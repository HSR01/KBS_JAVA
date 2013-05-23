package java_backend;

/**
 * @author Jelle Smeets
 */
import java.util.Date;
public class Persoon {
  private int persoonID;
  private int rechten;
  private String Voornaam;
  private String Tussenvoegsel;
  private String Achternaam;
  private String Emailadres;
  private String Wachtwoord;
  private Date Geboortedatum;
  private String Mobielnummer;
  
  public Persoon() {
      this.persoonID = 0;
      this.rechten = 0;
      this.Voornaam = "";
      this.Tussenvoegsel = "";
      this.Achternaam = "";
      this.Emailadres = "";
      this.Wachtwoord = "";
      this.Geboortedatum = new Date();
      this.Mobielnummer = "";
  }
  public Persoon(String voornaam, String tussenvoegsel, String achternaam, String emailadres, String wachtwoord, Date geboortedatum, String mobielnummer) {
      this.Voornaam = voornaam;
      this.Tussenvoegsel = tussenvoegsel;
      this.Achternaam = achternaam;
      this.Emailadres = emailadres;
      this.Wachtwoord = wachtwoord;
      this.Geboortedatum = geboortedatum;
      this.Mobielnummer = mobielnummer;
  }
    @Override
  public String toString() {
      return this.getVoornaam() + " " + this.getTussenvoegsel() +  " " + this.getAchternaam();
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

    /**
     * @return the persoonID
     */
    public int getPersoonID() {
        return persoonID;
    }

    /**
     * @param persoonID the persoonID to set
     */
    public void setPersoonID(int persoonID) {
        this.persoonID = persoonID;
    }

    /**
     * @return the rechten
     */
    public int getRechten() {
        return rechten;
    }

    /**
     * @param rechten the rechten to set
     */
    public void setRechten(int rechten) {
        this.rechten = rechten;
    }
    
}
