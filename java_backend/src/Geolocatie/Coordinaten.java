package Geolocatie;

/**
 *
 * @author LeonHuzen
 * Coordinaten volgens de EPSG:4326 specificatie
 */
public class Coordinaten {
    public Double Latitude;
    public Double Longitude;
    
    /**
     * Coordinaten volgens de EPSG:4326 specificatie
     * @param latitude De latitude van de coordinaten
     * @param longitude De longitude van coordinaten
     */
    public Coordinaten (Double latitude, Double longitude) {
        this.Latitude = latitude;
        this.Longitude = longitude;
    } 
    
    @Override
    public String toString() {
        return this.Latitude.toString() + "," + this.Longitude.toString();
    }
}