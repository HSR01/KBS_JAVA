package java_backend;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import parsers.JSON;

public class Geocoding{
    /**
     * @param apikey API key van cloudmade
     * @param query Querystring street:[straatnaam];city:[plaatsnaam]; 
     * @param key De te zoeken key in de JSON return
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public String QueryAndGetKey(String apikey, String query, String key) {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/" + apikey + "/geocoding/v2/find.js?query=country:NL;" + query);
            try {
                return new JSON().getKeyFromJSONURL(url, 
                        key,
                        "GET").toString();
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    /**
     * @param query Querystring street:[straatnaam];city:[plaatsnaam]; 
     * @param key De te zoeken key in de JSON return
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public String QueryAndGetKey(String query, String key) {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/3b072221200c491981975ab50d5134b3/geocoding/v2/find.js?query=country:NL;" + query);
            try {
                return new JSON().getKeyFromJSONURL(url, 
                        key,
                        "GET").toString();
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
        return "";
    }
    /**
     * Get value from key
     * @param url
     * @param key
     * @return The value from the key
     */
    public String QueryAndGetKey(URL url, String key) {
        try {
            return new JSON().getKeyFromJSONURL(url,
                    "GET",
                    key).toString();
        } catch (IOException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    /**
     * @param apikey API key van cloudmade
     * @param query Querystring street:[straatnaam];city:[plaatsnaam]; 
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String apikey, String query) throws MultipleAdressesFoundException {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/" + apikey + "/geocoding/v2/find.js?querycountry:NL;=" + query);
            try {
                return new JSON().getCoordinatenFromJSONURL(url,
                        "GET");
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MultipleAdressesFoundException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * @param query Querystring street:[straatnaam];city:[plaatsnaam]; 
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String query) throws MultipleAdressesFoundException {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/3b072221200c491981975ab50d5134b3/geocoding/v2/find.js?query=country:NL;" + query);
            try {
                return new JSON().getCoordinatenFromJSONURL(url,
                        "GET");
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * @param stad Plaatsnaam
     * @param street Straatnaam
     * @param huisnummer Huisnummer
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String stad, String street, int huisnummer) throws MultipleAdressesFoundException {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/3b072221200c491981975ab50d5134b3/geocoding/v2/find.js?query=country:NL;city:" + stad + ";street:" + street + ";house:" + huisnummer);
            try {
                return new JSON().getCoordinatenFromJSONURL(url,
                        "GET");
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * @param stad Plaatsnaam
     * @param street Straatnaam
     * @param huisnummer Huisnummer
     * @param toevoeging Toevoeging
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String stad, String street, int huisnummer, String toevoeging) throws MultipleAdressesFoundException {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/3b072221200c491981975ab50d5134b3/geocoding/v2/find.js?query=country:NL;city:" + stad + ";street:" + street + ";house:" + huisnummer + toevoeging);
            try {
                return new JSON().getCoordinatenFromJSONURL(url,
                        "GET");
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * @param postcode Plaatsnaam
     * @param huisnummer Huisnummer
     * @param toevoeging Toevoeging
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String postcode, int huisnummer, String toevoeging) throws MultipleAdressesFoundException {
        try {
            URL url = new URL("http://geocoding.cloudmade.com/3b072221200c491981975ab50d5134b3/geocoding/v2/find.js?query=country:NL;postcode:" + postcode + ";house:" + huisnummer + toevoeging);
            try {
                return new JSON().getCoordinatenFromJSONURL(url,
                        "GET");
            } catch (IOException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    /**
     * @param locatie Locatie
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(Locatie locatie) throws MultipleAdressesFoundException {
        String  query = "", 
                plaatsnaam = locatie.getPlaatsnaam(), 
                postcode = locatie.getPostcode(), 
                straatnaam = locatie.getStraatnaam(), 
                huisnummer = locatie.getHuisnummer() + (locatie.getToevoeging() == null ? "" : locatie.getToevoeging());
        if (!plaatsnaam.isEmpty()) {
            query += "city:" + plaatsnaam + ";";
        }
        if (!postcode.isEmpty()) {
            query += "postcode:" + postcode + ";";
        }
        if (!straatnaam.isEmpty()) {
            query += "street:" + straatnaam + ";";
        }
        if (!huisnummer.isEmpty()) {
            query += "house:" + huisnummer + ";";
        }
        return QueryAndGetCoordinates(query.replace(" ", "+").replace("null", ""));
    }
    /**
     * @param apikey API key van cloudmade
     * @param locatie Locatie
     * @return De value van key, lege string als hij niet gevonden kan worden.
     */
    public Coordinaten QueryAndGetCoordinates(String apikey, Locatie locatie) throws MultipleAdressesFoundException {
        String  query = "", 
                plaatsnaam = locatie.getPlaatsnaam(), 
                postcode = locatie.getPostcode(), 
                straatnaam = locatie.getStraatnaam(), 
                huisnummer = locatie.getHuisnummer() + locatie.getToevoeging();
        if (!plaatsnaam.isEmpty()) {
            query += "city:" + plaatsnaam + ";";
        }
        if (!postcode.isEmpty()) {
            query += "postcode:" + postcode + ";";
        }
        if (!straatnaam.isEmpty()) {
            query += "street:" + straatnaam + ";";
        }
        if (!huisnummer.isEmpty()) {
            query += "house:" + huisnummer + ";";
        }
        return QueryAndGetCoordinates(apikey, query);
    }
    /**
     * Haal route op van locatie naar locatie
     * @param from 
     * @param to 
     * @return Traject
     * @throws MultipleAdressesFoundException 
     */
    public Traject GetRouteFrom(Locatie from, Locatie to) throws MultipleAdressesFoundException {
        Coordinaten fromCoordinaten, toCoordinaten;
        String km = "-1";
        if (from.hasCoordinaten()) {
            fromCoordinaten = from.getCoordinaten();
        } else {
            fromCoordinaten = QueryAndGetCoordinates(from);
        }
        if (to.hasCoordinaten()) {
            toCoordinaten = to.getCoordinaten();
        } else {
            toCoordinaten = QueryAndGetCoordinates(to);
        }
        try {
            km = QueryAndGetKey(new URL("http://routes.cloudmade.com/3b072221200c491981975ab50d5134b3/api/0.3/" + fromCoordinaten.toString() + "," + toCoordinaten.toString() + "/car.js?lang=nl"), "total_distance");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Traject(Integer.parseInt(km));
    }
    /**
     * Haal route op van coordinaten naar coordinaten
     * @param from 
     * @param to 
     * @return Traject
     * @throws MultipleAdressesFoundException 
     */
    public Traject GetRouteFrom(Coordinaten fromCoordinaten, Coordinaten toCoordinaten) throws MultipleAdressesFoundException {
        String km = "-1";
        try {
            km = QueryAndGetKey(new URL("http://routes.cloudmade.com/3b072221200c491981975ab50d5134b3/api/0.3/" + fromCoordinaten.toString() + "," + toCoordinaten.toString() + "/car.js?lang=nl"), "total_distance");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Traject(Integer.parseInt(km));
    }
}