
/**
* TP 2 - programme en Java<br/>
* Le programme s’utilise : <b> le program lit des infos d'un fishier text et ecrit "Bonjour"+"Mme" ou "Mr",avec priorite pour "Mme" dans un autre document text</b><br/>
* Le programme affiche : <b>"Bonjour &lt;Mme ;nom_de_la_personne&gt; !"</b>
* @author Romulus-Marius Cimpeanu  et Adlane Belmokhtar
* @version 0.1, 09.04.2013
*/


public class Bonjour {

	/**
	 * @param args 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		/**
		 * declaration et initialisation des objets des deux classes definies, init. des thread et leur demarrage
		 */
		ThreadMme t=new ThreadMme();
		ThreadMr m=new ThreadMr();
		Thread tr=new Thread(t);
		Thread tr2=new Thread(m);
		tr.start();
		tr.join();
		tr2.start();

	}

}
