/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main2;

/**
 *
 * @author SunnY
 */
public class Main2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    for (int i = 0; i < 4; i++) //numarul de persoane 
    {
    final int nr = i ;
    new Thread(new Runnable()
    {
        public void run() {
            System.out.println("Thread " + new String[] { "ion", "vasile", "gigi", "ginel" }[nr] + " says Hello World!"); // se alege persoana 
        }
    }).start();
}}}

