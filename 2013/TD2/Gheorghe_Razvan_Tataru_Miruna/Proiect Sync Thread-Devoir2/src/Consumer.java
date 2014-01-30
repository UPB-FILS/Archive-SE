import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author Razvan & Miruna
 *
 */

public class Consumer {
	Thread t1 = new Thread();
	Thread t2 = new Thread();

	public static void OpenFile() {

		File file = new File("ListeNoms.txt");

		BufferedReader br = null;

		try {
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);

			String line;
			String salutation;

			while ((line = br.readLine()) != null) {
				System.out.println(line);
				StringTokenizer st = new StringTokenizer(line);
				salutation = st.nextToken();

				if (salutation.compareTo("Mme.") == 0) {
					System.out.println("Bonjour Madame " + st.nextToken());
				}
				if (salutation.compareTo("M.") == 0) {
					System.out.println("Bonjour Monsieur " + st.nextToken());
				}
			}

			controlThreads();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.toString());
		} catch (IOException e) {
			System.out.println("Unable to read: " + file.toString());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("Unable to close file: " + file.toString());
			} catch (NullPointerException ex) {
				// File was probably never opened!
			}
		}

	}

	public static void CloseFile() {

	}

	public static void controlThreads() {
		ThreadControlType doamna = new ThreadControlType("doamna");
		ThreadControlType domn = new ThreadControlType("domn");
		doamna.start();
		domn.start();
	}

}
