package java_backend;

import java.util.Date;

/**
 *
 * @author Jelle
 */
public class Ontvanger extends Persoon{
    public Ontvanger() {
        super();
    }
    public Ontvanger(String voornaam, String tussenvoegsel, String achternaam, String emailadres, String wachtwoord, Date geboortedatum, String mobielnummer) {
        super(voornaam, tussenvoegsel, achternaam, emailadres, wachtwoord, geboortedatum, mobielnummer);
    }
}
