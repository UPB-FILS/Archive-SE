/*
  * @version 1, 5 mai 2013
 * @param constructor for the Consumer class, method run()
 */
package tema2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @Alexandru Bogdan Georges
 */
public class Consumer extends Thread {
    
      private Buffer buffer ;
        
      public Consumer ( Buffer b) {
          this.buffer = b;
        }
 
    @Override
    public void run () {
            
      try{
        File f=new File("D:\\My Documents\\FAC\\SE\\Devoir2\\Tema2\\ListePersonnes.txt");
        File f2= new File("D:\\My Documents\\FAC\\SE\\Devoir2\\Tema2\\Bonjour.txt");
        FileInputStream f1= new FileInputStream(f);
        FileOutputStream f3= new FileOutputStream(f2);
        PrintWriter pw=new PrintWriter(f3,true);
        BufferedReader b1=new BufferedReader(new InputStreamReader(f1));
        String a=buffer.get() ;
        
         if(a!=null){ 
            pw.println(a);
            
            }
        
        
        b1.close();
        f1.close();
        
        }catch(Exception e){
            e.printStackTrace();
        }
            
             
           }
}
