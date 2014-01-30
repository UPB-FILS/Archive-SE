/*TD5-Devoir3-programme Serveur qui fournit ses services sur le port 7777<br/>
 * Le programme s'utilise:<b>Le Serveur traite les Clients par le biais des fils d’exécution et repond aux Clients connectes par "Bonjour Mme. <nom_de_la_personne>!" ou "Bonjour M. <nom_de_la_personne>!" selon le message du Client</b><br/>
 * Le programme affiche:<b>"Bonjour Mme./M. &lt;nom_de_la_personne&gt; !" sur la console et dans un fichier texte BonjourServeur.txt</b>
 * @author Mirela Urse, Andreea Radu
 * @version 7.2, 22.Avril.2013
 */
package serveur;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class MyLogger {

  public static void main(String[] args) throws IOException {

	// on cree un objet de type logger
    Logger logger = Logger.getLogger("MyLog");
    FileHandler fh;

    try {

      // This block configure the logger with handler and formatter
      fh = new FileHandler("c:\\MyLogFile.log", true);
      logger.addHandler(fh);
      logger.setLevel(Level.ALL);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);

      // the following statement is used to log any messages   
      logger.log(Level.WARNING,"My first log");

    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  
  }

}
