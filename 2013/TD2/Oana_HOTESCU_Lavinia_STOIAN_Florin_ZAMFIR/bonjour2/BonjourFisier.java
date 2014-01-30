/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Florin
 */
public class BonjourFisier {

    /**
     * @param args the command line arguments
     */
    public static void scrie(String str){// netoda scrie
     FileWriter fstream;
     try{// prinde o posibila eroare neoprind executia programului
         fstream=new FileWriter("Bonjour.txt");// scrie in fisierul Bonjour.txt
         BufferedWriter out=new BufferedWriter(fstream);
         out.write(str);//scrie stringul in fisier
         out.close();// se inchide fisierul
     }
     catch(IOException e){// se trateaza eroarea
         
     }
    }
    public static void main(String[] args) {
        // TODO code application logic here
      
    }
}
