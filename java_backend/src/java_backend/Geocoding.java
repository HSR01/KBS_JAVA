package java_backend;
import java.io.*;
import java.net.*;
import parsers.JSON;

public class Geocoding{

    //test opzet
    public void test() {
        Object ValueFromKey = null;
        try {
            ValueFromKey = new JSON().getKeyFromJSONURL(new URL("http://routes.cloudmade.com/3b072221200c491981975ab50d5134b3/api/0.3/47.25976,9.58423,47.26117,9.59882/car/shortest.js"), "total_distance", "GET");
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println(ValueFromKey.toString());
    }
}