package java_backend;
import java.net.*;
import java.io.*;

public class Geocoding{

    //test opzet
    public String test(){
        HttpURLConnection conn;
        URL url;
        BufferedReader rd;
        String line;
        String result = "";
        try {
         url = new URL("http://routes.cloudmade.com/3b072221200c491981975ab50d5134b3/api/0.3/47.25976,9.58423,47.26117,9.59882/car/shortest.js");
         conn = (HttpURLConnection) url.openConnection();
         conn.setRequestMethod("GET");
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         while ((line = rd.readLine()) != null) {
            result += line;
         }
         rd.close();
        } catch (Exception e) {
         e.printStackTrace();
        }
        return result;
    }
}