/*TD5-Devoir3-programme Serveur qui fournit ses services sur le port 7777<br/>
 * Le programme s'utilise:<b>Le Serveur traite les Clients par le biais des fils d’exécution et repond aux Clients connectes par "Bonjour Mme. <nom_de_la_personne>!" ou "Bonjour M. <nom_de_la_personne>!" selon le message du Client</b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" sur la console et dans un fichier texte BonjourServeur.txt</b>
 * @author Oana HOTESCU, LAvinia STOIAN, Florin ZAMFIR
 * @version 7.2, 22.Avril.2013
 */
package clientserveur2;

/**
 *
 * @author Oana User
 */
import java.io.*;
import java.net.*;

public class Client {

public static void main(String[] args) throws IOException {

        Socket ligneServeur = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Buffer buffer=new Buffer();

        try {
            ligneServeur = new Socket("localhost", 7777);
            out = new PrintWriter(ligneServeur.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ligneServeur.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Erreur : " + e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Erreur : " + e);
            System.exit(1);
        }

String[] messageClient=new String[5];
messageClient[0]="Mme. J";
messageClient[1]="M. D";
messageClient[2]="Mme. R";
messageClient[3]="M. V";
messageClient[4]="Mme. N";
for (int i=0;i<messageClient.length;i++)
{buffer.put(messageClient[i]);
out.println(messageClient[i]);

System.out.println("La reponse du serveur : " + in.readLine());
}
out.close();
in.close();
ligneServeur.close();
    }	

}



