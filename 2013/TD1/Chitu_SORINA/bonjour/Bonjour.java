/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour;

/**
 * Premier devoir</br>
 * Le programme utilise <b>la classe Thread</b></br>
 * Le programme affiche sur l'ecran <b>une liste du type "Bonjour, <nom de la personne#>!"</b>
 * @author Sorina CHITU
 * @version 0.2, 25.Feb.2013
 */
public class Bonjour extends Thread{
      public Bonjour(String message) {
        super(message); /* le constructeur du Thread, la superclasse */         
           } 
      
      public static void main(String[] args) {
         /* if(args.length ==0)
              System.out.println("On n'a pas un nombre suffisant des arguments");
              System.exit(0);/*on abandonne l'application parce que les conditions
                                de fonctionnement ne sont pas rempliees*/      
          
         
          String s[] = {"Sorina", "Mihaela", "Robert", "Cristian", "Cerasela"}; /*on ajoute des elements dans un String*/
            for(String i:s){     /*avec une variable i, utilisee pour parcourir la liste, 
                                   on va prendre chaque element de s, jusqu'a la fin du String*/
            new Bonjour(i).start();
            }
      }
    @Override /*il faut ajouter l'adnotation Override pour que le programme fonctionne*/
           public void run() {         /* la methode principale du thread*/
            System.out.println("Bonjour, "+ getName()+"!"); /*on affiche sur une ligne distincte le message demande,
                                                         en utilisant le nom du fil d'execution*/
   }
}
