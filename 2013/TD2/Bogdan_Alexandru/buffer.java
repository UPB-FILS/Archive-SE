/*
 * 
 */
package dev 2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandru Bogdan
 */
public class Buffer {
    
     private String cuv ;
     private boolean available = false ;
/**
 * @return cuv   string-ul citit din fisierul "ListePersonnes.txt"
 * @throws InterruptedException     Thrown when a thread is waiting, sleeping, or otherwise 
 *                                   occupied, and the thread is interrupted, 
 *                                    either before or during the activity. 
 */
     public synchronized String get () {
       while (! available ) {
         try {
          wait ();
            // Asteapta producatorul sa puna o valoare
             } catch ( InterruptedException e) {
                  e. printStackTrace ();
              }
             }
                 available = false ;
                  notifyAll ();
                    return cuv ;
      }
/**
 * @param expr     String-ul citit din ListePersonnes.txt
 * @return void
 * @throws InterruptedException     Thrown when a thread is waiting, sleeping, or otherwise 
 *                                   occupied, and the thread is interrupted, 
 *                                    either before or during the activity. 
 */
     public synchronized void put (String expr ) {
         while ( available ) {
             try {
               wait();
                  // Asteapta consumatorul sa preia valoarea
                 }catch ( InterruptedException e) {
                      e. printStackTrace ();
                   }
             }
                this . cuv = expr ;
                available = true ;
                 notifyAll ();
         
           }
}
