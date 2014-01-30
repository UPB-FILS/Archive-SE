import java.util.*;
import java.io.*;
/**
 * 
 * TP 2 - E/S en Java<br/>
 * Le programme s'utilise : <b>java HelloWorld &lt;nom_de_fichier&gt;</b><br/>
 * Le programme affiche : <b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>, pour chaque ligne trouvee dans le fichier
 * @author Dragos STOICA
 * @version 0.1, 25.Feb.2013 
 */
public class HelloWorld
{
	/**
	 * @param args contient le nom de la personne
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Utilisation: java HelloWorld <nom_de_fichier>");
			System.exit(0);
		}

		try{
			String line;
            File f = new File(args[0]);
            FileInputStream f_in = new FileInputStream(f);
            BufferedReader br =new BufferedReader(new InputStreamReader(f_in));
            
            while((line = br.readLine()) != null){
                FilsExecution threadExec = new FilsExecution(line);
            };
            br.close();
            f_in.close();
        }catch(Exception e){
            System.out.println("Exception: " + e);
        };

	}
}

class FilsExecution extends Thread{
	String arg;
	
	public FilsExecution (String str){
		super(str);
		this.arg = str;
		start();
	}
	
	
	public void run(){
		System.out.println("Bonjour " + this.arg  + " !");
	}

}
