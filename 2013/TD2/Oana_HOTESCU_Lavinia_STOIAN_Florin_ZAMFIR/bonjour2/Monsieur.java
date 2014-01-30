/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;

import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author Oana User
 */
public class Monsieur extends Thread{
    private int n;
  	private Buffer prodBuf;
  	
  	public Monsieur (int m, Buffer buf) {
  		n = m;
  		prodBuf = buf;
    }
    
 {
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Lavy\\Desktop\\Facultate\\An II\\Sem II\\SE\\ListePersonnes.txt"));
        } catch (IOException e)// scrierea in fisier a domnilor

{
System.out.println(e);//tratarea excepției
}
    }
    @Override
    public void run() {
    	
 try{
BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(" "));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();// folosita pt diagnosticarea unei exceptii
            }
 String str;
            try {
                while ((str = in.readLine()) != null) //lucru cu informațiile din fișier
                {
                    String[] words = str.split(" "); // se separa pe fiecare rand
                    if (words[0].equals("M.")) {
                        prodBuf.put(words[0], words[1]);//incepe de la 0
                        sleep(1000);
                    }
                }
            } catch (IOException ex) {//tratarea exceptiei ex
                ex.printStackTrace();//diagnosticarea exceptiei
            }
 }
 catch( InterruptedException e ){} 
 }
}

