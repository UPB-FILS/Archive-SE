/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listepersonnes;

import java.io.*;

/**
 *Devoir 2 Patron de conception Producer-Consumer
 * Le program s'utilise: <b> le programme lit un fichier d’entrée, nommé ListePersonnes.txt, en format texte, ligne par ligne</b><br/>
 * Le program affiche: <b>le programme doit saluer d'abord les dames et après les monsieur. Ceci doit être visible dans un fichier nommé Bonjor.txt, où nous allons trouvé des lignes correspondant aux modèle, </b><br/>
 * @author Greculescu Ana,Stanescu Alexandra,Nastasa Raluca
 * @version 0.2, 3.03.2013
 */
public class Listepersonnes extends Thread{
public String nom;
public String genre;

    public Listepersonnes(String nom, String genre) {
        this.nom = nom;
        this.genre = genre;
    }

     /**
     * @param args  contient les noms des personnes
     */
    public static void main(String[] args) {
        try{
            Femme femme= new Femme();//se creaza un nou obiect tip Femme
            Homme homme=new Homme();//se creaza un nou obiect de tip Homme
   femme.start();// se porneste firul de executie pentru obiectul "femme"
   femme.join();// se asteapta terminarea firului "femme" pana la pornirea urmatorului fir
   homme.start();// se porneste firul de executie pentru obiectul "homme"
         }
			catch (Exception e1){
			  System.err.println("Error: " + e1.getMessage());
			  }//se identifica si afiseaza eventualele erori
         
        // TODO code application logic here
    }
}
