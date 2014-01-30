import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe CPU qui extende la clase Thread
 * @author Padurariu Eusebiu-Alexandru
 * @version 1.0
 */
public class CPU extends Thread{

BlockingQueue<String> queue;
boolean disponible = true;
int ID;
Logger logger;

/**
 * Le constructeur
 * @param queue
 * @param CPU_ID
 * @param logger
 */
public CPU(BlockingQueue<String> queue,int CPU_ID, Logger logger){
    this.disponible = true;
    this.queue = queue;
    this.ID = CPU_ID;
    this.logger = logger;
    System.out.println("CPU [" + this.ID + "] active et disponible!");
    this.logger.log(Level.INFO, "Le CPU : {0} active et disponible!", this.ID);

}
/**
 * La methode execute le thread
 * @param nom
 * @throws IOException
 */
public synchronized void executer(String nom) throws IOException{
    if(this.disponible == true){
    this.disponible = false;

    try{
        Scanner s = new Scanner(nom).useDelimiter("#");
        String le_nom = s.next();
        int tempsExecution = s.nextInt();


       System.out.println("CPU [" + this.ID + "] execute la tache pendant : " + tempsExecution + " secondes >>> Bonjour Mme. " + le_nom + "!");
        
        this.wait(tempsExecution*100);
        this.notify();
        this.disponible = true;
        System.out.println("Tache " + le_nom + " accomplie ! CPU [" + this.ID + "] est disponible!");
        }catch (InterruptedException e){
            System.out.println("Exception: " + e);
            this.logger.log(Level.SEVERE, "Exception : " + e);
        }

    }
}


public synchronized boolean getDisponible(){
    return this.disponible;
}


    @Override
public void run(){
    try {

        String nom;
        while ((nom = queue.take()) != "#STOP#" && this.getDisponible()) {
                try {
                    executer(nom);
                } catch (IOException ex) {
                    Logger.getLogger(CPU.class.getName()).log(Level.SEVERE, null, ex);
                }

    }
    System.out.println("FIN d'execution CPU [" + this.ID +"]");
    this.logger.log(Level.INFO, "FIN d'execution CPU : " + this.ID);
    } catch (InterruptedException e) {
        System.out.println("Exception: " + e);
        this.logger.log(Level.SEVERE, "Exception : " + e);
    }
}
}