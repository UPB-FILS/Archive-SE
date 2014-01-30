/*
* TD 3 - Troisieme devoir a SE - Gestion FCFS des processus<br/>
* Le programme lit un fichier d'entree, nomme ListePersonnes.txt, en format texte, ligne par ligne. Chaque ligne doit respecter le modèle suivant: Madame <nom_de_la_personne> <temps_d'arrivee><br/>
* Le programme doit saluer d'abord les dames. Dans un fichier appelle Bonjour.txt, on va trouver les lignes correspondantes au modele: Bonjour  <nom> </br>
* @authors Mirela URSE, Andreea RADU 
* @version 1.7.0, 18.Mar.2013
* 
 */

package helloworldfcfs;

import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Andreas
 */
public class HelloWorldFCFS {

    /**
     * @param args the command line arguments
     */
    public static void main (String [] args) {
            
            
           TreeMap tm = new TreeMap();
           Random rand = new Random();
           LogManager lm = LogManager.getLogManager();
           Logger logger = Logger.getLogger("HelloworldFCFS");
           FileHandler fh = null;
           
           

        try {
				// on cree un logger
                fh = new FileHandler("log.xml");

                lm.addLogger(logger);
                logger.setLevel(Level.INFO);
                fh.setFormatter(new XMLFormatter());

                logger.addHandler(fh);
                logger.setUseParentHandlers(false);
                logger.log(Level.INFO, "Application demarre!");
                
                }
                    
                    catch (Exception e) {
                        
                        System.out.println("Exception thrown: " + e);
                        e.printStackTrace();
                    }


                    try {
                        
                        String line;
						
					//on declare le fichier ListePersonnes.tst comme un fichier du quel on va lire les donnees
                     BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Andreas\\Documents\\NetBeansProjects\\HelloWorldFCFS\\ListePersonnes.txt"));

              while((line = br.readLine()) != null){ // on lit chaque ligne
                        
                        Scanner s = new Scanner(line).useDelimiter(" "); //on separe l'apelatif du nom 
                        String Mme = s.next();    						// et on les memorise dans des variables
                        String nom = s.next() + "#" + (rand.nextInt(25 - 5 + 1) + 5);
                        int key = s.nextInt();
                        s.close();	
                        tm.put(key,nom);
                     };
        
                     br.close();
                    

                    }
                    
                    catch(Exception e){
                        
                            System.out.println("Exception: " + e);
                            logger.log(Level.SEVERE, "Exception : " + e);
                        
                            }

				//la lecture de fichier
                Set set = tm.entrySet();
				// prend un iterateur
                Iterator i = set.iterator();
                // Aficher les elements
                System.out.println("La liste de taches \nTA \tNom \tTE");
                
                while(i.hasNext()) { //on parcoure tout le Map
                    
                        Map.Entry me = (Map.Entry)i.next();
                        System.out.print(me.getKey() + "\t");
                        System.out.println(me.getValue().toString().replace("#","\t")); \\ on affiche chacun
                    }
                
                System.out.println("--------------------------------");

        FCFS Ordonanceur = new FCFS(tm, logger);

                    try{
                        
                        Ordonanceur.start();
                        Ordonanceur.join();
                        
                    }
                    
                    catch (IllegalThreadStateException e){
                        
                        System.out.println("Exception: " + e);
                        logger.log(Level.SEVERE, "Exception : " + e);
                        
                    }
                    
                    catch (InterruptedException e)	{
                           
                        System.out.println("Exception: " + e);
                        logger.log(Level.SEVERE, "Exception : " + e);
                        
                    }

                    logger.log(Level.INFO, "Application terminee!");

                    
                    try {
                        
                        fh.close();            
                       
                    } 
                    
                    catch (Exception e) {
                        
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

		//constructeur
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
                        
                    }
                    
                        catch (IllegalThreadStateException e) {
                                
                                System.out.println("Exception: " + e);
                                this.logger.log(Level.SEVERE, "Exception : " + e);
                            }
}	
                
                System.out.println("--------------------------------");

Set set = this.tm.entrySet();
Iterator i = set.iterator();

                while(i.hasNext()){	
                       
                        boolean disponible = false;
                        for (int k = 0; k < no_CPU; k++)
                        {
                        disponible = disponible || cpu[k].getDisponible();
                        }

                    if (!disponible){
                        
                    try{
                        
                        this.wait(1000);
                        }
                    
                    catch(InterruptedException e){
                            
                            System.out.println("Exception: " + e);
                            this.logger.log(Level.SEVERE, "Exception : " + e);
                        }
                    
                    }
                    else{
                        
                        Map.Entry me = (Map.Entry)i.next();
                            
                        try{	
                            
                        Scanner s = new Scanner(me.getValue().toString()).useDelimiter("#");
                        String le_nom = s.next();
                        int temps_execution = s.nextInt();
                        int temps_demarrage = (int)me.getKey();
                        
                        if (compteurTaches < no_CPU){
                                
                            tf.add(temps_demarrage + temps_execution);
                            }
                                else{
                            
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
                        }
                        
                        catch(InterruptedException e){
                                
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
                            }
                            catch(InterruptedException e){
                                
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
                    BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\Andreas\\Documents\\NetBeansProjects\\HelloWorldFCFS\\Bonjour.txt"));
                        Scanner s = new Scanner(nom).useDelimiter("#");
                        String le_nom = s.next();
                        int tempsExecution = s.nextInt();
                        //int tempsExecution = 2000;
                        System.out.println("CPU [" + this.ID + "] execute la tache pendant : " + tempsExecution + " secondes >>> Bonjour Mme. " + le_nom + "!");
                        //System.out.println("CPU [" + this.ID + "] : " + le_nom + " > " + s.next());
                        out.write("Bonjour"+" "+le_nom +"!"+tempsExecution);
                        out.close();
                        this.wait(tempsExecution*100);
                        this.notify();
                        this.disponible = true;
                        System.out.println("Tache " + le_nom + " accomplie ! CPU [" + this.ID + "] est disponible!");
            
            }
                catch (InterruptedException e){
                    
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
                            
                    } 
                
                catch (InterruptedException e) {
                    
                        System.out.println("Exception: " + e);
                        this.logger.log(Level.SEVERE, "Exception : " + e);
                     }
}

            
}
