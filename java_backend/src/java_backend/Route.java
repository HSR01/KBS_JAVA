/*
 */
package java_backend;

/**
 *
 * @author LeonHuzen
 */
public class Route {
    public int Meters;
    
    public Route (int meters) {
        this.Meters = meters;
    }
    
    @Override
    public String toString() {
        return "" + this.Meters;
    }
}
