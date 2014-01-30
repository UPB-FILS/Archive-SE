import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Razvan & Miruna
 *
 */

public class ThreadControlType extends Thread {
	String tipPersoana;

	public synchronized void run() {
		try {
			FileWriter fw = new FileWriter("Bonjour.txt");
			PrintWriter bw = new PrintWriter(fw);
			bw.println("Ceva");
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ThreadControlType(String tipPresoana) {
		this.tipPersoana = tipPresoana;
	}

}
