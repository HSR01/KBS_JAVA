/*
 */
package java_backend;

/**
 *
 * @author LeonHuzen
 */
public class Traject {
    public Locatie Van;
    public Locatie Naar;
    public int Meters;
    
    public Traject (int meters) {
        this.Meters = meters;
    } 
    public Traject (Locatie van, Locatie naar, int meters) {
        this.Van = van;
        this.Naar = naar;
        this.Meters = meters;
    }
    
    @Override
    public String toString() {
        return "" + this.Meters;
    }
}
