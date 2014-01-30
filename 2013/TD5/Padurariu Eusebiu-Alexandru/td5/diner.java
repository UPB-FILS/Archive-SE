/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td5;

public class diner {
	public static void main(String[] argv) {
		baguette b1 = new baguette();
		baguette b2 = new baguette();
		baguette b3 = new baguette();
		baguette b4 = new baguette();
		baguette b5 = new baguette();
		philosophe p1 = new philosophe(b1, b2, "P1");
		philosophe p2 = new philosophe(b2, b3, "P2");
		philosophe p3 = new philosophe(b3, b4, "P3");
		philosophe p4 = new philosophe(b4, b5, "P4");
		philosophe p5 = new philosophe(b5, b1, "P5");
		p1.start();
		p2.start();
		p3.start();
		p4.start();
		p5.start();
	}
}