/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice.pkg1;

/**
 *
 * @author Bathahi 
 */
public class Exercice1 extends Thread {
  
    
    
   public Exercice1 (String str) {
      super(str);              
   }


    @Override
   public void run() {  
        // la fonction run permet d'afficher le résultat  
        
        
         System.out.println("Bonjour "+ getName()); // le nom du thread

   }
    
  /**
     * @param args the command line arguments
     */
    
   public static void main (String[] args) {
   

       String s [] = {"Bathahi","Ilyass "," Maroc"};
      for(String t: s )
      {     
            new Exercice1(t).start();
            // démarer l'exécution 
       
    
        // TODO code application logic here
    }
}

}
