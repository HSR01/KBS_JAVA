package java_backend;

public class Java_backend {
    public static void main(String[] args) {
        Geocoding geo = new Geocoding();
        Locatie Daniel = new Locatie("7204MZ", "Zutphen", "Mr. Th. Heemskerkstraat", 26, null, null);
        Locatie Leon = new Locatie("8032CP", "Zwolle", "Gaasp", 6, null, null);
        try {
            System.out.println(geo.QueryAndGetCoordinates(Daniel));
        } catch (MultipleAdressesFoundException me) {
            System.out.println(me.toString());
        }
        
        try {
            System.out.println(geo.GetRouteFrom(Daniel, Leon).toString());
        } catch (Exception e) {
        }
    }
}
