/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bonjour;

/**
 ** Un thread est un fil d'execution dans un program
 * <p>
 * Chaque thread a une priorité. Threads avec une priorité plus élevée sont exécutés avant à des fils ayant une priorité inférieure.
 * @author Matreata Alexandra 
 * @author Craioveanu Andrada
 */
public class fir extends Thread{
    /**
     * Le programe affiche "Bonjour" + [liste_de_nomme] à l'aide d'un vecteur String qui contient les noms qui doivent être affichées.
     * @param  le vecteur des noms 
     * @param i le compteur à l'aide de qui on parcouru le vecteur 
     */
    public String [] v={"Adrian","Alex","Mirela","Carmen"};
    private int i;

    public fir() {
    }
    
    
    
    @Override
    public void run ()
    /**
     * Cette methode passe par le vecteur de noms et si la longueur de vecteur est plus grande que 1, elle affiche "Bonjour" + nomme 
     * <p>
     * Si le vecteur a une longueur plus petite que 1, on appelle la methode exit et on affiche un message
     */
    {
    for(i=0;i<v.length;i++)
    {if (v.length < 1) {
    System.err.println("Nu exista nume!");
    System.exit(0);
    }
// args[0] est le 1er parametre
        System.out.println("Buna ziua, "+v[i]+"!");}
}
    
}
