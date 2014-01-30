/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProducerConsumerPatern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProducerM implements Runnable{
	
    private BlockingQueue<String> sharedQueue;
    private ArrayList<String> listPersonnes;
    public static boolean suspended = true;

	public ProducerM(BlockingQueue sharedQueue,
			ArrayList<String> listPersonnes) {
        this.sharedQueue = sharedQueue;
        this.listPersonnes = listPersonnes;
		// TODO Auto-generated constructor stub
	}

	public void run(){
		synchronized (this){
		while (suspended)
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		    for(int i=0; i<listPersonnes.size(); i++){
		    	if (listPersonnes.get(i).substring(0, 2).equals("M."))
		        try {
		            System.out.println("Produced: " + listPersonnes.get(i));
		            sharedQueue.put(listPersonnes.get(i));
		        } catch (InterruptedException ex) {
		            Logger.getLogger(ProducerM.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }
	}
	
}
