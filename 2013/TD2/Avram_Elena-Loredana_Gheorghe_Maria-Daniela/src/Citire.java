
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Acer
 */
public class Citire {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
BufferedReader rd = new BufferedReader(new FileReader("personnes.txt"));
Writer output=null;
File file=new File("bonjour.txt");
output=new BufferedWriter(new FileWriter(file));


Madame m = new Madame();
m.start();

Monsieur mm=new Monsieur();
mm.start();
    }
          

 
    public class Madame extends Thread{
        public void run()
        {
                Scanner sc2 = null;
    try {
        sc2 = new Scanner(new File("personnes.txt"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();  
    }
    while (sc2.hasNextLine()) {
            Scanner s2 = new Scanner(sc2.nextLine());
        boolean b;
        while (b = s2.hasNext()) {
            String s = s2.next();
            
            if(s.equalsIgnoreCase("Mme"))
            {
                output.write("Bonjour Madame "+s2.next()+"\r\n");
            }
     }
        output.close();
    }
    
    }
    }
        
        public class Monsieur extends Thread
        {
            
            public void run()
            {    Scanner sc2 = null;
    try {
        sc2 = new Scanner(new File("personnes.txt"));
    } catch (FileNotFoundException e) {
        e.printStackTrace();  
    }
             Scanner s3 = new Scanner(sc2.nextLine());
        boolean c;
        while (c = s3.hasNext()) {
            String s = s3.next();
            
            if(s.equalsIgnoreCase("M"))
            {
                output.write("Bonjour Monsieur "+s3.next()+"\r\n");
            }
     }
         output.close();
          
            }
        
        
        
      }
        

   
}



    


