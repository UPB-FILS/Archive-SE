/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td_6;

/**
 *
 * @author Chitu Sorina,Bathahi Ilyass
 */
public class PageTableEntry {

  private int frameNumber;
	private boolean valid;

	public PageTableEntry() {
		// initially we do not have a valid mapping
		valid = false;
		frameNumber = -1;
	}

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