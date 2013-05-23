package java_backend;

import java.util.Date;

/**
 *
 * @author Jelle
 */
public class Beschikbaar {
    private boolean Beschikbaar;
    private Date Begin;
    private Date Eind;
    private int Weekend;
    
    public Beschikbaar() {
        this.Begin = new Date();
        this.Beschikbaar = false;
        this.Eind = new Date();
        this.Weekend = 0;
    }
    public Beschikbaar(boolean be, Date b, Date e, int w) {
        
    }

    /**
     * @return the Beschikbaar
     */
    public boolean isBeschikbaar() {
        return Beschikbaar;
    }

    /**
     * @param Beschikbaar the Beschikbaar to set
     */
    public void setBeschikbaar(boolean Beschikbaar) {
        this.Beschikbaar = Beschikbaar;
    }

    /**
     * @return the Begin
     */
    public Date getBegin() {
        return Begin;
    }

    /**
     * @param Begin the Begin to set
     */
    public void setBegin(Date Begin) {
        this.Begin = Begin;
    }

    /**
     * @return the Eind
     */
    public Date getEind() {
        return Eind;
    }

    /**
     * @param Eind the Eind to set
     */
    public void setEind(Date Eind) {
        this.Eind = Eind;
    }

    /**
     * @return the Weekend
     */
    public int getWeekend() {
        return Weekend;
    }

    /**
     * @param Weekend the Weekend to set
     */
    public void setWeekend(int Weekend) {
        this.Weekend = Weekend;
    }
}
