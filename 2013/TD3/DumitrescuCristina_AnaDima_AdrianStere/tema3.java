import java.io.BufferedReader;
import java.io.FileNotFoundException;
   import java.io.FileReader;
   import java.io.IOException;
   import java.util.StringTokenizer;
   import java.util.TreeMap;
   import java.util.logging.FileHandler; 
   import java.util.logging.Level;
    import java.util.logging.LogManager;
    import java.util.logging.Logger;
    import java.util.logging.XMLFormatter;
        
public class tema3
{

	/**
	* 
	*
	* @author Tudor TURCU
	* 
	* 

	*/
	public static void main(String[] args) throws InterruptedException {
    		
    		
    		
    		LogManager lm = LogManager.getLogManager();
    		final Logger logger = Logger.getLogger("princ");//logger pour enregistrer de possibles erreurs
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
     	logger.log(Level.SEVERE, "Exception : " + e);
    		}
    
    
    		// TODO Auto-generated method stub
    
    class threads extends Thread // thread qui execute pour chaque personne 
    
    {
      String s;
      Integer i;
      
    	public void run()
    	{
    		System.out.println("Fil d'execution pour Mme "+s+" a commence");
    		try { 
    			sleep(i*1000);
     	} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			logger.log(Level.SEVERE, "Exception : " + e);
    		}  
    		System.out.println("Bonjour Mme "+s);
    		
    		
    	}
    	
    	
    }
    		
    
    
     class Bonjour extends Thread
    {
    	TreeMap<Integer, String> tree=new TreeMap<Integer,String>(); // treemap pour pour aficher les nom qui doivent attendre le plus court temps
    	public void run()
    	{
    		BufferedReader in = null;
    		
    		try {
     		in = new BufferedReader(new FileReader("ListeNoms.txt"));
    			} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    			logger.log(Level.SEVERE, "Exception : " + e1);
    			}
    		
    		
    		String strLine;
    		/**
    		* utiliser la methode Tokenizer pour separer les mots de chaque ligne; possibles erreurs traitees avec try-catch
    		*/
    		try {
    		while ((strLine = in.readLine()) != null)
    		{ StringTokenizer st=new StringTokenizer(strLine," ");
    		String tipe=st.nextToken();
    		String nume=st.nextToken();
     		Integer durata=Integer.parseInt(st.nextToken());
     		tree.put(durata, nume);
     
     		}
     		in.close();
     		} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     		logger.log(Level.SEVERE, "Exception : " + e);
     		}
     
     		System.out.println("Le fichier a ete lu");
     		
     	}
     
     	
     
     }		 
     		
     		
     		
     		
     		Bonjour bonj =new Bonjour();
     		bonj.start();
     		bonj.join(); 
     		
     		
     		while(!bonj.tree.isEmpty())
     		{
     			threads tr=new threads();
      		tr.s=bonj.tree.firstEntry().getValue();
     			tr.i=bonj.tree.firstEntry().getKey();
     			tr.start();
      		tr.join();		
     			bonj.tree.remove(bonj.tree.firstKey());  
     			
     			
     		} 
     		logger.log(Level.INFO, "Application terminee!");
     		System.out.println("Application terminee");
     
     
     	}
     
     }