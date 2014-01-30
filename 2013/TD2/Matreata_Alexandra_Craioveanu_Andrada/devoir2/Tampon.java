/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoir2;

/**
 *
 * @author Matreata Alexandra
 * @author Craioveanu Andrada
 * @param valoare 
 * @param disp 
 */
public class Tampon {
    private String valoare;
    private boolean disp=false;
    
    
    public synchronized String preluare()
    /**
     * La methode preluare() est utilisé par la classe Consumer 
     * La variable disp dit s'il y a une valeur pour le Consumer 
    */
    {if(!disp)
    {   try{wait();
   
    }catch (InterruptedException e){}
    }
    
    disp=false;
    notify();
   
    return valoare;
    }
    
    
    public synchronized void transmitere(String valoare)
    /** 
     * La methode transmitere() est utilisé par le thread Producer pour transmettre une valeur qui doit etre affiché 
     */
    {if(disp)
    {   try{wait();
     
        }
        catch (InterruptedException e){}
    }
    
    this.valoare=valoare;
    disp=true;
    notify();
    
    
    }
}
