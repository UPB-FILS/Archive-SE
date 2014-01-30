

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

public class Monsieurs extends Thread {
       public void run(String strline) throws IOException // la methode necesaire pour commencer le Thread
{
		FileInputStream fstream = null;
        try 
        {//denommer le fichier 
            fstream = new FileInputStream("ListeNoms.txt"); 
            DataInputStream in=new DataInputStream(fstream);
            BufferedReader br=new BufferedReader( new InputStreamReader(in));// lire les information dans le fichier
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Madames.class.getName()).log(Level.SEVERE, null, ex);
        } 
				while ((strline = in.readLine()) != null)  //on separe les mots
			 {  StringTokenizer st=new StringTokenizer(strline," ");
			 String a=st.nextToken();
			 String b=st.nextToken();
			 if(a.equalsIgnoreCase("Mme"))// verifie s'il est madame ou monsieur
			 {

                    write("Bonjour"+" "+a+" "+b+"!");//ecrire le resultat dans le fichier
                
			 }
}  
}
    public void write(String s) throws IOException
{
		Writer output = null;
	    File file = new File("Bonjour.txt");
	    output = new BufferedWriter(new FileWriter(file));//creer un nouveau fichier pour ecrir les resultates

		  output.close();

} 
}

