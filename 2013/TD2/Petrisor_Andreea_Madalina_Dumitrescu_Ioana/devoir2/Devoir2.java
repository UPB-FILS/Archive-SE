/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Petrisor Andreea Madalina si Dumitrescu Ioana
 * groupe:1221F
 */
public class Devoir2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException{
        // TODO code application logic here
      //declaratea threadurilor
        Doamne mme=new Doamne();
		domni m=new domni();
                //initializarea lor
		Thread tr=new Thread(mme);
		Thread tr2=new Thread(m);
                //pornirea lorS
		tr.start();
		tr.join();//tr2 asteapta tr
		tr2.start();
    }
}
