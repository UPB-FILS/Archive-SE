import java.util.ArrayList;
import java.util.logging.Logger;

public class ProducerM extends Thread 
{
	private ArrayList<String> nameList;
	private ArrayList<String> bufferOut;
	private Notifier n;
	public ProducerM(ArrayList<String> nameList, ArrayList<String> bufferOut, Notifier n)
	{
		this.nameList = nameList;
		this.bufferOut = bufferOut;
		this.n = n;
	}
	public void run()
	{
		Logger log = Logger.getLogger("ProducerM");
		synchronized(n)
		{
			while(!n.isReady)
			{
				try {
					n.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i=0; i<nameList.size(); i++)
			{
				String name = nameList.get(i);
				if(name.startsWith("M."))
				{
					bufferOut.add(name);
					log.info(name+" added");
				}
			}
		}
	}
}
