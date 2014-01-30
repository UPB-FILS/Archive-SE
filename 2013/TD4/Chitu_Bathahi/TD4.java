


package td4;

/**
 * Quatrieme devoir</br>
 * <b>La communication client - serveur</b></br>
 * Le programme Serveur fournit ses services sur le port 777;
 * Un programme Client qui se connecte au Serveur
 * @authors Sorina CHITU, Ilyass BATHAHI, groupe 1221F
 * @version 0.1
 */

  import java.io.*;
  import java.net.*;

public class Client {

public static void main(String[] args) throws IOException {

        Socket ligneServeur = null;
        PrintWriter out = null;
        BufferedReader in = null;

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

String messageClient = "Mme. M";

out.println(messageClient);
System.out.println("La reponse du serveur : " + in.readLine());

out.close();
in.close();
ligneServeur.close();
    }	

}
