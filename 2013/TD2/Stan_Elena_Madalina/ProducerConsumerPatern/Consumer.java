
package ProducerConsumerPatern;


import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

class Consumer implements Runnable{

    private final BlockingQueue sharedQueue;
    private int i=0;

    public Consumer (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
  
    @Override
    public void run() {
        while(true){
            try {
            	String personne = (String)sharedQueue.take();
                System.out.println("Consumed: "+ personne);
                ProducerConsumerPattern.writeToFile(personne);
                i++; //linie
                if (i == ProducerConsumerPattern.personnes.size())
                	ProducerConsumerPattern.closeBufferOutput();
                	
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   	
    }
  
  
}