package java_backend;



import java.sql.*;
import javax.security.auth.login.FailedLoginException;


/*
 * Belangrijk zorg bij het uitvoeren van deze classe de plugin toegevoegd is aan het project!!!!
 * 
 */

import java.sql.*;


import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Daniel
 */
public class DbConnect {

    //Initializeer connection, statement en result.
    private Connection con;
    private Statement st;
    private ResultSet rs;

    private String persoontabel = "LocatieID, Voornaam, Tussenvoegsel, Achternaam, Emailadres, Wachtwoord, Geboortedatum, Mobielnummer, Profielfoto, IBAN";
    private String locatie = "Latitude, Longitude, Plaatsnaam, Straatnaam, Huisnummer, Toevoeging, Postcode, Telefoonnummer, TZTPoint";
    
    private String tabel;
    

    //Server url
    String url = "jdbc:mysql://server48.firstfind.nl/vanderbe-2";
    //Server login naam
    String user = "vanderbe";
    //Server wachtwoord
    String pasw = "Daniel26061990";
    //Query holder
    String query = "";

    public DbConnect() {
        //Probeer mysql driver te laden
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pasw);
            st = con.createStatement();
            //Afvangen fouten voor database connectie    
        } catch(SQLException ex){
            System.out.println("Problemen met verbinding.");
        }catch (Exception ex) {
            System.out.println("Onbekende error");
        }
        System.out.println(st);

    }
    /**
     * @author Jelle
     * @description Controleerd of verbinding goed is of niet adhv dbconnect.
     * @return Boolean
     */
    public boolean checkConnection(){
        //als st niet null is is connection actief.
        if(st != null){
            return true;
        }else{
            //bij null stuur false, omdat connectie niet actief is.
            return false;
        }
    }
    public void getData(){
        //Query voor uitlezen!!!!----->
        try {

            //Select query
            query = "SELECT * FROM test";

            //Select collum
            String collum = "YOLO";

            //Query uitvoeren
            rs = st.executeQuery(query);

            //Loop door de query data heen
            while (rs.next()) {
                String content = rs.getString(collum);
                System.out.println(content);
            }
            //Afvangen fouten voor getdata    
        } catch (Exception ea) {
            System.out.println("Query lees ERROR: " + ea);
        }

    }

    public Object[][] getUsers(){
        try{
            //get aantal personen.
            rs = st.executeQuery("Select Count(*) from Persoon");
            int aantal = 0;
            while(rs.next()){
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][11];
            query = "SELECT * from Persoon";
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                returnval[i][0] = rs.getString("PersoonID");
                returnval[i][1] = rs.getString("Voornaam");
                returnval[i][2] = rs.getString("Tussenvoegsel");
                returnval[i][3] = rs.getString("Achternaam");
                returnval[i][4] = rs.getString("Emailadres");
                returnval[i][5] = rs.getString("Wachtwoord");
                returnval[i][6] = rs.getString("Geboortedatum");
                returnval[i][7] = rs.getString("Mobielnummer");
                returnval[i][8] = rs.getString("Profielfoto");
                returnval[i][9] = rs.getString("IBAN");
                returnval[i][10] = rs.getString("Rechten");
                i++;
            }
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

        public Object[][] getPersonen(int ID){
        try{
            //get aantal personen.
            rs = st.executeQuery("Select count(*) from Persoon");
            int aantal = 0;
            while(rs.next()){
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][7];
            query = "SELECT P.PersoonID, P.Voornaam, P.Tussenvoegsel, P.Achternaam, Tr.TrajectID, Tr.Begin, Tr.Eind FROM Persoon P JOIN Traject_BPS T ON P.PersoonID = T.PersoonID JOIN Traject Tr ON T.TrajectID = Tr.TrajectID WHERE P.PersoonID = \"" + ID + "\"";
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                returnval[i][0] = rs.getString("PersoonID");
                returnval[i][1] = rs.getString("Voornaam");
                returnval[i][2] = rs.getString("Tussenvoegsel");
                returnval[i][3] = rs.getString("Achternaam");
                returnval[i][4] = rs.getString("TrajectID");
                returnval[i][5] = rs.getString("Begin");
                returnval[i][6] = rs.getString("Eind");
                i++;
            }
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
    
    public String[] getSpecifiekeGebruikerGegevens(Object ID){
        // Auteur Dominique
        try{
            String[] returnval = new String[11];
            query = "SELECT * from Persoon Where PersoonID = " + ID;
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                returnval[0] = rs.getString("PersoonID");
                returnval[1] = rs.getString("Voornaam");
                returnval[2] = rs.getString("Tussenvoegsel");
                returnval[3] = rs.getString("Achternaam");
                returnval[4] = rs.getString("Emailadres");
                returnval[5] = rs.getString("Wachtwoord");
                returnval[6] = rs.getString("Geboortedatum");
                returnval[7] = rs.getString("Mobielnummer");
                returnval[8] = rs.getString("Profielfoto");
                returnval[9] = rs.getString("IBAN");
                returnval[10] = rs.getString("Rechten");
                i++;
            }
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
    
 
    
    
      public String[] getSpecifiekeGebruikerLocatie(Object ID){
        // Auteur Dominique
        try{
            String[] returnval = new String[5];
            
            // Haalt het bijbehorende LocatieID object op.
            query = "SELECT LocatieID from Persoon_Locatie Where PersoonID = " + ID;
            rs = st.executeQuery(query);
            while(rs.next()){
                returnval[0] = rs.getString("LocatieID");
            }
            
            // Gebruikt locatieID en haalt de records daarvan uit database
            query = "SELECT * from Locatie Where LocatieID = " + returnval[0];
            rs = st.executeQuery(query);
            while(rs.next()){
                returnval[0] = rs.getString("Plaatsnaam");
                returnval[1] = rs.getString("Straatnaam");
                returnval[2] = rs.getString("Huisnummer");
                returnval[3] = rs.getString("Toevoeging");
                returnval[4] = rs.getString("Postcode");}
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
    
    
    public Boolean updateGebruikerAccount(String[] data){
        try{
            query = "UPDATE Persoon "
                    + "SET Voornaam = \"" + data[1] + "\", "
                    + "Tussenvoegsel = \"" + data[2] + "\", "
                    + "Achternaam = \"" + data[3] + "\", "
                    + "Emailadres = \"" + data[4] + "\", "
                    + "Wachtwoord = \"" + data[5] + "\", " 
                    + "Geboortedatum = \"" + data[6] + "\", "
                    + "Mobielnummer = \"" + data[7] + "\", "
                    + "Profielfoto = \"" + data[8] + "\", "
                    + "IBAN = \"" + data[9] + "\", "
                    + "Rechten = \"" + data[10] + "\" "
                    + "WHERE PersoonID = \"" + data[0] + "\"";
            System.out.println(query);
            st.executeUpdate(query);
        }catch(Exception e){
            System.out.println("error : " + e.getMessage());

        }
        return null;
    }    
    /**
     * @author Laurens
     * @autorv2 Jelle
     * @param emailadres
     * @param wachtwoord
     * @param succes
     * @return Object of Persoon or NULL
     * @throws SQLException 
     */
    public Persoon getLoginData(String emailadres, String wachtwoord, boolean succes) throws SQLException{
        //Query voor uitlezen login gegevens!!!!----->
        PreparedStatement stmt = null;

        try {
            //Select query
            //rechten >0 houd in dat iedereen met rechten BOVEN BPS'er in mogen loggen.
            stmt = con.prepareStatement("SELECT * FROM Persoon WHERE Emailadres = ? AND Wachtwoord = ? AND rechten > 0");

           stmt.setString(1, emailadres);
           stmt.setString(2, wachtwoord);
           
            //Select collum
            String password = "Wachtwoord";
            String email = "Emailadres";

            //Query uitvoeren
            rs = stmt.executeQuery();

            //Loop door de query data heen
            while (rs.next()) {
                
                String content = rs.getString(password);
                String content1 = rs.getString(email);

                if (content1.equals(emailadres) && content.equals(wachtwoord)) {     
                    //inloggen is gelukt, vul persoon object.
                    Persoon p = new Persoon();
                    p.setVoornaam(rs.getString("Voornaam"));
                    p.setTussenvoegsel(rs.getString("Tussenvoegsel"));
                    p.setAchternaam(rs.getString("Achternaam"));
                    p.setEmailadres(rs.getString("Emailadres"));
                    
                    //zet geboortedatum om van String uit DB naar Date in Java
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        p.setGeboortedatum(df.parse(rs.getString("Geboortedatum")));
                        System.out.println(p.getGeboortedatum());
                    } catch (ParseException ex) {
                        Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    p.setMobielnummer(rs.getString("Mobielnummer"));
                    p.setPersoonID(rs.getInt("PersoonID"));
                    p.setRechten(rs.getInt("Rechten"));
                    
                    //object is gevuld geef het object terug.
                    return p;

                }
                else {
                    //geen match gevonden in het systeem, inloggen is niet succesvol.
                    return null;
                }
            }
        }
       finally {
            //sluiten van databaseconnectie
      try {
         if (stmt != null) { stmt.close(); }
      }
      catch (Exception e) {
         // log this error
      }
      try {
         if (con != null) { con.close(); }
      }
      catch (Exception e) {
         // log this error
      }
   }
        return null;
} 
        

    

    public void insertData(String content, String aa){
        //Query voor inserten!!!!----->       

        try {

            //Insert query
            query = "INSERT INTO test VALUES('" + content + "')";

            //Query uitvoeren
            st.executeUpdate(query);

        } catch (Exception ea) {
            System.out.println("Query schrijf ERROR: " + ea);

        }

    }

    public void insertData(){
        //Query voor inserten!!!!----->       
        try {

            //Insert query
            query = "INSERT INTO test VALUES('')";

            //Query uitvoeren
            st.executeUpdate(query);

        } catch (Exception ea) {
            System.out.println("Query schrijf ERROR: " + ea);

        }

    }


      
    public void insertData(String tabelnaam, String ... value){
        //Query voor inserten!!!!----->   
        String waardes = "";
        
        if(tabelnaam.equals("Persoon")){
            this.tabel = this.persoontabel;                      
        }else if(tabelnaam.equals("Locatie")){
            this.tabel = this.locatie;
        }  
        
        for (int i = 0; i < value.length; i++){
            waardes += i <= value.length-2  ? 
                    value [i] + "','" : 
                    value [i] + "" ;
        }
            System.out.println(tabelnaam);
            System.out.println(tabel);
            System.out.println(waardes);

            try{
                  
                //Insert query
                query = "INSERT INTO  " + tabelnaam + " ( " + tabel + ")"
                      + " VALUES('" + waardes + "')"; 
                
                //Query uitvoeren
                st.executeUpdate(query);
                System.out.println(query);
                
            }catch(Exception e){
                System.out.println(e);
            }
    }
            
  


    public void updateData(String field, String content){
        //Query voor updaten!!!!----->
        try {


            //Select collum
            String collum = "YOLO";

            //Update query
            query = "UPDATE test SET " + collum + "='" + content + "' WHERE " + collum + "'" + field + "'";

            //Query uitvoeren
            st.executeUpdate(query);

        } catch (Exception ea) {
            System.out.println("Query update ERROR: " + ea);

        }

    }
            public Object[][] getPakket(){
        try{
            //LAURENS
            //get aantal personen.
            rs = st.executeQuery("SELECT Count(*) FROM Pakket P JOIN Verzending V ON P.PakketID = V.VerzendingID JOIN Traject T ON V.VerzendingID = T.VerzendingID");
           int aantal = 0;
            while(rs.next()){
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][9];
            query = "SELECT P.PakketID, P.Gewicht, P.Prijs, P.Omschrijving, P.Datum, V.VerzendingID, T.TrajectID, T.Begin, T.Eind FROM Pakket P JOIN Verzending V ON P.PakketID = V.VerzendingID JOIN Traject T ON V.VerzendingID = T.VerzendingID";
            rs = st.executeQuery(query);
            int i = 0;
            while(rs.next()){
                returnval[i][0] = rs.getString("PakketID");
                returnval[i][1] = rs.getString("Gewicht");
                returnval[i][2] = rs.getString("Prijs");
                returnval[i][3] = rs.getString("Omschrijving");
                returnval[i][4] = rs.getString("Datum");
                returnval[i][5] = rs.getString("VerzendingID");
                returnval[i][6] = rs.getString("TrajectID");
                returnval[i][7] = rs.getString("Begin");
                returnval[i][8] = rs.getString("Eind");
                i++;
            }
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
            
                public Object[] getLocatie  (){
        try{
            //LAURENS
            String[] returnval = new String[1];
            //haal alles op.
            
            query = "SELECT Plaats FROM Locatie";
            rs = st.executeQuery(query);
            
            while(rs.next()){
                returnval[0] = rs.getString("Plaatsnaam");
                
                
            }
            return returnval;
        }catch(Exception e){
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
}
  


