/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package td5;

/**
 * 
 * @author Padurariu Eusebiu-Alexandru
 */
class philosophe extends Thread {
	baguette droit;
	baguette gauche;

	public philosophe(baguette droit,baguette gauche,String name) {
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