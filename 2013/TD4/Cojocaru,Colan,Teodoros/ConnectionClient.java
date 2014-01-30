package serveur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
 /**
 * @author Cojocaru Laurentiu
 * @author Colan Vlad
 * @author Teodoros Silviu
 * version 1.2
 */
class ConnectionClient extends Thread{

Socket communicationClient;
Serveur administrateur;
int ConnectionsActives;

public ConnectionClient(Socket ecouteurClient, Serveur le_serveur){
communicationClient = ecouteurClient;
administrateur = le_serveur;

}

        @Override
public void run(){
administrateur.connectionDemarree();

try{

    BufferedReader messageClient = new BufferedReader(new InputStreamReader(communicationClient.getInputStream()));
   
    String receptionClient = messageClient.readLine();
   
   
    PrintStream ligneClient = new PrintStream(communicationClient.getOutputStream());
    
    InetAddress adresseClient = communicationClient.getInetAddress();
    int portClient = communicationClient.getPort();
    InetAddress adresseServeur = null;
        try{
            adresseServeur = InetAddress.getLocalHost();
        }catch (UnknownHostException e){
            System.out.println("Erreur : " + e);
        }
    int portServeur = communicationClient.getLocalPort();
    String CR = System.getProperty("line.separator");
    ligneClient.println("Bonjour " + receptionClient + " ! " + "Vour etes le client : " + administrateur.getConnectionsActives() + " de " + administrateur.NO_CONNECTIONS + " !");
System.out.println("Bonjour " + receptionClient + " !" + CR + "Client >>> nom : " + adresseClient.getHostName() + " | IP : " + adresseClient.getHostAddress() + " | port : " + portClient + CR +
"Serveur >>> nom : " + adresseServeur.getHostName() + " | IP : " + adresseServeur.getHostAddress() + " | port : " + portServeur);
}catch (IOException e){
System.out.println("Erreur : " + e);
}finally{
try{
communicationClient.close();
}catch(IOException e){
System.out.println("Erreur : " + e);
}
administrateur.connectionFermee();
}    
}
           
}