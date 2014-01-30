/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listepersonnes;

import java.io.*;

/**
 *Devoir 2 Patron de conception Producer-Consumer
 * Le program s'utilise: <b> le programme lit un fichier d’entrée, nommé ListePersonnes.txt, en format texte, ligne par ligne</b><br/>
 * Le program affiche: <b>le programme doit saluer d'abord les dames et après les monsieur. Ceci doit être visible dans un fichier nommé Bonjor.txt, où nous allons trouvé des lignes correspondant aux modèle, </b><br/>
 * @author Nastasa Raluca, Stanescu Alexandra, Greculescu Ana
 * @version 0.2, 3.03.2013
 */
public class Homme extends Thread{
    @Override
   public void run () {
               try{ 
        File f= new File("C:\\listepersonnes.txt");
        FileWriter out = new FileWriter("C:\\Users\\Alexandra\\Documents\\Bonjour.txt",true);// au fost initializate fisierele pentru scriere si citire
       //true in tipul FileWriter este folosit pentru a se adauga la fisierul in care deja s-a scris mai devreme. Altfel, tot continutul ar fi fost sters
        FileInputStream fstream = new FileInputStream(f);
			  
	DataInputStream in = new DataInputStream(fstream);
	

        
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
       String strLine;
        
			
	while (((strLine = br.readLine()) != null))
        {String[] words = strLine.split(" ");// se separa pe fiecare rand constructia identificatoare "M." de numele persoanei
        if (words[0].equals("M."))// se verifica daca este vorba despre un barbat
        {
            out.write("Bonjour Monsieur "+words[1]+System.getProperty("line.separator")  ); // se scrie mesajul in fisier si se adauga o linie noua folosind System.getProperty("line.separator")
        }
        
        }          out.close();     // se inchide fisierul de scriere       
  }catch (Exception e1){
			  System.err.println("Error: " + e1.getMessage());
			  }//se identifica eventualele erori
    }
}
