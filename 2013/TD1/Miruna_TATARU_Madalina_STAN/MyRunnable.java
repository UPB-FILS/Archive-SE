/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIRUNA
 */
/*Program care spune "Bonjour" realizat la data de 22.02.2013
 * compilat cu ajutorul compilatorului javac si se apeleaza cu java Bonjour nume
*/
public class MyRunnable{
    public static void main (String args[]) {
        new SimpleThread("Vasile").start(); //pornesc threadul
        new SimpleThread("Ion").start();
        new SimpleThread("Gheorghe").start();
    }
}

class SimpleThread extends Thread {
    public SimpleThread(String str) { //constructor
	super(str);
    }
    public void run() {         //metoda run din interfata Runnable pt afisare                
	for (int i = 0; i < 1; i++) {
	    System.out.println("Bonjour" + " " + getName()); 
}}}

