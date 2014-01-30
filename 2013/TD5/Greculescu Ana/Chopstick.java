/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd6;

/**
 *
 * @author ANA
 */
public class Chopstick {
    private boolean _isUsed;

    /*
    * @return the current state of the chopstick
    */
    public boolean isUsed(){
        return _isUsed; 
    }

    /*
    * @param usedFlag the new state of the chopstick
    */
    public void setUsed(boolean usedFlag){
        _isUsed = usedFlag;
    }
}
    

