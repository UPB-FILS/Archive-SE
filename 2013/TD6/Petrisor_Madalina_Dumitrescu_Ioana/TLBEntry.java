/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td6;

/**
 *
 * @author madalina
 */

public class TLBEntry
{//declararea variabilelor
private int pageNumber;	// numarul virtual al paginii
private int frameNumber;	//numarul fizic al paginii
private boolean isValid;	// flag that indicates if the mapping

//scrierea getter si setter
public TLBEntry() {
pageNumber = -1;
frameNumber = -1;
isValid = false;
}

public boolean setMapping(int pageNumber, int frameNumber) {
this.pageNumber = pageNumber;
this.frameNumber = frameNumber;
isValid = true;

return isValid;
}	

public boolean checkPageNumber(int pageNumber) {
if (pageNumber == this.pageNumber) //verificam numarul paginii
return true;
else
return false;
}

public int getFrameNumber() {
return frameNumber;
}
}