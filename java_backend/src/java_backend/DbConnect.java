package java_backend;


import java.sql.*;


/*
 * Belangrijk zorg bij het uitvoeren van deze classe de plugin toegevoegd is aan het project!!!!
 * 
 */

/**
 *
 * @author Daniel
 */
public class DbConnect {
    
    //Initializeer connection, statement en result.
    private Connection con;    
    private Statement st;
    private ResultSet rs;
    
    //Server url
    String url = "jdbc:mysql://server48.firstfind.nl/vanderbe-2";
    //Server login naam
    String user = "vanderbe";
    //Server wachtwoord
    String pasw = "Daniel26061990";
    //Query holder
    String query = "";
 
    
    public DbConnect(){
        //Probeer mysql driver te laden
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pasw);
            st = con.createStatement();
        //Afvangen fouten voor database connectie    
        }catch(Exception ex){
            System.out.println("Connectie ERROR: " + ex);            
        }
        

    }
    public void getData(){
    //Query voor uitlezen!!!!----->
        try{
            
            //Select query
            query = "SELECT * FROM test";
            
            //Select collum
            String collum = "YOLO";
            
            //Query uitvoeren
            rs = st.executeQuery(query); 
            
            //Loop door de query data heen
            while(rs.next()){
                String content = rs.getString(collum);
                System.out.println(content);
            }
        //Afvangen fouten voor getdata    
        }catch(Exception ea){
            System.out.println("Query lees ERROR: " + ea);
        }     
        
    }
    
         
    public void insertData(String content, String aa){
    //Query voor inserten!!!!----->       
          try{
                     
            //Insert query
            query = "INSERT INTO test VALUES('" + content + "')";              
                        
            //Query uitvoeren
            st.executeUpdate(query);
            
        }catch(Exception ea){
            System.out.println("Query schrijf ERROR: " + ea);
            
        }
        
    }
    public void insertData(){
        //Query voor inserten!!!!----->       
              try{

                //Insert query
                query = "INSERT INTO test VALUES('')";              

                //Query uitvoeren
                st.executeUpdate(query);

            }catch(Exception ea){
                System.out.println("Query schrijf ERROR: " + ea);

            }

     } 
    public void insertData(String vnaam, String tussenv, String anaam, String email, String wachtw, String gebdatum, String mobiel,  String iban){
        //Query voor inserten!!!!----->       
              try{

                //Insert query
                query = "INSERT INTO Persoon (Voornaam, Tussenvoegsel, Achternaam, Emailadres, Wachtwoord, Geboortedatum, Mobielnummer,  IBAN)"
                      + " VALUES('" + vnaam + "','" + tussenv + "','" + anaam + "','" + email + "','" + wachtw + "','" + gebdatum + "','" + mobiel + "','" + iban + "')";              

                //Query uitvoeren
                st.executeUpdate(query);

            }catch(Exception ea){
                System.out.println("Query schrijf ERROR: " + ea);

            }

     }     
    
    
    public void updateData(String field, String content){
    //Query voor updaten!!!!----->
        try{
            
            //Select collum
            String collum = "YOLO";
            
            //Update query
            query = "UPDATE test SET " + collum + "='" + content + "' WHERE " + collum + "'" + field + "'";              
                        
            //Query uitvoeren
            st.executeUpdate(query);
            
        }catch(Exception ea){
            System.out.println("Query update ERROR: " + ea);
            
        }
        
    }
    
}
    

