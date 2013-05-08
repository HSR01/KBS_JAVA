package java_backend;

/**
 *
 * @author Jelle
 */
public class Financien {
    
    public Financien(){
        
    }
    /**
     * @author Jelle Smeets
     * @param km
     * @return 
     */
    public Koerier BerekenKoerier(int km){
        
        // nog even koeriers objecten vanuit de database inladen om te returnen.
        if(km <= 19){
            //fietskoerier
            System.out.println("fietskoerier");
        }else if(km  == 20){
            //fietskoerier of theo en zonen (random)
            System.out.println("Random Theo en zonen of Fietskoerier");
        }else if (km >20){
            //theo en zonen
            System.out.println("Theo en zonen");
        }
        return null;
    }
}
