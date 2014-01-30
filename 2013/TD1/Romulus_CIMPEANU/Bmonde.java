import java.util.ArrayList;


public class Bmonde {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		args=new String[]{"Vasile","Ion","Victor",""};
		ArrayList<String> a=new ArrayList<String>();

		for(int i=0;i<args.length;i++)
			a.add(args[i]);
		
	
	Bonjour b=new Bonjour(a);
	Thread t=new Thread(b);
	t.start();
	

	}

}