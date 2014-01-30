// File Name : Synch.java

/**
 * 
 * @author Razvan & Miruna
 *
 */

public class Synch {
	public static void main(String args[]) {
		Consumer.OpenFile();
		
		Producer target = new Producer();
		Tampon ob1 = new Tampon(target, 0);
	
		try {
			ob1.t.join();
			//ob2.t.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		
		Consumer.CloseFile();
		
	}
}
