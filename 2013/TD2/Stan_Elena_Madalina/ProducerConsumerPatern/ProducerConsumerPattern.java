// TD2- E/S en Java
//auteur Stan Elena Madalina
// Progrmme realise en Netbeans, le 9 mars 2013, qui dit Bonjour "homme" et "femme".On ecrit dans un fichier Bonjour "homme" et "femme" et dans un autre fichier nous 
//afichons ordonne 
//Nous avons un seul producteur et deux consumateurs
// On utilise aussi la classe Logger et Level de la blibliotheque java.util


package ProducerConsumerPatern;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumerPattern {
	
	private static  BufferedInputStream bufferedInput = null;
	
	private static FileInputStream fis;
	
	private static FileOutputStream fos;
	
	private static  BufferedOutputStream bufferedOutput = null;
	
	private static  String chunk;
	
	public static ArrayList<String> personnes = new ArrayList<String>();
	
    //Creating shared object
	 static BlockingQueue sharedQueue = new LinkedBlockingQueue();

    public static void main(String args[]){
    	
    	try {
			fos = new FileOutputStream("Bonjour.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bufferedOutput = new BufferedOutputStream(fos);
  
    readFromFile();
     //Creating Producer and Consumer Thread
     Thread prodThreadMme = new Thread(new ProducerMme(sharedQueue, personnes));
     Thread prodThreadM = new Thread(new ProducerM(sharedQueue, personnes));
     Thread consThread = new Thread(new Consumer(sharedQueue));

     //Starting producer and Consumer thread
     prodThreadM.start();
     prodThreadMme.start();
     consThread.start();
    }
    
    public static void readFromFile() {
    	
    	try {
			fis = new FileInputStream("ListePersonnes.txt");
			bufferedInput = new BufferedInputStream(fis);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

        byte[] buffer = new byte[1024];
        
        try {
            int bytesRead = 0;  
            //Keep reading from the file while there is any content
            //when the end of the stream has been reached, -1 is returned
            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                 chunk = new String(buffer, 0, bytesRead);
                 String[] aux = chunk.split("\n");
                 for (int i=0; i<aux.length; i++)
                	 personnes.add(aux[i]);
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
        finally {
            //Close the BufferedInputStream
            try {
                if (bufferedInput != null)
                    bufferedInput.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void writeToFile(String s) {
    	  try {
				bufferedOutput.write(("Bonjour " + s).getBytes());
				System.out.println("Bonjour " + s);
				bufferedOutput.write("\r\n".getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public static void closeBufferOutput(){
	     try {
	    	 System.out.println("!!!!!!");
	       if (bufferedOutput != null) {
	           bufferedOutput.flush();
	           bufferedOutput.close();
	       }
		   } catch (IOException ex) {
		
                       
                       ex.printStackTrace();
		   }
    }

 
}

