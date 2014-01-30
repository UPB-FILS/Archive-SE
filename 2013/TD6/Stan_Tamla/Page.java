Page.java
public class Page {
	private int frameNumber;
	private boolean valid;

	public PageE() {
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
