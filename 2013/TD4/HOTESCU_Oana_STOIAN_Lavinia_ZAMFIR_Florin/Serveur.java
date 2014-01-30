/*TD5-Devoir3-programme Serveur qui fournit ses services sur le port 7777<br/>
 * Le programme s'utilise:<b>Le Serveur traite les Clients par le biais des fils d’exécution et repond aux Clients connectes par "Bonjour Mme. <nom_de_la_personne>!" ou "Bonjour M. <nom_de_la_personne>!" selon le message du Client</b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" sur la console et dans un fichier texte BonjourServeur.txt</b>
 * @author Oana HOTESCU, LAvinia STOIAN, Florin ZAMFIR
 * @version 7.2, 22.Avril.2013
 */
import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class Serveur extends Thread{

protected ServerSocket ecouteurServeur;
private int ConnectionsActives;
private final int PORT = 7777;
public final int NO_CONNECTIONS = 5;

public static void main(String[] args){
new Serveur();

}

public Serveur(){
try{
ecouteurServeur = new ServerSocket(PORT, NO_CONNECTIONS);
ConnectionsActives = 0;
this.start();
}catch (BindException e){
System.out.println("Erreur : " + e);
}catch(IOException e){
System.out.println("Erreur : " + e);
}	
}

public void run(){
try{
while(true){
Socket ecouteurClient = ecouteurServeur.accept();
ConnectionClient connectionClient = new ConnectionClient(ecouteurClient,this);
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

class ConnectionClient extends Thread{

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

BufferedReader messageClient = new BufferedReader(new InputStreamReader(communicationClient.getInputStream()));
            String receptionClient = messageClient.readLine();
            
PrintWriter ligneClient = new PrintWriter(communicationClient.getOutputStream(), true);
InetAddress adresseClient = communicationClient.getInetAddress();
int portClient = communicationClient.getPort();
InetAddress adresseServeur = null;
try{
adresseServeur = InetAddress.getLocalHost();
}catch (UnknownHostException e){
System.out.println("Erreur : " + e);
}
Logger log = Logger.getLogger("Serveur");
    
int portServeur = communicationClient.getLocalPort();
String CR = System.getProperty("line.separator");
ligneClient.println("Bonjour " + receptionClient + " ! " +
"Vour etes le client : " + administrateur.getConnectionsActives() + " de " + administrateur.NO_CONNECTIONS + " !");
System.out.println("Bonjour " + receptionClient + " !" + CR +
"Client >>> nom : " + adresseClient.getHostName() + " | IP : " + adresseClient.getHostAddress() + " | port : " + portClient + CR +
"Serveur >>> nom : " + adresseServeur.getHostName() + " | IP : " + adresseServeur.getHostAddress() + " | port : " + portServeur);
log.info(receptionClient+" wrote");
try
{
BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Oana User\\My Documents\\NetBeansProjects\\Bonjour1\\Bonjour.txt"));
out.write("Bonjour "+receptionClient + " !");

out.close();
}
catch (IOException e)
{
System.out.println(e);// tratarea excepției
}
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