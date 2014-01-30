package td6;

/**
 * @author Padurariu Eusebiu-Alexandru
 */



public class frame 
{
	public  static final int PAGE_SIZE = 256;

	private byte[] frameValue;

	public frame() {
		frameValue = new byte[PAGE_SIZE];
		for (int i = 0; i < PAGE_SIZE; i++)
		    frameValue[i] = (byte) i;
	}

	public void setFrame(byte[] bytes) {
		/**
		 * Make sure we use System.arraycopy() as we don't
		 * want the frame to be a unique refernece.
		 */
		System.arraycopy(bytes, 0, frameValue, 0, PAGE_SIZE);

	}

	public byte readWord(int offset) {
		return frameValue[offset];
	}
}
