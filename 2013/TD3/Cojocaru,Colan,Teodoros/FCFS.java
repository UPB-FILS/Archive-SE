import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * La clase plan le thread
 * @author Cojocaru Laurentiu
 * @author Colan Vlad
 * @author Teodoros Silviu
 * version 1.0
 */

public class FCFS extends Thread{

TreeMap tm;
int no_CPU = 5;
int capacite = 18;
BlockingQueue<String> queue = new ArrayBlockingQueue<String>(capacite);
BlockingQueue<String> consumator = new ArrayBlockingQueue<String>(15);

Logger logger;
ArrayList tf = new ArrayList();

FileWriter fw;
BufferedWriter bw;
/**
 * Le constructeur de la classe
 * @param tm
 * @param logger
 * @param fw
 * @param bw 
 */
public FCFS(TreeMap tm, Logger logger,FileWriter fw, BufferedWriter bw){
    this.tm = tm;
    this.logger = logger;
    System.out.println("Ordonanceur FCFS active !");
    this.logger.log(Level.INFO, "Ordonanceur FCFS active !");
    this.fw =fw;
    this.bw =bw;
}
    @Override
public synchronized void run(){

    int compteurTaches = 0;

    CPU[] cpu = new CPU[no_CPU];
    //se pornesc cele doua thread-uri
    for(int k = 0; k < cpu.length; k++) {
        cpu[k] = new CPU(queue,k, this.logger);
        try{
            cpu[k].start();
        }catch (IllegalThreadStateException e) {
            System.out.println("Exception: " + e);
            this.logger.log(Level.SEVERE, "Exception : {0}", e);
        }
    }	
    System.out.println("--------------------------------");
    
    Set set = this.tm.entrySet();
    Iterator i = set.iterator();
    while(i.hasNext()){	
        boolean disponible = false;
        for (int k = 0; k < no_CPU; k++){
            disponible = disponible || cpu[k].getDisponible();
        }

        if (!disponible){
            try{
                this.wait(1000);
            }catch(InterruptedException e){
                System.out.println("Exception: " + e);
                this.logger.log(Level.SEVERE, "Exception : {0}", e);
            }
        }else{
            Map.Entry me = (Map.Entry)i.next();
                try{	
                    Scanner s = new Scanner(me.getValue().toString()).useDelimiter("#");
                    String le_nom = s.next();
                    try {
                    synchronized(this.bw){
                        bw.write("Bonjour Mme. "+ le_nom+ "!");
                        bw.newLine();
                        bw.notify();
                    }
                    } catch (IOException ex) {
                        Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int temps_execution = s.nextInt();
                    int temps_demarrage = (Integer)me.getKey();
                    if (compteurTaches < no_CPU){
                        tf.add(temps_demarrage + temps_execution);
                    }else{
                        Comparator comparator = Collections.reverseOrder();
                        Collections.sort(tf,comparator);
                        temps_demarrage = (Integer)tf.get(0);
                        for (int m = 1; m < no_CPU; m++){
                            temps_demarrage = Math.min(temps_demarrage, (Integer)tf.get(m));
                      }
                        if (temps_demarrage <= (Integer)me.getKey()) temps_demarrage = (Integer)me.getKey();
                            tf.add(temps_demarrage+temps_execution);
                    }
                    compteurTaches++;
                    System.out.println("Tache : " + le_nom +
                    " | TA : " + me.getKey() +
                    " | TE : " + temps_execution +
                    " | TD : " + temps_demarrage +
                    " | TF : " + (temps_demarrage+temps_execution) +
                    " | ZZ : " + (temps_demarrage - (Integer)me.getKey()) );
                    this.queue.put(me.getValue().toString());
                   
                }catch(InterruptedException e){
                System.out.println("Exception: " + e);
                this.logger.log(Level.SEVERE, "Exception : {0}", e);
                }
            }
        }
        try{
            for (int k = 0; k < no_CPU; k++)
                {
                queue.put("#STOP#");
                }
        }catch(InterruptedException e){
            System.out.println("Exception: " + e);
            this.logger.log(Level.SEVERE, "Exception : {0}", e);
            }
    this.logger.log(Level.INFO, "Ordonanceur FCFS terminee!");
    }	
}