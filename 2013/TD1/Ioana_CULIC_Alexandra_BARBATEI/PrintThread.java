public class PrintThread extends Thread
{
	String name;
	/** Constructorul este utilizat pentru ca thread-ul sa primeasca argumente
	 * 
	 * @param s		Numele persoanelor
	 */
	PrintThread(String s)
	{
		this.name = s;
	}
	public void run()
	{
		System.out.println("Bonjour,"+name);
	}
}
