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
        //Probeer de volgende query uit te voeren
        try{
            query = "select * from test";
            rs = st.executeQuery(query); 
            
            //Loop door de query data heen
            while(rs.next()){
                String content = rs.getString("YOLO");
                System.out.println(content);
            }
        //Afvangen fouten voor getdata    
        }catch(Exception ea){
            System.out.println("Query ERROR: " + ea);
        }
        
    }
    

    
}
    

