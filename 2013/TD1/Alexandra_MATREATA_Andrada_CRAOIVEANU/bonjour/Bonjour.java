/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour;

/**
 *Le program a été realisé le 02.03.2013
 * <p>
 * compilé avec java 
 * <p>
 * apellé avec Java Bonjour [liste_de_nomme}
 * @author Matreata Alexandra
 * @author Craioveanu Andrada
 * 
 */
public class Bonjour {

    /**
     * Le programe affiche "Bonjour" + [liste_de_nomme]
     * @param args the command line arguments
     * @param fir le thread qui est utilisé
     * @see v  
     * 
     */
    public static void main(String[] args) {
        // TODO code application logic here
         fir f=new fir();
       
        f.start();
        /**
         * détermine ce fil de commencer l'exécution, la machine virtuelle Java appelle la méthode run de ce fil
        */
    }
}
