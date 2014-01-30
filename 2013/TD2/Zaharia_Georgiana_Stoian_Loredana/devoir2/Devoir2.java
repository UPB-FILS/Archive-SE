/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.io.IOException;

/**
 *
 * @author Georgi
 */
public class Devoir2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
         // TODO code application logic here
        Pers pc=new Pers();
        pc.ReadFile();
        pc.OpenFileToWrite();
        pc.Start();
        
    }
}
  





