package Geolocatie;

/**
 *
 * @author LeonHuzen
 */
public class MultipleAdressesFoundException extends Exception {
    private String Query;
    
    public MultipleAdressesFoundException (String query) {
        this.Query = query;
    }
    
    @Override
    public String toString () {
        return "Meerdere adressen gevonden met query: " + this.Query;
    }
}
