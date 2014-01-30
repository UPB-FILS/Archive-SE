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
 * @author Greculescu Ana, Stanescu Alexandra, Raluca Nastasa
 * @version 0.2, 3.03.2013
 */
public class Femme extends Thread  {
   
    @Override
  public void run () { // se scrie metoda run specifica clasei Thread
               try{ 
        File f= new File("c:\\listepersonnes.txt"); // se citeste fisierul
        File g= new File("C:\\Users\\Alexandra\\Documents\\Bonjour.txt"); // au fost initializate fisierele pentru scriere si citire
        FileInputStream fstream = new FileInputStream(f);
        FileWriter ostream = new FileWriter(g);
        BufferedWriter out= new BufferedWriter(ostream);
			  
	DataInputStream in = new DataInputStream(fstream);
			
        BufferedReader br = new BufferedReader(new InputStreamReader(in)); //comenzile pentru scriere si citire
       String strLine;
       
			
	while (((strLine = br.readLine()) != null))
        {String[] words = strLine.split(" "); // se separa pe fiecare rand constructia identificatoare "Mme." de numele persoanei
        if (words[0].equals("Mme."))// se verifica daca este vorba despre o femeie
        {
            out.write("Bonjour Madame "+words[1]);// se scrie in fisierul de iesire mesajul cerut
            out.newLine();//se adauga un rand nou
        }
        }
        out.close();// se inchide fisierul de scriere
  }catch (Exception e1){
			  System.err.println("Error: " + e1.getMessage());
			  }// sunt identificate eventualele erori
    }
    
}
