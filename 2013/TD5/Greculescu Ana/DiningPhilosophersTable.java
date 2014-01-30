/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd6;

/**
 *
 * @author ANA
 */
public class DiningPhilosophersTable {
    
    //An array holding all the chopsticks
    private final Chopstick[] chopsticks = new Chopstick[5];

    /*Constructor for the main class
    * Creates all the chopsticks 
    * Creates and starts all the threads*/
    public DiningPhilosophersTable(){
        putChopsticksOnTheTable();
        Thread t1 = new Thread(new Philosopher("First",this.chopsticks[4],this.chopsticks[0]));
        Thread t2 = new Thread(new Philosopher("Second",this.chopsticks[0],this.chopsticks[1]));
        Thread t3 = new Thread(new Philosopher("Third",this.chopsticks[1],this.chopsticks[2]));
        Thread t4 = new Thread(new Philosopher("Fourth",this.chopsticks[2],this.chopsticks[3]));
        Thread t5 = new Thread(new Philosopher("Fifth",this.chopsticks[3],this.chopsticks[4]));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    /*Initialise the chopsticks in the array*/
    private void putChopsticksOnTheTable(){
        for(int i = 0;i < chopsticks.length;i++)
        chopsticks[i]= new Chopstick(); 
    }

    public static void main(String[] args){
        new DiningPhilosophersTable();
    }
}
    
}
