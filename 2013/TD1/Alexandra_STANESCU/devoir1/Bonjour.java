/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package devoir1;

/**
 * Mon premier devoir
 * Alexandra Stanescu_groupe 1221F
 */
public class Bonjour extends Thread { // Bonjour- le nom de la classe

   public Bonjour(String str) {
      super(str);              // le constructeur du fil d'execution //Thread(String)
                               // de la  superclasse Thread
   }



    @Override
   public void run() {         // la methode principale du fil d'execution curent

         System.out.println("Bonjour "+ getName()); // le nom du thread


   }

   public static void main (String[] args) {
        /** le type static- la machine utilise le meme code pour toutes les instances de la classe
        *String []- une chaine de Strng- array, un  type de donne englobe par le langage
++         */

       String s[] = {"Alexandra","Dragos "," Michel"};
    for(String x: s){       //on va utiliser de  "s" chaque element, a son tour, jusqu'a la fin du String ( on utlise x auxiliaire pour parcurir le nom de la liste)
          new Bonjour(x).start(); //le lancement en execution d'un nouveau fil
    }

}
}


    


