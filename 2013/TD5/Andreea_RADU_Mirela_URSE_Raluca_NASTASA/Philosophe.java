
package V1;

import java.util.Random;

class Philosophe extends Thread {
    
    int max_mange_time=1000;
    int max_pense_time=800;                         
    private final Random randomise = new Random();
    Baguette droit;
    Baguette gauche;
    String nom;
    private State state;
     
          
    public enum State {
    
           mange,pense, atteindre //la classe contient des informations sur l'etat possible des philosohpes
    }
    
    public Philosophe(Baguette droit, Baguette gauche,String nom) { //constructorul clasei "Philosophe"
        System.out.println(nom+"Demarage");
            this.droit=droit; //fourchette du droite
            this.gauche=gauche; //fourchette du gauche                
            this.nom=nom; le nom du philosophe
    }
    
    /* La methode mange utilise 2 fourchettes. Elle bloque les deux fourchettes 
		* pour qu'elle ne soient pas changes que dans le moment 
		* que les philosophes changent leur etat
		* A la fin de la methode, les philosophes entres dans l'etat de pensement
     * */
    
     private void mange() throws InterruptedException {

        synchronized(gauche){
        while(gauche.isUsed() || droit.isUsed()) {                          
                try{
                    this.setPhilosopherState(Philosophe.State.atteindre);
                    gauche.wait();
                }catch (InterruptedException e){}
            }
                synchronized(droit) {
                try{
                    Thread.sleep(1);
                    gauche.setUsed(true);
                    droit.setUsed(true);
                    this.setPhilosopherState(Philosophe.State.mange);
                    Thread.sleep(randomise.nextInt(max_mange_time));
                }
                finally {
                   gauche.setUsed(false);
                    droit.setUsed(false); 
                    gauche.notify();
                    droit.notify();   
                }
                }
            }

        pense();
    }

    /*
    * Methode qui change l'etat des philosophes en "pense" 
    */
     
    private void pense() throws InterruptedException{
        this.setPhilosopherState(Philosophe.State.pense);
        Thread.sleep(randomise.nextInt(max_pense_time));
    }

    /*
    * set l'etat actuelle des philosophes
    */
    private void setPhilosopherState(State state){
        this.state = state;
        System.out.println(System.currentTimeMillis() +":"+ state +", "+ nom+";");
    }

    
    public State getPhilosopherState(){
        return state;
    }

    /*
    * La methode est appelle lorsque le thread est demarre et appelle la fonction "mange" 10 fois
    */
    public void run(){
        for(int i =0; i< 10;i++){
            try {
                mange();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("final: " +nom);
    }
    
   
    }

