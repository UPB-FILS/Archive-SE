package V1;
/**
 * 
 * @author Razvan & Miruna
 *
 */

class Philosophe extends Thread {
	Baguette droit;
	Baguette gauche;

	public Philosophe(Baguette droit, Baguette gauche, String name) {
		this.droit = droit;
		this.gauche = gauche;
		setName(name);
	}

	public void run() {
		while (true)
			try {
				//Implementez cu semafor (acquire si release in loc de "this")
				synchronized (this) {
					droit.prend();
					gauche.prend();
				}

				System.out.println(getName() + "eating"+ System.currentTimeMillis());
				this.sleep(5000);
				droit.libere();
				gauche.libere();
				System.out.println(getName() + "thinking"+ System.currentTimeMillis());
				this.sleep(3000);
			} catch (InterruptedException e) {
				e.getStackTrace();
			}

	}
}
