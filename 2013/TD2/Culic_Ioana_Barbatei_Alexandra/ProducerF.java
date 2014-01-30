import java.util.ArrayList;
import java.util.logging.Logger;

public class ProducerF extends Thread
{
	private ArrayList<String> nameList;
	private ArrayList<String> bufferOut;
	private Notifier n;
	public ProducerF(ArrayList<String> nameList, ArrayList<String> bufferOut, Notifier n)
	{
		this.nameList = nameList;
		this.bufferOut = bufferOut;
		this.n = n;
	}
	/** Functia care va rula thread-ul.
	 */
	public void run()
	{
		Logger log = Logger.getLogger("ProducerF");
		synchronized(n)
		{
			n.isReady = false;
		}
		for (int i=0; i<nameList.size(); i++)
		{
			String name = nameList.get(i);
			if(name.startsWith("Mme."))
			{
				bufferOut.add(name);
				log.info(name+" added");
			}
		}
		synchronized(n)
		{
			n.isReady = true;
			n.notifyAll();
		}
	}
}
