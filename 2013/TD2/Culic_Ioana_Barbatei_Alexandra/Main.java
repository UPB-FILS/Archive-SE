/** Description of Main
 *
 * @author Culic Ioana
 * @author Barbatei Alexandra
 * @version 1 Build 1-OpenJDK-1.6.0_27 Mar 16, 2013.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("ListePersonnes.txt"));
		String name;
		ArrayList<String> nameList = new ArrayList<String>();
		while((name = br.readLine()) != null)
		{
			nameList.add(name);
		}
		ArrayList<String> buffer = new ArrayList<String>();
		Notifier n = new Notifier();
		ProducerF fThread = new ProducerF(nameList, buffer, n);
		ProducerM mThread = new ProducerM(nameList, buffer, n);
		Consumer cThread = new Consumer(buffer, "Bonjour.txt");
		
		fThread.run();
		mThread.run();
		cThread.run();
		
	}

}
