/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

/**
 *
 * @author rufus
 */
public class MRresult {
    
    private final String state;
    private final int val;

    public MRresult(String state, int val) {
        this.state = state;
        this.val = val;
    }
    
    public String getState() {
        return state;
    }

    public int getVal() {
        return val;
    }
}
