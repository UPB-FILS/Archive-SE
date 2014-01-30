package td6;

/**
 * @author Padurariu Eusebiu-Alexandru
 */



public class pagetableentry 
{
	private int frameNumber;
	private boolean valid;

	public pagetableentry() 
        {
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