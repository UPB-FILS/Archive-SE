/*TD5-Devoir3-programme Serveur qui fournit ses services sur le port 7777<br/>
 * Le programme s'utilise:<b>Le Serveur traite les Clients par le biais des fils d’exécution et repond aux Clients connectes par "Bonjour Mme. <nom_de_la_personne>!" ou "Bonjour M. <nom_de_la_personne>!" selon le message du Client</b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" sur la console et dans un fichier texte BonjourServeur.txt</b>
 * @author Mirela Urse, Andreea Radu
 * @version 7.2, 22.Avril.2013
 */
package serveur;

import com.sun.istack.internal.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Serveur extends Thread{

            protected ServerSocket ecouteurServeur;
            private int ConnectionsActives;
            private final int PORT = 7777;
            public final int NO_CONNECTIONS = 5;

public static void main(String[] args) throws IOException{
new Serveur(); // on cree un objet en air = du type de la classe principale

    
}

public Serveur() throws IOException{
try{
    ecouteurServeur = new ServerSocket(PORT, NO_CONNECTIONS);
    ConnectionsActives = 0;
    
        this.start();
    }
            catch (BindException e){
                
                System.out.println("Erreur : " + e);
                
             }

            catch(IOException e){
                
                System.out.println("Erreur : " + e);
            }	
}


    public void run(){
        
        try{
            
            while(true){

                Socket ecouteurClient = ecouteurServeur.accept(); // on accepte une connexion socket d'un client
                ConnectionClient connectionClient = new ConnectionClient(ecouteurClient,this);
            }
            
        }
        
                 catch (IOException e){
                     
                        System.out.println("Erreur : " + e);
                }
        
        finally{
            
            try{
                
                ecouteurServeur.close();
                
            }
            
                catch (IOException e){
                    
                    System.out.println("Erreur : " + e);
                }	
}

}

    
        public synchronized void connectionDemarree(){
        ConnectionsActives++;
        }

        public synchronized void connectionFermee(){
        ConnectionsActives--;
        }

        public synchronized int getConnectionsActives(){
        return this.ConnectionsActives;
        }
}


class ConnectionClient extends Thread{ // thread pour eliberer le socket du serveur

        Socket communicationClient;
        Serveur administrateur;

public ConnectionClient(Socket ecouteurClient, Serveur le_serveur){  
        
        communicationClient = ecouteurClient;
        administrateur = le_serveur;
        this.start();

}

public void run(){
    
    administrateur.connectionDemarree();

try{

        BufferedReader messageClient = new BufferedReader(new InputStreamReader(communicationClient.getInputStream())); //prend getinput stream du socket
        String receptionClient = messageClient.readLine();
            
        PrintWriter ligneClient = new PrintWriter(communicationClient.getOutputStream(), true);//communication serveur
        InetAddress adresseClient = communicationClient.getInetAddress(); //recuperation addresse
        int portClient = communicationClient.getPort(); //recuperation port
        InetAddress adresseServeur = null;
        
                try{
    
                    adresseServeur = InetAddress.getLocalHost();
                }
                
                catch (UnknownHostException e){
                        
                        System.out.println("Erreur : " + e);
                    }
                
            
        int portServeur = communicationClient.getLocalPort();
        String CR = System.getProperty("line.separator");//
        ligneClient.println("Bonjour " + receptionClient + " ! " +
                                "Vour etes le client : " + administrateur.getConnectionsActives() + " de " + administrateur.NO_CONNECTIONS + " !"); // on envoie au client un message
        System.out.println("Bonjour " + receptionClient + " !" + CR +"Client >>> nom : " + adresseClient.getHostName() + " | IP : " + adresseClient.getHostAddress() + " | port : " + portClient + CR +"Serveur >>> nom : " + adresseServeur.getHostName() + " | IP : " + adresseServeur.getHostAddress() + " | port : " + portServeur); // cr - pour rouler et aficher correctement et sur n'importe quel type de machine
      

        try
        {
            BufferedWriter out= new BufferedWriter(new FileWriter("C:\\Users\\Andreas\\Documents\\NetBeansProjects\\Serveur\\BonjourServeur.txt"));
    
            out.write("Bonjour " + receptionClient + " ! " +
                                "Vour etes le client : " + administrateur.getConnectionsActives() + " de " + administrateur.NO_CONNECTIONS + " !");
        out.close();
        }

                catch (IOException e){
                          
                    System.out.println(e);
                }
}
        catch (IOException e){
                          
                    System.out.println("Erreur : " + e);
                }

         
                   
             finally{
    
                try{
                    
                    communicationClient.close();
                }
                
                catch(IOException e){
                        
                    System.out.println("Erreur : " + e);
                }
                
                    administrateur.connectionFermee();
               }


}

        
}