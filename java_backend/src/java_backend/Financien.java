package java_backend;

public class Financien {
    
    public Financien() {
        
    }
    /**
     * @author Daniel van der Berg (bitch!)
     * Pas de onderstaande gegevens aan om koeriers te wijzigen.
     */
    
    //Theo Snel en zonen gegevens
    private final int TheoSnelenZonen = 1;
    private double TS_Perkm = 0.20;
    private double TS_Starttarief = 5.00;
    private int TS_Startm = 25000;
    //------------------------->
    
    //Sjors fiets koeriers gegevens
    private final int SjorsFietsKoeriers = 2;
    private double SF_Perkm = 0.25;
    private double SF_Starttarief = 0.00;
    private int SF_Startm = 0;
    //------------------------->
    
    //Jansen transport diensten
    private final int JansenTransport = 3;
    private double JT_Perkm = 0.00;
    private double JT_Starttarief = 20.00;
    private int JT_Startm = 400000;
    //------------------------->  
    
    public double[] BerekenKoerier(int m) {   
        
        //variabelen waarin de waardes teruggegeven worden.
        double ret = 0;
        double type = 0;
       
        //tarief per koerier berekenen inclusief start kosten
        double TS = m > TS_Startm ? TS_Perkm * ((m - TS_Startm) / 1000) + TS_Starttarief : TS_Starttarief;        
        double SF = m > SF_Startm ? SF_Perkm * ((m - SF_Startm) / 1000) + SF_Starttarief : SF_Starttarief;
        double JT = m > JT_Startm ? JT_Perkm * ((m - JT_Startm) / 1000) + JT_Starttarief : JT_Starttarief;
        
        //controleren welke koerier het goedkoopst is
        if (m < 1000) {
            ret = 0.25;
            type = SjorsFietsKoeriers;
        } else {
            if (TS <= SF && TS <= JT) {
                ret = TS;
                type = TheoSnelenZonen;
            }if (SF <= TS && SF <= JT) {
                ret = SF;
                type = SjorsFietsKoeriers;
            }if (JT <= TS && JT <= SF) {
                ret = JT;  
                type = JansenTransport;
            }              
        }
        
        
        //na de vorige loop is in de return array gevuld in ret zit de prijs van de goedkoopste koerier in type welke koerier het goedkoopst is.
        double[] result = {ret,type};
        
        //array returnen.
        return result;
 
    }
}
