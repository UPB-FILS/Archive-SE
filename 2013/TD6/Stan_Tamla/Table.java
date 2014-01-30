//auteurs Tamla Tekam Merlin, Stan Elena Madalina

public class Table
{
	private int pageNumber;		// the virtual page number
	private int frameNumber;	// the physical frame number
	private boolean isValid;	// flag that indicates if the mapping
					// is valid.

	public Table() {
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

