
package td2;

/**
 * Deuxieme devoir</br>
 * <b>Patron de conception Producer - Consumer</b></br>
 * Le programme lit un <b>fichier d'entree</b>, ligne par ligne; on utilise deux fils d'execution pour
 * traiter separement les madames et les monsieurs.
 * @authors Sorina CHITU, Ilyass BATHAHI, groupe 1221F
 * @version 0.3
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class TD2 {

    public static void main(String[] args) throws IOException, InterruptedException{
       
      //la declaration des threads
        Madames mme=new Madames();
		monsieurs m=new monsieurs();/*L'initialisation*/ 
                Thread tr=new Thread(mme);
		Thread tr2=new Thread(m); /*le demarrage*/
		tr.start();
		tr.join();/*tr2 attends pour tr*/
		tr2.start();
    }
}