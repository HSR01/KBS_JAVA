package java_backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class Java_backend {
    public static void main(String[] args) {
        GUI GUI = new GUI();      
    }
    
    /**
     * author Leon Huzen
     */
    public static void geoTest() {
    
        Geocoding geo = new Geocoding();
        Coordinaten jelle=null, daniel=null;
        try {
            jelle = geo.QueryAndGetCoordinates("Zutphen", "Slindewaterstraat", 26);
            System.out.println("Coordinaten Zuthpen, Slindewaterstraat 26: " + jelle.toString());
            
            daniel = geo.QueryAndGetCoordinates("Elburg", "Hoekwant", 62);
            System.out.println("Coordinaten Elburg, Hoekwant 62: " + daniel.toString());
        } catch (MultipleAdressesFoundException ex) {
            Logger.getLogger(Java_backend.class.getName()).log(Level.SEVERE, null, ex);
        }
        Traject traj;
        traj = geo.GetRouteFrom(jelle, daniel);
        System.out.println("Aantal kilometer van (Jelle) naar (Daniel)" + traj.toString());
        if (traj.Meters < 20000) {
            // Direct koerier!
        } else if (traj.Meters > 20000) {
            // Coordinaten van TZTPoint (station)
            Coordinaten jelletotzt, tzttodaniel;
            jelletotzt = geo.GetNearestTZTPoint(jelle).getCoordinaten();
            System.out.println("Aantal kilometer van (Jelle) naar het dichtsbijzijnde TZTPoint" + jelletotzt.toString());
            
            tzttodaniel = geo.GetNearestTZTPoint(daniel).getCoordinaten();
            System.out.println("Aantal kilometer van het dichtsbijzijnde TZTPoint naar (Daniel)" + tzttodaniel.toString());
            Traject eerste, tweede;
            eerste = geo.GetRouteFrom(jelle, jelletotzt);
            tweede = geo.GetRouteFrom(tzttodaniel, daniel);
            
            System.out.println("eerste:" + eerste.toString() + "\ntweede:" + tweede.toString());
        }
    }
}
