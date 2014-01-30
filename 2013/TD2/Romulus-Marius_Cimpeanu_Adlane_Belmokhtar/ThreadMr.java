import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
* TP 2 - programme en Java<br/>
* Le programme s’utilise : <b> la classe qui est un sous-classe de Thread et definit l'action de lire et separer les "Mr" du fichier text</b><br/>
* Le programme affiche : <b>"Bonjour &lt;Mme ;nom_de_la_personne&gt; !"</b>
* @author Romulus-Marius Cimpeanu  et Adlane Belmokhtar
* @version 0.1, 09.04.2013
*/

public class ThreadMr extends Thread
{
	/**
	 * fonction specifique aux threads, necessaire pour les demarrer	
	 */
	public  void run()
	{
		/**
		 * lire avec un "Buffer" et utiliser try-catch pour traiter les possibles erreurs
		 */
		
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader("ListeNoms.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		String strLine;
		/**
		 * utiliser la methode Tokenizer pour separer les mots de chaque ligne; possibles erreurs traitees avec try-catch 
		 */
		
			try {
				while ((strLine = in.readLine()) != null)  
				 {  StringTokenizer st=new StringTokenizer(strLine," ");
				 String tip=st.nextToken();
				 String nume=st.nextToken();
				 if(tip.equalsIgnoreCase("Mr"))
				 {
				 System.out.println("Bonjour "+tip+" "+nume);
				 scrie("Bonjour "+tip+" "+nume);
				 }
	}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * 
			 * @param s - le string qui doit etre ecrit dans le document
			 */
			
		}
	
	public void scrie(String s)
	{
		try{
			  // Create file 
			  FileWriter fstream = new FileWriter("out.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(s +"\n");
			  //Close the output stream
			  out.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }	
	}
	
}
