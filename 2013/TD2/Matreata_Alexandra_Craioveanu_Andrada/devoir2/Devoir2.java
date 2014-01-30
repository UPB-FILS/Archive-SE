/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * Le programme s'utilise : <b>java Devoir2 &lt;nom_de_la_personne&gt;</b><br/>
 * Le programme affiche : <b>"Bonjour M.  &lt;nom_de_la_personne&gt; !"</b> 
 * @author Matreata Alexandra 
 * @author Craioveanu Andrada 
 */
public class Devoir2{

    /**
     * Le programme affiche en le fichier Bonjour.txt "Bonjour M. " + [liste_de_noms] de le fichier Listepersonnes.txt
     * @param args the command line arguments
     * @param t qui est le tampon 
     * @param f1 qui est un thread
     * @param f2 qui est un thread
     * @param c qui est un thread 
     */
    public static void main(String[] args)throws IOException {
        
        
        Tampon t=new Tampon();
        Producer f1=new Producer(t);
        f1.setPriority(2);
        Producer f2=new Producer(t);
        f2.setPriority(1);
        
        int k=0;
        /**
         * On lit le fichier a l'aide de le block try catch et on appelle les fonctions "functie" pour chaque thread 
         */
        try
        {   String strLine;
            int i=0;
            FileInputStream fstream = new FileInputStream("ListePersonnes.txt");
                // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
                // we memorize in a buffer the file content
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((strLine = br.readLine()) != null)   
                {k++;
                 String[] words = strLine.split(" ");
                 switch(words[0])
                        {
                            case "Mme.": { 
                                f1.functie(words[0]+words[1]);
                                break;}
                            
                            case "M.": {                               
                            f2.functie(words[0]+words[1]); }
                        } 
                                        
                }
                in.close();}
        catch (Exception e1){
            System.err.println("Error: " + e1.getMessage()+"it can be customized");
            }
      /**
       * On declare un logger a l'aide de le block try catch et on affiche un message
       */
         try{
            FileHandler hand = new FileHandler("vk.log");
            Logger log = Logger.getLogger("log_file");
            log.addHandler(hand);
            log.info("Le programme commence a afficher les noms de fichier");
         
            System.out.println(log.getName());
            }
        catch(IOException e){}
      
          
      Consumer c=new Consumer(t);   
      
      f1.start();
      f2.start();
       c.start(); 
      /**
         * détermine ces fils de commencer l'exécution, la machine virtuelle Java appelle la méthode run de ces fils
      */
       
    }
}
