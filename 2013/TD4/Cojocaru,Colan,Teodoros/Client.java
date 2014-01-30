
package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

 /**
 * @author Cojocaru Laurentiu
 * @author Colan Vlad
 * @author Teodoros Silviu
 * version 1.2
 */
public class Client {

   
    public static void main(String[] args) throws UnknownHostException, IOException {
  
       Socket ligneServeur = new Socket("localhost", 7777);
     
       PrintStream  out = new PrintStream(ligneServeur.getOutputStream());
       System.out.println("Entree le nom: ");
       InputStreamReader rd = new InputStreamReader(System.in);
       BufferedReader ed = new BufferedReader(rd);
       String temp =ed.readLine();
       out.println(temp);
       BufferedReader in = new BufferedReader(new InputStreamReader(ligneServeur.getInputStream()));
      
        System.out.println("La reponse du serveur : " + in.readLine());
        out.close();
        in.close();
        ligneServeur.close();
    }	

}

