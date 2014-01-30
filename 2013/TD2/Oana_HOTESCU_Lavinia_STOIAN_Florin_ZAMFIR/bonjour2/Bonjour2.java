 /*TD2-Devoir2-programme qui salue d'abord les dames et après les monsieur<br/>
 * Le programme s'utilise:<b>le programmmelit les donnees d'un fichier texte et ecrit dans un autre fichier texte "Bonjour"+"Mme." ou "M."+nom_de_la_personne,avec priorite pour "Mme" </b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" dans un fichier texte Bonjour.txt</b>
 * @author Oana HOTESCU, Lavinia STOIAN, Florin ZAMFIR
 * @version 7.2, 18.Mar.2013
 */
package bonjour2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Oana User
 */
public class Bonjour2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       Buffer buf = new Buffer(); 
 Madame mme = new Madame( 10,buf);// se creeaza thread-ul madame
 Monsieur m = new Monsieur( 10,buf);//se creeaza thread-ul monsieur
 Consumer c = new Consumer( 10,buf); //se creeaza thread-ul consumer
 mme.start();// se porneste thread-ul madame
 m.start();// se porneste thread-ul monsieur
 c.start();//se porneste thread-ul consumer
 
}
}