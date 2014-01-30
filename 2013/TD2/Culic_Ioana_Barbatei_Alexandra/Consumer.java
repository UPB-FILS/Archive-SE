import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Consumer extends Thread
{
	private ArrayList<String> buffer;
	private String fileName;
	
	public Consumer(ArrayList<String> buffer, String fileName)
	{
		this.buffer  = buffer;
		this.fileName = fileName;
	}
	/** Functia prin care se ruleaza thread-ul.
	 */
	public void run()
	{
		//Fisierul in care vom scrie.
		File f = new File(fileName);
		PrintWriter writer;
		Logger log = Logger.getLogger("Consumer");
		try 
		{
			writer = new PrintWriter(f);
			//Se parcurge buffer-ul.
			for (int i = 0; i<buffer.size(); i++)
			{
				String line  = buffer.get(i);
				writer.print("Bonjour "+line+"\r\n");
				log.info(line+" wrote");
			}
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
