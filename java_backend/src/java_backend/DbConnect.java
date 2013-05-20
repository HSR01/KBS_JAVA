package java_backend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import javax.security.auth.login.FailedLoginException;


/*
 * Belangrijk zorg bij het uitvoeren van deze classe de plugin toegevoegd is aan het project!!!!
 * 
 */

import java.sql.*;


import java.sql.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        } catch (SQLException ex) {
            System.out.println("Problemen met verbinding.");
        } catch (Exception ex) {
            System.out.println("Onbekende error");
        }
        System.out.println(st);
    }

    /**
     * @author Jelle
     * @description Controleerd of verbinding goed is of niet adhv dbconnect.
     * @return Boolean
     */
    public boolean checkConnection() {
        //als st niet null is is connection actief.
        if (st != null) {
            return true;
        } else {
            //bij null stuur false, omdat connectie niet actief is.
            return false;
        }
    }

    public void getData() {
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

    public Object[][] getUsers() {
        try {
            //get aantal personen.
            rs = st.executeQuery("Select Count(*) from Persoon");
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][11];
            query = "SELECT * from Persoon";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public Object[][] getPakketPersoon(int ID) {
        try {
            //get aantal personen.
            rs = st.executeQuery("Select count(*) from Persoon");
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][7];
            query = "SELECT P.PersoonID, P.Voornaam, P.Tussenvoegsel, P.Achternaam, Tr.TrajectID, Tr.Begin, Tr.Eind FROM Persoon P JOIN Traject_BPS T ON P.PersoonID = T.PersoonID JOIN Traject Tr ON T.TrajectID = Tr.TrajectID WHERE P.PersoonID = \"" + ID + "\"";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public Object[][] getPersonen(int ID) {
        try {
            //get aantal personen.
            rs = st.executeQuery("Select count(*) from Persoon");
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][7];
            query = "SELECT P.PersoonID, P.Voornaam, P.Tussenvoegsel, P.Achternaam, Tr.TrajectID, Tr.Begin, Tr.Eind FROM Persoon P JOIN Traject_BPS T ON P.PersoonID = T.PersoonID JOIN Traject Tr ON T.TrajectID = Tr.TrajectID";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public String[] getSpecifiekeGebruikerGegevens(Object ID) {
        // Auteur Dominique
        try {
            String[] returnval = new String[11];
            query = "SELECT * from Persoon Where PersoonID = " + ID;
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public String[] getSpecifiekeGebruikerLocatie(Object ID) {
        // Auteur Dominique
        try {
            String[] returnval = new String[5];

            // Haalt het bijbehorende LocatieID object op.
            query = "SELECT LocatieID from Persoon_Locatie Where PersoonID = " + ID;
            rs = st.executeQuery(query);
            while (rs.next()) {
                returnval[0] = rs.getString("LocatieID");
            }

            // Gebruikt locatieID en haalt de records daarvan uit database
            query = "SELECT * from Locatie Where LocatieID = " + returnval[0];
            rs = st.executeQuery(query);
            while (rs.next()) {
                returnval[0] = rs.getString("Plaatsnaam");
                returnval[1] = rs.getString("Straatnaam");
                returnval[2] = rs.getString("Huisnummer");
                returnval[3] = rs.getString("Toevoeging");
                returnval[4] = rs.getString("Postcode");
            }
            return returnval;
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());
        }
        return null;
    }

    public void updateGebruikerAccount(String[] data) {
        String wachtwoord = data[6];
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(wachtwoord.getBytes(), 0, wachtwoord.length());
            wachtwoord = new BigInteger(1, md.digest()).toString(16);
        } catch (Exception o) {
            System.out.println("Hash Error:" + o);
        }
        try {
            query = "UPDATE Persoon "
                    + "SET Voornaam = \"" + data[1] + "\", "
                    + "Tussenvoegsel = \"" + data[2] + "\", "
                    + "Achternaam = \"" + data[3] + "\", "
                    + "Emailadres = \"" + data[5] + "\", "
                    + "Wachtwoord = \"" + wachtwoord + "\", "
                    + "Geboortedatum = \"" + data[4] + "\", "
                    + "Mobielnummer = \"" + data[7] + "\", "
                    + "Profielfoto = \"" + data[8] + "\", "
                    + "IBAN = \"" + data[9] + "\", "
                    + "Rechten = \"" + data[10] + "\" "
                    + "WHERE PersoonID = \"" + data[0] + "\"";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
        }
    }
    
    /**
     * Nieuwe verzending opslaan.
     * @param persoonsLocatie locatie van de verzender
     * @param data meegeven data (11 values) is volgorde:
     * <OL start="0">
     *  <LI>voornaam</LI>
     *  <LI>tussenvoegsel</LI>
     *  <LI>achternaam</LI>
     *  <LI>straatnaam</LI>
     *  <LI>huisnr</LI>
     *  <LI>toevoeging</LI>
     *  <LI>postcode</LI>
     *  <LI>plaats</LI>
     *  <LI>telefoonnummer</LI>
     *  <LI>gewicht</LI>
     *  <LI>omschrijving</LI>
     * </OL>
     * @return 
     */
    public Boolean newVerzending(Locatie persoonsLocatie, String[] data) throws MultipleAdressesFoundException {
        String voornaam = data[0], tussenvoegsel = data[1], achternaam = data[2], straatnaam = data[3], huisnummer = data[4], toevoeging = data[5],
                postcode = data[6], plaats = data[7], telefoonnummer = data[8], gewicht = data[9], omschrijving = data[10];
        Geocoding geo = new Geocoding();
        int locatieId = -1, persoonId = -1, pakketId = -1, verzendingId = -1, trajectId1 = -1, trajectId2 = -1, trajectId3 = -1;
        Coordinaten coordinatenToLocatie;
        coordinatenToLocatie = geo.QueryAndGetCoordinates(data[7], data[3], Integer.parseInt(data[4]), data[5]);
        try {
            // INSERT LOCATIE, get LocatieID
            query = "INSERT INTO Locatie "
                    + "(LocatieID, Latitude, Longitude, Plaatsnaam, Straatnaam, Huisnummer, Toevoeging, Postcode, Telefoonnummer, TZTPoint) "
                    + "VALUES (0, "
                    + "'" + coordinatenToLocatie.Latitude.toString() + "',"
                    + "'" + coordinatenToLocatie.Latitude.toString() + "',"
                    + "'" + plaats + "', "
                    + "'" + straatnaam + "', "
                    + "'" + huisnummer + "', "
                    + "'" + toevoeging + "', "
                    + "'" + postcode + "', "
                    + "'" + telefoonnummer + "', "
                    + "'0')";            
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = st.getGeneratedKeys();

            if (rs.next()) {
                locatieId = rs.getInt(1);
            } else {
            }
            rs.close();
            rs = null;
            
            // INSERT PERSOON, get PersoonID
            query = "INSERT INTO Persoon "
                    + "(PersoonID, LocatieID, Voornaam, Tussenvoegsel, Achternaam) "
                    + "VALUES (0, "
                    + "'" + locatieId + "',"
                    + "'" + voornaam + "',"
                    + "'" + tussenvoegsel + "', "
                    + "'" + achternaam + "')";            
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = st.getGeneratedKeys();

            if (rs.next()) {
                persoonId = rs.getInt(1);
            } else {
            }
            rs.close();
            rs = null;
            
             // INSERT Pakket, get PakketID
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            query = "INSERT INTO Pakket "
                    + "(PakketID, Gewicht, Prijs, Omschrijving, Datum) "
                    + "VALUES (0, "
                    + "'" + gewicht + "',"
                    + "'0', " // TODO prijsberekening
                    + "'" + omschrijving + "', "
                    + "'" + timeStamp + "')";            
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = st.getGeneratedKeys();

            if (rs.next()) {
                pakketId = rs.getInt(1);
            } else {
            }
            rs.close();
            rs = null;
            
            // INSERT Verzending, get VerzendingID
            query = "INSERT INTO Verzending "
                    + "(VerzendingID, PakketID, Aankomsttijd, Aflevertijd, Status) "
                    + "VALUES (0, "
                    + pakketId + ", "
                    + "null, " //todo
                    + "null, " //todo
                    + "'0')";            
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = st.getGeneratedKeys();

            if (rs.next()) {
                verzendingId = rs.getInt(1);
            } else {
            }
            rs.close();
            rs = null;
            
            
            Coordinaten from = null, to = coordinatenToLocatie;
            if (persoonsLocatie.hasCoordinaten())
                from = persoonsLocatie.getCoordinaten();
            else
                from = geo.QueryAndGetCoordinates(persoonsLocatie);
            
            Traject compleetTraject;
            compleetTraject = geo.GetRouteFrom(from, to);
            if (compleetTraject.Meters < 20000) {
                // TODO FINANCIEN! @Daniel en @Leon
            } else if (compleetTraject.Meters > 20000) {
                // Coordinaten van TZTPoint (station)
                Coordinaten fromToTZT, TZTToTo;
                fromToTZT = geo.GetNearestTZTPoint(from).getCoordinaten();
                TZTToTo = geo.GetNearestTZTPoint(to).getCoordinaten();
                
                Traject Traject1, Traject3;
                int stop1, stop2;
                Financien financien = new Financien();
                double[] koerier;
                Traject1 = geo.GetRouteFrom(from, fromToTZT);
                koerier = financien.BerekenKoerier(Traject1.Meters);
                // 1e gedeelte
                stop1 = getLocatieId(fromToTZT, true);
                insertTraject(verzendingId, persoonsLocatie.getId(), stop1, "2:00", Traject1.Meters, 0, (int)Math.round(koerier[1]));
                // 2e gedeelte
                stop2 = getLocatieId(TZTToTo, true);
                insertTraject(verzendingId, stop1, stop2, "1:00", 333, 0, 0);
                // 3e gedeelte
                Traject3 = geo.GetRouteFrom(TZTToTo, to);
                koerier = financien.BerekenKoerier(Traject3.Meters);
                insertTraject(verzendingId, stop2, locatieId, "2:00", Traject3.Meters, 0, (int)Math.round(koerier[1]));
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("(DbConnect.java) @ newVerzending - Error : " + e.getMessage());
        }
        return false;
    }
    
    public int getLocatieId (Coordinaten coordinaten, boolean isTZT) {
        try {
            query = "SELECT LocatieID "
                    + "FROM Locatie "
                    + "WHERE Latitude = '" + coordinaten.Latitude.toString() + "' "
                    + "AND Longitude = '" + coordinaten.Longitude.toString() + "' "
                    + "AND TZTPoint ";
            if (isTZT) { 
                query += "= 1"; 
            } else { 
                query += "!= 0"; 
            }
            rs = st.executeQuery(query);
            while (rs.next()) {
                return rs.getInt("LocatieID");
            }
        } catch (Exception e) {
            System.out.println("(DbConnect.java) @ getLocatieId - Error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * INSERT Traject in database
     * @return TrajectID
     */
    public int insertTraject (int verzendingID, int begin, int eind, String reistijd, int kilometers, int bps, int koerierId) {
        try {
            query = "INSERT INTO Traject "
                    + "(TrajectID, VerzendingID, Begin, Eind, Reistijd, Kilometers, BPS, KoerierID) "
                    + "VALUES (0, "
                    + verzendingID + ","
                    + "'" + begin + "',"
                    + "'" + eind + "', "
                    + "'" + reistijd + "', "  
                    + "'" + kilometers + "', "  
                    + "'" + bps + "', "  
                    + "'" + koerierId + "')";          
            st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            rs = st.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
            }
            rs.close();
            rs = null;
        } catch (Exception e) {
            System.out.println("(DbConnect.java) @ insertTraject - Error: " + e.getMessage());
        }
        return 0;
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
    public Persoon getLoginData(String emailadres, String wachtwoord, boolean succes) throws SQLException {
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

                } else {
                    //geen match gevonden in het systeem, inloggen is niet succesvol.
                    return null;
                }
            }
        } finally {
            //sluiten van databaseconnectie
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e) {
                // log this error
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                // log this error
            }
        }
        return null;
    }

    public void insertData(String content, String aa) {
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

    public void insertData() {
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

    public void insertData(String tabelnaam, String... value) {
        //Query voor inserten!!!!----->   
        String waardes = "";

        if (tabelnaam.equals("Persoon")) {
            this.tabel = this.persoontabel;
        } else if (tabelnaam.equals("Locatie")) {
            this.tabel = this.locatie;
        }

        for (int i = 0; i < value.length; i++) {
            waardes += i <= value.length - 2
                    ? value[i] + "','"
                    : value[i] + "";
        }
        System.out.println(tabelnaam);
        System.out.println(tabel);
        System.out.println(waardes);

        try {

            //Insert query
            query = "INSERT INTO  " + tabelnaam + " ( " + tabel + ")"
                    + " VALUES('" + waardes + "')";

            //Query uitvoeren
            st.executeUpdate(query);
            System.out.println(query);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateData(String field, String content) {
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

    public Object[][] getPakket() {
        try {
            //LAURENS
            //pakt alle pakketen.
            query = "SELECT Count(*) AS aantalPakketten "
                    + "FROM Pakket A "
                    + "JOIN Verzending B ON A.PakketID = B.PakketID "
                    + "JOIN Traject C ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D ON C.Eind = D.LocatieID "
                    + "JOIN Locatie E ON C.Begin = E.LocatieID";
            rs = st.executeQuery(query);
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("aantalPakketten");
            }

           
            Object[][] returnval = new Object[aantal][8];
            query = "SELECT A.PakketID, B.VerzendingID, C.TrajectID, A.Omschrijving, C.Eind, C.Begin, D.Plaatsnaam AS BeginPlaats, E.Plaatsnaam AS EindPlaats "
                    + "FROM Pakket A "
                    + "JOIN Verzending B ON A.PakketID = B.PakketID "
                    + "JOIN Traject C ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D ON C.Eind = D.LocatieID "
                    + "JOIN Locatie E ON C.Begin = E.LocatieID";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
               returnval[i][0] = rs.getString("PakketID");
                returnval[i][1] = rs.getString("VerzendingID");
                returnval[i][2] = rs.getString("TrajectID");
                returnval[i][3] = rs.getString("Omschrijving");
                 returnval[i][4] = rs.getString("Begin");
                returnval[i][5] = rs.getString("Eind");
                returnval[i][6] = rs.getString("BeginPlaats");
                returnval[i][7] = rs.getString("Eindplaats");
                i++;
            }
            return returnval;
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public Object[] getLocatie() {
        try {
            //LAURENS
            String[] returnval = new String[26];
            //haal alles op.

            query = "SELECT Plaatsnaam FROM Locatie WHERE TZTPoint = '1'";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                returnval[i] = rs.getString("Plaatsnaam");


                i++;
            }
            return returnval;
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public Boolean bpsTrajectUpdate(String[] data) {
        /*
         * Auteur: Dominique
         */
        try {
            query = "UPDATE Traject "
                    + "SET Begin = \"" + data[0] + "\", "
                    + "Eind = \"" + data[1] + "\""
                    + "WHERE TrajectID = \"" + data[2] + "\"";
            System.out.println(query);
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());

        }
        return null;
    }

   public Object[][] getSpecifiekPakket(String begin, String eind) {
        try {
            //LAURENS
            //get specifiekpakket.
            rs = st.executeQuery("SELECT COUNT(*) AS aantalPakketten "
                    + "FROM Pakket A "
                    + "JOIN Verzending B ON A.PakketID = B.PakketID "
                    + "JOIN Traject C ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D ON C.Eind = D.LocatieID "
                    + "JOIN Locatie E ON C.Begin = E.LocatieID");
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("aantalPakketten");
            }
            //haal alles op.


            Object[][] returnval = new Object[aantal][8];
            query = "SELECT A.PakketID, B.VerzendingID, C.TrajectID, A.Omschrijving, C.Eind, C.Begin, D.Plaatsnaam AS BeginPlaats, E.Plaatsnaam AS EindPlaats "
                    + "FROM Pakket A "
                    + "JOIN Verzending B ON A.PakketID = B.PakketID "
                    + "JOIN Traject C ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D ON C.Eind = D.LocatieID "
                    + "JOIN Locatie E ON C.Begin = E.LocatieID "
                    + "WHERE D.Plaatsnaam = '" + begin + "' AND E.Plaatsnaam = '" + eind +"'";
            int i = 0;
            rs = st.executeQuery(query);
            while (rs.next()) {

                returnval[i][0] = rs.getString("PakketID");
                returnval[i][1] = rs.getString("VerzendingID");
                returnval[i][2] = rs.getString("TrajectID");
                returnval[i][3] = rs.getString("Omschrijving");
                 returnval[i][4] = rs.getString("Begin");
                returnval[i][5] = rs.getString("Eind");
                returnval[i][6] = rs.getString("BeginPlaats");
                returnval[i][7] = rs.getString("EindPlaats");
                i++;
            }
            return returnval;
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }

    public Object[][] getGebruikStatistiek() {
        try {
            //get aantal personen.
            rs = st.executeQuery(" SELECT Count(*) FROM Persoon P JOIN Traject_BPS T ON P.PersoonID = T.PersoonID JOIN Traject Tr ON T.TrajectID = Tr.TrajectID");
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("Count(*)");
            }
            //haal alles op.
            Object[][] returnval = new Object[aantal][7];
            query = "SELECT P.PersoonID, P.Voornaam, P.Tussenvoegsel, P.Achternaam, Tr.TrajectID, Tr.Begin, Tr.Eind FROM Persoon P JOIN Traject_BPS T ON P.PersoonID = T.PersoonID JOIN Traject Tr ON T.TrajectID = Tr.TrajectID";
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next()) {
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
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());

        }
        return null;
    }
    
    /**
     * @autor Jelle
     * @param int year, year is the year you want the finance information from. 
     * @description gets finance information from database with 
     * @return returnval array with integer information about finance.
     */
    public int[][] getFinance(int year){
        //make query still to do.
        //instancieer returnval
        int[][] returnval = new int[1][5];
        if(year == 0){
            //als jaar 0 is toon dan alle waarden op 0 voor eeste invul van jtable. // lelijke fix
            returnval[0][0] = 0;
            returnval[0][1] = 0;
            returnval[0][2] = 0;
            returnval[0][3] = 0;
            returnval[0][4] = 0;
        }else{
        //maak query en vul returnval;
            returnval[0][0] = year;
            returnval[0][1] = 75;
            returnval[0][2] = 25;
            returnval[0][3] = 1000;
            returnval[0][4] = 100;
        }
        //return de array
        return returnval;
    }
   
public Object[][] getPakketWijzigen(int pakketID) {
        try {
            //get aantal personen.
            rs = st.executeQuery("SELECT COUNT(*)"
                    + "FROM Pakket A "
                    + "JOIN Verzending B "
                    + "ON A.PakketID = B.PakketID "
                    + "JOIN Traject C "
                    + "ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D "
                    + "ON C.Begin = D.LocatieID "
                    + "JOIN Locatie E "
                    + "On C.Eind = E.LocatieID "
                    + "WHERE A.PakketID = " + pakketID + ";");
            
            int aantal = 0;
            while (rs.next()) {
                aantal = rs.getInt("Count(*)");

            }

            Object[][] returnval = new Object[aantal][6];


            //Select query
            query = "SELECT C.TrajectID AS traj, C.BPS AS bp, C.KoerierID AS k, D.Plaatsnaam AS begin, E.Plaatsnaam AS eind, B.Status AS s "
                    + "FROM Pakket A "
                    + "JOIN Verzending B "
                    + "ON A.PakketID = B.PakketID "
                    + "JOIN Traject C "
                    + "ON B.VerzendingID = C.VerzendingID "
                    + "JOIN Locatie D "
                    + "ON C.Begin = D.LocatieID "
                    + "JOIN Locatie E "
                    + "On C.Eind = E.LocatieID "
                    + "WHERE A.PakketID = " + pakketID + ";";
            


            //Query uitvoeren
            rs = st.executeQuery(query);

            //Loop door de query data heen
            int i = 0;
            while (rs.next()) {

                returnval[i][0] = rs.getString("traj");
                returnval[i][1] = rs.getString("bp");
                returnval[i][2] = rs.getString("k");
                returnval[i][3] = rs.getString("begin");
                returnval[i][4] = rs.getString("eind");
                returnval[i][5] = rs.getString("s");
                i++;
            }

            //Afvangen fouten voor getdata
            return returnval;
        } catch (Exception ea) {
            System.out.println("Query lees ERROR: " + ea);
        }
          return null;
    }

    public String getLocatieID(String query) {
        System.out.println(query);
        String locatieID = "";
        try{
            rs = st.executeQuery(query);
            while (rs.next()) {
                locatieID = rs.getString("LocatieID");
            }
            return locatieID;
        } catch (Exception e) {
            System.out.println("error : " + e.getClass());
        }
        return locatieID;
    }
    
    public void nieuweGebruiker(String query) {
        try {
            //Query uitvoeren
            st.executeUpdate(query);

        } catch (Exception ea) {
            System.out.println("Query schrijf ERROR: " + ea);

        }

    }   
}