/*
* TD 5 - Le partage des ressources <br/>
* Le programme lit un fichier d'entree, nomme ListePersonnes.txt, en format texte, ligne par ligne. Chaque ligne doit respecter le modèle suivant: Madame <nom_de_la_personne> <temps_d'arrivee><br/>
* Le programme doit saluer d'abord les dames. Dans un fichier appelle Bonjour.txt, on va trouver les lignes correspondantes au modele: Bonjour  <nom> </br>
* @authors Mirela URSE, Andreea RADU 
* @version 1.7.0
* 
 */
package V1;

class Diner {
    public static void main(String [] argv) {
	
				//on declare des objets de type Baguette 
				
                Baguette b1 = new Baguette();
                Baguette b2 = new Baguette();
                Baguette b3 = new Baguette();
                Baguette b4 = new Baguette();
                Baguette b5 = new Baguette();
                
				//on declare des objets de type Philosophe, avec des parametres
				
                Philosophe p1 = new Philosophe(b1,b2,"P1");
                Philosophe p2 = new Philosophe(b2,b3,"P2");
                Philosophe p3 = new Philosophe(b3,b4,"P3");
                Philosophe p4 = new Philosophe(b4,b5,"P4");
                Philosophe p5 = new Philosophe(b5,b1,"P5");
                
				//on demarre les 5 threads
				
                p1.start();
                p2.start();
                p3.start();
                p4.start();
                p5.start();
    }
}