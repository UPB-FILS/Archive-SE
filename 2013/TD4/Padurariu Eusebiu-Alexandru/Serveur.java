package serveur;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *Clasa care creeaza serverul
 * @author Padurariu Eusebiu-Alexandru
 * version 1.2
 */
public class Serveur {
    int NO_CONNECTIONS;
    int ConnectionsActives=0;
    
    ServerSocket ecouteurServeur;


public Serveur(ServerSocket ecouteurServeur){

    this.ecouteurServeur = ecouteurServeur;
 
}
public  void run(ServerSocket ecouteurServeur){
    try{
        while(true){
        
        Socket ecouteurClient = ecouteurServeur.accept();
        
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
 * Tine cont de conectiuni
 */
     public synchronized void connectionDemarree(){
            ConnectionsActives++;
            //System.out.print("Conexiune demarata");
        }
/**
 * inchidem conectiunea
 */
public synchronized void connectionFermee(){
    ConnectionsActives--;
}
/**
 * Returneaza conectiuni active
 * @return 
 */
public synchronized int getConnectionsActives(){
    return this.ConnectionsActives;
}
}   