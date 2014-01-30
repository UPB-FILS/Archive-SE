/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bonjour;

/**
 *
 * @author Beatrice GRIGORE,Gabriela SPATARELU
 */
public class SimpleThread extends Thread{
    
    public SimpleThread(String str) {
	super(str);
    }
    @Override
    public void run() {
	for (int i = 0; i < 1;i++) {
	    System.out.println("Bonjour "+ getName()+"!");
        }
        
	}
            
    
}
    
