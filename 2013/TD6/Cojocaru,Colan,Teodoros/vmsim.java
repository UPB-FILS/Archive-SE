/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td6;

/**
 * @author Cojocaru Laurentiu
 * Colan Vlad
 * Teodoros Silviu
 */

import java.io.*;


public class vmsim
{
	// constants
	// public  static final int PAGE_SIZE defined in vmm.Frame
	private static final int PAGE_TABLE_ENTRIES = 256;
	private static final int NUMBER_OF_FRAMES = 256;
	private static final int PHYSICAL_MEMORY_SIZE = frame.PAGE_SIZE * NUMBER_OF_FRAMES;
	private static final int TLB_SIZE = 16;
	private static final int PAGE_MASK = 0xff00;
	private static final int PAGE_SHIFT = 8;
	private static final int OFFSET_MASK = 0xff;

	private BufferedReader r = null;

	private int nextFrameNumber;		/* the next available frame number */
	private int nextTLBEntry;		/* the next available entry in the TLB */

	private pagetableentry[] pageTable;	/* the page table */
	private frame[] physicalMemory;		/* physical memory (organized in frames) */
	private tableentry[] TLB;			/* the TLB */

	private int pageFaults;			/* the number of page faults */
	private int TLBHits;			/* the number of TLB hits */
	private int numberOfAddresses;		/* the number of addresses that were translated */

