/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjourr;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ANA
 */
public class Bonjourr {

    /**TP1
     * Le program s'utilise: <b> pour lire le fichier nume.txt, un bloc try catch</b><br/>
     * Le program affiche : <b> Bonjour Dragos, Bonjour Stoica</b><br/>
     * @author Ana Greculescu
     * @version 0.01, 03.03.2013
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        try{
            
            File f= new File("nume.txt");
        FileInputStream fstream = new FileInputStream(f);
			  
			  DataInputStream in = new DataInputStream(fstream);
			
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
       String strLine;
			  int i = 0;
			
			  while (((strLine = br.readLine()) != null)&& (i<3))   {
				  
				  String[] words = strLine.split(" ");
                                  switch(words[0])
                                  {case "Bonjour Dragos":
                                   System.out.println("Bonjour Dragos");
                                      break;
                                  case "Bonjour Stoica":
                                       System.out.println("Bonjour Stoica");
                                      break;
                                  
                                      
				  }}
			  
			  
			  in.close();
                          
    }
    }
}
