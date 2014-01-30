import java.util.Arrays;

/** liste des Fourchettes que doivent se partager les philosophes*/
public class Fourchettes {
	/** tableau d'occupation des fourchettes false = occupee, true = libre*/
     boolean[] lesFourchettes;
    /** nb de fourchettes*/
     int taille;
    
    /** constructeur initialisant la taille et le tableau des fourchettes a true*/
    public Fourchettes(int _taille) {
    	lesFourchettes = new boolean[_taille];
    	Arrays.fill(lesFourchettes, true);
        taille = _taille;
    }
    
    /** fonction appelee par un processus philosophe no. <br>
     * Si la fourchette de gauche (no) et de droite (no+1) est libre alors le philosophe les prend, 
     * sinon, il est mis en attente
     * @param no no du philosophe demandant les fourchettes*/
    public synchronized void prendre(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;    	
        while (!lesFourchettes[gauche] || !lesFourchettes[droite]) {            
            try   {  wait();  }
            catch (InterruptedException e) {}
        }
        lesFourchettes[gauche] = false;
        lesFourchettes[droite] = false;
    }
    
    /** fonction appelee par un processus philosophe no. <br>
     * libere la fourchette de gauche (no) et de droite (no+1) <br> 
     * et reveille les processus en attente sur les fourchettes
    * @param no no du philosophe deposant les fourchettes*/
    public synchronized void deposer(int no) {
    	int gauche = no;
    	int droite = (no+1)%taille;    	
        lesFourchettes[gauche] = true;
        lesFourchettes[droite] = true;
        notifyAll(); // reveille les processus en attente de fourchettes
    }       
    
    /** retourne sous forme de chaine l'etat des differentes fourchettes*/
    public String toString() {
        String chaine = "";
        for(int i=0; i<taille; i++)
            chaine = chaine + lesFourchettes[i] + " ; ";
        return chaine;
    }


}
