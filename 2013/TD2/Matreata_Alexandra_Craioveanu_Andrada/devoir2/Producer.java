/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

import java.util.Vector;





/**
 * Le programme affiche : <b>"Bonjour M.  &lt;nom_de_la_personne&gt; !"</b>, pour chaque ligne trouvee dans le fichier 
 * @author Matreata Alexandra
 * @author Craioveanu Andrada
 * @param t le tampon 
 * @param v le vecteur de strings 
 */
public class Producer extends Thread{
    
    private Tampon t;
    
    public static String [] v=new String[100];
   private static int i=0;
   static int j=0;
   
    public Producer(Tampon t) {
    this.t=t;
   
    }
    // La methode functie(String) passe par un string et affiche "Bonjour" + [string] si le string est non-nulle et affiche un message si le string est nulle.
    public void functie(String u)
    {   
    if (u==null) {
    System.err.println("Nu exista nume!");
    System.exit(0);
    }
    System.out.println("Bonjour, "+u+"!");
    v[i]=u;
    i++;}
    
    
    @Override
    public void run ()
     /**  Cette methode  utilise la methode transmitere() de la classe Tampon, pour transmettre a le Consumer la ligne de le fichier text qui doit etre affiche et mis le thread dans le sleep pour une periode de certains milisecondes  
     */
    {     while(v[j]!=null)
            { 
                
            t.transmitere(v[j]);
            System.out.println(j);
            System.out.println("Producator a transmis:"+v[j]);
            j++;
            try
                {sleep((int)(Math.random()*3000));}
            catch(InterruptedException e){}
    
            }
   
}


}

