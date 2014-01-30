import java.io.*;
import java.util.*;
import java.util.logging.*;

@SuppressWarnings("unchecked")

public class HWFCFS {
    
/**
 * La metode main
 * @param args
 * @throws IOException 
 */
public static void main (String [] args) throws IOException {

TreeMap tm = new TreeMap();
Random rand = new Random();
LogManager lm = LogManager.getLogManager();
Logger logger = Logger.getLogger("HelloworldFCFS");
FileHandler fh = null;
FileWriter fw = new FileWriter("Bonjour.txt");
BufferedWriter bw =new BufferedWriter(fw);

    
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

//File f = new File(args[0]);
File f = new File("ListePersonnes.txt");
FileInputStream f_in = new FileInputStream(f);
BufferedReader br = new BufferedReader(new InputStreamReader(f_in));
while((line = br.readLine()) != null){
Scanner s = new Scanner(line).useDelimiter(" ");
String Mme = s.next();
String nom = s.next() + "#" + (rand.nextInt(25 - 5 + 1) + 5);
int key = s.nextInt();
s.close();	
tm.put(key,nom);
};
br.close();
f_in.close();

}catch(Exception e){
System.out.println("Exception: " + e);
logger.log(Level.SEVERE, "Exception : {0}", e);
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

FCFS Ordonanceur = new FCFS(tm, logger,fw,bw);

try{
Ordonanceur.start();
Ordonanceur.join();
}catch (IllegalThreadStateException e){
System.out.println("Exception: " + e);
logger.log(Level.SEVERE, "Exception : " + e);
}catch (InterruptedException e)	{
System.out.println("Exception: " + e);
logger.log(Level.SEVERE, "Exception : " + e);
}

logger.log(Level.INFO, "Application terminee!");

try {
fh.close();
bw.close();
} catch (Exception e) {
System.out.println("Exception thrown: " + e);
e.printStackTrace();
}
 
}
}



