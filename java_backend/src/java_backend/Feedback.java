package java_backend;

/**
 *
 * @author Laurens
 */
public class Feedback {
    private int Waardering, FeedbackID, Ontvangststatus, PakketID;
    private String Omschrijving;
    
    public Feedback() {
        this.Waardering = 0;
        this.Omschrijving = "";
        this.Ontvangststatus = 0;
        this.FeedbackID = 0;
        this.PakketID = 0;
    }
    
    public Feedback(int w, String om, int o, int f, int p) {
        Waardering = w;
        Omschrijving = om;
        Ontvangststatus = o;
        this.FeedbackID = f;
        this.PakketID = p;
    }

    /**
     * @return the Waardering
     */
    public int getWaardering() {
        return Waardering;
    }

    /**
     * @return the Omschrijving
     */
    public String getOmschrijving() {
        return Omschrijving;
    }

    /**
     * @return the Ontvangststatus
     */
    public int getOntvangststatus() {
        return Ontvangststatus;
    }

    /**
     * @param Waardering the Waardering to set
     */
    public void setWaardering(int Waardering) {
        this.Waardering = Waardering;
    }

    /**
     * @return the FeedbackID
     */
    public int getFeedbackID() {
        return FeedbackID;
    }

    /**
     * @param FeedbackID the FeedbackID to set
     */
    public void setFeedbackID(int FeedbackID) {
        this.FeedbackID = FeedbackID;
    }

    /**
     * @param Ontvangststatus the Ontvangststatus to set
     */
    public void setOntvangststatus(int Ontvangststatus) {
        this.Ontvangststatus = Ontvangststatus;
    }

    /**
     * @return the PakketID
     */
    public int getPakketID() {
        return PakketID;
    }

    /**
     * @param PakketID the PakketID to set
     */
    public void setPakketID(int PakketID) {
        this.PakketID = PakketID;
    }

    /**
     * @param Omschrijving the Omschrijving to set
     */
    public void setOmschrijving(String Omschrijving) {
        this.Omschrijving = Omschrijving;
    }
}
