/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Oana User
 */
public class Madame extends Thread{
    private int n;
  	private Buffer prodBuf;
  	
  	public Madame (int m, Buffer buf) {
  		n = m;
  		prodBuf = buf;
    }
    
     
{
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Lavy\\Desktop\\Facultate\\An II\\Sem II\\SE\\ListePersonnes.txt"));
        } catch (IOException e)

{
System.out.println(e);//tratarea excepției
}
    }
    @Override
    public void run() {
    	
 try{
     BufferedReader in = null;
            try {// prinde o posibila eroare neoprind executia programului
                in = new BufferedReader(new FileReader(" "));//scrie in fisierul Bonjour.txt
            } catch (FileNotFoundException ex) {// prinde exceptia ex
                ex.printStackTrace();
            }
 String str;
            try {
                while ((str = in.readLine()) != null) //lucru cu informațiile din fișier
                {
                    String[] words = str.split(" "); // se separa pe fiecare rand constructia Mme de numele persoanei
                    if (words[0].equals("Mme.")) {// se verifica daca este vorba despre o femeie
                        prodBuf.put(words[0], words[1]);// incepe de la 0
                        sleep(1000);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
 }
 catch( InterruptedException e ){} 
 }
}

