/*
 */
package java_backend;

/**
 *
 * @author LeonHuzen
 */
public class Traject {
    public int Meters;
    
    public Traject (int meters) {
        this.Meters = meters;
    }
    
    @Override
    public String toString() {
        return "" + this.Meters;
    }
}
