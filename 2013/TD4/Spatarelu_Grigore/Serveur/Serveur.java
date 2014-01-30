import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *La clase qui cree le serveur
 * @author Gabriela
 * version 1.0
 */
public class Serveur {
    int NO_CONNECTIONS;
    int ConnectionsActives=0;
    //ServerSocket sc;
    //Socket sock;
    ServerSocket ecouteurServeur;


public Serveur(ServerSocket ecouteurServeur){
//try{
    this.ecouteurServeur = ecouteurServeur;
    //System.out.println(ecouteurServeur);
    //ConnectionsActives = 0;
    //this.start();
//}catch (BindException e){
//System.out.println("Erreur : " + e);
//}catch(IOException e){
//System.out.println("Erreur : " + e);
//}
}
public  void run(ServerSocket ecouteurServeur){
    try{
        while(true){
        //System.out.println(ecouteurServeur);
        Socket ecouteurClient = ecouteurServeur.accept();
        //System.out.println(ecouteurClient);
        ConnectionClient connectionClient = new ConnectionClient(ecouteurClient,this);
        connectionClient.start();
        
        }
    }catch (IOException e){
        System.out.println("Erreur : " + e);
    }finally{
        try{
            ecouteurServeur.close();
            
        }catch (IOException e){
            System.out.println("Erreur : " + e);
        }	
    }

}
/**
 * La methode qui compte les conections actives
 */
     public synchronized void connectionDemarree(){
            ConnectionsActives++;
            //System.out.print("Conexiune demarata");
        }
/**
 * Methode qui ferme la conection
 */
public synchronized void connectionFermee(){
    ConnectionsActives--;
}
/**
 * Methode qui retourn les conections  qui sont actives
 * @return 
 */
public synchronized int getConnectionsActives(){
    return this.ConnectionsActives;
}
}   
