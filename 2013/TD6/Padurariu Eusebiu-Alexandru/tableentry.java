/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td6;

/**
 * @author Padurariu Eusebiu-Alexandru
 */



public class tableentry
{
	private int pageNumber;		// the virtual page number
	private int frameNumber;	// the physical frame number
	private boolean isValid;	// flag that indicates if the mapping
					// is valid.

	public tableentry() {
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
		if (pageNumber == this.pageNumber)
			return true;
		else
			return false;
	}

	public int getFrameNumber() {
		return frameNumber;
	}
}