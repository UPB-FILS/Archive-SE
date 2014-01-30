/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td6;

/**
 *
 * @author madalina petrisor& dumitrescu ioana
 */
public class Frame
{
public static final int PAGE_SIZE = 256;

private byte[] frameValue;

public Frame() {
frameValue = new byte[PAGE_SIZE];
for (int i = 0; i < PAGE_SIZE; i++)
frameValue[i] = (byte) i;
}

public void setFrame(byte[] bytes) {//se asigura ca este folosit System.arraycopy()

System.arraycopy(bytes, 0, frameValue, 0, PAGE_SIZE); //folosirea lui System.arraycopy
}

public byte readWord(int offset) {
return frameValue[offset]; //returnarea valorii framaValue
}
}