/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Le programme affiche : <b>"Bonjour M.  &lt;nom_de_la_personne&gt; !"</b>, pour chaque ligne trouvee dans le fichier 
 * @author Matreata Alexandra
 * @author Craioveanu Andrada
 * @param file le fichier text
 * @param t le tampon 
 */
public class Consumer extends Thread{
   String a;
   File file =new File("Bonjour.txt");
   Writer output;
   private Tampon t;
   
   
   
    public Consumer(Tampon t)
    {this.t=t;
    
    
    try
        {
            output=new BufferedWriter(new FileWriter(file));
        }
    catch (Exception e1){//Catch exception if any
           System.err.println("Error: " + e1.getMessage()+"it can be customized");
    }
    
    }
    
    // Le fonction "scrie" ecrive dans le fichier "Bonjour, " + nomme 
    public void scrie(String a) throws IOException
    {   this.a=a;
    
        try
        {
            output.write("Bonjour,"+a+"!");
            
            output.flush();
            
        }
        catch (Exception e1){
            System.err.println("Error: " + e1.getMessage()+"it can be customized");
            }}
    
   
    @Override
    public void run()
     /**  Cette methode passe par le fichier de noms, utilise la methode preluare() de la classe Tampon, ecrit a l'aide de la fonction scrie() et mis le thread a sleep pour une periode de certain milisecondes
     */
    {
    for (int i=0;i<7;i++)
    { a=t.preluare();
    System.out.println("Consumator a preluat "+a);
        try {
            scrie(a);
            
            System.out.println("am scris "+a);
            sleep((int)(Math.random()*1000));
        } catch (Exception e1) {
            System.err.println("Error: " + e1.getMessage()+"it can be customized");
        }
        finally {}
    } }
    
    
}
