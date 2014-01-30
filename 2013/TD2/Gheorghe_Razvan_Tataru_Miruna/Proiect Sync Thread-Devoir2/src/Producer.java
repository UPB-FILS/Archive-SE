import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 
 * @author Razvan & Miruna
 *
 */

class Producer {

	void call(int tipSalut) {

		// deschid fisierul cu nume;
		// pentru fiecare nume verific daca este doamana sau domn
		// daca este domn si tipSalut este 1 atunci scriun in fisier salutul
		// folosind comanda de mai jos\
		// daca este doamna si tipSalut este 0 atunci scriun in fisier salutul
		// folosind comanda de mai jos
		// inchid fisierul

		Consumer.OpenFile();
		Consumer.CloseFile();
	}
	
	public void ReadFromFile(){
		
	}
}
