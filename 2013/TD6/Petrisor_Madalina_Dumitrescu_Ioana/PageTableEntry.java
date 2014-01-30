/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td6;

/**
 *
 * @author madalina
 */
public class PageTableEntry
{
private int frameNumber;
private boolean valid;

public PageTableEntry() {
// initializarea variabilelor
valid = false;
frameNumber = -1;
}
//getter si setter
public boolean getValidBit() {
return valid;
}

public int getFrameNumber() {
return frameNumber;
}

public void setMapping(int frameNumber) {
this.frameNumber = frameNumber;

valid = true;
}
}