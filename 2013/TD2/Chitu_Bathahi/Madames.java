

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
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Madames extends Thread{
 
    public void run() // la methode necesaire pour commencer le Thread
   {
		FileInputStream fstream = null;
        try 
        {//denommer le fichier 
            fstream = new FileInputStream("ListeNoms.txt"); 
            DataInputStream in=new DataInputStream(fstream);
            BufferedReader br=new BufferedReader( new InputStreamReader(in));// lire les information dans le fichier
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Madames.class.getName()).log(Level.SEVERE, null, ex);
        } String strline;
			while ((strline = br.readLine()) != null)  //desparte cuvinele
			 {  StringTokenizer st=new StringTokenizer(strline," ");
			 String a=st.nextToken();
			 String b=st.nextToken();
			 if(a.equalsIgnoreCase("Mme")) // verifica daca este doamna sau domn
			 {
                try {
                    write("Bonjour"+" "+a+" "+b+"!");//ecrire le resultat dans le fichier
                } catch (IOException ex) {
                    Logger.getLogger(Madames.class.getName()).log(Level.SEVERE, null, ex);
                }
                         }
                
}  
}
    public void write(String s) throws IOException
{

		 Writer output = null;
	    File file = new File("Bonjour.txt");
	    output = new BufferedWriter(new FileWriter(file));//creer un nouveau fichier pour ecrir les resulatates
		  output.close();

}
}


