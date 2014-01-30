package serveur;
import java.io.IOException;
import java.net.ServerSocket;


 /**
 * @author Padurariu Eusebiu-Alexandru
 * version 1.2
 */
public class Srv {
    
    public static void main(String [] args) throws IOException{
        int NO_CONNECTIONS =0;
   ServerSocket ecouteurServeur = new ServerSocket(7777, NO_CONNECTIONS);
  
    Serveur server = new Serveur(ecouteurServeur);
    server.run(ecouteurServeur);
    
    }
}