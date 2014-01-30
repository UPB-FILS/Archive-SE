/**
 * Frame.java
 *
 * A class which represents a physical page frame.
 *
 * @author Alexandru Bogdan
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 *
 * adapted by myf1: at construction every frame is filled 
 *                  with values [0..PAGE_SIZE-1] 
 */
//package opsys1.vmm;


public class Frame 
{
  public  static final int PAGE_SIZE = 256;

	private byte[] frameValue;

	public Frame() {
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
