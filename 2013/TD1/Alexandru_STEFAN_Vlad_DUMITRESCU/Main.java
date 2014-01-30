
/**
* Devoir 1<br/>
* Le programme s’utilise : <b>java Devoi01 &lt;nom_de_la_personne&gt;</b><br/>
* Le programme affiche : <b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>
* @author Stefan ALEXANDRU, Vlad DUMITRESCU
**/



public class Main {
static String global_args[];
static int i;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	global_args=args;
	if(global_args.length==0) 
		{System.out.println("Acest program se apeleaza cu minim un parametru");
	     System.exit(0);}
	
	for(i=0;i<global_args.length;i++)
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Bonjour, "+global_args[i]+" !");
				
			}
		}).run();

	}

}
