
import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author Gabriela
 * version 1.0
 */
public class Srv {
    
    public static void main(String [] args) throws IOException{
        int NO_CONNECTIONS =0;
   ServerSocket ecouteurServeur = new ServerSocket(7777, NO_CONNECTIONS);
  // System.out.println(ecouteurServeur);
  // Socket sock  = ecouteurServeur.accept();
    Serveur server = new Serveur(ecouteurServeur);
    server.run(ecouteurServeur);
    
    }
}