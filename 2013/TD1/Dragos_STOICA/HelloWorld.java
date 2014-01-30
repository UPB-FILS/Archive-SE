import java.io.*;

/**
 * 
 * TP 1 - Premier programme en Java<br/>
 * Le programme s'utilise : <b>java HelloWorld &lt;nom_de_la_personne&gt;</b><br/>
 * Le programme affiche : <b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>
 * @author Dragos STOICA
 * @version 0.1, 18.Feb.2013 
 */
public class HelloWorld
{
	/**
	 * @param args contient le nom de la personne
	 */
	public static void main(String[] args) {
		
		if (args.length != 1) {
			System.err.println("Utilisation: java HelloWorld <prenom>");
			System.exit(0);
		}

		// args[0] est le 1er parametre
		System.out.println("Bonjour " + args[0] + " !");
	}
}
