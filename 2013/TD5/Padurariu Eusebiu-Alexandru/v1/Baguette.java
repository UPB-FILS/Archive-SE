package V1;

/**
 * 
 * @author Padurariu Eusebiu-Alexandru
 */

public class Baguette {
	int etat;
	static final int ETAT_LIBRE = 0;
	static final int ETAT_PRIS = 1;

	public Baguette() {
	}

	public synchronized void prend() throws InterruptedException {
		while (etat == ETAT_PRIS) {
			wait();
		}
		etat = ETAT_PRIS;
	}

	public synchronized void libere() throws InterruptedException {
		etat = ETAT_LIBRE;
		notifyAll();
	}
}