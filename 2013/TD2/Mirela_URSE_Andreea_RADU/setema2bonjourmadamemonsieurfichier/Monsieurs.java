package setema2bonjourmadamemonsieurfichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author Myry
 */

public class Monsieurs extends Thread
{
    @Override
    public void run() 
    {
        try
        { 
            // on declare et initialise les fichiers d'ou on lit et ou on va ecrire
            File f1 = new File("noms.txt");
            FileInputStream ifstream = new FileInputStream(f1);
            DataInputStream in = new DataInputStream(ifstream);
            File f2 = new File("bonjour.txt");
            FileWriter ofstream = new FileWriter(f2, true);
            BufferedWriter out = new BufferedWriter(ofstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            
            while (((str = br.readLine()) != null)) // on verifie s'il y a encore des lignes non-nulles
            {
                String[] noms = str.split(" "); // on separe le nom de la personne de son appelative (madame ou monsieur)
                if (noms[0].equals("Monsieur")) // on verifie si l'appelative est "monsieur"
                {
                    out.write("Bonjour, Monsieur " + noms[1] + "!"); 
                    out.newLine(); // on ecrit dans le fichier le salut et, apres, on ajoute un nouveau ligne
                }
            }          
            out.close();     // on ferme le fichier       
        }
        catch (Exception e2)
        {
            System.err.println("Error: " + e2.getMessage()); // s'il y a des erreurs, on affiche un message 
	}
    }
}
