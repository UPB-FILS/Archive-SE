// File Name : Caller.java

/**
 * 
 * @author Razvan & Miruna
 *
 */

class Tampon implements Runnable {
	int tipSalut;
	Producer target;
	Thread t;

	public Tampon(Producer targ, int tipSalut) {
		target = targ;
		this.tipSalut = tipSalut;
		t = new Thread(this);
		t.start();
	}

	// synchronize calls to call()
	public void run() {
		synchronized (target) { // synchronized block
			target.call(tipSalut);
		}
	}
}