	/** 
	 * Constructor.
	 *
	 * Creates and intiailizes the various data structures including:
	 *
	 * (1) Page table, only the lower PAGE_TABLE_ENTRIES/2 pages are valid
	 * (2) TLB
	 * (3) Physical memory
	 */
	public vmsim() {

		pageTable = new pagetableentry[PAGE_TABLE_ENTRIES];
		for (int i = 0; i < PAGE_TABLE_ENTRIES; i++)
			pageTable[i] = new pagetableentry();

		TLB = new tableentry[TLB_SIZE];
		for (int i = 0; i < TLB_SIZE; i++)
			TLB[i] = new tableentry();

		physicalMemory = new frame[NUMBER_OF_FRAMES];
		for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
			physicalMemory[i] = new frame();

		}
		nextFrameNumber = nextTLBEntry = 0;
		/*
		 *initialize the statistics counters
		 */
		pageFaults = 0;
		TLBHits = 0;
		/* 
		 * only pages in [0..PAGE_TABLE_ENTRIES/2) are valid
		 */
		for (int i = 0; i < PAGE_TABLE_ENTRIES/2; i++)
			pageTable[i].setMapping(PAGE_TABLE_ENTRIES - i -1);
	}

	/**
	 * Extract the page number given the virtual address.
	 */
	public int getPageNumber(int virtualAddress) {
		return  (virtualAddress & PAGE_MASK) >> PAGE_SHIFT;
	}

	/**
	 * Extract the offset given the virtual address.
	 */
	public int getOffset(int virtualAddress) {
		return (virtualAddress & OFFSET_MASK);
	}

	/**
	 * Check the TLB for a mapping of
	 * page number to physical frame
	 *
	 * @return -1 if no mapping or the frame number >= 0
	 */
	public int checkTLB(int pageNumber) {
		int frameNumber = -1;
		/**
		 * A "real" TLB would use associative memory
		 * where we could check all values in the
		 * TLB memory at the same time. We have 
		 * in fact do a linear search of our TLB
	 	 */
		for (int i = 0; i < TLB_SIZE; i++) {
			if (TLB[i].checkPageNumber(pageNumber)) {
				frameNumber = TLB[i].getFrameNumber();
				TLBHits++;
				break;
			}
		}
		return frameNumber;
	}

	/**
	 * Maps a page number to its frame number in the TLB.
	 */
	public void setTLBMapping(int pageNumber, int frameNumber) {
		// establish the mapping
		TLB[nextTLBEntry].setMapping(pageNumber, frameNumber);

		/**
		 * Update the next TLB entry.
		 *
		 * This uses a very simple FIFO approach for
		 * managing entries in the TLB.
		 */
		nextTLBEntry = (nextTLBEntry + 1) % TLB_SIZE;
	}


	/**
	 * Address Translation: Determine the physical address given a virtual address
	 */
	public int getPhysicalAddress(int virtualAddress) throws java.io.IOException {
		int  pageNumber;		/* virtual page number */
		int  offset;			/* offset in page/frame */
		int  frameNumber;		/* physical frame number */
		// determine the page number
		pageNumber = getPageNumber(virtualAddress);
		//System.out.println("Page number = " + pageNumber);

		// determine the offset
		offset = getOffset(virtualAddress);
		//System.out.println("offset = " + offset);

		/**
		 * First check the TLB. We only have to run the 
		 * algorithm to extract the frame in the case of
		 * a TLB miss. Where we have a TLB hit, we can
		 * directly obtain the associated frame from the
		 * given page number.
		 */
		if ( (frameNumber = checkTLB(pageNumber)) == -1 ) {  /** TLB Miss **/
			// Check the page table
			if (pageTable[pageNumber].getValidBit() == true) {
				/** Page Table Hit **/
				frameNumber = pageTable[pageNumber].getFrameNumber();
			} else { 	/** Page Fault **/
				pageFaults++;
				// get a free frame
				// FIXME: done in VM exercise
				if(pageTable.length<PAGE_TABLE_ENTRIES)
				{
					pageTable[pageTable.length-1].setMapping(frameNumber);
					frameNumber = pageTable[pageNumber].getFrameNumber();
				}
				else 
					{
					changement(frameNumber);
					frameNumber = pageTable[pageNumber].getFrameNumber();
					}



			}
			// lastly, update the TLB
			setTLBMapping(pageNumber, frameNumber);	
		}
		// construct the physical address and return it
		if (frameNumber == -1)
			return (-1);
		else
			return ((frameNumber << PAGE_SHIFT) + offset);
	}

	/**
	 * Returns the signed byte value at the specified physical address.
	 */
	public byte getValue(int physicalAddress) throws java.io.IOException {
		/**
		 * Essentially, the code below performs the following:
		 * return physicalMemory[frameNumber].readWord(offset);
		 */
		return physicalMemory[((physicalAddress & PAGE_MASK) >> PAGE_SHIFT)].readWord(physicalAddress & OFFSET_MASK);
	}

	/** 
	 * Generate statistics.
	 */
	public void generateStatistics() {
		System.out.println("Number of Translated Addresses = " + numberOfAddresses);
		System.out.println("Page Faults = " + pageFaults);
		System.out.println("Page Fault Rate = " + ( (float) pageFaults) / numberOfAddresses);
		System.out.println("TLB Hits = " + TLBHits);
		System.out.println("TLB Hit Rate = " + ( (float) TLBHits) / numberOfAddresses);
	}

	/**
	 * The primary method that runs the translation of logical to physical addresses.
	 */
	public void runTranslation(String inputFile) throws java.io.IOException {
		int virtualAddress;		/* the virtual address being translated */
		int physicalAddress;		/* the physical address */
		byte value;			/* the value stored at the physical address */

		try {
			r = new BufferedReader(new FileReader(inputFile));
 
			String stringValue;

			while ( (stringValue = r.readLine()) != null) {
				// read in the virtual address and translate it
				virtualAddress = Integer.parseInt(stringValue);
				physicalAddress = getPhysicalAddress(virtualAddress);
				numberOfAddresses++;
				// get the value stored at the physical address	
				value = getValue(physicalAddress);
				System.out.println("Virtual address: " + virtualAddress + " Physical address: " + physicalAddress + " Value: " + value);
			}
			generateStatistics();
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			r.close();
		}
	}

	public static void main(String[] args) throws java.io.IOException {
		if (args.length != 1) {
			System.err.println("Usage: java VM <input file>");
			System.exit(-1);
		}
		else {
			vmsim vm = new vmsim();
			vm.runTranslation(args[0]);
		}
	}
	public void changement(Integer frameNumber)
	{

		for(int i=pageTable.length-2;i>=0;i--)
		{

			pageTable[i+1]=pageTable[i];
			pageTable[0].setMapping(frameNumber);

		}

	}
}