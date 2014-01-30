import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The program uses: <b>java Bonjour &lt; name_of_the_person&gt;</b></br> The
 * program prints out: <b>"Bonjour &lt; name_of_the_person&gt; !"</b>
 * 
 * @author Razvan
 * @version Juno Service Release 1, 25.February.13
 */

/*
 * Programul trebuie sa afiseze Bonjour + numele persoanei dintr-o lista
 * folosind Thread
 */
public class Bonjour {
	/**
	 * args contains the name of the person
	 * 
	 **/
	private Random random = new Random();

	public static void main(String[] args) {

		// Writing stuff from the keyboard with Scanner
		System.out.println("Enter the name of the person: ");
		Scanner sc = new Scanner(System.in);

		// We store the String that we are entering from keyboard in the
		// variable name

		final String name = sc.nextLine();
		sc.close();

		// We store the variable name in an ArrayList
		final ArrayList<String> al = new ArrayList<String>();
		al.add(name);

		if (name.isEmpty()) {
			System.err.println("Not good!");
			System.exit(0);
		}
		// We declare a Thread in which we run our code
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				do {
					System.out.println("Bonjour" + " " + al.get(0) + "!");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// While we have names in our list we say "Bonjour" + name;
				} while (al.size() != 0);

			}

		});

		t1.start();
		

	}
}
