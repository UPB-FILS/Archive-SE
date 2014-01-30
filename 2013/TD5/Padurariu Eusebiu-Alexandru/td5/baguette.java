package Td5;

/**
 * 
 * @author Padurariu Eusebiu-Alexandru
 */


/**
 * 
 * @author Razvan & Miruna
 * 
 */

public class baguette {
	int etat;
	static final int ETAT_LIBRE = 0;
	static final int ETAT_PRIS = 1;

	public baguette() {
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