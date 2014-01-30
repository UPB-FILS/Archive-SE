/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworldthread;

/**
 *
 * @author IBM
 */
public class HelloWorldThread extends Thread 
//on fait l'implementation par la classe Thread
{
//sans la fonction run() le programme n'afiche aucune chose
    @Override
    public void run() {
        
        for(int j=1;j<=5;j++)
            System.out.println("Bonjour"+j);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       HelloWorldThread t=new HelloWorldThread();
       t.start();//demarer le thread
       for(int j=1;j<=args.length;j++)
            System.out.println("Bonjour"+j);
        
        
        // TODO code application logic here
    }
}
