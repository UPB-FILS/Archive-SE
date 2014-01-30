/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author SunnY
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        (new Thread(new Vasile())).start();
        (new Thread(new Dragos())).start();
        (new Thread(new Gheorghe())).start();
        
    }
}
