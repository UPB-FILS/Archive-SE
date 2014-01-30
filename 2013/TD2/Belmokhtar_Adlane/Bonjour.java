
public class Bonjour {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		ThreadMme t=new ThreadMme();
		ThreadMr m=new ThreadMr();
		Thread tr=new Thread(t);
		Thread tr2=new Thread(m);
		tr.start();
		tr.join();
		tr2.start();

	}

}
