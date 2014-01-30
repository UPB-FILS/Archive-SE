/*
* TD 2 - Deuxieme devoir a SE - Patron de conception Producer-Consumer<br/>
* Le programme lit un fichier d'entree, nomme noms.txt, en format texte, ligne par ligne. Chaque ligne doit respecter le modèle suivant: Madame <nom_de_la_personne> ou Monsieur <nom_de_la_personne><br/>
* Le programme doit saluer d'abord les dames et apres les monsieurs. Dans un fichier appelle bonjour.txt, on va trouver les lignes correspondantes au modele: Bonjour, madame <nom>, Bonjour, monsieur <nom></br>
* @authors Mirela URSE, Andreea RADU 
* @version 1.7.0, 18.Mar.2013
* 
 */

package setema2bonjourmadamemonsieurfichier;

import java.io.*;

public class SEtema2BonjourMadameMonsieurFichier extends Thread
{
    public static void main(String[] args) 
    {
        try
        {
            Madames mme = new Madames(); // on fait un objet de type "madames"
            Monsieurs mons = new Monsieurs(); // on fait un objet de type "monsieurs"
            mme.start(); // on demarre le fil d'execution pour "madames"
            mme.join(); // on attend que "madame" finit l'execution
            mons.start(); // on demarre le fil d'execution pour "monsieurs"
        }
        catch (Exception e1)
        {
            System.err.println("Error: " + e1.getMessage()); // s'il y a des erreurs, on affiche un message 
        }
    }
}

