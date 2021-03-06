package Financien;

import Database.DbConnect;
import java.util.ArrayList;
import java_backend.Koerier;

public class Financien {
    
    public Financien() {
        
    }
    /**
     * @author Daniel & Leon
     * Pas de onderstaande gegevens aan om koeriers te wijzigen.
     */
    
   
    private int KoerierID;
    private double PerKm;
    private double StartTarief;
    private int StartKm;
    private int Actief;
    private double som[][];
  
    /**
     * 
     * @param meter
     * @return goedkoopste koerier
     */
    public Koerier BerekenGoedkoopsteKoerier(int meter) {        
        DbConnect dbc = new DbConnect();
        
        //haal de arraylist op uit Dbconnect klasse
        final ArrayList<Koerier> koeriers = dbc.GetAllActiveKoeriers();
 
        //loop er door heen en bereken de prijs over de afgelopen afstand
        for (Koerier k : koeriers) {
            k.RitPrijs = meter > k.StartMeters ? k.PrijsPerKm * ((meter - k.StartMeters) / 1000) + k.StartTarief : k.StartTarief;
        }
       
        // Goedkoopste koerier teruggeven.
        return getCheapestKoerier(koeriers);
    }
    
    //Haal de goedkoopste koerier uit de koerier array en return deze
    private Koerier getCheapestKoerier(ArrayList<Koerier> koeriers) {
        Koerier goedkoopste = koeriers.get(0);
        for (int i = 1; i < koeriers.size(); i++){
            if (koeriers.get(i).RitPrijs < goedkoopste.RitPrijs) {
                goedkoopste = koeriers.get(i);
            }
        }
        return goedkoopste;
    }
    
    /**
     * Haal de kostprijs op.
     * @param verzendingID
     * @return 
     */
    public int getKostprijs(int verzendingID){
        DbConnect dbc = new DbConnect();
        //haal kostprijs op.
        int kostprijs = dbc.getKostPrijsById(verzendingID);
        //als kostprijs 0 is bereken deze dan.
        if(kostprijs == 0){
            return dbc.calculateKostprijsByVerzendingID(verzendingID);
        }else{
            //er is al een kostprijs return deze.
            return kostprijs;
        }

    }
    
    public double getKoerierskosten(int meters, int KoerierID){
        DbConnect dbc = new DbConnect();
        double[] kosten = dbc.getKostenberekeningKoerier(KoerierID);
        if(kosten != null){
            //als meters kleiner is dan startmeters
            if(meters < kosten[2]){
                //keer starttarief uit.
                return kosten[1];
            }else{
                //anders starttarief + aantal km * Km
                return kosten[1] + ((meters / 1000) * kosten[0]);
            }
        }else{
            return 0; // foutmelding berekening koerier niet gelukt.
        }
    }
    
    
}
