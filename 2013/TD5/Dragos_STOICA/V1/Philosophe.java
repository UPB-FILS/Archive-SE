package V1;

class Philosophe extends Thread {
    Baguette droit;
    Baguette gauche;

    public Philosophe(Baguette droit, Baguette gauche,String name) {
	this.droit=droit;
	this.gauche=gauche;
	setName(name);
    }
    public void run() {
	// ...
    }
}
