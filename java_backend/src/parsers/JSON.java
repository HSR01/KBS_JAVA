package parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author LeonHuzen
 */
public class JSON {   
    /**
     * Set the method for the URL request, one of:
     * <UL>
     *  <LI>GET
     *  <LI>POST
     *  <LI>HEAD
     *  <LI>OPTIONS
     *  <LI>PUT
     *  <LI>DELETE
     *  <LI>TRACE
     * </UL> are legal, subject to protocol restrictions. The default method is GET.
     */
    public Object getKeyFromJSONURL(URL url, String key, String requestMethod) throws IOException {
        HttpURLConnection conn;
        BufferedReader rd;
        String line, JSONResult = "";
        
        try {
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = rd.readLine()) != null) {
            JSONResult += line;
        }
        rd.close();
        } catch (IOException e) {
            throw e;
        }
        
        Character firstChar= (Character)JSONResult.charAt(0);
        JSONResult = firstChar.equals('[') ? JSONResult : "[0," + JSONResult + "]";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey(key);
        while(!finder.isEnd()){
            try {
                parser.parse(JSONResult, finder, true);
            } catch (ParseException ex) {
                Logger.getLogger(JSON.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(finder.isFound()){
                finder.setFound(false);
                return finder.getValue();
            }
        }
        return "";
    }
}
