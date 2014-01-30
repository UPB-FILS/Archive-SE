import java.util.*;
import java.io.*;
import java.text.*;

/**
 * 
 * TP 2 - E/S en Java<br/>
 * Le programme s'utilise : <b>java HelloWorld &lt;nom_de_fichier&gt;</b><br/>
 * Le programme affiche dans un fichier : <b>"Bonjour &lt;nom_de_la_personne&gt; !"</b>, pour chaque ligne trouvee dans le fichier <br/>
 * Les dammes Mme. d'abord apres les monsieurs M. sont salues
 * @author Dragos STOICA
 * 
 * 
 * @version 0.1, 25.Feb.2013 
 */
 public class HelloWorldIO {

	public static void main (String [] args) {
		Buffer buf = new Buffer();

		if (args.length < 1) {
			System.err.println("Utilisation: java HelloWorldIO <nom_de_fichier>");
			System.exit(0);
		}

		try{			
			List<String> listeMadames = new ArrayList<String>();
			List<String> listeMonsieurs = new ArrayList<String>();
            
            String line;
            File f = new File(args[0]);
            FileInputStream f_in = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(f_in));
            GlobalVariables.logger.log("main - debut de la lecture de fichier!");
            while((line = br.readLine()) != null){
              if(line.indexOf("Mme.")!=-1) listeMadames.add(line);
              if(line.indexOf("M.")!=-1) listeMonsieurs.add(line);
            };
            br.close();
            f_in.close();

            GlobalVariables.logger.log("main - fichier d'entree lu!");

			// Creation des threads
			Thread Mme = new Producer(listeMadames.toArray( new String[ listeMadames.size() ] ), buf);
			Thread M = new Producer(listeMonsieurs.toArray( new String[ listeMonsieurs.size() ] ),buf);
			Thread cons = new Consumer(listeMadames.size()+listeMonsieurs.size(), buf);
			
			// L'ordonancement des threads
			try {
				// starting threads
				cons.start();
				Mme.start();
				Mme.join();
				M.start();
				M.join();
				cons.join();
			} catch (InterruptedException e) {
				GlobalVariables.logger.log("main - thread interrompu!");
			}

            GlobalVariables.logger.log("main - threads completes!");
        }catch(Exception e){
            System.out.println("Exception: " + e);
        };
        
        GlobalVariables.logger.fermeLogger();    
  }
 
} 

class Buffer {
  		private String contents;
  		private boolean empty = true;
  		
  		public synchronized void put (String nom) throws InterruptedException { 
  			while (empty == false) { 	//wait till the buffer becomes empty
  				try { 
					wait(); 
				}catch (InterruptedException e) {
					throw e;
				}
  			}
  			contents = nom;
  			empty = false;
  			GlobalVariables.logger.log("Producer: put..." + contents);
  			notify();
  		} 
  		
  		public synchronized String get () throws InterruptedException {
  			while (empty == true)  {	//wait till something appears in the buffer
  				try {
					wait(); 
				}catch (InterruptedException e) {
					throw e;
				}
  			}
  			empty = true;
  			notify();
  			String val = contents;
  			GlobalVariables.logger.log("Consumer: get..." + val);
  			return val;
  		}
}

class Producer extends Thread {
  	private String[] listeNoms;
  	private Buffer prodBuf;
  	
  	public Producer (String[] noms, Buffer buf) {
  		listeNoms = noms;
  		prodBuf = buf;
    }
    
    public void run() {
    	for (int i = 0; i < listeNoms.length; i++) {
    	    try {
    	    	prodBuf.put(listeNoms[i]);
    	    } catch (InterruptedException e) {
				return;
			}	
    	}
    }
  }

class Consumer extends Thread {
  	private int n;
  	private Buffer consBuf;
  	private	PrintWriter pw;

  	public Consumer (int m, Buffer buf) {
  		n = m;
  		consBuf = buf;
		try{
			this.pw = new PrintWriter(new FileWriter("Bonjour.txt"));
			GlobalVariables.logger.log("Consumer - fichier ouvert!");
		}catch(Exception e){
			System.out.println("Consumer: " + e);
		}  		
    }
    
    public void run() {
    	String nom;
    	for (int i = 0; i < n; i++) {
    		try {
    			nom = consBuf.get();
				this.pw.println("Bonjour " + nom + " !");			
				GlobalVariables.logger.log("Consumer ecris:" + nom);
				pw.flush();		

    		}catch (InterruptedException e) {
				System.out.println("Consumer: " + e);
			}catch(Exception e){
				System.out.println("Consumer: " + e);
			}
    	}
		try{
			this.pw.close();
			GlobalVariables.logger.log("Consumer - fichier ferme!");
		}catch(Exception e){
			System.out.println("Consumer: " + e);
		}	
    }
  }

class SimpleLogger{
	PrintWriter pw;
	Locale currentLocale = new Locale("fr","FR");
	
	public SimpleLogger(){
		try{
			this.pw = new PrintWriter(new FileWriter("HelloWorldIO.log"));
			this.log("Logger ouvert!");
		}catch(Exception e){
			System.out.println("SimpleLogger: " + e);
		}
	
	}
	
	public synchronized void log(String msg){
		try{
			Date today;
			String output;
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("yyyy MM dd HH:mm:ss", currentLocale);
			today = new Date();
			output = formatter.format(today);
			this.pw.println("[" + output + "] " + msg);			
			this.pw.flush();
		}catch(Exception e){
			System.out.println("log: " + e);
		}				
	}

	public void fermeLogger(){
		try{			
			this.log("Logger ferme!");
			this.pw.close();
		}catch(Exception e){
			System.out.println("fermeLogger: " + e);
		}
		
	}
}


class GlobalVariables{	
	public static SimpleLogger logger = new SimpleLogger();
}
