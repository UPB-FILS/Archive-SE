/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tdd6;

import java.util.Random;

/**
 *
 * @author ANA
 */
public class Philosopher extends Thread{

    private static final int MAX_EATING_TIME = 1000;
    private static final int MAX_THINKING_TIME = 800;
    private final Random randomise = new Random();
    private final Chopstick _leftChopstick;
    private final Chopstick _rightChopstick;
    private final String _name;
    private State _state;

    /* Enumeration class that holds 
    * information about the possible 
    * Philosopher's states 
    */
    public enum State {
        EATING, THINKING, WAITING
    }

    /*
    * Main constructor for the Philosopher class
    * @param name   the name of the Philosopher
    * @param leftChopstick  the chopstick that is currently on the left of the Philosopher
    * @param rightChopstick the chopstick currently on the right of the Philosopher
    * 
    */
    public Philosopher(String name, Chopstick leftChopstick, Chopstick rightChopstick) {
        System.out.println(name +"Started");
        this._leftChopstick = leftChopstick;
        this._rightChopstick = rightChopstick;
        this._name = name;
    }

    /*
    * The method eat that uses two chopsticks. It blockes the two Chopstick
    * objects so they could not be changed then it changes their state 
    * as well as the state of the philosopher
    * At the end of the method, the chopsticks' state is reverted and
    * the Philosopher goes into the Thinking state 
    */
    private void eat() throws InterruptedException {

        synchronized(_leftChopstick){
        while(_leftChopstick.isUsed() || _rightChopstick.isUsed())      
            try{
                this.setPhilosopherState(Philosopher.State.WAITING);
                _leftChopstick.wait();
            }catch (InterruptedException e){}
                synchronized(_rightChopstick) {
                try{
                    Thread.sleep(1);
                    _leftChopstick.setUsed(true);
                    _rightChopstick.setUsed(true);
                    this.setPhilosopherState(Philosopher.State.EATING);
                    Thread.sleep(randomise.nextInt(MAX_EATING_TIME));
                }
                finally {
                    _leftChopstick.setUsed(false);
                    _rightChopstick.setUsed(false); 
                    _leftChopstick.notify();
                    _rightChopstick.notify();   
                }
                }
            }

        think();
    }

    /*
    * This method only changes the state 
    * of the Philosopher to Thinking
    */
    private void think() throws InterruptedException{
        this.setPhilosopherState(Philosopher.State.THINKING);
        Thread.sleep(randomise.nextInt(MAX_THINKING_TIME));
    }

    /*
    * Set the current state of the Philosopher
    */
    private void setPhilosopherState(State state){
        this._state = state;
        System.out.println(System.currentTimeMillis() +":"+ _state +", "+ _name+";");
    }

    /*
    * Get the current state of the Philosopher
    */
    public State getPhilosopherState(){
        return _state;
    }

    /*
    * The method is invoked with the start of the thread
    * and runs the eat function for 10 times
    */
    public void run(){
        for(int i =0; i< 10;i++){
            try {
                eat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Succesfully finished: " +_name);
    }
}