/*TD3-Devoir3-le programme lit les dames en fonction du temps d'arrivee<br/>
 * Le programme s'utilise:<b>le programmmelit les donnees d'un fichier texte et ecrit dans un autre fichier texte "Bonjour"+"Mme." +nom_de_la_personne,avec priorite en fonction du temps d'arrivee </b><br/>
 * Le programme affiche:<b>"Bonjour Mme. &lt;nom_de_la_personne&gt; !" dans un fichier texte Bonjour.txt</b>
 * @author Oana HOTESCU, LAvinia STOIAN, Florin ZAMFIR
 * @version 7.2, 1.Avr.2013
 */


package helloworldfcfs;
/**
 *
 * @author Oana HOTESCU, LAvinia STOIAN, Florin ZAMFIR
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import java.util.logging.SimpleFormatter;

@SuppressWarnings("unchecked")

public class HelloWorldFCFS {

    public static void main (String [] args) {

      TreeMap tm = new TreeMap(); 
      Random rand = new Random();
      LogManager lm = LogManager.getLogManager();
      Logger logger = Logger.getLogger("HelloworldFCFS");
      FileHandler fh = null;
      
      try {
        fh = new FileHandler("log.xml");

        lm.addLogger(logger);
        logger.setLevel(Level.INFO);
        fh.setFormatter(new XMLFormatter());

        logger.addHandler(fh);
        logger.setUseParentHandlers(false);
        logger.log(Level.INFO, "Application demarre!");
      } catch (Exception e) {
        System.out.println("Exception thrown: " + e);
        e.printStackTrace();
      }
            
            
      try {
        String line;
       
        BufferedReader in = new BufferedReader(new FileReader("C:\\Documents and Settings\\Oana User\\My Documents\\NetBeansProjects\\HelloWorldFCFS\\ListePersonnes.txt"));
        
        while((line = in.readLine()) != null){
          Scanner s = new Scanner(line).useDelimiter(" ");
          String Mme = s.next();
          String nom = s.next() + "#" + (rand.nextInt(25 - 5 + 1) + 5);
          int key = s.nextInt();
          s.close();          
          tm.put(key,nom);
        };
        in.close();
        
        
      }catch(Exception e){
        System.out.println("Exception: " + e);
        logger.log(Level.SEVERE, "Exception : " + e);
      }

      //lecture finie ...
      Set set = tm.entrySet(); 
      // Get an iterator 
      Iterator i = set.iterator(); 
      // Display elements 
     System.out.println("La liste de taches \nTA \tNom \tTE");
      while(i.hasNext()) { 
        Map.Entry me = (Map.Entry)i.next(); 
        System.out.print(me.getKey() + "\t"); 
       System.out.println(me.getValue().toString().replace("#","\t")); 
      }
      System.out.println("--------------------------------");
      
      FCFS Ordonanceur = new FCFS(tm, logger);
      
      try{
        Ordonanceur.start();
        Ordonanceur.join();
      }catch (IllegalThreadStateException e){
        System.out.println("Exception: " + e);
        logger.log(Level.SEVERE, "Exception : " + e);
      }catch (InterruptedException e)  {
        System.out.println("Exception: " + e);
        logger.log(Level.SEVERE, "Exception : " + e);
      }
      
      logger.log(Level.INFO, "Application terminee!");
      
      try {
        fh.close();
      } catch (Exception e) {
        System.out.println("Exception thrown: " + e);
        e.printStackTrace();
      }
 
    }
}


class FCFS extends Thread{

  TreeMap tm;
  int no_CPU = 2;
  int capacite = 10;
  BlockingQueue<String> queue = new ArrayBlockingQueue<String>(capacite);
  Logger logger;
  ArrayList tf = new ArrayList();
  
  public FCFS(TreeMap tm, Logger logger){
    this.tm = tm;
    this.logger = logger;
    System.out.println("Ordonanceur FCFS active !");
    this.logger.log(Level.INFO, "Ordonanceur FCFS active !");
  }

 public synchronized void run(){
    
    int compteurTaches = 0;
    
    CPU[] cpu = new CPU[no_CPU];
    
    for (int k = 0; k < cpu.length; k++) {
      cpu[k] = new CPU(queue, k, this.logger);
      try{
        cpu[k].start();
      }catch (IllegalThreadStateException e) {
        System.out.println("Exception: " + e);
        this.logger.log(Level.SEVERE, "Exception : " + e);
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
          this.logger.log(Level.SEVERE, "Exception : " + e);
        }
      }else{
        Map.Entry me = (Map.Entry)i.next();
        try{                
          Scanner s = new Scanner(me.getValue().toString()).useDelimiter("#");
          String le_nom = s.next();
          int temps_execution = s.nextInt();
          int temps_demarrage = (int)me.getKey();
          if (compteurTaches < no_CPU){
            tf.add(temps_demarrage + temps_execution);
          }else{
            Comparator comparator = Collections.reverseOrder();
            Collections.sort(tf,comparator);
            
            temps_demarrage = (int)tf.get(0);
            for (int m = 1; m < no_CPU; m++){
                temps_demarrage = Math.min(temps_demarrage, (int)tf.get(m));
            }
            if (temps_demarrage <= (int)me.getKey()) temps_demarrage = (int)me.getKey();
            tf.add(temps_demarrage+temps_execution);
          }
          compteurTaches++;
          System.out.println("Tache : " + le_nom +
                             " | TA : " + me.getKey() + 
                             " | TE : " + temps_execution + 
                             " | TD : " + temps_demarrage + 
                             " | TF : " + (temps_demarrage+temps_execution) + 
                             " | ZZ : " + (temps_demarrage - (int)me.getKey()) );
          this.queue.put(me.getValue().toString());
          //this.queue.put(le_nom +"#TA : " + me.getKey() + " TE : " + temps_execution + " TD : " + temps_demarrage + " TF : " + (temps_demarrage+temps_execution));
        }catch(InterruptedException e){
          System.out.println("Exception: " + e);
          this.logger.log(Level.SEVERE, "Exception : " + e);
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
      this.logger.log(Level.SEVERE, "Exception : " + e);
    }
    this.logger.log(Level.INFO, "Ordonanceur FCFS terminee!");
  }  
}


class CPU extends Thread{

  BlockingQueue<String> queue;
  boolean disponible = true;
  int ID;
  Logger logger;

  
  public CPU(BlockingQueue<String> queue, int CPU_ID, Logger logger){
    this.disponible = true;
    this.queue = queue;
    this.ID = CPU_ID;
    this.logger = logger;
    System.out.println("CPU [" + this.ID + "] active et disponible!");
    this.logger.log(Level.INFO, "Le CPU : " + this.ID + " active et disponible!");
  }

  public synchronized void executer(String nom) throws IOException{
    if(this.disponible == true){
      this.disponible = false;
      
      try{
        BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Oana User\\My Documents\\NetBeansProjects\\HelloWorldFCFS\\Bonjour.txt"));
        Scanner s = new Scanner(nom).useDelimiter("#");
        String le_nom = s.next();
        int tempsExecution = s.nextInt();
        //int tempsExecution = 2000;
        System.out.println("CPU [" + this.ID + "] execute la tache pendant : " + tempsExecution + " secondes >>> Bonjour Mme. " + le_nom + "!");
        //System.out.println("CPU [" + this.ID + "] : " +  le_nom + " > " + s.next());
        out.write("Bonjour "+le_nom+"\n");
        
        this.wait(tempsExecution*100);
        this.notify();
        this.disponible = true;
        System.out.println("Tache " + le_nom + " accomplie ! CPU [" + this.ID + "] est disponible!");
        out.close();
      }catch (InterruptedException e){
        System.out.println("Exception: " + e);
        this.logger.log(Level.SEVERE, "Exception : " + e);
      }
      
    }
  }

  public synchronized boolean getDisponible(){
    return this.disponible;
  }
  
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