/*TD5-Devoir3-programme Serveur qui fournit ses services sur le port 7777<br/>
 * Le programme s'utilise:<b>Le Serveur traite les Clients par le biais des fils d’exécution et repond aux Clients connectes par "Bonjour Mme. <nom_de_la_personne>!" ou "Bonjour M. <nom_de_la_personne>!" selon le message du Client</b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" sur la console et dans un fichier texte BonjourServeur.txt</b>
 * @author Mirela Urse, Andreea Radu
 * @version 7.2, 22.Avril.2013
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.Buffer;


public class Client {


    public static void main(String[] args) throws IOException {
        // TODO code application logic here
    Socket ligneServeur = null;//socket
        PrintWriter out = null;// envoie message au serveur
        BufferedReader in = null; // recevoit message du serveur
         
       
        try {
            ligneServeur = new Socket("localhost", 7777); // nom du serveur et port
            out = new PrintWriter(ligneServeur.getOutputStream(), true);
           
            in = new BufferedReader(new InputStreamReader(ligneServeur.getInputStream()));
             
        
        } catch (UnknownHostException e) {
            System.err.println("Erreur : " + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erreur : " + e);
            System.exit(1);
        }
       

String messageClient = "Mme. M"; // pour etre pris avec le nom

out.println(messageClient);
System.out.println("La reponse du serveur : " + in.readLine()); // on ecrit sur la console du serveur ce qu'on recoit de lui
out.close();
in.close();
ligneServeur.close();
    }	

}
