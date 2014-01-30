/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProducerConsumerPatern;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProducerMme implements Runnable{
	
    private BlockingQueue<String> sharedQueue;
    private ArrayList<String> listPersonnes;

	public ProducerMme(BlockingQueue sharedQueue,
			ArrayList<String> listPersonnes) {
        this.sharedQueue = sharedQueue;
        this.listPersonnes = listPersonnes;
		// TODO Auto-generated constructor stub
	}

	public void run(){
		synchronized (this){
	    for(int i=0; i<listPersonnes.size(); i++){
	    	if (listPersonnes.get(i).substring(0, 3).equals("Mme"))
	        try {
	            System.out.println("Produced: " + listPersonnes.get(i));
	            sharedQueue.put(listPersonnes.get(i));
	        } catch (InterruptedException ex) {
	            Logger.getLogger(ProducerMme.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    ProducerM.suspended = false;
        try {
          wait();                     
                       
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        notify();
	}
	}
}
