/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package java_backend;

/**
 *
 * @author Jelle
 */
public class TZTPoint extends Locatie{
    private boolean TZTPoint;
    
    public TZTPoint() {
        super();
        this.TZTPoint = true;
    }
    public TZTPoint(String po, String pl, String sr, int h, String to, String tl, boolean p) {
        super(po, pl, sr, h, to, tl);
        this.TZTPoint = p;
    }

    /**
     * @return the TZTPoint
     */
    public boolean isTZTPoint() {
        return TZTPoint;
    }

    /**
     * @param TZTPoint the TZTPoint to set
     */
    public void setTZTPoint(boolean TZTPoint) {
        this.TZTPoint = TZTPoint;
    }
}
