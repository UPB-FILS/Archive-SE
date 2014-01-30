
package td5;

/**
 *
 * @author Petrisor Madalina, Dumitrescu Ioana
 */
class Diner {
    public static void main(String [] argv) {
	Baguette b1 = new Baguette();
	Baguette b2 = new Baguette();
	Baguette b3 = new Baguette();
	Baguette b4 = new Baguette();
	Baguette b5 = new Baguette();
	Philosophe p1 = new Philosophe(b1,b2,"P1");
	Philosophe p2 = new Philosophe(b2,b3,"P2");
	Philosophe p3 = new Philosophe(b3,b4,"P3");
	Philosophe p4 = new Philosophe(b4,b5,"P4");
	Philosophe p5 = new Philosophe(b5,b1,"P5");
	p1.start();
	p2.start();
	p3.start();
	p4.start();
	p5.start();
    }
}